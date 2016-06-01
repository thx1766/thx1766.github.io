/*
 * Nathanial Schaffner
 * main.c
 */

#include <stdio.h>
#include "sorted-list.h"

int ci(void *p1, void *p2){
	int i1 = *(int*)p1;
	int i2 = *(int*)p2;
	return i1 - i2;
}

void di(void *p){
	return;
}

void intPrint(void *p){
	int i = *(int*)p;
	printf("%d",i);
}

int main(int argc, char * argv[]){
	SortedListPtr test = SLCreate(ci, di);
/*
	int myint=1;
	SLInsert(test, &myint);
	int int2=2;
	int int1=1;
	int test1, test2, test3;
	test1=SLContains(test,&myint);
	printf("value is %d\n",test1);
	test2=SLContains(test, &int1);
	printf("value is %d",test2);
	test3=SLContains(test, &int2);
	printf("value is %d",test3);
*/
	int int1=1, int2=2, int3=3;
	int in1=SLInsert(test, &int1);
	int in2=SLInsert(test, &int2);
	int in3=SLInsert(test, &int3);
	printf("insert1: %d insert2: %d insert3: %d\n",in1,in2,in3);
	SLPrint(test, intPrint);
	int int4=-1, int5=1;
	int in4=SLInsert(test, &int4);
	int in5=SLInsert(test, &int5);
	printf("insert -1: %d insert 1: %d\n",in4,in5);
	SLPrint(test, intPrint);
	int int6=7, int7=5, int8=6;
	SLInsert(test, &int6);
	SLPrint(test, intPrint);
	SLInsert(test, &int7);
	SLPrint(test, intPrint);
	SLInsert(test, &int8);
	SLPrint(test, intPrint);
	
	SLRemove(test, &int7);
	SLPrint(test, intPrint);

	SortedListIteratorPtr iterTest = SLCreateIterator(test);
	if(iterTest==NULL)
		printf("value of iterTest is NULL\n");
	else{
		printf("result of iterTest (should be first node):\n");
		intPrint(iterTest->currentNode->data);
		printf("\n");
		printf("size of list when iterator created:%d\n", iterTest->nodeCount);
		//intPrint(iterTest->nodeCount);
	}

	int * testRetrieve = SLGetItem(iterTest);
	printf("result of call to SLGetItem\n");
	intPrint(testRetrieve);
	printf("\n");

	printf("\ntesting SLNextItem\n");

	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");
	intPrint(SLNextItem(iterTest)); printf("\n");


	SLDestroy(test);

	
return 0;
}
