#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*structs*/

/*node in linked list*/
struct palNode{
	char* palindrome;
	int frequency;
	struct palNode* next; /*pointer to next entry*/
	};

/*linked list struct*/
struct palList{
	struct palNode* head;
	int numElements;
	};

/*prototypes*/
void lines (char*);
void words (char* ,struct palList*);
void lex (char*);
void lexlines (char*);
void help (void);
void insert (char* , struct palList*);
int wordcount(struct palList*);
void destroylist(struct palList*);
