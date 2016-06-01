#include <stdio.h>

int
main(int argc, char * argv[])
{
	int index = 0;
	while(index<5)
	{
		printf("fork b\n");
		usleep(1000000);
		index++;
	}
	return 0;
}
