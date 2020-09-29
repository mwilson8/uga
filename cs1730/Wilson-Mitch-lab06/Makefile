all : lab06

lab06 : lab06.o
	g++ -Wall -lncurses -g -o lab06 lab06.o

lab06.o : lab06.cpp
	g++ -Wall -std=c++14 -c -lncurses -g -O0 -pedantic-errors lab06.cpp

clean : 
	rm -f lab06
	rm -f *.o