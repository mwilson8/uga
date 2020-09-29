#makefile

all: lab01

run: lab01
	./lab01


#compilation
lab01.o:lab01.cpp
	g++ -c lab01.cpp

#linking
lab01:lab01.o
	g++ -o lab01.o

clean:
	rm -f lab01
	rm -f lab01.o