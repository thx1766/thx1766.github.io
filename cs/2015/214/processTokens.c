tList* processTokens(char* separators, char* tokens){
	int tindex=0;
	int tlength=strlen(tokens); 
	tList* result;
	result=createList();
	result->size=0;
	tNode* tempNode = (tNode*)malloc(sizeof(tNode));
	tNode* previous = (tNode*)malloc(sizeof(tNode));
	char* tempString=(char*)malloc(sizeof(char));
while(tindex<tlength){
	char* tempChar=malloc(sizeof(char)*2);
	tempChar[0] = tokens[tindex];
	tempChar[1] = '\0';
	if((tokens[tindex]>=65 && tokens[tindex]<=90 ) || (tokens[tindex]>=97 && tokens[tindex]<=122)){
//manual "hack" in an attempt to do some filtering
		tempString = strcat(tempString,tempChar);
	}else{
		if(result->size==0 && tempString!=""){
			tempNode=createNode(tempString, 0, NULL);
			tempString="";
			result->first=tempNode;
			previous=tempNode;
			result->size++;
		}else if(tempString!=""){
			tempNode=createNode(tempString, result->size, NULL);
			tempString=="";
			previous->nextNode=tempNode;
			previous=tempNode;
			result->size++;
		}
	}
	tindex++;
}
return (tList*)result;
}
