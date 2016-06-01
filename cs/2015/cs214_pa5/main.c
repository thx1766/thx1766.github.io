#include <stdio.h>

#include "bookorder.h"

void
usage()
{
	printf("Usage:\n");
	printf("\t./bookorder database bookorder categories\n");
	printf("\t\targv[1]: the name of the database input file\n");
	printf("\t\targv[2]: the name of the book order input file\n");
	printf("\t\targv[3]: the name of a file containing category names\n");

}

int
main(int argc, char * argv[])
{
	//argv[1]: the name of the database input file
	//argv[2]: the name of the book order input file
	//argv[3]: the name of a file containing category names

	if(argc!=4)
	{
		printf("Wrong number of arguments\n");
		usage();
		return 0;
	}

	return 0;
}
