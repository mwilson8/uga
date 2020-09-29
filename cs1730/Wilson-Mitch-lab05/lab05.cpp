#include <cstdlib>
#include <ncurses.h>
#include <fcntl.h>

int main (int argc, char * argv []) {

  int ch;
  char fileString [80];
  FILE * file;
  initscr();

  if (argc != 2) {
    printw("enter a file name: \n");
    getstr (fileString);
    refresh();
    clear();
    argv[1] = fileString;
  }//end if

  while (open (argv[1], O_RDONLY) == -1) {

    printw("error opening file");
    getstr (fileString);
    refresh();
    argv [1] = fileString;
  } //end while

  file = fopen(argv [1], "r");

  while ( ( ch = fgetc(file) ) != EOF ) {

    printw ("%c", ch);
    refresh();

  }//end while

  addnstr( " test string to be added ", 10);
  //printw ( "<~~press any key to exit~~>");
  getch();
  endwin();
  fclose ( file );

  return 0;

}//end main
