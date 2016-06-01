#include	<stdio.h>
#include	<string.h>
#include	"sorted-list.h"

int main(int argc, char** argv)
{
printf("number of args: %di\n",argc);
int index=0;
while(index!=100)
{
	printf("shell var %d:\t%s\n", index, argv[index]);
	index++;
}
/*
puts(argv[0]);
puts (argv[1]);
puts (argv[2]);
puts (argv[3]);
puts (argv[4]);
puts (argv[5]);
puts("this is the main ghfjknvfjjvf");
*/
return 0;
}
