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

void segment_fault_handler(int signum)
{
	printf("I am slain!\n");
	
	//Use the signnum to construct a pointer to flag on stored stack
	int * pointer = &signum;
	//Increment pointer down to the stored PC
//	pointer += 6;
	//Increment value at pointer by length of bad instruction
//	pointer[0]++;
//	following the comments did not seem super-helpful

	//old value stored at 0x084843d, previous frame at 0x084838, so move by 6 to bypass complication
//	__asm__ ("jmp 0x0848444");
//	__asm__ ("jmp *(%ebp)");	
//	__asm__ ("jmp 0x147d36");
//	__asm__ ("jmp *(%eip)");
//	__asm__ ("jmp (0xffffd3fc)");
//	__asm__ ("movl (%eax), %esp");
//	__asm__ ("ret");
//	__asm__ ("jmp (0xffffd3e8)");
	//__asm__ ("jmp .loc1450");
}


int main()
{
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);

	r2 = *( (int *) 0 );
	
	printf("I live again!\n");

	return 0;
}
