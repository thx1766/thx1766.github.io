//#include "stdio.h"
//#include "stdlib.h"
//#include "tokenList.h"
//#include "tokenNode.h"
//#include "createList.h"
//#include "createNode.h"
#include "processTokens.h"

tList* createTokenizer(char* separators, char* tokens){
	tList* result;
	tNode* firstNode;
	result=createList();
//start main logic
//three cases based on input: tokens NULL, separators NULL, neither NULL
//if tokens are null then the list contains one node that is empty
//if separators are null then the list contains one node containing the full string
//if neither are null, proceed normally
//case1
	if(tokens==NULL){
		firstNode=createNode(NULL, 0, NULL);
		result->size=0;
		result->first=firstNode;
	}
//case2
	else if(separators==NULL){
		firstNode=createNode(tokens, 0, NULL);
		result->size=1;
		result->first=firstNode;
	}
//case3
	else{
		result=(tList*) processTokens(separators, tokens);
	}
//end main logic
	return (tList*)result;
	}
