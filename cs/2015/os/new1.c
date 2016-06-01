#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

#define __USE_GNU
#include <ucontext.h>

int b = 100;

void segment_fault_handler(int signum, siginfo_t * si, ucontext_t * context){
	printf("Segment Fault Detected\n");
	printf("Segment Fault Handler invoked with signal: %d\n", signum);
//	int * pointer = &signum;
//	__asm__ volatile ("jmp (%eax)");
//	__asm__ volatile ("jmp (%esp)");
//	__asm__ volatile ("ret");
	context->uc_mcontext.gregs[REG_RAX] = &n;
	}

int main(){
	int a = 0;

	signal(SIGSEGV, segment_fault_handler);
	
	a = *( (int *) 0 );
	
	return 0;
	}
