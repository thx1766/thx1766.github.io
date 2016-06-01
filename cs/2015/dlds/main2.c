
#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>

#include "indexer.c" //include!
#include "tokenizer.c"


/*Main2.c is used to test vertical sorted list orders */
int main(int argc, char **argv) {

//	indexerFunction();
//	TKCreate("123", "abc123abc123abc" );
	TokenizerT* tokenizer = TKCreate(" ", "a b c d a b");
//	TokenizerT* tokenizer = TKCreate(" ", "abc def ihj");

        if(tokenizer == NULL) {
                printf("Error: unable to create tokenizer\n");
        }
        char* token = NULL;

	SortedList1Ptr SLMain = malloc (sizeof(SortedList1Ptr)); //create a SL struct1

	if((token = TKGetNextToken(tokenizer)) != NULL){ //FIRST TOKEN!
		printf("Main(): This is the first token: %s\n", token );
		SLMain = SLCreate(token, "hardCodedFile1" );
	}
/*
	if((token = TKGetNextToken(tokenizer)) != NULL){ //FIRST TOKEN!
		printf("Main(): This is the second token: %s\n", token );
		SLInsert(SLMain, token, "hardCodedFile2");//same FILE example
		//SLInsert(SLMain, token, "hardCodedFile2");//diff FILE example
	}
	if((token = TKGetNextToken(tokenizer)) != NULL){ //FIRST TOKEN!
		printf("Main(): This is the third token: %s\n", token );
		SLInsert(SLMain, token, "hardCodedFile3");//same FILE example
		//SLInsert(SLMain, token, "hardCodedFile2");//diff FILE example
	}

*/
        while((token = TKGetNextToken(tokenizer)) != NULL) {
                printf("Extra NEXT tokens: %s\n", token);
		SLInsert(SLMain, token, "hardCodedFile1");
	}
/*
        while((token = TKGetNextToken(tokenizer)) != NULL) {
                printf("Extra NEXT tokens: %s\n", token);
		SLInsert(SLMain, token, "hardCodedFile3");
//		printf("***************(1)print in main : %s\n", SLMain->root->token );
	//	printf("***************(2)print in main : %s\n", SLMain->root->next->token ); //remove/add this line depending on CONDITIONS!!!!
                free(token);
        }
*/
	printf("Main(): Final result, print the ROOT from Vertical SL: %s\n", SLMain->root->token );
	printf("Main(): Final result, print the ROOT's filename from Vertical SL: %s\n", SLMain->root->accessToNode2->fileName );
	printf("Main(): Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->accessToNode2->frequency);
//	printf("Main(): 2 items in HSL : Final result, print the ROOT's frequency from Vertical SL: %s\n", SLMain->root->accessToNode2->next->fileName);
//	printf("Main(): 2 items in HSL : Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->accessToNode2->next->frequency);
	

	printf("Main():22222 Final result, print the ROOT from Vertical SL: %s\n", SLMain->root->next->token );
	printf("Main():22222 Final result, print the ROOT's filename from Vertical SL: %s\n", SLMain->root->next->accessToNode2->fileName );
	printf("Main():22222 Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->next->accessToNode2->frequency);

	printf("Main():33333 Final result, print the ROOT from Vertical SL: %s\n", SLMain->root->next->next->token );
	printf("Main():33333 Final result, print the ROOT's filename from Vertical SL: %s\n", SLMain->root->next->next->accessToNode2->fileName );
	printf("Main():33333 Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->next->next->accessToNode2->frequency);

	printf("Main():55555 Final result, print the ROOT from Vertical SL: %s\n", SLMain->root->next->next->next->token );
//	printf("Main():55555 Final result, print the ROOT's filename from Vertical SL: %s\n", SLMain->root->next->next->next->accessToNode2->fileName );
//	printf("Main():55555 Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->next->next->next->accessToNode2->frequency);

//	printf("Main():66666 Final result, print the ROOT from Vertical SL: %s\n", SLMain->root->next->next->next->next->token );
//	printf("Main():66666 Final result, print the ROOT's filename from Vertical SL: %s\n", SLMain->root->next->next->next->next->accessToNode2->fileName );
//	printf("Main():66666 Final result, print the ROOT's frequency from Vertical SL: %d\n", SLMain->root->next->next->next->next->accessToNode2->frequency);

        TKDestroy(tokenizer);


/*

	DIR  *dp;
	struct dirent *ep;
	int Size = 0;
	//FILE* fp;
	char* fileString;
	dp = opendir("./worddocs");

	if(dp!=NULL){

		while (ep=readdir(dp)){
		printf(ep->d_name);
		printf("\n");

		char fileName[80] = "./worddocs/";
		strcat(fileName, ep->d_name);
		FILE* fp =  fopen(fileName, "r");

			if(fp == NULL){
				printf("ERROR: NULL FILE\n");
			}
			else{
				Size = 0;
				fseek(fp, 0L, SEEK_END);
				Size = ftell(fp);
				fseek(fp,0L, SEEK_SET);
				fileString = malloc(Size+1);
				fread(fileString,1,Size,fp);
				fileString[Size] = '\0';
				printf("Working:\n%s\n", fileString);
			}

		fclose(fp);
		free(fileString);
		}
	(void)closedir(dp);
	}
	else{
	printf("Can not open dir \n");
	}
*/

return 0;
}

