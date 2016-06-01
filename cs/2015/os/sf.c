#include <stdio.h>

int main(int argc, char ** argv){
	printf("Hallo Wollt!!\n");

	int testarray[5]  = {1,2,3,4};

int a = 4;


while(1)
	printf("\na is now at %d\n, the value of testarray[%d] is %d\n",a, a, testarray[a--]);
	

	printf("should crash before this line\n");
}
