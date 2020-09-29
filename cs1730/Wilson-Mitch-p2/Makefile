all : p2

p2 : p2.o
	g++ -Wall -lncurses -g -o p2 p2.o

p2.o : p2.cpp
	g++ -Wall -std=c++14 -c -lncurses -g -O0 -pedantic-errors p2.cpp

clean : 
	rm -f p2
	rm -f *.o