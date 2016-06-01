/*
 * Nathanial Schaffner
 * sorted-list.h
 */

#ifndef SORTED_LIST_H /* include guard */
#define SORTED_LIST_H

struct SortedListNode{
	void * data;
	struct SortedListNode * next;
	};

typedef int (*CompareFuncT) (void *, void *);
typedef void (*DestructFuncT) (void *);

struct SortedList{
	struct SortedListNode * first;
	CompareFuncT myComp;
	DestructFuncT myDest;
	};

typedef struct SortedList* SortedListPtr;

struct SortedListIterator{
	SortedListPtr myList;
	struct SortedListNode* currentNode;
	int nodeCount;
};

typedef struct SortedListIterator* SortedListIteratorPtr;

//typedef void (*PrintFuncT) (SortedListPtr list);
typedef void (*PrintFuncT) (void *);

SortedListPtr SLCreate(CompareFuncT cf, DestructFuncT df);

void SLDestroy(SortedListPtr list);

int SLContains(SortedListPtr list, void *newObj);
void SLPrint(SortedListPtr list, PrintFuncT printer);

int SLInsert(SortedListPtr list, void *newObj);

int SLRemove(SortedListPtr list, void *newObj);

SortedListIteratorPtr SLCreateIterator(SortedListPtr list);

void SLDestroyIterator(SortedListIteratorPtr iter);

void * SLGetItem(SortedListIteratorPtr iter);

void * SLNextItem(SortedListIteratorPtr iter);

#endif
