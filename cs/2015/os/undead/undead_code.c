// Author: John-Austen Francisco
// // Date: 9 September 2015
// //
// // Preconditions: Appropriate C libraries
// // Postconditions: Generates Segmentation Fault for
// //                               signal handler self-hack
//
// // Student name:	Nathanial Schaffner
// // Ilab machine used:	Prototype
//
// //compiled with:
// //gcc -m32 -O0 -g undead_code.c

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum)
{
	printf("I am slain!\n");
}

int main()
{
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);

	r2 = *( (int *) 0 );
	
	printf("I live again!\n");

	return 0;
}
