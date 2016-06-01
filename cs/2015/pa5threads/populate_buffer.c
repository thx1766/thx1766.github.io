#include "populate_buffer.h"

void
populate_buffer(char * buffer, char * filename)
{
	FILE * fp = NULL;
	fp = fopen(filename, "r");
	char temp = getc(fp);
	int index = 0;
	while(temp!=EOF)
	{
		buffer[index] = temp;
		temp = getc(fp);
		index++;
	}
	if(temp == EOF)
		buffer[index] = '\0';
    
    fclose(fp);

	printf("buffer contains:\n%s\n", buffer);
	
}
