#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*common functions*/

char charval(int input){
	switch (input){
			case 0:
				return '0';
			case 1:
				return '1';
			case 2:
				return '2';
			case 3:
				return '3';
			case 4:
				return '4';
			case 5:
				return '5';
			case 6:
				return '6';
			case 7:
				return '7';
			case 8:
				return '8';
			case 9:
				return '9';
			case 10:
				return 'a';
			case 11:
				return 'b';
			case 12:
				return 'c';
			case 13:
				return 'd';
			case 14:
				return 'e';
			case 15:
				return 'f';
		}
	}


int numdigits(unsigned int input, int base){
	int i;
	for(i=0; input!=0; i++)
		input/=base;
	return i;
}


char* convertOut(unsigned int answer, int base){

/*printf("invalue:%d\n",answer);*/

char* final;
int* realret;
int count =0;
int newcount=0;

int digits=numdigits(answer, base);

realret=malloc(sizeof(int)*digits);

while(count < digits){
	realret[count]=(answer%base);
	printf("value: %d\n", realret[count]);
	answer/=base;
	count++;
}

/*printf("intarray:%d%d%d%d\n",realret[0],realret[1],realret[2],realret[3]);*/

final=malloc(sizeof(char)*(digits+1));

newcount=digits-1;

count=0;

while(count<digits){
	final[count]=charval(realret[newcount]);
	count++;
	newcount--;
}

final[count]='\0';

/*printf("chararray:%s\n",final);*/

	return final;
}

unsigned int exponent(unsigned int base, unsigned int exp){
	/* base^expononet*/
	int i;
	unsigned int answer=1;
	if(base==0)
		return 0;
	for(i=1; i<= exp; i++){
		answer*=base;
	}
	return answer;
}

int inputCheck(char* number, char* radix){
	/*returns 0 for good input, 1 for bad input*/
	int lowercount = 0;
	int iradix = 0;
	int rlength = strlen(radix);
	int count = 0;
	char lowercase[32];
	char lowerrad[5];
	
	if(number[0]=='0' && strlen(number)==1)
		return 0;
	
	strcpy(lowercase,number);
	lowercount=strlen(lowercase);
	while(count<lowercount){
/*		printf("changing %c to %c\n",lowercase[count], tolower(lowercase[count]));*/
		lowercase[count]=tolower(lowercase[count]);
		count++;
		}
	count=0;
	lowercount=0;

	strcpy(lowerrad,radix);
	lowercount=strlen(radix);
	while(count<lowercount){
/*		printf("changing %c to %c\n",lowerrad[count], tolower(lowerrad[count]));*/
		lowerrad[count]=tolower(lowerrad[count]);
		count++;
		}
	count=0;
	lowercount=0;
	
	/*convert string to number for radix*/
	if(!strcmp(lowerrad,"bin")){
		iradix=2;
/*		printf("type binary detected\n");*/
	}
	else if(!strcmp(lowerrad,"oct")){
		iradix=8;
/*		printf("type octal detected\n");*/
	}
	else if(!strcmp(lowerrad,"dec")){
		iradix=10;
/*		printf("type decimal detected\n");*/
	}
	else if(!strcmp(lowerrad,"hex")){
		iradix=16;
/*		printf("type hex detected\n");*/
	}
	else{
/*		printf("type undetected\n");*/
		return 1;
	}
	
	switch(iradix){
		case 2:
/*			printf("case 2\n");*/
			while(count<rlength){
				if(lowercase[count]!='0' && lowercase[count]!='1'){
/*					printf("Mismatched input \"%c\"detected, returning\n",lowercase[count]);*/
					return 1;
				}
				count++;
				}
			return 0;
			break;
		case 8:
/*		printf("case 8\n");*/
			while(count<rlength){
				if(
				lowercase[count]!='0' &&
				lowercase[count]!='1' &&
				lowercase[count]!='2' &&
				lowercase[count]!='3' &&
				lowercase[count]!='4' &&
				lowercase[count]!='5' &&
				lowercase[count]!='6' &&
				lowercase[count]!='7'){
/*					printf("Mismatched input \"%c\"detected, returning\n",lowercase[count]);*/
					return 1;
				}
				count++;
				}
			return 0;
			break;
		case 10:
/*		printf("case 10\n");*/
			while(count<rlength){
				if(
				lowercase[count]!='0' &&
				lowercase[count]!='1' &&
				lowercase[count]!='2' &&
				lowercase[count]!='3' &&
				lowercase[count]!='4' &&
				lowercase[count]!='5' &&
				lowercase[count]!='6' &&
				lowercase[count]!='7' &&
				lowercase[count]!='8' &&
				lowercase[count]!='9'){
/*					printf("Mismatched input \"%c\"detected, returning\n",lowercase[count]);*/
					return 1;
				}
				count++;
				}
			return 0;
			break;
		case 16:
/*		printf("case 16\n");*/
			while(count<rlength){
				if(
				lowercase[count]!='0' &&
				lowercase[count]!='1' &&
				lowercase[count]!='2' &&
				lowercase[count]!='3' &&
				lowercase[count]!='4' &&
				lowercase[count]!='5' &&
				lowercase[count]!='6' &&
				lowercase[count]!='7' &&
				lowercase[count]!='8' &&
				lowercase[count]!='9' &&
				lowercase[count]!='a' &&
				lowercase[count]!='b' &&
				lowercase[count]!='c' &&
				lowercase[count]!='d' &&
				lowercase[count]!='e' &&
				lowercase[count]!='f'){
/*					printf("Mismatched input \"%c\"detected, returning\n",lowercase[count]);*/
					return 1;
				}
				count++;
				}
			return 0;
			break;
		default:
/*		printf("case default\n");*/
			return 1;
			break;
		}
	}

int charconv(char in){
	if(in=='0')
		return 0;
	if(in=='1')
		return 1;
	if(in=='2')
		return 2;
	if(in=='3')
		return 3;
	if(in=='4')
		return 4;
	if(in=='5')
		return 5;
	if(in=='6')
		return 6;
	if(in=='7')
		return 7;
	if(in=='8')
		return 8;
	if(in=='9')
		return 9;
	if(in=='a'||in=='A')
		return 10;
	if(in=='b'||in=='B')
		return 11;
	if(in=='c'||in=='C')
		return 12;
	if(in=='d'||in=='D')
		return 13;
	if(in=='e'||in=='E')
		return 14;
	if(in=='f'||in=='F')
		return 15;
	}
		
unsigned int convert(char* input, char* radix){
	int count=0;
	int count2 = 0;
	int radixint=0;
	int inputlength=strlen(input);
	unsigned int output=0;
	
	while(count<inputlength){/*convert number to lowercase*/
		input[count]=tolower(input[count]);
		count++;
	}
	count=0;
	inputlength=strlen(radix);
	while(count<inputlength){/*convert radix to lowercase*/
		radix[count] = tolower(radix[count]);
		count++;
	}
	count=0;
	inputlength=0;
	
	if(!strcmp(radix, "bin")) radixint = 2;
	if(!strcmp(radix, "oct")) radixint = 8;
	if(!strcmp(radix, "dec")) radixint = 10;
	if(!strcmp(radix, "hex")) radixint = 16;

	count=strlen(input)-1;

	//move through string right-to-left
	while(count>=0){
			output = output+(charconv(input[count]))*exponent(radixint,count2);
			count--;
			count2++;
		}		
	return output;
}
