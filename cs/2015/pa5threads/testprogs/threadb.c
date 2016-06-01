#include "threada.h"

#define KRED  "\x1B[31m"
#define KNRM  "\x1B[0m"

void * threadb(void * arg0)
{
	char * str = (char * ) arg0;
	
	int count = 5;

	int index = 0;

	while(index < count)
	{
		printf("%sThe string is %s\n%s", KRED, str, KNRM);
		index++;
	}
	return NULL;
}
