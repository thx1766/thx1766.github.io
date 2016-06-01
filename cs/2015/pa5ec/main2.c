#include <stdio.h>

#include <sys/ipc.h>//shmget()??
#include <sys/shm.h>//shmget()??

#include <unistd.h>//execl()

void endfunc()
{
	printf("this is a test of printing after if fork()==0 execl else execl\n");
}

int countcustomers(char * filename)
{
	FILE * fp = NULL;
	fp = fopen(filename,"r");
	char temp = getc(fp);
	int linecount = 0;
	while(temp!=EOF)
	{
		if(temp=='\n')
			linecount++;
		printf("%c", temp);
		temp = getc(fp);
	}
	fclose(fp);
	printf("count:%d\n", linecount);
}

int main(int argc, char * argv[])
{
	char * filename="inputfile";
	int c_count = countcustomers(filename);

	key_t key_for_balance_locks;
	size_t balance_lock_size= sizeof(int)*c_count;
	key_t key_for_balances;
	size_t balances_size = sizeof(double)*c_count;
	int segment_locks = shmget(key_for_balance_locks, balance_lock_size, IPC_CREAT | 0666);
	int segment_balances = shmget(key_for_balances, balances_size, IPC_CREAT | 0666);

	atexit( endfunc );

	if(fork()==0)
//		execl("/bin/echo", "echo", "execl fork==0", 0);
		execl("./shm_connect", key_for_balance_locks, balance_lock_size, 0);
	else
		execl("/bin/echo", "echo", "execl fork!=0", 0);


	return 0;
}
