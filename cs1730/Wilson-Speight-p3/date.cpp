#include <cstdlib>
#include <ctime>
#include <iostream>
#include <iomanip>
using namespace std;

int main(int argc, char * argv[]){

  time_t now = time(0);
  char* time_string = ctime(&now);

  if (argc == 1){
  /*************
   *default case: print current time in format
   * DOW MMM DD HH:MM:SS TMZ YYYY
   *************/
  //////////////
  printf("%.*s", 19, time_string);
  printf(" EDT "); // yes this is not the best idea
  printf("%.*s\n", 5, time_string + 19);
  ///////////////
  }//if 

  else if (argc == 2) {
    char outstr[200];
    time_t t;
    struct tm *tmp;

    t = time(NULL);
    tmp = localtime(&t);
    if (tmp == NULL) {
      perror("localtime");
      return EXIT_FAILURE;
    }

    if (strftime(outstr, sizeof(outstr), argv[1]+1, tmp) == 0) {
      fprintf(stderr, "strftime error");
      return EXIT_FAILURE;
    }

    printf("%s \n", outstr);
  }

  return EXIT_SUCCESS;
}//main
