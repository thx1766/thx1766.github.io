/*
 *	Implement User-Level Threads
 *		Idea #1: use signal-sending and signal-handling to implement switching to a specific thread
 * */

#include <stdio.h>

void
thread0()
{
	while(1)
	{
		printf("this is thread 0\n");
		//if signal for threadswitch 0 is called, call thread0
		//	else signal for threadswitch 1 called call thread 1
		//	else signal for threadswitch x called error thread not exist
	}
}

void
thread1()
{
	while(1)
	{
		printf("this is thread 1\n");
		//if signal for threadswitch 0 is called, call thread0
		//	else signal for threadswitch 1 called call thread 1
		//	else signal switchthread x called error thread not exist
	}
}

int
main(int argc, char * argv[])
{
	return 0;
}
