#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fib.h"

/*
fibonacci
usage:
fibonacci <n>
Prints the nth fibonacci number
*/

void usage(); /*prints proper application usage*/

int main(int argc, char** argv){
int input=0;
if(argc!=2){
	/*improper argument number*/
	fprintf(stderr, "\nImproper number of arguments.\n");
	usage();
	return 0;
}else if(argv[1][0]=='-' && argv[1][1]=='h'){
	/*help flag detected*/
	usage();
	return 0;
}else{
	/*proper number of arguments*/
	input = atoi(argv[1]);
	if (input < 0){
		fprintf(stderr, "You supplied a negative integer\n");
		usage();
		return 0;
		}
	input = fib(input);
	if(input == -1){
		fprintf(stderr, "overflow\n");
		return 0;
		}
	printf("%d\n", input);
	}
return 0;
}

void usage(){
	printf("\nUsage:\tfibonacci <n>\n");
	printf("The argument <n> should be a nonnegative integer.\n");
	printf("Prints out the nth fibonacci number.\n\n");
	}
