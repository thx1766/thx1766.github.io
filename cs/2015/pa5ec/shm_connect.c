#include <stdio.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <sys/types.h>

int
main(int argc, char * argv[])
{
	int shmid;
	key_t key = (key_t) argv[0];
	
	char * shm;

	shmid = shmget(key, argv[1], 0666);

	shm = shmat(shmid, NULL, 0); 

	return 0;
}
