#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum)
{
	__asm__ volatile ("nop");//for reading assembly
	
	printf("I am slain!\n");

	int * pointer = &signum;
	
	__asm__ volatile ("nop");//for navigating assembly translations

	__asm__ volatile ("movl 11(%esp), %esp");
}


int main()
{
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);
__asm__ ("nop");
	r2 = *( (int *) 0 );
__asm__ ("nop");
	printf("I live again!\n");

	return 0;
}
