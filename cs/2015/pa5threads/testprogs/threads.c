#include <stdio.h>

#include "threada.h"
#include "threadb.h"


int
main(int argc, char * argv[])
{

	printf("main() beginning\n");

	pthread_t thread0;//threada
	pthread_t thread1;//threadb

	pthread_create(&thread0, NULL, threada, "stringggggg");
	pthread_create(&thread1, NULL, threadb, "stringggggg");

	pthread_join(thread0, NULL);
	pthread_join(thread1, NULL);

	return 0;
}
