#include <stdlib.h>
#include "common.c"

int main(int argc, char** argv){
	int test1, test2, test3, test4;
	test1=inputCheck("FFF","bin");
	test2=inputCheck("RRR","bin");
	test3=inputCheck("999", "bin");
	test4=inputCheck("000","bin");
	printf("output:\t%d\t%d\t%d\t%d\n\n",test1, test2, test3, test4);
	return 0;
}
