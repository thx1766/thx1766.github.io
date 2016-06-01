#include <stdio.h>

//global_value

int global_value = 0xdeadbeef;

int test(){

	global_value = 0x4a276234;

	return 1;
}

int main(int argc, char * argv){
	
	printf("1: global_value: %x\n", global_value);
	global_value = 0xff;
	printf("2: global_value: %x\n", global_value);
	test();
	printf("3: global_value: %x\n", global_value);
//	printf("test");

	return 0;
}
