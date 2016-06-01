#include <stdlib.h>
#include <string.h>
//creates node

tNode* createNode(char* content, int index, int type, tNode* nextN){
	tNode* newNode = (tNode*) malloc(sizeof(tNode));
	newNode->index=index;
	newNode->type=type;
	if(nextN!=NULL)
		newNode->nextNode = nextN;
	newNode->data=(char*) malloc(sizeof(char)*strlen(content));
	strcpy(newNode->data, content);
	return newNode;
}
