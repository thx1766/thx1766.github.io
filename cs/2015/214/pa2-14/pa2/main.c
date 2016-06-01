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

void test(){
	// Testing creation of a SortedList
	SortedListPtr test = SLCreate(ci, di);
	// Integers for ease of passing pointers
	int int05=-5, int04=-4, int03=-3, int02=-2, int01=-1;
	int int0=0;
	int int1=1, int2=2, int3=3, int4=4, int5=5;
	//Integers for tracking results of tests
	int t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
	//Testing of SLInsert
	t0=SLInsert(test, &int2);
	t1=SLInsert(test, &int4);
	t2=SLInsert(test, &int3);
	t3=SLInsert(test, &int02);
	t4=SLInsert(test, &int0);
	t5=SLInsert(test, &int01);
	t6=SLInsert(test, &int5);
	t7=SLInsert(test, &int03);
	t8=SLInsert(test, &int1);
	t9=SLInsert(test, &int05);
	t10=SLInsert(test, &int04);
	//Expected result: t0..9 all 1 for success
	//Demonstration of printing
	SLPrint(test, intPrint);
}

int main(int argc, char * argv[]){
	test();
	
	//code below is incomplete for testing	
/*
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

*/	
return 0;
}
