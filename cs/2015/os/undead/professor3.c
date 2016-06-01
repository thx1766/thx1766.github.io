// Author: John-Austen Francisco
// Date: 9 September 2015
//
// Preconditions: Appropriate C libraries
// Postconditions: Generates Segmentation Fault for
//                               signal handler self-hack

// Student name:
// Ilab machine used:

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

#include <setjmp.h>

void segment_fault_handler(int signum)
{
	printf("I am slain!\n");

	longjmp(&signum);

	//__asm__ ("jmp *%esp");
	//__asm__ ("jmp *4(%ebp)");
	//__asm__ ("ret");
}

int main()
{
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);

	r2 = *( (int *) 0 );
	
	printf("I live again!\n");

	return 0;
}
