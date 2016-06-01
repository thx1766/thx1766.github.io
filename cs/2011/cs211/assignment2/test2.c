#include <stdlib.h>
#include <math.h>
#include "common.c"

int main(int argc, char** argv){

char number[5]="123ff";

int inum=charconv(number[0]);
	printf("value:%d\n",inum);
inum = inum*(charconv( number[1]));
	printf("value:%d\n",inum);
inum = inum*(charconv(number[2]));
	printf("value:%d\n",inum);
inum = inum*(charconv( number[3]));
	printf("value:%d\n",inum);
inum = inum*(charconv( number[4]));
	printf("value:%d\n",inum);



return 0;
}
