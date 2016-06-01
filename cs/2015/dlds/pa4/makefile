CC=gcc
CFLAGS=-Wall

all:
	$(CC) $(CFLAGS) -o search search.c

test:
	./search output

indexer-jessie:
	$(CC) $(CFLAGS) -o indexer indexer.c

indexer-nat:
	$(CC) $(CFLAGS) -o indexer-nat indexer-nat.c

sorted-list:  sorted-list.c
	$(CC) $(CFLAGS) sorted-list sorted-list.c

$(LIB)/libsorted-list.a: sorted-list.o
	ar rv $(LIB)/libsorted-list.a sortedlist.o

clean:
	rm -f sorted-list
	rm -f *.o
