#include <stdio.h>
#include <string.h>

int
main(int argc, char * argv[])
{
	if(argc!=3)
	{
		printf("need two arguments\n");
		return 0;
	}

	printf("strcmp result for strcmp(%s, %s): %d\n", argv[1], argv[2], strcmp(argv[1], argv[2]));

	return 0;
}
