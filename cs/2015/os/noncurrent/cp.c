//Hw 0: part 1 of 2: L2 cache parameters

/*
	Should be able to determine:
		1) Cache block/ line size
		2) Cache size
		3) Cache miss penalty
			(time spent when data is not cached,
			and must be fetched from main memory)
*/

#include <sys/time.h>//for gettimeofday()

#include <stdio.h>//for printf()

#include <stdlib.h>//for atoi()

int
main(int argc, char * argv[])
{

int index = 0;
//int indexmax = 1024;
int indexmax;
indexmax = atoi(argv[1]);

int testarray[indexmax];

struct timeval t0, t1;

gettimeofday(&t0, 0);

for(index = 0; index<indexmax; index++)
	testarray[index] = 1;

gettimeofday(&t1, 0);

int elapsed = (t1.tv_usec - t0.tv_usec);

printf("elapsed time for writing to an array of %d: %d microseconds\n", indexmax, elapsed);

return 0;
}
