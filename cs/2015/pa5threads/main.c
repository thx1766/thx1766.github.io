#include "main.h"

int
main(int argc, char * argv[])
{
	if(argc!=4)
		usage();
	else
	{
		bookorder(argv[1], argv[2], argv[3]);
	}
	return 0;
}

void usage()
{
	printf("Wrong number of arguments.\n\n");
	printf("Usage:\n\t./bookorder <1> <2> <3>\n");
	printf("<1>: name of database input file\n");
	printf("<2>: name of book order input file\n");
	printf("<3>: name of file containing category names\n\n");
}
