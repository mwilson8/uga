
all: lab08


lab08: lab08.o
	g++ -o lab08 lab08.o

lab08.o: lab08.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors lab08.cpp

clean:
	rm -f lab08
	rm -f lab08.o