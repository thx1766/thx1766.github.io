Compiler = gcc
CompilerFlags = -ansi -pedantic -Wall

all: sum2n.c sumInts.c sumInts.h sum2n

sum2n.c:
	$(Compiler) $(CompilerFlags) -c sum2n.c

sumInts.c:
	$(Compiler) $(CompilerFlags) -c sumInts.s


sumInts.h:
	$(Compiler) $(CompilerFlags) -c sumInts.h

sum2n:
	$(Compiler) $(CompilerFlags) sum2n.c sumInts.s sumInts.h -o sum2n

clean:
	rm sum2n
	rm *.o
