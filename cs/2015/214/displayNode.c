void displayNode(tNode* node){
	if(node==NULL){
		puts("Attempted to print a null node");
		return;
	}else{
 /*if(node->type==0){
		printf("Separator:\t%s\n", node->data);
		return;
	}else{*/
	//	printf("Token:\t%s\n", node->data);
		puts(node->data);
		//printf("%s\n",node->data);
		return;
	}	
}
