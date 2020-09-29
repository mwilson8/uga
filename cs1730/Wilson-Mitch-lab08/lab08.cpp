#include <iostream>
#include <cstdlib>
#include <errno.h>
#include <fcntl.h>
#include <string>
#include <unistd.h>

using namespace std;
int fd;
void readInput();
int main ( int argc,  char * argv[]) {
  char buf [1];
  int x = 1;

  if (argc == 1)
    {

      readInput();
    }//if

  else 
    for (int i = 1; i < argc; i ++ ){

      if (argv[i] == "-")
	readInput();

      else if ( (fd = open (argv[i], O_RDONLY)) == -1)
	cout << "Invalid file name" << endl;

	  while (read(fd, buf, x) > 0) 
	    write (STDOUT_FILENO, buf, x);

	   }

    return EXIT_SUCCESS;
}//main
 void readInput()
      {
	int x;
	char buf [1];
	while (read (STDIN_FILENO, buf, x) > 0)
	  write ( STDOUT_FILENO, buf, x );
      }
