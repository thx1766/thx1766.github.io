#include <stdio.h>
#include "common.h"
#include "common.c"

int main (int argc, char** argv){
	unsigned int number1, number2, answer;
	if(argc !=6){/*error checking*/
		printf("Invalid number of arguments, proper usage:\n");
		printf("mult <number1> <number2> <base1> <base2> <output base>\n");
		return 0;
		}
	else{/*correct number of arguments given, proceed*/
		if(inputCheck(argv[1],argv[3])){
			printf("Input type for %s does not match %s\n",argv[1],argv[3]);
			return 0;
		}
		if(inputCheck(argv[2],argv[4])){
			printf("Input type for %s does not match %s\n",argv[2],argv[4]);
			return 0;
		}
		
		if(argv[1][0]=='0' && strlen(argv[1])==1){
			printf("0");
			return 0;
		}
		else if(argv[3][0]=='0' && strlen(argv[3])==1){
			printf("0");
			return 0;
		}
		/* provided parameters are of the type they say they are, proceed*/
		/*convert numbers to decimal for ease of computation*/
		
		number1=convert(argv[1], argv[3]);
		number2=convert(argv[2], argv[4]);
		
		/*OVERFLOW CHECK*/
		if(number2>(MAXUS/number1))
			fprintf(stderr, "OVERFLOW\n");
		
		answer=number1*number2;
		
		if(!strcmp(argv[5],"bin"))
			printf("answer:%s\n",convertOut(answer, 2));
		else if(!strcmp(argv[5], "oct"))
			printf("%s\n",convertOut(answer, 8));
		else if(!strcmp(argv[5], "dec"))
			printf("%s\n",convertOut(answer, 10));
		else if(!strcmp(argv[5], "hex"))
			printf("%s\n",convertOut(answer, 16));
		else
			printf("Unrecognized output radix");
	}
}
