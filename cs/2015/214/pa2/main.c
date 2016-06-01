/*
 * sorted-list.c
 */
#include	<stdio.h>
#include	<string.h>
#include	"sorted-list.h"
#include	<time.h>

int compareInts(void *p1, void *p2)
{
	int i1 = *(int*)p1;
	int i2 = *(int*)p2;

	return i1 - i2;
}

int compareDoubles(void *p1, void *p2)
{
	double d1 = *(double*)p1;
	double d2 = *(double*)p2;

	return (d1 < d2) ? -1 : ((d1 > d2) ? 1 : 0);
}

int compareStrings(void *p1, void *p2)
{
	char *s1 = p1;
	char *s2 = p2;

	return strcmp(s1, s2);
}

int main(int argc, char** argv)
{
	//set up list
	CompareFuncT intComp;
	intComp = &compareInts;
	SortedListPtr intList;
	intList  = SLCreate(intComp);
	//set up random insertion
	int count=0, rint=0;
	time_t seconds;
	time(&seconds);
	srand((unsigned int) seconds);
	while(count<100){
		rint=(rand()%100);
		printf("Inserting value #%d: %d\n", count, rint);
		//insert value
		count++;
		}
	return 0;
}
