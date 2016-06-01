tNode* destroyNode(tNode* target){
	if(target==NULL){
		return target;
	}else{
		free(target->data);
		free(target);
		target=NULL;
		return target;
	}
}
