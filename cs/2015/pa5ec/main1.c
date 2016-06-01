#include <unistd.h>

#include <stdio.h>

void endfunc()
{
	printf("test endfunc\n");
}

void main()
{
	atexit( endfunc );

	execl("/bin/echo", "echo", "test echo", 0);

	return;
}
