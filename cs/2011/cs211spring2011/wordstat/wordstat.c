#include <stdio.h>
#include "wordstat.h"

int main(int argc, char **argv){
	if(argc!=2){ /*improper number of arguments*/
		printf("Improper number of arguments!\n\n");
		usage();
		return 0;
		}
	else if(argv[1][0]=='-' && argv[1][1]=='h'){ /*help menu called*/
		usage();
		return 0;
		}
	else{ /*argument is filename*/
		printf("You have selected file:\t%s\n\n",argv[1]);
		wordstat(argv[1]);
		}
	return 0;
	}

void usage(){
	/*keep strings less than 509 chars for ISO c89 compiler compliance*/
	printf("Usage:\n\twordstat -h\n\t\tDisplays this help menu\n\twordstat <filename>\n\t\tRuns wordstat on specified file.\n\nWordstat reads a text file, finds different words in the file, prints them in alphabetical order, and checks if a word is in dictionary.\n\nA word is defined as any sequence of upper and lower-case alphabetical letters (A-Z, a-z) that appears in the input file preceded immediately by the end of the file or a non-letter character. ");
	printf("Words should be case-insensitive. That is, \"book\" and \"Book\" and \"bOOk\" are the same word.\n\nThe program should finds all the different words in the file (according to the definition of word above), then prints them in alphabetical order together with their frequencies (how many times each word appears in the file). ");
	printf("Also checks if a word is in the dictionary.\n\nAs an example, running wordstat on a file with the following content:\n\tSome ?Random> (random12) weird-$con@tent. Madam, I'm adam.\ngiven the following dictionary:\n\tADAM\n\tI\n\tMADAM\n\tRANDOM\n\tSOME\n\tTENT\n\tWEIRD\nshould produce:\n\tWORDS\t\tFREQUENCIES\tIN DICTIONARY?\n\tADAM\t\t1\t\tY\n\tCON\t\t1\t\tN\n\tI\t\t1\t\tY\n\tM\t\t1\t\tN\n\tMADAM\t\t1\t\tY\n\tRANDOM\t\t2\t\tY\n\tSOME\t\t1\t\tY\n\tTENT\t\t1\t\tY\n\tWEIRD\t\t1\t\tY\n\n");
	}

void wordstat(char *filename){
	printf("filename chosen:\t%s\n\n",filename);
	/*FILE myfile = fopen(filename, 'r');*/
	/*
	
	//create list
	struct listNode{
		int count;
		char word[255];
		listNode *next;
		}
	
	listNode *listHead = NULL;
	listNode *listTail = NULL;

	//adding a new item
	listNode *newWord;
	malloc(sizeof(listNode));
	newWord->count = 1;
	newWord->next = NULL;
	
	if(!listHead){
		listHead = newWord;
		listTail -> next = newWord;
		listTail = newWord;
		}
	else{
		
	
	list.count = 0;
		

	open file
	create linked list (struct: word, count)
	iterate through file and add words to list
	while(!eof)
		addToList(struct* list, word)
	
	void addToList(char *newword){
		
		}
	*/
	}


