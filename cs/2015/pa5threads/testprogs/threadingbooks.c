#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>

void * threadFunc(void * arg)
{
	char * str;
	int i = 0;

	str = (char * ) arg;

	while(i<10)
	{
		usleep(1);
		printf("thraedfunc says: %s\n", str);
		i++;
	}
	return NULL;
}

int
main(int argc, char * argv[])
{
	pthread_t pth; // thread identifier	
	int i = 0;

	/* create worker thread */	
	pthread_create(&pth, NULL, threadFunc, "string passed to threadfunc...");

	/* wait for thread to finish before continuing */
	pthread_join(pth, NULL /* void ** return value here*/);

	while(i<10)
	{
		usleep(1);
		printf("main() is running...\n");
		++i;
	}

	return 0;
}
