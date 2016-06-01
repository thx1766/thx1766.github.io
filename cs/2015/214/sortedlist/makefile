# makefile for assignment 2 (sorted list)
CC=gcc
CFLAGS=-Wall

# assignment directory
ADIR=pa2

# files to copy into submission bundle
CPFILES=sorted-list.h sorted-list.c main.c makefile testplan.txt readme.pdf

# -------------------------
all: lib exec

#library
lib: ostep astep

ostep:
	$(CC) $(CFLAGS) -c sorted-list.c

astep:
	ar -cvq libsl.a sorted-list.o

lclean:
	rm sorted-list.o libsl.a

exec:
	$(CC) $(CFLAGS) -o sl main.c -L. -lsl

# -------------------------
clean:
	

# --------------------------	
# bundles files for submission
bundle: touch dir copy zip rmcopy

# touches all files to prevent missing file error before all documents exist
touch:
	touch $(CPFILES)

dir:
	mkdir $(ADIR)

copy:
	cp $(CPFILES) $(ADIR)

zip:
	tar cfz $(ADIR).tgz $(ADIR)

rmcopy:
	rm -r $(ADIR)
