#include <stdio.h>
#include <malloc.h>
#include <string.h>

char* append(char* string, char insert, int appendIndex){
	if(appendIndex==0){
		string=malloc(sizeof(char));
		string=strcat(string, &insert);
		string=strcat(string, &temp3); 
		appendIndex++;
	}else{
		string=realloc(string, (strlen(string)+&insert+1));
		string=strcat(string, &insert);
		appendIndex++;
	}
	return string;
}
/*
int main(int argc, char** argv){
	char* string="this is a string";
	printf("\n\'%c\'\n",string[2]);
	return 0;	
}
*/
