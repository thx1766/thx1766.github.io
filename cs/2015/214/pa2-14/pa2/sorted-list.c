/*
 * Nathanial Schaffner
 * sorted-list.c
 */

#include <stdlib.h>
#include <stdio.h>
#include "sorted-list.h"

SortedListPtr SLCreate(CompareFuncT cf, DestructFuncT df){
	SortedListPtr myList = malloc(sizeof(struct SortedList));
	myList->myComp = cf;
	myList->myDest = df;
	return myList;
}

void SLDestroy(SortedListPtr list){
	DestructFuncT df = list->myDest;
	struct SortedListNode * currentNode = list->first;
	struct SortedListNode * temp;
	while(currentNode!=NULL){
		df(currentNode->data);
		temp=currentNode;
		currentNode=currentNode->next;
		free(temp);
	}
	free(list);
}

/*
 * returns 1 if data is in list, 0 if not, -1 if error
 */
int SLContains(SortedListPtr list, void *newObj){
	if(list==NULL){
		return -1;
	}
	struct SortedListNode * currentNode= list->first;
	while(currentNode!=NULL){
		if(list->myComp(currentNode->data, newObj)==0){
			return 1;
		}
		currentNode = currentNode->next;
	}
	return 0;
}

void SLPrint(SortedListPtr list, PrintFuncT printer){
	struct SortedListNode * temp = list->first;
	printf("\n");
	while(temp!=NULL){
		printer(temp->data);
		printf("\n");
		temp=temp->next;
	}
}

int SLInsert(SortedListPtr list, void *newObj){
	struct SortedListNode * newNode = malloc(sizeof(struct SortedListNode));
	newNode->data=newObj;
	
	if(list->first == NULL){
		list->first=newNode;
		return 1;
	}else{
		/*
		 * case: item not in list
		 * case: in list
		 */
		int contained=SLContains(list, newObj);
		if(contained==1 || contained==-1)
			return 0;
		else{
			if(list->myComp(list->first->data, newObj)>0){
				newNode->next=list->first;
				list->first=newNode;
				return 1;
			}
			/*
			 * iterate until location found
			 *	compare each:::(while)
			 *		compare until smaller than
			 * temp for previous to link node before location
			 */
			//***********
			//confused over whether to use SLIterator or hardcode
			//***********
			struct SortedListNode * temp = list->first;
			struct SortedListNode * prev;
			while(list->myComp(temp->data, newObj)<0){
				if(temp->next==NULL){
					temp->next=newNode;
					return 1;
				}
				prev=temp;
				temp=temp->next;
			}
			newNode->next=prev->next;
			prev->next=newNode;
			//***********
			return 1;
		}
	}
}

int SLRemove(SortedListPtr list, void *newObj){
	if(SLContains(list, newObj)==0 || SLContains(list, newObj)==-1)
		return 0;
	struct SortedListNode * currentNode = list -> first;
	struct SortedListNode * prevNode;
	while(currentNode!=NULL){
		if(list->myComp(currentNode->data, newObj)==0){
			if(prevNode==NULL){
				list->first=list->first->next;
			}else{
				prevNode->next = currentNode->next;
			}
		return 1;
		}
		prevNode=currentNode;
		currentNode=currentNode->next;
	}
	//if we reach this point, something went wrong
	return 0;
}

SortedListIteratorPtr SLCreateIterator(SortedListPtr list){
	if(list==NULL)
		return NULL;
	SortedListIteratorPtr mySLIP=malloc(sizeof(struct SortedListIterator));
	mySLIP->myList = list;
	mySLIP->nodeCount=0;
	struct SortedListNode * temp = list->first;
	while(temp!=NULL){
		mySLIP->nodeCount++;
		temp=temp->next;
	}
	if(mySLIP->nodeCount==0)
		mySLIP->currentNode=NULL;
	else
		mySLIP->currentNode=list->first;
	return mySLIP;
}

void SLDestroyIterator(SortedListIteratorPtr iter){
	if(iter!=NULL){
		iter->myList=NULL;
		iter->currentNode=NULL;
		iter->nodeCount=0;
		free(iter);
	}
}

void * SLGetItem(SortedListIteratorPtr iter){
	if(iter!=NULL)
		return iter->currentNode->data;
	else
		return 0;
}

void * SLNextItem(SortedListIteratorPtr iter){
	if(iter!=NULL){
		iter->currentNode=iter->currentNode->next;
		if(iter->currentNode != NULL)
			return iter->currentNode->data;
		else
			return NULL;
	}	
	else
		return NULL;
}
