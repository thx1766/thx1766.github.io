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



void writeFile(char *pathName, SortedList1Ptr SL)
{
	FILE *wf = fopen(pathName, "w");
printf("writeFile():FILEOPEN: what is pathName: (%s)\n", pathName);
	if (wf == NULL)
	{
		printf("Error Opening File\n");
		exit(1);
	}
	//printf(SL->root->token);
	if(SL == NULL)
	{
		printf("NO SL\n");
	}
	else
	{
	Node1* temp = SL->root;
	printList(wf, SL->root);
//	while(temp!=NULL)
//	{
//	printf("%s->fileName:[%s] frequency: [%d]\n",temp->token,temp->accessToNode2->fileName, temp->accessToNode2->frequency);

//	temp = temp->next;
//	}	
	}
//	fprintf(wf, "DOES THIS WORK ALI?");
	fclose(wf);

}


void printList(FILE *wf, Node1 *root) //useful printList function
{
	Node1 *		p;
	Node2 *		j;

	for ( p = root ; p != 0 ; p = p->next )
	{
		printf("------>word<list>[%s]\n", p->token);
		fprintf(wf, "<list> %s\n", p->token );

		for ( j  = p->accessToNode2 ; j != 0; j = j->next){
			printf("-------->filename[%s][%d] \n</list>\n", j->fileName, j->frequency);
			fprintf(wf,"%s %d \n</list>\n", j->fileName, j->frequency);

		}//end inner for

		//printf( "data is %#x *data is %d next is %#x\n", p->word, *(int *)(p->word), p->nextWord );
	}
//	printf( "End of list.\n" );
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

SortedList1Ptr addToList(char *fS, SortedList1Ptr SL, char *pathName)
{
			//char *token = strtok(fS, " \n");
			if(fS == NULL) {
			printf("Error: unable to create tokenizer\n");
			}
//			char* token = NULL;
			char *token = strtok(fS, " \n");

			while(token != NULL) {
			if(SL==NULL)
			{
				printf("SL CREATED\n");
				SL = SLCreate(token, pathName);
			}
			else
			{
				//SLInsert(SL, token, pathName);
				token = strtok(NULL, " \n");
				if ( token == NULL){
					printf("yo its NULL, break\n");
					break;
				}
				SLInsert(SL, token, pathName);
				printf("TOKEN ADDED is :[%s], pathName: [%s]\n",token,pathName);
				
			}
				
			}
			
printf("1st----------->What is [%s] [%s] [%s]\n", SL->root->token, SL->root->next->token, SL->root->next->next->token);
printf("1st-filename~~~~~~>What is[%s]\n", SL->root->accessToNode2->fileName);
//printf("1st-filename~~~~~~>What is [%s] [%s]\n", SL->root->accessToNode2->fileName, SL->root->accessToNode2->next->fileName);

//	TKDestroy(tokenizer);
//	free(token);
	//writeFile("answers.txt", SL);
	return SL;
}

//indexFile will check directory vs file, and either write file or open new directory and call itself recurseively on the new directory.
SortedList1Ptr indexFileRec(char *pathName, SortedList1Ptr SL)
{
		printf("%s\n", pathName);
		if(checkDir(pathName)==1)//checks file
		{
			
			int Size = 0;
			FILE* fp =  fopen(pathName, "r");
			fseek(fp, 0L, SEEK_END);
			Size = ftell(fp);
			fseek(fp,0L, SEEK_SET);
			char* fileString = (char*)malloc((Size+1)*sizeof(char));
			fread(fileString,1,Size,fp);
			fileString[Size] = '\0';
			SL = addToList(fileString, SL, pathName);
		//	free(fileString); //THIS IS THE COST THAT MESS UP THE SL!!!!!!!!!!!!!!!
			fclose(fp);
				/*printf("============\n");
				writeFile("testing.txt", SL);	
				printf("REC=========\n");*/
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
				SL = indexFileRec(temp, SL);
				free(temp);

			}
		}
		(void)closedir(dp);

	}	

	//printf("============\n");
	//writeFile("testing.txt", SL);	
	//printf("REC=========\n");
	return SL;	
}

int main(int argc, char **argv) {
//indexFileRec(argv[1]);

	SortedList1Ptr SL = NULL;
	char *pathName = malloc(1024);
	strcpy(pathName, argv[1]);
printf("PrINT the argv[1] : (%s)\n", pathName);
	SL = indexFileRec(pathName, SL);
printf("BEFORE calling writeFile()------------------>() :What is [%s] [%s] [%s]\n", SL->root->token, SL->root->next->token, SL->root->next->next->token);

	printf("Main() calls writefile()========\n");
	writeFile("testing.txt", SL);	
	printf("=======MAIN=========\n");
	printf("In main(): print out filename[%s]\n", SL->root->accessToNode2->fileName);

return 0;
}


