#include "filelength.h"

int
filelength(FILE * fp)
{
    int count = 0;
	char temp = getc(fp);
	//^this line may segfault when porting
	while(temp!=EOF)
	{
		temp = getc(fp);
		count++;
	}
	return count;
}

int
filelengthwrapper(char * filename)
{
	int retval = 0;
	FILE * fp = NULL;
	fp = fopen(filename, "r");
	retval = filelength(fp);
	return retval;
}
