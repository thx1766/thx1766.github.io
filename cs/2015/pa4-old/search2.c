#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "search.h"
#include <dirent.h>
#include <ctype.h>


int insertWordNode(searchable_index *searchableIndex, wordNode *newWordNode, char *newSubstring){//return 1 for success
//printf("%c[%d;%dmColor Me!%c[%dm\n",27,1,33,27,0);
if(searchableIndex->wordlist == NULL){
printf("insertWordNode (): the wordlist is empty \n");

		fileNode *newFileNode = malloc(sizeof(fileNode));
		newFileNode->filename = newSubstring;

		searchableIndex->wordlist = newWordNode;
		searchableIndex->wordlist->fileList = newFileNode;
		printf("%c[%d;%dmBELOW IS CASE1: %c[%dm word=[%s],filename= [%s]\n",27,1,33,27,0, searchableIndex->wordlist->word, searchableIndex->wordlist->fileList->filename);
		searchableIndex->wordlist->fileList->nextFile = NULL;
		newWordNode->nextWord = NULL; //add null to back of filelist too?
		return 1;
}else{
		wordNode *tempRoot = malloc(sizeof(wordNode));
		tempRoot = searchableIndex->wordlist;

		if( strcmp( tempRoot->word , newWordNode->word)== 0){//first word has more than one filenames.
			printf("first word has more than one filename!!!!!!!!!, and the current file-[%s]\n", tempRoot->fileList->filename);
			fileNode *newFileNode = malloc(sizeof(fileNode));
			newFileNode->filename = newSubstring;
			addToBack(tempRoot, newFileNode);
		}else {

			while(tempRoot->nextWord !=NULL){
				tempRoot = tempRoot->nextWord;
			}
			//printf("Last word is : tempRoot: [%s], [%s] --------open--------------\n", tempRoot->word , newWordNode->word);
				if( strcmp( tempRoot->word, newWordNode->word) == 0){
					fileNode *newFileNode = malloc(sizeof(fileNode));
					newFileNode->filename = newSubstring;
					addToBack(tempRoot, newFileNode);
			//		printf("call addToBack() -------close------------\n");
				}else if( strcmp(tempRoot->word, newWordNode->word) != 0){
				fileNode *newFileNode = malloc(sizeof(fileNode));
				newFileNode->filename = newSubstring;
				tempRoot->nextWord = newWordNode;
				tempRoot->nextWord->fileList = newFileNode;
				printf("%c[%d;%dmCase 3!%c[%dm word=[%s], filename=[%s]\n",27,1,36,27,0, tempRoot->nextWord->word, tempRoot->nextWord->fileList->filename);
				//newFileNode->nextFile = NULL;
				newWordNode->nextWord= NULL;
				}//end else-if
		}//end else
		return 1;
}//end else

return 1;
}
void addToBack(wordNode *tempRoot,fileNode *newFileNode){
printf("Welcome to addToBack()-.-.-.-.\n");
	if(tempRoot->fileList  == NULL){
		tempRoot->fileList = newFileNode;
		newFileNode->nextFile = NULL;
		//should not be possble tho...
	}else{
		fileNode *tempFileRoot = malloc(sizeof(fileNode));
		tempFileRoot = tempRoot->fileList;

		while(tempFileRoot->nextFile != NULL){
			tempFileRoot = tempFileRoot->nextFile;
		}
//		printf("addtoBack(): ----------------here tempRoot should be holding the last node of the list: [%s].\n", tempFileRoot->filename);
		newFileNode->nextFile = NULL;
		tempFileRoot->nextFile = newFileNode;
		printf("%c[%d;%dmCase 2() within addToBack()!%c[%dm word=[%s], filename=[%s]\n",27,1,32,27,0, tempRoot->word, tempRoot->fileList->filename);
//		printf("End of addToBack(): case 2----------->thisis current word :(%s) and filename :(%s)\n", tempRoot->word, tempRoot->fileList->filename);
	}
}


/*
void discoverWord(searchable_index *searchableIndex, char* line){//return 1 for success
//printf("~~~~~~~~~~~~~~~~~~~~~~~~~Discovering the word,print line:~~~~~~~~~~~~~~~~~~~~~~~~ [%s]\n",line);
line += 7;
,//printf("discoverWord():~~~~~~~~~~~NEW word:[%s]~~~~~~~~~~\n", line);
wordNode *wn = malloc(sizeof(wordNode));
wn->word = line;
fileNode *fn = NULL;
//insertWordNode(searchableIndex, wn, fn);
insertWordNode(searchableIndex, wn, fn);
}
*/


int discoverFilename(searchable_index *searchableIndex, char*line, char *tempWord ){ //return 1= done, 0= error
//printf("discoverFilename(): welcome~!~~~~~~~~~~~~~~~~yooooo \n");

char *tempLine = malloc(1024); //because i should not modify line
strcpy(tempLine, line);
//printf("print tempLine = [%s] and line =[%s]\n", tempLine, line );
int i;
int substringPtr =0;

wordNode *wn =malloc(sizeof(wordNode));
wn->word = tempWord;


for( i=0; i < strlen(tempLine); i++){
	if( tempLine[i] == ' '){
		//printf("we found the space! and the location is [%d]\n", i);
		if( i == strlen(tempLine)-1 ){ //we found the last space
			return 1;
			//printf("Last space.... location is [%d]\n", i);
		}else{ //not last space--- means it should be a filename or an integer [filename integer filename integer ]
			if(isdigit( tempLine[i+1])){ //we don't need the integer.
				int difference = i - substringPtr;
				//char newSubstring[1024];
				char *newSubstring = malloc(1024); //Lesson of the day.... strncpy, memcpy and string[1024] != string =malloc(1024)
				//char newSubstring[strlen(tempLine)];
				strncpy(newSubstring, tempLine + substringPtr, difference);
			//	memcpy(newSubstring, tempLine + substringPtr, difference);
			//	newSubstring[i] = '\0';
				//printf("********This would be the string we needed : [%s], wn->word is:[%s]\n", newSubstring, wn->word );
				//printf("Discover(): I put wn = [%s] and fn = [%s] \n", wn->word, fn->filename );
				int returnNum;
				returnNum = insertWordNode(searchableIndex, wn, newSubstring);
		printf("%c[%d;%dmReturn from insert..!%c[%dm word=[%s], filename=[%s]\n",27,1,31,27,0, searchableIndex->wordlist->word, searchableIndex->wordlist->fileList->filename);
			//	return 1; should NOT RETURN!!! cuz we have to do it n-times
			}else{ //then behind space is the filename we need.
				substringPtr= i+1;
			}//end else

		}//end else
	}

}//end for
return 1;
}


static void printList( wordNode * root ) //useful printList function
{
	wordNode * p;
	for ( p = root ; p != 0 ; p = p->nextWord )
	{
		printf( "the word is [%s]\n", p->word );
		//printf( "data is %#x *data is %d next is %#x\n", p->word, *(int *)(p->word), p->nextWord );
	}
	printf( "End of list.\n" );
}


static void printList2(Node *root ) //useful printList function
{
	Node * p;

	for ( p = root ; p != 0 ; p = p->next )
	{
		printf( "the word is [%s]\n", p->fileName );
		//printf( "data is %#x *data is %d next is %#x\n", p->word, *(int *)(p->word), p->nextWord );
	}
	printf( "End of list.\n" );
}


int main(int argc, char** argv)
{
	if(argc!=2)
	{
		printf("Usage:\n\tsearch <inverted-index>\n");
		return 0;
	}
	/* declare a file pointer */
	FILE    *infile;
	char    *buffer;
	long    numbytes;

	/* open an existing file for reading */
	if( ! ( infile = fopen(argv[1], "r") ) )
	{
		printf("Error opening file\n");
		return 0;
	}

	/* quit if the file does not exist */
	if(infile == NULL)
	    return 1;

	/* Get the number of bytes */
	fseek(infile, 0L, SEEK_END);
	numbytes = ftell(infile);

	/* reset the file position indicator to the beginning of the file */
	fseek(infile, 0L, SEEK_SET);

	/* grab sufficient memory for the buffer to hold the text */
	buffer = (char*)calloc(numbytes, sizeof(char));

	/* memory error */
	if(buffer == NULL)
	    return 1;

	/* copy all the text into the buffer */
	fread(buffer, sizeof(char), numbytes, infile);
	//fclose(infile); //********************************* ????

	/* confirm we have read the file by outputing it to the console */
	printf("The file called 'output' contains this text\n\n%s", buffer);

	searchable_index *searchableIndex = malloc(sizeof(searchable_index) );

/*
if(searchableIndex->wordlist == NULL){
printf("IS NULL 8888888888888888888888888888888888888888888\n");
}else{
printf("is NOT NULL , [%s]\n", searchableIndex->wordlist);
}
*/

	char *list = "<list>";
	char *endlist = "</list>";

	char *line = malloc(1024);
	line = strtok(buffer, "\n");
	//char *line = strtok(buffer, "\n");
	char *tempWord = malloc(128);

		if( strncmp(line,list,6 ) != 0 ){ //if first 6 letters != <list>
			printf("ERROR: must begings with <list> \n");
		}else{
			//	printf("calling discoverword()....... line:[%s]\n", line);
			//	discoverWord(searchableIndex, line );
			while (line!= NULL) {
				if( strncmp(line, list, 6) == 0  ){ //if token == <list>
	
					//printf("calling discoverword()....... line:[%s]\n", line);
					//discoverWord(searchableIndex, line );
					tempWord= (line+=7);
				}else if (strncmp(line, endlist, 7)== 0 ){
					//printf("line begins with </list>\n");
					;
				}else{
					//printf("MAIN() Calling discoverFilename(): (%s),(%s)\n",line, tempWord);
					discoverFilename(searchableIndex, line, tempWord);
	//printf("??This is filename:[%s], this is length of file: [%d] \n",searchableIndex->wordlist->fileList->filename, strlen(searchableIndex->wordlist->fileList->filename));
				}
				line = strtok(NULL, "\n"); //we get the terms we need to create the individual VSLs
			}//end while
		}//end else

	//printList(searchableIndex->wordlist);
	//printf("In main(): filename:[%s],[%s]\n", searchableIndex->wordlist->fileList->filename, searchableIndex->wordlist->fileList->nextFile->filename); 

	char term[100]; //init size??

while(1)
{
	printf("Query options:\n\tsa <term(s)>\n\tso <term(s)>\n\t or q/quit:\t");

	//fgets (term, 100, stdin);
	//scanf("%s", term);
	scanf ("%[^\n]%*c", term);
	printf("You have entered [%s], length of term = [%d]\n", term, strlen(term));
		if( strcmp(term,"q") == 0 || strcmp(term,"quit")==0 || strcmp(term,"Quit")==0){
			printf("Goodbye!\n");
			exit(0);
		}
		else if (strncmp(term, "sa", 2) == 0){
			printf("First two characters are 'sa' - you have decided to use sa <term> function \n");
			char *token = malloc(sizeof(char)*256);
			token = strtok(term, " ");
			//char *token = strtok(term, " ");
			while (token != NULL) {
				printf("token:[%s], and length of token =[%d]\n", token, strlen(token));
				token = strtok(NULL, " "); //we get the terms we need to create the individual VSLs
			}
		}else if (strncmp(term, "so",2) ==0){
			printf("First two characters are 'so' - you have decided to use so <term> function \n");
			char *token = malloc(sizeof(char)*256);
			token = strtok(term, " ");
			//char *token = strtok(term, " ");
			sortedListPtr *sl = malloc(sizeof(sortedListPtr)); //init the sortedlist so we can store filenames there
		
			while (token != NULL) {
				//printf("token:[%s],and length of token =[%d]\n", token, strlen(token));
				so(sl, searchableIndex, token);
				printList2(sl->root);//print out the sortedListPtr's sl for me
				token = strtok(NULL, " "); //we get the terms we need to create the individual VSLs
			}
		}else{
			printf("You must enter sa<term>, so<term> or q(quit)\n");
			exit(0);
		}
	}
	/* free the memory we used for the buffer */
	free(buffer);

	return 0;
}

void sa(sortedListPtr *sl, searchable_index *searchableIndex, char * token)
{
	wordNode * tempWn;
	tempWn = searchableIndex->wordlist;
	
	//listA: list of terms in query
	//each query term contains a list of files it is contained in

	sortedListPtr * qsl;	
}


void so(sortedListPtr *sl, searchable_index *searchableIndex, char * token ){
	//printf("so() : What are the tokens:(%s), length of each token(for debug):[%d] \n", token, strlen(token) ); //ignore first token cuz its "so"

	wordNode *tempWn;//NTS: unnecessary: we overwrite the address below; also this creates a target for hackers by orphaning a tiny bit of memory// = malloc(sizeof(wordNode));
	tempWn = searchableIndex->wordlist; //tempWn holding the first word

	if(tempWn == NULL){
		printf("ERROR, our list is empty\n");
		exit(0);
	}else if(tempWn->nextWord == NULL){//list only has one word in it 
		if( strcmp(tempWn->word, token ) ){
			printf("The input matches the first(and only) word on the list! \n");
		}
	}else{
		printf("There are more than one words in the list\n");
		while(tempWn->nextWord != NULL){
		//printf("I am comparing tempWn->word:[%s], in the while loop \n", tempWn->word);
			if( strcmp(tempWn->word, token)==0 ){
				printf("THEY MATCHES(within while loop)! word is:[%s]\n ", token);
				outputAllFilenames(sl, tempWn, token);
			}
			tempWn= tempWn->nextWord;
		}//end while
		if (strcmp(tempWn->word, token)== 0){
			printf("THEY MATCHES!(after while-loop) word is:[%s]\n", token);
			outputAllFilenames(sl, tempWn, token);
		}
	}//end else
}

void outputAllFilenames(sortedListPtr *sl, wordNode *tempWn, char*token){
//printf("Welcome to outputAllFilenames() ! \n");

	if(tempWn->fileList->nextFile == NULL){ //only 1 filename in list
		printf("%c[%d;%dmResult Output:%c[%dm token=[%s]filenames:[%s]\n",27,1,36,27,0,token, tempWn->fileList->filename);
		soAddFilename(sl,tempWn->fileList->filename );
	}else{ //more than 1 filename in list, we have to loop it and print all

		fileNode *tempFn = malloc(sizeof(fileNode));
		tempFn = tempWn->fileList; //tempFn holds the head of fileList

		while( tempFn->nextFile != NULL){
			printf("%c[%d;%dmResult Output:%c[%dm token=[%s]filenames:[%s]\n",27,1,36,27,0,token, tempFn->filename);
			soAddFilename(sl,tempFn->filename );
			tempFn = tempFn->nextFile;
		}//one more after the while loop
		printf("%c[%d;%dmResult Output:%c[%dm token=[%s]filenames:[%s]\n",27,1,36,27,0,token, tempFn->filename);
		soAddFilename(sl,tempFn->filename );
	}
}


int soAddFilename(sortedListPtr *sl, char* filename){ //add the filenames to a list while removing duplicates
	if(sl->root == NULL){
		printf("soAddFilename():-------------------------->The sortedListPtr sl is empty \n");
		Node *newNode = malloc(sizeof(Node)); //create a new Node
		newNode->fileName = filename;
		sl->root = newNode;
		return 1;
	}else{
	//printf("soAddFilename(): Now its not longer empty, we have [%s]as first item in sortedListPtr sl\n", sl->root->fileName);
	//check if the filename ALREADY EXISTS in our list... if it is, ignore else add it to the end of our list
	if(sl->root->next ==  NULL){ //there is only 1 filename in the list
		if( strcmp (sl->root->fileName, filename)==0 ){
			printf("%c[%d;%dmThere is only1 filename in list, and found duplicates:%c[%dm filename:[%s]\n",27,1,33,27,0,sl->root->fileName);
			return 1;
		}else{//add the newfilenames to the back of the list
			Node *newNode = malloc(sizeof(Node));
			newNode->fileName = filename;
			sl->root->next = newNode;
			return 1;
		}
	}else{ //there are more than 1 filenames, we should loop through all files to find duplicates, if none, add at back of list
		Node *tempRoot =malloc(sizeof(Node));
		tempRoot = sl->root;

		while(tempRoot->next != NULL){
			if( strcmp( tempRoot->fileName, filename ) == 0 ){
				printf("%c[%d;%dmFound duplicates in while loop:%c[%dm filename:[%s]\n",27,1,33,27,0, tempRoot->fileName);
				return 1;
				}
				tempRoot= tempRoot->next;
			}
			if( strcmp( tempRoot->fileName, filename) == 0){ //last check, if we can't find the duplicates here, then theres none
				printf("%c[%d;%dmFound duplicates after while loop:%c[%dm filename:[%s]\n",27,1,33,27,0, tempRoot->fileName);
				return 1;
			}else{
				Node *newNode = malloc(sizeof(Node));
				newNode->fileName = filename;
				tempRoot->next = newNode;
				return 1;
				}
			}//end else
		}//end else
return 0;
}
