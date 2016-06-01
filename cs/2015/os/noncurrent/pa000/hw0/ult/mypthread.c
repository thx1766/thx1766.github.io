int mypthread_create(mypthread_t *thread, const mypthread_attr_t *attr, void *(*start_routine) (void *), void *arg)
{
	//used to create a new thread
	//
	//1
	//makecontext for new thread
	//
	//2
	//add context created to queue
	//
	//(nothing else needs to change at this step)
	return 0;
}

void mypthread_exit(void *retval)
{
	//terminates the calling thread
	//
	//1
	//makecontext from next thread in queue
	//
	//2
	//remove current thread from queue
	//
	//3
	//setcontext from nextthread
}

int mypthread_yield(void)
{
	//causes calling thread to relinquish CPU
	//thread placed at end of un queue
	//another thread is scheduled to run
	//
	//1
	//makecontext for current thread
	//
	//2
	//insert created context into queue
	//
	//3
	//makecontext for thread next in queue
	//
	//4
	//setcontext as next thread 
	return 0;
}

int mypthread_join(mypthread_t thread, void **retval)
{
	//suspends execution of calling thread until target thread
	//terminates, unless target thread has already terminated
	//
	//1
	//makecontext for new thread
	//
	//2
	//makecontext for old thread
	//
	//3
	//swapcontext from old thread to new
	return 0;
}


