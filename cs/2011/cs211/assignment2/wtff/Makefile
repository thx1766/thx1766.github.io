Compiler = gcc
CompilerFlags = -ansi -pedantic -Wall

all: add sub mult

add: add.c

sub: sub.c

mult: mult.c

test: test.c

add.c:
	$(Compiler) $(CompilerFlags) -c add.c -o add

sub.c:
	$(Compiler) $(CompilerFlags) -c sub.c -o sub

mult.c:
	$(Compiler) $(CompilerFlags) -c mult.c -o mult

test.c:
	$(Compiler) $(CompilerFlags) -c test.c -o test

clean:
	rm -f *.o
	rm -f mult
	rm -f sub
	rm -f add
	rm -f test
