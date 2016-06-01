#include <stdio.h>

int add(int a, int b){
	return (a+b);
	}

int main(int argc, char** argv){
	int one = 1;
	int two = 2;
	int result = add(one,two);
	printf("the result of %d + %d is %d\n", one, two, result);
	}
