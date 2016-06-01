#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include "indexer.c"
#include "tokenizer.c"
#include "indexer.h"

#define KRED "\x1B[31m"
#define KNORM "\x1B[0m"
#define KGREEN "\x1B[32m"



void
printlist()
{
	;
}

void writeFile(char *pathName, SortedList1Ptr SL)
{
	FILE *wf = fopen(pathName, "w");
	if (wf == NULL)
	{
		printf("Error Opening File\n");
		exit(1);
	}
	//printf(SL->root->token);
	//Node1 *temp = SL->root;
	/*while(temp!=NULL)
	{
	printf("TOKEN: %s\n", temp->token);
	temp = temp->next;
	}*/	

	fprintf(wf, "DOES THIS WORK?");
	fclose(wf);

}
//Check Directory: returns 1 if file, 0 if directory, -1 if other file
int checkDir(char *pathName)
{
	struct stat checkDir;
	if(stat(pathName,&checkDir)==0)
	{
		if(checkDir.st_mode & S_IFDIR)
		{
			//printf("Prints: Directory:%s\n",pathName);
			return 0;
		}
		if(checkDir.st_mode & S_IFREG)
		{
			//printf("Prints: Regular File%s\n",pathName);
			return 1;
		}
	}	
	//printf("Prints: Other: %s\n",pathName);
	return -1;
}

void addToList(char *fS, SortedList1Ptr SL, char *pathName)
{
			TokenizerT* tokenizer = TKCreate(" ", fS);
			Node1* temp;

			if(tokenizer == NULL) {
				printf("Error: unable to create tokenizer\n");
			}
			char* token = NULL;
			while((token = TKGetNextToken(tokenizer)) != NULL) {
			if(SL==NULL)
			{
				printf("SL CREATED\n");
				SL = SLCreate(token, pathName);
				//printf(SL->root->token);
				temp = SL->root;
			while(temp!=NULL)
			{
				printf("%s->",temp->token);
				temp=temp->next;
			}

			printf("\n");
			}
			else
			{
#ifdef DEBUG
				printf("***************TOKEN ADDED: %s\n", token);
				printf("***************Pathname ADDED: %s\n", pathName);
#endif

				SLInsert(SL, token, pathName);
				if(strcmp(pathName, "") == 0) printf("\n%spathname is blank%s\n",KRED, KNORM);
				else printf("\n%s%s%s\n", KRED, pathName, KNORM);
				printlist(SL);

			//	printf(SL->root->token);
			//	temp = SL->root;
			while(temp!=NULL)
			{
				printf("%s->",temp->token);
				temp=temp->next;
			}

			printf("\n");

			}
			//			free(token); ????????????
			}

	 printf("Main(): Final result, print the ROOT from Vertical SL: %s\n", SL->root->token );
        printf("Main(): Final result, print the ROOT's filename from Vertical SL: %s\n", SL->root->accessToNode2->fileName );
        printf("Main(): Final result, print the ROOT's frequency from Vertical SL: %d\n", SL->root->accessToNode2->frequency);
//      printf("Main(): 2 items in HSL : Final result, print the ROOT's frequency from Vertical SL: %s\n", SLMain->root->accessToNode2->next->fileName);
//      printf("Main(): 2 items in HSL : Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->accessToNode2->next->frequency);


        printf("Main():22222 Final result, print the ROOT from Vertical SL: %s\n", SL->root->next->token );
        printf("Main():22222 Final result, print the ROOT's filename from Vertical SL: %s\n", SL->root->next->accessToNode2->fileName );
        printf("Main():22222 Final result, print the ROOT's frequency from Vertical SL: %d\n", SL->root->next->accessToNode2->frequency);

        printf("Main():33333 Final result, print the ROOT from Vertical SL: %s\n", SL->root->next->next->token );
        printf("Main():33333 Final result, print the ROOT's filename from Vertical SL: %s\n", SL->root->next->next->accessToNode2->fileName );
        printf("Main():33333 Final result, print the ROOT's frequency from Vertical SL: %d\n", SL->root->next->next->accessToNode2->frequency);

        printf("Main():55555 Final result, print the ROOT from Vertical SL: %s\n", SL->root->next->next->next->token );

	TKDestroy(tokenizer);

}

//indexFile will check directory vs file, and either write file or open new directory and call itself recurseively on the new directory.
void indexFileRec(char *pathName, SortedList1Ptr SL)
{
		printf("%s\n", pathName);
		if(checkDir(pathName)==1)//checks file vs directory
		{
			
			int Size = 0;
			FILE* fp =  fopen(pathName, "r");
			fseek(fp, 0L, SEEK_END);
			Size = ftell(fp);
			fseek(fp,0L, SEEK_SET);
			char* fileString = (char*)malloc((Size+1)*sizeof(char));
			fread(fileString,1,Size,fp);
			fileString[Size] = '\0';
			addToList(fileString, SL, pathName);
			free(fileString);
			fclose(fp);

		}

	else if(checkDir(pathName)==0)//checks for directory
	{
		DIR *dp;
		dp= opendir(pathName);
		struct dirent *ep;
		char* temp;

		if(dp!=NULL)
		{
			
			while(ep=readdir(dp))
			{
				if(strcmp(ep->d_name, ".")==0)
					{
						continue;
					}
				if(strcmp(ep->d_name, "..")==0)
					{
						continue;
					}

				int len = strlen(pathName)+strlen(ep->d_name)+3;
				temp = (char*)malloc(len*sizeof(char));
				temp = strcat(temp,pathName);
				temp = strcat(temp,"/");
				temp = strcat(temp,ep->d_name);
				indexFileRec(temp, SL);
				free(temp);

			}
		}
		(void)closedir(dp);

	}	

		
	
}

int main(int argc, char **argv) {
//indexFileRec(argv[1]);
printf("welcome to main.c \n");
SortedList1Ptr SL = NULL;
//writeFile(argv[1], SL);
indexFileRec(argv[1], SL);
//writeFile("test.txt", SL);
return 0;
}


