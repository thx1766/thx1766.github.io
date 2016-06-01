#include "bookorder.h"

void
bookorder(char * clientdbfilename, char * orderinputfilename, char * categoriesfilename)
{
	FILE * clients = NULL;
	FILE * orders = NULL;
	FILE * categories = NULL;

	//calculate lengths of files for buffers
	int client_bufferlen = filelengthwrapper(clientdbfilename);
	int order_bufferlen = filelengthwrapper(orderinputfilename);
	int category_bufferlen = filelengthwrapper(categoriesfilename);

	//create buffers
	char * client_buffer = malloc(sizeof(char)*client_bufferlen);
	char * order_buffer = malloc(sizeof(char)*order_bufferlen);
	char * category_buffer = malloc(sizeof(char)*category_bufferlen);
    
    //copy data from files into buffers
    populate_buffer(client_buffer,clientdbfilename);
    populate_buffer(order_buffer, orderinputfilename);
    populate_buffer(category_buffer, categoriesfilename);

    //copy data from buffers into structures
//  ... filetostructure_ ...
//  ... filetostructure_ ...
//  ... filetostructure_ ...
    
	printf("bookorder()\n");

}


orders *
filetostructure_order(char * buffer)
{
    orders * myorders = malloc(sizeof(orders));
    int index = 0;
    char temp = buffer[index];
    
    //order layout:
    //<doublequote> <book title> <doublequote> <verticalbar> <double:value> <verticalbar> <integer:userIDnumber> <verticalbar> <category> <newline>
    //"I Could Pee on This: And Other Poems by Cats"|7.49|1|HOUSING01
    
    //loop until newline or end of file
    
    return myorders;
}

clients *
filetostructure_clientdatabase(char * buffer)
{
    clients * myclients = malloc(sizeof(clients));
    return myclients;
}

categories *
filetostructure_categories(char * buffer)
{
    categories * mycategories = malloc(sizeof(categories));
    return mycategories;
}
