#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int
main(int argc, char * argv[])
{

	char firststring[] = "this is the string to be tokenized";

	char * newword = malloc(strlen(firststring));
	newword =  strtok(firststring, " ");

	printf("the token is %s\n", newword);

	while(newword != NULL)
	{
		newword = strtok(NULL, " ");
		if (newword == NULL)
			break;
		printf("%s\n", newword);
	}
	return 0;
}
