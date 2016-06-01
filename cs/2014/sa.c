

/*
	Insert cases
	before first (strcmp (cur, new) < 0 )
		new->next = list
		list->next = new
	after first 
		new -> next = current -> next
		current -> next = new
	after last
		current -> next = new
		new -> next = NULL
*/

void add_NTSword_to_sa_struct(struct NTSword * myword, struct sa_struct){

}

void add_NTSfile_to_NTSword(struct NTSfile * myfile, struct NTSword * myword){
	
}

void trim_non_duplicates_for_sa(struct sa_struct * mysastruct){

}

void sa(searchable_index * searchableIndex, char * tokens){

	char * word = malloc(sizeof(char)*256);//max query term size is 256 chars

	word = strtok(tokens, " ");//word should now be "sa"

	while(word!=NULL){//loop through tokens
		word = strtok(NULL, " ");
		if(word == NULL)
			break;
	}
}
