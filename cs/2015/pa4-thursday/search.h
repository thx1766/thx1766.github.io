/*
 Filename:
    search.h
 */
#ifndef SEARCH_H
#define SEARCH_H


#include <stdlib.h>


/*structs*/

struct NTSfile{
	char * filename;
	struct NTSfile * next;
};

struct NTSfile * create_NTSfile(){
	struct NTSfile * retval = malloc(sizeof(struct NTSfile));
	retval -> filename = NULL;
	retval -> next = NULL;
	return retval;
};

struct NTSword{
	char * word;
	struct NTSword * next;
	struct NTSfile * filelist;
};

struct NTSword * create_NTSword(){
	struct NTSword * retval = malloc(sizeof(struct NTSword));
	retval -> word = NULL;
	retval -> next = NULL;
	retval -> filelist = NULL;
	return retval;
};

struct sa_struct{
	struct NTSword * wordlist;
};


typedef struct nNode Node;
struct nNode
{
//node should consists of fileName and a pointer to next node
	char *fileName; 
	Node *next;
};

//vertical sortedlist
struct SortedList
{
	Node *root;
	int sizeOfList;

};
typedef struct SortedList sortedListPtr; //i changed SortedListPtr to sortedListPtr

struct listoflist_{
	sortedListPtr * list;
	struct listoflist_ * next;
};
typedef struct listoflist_ listoflist;

typedef struct filenode fileNode;
struct filenode
{
    char * filename;
	fileNode *nextFile;
//    struct filenode * nextFile; //change filenode -> fileNode
    int count;
};

typedef struct wordnode wordNode;
struct wordnode
{
    char * word;
	wordNode *nextWord;
	fileNode *fileList;
//    struct wordnode * nextWord;
//    struct filenode * fileList;
};

struct searchable_index_
{
	wordNode *wordlist;
 //   struct wordnode * wordlist;
};

typedef struct searchable_index_ searchable_index;


/*
 Function prototypes
 */
int insertWordNode(searchable_index *searchableIndex, wordNode *newWordNode, char *newSubstring);
void addToBack(wordNode *tempRoot, fileNode *newFileNode);
void so(sortedListPtr *sl, searchable_index *searchableIndex, char *token);
void sa(searchable_index * searchableIndex, char * termlist);
void outputAllFilenames(sortedListPtr *sl, wordNode *tempWn, char *token);
int soAddFilename(sortedListPtr *sl, char* filename);
//searchable_index * createTestIndex();
//void printIndex(searchable_index * toBePrinted);




/*functions to implement*/
//case1: when query is sa<term>

/*call SO for each term individually, 
take result and create a list of files for each term.

----------------------------------------------------------------------------------------------------------
METHOD1:
take the term/node from shortest list and compare it with other sortedlist with *TEMP and a COUNTER
Remove the filename(node) from the VSL and increment the counter; 

if the counter == number of VSL created, then return put FILENAME in candidate list else
put FILENAME in noncandidate list.
----------------------------------------------------------------------------------------------------------
METHOD2:
move to next VSLs, remove all the noncandidate from the list, start checking candidate based on candidate list.
Then, add everything else thats not the original candidate list to the non-candidate list.

if theres any element thats in the candidate list that is not in the VSL then we MOVE it from the candidate list
to the non-candidate list.

----------------------------------------------------------------------------------------------------------
*/

#endif
