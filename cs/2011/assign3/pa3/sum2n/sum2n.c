#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sumInts.h"

/*
sum2n
usage:
sum2n <n> <k>
Returns (k+1)+(k+2)+…+(k+n)
*/

void usage(); /*prints proper application usage*/

void usage(){
	printf("\nUsage:\tsum2n <n> <k>\n");
	printf("The arguments <n> and <k> should both be nonnegative integers.\n");
	printf("Calculates (k+1)+(k+2)+…+(k+n)\n\n");
	}

int main(int argc, char** argv){
int n=0, k=0, result=0;
if(argc!=3){
	/*improper argument number*/
	printf("\nImproper number of arguments.\n");
	usage();
	return 0;
}else if(argv[1][0]=='-' && argv[1][1]=='h'){
	/*help flag detected*/
	usage();
	return 0;
}else{
	/*proper number of arguments*/
	n = atoi(argv[1]);
	k = atoi(argv[2]);

	if (n < 0 || k < 0 ){
		printf("You supplied a negative integer\n");
		usage();
		return 0;
		}
	result = sumInts(n, k);
	if(result == -1){
		printf("overflow\n");
		return 0;
		}
	printf("%d\n", result);
	return 0;
	}
}

