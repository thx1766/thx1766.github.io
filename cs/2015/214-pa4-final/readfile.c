searchstruct * readfile(char * filename)
{
	FILE * fp;
	fp = fopen(filename, "r");

	filenode * tempfile = malloc(sizeof(filenode));
	wordnode * tempword = malloc(sizeof(wordnode));
	searchstruct * mysearchstruct = malloc(sizeof(searchstruct));

	char tempc;
	tempc = getc(fp);
	int index = 0;
	//<list> -> 6chars + space = 7
	while(index < 7)
		tempc = getc(fp);
	if(tempc == ' ') //should be space now
}
