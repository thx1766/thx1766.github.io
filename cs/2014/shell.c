#include <stdio.h>
int
main(int argc, char * argv[])
{
	printf("arg:%d\t",argc);
	int a=0;
	while(1)
	{
		printf("var%d:%s\t",a,argv[a]);
		a++;
	}
return 0;
}
