#include <stdio.h>
#include <string.h>

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
	if( argc!=3)
	{
		usage();
		return 0;
	}

		int numberofseparators = strlen(argv[1]);
		int charsininputstream = strlen(argv[2]);

		while(charsininputstream >  0)
		{

			int numseparators=numberofseparators;
			int oneforsep=0;
			while(numseparators >= 0)
			{
                
                if(argv[2][(strlen(argv[2])-charsininputstream)]=='\\')
                {//printf("inside slash");
                    if(argv[2][(strlen(argv[2])-charsininputstream)+1] == '\0')
                        printf("\nnulldetected\n");
                    else
                    {
                        if(argv[2][(strlen(argv[2])-charsininputstream)+1]=='n')
                        {
                            printf("[0x0a]");
                            charsininputstream--;
                        }
                        charsininputstream--;
                        //oneforsep=1;
                      //  break;
                    }
                    
                }
               // break;
		//else
		//{
		//	printf("\nnonslash:%c\n",argv[2][(strlen(argv[2])-charsininputstream)]);
		//}
				if(argv[2][(strlen(argv[2])-charsininputstream)]==argv[1][(strlen(argv[1])-numseparators)])
				{
						printf("\n");
						oneforsep=1;
				}
				numseparators--;
			}
	
				if(oneforsep==0)
				{
					printf("%c",argv[2][strlen(argv[2])-charsininputstream]);
				}
			charsininputstream--;
		}

printf("\n");

return 0;
}

