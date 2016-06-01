#include <stdio.h>

int
main(int argc, char * argv[])
{
//	FILE * fp = NULL;
//	fp = fopen(argv[1], "r");
//	//	fp = fopen("orders.txt", "r");

	FILE * fp = fopen("orders.txt", "r");

//	char temp;
//	temp = getc(fp);

	char temp = getc(fp);

	int count = 0;//"
	int count1 = 0;//|
	while (temp != EOF)
	{
		if(temp == '\"'){
			if(count == 0){count = 1; printf(" \x1B[31m");}
			else if(count == 1){count = 0; printf(" \x1B[0m");}
			}
		else if(temp == '|'){
			if(count1 == 0) {count1 = 1; printf(" \x1B[32m"); }
			else if(count1 == 1) {count1 = 2; printf(" \x1B[33m"); }
			else if(count1 == 2) { count1 = 0; printf(" \x1B[34m"); }
			}
		else if(temp == '\n')
			printf("\n\x1B[0m\n");
		else
			printf("%c", temp);
		
		temp = getc(fp);
	}
	return 0;
}
