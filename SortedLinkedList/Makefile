# CC = g++
#DEBUG = -g -O0 -pedantic-errors
#CFLAGS = -Wall -std=c++11 -c $(DEBUG)
#LFLAGS = -Wall $(DEBUG)

all: DataType SortedLinkedList main

DataType: DataType.o
	g++  -o DataType DataType.o

DataType.o: DataType.cpp DataType.h
	g++  -c DataType.cpp

SortedLinkedList: SortedLinkedList.o
	g++ -o  SortedLinkedList SortedLinkedList.o

SortedLinkedList.o: SortedLinkedList.cpp SortedLinkedList.h ListNode.h
	g++ -c SortedLinkedList.cpp

main.o: main.cpp
	g++ -c main.cpp

main: main.o DataType.o SortedLinkedList.o
	g++ -o main main.o DataType.o SortedLinkedList.o

run: main
	./main

clean:
	rm -rf *.o
	rm -rf DataType
	rm -rf SortedLinkedList
	rm -rf main
	rm -rf *~