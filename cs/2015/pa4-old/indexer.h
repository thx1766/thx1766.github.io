#ifndef INDEXER_H
#define INDEXER_H
/*
 * indexer.h
 */

#include <stdlib.h>

/*
 * Sorted list type.  You need to fill in the type as part of your implementation.
 */
//typedef void (*DestructFuncT)( void * );
/*
typedef struct struct1Node Node1;
typedef struct struct2Node Node2;
struct struct1Node{
	char *token;
	Node1 *next;

	struct struct2Node{
	char *filenme;
	int frequency;
	Node2 *next;
	};
	Node2 *toAccess2;

};
*/

/*
Structure of Node2 (AKA: the horizontal node) of SortedList2
*/

typedef struct struct2Node Node2; //struct 2 is horizontal SL, which includes filename, frequency, next
struct struct2Node{ 
	char *fileName;
	Node2 *next;
	int frequency;
};


/*
Structure of Node1 (AKA; the vertical node) of SortedList1
*/

typedef struct struct1Node Node1; //struct 1 is vertical SL, which includes struct2,next,word
struct struct1Node{
	char *token;//token(is words)
	Node1 *next;
	Node2 *accessToNode2;
};

/*
Struct1, SortedList1, is the vertical sorted list which includes Node1 (with structure of word,next,struct2) and HOLDS struct2
*/
struct SortedList1
{
	Node1 *root;

};
typedef struct SortedList1* SortedList1Ptr;

/*
Struct2, SortedList2, is the horizontal sorted list which includes Node2 (with structure of filename, frequency, next (+maybe struct1)
*/
struct SortedList2
{
	Node2 *root2;
};

typedef struct SortedList2* SortedList2Ptr;

void printList(FILE *wf, Node1 *temp);
/* 
Create a 'complete' SortedList, given the token and filename we can create the SL
*/
//SortedList1Ptr SLCreate(char* token , char* fileName); //return type: SortedList1Ptr should be holding struct1 &2


//int SLInsert(SortedList1Ptr list, char *token);


#endif
