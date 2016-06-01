#include <stdio.h>
#include "bookorder.h"

void
bookorder(char * fp0, char * fp1, char *fp2)
{
	checkopen(fp0);//verify the file pointer provided for <> is valid
	checkopen(fp1);//verify the file pointer provided for <> is valid
	checkopen(fp2);//verify the file pointer provided for <> is valid

	printf("Welcome to the book order system!\n");

	return;
}

int
checkopen(char * fp)
{
	FILE * test;
	if ( ( test = fopen(fp,"r") ) )
	{
		fclose(test);
		return 1;
	}
	else
	{
		fclose(test);
		return 0;
	}
}
