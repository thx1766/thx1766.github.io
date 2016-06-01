#include <stdio.h>
#include <unistd.h>

int
main(int argc, char * argv[])
{

	if(fork()!=0)
	{
		execl("./forka", "./forka", 0);
	}
	else if(fork()!=0)
	{
		usleep(500000);
		execl("./forkb", "./forkb", 0);
	}
	return 0;
}
