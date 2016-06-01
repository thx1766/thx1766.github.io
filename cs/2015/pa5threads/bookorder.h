#ifndef BOOKORDER_H
#define BOOKORDER_H

#include <stdio.h>
#include <stdlib.h>

#include "filelength.h"
#include "populate_buffer.h"

//structures

//client structures
//order structures
//categories structures

struct client_
{
	char * name;
	int clientID;
	double account_balance;
	char * street_address;
	char * state;
	char * zip;
	
	struct client * next;
};
typedef struct client_ client;

struct clients_
{
	client * list;
};
typedef struct clients_ clients;

struct order_
{
	char * book_title;
	double * price;
	int clientID;
	char * category;
};
typedef struct order_ order;

struct orders_
{
	order * list;
	int ordercount;
};
typedef struct orders_ orders;

struct category_
{
	char * category_name;
	struct category_ * next;
};
typedef struct category_ category;

struct categories_
{
	struct category_ * list;
	int num_categories;
};
typedef struct categories_ categories;

//function prototypes
void bookorder(char * arg1, char * arg2, char * arg3);

int filelength(FILE * fp);

int filelengthwrapper(char * filename);

void populate_buffer(char * buffer, char * filename);

orders * filetostructure_order(char * buffer);

clients * filetostructure_clientdatabase(char * buffer);

categories * filetostructure_categories(char * buffer);

#endif//BOOKORDER_H
