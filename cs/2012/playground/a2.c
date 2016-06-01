#include <stdio.h>
int main(int argc, char** argv){

	if(argc!=1){
		printf("Ascii value of %c as an integer is %d\n", argv[1][0], argv[1][0]);
	}
	else{
		char stringgg[]="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i=0;
		while(i<52){
			printf("Ascii value of %c as an integer is %d\n", stringgg[i], stringgg[i]);
			i++;	
			}
		}
	return 0;
	}
