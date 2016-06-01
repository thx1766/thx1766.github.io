void displayList(tList* myList){
	if(myList==NULL)
		puts("Attempted to print NULL list");
	else if(myList->size==0)
		puts("Attempted to print empty list");)
	else{
		int index=0;
		tNode* current = myList->first;
		while(index<myList->size && current!=NULL){
			displayNode(current);
			index++;
			current = current->nextNode;
		}
	}
}
