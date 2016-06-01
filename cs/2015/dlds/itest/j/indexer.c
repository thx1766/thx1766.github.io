/*indexer.c
*/
#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>

#include "indexer.h" //includes the header
/*
This function SLCreate will create the vertical SL when the first file is read.
*/
SortedList1Ptr SLCreate(char *token, char *fileName){ //First token from the first file
	if(token != NULL && fileName != NULL){

		int i =0; //This function is to convert token to lowercase
		for(i = 0; i < strlen(token); i++){
			token[i] = tolower(token[i]);
			}

		SortedList1Ptr SortedListNEW = malloc(sizeof(SortedList1Ptr)); //malloc Struct1
		SortedListNEW->root = malloc(sizeof(Node1)); //malloc a Node1 called root
		SortedListNEW->root->token = token;//DO NOT USE STRCPY

		SortedList2Ptr SortedListHORI = malloc(sizeof(SortedList2Ptr)); //Create struct2
		SortedListHORI->root2 = malloc(sizeof(Node2)); //malloc the root for struct2 and assign the data to it
		SortedListHORI->root2->fileName = fileName;
		SortedListHORI->root2->frequency = 1;
	//	SortedListHORI->root2->next = NULL;

		SortedListNEW->root->accessToNode2 = SortedListHORI->root2; //then set Struct1's accessToNode2's (node2) TO the root2 of struct2	
		return SortedListNEW;

	}else{ 
		return NULL;
	}
}
/*filenames are same, increment frequency */
/*
void sameFileName(Node2 *tempHeadHORI ){
	tempHeadHORI->frequency++; //increment the frequency by 1
	printf("sameFileName() :There is one item in Horizontal list, and the fileNames are the same \n");
}
*/

/*filenames are different, then just make a new Node2 and put it in the BACK of the list (since its smallest)*/
void diffFileName(Node2 *tempHeadHORI, char *currentFileName){ //might need to modify the argument/inputs
	Node2 *newNode2 = malloc(sizeof(Node2)); //right now i will just add to the back of the list********************
	newNode2->fileName = currentFileName;
	newNode2->frequency = 1;
	tempHeadHORI->next = newNode2;
	printf("diffFileName() : There is one item in Horizontal list, and the fileNames are NOT the same \n");
}

/*insert by frequency order, swapping*/
void insertByFrequency(Node2 *tempHolderHORI ,Node2 *tempNextHORI){ //AKA SWAP method
	if ( strcmp(tempHolderHORI->fileName, tempNextHORI->fileName )== 0 ){ //if the root IS the node we are comparing
		printf("TempHolderHORI and tempNextHORI are the same thing, do nothing! \n");
	}else {

	while(tempHolderHORI->next!=NULL){
		printf("insertByFrequency(): Ready for swaping! \n");
		if( tempHolderHORI->frequency <= tempNextHORI->frequency ){
			//swap
			int tempFreq = tempHolderHORI->frequency;
			char *tempFileName = tempHolderHORI->fileName;
			tempHolderHORI->frequency = tempNextHORI->frequency;
			tempHolderHORI->fileName = tempNextHORI->fileName;
			tempNextHORI->frequency = tempFreq;
			tempNextHORI->fileName = tempFileName;
		}else{
			printf("DO nothing \n");
		}
	tempHolderHORI = tempHolderHORI->next;
	}//end while
	}//end else
}

/*insert the token by alpha order */
int insertByAlpha(Node1 *list, char *newToken, char *currentFileName){
//condition 1: if the list is empty, we simply add the node to the list
	if(list->token !=  NULL){
	//condition 2A: if new object is smallest(alpha order):
		if( strcasecmp (newToken, list->token) <= -1){
		//printf("Insert(): The new object should be placed in front of the list! \n");
			Node1 *tempRoot =(Node1*) malloc(sizeof(Node1));
			Node1 *newNode = (Node1*)malloc(sizeof(Node1));

			Node2 *newNodeHori = malloc(sizeof(Node2)); //set node2
			newNodeHori->fileName = currentFileName;
			newNodeHori->frequency = 1;
			newNode->accessToNode2 = newNodeHori;

			tempRoot = list; //tempRoot holds root
			newNode->next = tempRoot; //temp2's next becomes root
			list = newNode; //root becomes temp2
			list->token = newToken;
			return 1;
		}
	//condition 2B: if object ranks between first and last
	if( strcasecmp(newToken, list->token) >= 1){ //newObject is greater
	printf("Insert(): Insert somewhere in the middle of the list: \n");
		Node1 *tempRoot2B =(Node1*) malloc(sizeof(Node1));
		Node1 *tempBack = (Node1*)malloc(sizeof(Node1));
		tempBack = list;//Create this temporary variable(node) called tempBack to hold onto the root of the sortedlist

			for(tempRoot2B = list->next  ; tempRoot2B != NULL; tempRoot2B = tempRoot2B->next){
			printf("Got into this part how many times??????????????????\n");
				if( strcasecmp(newToken, tempRoot2B->token) <= -1){ //first object is bigger, we can insert the new node
					Node1 *newNode2B =(Node1 *) malloc(sizeof(Node1));
					newNode2B->token = newToken;

					Node2 *newNodeHori = malloc(sizeof(Node2)); //set node2
					newNodeHori->fileName = currentFileName;
					newNodeHori->frequency = 1;
					newNode2B->accessToNode2 = newNodeHori;

					tempBack->next = newNode2B;//tempBack (which is ahead(before/in front) of tempRoot)'s next will become this new node
					newNode2B->next = tempRoot2B;//and this new node's back will be linked to the current node (AKA: tempRoot2B)
					return 1;
				}
				tempBack = tempBack->next;
			} 
			if( strcasecmp(newToken, tempBack->token) >= 1){ //new object is biggest, insert at LAST position...
			//printf("------------------>New token is Largest, should be inserted at the last position of the list !\n");
					Node1 *newNode =(Node1 *) malloc(sizeof(Node1));
					newNode->token = newToken;
					Node2 *newNodeHori = malloc(sizeof(Node2)); //set node2
					newNodeHori->fileName = currentFileName;
					newNodeHori->frequency = 1;
					newNode->accessToNode2 = newNodeHori;

					tempBack->next = newNode;//tempBack (which is ahead(before/in front) of tempRoot)'s next will become this new node
					return 1;
			}
	}
}
return 0; 
}

/*compile */
int horiComp(Node1 *tempRoot, char *token, char *currentFileName) {
	if(strcasecmp(tempRoot->token, token)== 0){ //we found it, we can now work on horizontal list!
		Node2 *tempHeadHORI = malloc(sizeof(Node2));
		tempHeadHORI = tempRoot->accessToNode2;
			if(tempHeadHORI->next ==NULL){//there is only one item in Horizontal SL  
				if(strcmp(tempHeadHORI->fileName, currentFileName)== 0){//check the "one node's filename" are the same
					//sameFileName(tempHeadHORI);
					tempHeadHORI->frequency++;
					return 1;
				}else {//The filename is Different, so we need a comparison functon to compare frequncy amd create a new Node2
					diffFileName(tempHeadHORI, currentFileName);
					return 1;
				}
			} //end if
	//printf("Condition3b: more than 1 item on Horizontal SL, example will be; input; 'abc(file1) def(file1) abc(file2) abc(file1)' \n");
	while(tempHeadHORI->next != NULL){
		if(strcmp(tempHeadHORI->fileName, currentFileName) == 0){ //loop the HSL and compare filenames, if same: frequency++
			//sameFileName(tempHeadHORI);
			tempHeadHORI->frequency++;
			Node2 *tempHolderHORI = malloc (sizeof(Node2));
			tempHolderHORI = tempRoot->accessToNode2; //??????????????????????????????????not sure
			insertByFrequency(tempHolderHORI, tempHeadHORI); //call this function to swap
			return 1;
		}
		tempHeadHORI = tempHeadHORI->next; 
	} //end while-loop

	//One last check from while-loop
	if(strcmp(tempHeadHORI->fileName, currentFileName) == 0){
	printf("One last loop outside of while-loop, Filename matched! frequency++, return1 \n");
		//sameFileName(tempHeadHORI);
		tempHeadHORI->frequency++;
		Node2 *tempHolderHORI = malloc (sizeof(Node2));
		tempHolderHORI = tempRoot->accessToNode2; //??????????????????????????????????not sure
		insertByFrequency(tempHolderHORI, tempHeadHORI); //call this function to swap
		return 1;
	}
	//If it gets here, then that means no Filename matched, we need to make a comparison function on frequency and create a new Node2
	diffFileName(tempHeadHORI ,currentFileName);
	return 1;
	}	
}





/*
Insert will return 1 if success, 0 if fail
*/
int SLInsert(SortedList1Ptr SortedList, char *token, char* currentFileName){
	int i =0; //This function is to convert token to lowercase
	for(i = 0; i < strlen(token); i++){
		 token[i] = tolower(token[i]);
	}
	/*Condition 1a, when the vertical SL only has 1 item*/
	if(SortedList->root->next == NULL){
		if(strcasecmp(SortedList->root->token, token)== 0){//if they're the same, we have to work on HORIZONTAL list
			printf("Condition1: Vertical SL only has 1 item and the NEW token are the same!!! \n");
			Node2 *tempHeadHORI = malloc(sizeof(Node2));
			tempHeadHORI = SortedList->root->accessToNode2;
			if(tempHeadHORI->next ==NULL){//there is only one item in Horizontal SL  
				if(strcmp(tempHeadHORI->fileName, currentFileName)== 0){//check the "one node's filename" are the same
					tempHeadHORI->frequency++; //increment the frequency by 1
					//sameFileName(tempHeadHORI);
					return 1;
				}else {//The filename is Different, so we need a comparison functon to compare frequncy amd create a new Node2
					diffFileName(tempHeadHORI, currentFileName);
					return 1;
				}
			}
			//below is:1 item in vertical list and  more than one iem in Horizontal SL
			printf("Condition1b: Vertical SL only has 1 item but More than 1 item on Horizontal SL, example will be; input; 'abc(file1) abc(file2) abc(file3)' \n");
			Node2 *tempNextHORI = malloc(sizeof(Node2));
			tempNextHORI = SortedList->root->accessToNode2;
			while(tempNextHORI->next != NULL){
				if(strcmp(tempNextHORI->fileName, currentFileName) == 0){ //loop HSL, compare filenames, if same: freq++
					//sameFileName(tempNextHORI);
					tempNextHORI->frequency++;
					Node2 *tempHolderHORI = malloc (sizeof(Node2));
					tempHolderHORI = SortedList->root->accessToNode2;
					insertByFrequency(tempHolderHORI, tempNextHORI); //call this function to swap
					return 1;
				}
			tempNextHORI = tempNextHORI->next; 
			}
			//one more check for while-loop
			if(strcmp(tempNextHORI->fileName, currentFileName) == 0){
			printf("One last loop outside of while-loop, Filename matched! frequency++, return1 \n");
			//	sameFileName(tempNextHORI);
				tempNextHORI->frequency++;
				Node2 *tempHolderHORI = malloc (sizeof(Node2));
				tempHolderHORI = SortedList->root->accessToNode2;
				insertByFrequency(tempHolderHORI, tempNextHORI);

				return 1;
			}
			//If it gets here, then that means no Filename matched, we need to make a comparison function on frequency and create a new Node2
			diffFileName(tempNextHORI ,currentFileName); 
			return 1;

		/*One item in vertical list and we cannot find the same token from vertical list */
		}else{ 

		//printf("Conditon 2,There is one item in VSL and we cannot find the same token from the vertical list.\n");
			if(strcasecmp(SortedList->root->token, token) < 0){ //token in our list is SMALLER, so NEW token should be in root's next
				Node1 *newNode1 = malloc(sizeof(Node1)); //create a new Node
				//printf("Condition 2, diff token: our 'original' token should still be in first position!\n");
				newNode1->token = token; //assignments
				Node2 *newNodeHori = malloc(sizeof(Node2)); //set node2
				newNodeHori->fileName = currentFileName;
				newNodeHori->frequency = 1;
				newNode1->accessToNode2 = newNodeHori;
				SortedList->root->next = newNode1;
				return 1;
			}else{ //the NEW token should be in first position
			//printf("Condition 2B, diff token: NEW token should still be in first position!\n");
				Node1 *newNode1 = malloc(sizeof(Node1));
				newNode1->token = token;
				Node2 *newNodeHori = malloc(sizeof(Node2));
				newNodeHori->fileName = currentFileName;
				newNodeHori->frequency = 1;
				newNode1->accessToNode2 = newNodeHori;
				Node1 *tempHead = malloc(sizeof(Node1));
				
				tempHead = SortedList->root; //tempHead holds the root
				newNode1->next = tempHead; //newNode's next is tempHead
				SortedList->root = newNode1; //root became newNode1
				return 1;
			}
		}
	}
/*---------------------------------------more than 1 item on vertical list ----------------------------------------------------------------------------*/
	/*Condition 3: when there are more than 1 items in Vertical list */
	printf("Condition 3: there are more than 1 item in the VSL \n");
	Node1 *tempRoot = malloc(sizeof(Node1));
	tempRoot = SortedList->root;

	while (tempRoot->next != NULL){
		int return1;
		return1 = horiComp(tempRoot,token, currentFileName );
		if(return1 == 1){
		return 1;
		}
		tempRoot = tempRoot->next;
	}//end of while????????
	//check for last while-loop condition
	
	int return2;
	return2 = horiComp(tempRoot, token, currentFileName);
	if(return2 == 1){
		return 1;
	}

	int returnNum;
	//insertByAlpha(SortedList->root, token, currentFileName );
	returnNum = insertByAlpha(SortedList->root, token, currentFileName );
	return returnNum;
//return 0;
}












