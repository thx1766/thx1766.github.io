#include <stdio.h>//printf()
#include <stdlib.h>//atexit();
#include <unistd.h>//execl()

void
endfunc()
{
	printf("atexit()");

	if(fork()==0)
		execl("/bin/echo", "echo", "test0" , 0);
	else
		execl("/bin/echo", "echo", "test1" , 0);
}

int
main(int argc, char * argv[])
{
	atexit( endfunc );
	return 0;
}
