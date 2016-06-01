#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//converts escape codes to escape characters in a string
void
reduce(char * input, char * output)
{
	int i=0;
	int o=0;
	int ilen = strlen(input);

	while(i<(ilen+1))
	{
		if(input[i]==0x5c && input[i+1]==0x6e)// \n
		{
			output[o]=0x0a;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x74)// \t
		{
			output[o]=0x09;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x76)// \v
		{
			output[o]=0x0b;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x62)// \b
		{
			output[o]=0x08;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x72)// \r
		{
			output[o]=0x0d;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x66)// \f
		{
			output[o]=0x0c;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x61)// \a
		{
			output[o]=0x07;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x5c)// (backslash)
		{
			output[o]=0x5c;
			i++;
			o++;
		}
		else if(input[i]==0x5c && input[i+1]==0x22)// (double quote)
		{
			output[o]=0x22;
			i++;
			o++;
		}
		else
		{
			output[o]=input[i];
			o++;
		}
		i++;
	}
	output[o]='\0';
    
//    printf("reduced string:%s\n", output);
}

void
usage()
{

	printf("\nwrong number of arguments\n");
	printf("\nUsage of Tokenizer:\n~\\tokenizer ");
	printf("<separator characters> <token stream>\n");
}

int
main (int argc, char * argv[])
{
	//check number of arguments

	if(argc!=3)
	{
		usage();
		return 0;
	}

	//reduce separators string (“\n” -> ‘\n’ etc)

	char * seps;
	seps = malloc(sizeof(char)*strlen(argv[1]));
	reduce(argv[1], seps);

	//reduce tokens string (“\n” -> ‘\n’ etc)

	char * toks;
	toks = malloc(sizeof(char)*strlen(argv[2]));
	reduce(argv[2], toks);

	int len = strlen(toks);
	int tokindex = 0;

	int issep=0;
	int newline = 0;

	// loop through all chars in token stream
	while(tokindex < len)
	{
		int slen = strlen(seps);
		int sindex = 0;
		
		// loop through all chars in separator string
		while(sindex < slen)
		{
			//token char is a separator
			if(toks[tokindex] == seps[sindex])
			{
				issep = 1;
			}

			sindex++;
		}

	if(issep == 1 && newline == 0)
	{
		newline = 1;
		printf("\n");
	}
	else if(toks[tokindex] == '\n')
	{
		newline = 0;
		printf("[0x0a]");
	}
	else if(toks[tokindex] == '\t')
	{
		newline = 0;
		printf("[0x09]");
	}
	else if(toks[tokindex] == '\v')
	{
		newline = 0;
		printf("[0x0b]");
	}
	else if(toks[tokindex] == '\b')
	{
		newline = 0;
		printf("[0x08]");
	}
	else if(toks[tokindex] == '\r')
	{
		newline = 0;
		printf("[0x0d]");
	}
	else if(toks[tokindex] == '\f')
	{
		newline = 0;
		printf("[0x0c]");
	}
	else if(toks[tokindex] == '\a')
	{
		newline = 0;
		printf("[0x07]");
	}
	else if(toks[tokindex] == '\\')
	{
		newline = 0;
		printf("[0x5c]");
	}
	else if(toks[tokindex] == '\"')
	{
		newline = 0;
		printf("[0x22]");
	}
	// print standard char
	else
	{
		newline = 0;
		printf("%c",toks[tokindex]);
	}

    issep = 0;
	
	tokindex++;
	}

	printf("\n");
	return 0;
	}
