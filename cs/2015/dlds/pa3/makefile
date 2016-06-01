CC=gcc
CFLAGS=-Wall
ADIR=pa3
CPFILES=index.c index.h makefile
#testplan.txt readme.pdf

all:
	$(CC) $(CFLAGS) -o index index.c

zip:
	mkdir $(ADIR)
	cp $(CPFILES) $(ADIR)
	tar cfz $(ADIR).tgz $(ADIR)
	rm -r $(ADIR)


clean:
	rm index

buildtest1:
	mkdir a
	$(all)
	cp ./index ./a/
	echo "A dog name name Boo" > ./a/boo
	echo "A cat name Baa" > ./a/baa
	cd ./a/
	./a/index out .
test1:
	echo "This test was run with \"make buildtest1\". It creates the example given in the assignment document, and stores the output in ./a/out"

cleantest1:
	rm -r ./a/

buildtest2:
	mkdir ./b/
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file0
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file1
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file2
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file3
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file4
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file5
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file6
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file7
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file8
	echo "testword tw0 tw1 tw2 tw3 tw4 tw5" > ./b/file9
	$(all)
	mv ./index ./b/index
	cd ./b/
	./index out file0
	./index out2 .
test2:
	echo "test 2 was set up and run with \"make buildtest2\", and output are in ./b/out and ./b/out2, showing both single file and directory scanning. Clean up with cleantest2"

cleantest2:
	rm -r ./b/
