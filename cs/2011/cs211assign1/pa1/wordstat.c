#include "wordstat.h"

int main (int argc, char **argv){
	char* tempword;
	struct palList* myList;
	myList = malloc(sizeof(struct palList));
	if(argc==3){
		/*
		if (strcmp(argv[1],"-x")==0){
			printf("inserting words into a new list until you type \"zed\"");
			tempword="test";
			while(strcmp(tempword, "zed")!=0)
				
			insert("test",myList);
			}*/
		if (strcmp(argv[1],"-l")==0){
			lines(argv[2]);
			}
		else if (strcmp(argv[1],"-w")==0){
			words(argv[2], myList);
			}
		else if (strcmp(argv[1],"-p")==0){
			lex(argv[2]);
			}
		else if (strcmp(argv[1],"-pf")==0){
			lexlines(argv[2]);
			}
		else if (strcmp(argv[1], "-h")==0)
			help();
		else
			help();
		}
	else
		help();
destroylist(myList);
return 0;
	}

void lines (char* filename){
	char templine[256];/*holds lines*/
	int size=0;
	char* string=filename;
	FILE* filepointer; /*create pointer to file*/
	filepointer=fopen(string, "r"); /*open the file for reading*/
	if(filepointer==NULL){
		printf("File error when opening - (does not exist or do not have permissions) - aborting");
		return 0;
		}
	while(fgets(templine, 256, filepointer)){/*read into templine until newline*/
		size++;
		}
		printf("%d",size);
	/*close file after we're done*/
	fclose(filepointer);
	}

void words (char* filename, struct palList* myList){
	char templine[256];/*holds lines*/
	int words=0;
	int i=0;/*index in line*/
	int j=0;/*index in word*/
	char* string=filename;
	char tempword[256];
	char* insertword;
	FILE* filepointer; /*create pointer to file*/
	filepointer=fopen(string, "r"); /*open the file for reading*/
	while(fgets(templine, 256, filepointer)){/*read into templine until newline*/
		for(i=0; i<sizeof(templine); i++){
			if(isalpha(templine[i])){
				tempword[j]=templine[i];
				j++;
			}
			else{
				if(j==0)
					continue;
				tempword[j]='\0';
				insertword=malloc(sizeof(char)*strlen(tempword));
				strcpy(insertword, tempword);
				printf("\t\tinserting %s\n", insertword);
				insert(insertword, myList);
				words++;
				j=0;
				}
			}
		}
		printf("%d",wordcount(myList));
	/*close file after we're done*/
	fclose(filepointer);
	}

void lex (char* filename){
	printf("function lex called\n");
	}

void lexlines (char* filename){
	printf("function lexlines called\n");
	}

void help (){
	char* string="Usage: ./wordstat <option> <filename>\nOptions:\n\t-l: number of lines\n\t-w: number of words\n\t-p: palindromes's statistics\n\t-pf: palindrome's frequency\n";
	printf("%s", string);
	}
	
void insert(char* newWord, struct palList* myList){/*inserts word in alphabetical order*/
printf("insert called with:%s\n", newWord);
		struct palNode* pointer;
		struct palNode* newtemp;
	if(myList->head==NULL){/*list is empty*/
		myList->head=malloc(sizeof(struct palNode)); 
		myList->head->frequency=1;
		myList->head->palindrome=malloc(strlen(newWord));
		strcpy(myList->head->palindrome, newWord);
		myList->head->next=NULL;
	}else{/*list is not empty*/
		if(strcmp(myList->head->palindrome, newWord) > 0){/*new word goes before head*/
			newtemp = malloc(sizeof(struct palNode));
			newtemp->frequency = 1;
			newtemp->palindrome = malloc(strlen(newWord));
			strcpy(newtemp->palindrome, newWord);
			newtemp-> next = myList->head;
			myList->head=newtemp;
			}
		else if(strcmp(myList->head->palindrome, newWord)==0){/*new word is same as head*/
			myList->head->frequency++;
			}
		else {/*new word comes after head*/
			while(pointer->next!=NULL){/*until end of list reached*/
				if(strcmp(pointer->next->palindrome, newWord) >0){/*new word goes before current's next*/
					newtemp = malloc(sizeof(struct palNode));
					newtemp->frequency = 1;
					newtemp->palindrome=malloc(strlen(newWord));
					strcpy(newtemp->palindrome, newWord);
					newtemp->next=pointer->next;
					pointer->next=newtemp;
					break;
				}else if(strcmp(pointer->next->palindrome, newWord)==0){/*new word is same as current't next*/
					pointer->frequency++;
					break;
				}else if(pointer->next!=NULL){/*new word comes after current's next*/
					pointer=pointer->next;
				}else{ /*list ends*/
				newtemp = malloc(sizeof(struct palNode));
				newtemp->frequency =1;
				newtemp->palindrome = malloc(strlen(newWord));
				strcpy(newtemp->palindrome, newWord);
				pointer->next = newtemp;
				}
			}
		}
	}
}

int wordcount(struct palList* myList){
		struct palNode* pointer;
		int count = 0;
		if(myList->head==NULL)
			return 0;
		else{
			pointer = myList->head;
			while(pointer!=NULL){
				count+=pointer->frequency;
				pointer=pointer->next;
			}
		}
	return count;
	}

void destroylist(struct palList* myList){
	struct palNode* pointer, * temp;
	pointer=myList->head;
	while(pointer!=NULL){
			temp=pointer;
			pointer=pointer->next;
			free(temp->palindrome);
			free(temp);
			}
		free(myList);
	}
