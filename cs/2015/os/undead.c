#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum)
{
//	__asm__ volatile ("nop");//for reading assembly
	
	printf("I am slain!\n");
	
//	__asm__ volatile ("nop");//for navigating assembly translations

//	int * pointer = &signum;

//	__asm__ volatile ("nop");//end of assembly for logic in C

//	__asm__ volatile ("movl 100(%eax), %esp");

//	__asm__ volatile ("push %eax");

//	printf("%x\n", pointer);
//	printf("%x\n", __builtin_return_address(0));
//
//	i

//	__asm__ ("nop");
//	int * address = __builtin_return_address(0);	
//	__asm__ ("nop");
//	address+=signum;
	__asm__ volatile ("movl $100, %esp");
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
