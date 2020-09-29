
all: true false ls cal date cksum env head ln pwd
######

#true
true: true.o
	g++ -o true true.o
true.o: true.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors true.cpp
#//false

#false
false: false.o
	g++ -o false false.o
false.o: false.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors false.cpp
#//false

#ls
ls: ls.o
	g++ -o ls ls.o
ls.o: ls.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors ls.cpp
#//ls

#cal
cal: cal.o
	g++ -o cal cal.o
cal.o: cal.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors cal.cpp
#//cal

#date
date: date.o
	g++ -o date date.o
date.o: date.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors date.cpp
#//date

#cksum
cksum: cksum.o
	g++ -o cksum cksum.o
cksum.o: cksum.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors cksum.cpp
#//cksum

#env
env: env.o
	g++ -o env env.o
env.o: env.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors env.cpp
#//env

#head
head: head.o
	g++ -o head head.o
head.o: head.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors head.cpp
#//head

#ln
ln: ln.o
	g++ -o ln ln.o
ln.o: ln.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors ln.cpp
#//ln

#pwd
pwd: pwd.o
	g++ -o pwd pwd.o
pwd.o: pwd.cpp
	g++ -c -Wall -std=c++14 -g -O0 -pedantic-errors pwd.cpp
#//pwd

clean:
	rm -f *.o
	rm -f core.*
	rm -f true
	rm -f false
	rm -f ls
	rm -f cal
	rm -f cal2
	rm -f date
	rm -f cksum
	rm -f env
	rm -f head
	rm -f ln
	rm -f pwd
