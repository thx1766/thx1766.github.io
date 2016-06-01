#include <stdio.h>
#include <string.h>

int
main(int argc, char * argv[])
{
	int value = atoi(argv[1]);
	printf("value %d:\n%s\n", value, argv[value]);
	printf("location argv[value]:\n\t%d\n&argv[value]\n\t%x\n", argv[value], &argv[value]);
return 0;
}
