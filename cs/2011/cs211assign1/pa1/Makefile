CC = gcc
flags= -ansi -pedantic -Wall

all: wordstat

wordstat: wordstat.c
	$(CC) $(flags) wordstat.c wordstat.h -o wordstat

clean: 
	rm -rf *.o wordstat
