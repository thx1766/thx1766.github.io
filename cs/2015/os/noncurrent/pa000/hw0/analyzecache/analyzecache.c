/*
 * NOTE:
 *	I originally found the solutions to a suspiciously similar task:
 *	http://discolab.rutgers.edu/classes/cs519/sp2000/assignments/hw1sol.html
 *
 *	I approached the TA in person, and was told to send an email, to which I recieved no reply.
 *
 *	With regard to academic integrity, I am not certain how to proceed. 
 * */

#include <stdio.h>
#include "analyzecache.h"

int main(int argc, char *argv[])
{
	//determines cache attributes:
	//	cache line size
	//	cache size
	//	cache miss penalty
	
	int clsize=0, csize=0, cmp=0;

	//calculate data requested
	
	printf("Cache Clock/Line Size: %d B\n",clsize);
	printf("Cache Size: %d KB\n", csize);
	printf("Cache Miss Penalty: %d us\n", cmp);

	return 0;
}
