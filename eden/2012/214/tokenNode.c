//#include "tokenModel.h"

struct tokenNode{
	int type;
		//0 for separator
		//1 for token
	int index;
		//index in full list
	struct tokenNode* nextNode;
	//tNode* nextToken;
	//tNode* nextSeparator;
	char* data;
};
