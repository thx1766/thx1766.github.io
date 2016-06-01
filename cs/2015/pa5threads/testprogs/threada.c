#include "threada.h"

#define KGRN  "\x1B[32m"
#define KNRM  "\x1B[0m"

void * threada(void * arg0)
{
	char * str = (char * ) arg0;
	
	int count = 5;

	int index = 0;

	while(index < count)
	{
		printf("%sThe string is %s\n%s", KGRN, str, KNRM);
		index++;
	}
	return NULL;
}
