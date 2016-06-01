#include <stdio.h>
#include "search.h"
#include <string.h>

int contains(searchstruct * mysearchstruct, char * word)
{
	int result = 0;
	wordnode * temp;
	temp = mysearchstruct->wordlist;
	while(temp != NULL)
	{
		if(strcmp(word,temp->word) == 0)
			result = 1;
		temp = temp -> next;
	}
	return result;
}
