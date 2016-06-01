void so(sortedListPtr *sl, searchable_index *searchableIndex, char * token ){
	wordNode *tempWn;
	tempWn = searchableIndex->wordlist; //tempWn holding the first word
	if(tempWn == NULL){
		printf("list empty\n");
		exit(0);
	}else{
		while(tempWn->nextWord != NULL){
			if( strcmp(tempWn->word, token)==0 )
				outputAllFilenames(sl, tempWn, token);
			tempWn= tempWn->nextWord;
		}
		if (strcmp(tempWn->word, token)== 0)
			outputAllFilenames(sl, tempWn, token);
	}
}

void outputAllFilenames(sortedListPtr *sl, wordNode *tempWn, char*token){
	if(tempWn->fileList->nextFile == NULL)//only 1 filename in list
		soAddFilename(sl,tempWn->fileList->filename );
	else{ //more than 1 filename in list, we have to loop it and print all
		fileNode *tempFn = malloc(sizeof(fileNode));
		tempFn = tempWn->fileList; //tempFn holds the head of fileList
		while( tempFn->nextFile != NULL){
			printf("Result Output: token=[%s]filenames:[%s]\n",token, tempFn->filename);
			soAddFilename(sl,tempFn->filename );
			tempFn = tempFn->nextFile;
		}//one more after the while loop
		printf("Result Output: token=[%s]filenames:[%s]\n",token, tempFn->filename);
		soAddFilename(sl,tempFn->filename );
	}
}

int soAddFilename(sortedListPtr *sl, char* filename){ //add the filenames to a list while removing duplicates
	if(sl->root == NULL){
		Node *newNode = malloc(sizeof(Node)); //create a new Node
		newNode->fileName = filename;
		sl->root = newNode;
		return 1;
	}else{
		//check if the filename ALREADY EXISTS in our list... if it is, ignore else add it to the end of our list
		if(sl->root->next ==  NULL){ //there is only 1 filename in the list
			if( strcmp (sl->root->fileName, filename)==0 )
				return 1;
			else{//add the newfilenames to the back of the list
				Node *newNode = malloc(sizeof(Node));
				newNode->fileName = filename;
				sl->root->next = newNode;
				return 1;
			}
		}else{ //there are more than 1 filenames, we should loop through all files to find duplicates, if none, add at back of list
			Node *tempRoot =malloc(sizeof(Node));
			tempRoot = sl->root;
			while(tempRoot->next != NULL){
				if( strcmp( tempRoot->fileName, filename ) == 0 )
					return 1;
				tempRoot= tempRoot->next;
			}
			if( strcmp( tempRoot->fileName, filename) == 0)//last check, if we can't find the duplicates here, then theres none
				return 1;
			else{
				Node *newNode = malloc(sizeof(Node));
				newNode->fileName = filename;
				tempRoot->next = newNode;
				return 1;
			}
		}
	}
	return 0;
}
