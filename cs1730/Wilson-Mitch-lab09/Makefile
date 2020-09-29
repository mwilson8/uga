#stat 
all: stat


stat: stat.o
	g++ -o stat stat.o

stat.o: stat.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors stat.cpp

clean:
	rm -f stat
	rm -f stat.o