CC=gcc
CFLAGS=-Wall
OFILES=bookorder.o

all: clearscreen bookorder main

debug: clearscreen bookorder maindebug

#newrule
#	$(CC) $(CFLAGS) -c newfile.c
#
#	add newfile.o to end of main rule

clearscreen:
	clear

bookorder:
	$(CC) $(CFLAGS) -c bookorder.c

main:
	$(CC) $(CFLAGS) -o booksystem main.c $(OFILES)

maindebug:
	$(CC) $(CFLAGS) -g -o bookdebug main.c $(OFILES)

pull:
	scp schaffna@cpp.cs.rutgers.edu:~/book/* ~/book/

push:
	scp ~/book/* schaffna@cpp.cs.rutgers.edu:~/book/
