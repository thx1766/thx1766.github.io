//System
#include <stdio.h>

//Nat
#include "main.h"
#include "bookorder.h"


int
main
(int argc, char * argv[])
{
	if(argc!=4)
	{
		usage(argv[0]);
		return 0;
	}

	FILE * fp0 = fopen(argv[1], "r");
	FILE * fp1 = fopen(argv[2], "r");
	FILE * fp2 = fopen(argv[3], "r");

	bookorder(argv[1], argv[2], argv[3]);

	return 0;
}

void usage(char * argv0)
{
	printf("Wrong number of arguments.\n");
	printf("\nUsage:\n\t%s <argv1> <argv2> <argv3>\n", argv0);
    printf("\t\t<argv1>: database file\n");
    printf("\t\t<argv2>: book order file\n");
    printf("\t\t<argv3>: category name file\n");
}

