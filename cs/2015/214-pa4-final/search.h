#include <stdio.h>

typedef struct filenode_
{
	char * filename;
	struct filenode_ * next;
} filenode;

typedef struct wordnode_
{
	char * word;
	struct wordnode_ * next;
} wordnode;

typedef struct searchstructure_
{
	struct wordnode_ * wordlist;
} searchstructure;
