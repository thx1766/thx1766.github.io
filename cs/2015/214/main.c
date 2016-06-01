#include <stdio.h>
#include "tokenizer.h"
#include <stdlib.h>

#define DEBUG 1

int main(int argc, char** argv){
	if (argc!=3){//incorrect number of arguments
		if(argc!=1)//if the utility is run with wrong number of arguments
			puts("\tWrong number of arguments!");
		usage();
	}else{//correct number of arguments
if(DEBUG) printf("You supplied \"%s\" as your separator string.\n",argv[1]);
if(DEBUG) printf("You supplied \"%s\" as your token string.\n",argv[2]);
		puts("Output:");
		tNode* myNode;
		tList* myList = createTokenizer(argv[1], argv[2]);
		displayList(myList);
		if(DEBUG){
	//		myNode = createNode(argv[1],0,NULL);
	//		displayNode(myNode);
	//		myNode =destroyNode(myNode);
	//		displayNode(myNode);
		}
	}
	return 0;
	}
