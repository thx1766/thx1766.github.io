#include <stdio.h>

int
main(int argc, char* argv[])
{
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,31,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,32,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,33,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,34,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,35,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,36,27,0);
	printf("%c[%d;%dmTEXT%c[%dm\n",27,1,37,27,0);
	return 0;
}
