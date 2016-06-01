#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include "index.h"

int contains_word(struct indexstruct* myindex, char* word){
    if (myindex != NULL){
        if (myindex->wordlist != NULL){
            struct wordstruct* tempword;
            tempword = myindex->wordlist;
            while(tempword != NULL){
                if(strcmp(tempword->word, word) == 0){
                    return 1;
                }
                tempword = tempword->next;
            }
        }
    }
    return 0;
}

struct wordstruct* contains_word_s(struct indexstruct* myindex, char* word){
    if (myindex != NULL){
        if (myindex->wordlist != NULL){
            struct wordstruct* tempword;
            tempword = myindex->wordlist;
            while(tempword != NULL){
                if(strcmp(tempword->word, word) == 0){
                    return tempword;
                }
                tempword = tempword->next;
            }
        }
    }
    return NULL;
}

int contains_file(struct wordstruct* myword, char* filename){
    if (myword != NULL){
        if (myword->filelist != NULL){
            struct filestruct* tempfile;
            tempfile = myword->filelist;
            
            while(tempfile != NULL){
                if(strcmp(tempfile->filename, filename) == 0){
                    return 1;
                }
                tempfile = tempfile->next;
            }
        }
    }
    return 0;
}

struct filestruct* contains_file_s(struct wordstruct* myword, char* filename){
    if (myword != NULL){
        if (myword->filelist != NULL){
            struct filestruct* tempfile;
            tempfile = myword->filelist;
            while(tempfile != NULL){
                if(strcmp(tempfile->filename, filename) == 0){
                    return tempfile;
                }
                tempfile = tempfile->next;
            }
        }
    }
    return NULL;
}

int insertfile(struct wordstruct* myword, struct filestruct* myfile){
    struct filestruct* temp;
    struct filestruct* previous;
    temp = myword->filelist;
    if(temp == NULL){
        myword->filelist = myfile;
        return 1;
    }else{
        while(temp != NULL && strcmp(temp->filename, myfile->filename) > 1){
            previous = temp;
            temp = temp->next;
        }
        if(previous == NULL){
            myfile->next = temp;
            myword->filelist = myfile;
        }else if (temp == NULL){
            previous->next = myfile;
        }else{
            previous->next = myfile;
            myfile -> next = temp;
        }
        return 1;
    }
    return 0;
}

int insertword(struct indexstruct* myindex, struct wordstruct* myword){
    struct wordstruct* temp;
    struct wordstruct* previous;
    temp = myindex->wordlist;
    if(temp == NULL){
        myindex->wordlist = myword;
        return 1;
    }else{
        while(temp != NULL && strcmp(temp->word, myword->word) < 1){
            previous = temp;
            temp = temp->next;
        }
        if(previous == NULL){
            myword->next = temp;
            myindex->wordlist=myword;
        }else if(temp == NULL){
            previous->next = myword;
        }else{
            previous->next = myword;
            myword->next = temp;
        }
        return 1;
    }
    return 0;
}

int add_word(struct indexstruct* myindex, char* word, char* filename){
    if(myindex->wordlist == NULL){//empty wordlist
        struct wordstruct* newword = malloc(sizeof(struct wordstruct));
        newword->word = word;
        struct filestruct* newfile = malloc(sizeof(struct filestruct));
        newfile->filename = filename;
        newfile->count = 1;
        newword->filelist = newfile;
        myindex->wordlist = newword;
        return 1;
    }else if(contains_word(myindex, word) == 1){//word contained
        struct wordstruct* wordlocation = contains_word_s(myindex, word);
        if(contains_file(wordlocation, filename) == 1){//file contained
            struct filestruct* filelocation = contains_file_s(wordlocation, filename);
            filelocation->count=filelocation->count+1;
            return 1;
        }else{//file not contained
            struct filestruct* newfile = malloc(sizeof(struct filestruct));
            newfile->filename = filename;
            newfile->count = 1;
            insertfile(wordlocation, newfile);
            return 1;
        }
    }else{//word not contained
        struct wordstruct* newword = malloc(sizeof(struct wordstruct));
        newword->word = word;
        struct filestruct* newfile = malloc(sizeof(struct filestruct));
        newfile->filename = filename;
        newfile->count = 1;
        newword->filelist = newfile;
        int insertval = insertword(myindex, newword);
        return insertval;
    }
    return 0;
}

void print_index(struct indexstruct* myindex){
    struct wordstruct* tempword;
    tempword = myindex->wordlist;
    struct filestruct* tempfile;
    while(tempword != NULL){
        tempfile = tempword->filelist;
//printf("\n\"%s\" -> ", tempword->word);
        while(tempfile != NULL){
//printf("(\"%s\", \"%d\")", tempfile->filename, tempfile->count);
            if(tempfile->next != NULL ){
                printf(", ");
                
            }
            tempfile = tempfile ->next;
        }
        tempword=tempword->next;
    }
    printf("\n");
}

void print_index_to_file(struct indexstruct* myindex, FILE* fp){
    struct wordstruct* tempword;
    tempword = myindex->wordlist;
    struct filestruct* tempfile;
    int linecount = 0;
    while(tempword != NULL){
        tempfile = tempword->filelist;
printf("<list> %s\n", tempword->word);
        linecount = 0;
        fprintf(fp, "<list> %s\n", tempword->word);
        while(tempfile != NULL){
            if(linecount==5){
                printf("\n");
                fprintf(fp,"\n");
                linecount = 0;
            }else{
                linecount++;
            }
printf("%s %d ", tempfile->filename, tempfile->count);
            fprintf(fp, "%s %d ", tempfile->filename, tempfile->count);
            tempfile = tempfile ->next;
        }
        fprintf(fp, "\n</list>\n");
        printf("\n</list>\n");
        tempword=tempword->next;
    }
}

void indexfile(char* filein, char* fileout, struct indexstruct* myindex){

    FILE* fp_in;
    if((fp_in = fopen(filein,"r"))){
        //no problem opening input
    }else{
        printf("error opening input file %s, aborting\n", filein);
    }
    //input ok
    int filelength=0;
    char nextChar = getc(fp_in);
    while(nextChar!=EOF){
        filelength++;
        nextChar = getc(fp_in);
    }
    char* carray = malloc(sizeof(char)*filelength);
    //now have length of input file
    int indexchar = -1;
    fclose(fp_in);
    fp_in = fopen(filein,"r");
    //reopen input file at beginning
    
    nextChar = getc(fp_in);
    while(nextChar!=EOF){
        if(
           (nextChar > 47 && nextChar < 57) ||
           (nextChar > 64 && nextChar < 91) ||
           (nextChar > 96 && nextChar < 123)
           ){
            if(indexchar == -1)
                indexchar = 0;
            if(nextChar > 64 && nextChar < 91)
                nextChar +=32;
            carray[indexchar] = nextChar;
            indexchar++;
        }
        else{
            if(indexchar != 0){
                carray[indexchar] = '\0';
                char* newword = malloc(sizeof(char)*strlen(carray));
                strcpy(newword, carray);
                add_word(myindex, newword, fileout);
                indexchar=0;
                while(carray[indexchar]!='\0'){
                    carray[indexchar] = '\0';
                    indexchar++;
                }
                indexchar=0;
            }
            //	insert into data structure instead
        }
        //if carray[indexchar-1] == ' ' do nothing
        nextChar = getc(fp_in);
    }
}

void indexdir(char* directoryin, struct indexstruct* myindex){
    DIR * dp;
    struct dirent *ep;
    FILE * fp;
    dp = opendir(directoryin);
    if(dp != NULL){
        while((ep = readdir(dp)) != NULL){
            if(strcmp(ep->d_name, "..") != 0 && strcmp(ep->d_name, ".") != 0)
                indexdir(ep->d_name, myindex);
        }
        closedir(dp);
    }else{
        fp = fopen(directoryin, "r");
        if(fp != NULL){
            if(strcmp(directoryin, "index")!=0){
                indexfile(directoryin, directoryin, myindex);
            }
        }
    }
}

int main(int argc, char* argv[]){

    if(argc!=3)
        printf("Usage:\nindex <inverted-index filename> <directory or filename>\n");
    else{

    struct indexstruct* myindex = malloc(sizeof(struct indexstruct));

    indexdir(argv[2], myindex);
        //
          //  print_index(myindex);
        //
//    indexfile(argv[1], argv[1], myindex);
    
//    print_index(myindex);
//    FILE* fp_out = fopen(argv[2], "w");

    
    FILE * op = fopen(argv[1],"w");
    print_index_to_file(myindex, op);
    }
    return 0;
}