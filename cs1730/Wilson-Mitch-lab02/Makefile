
all: stat minmax numbers degrees time


stat: stat.o
	g++ -o stat stat.o

stat.o: stat.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors stat.cpp

minmax: minmax.o
	g++ -o minmax minmax.o

minmax.o: minmax.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors minmax.cpp

numbers: numbers.o
	g++ -o numbers numbers.o

numbers.o: numbers.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors numbers.cpp

degrees: degrees.o
	g++ -o degrees degrees.o

degrees.o: degrees.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors degrees.cpp

time: time.o
	g++ -o time time.o

time.o: time.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors time.cpp

clean:
	rm -f stat
	rm -f stat.o
	rm -f minmax
	rm -f minmax.o
	rm -f numbers
	rm -f numbers.o
	rm -f degrees
	rm -f degrees.o
	rm -f time
	rm -f time.o