#ifndef BOOKORDER_H
#define BOOKORDER_H

#include <stdio.h>

//primary function for the program kernel
void bookorder(char * fp0, char * fp1, char *fp2);

//verifys that a provided input file can be opened in read only mode
int checkopen(char * fp);

#endif
