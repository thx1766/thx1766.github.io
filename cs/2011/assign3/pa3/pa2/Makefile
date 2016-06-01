Compiler = gcc
CompilerFlags = -ansi -pedantic -Wall

all: fibonacci.c fib.s fib.h fibonacci

fibonacci.c:
	$(Compiler) $(CompilerFlags) -c fibonacci.c

fib.s:
	$(Compiler) $(CompilerFlags) -c fib.s

fib.h:
	$(Compiler) $(CompilerFlags) -c fib.h

fibonacci:
	$(Compiler) $(CompilerFlags) fib.s fib.h fibonacci.c -o fibonacci

clean:
	rm fibonacci
