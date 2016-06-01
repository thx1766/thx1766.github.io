#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum){
	printf("Segment Fault Detected\n");
	printf("Segment Fault Handler invoked with signal: %d\n", signum);
	int * pointer = &signum;
//	__asm__ volatile ("jmp (%eax)");
//	__asm__ volatile ("jmp (%esp)");
//	__asm__ volatile ("ret");
	}

int main(){
	int a = 0;

	signal(SIGSEGV, segment_fault_handler);
	
	a = *( (int *) 0 );
	
	return 0;
	}
