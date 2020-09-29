#include <ncurses.h>
#include <cstdlib>
#include <fcntl.h>
#include <iostream>
#include <string>

using namespace std;

WINDOW *create_newwin(int height, int width, int starty, int startx);
void printMenu(WINDOW *menu_win, int highlight);
void printFile(int c, char * v[]);
void saveAs();
void save();
void openWindow();
void createMenu(int c, char * v[]);

#define WIDTH 30
#define HEIGHT 10 

int startx = 0;
int starty = 0;
char * argv[80];
int argc;

const char *choices[] = { 
  "Open",
  "Save",
  "Save As",
  "Exit",
};

int n_choices = sizeof(choices) / sizeof(char *);

int main( int argc, char * argv[] ) 
{
  createMenu(argc, argv);
  endwin();
  return EXIT_SUCCESS;
} // end main                                                                                                                                                                                                      
void saveAs()
{
  
}


void save()
{
  //int ch
  
  char fileName[ 80 ];
  //FILE *file; 
  initscr();
  cbreak();

  printw("Enter the new name for the file: \n");
  getstr( fileName );
  refresh();
  getch();
  endwin();

}



void printFile(int c, char * v []) //Open. Print file contents                         
{
  WINDOW *my_win;
  int startx = 0;
  int starty = 0;
  int width = 0;
  int height = 0;

  int ch;
  char fileString[ 80 ];
  FILE *file;

  initscr();
  cbreak();// Line buffering disabled                                                                                                                                                                             
  keypad(stdscr, TRUE);

  height = 3;
  width = 10;
  starty = (LINES - height) / 2;
  startx = (COLS - width) / 2;
  my_win = create_newwin(height, width, starty, startx);
  printw("", my_win);
  //if the user doesn't pass a command argument                                                                                                                                                                    
  if ( c != 2 ) {
    printw( "Enter a file name: \n");
    getstr( fileString );
    refresh();
    clear();
    v[1] = fileString;
  }

  //if the user cannot open the file, try again.                                                                                                                                                                   
  while ( open( v[ 1 ], O_RDONLY ) == -1 ) {
    printw( "Invalid File Name. Try again\n" );
    getstr( fileString );
    refresh();
    v [ 1 ] = fileString;
  }
  string fileName = string (fileString);
  file = fopen( v[ 1 ], "r" );

  //prints file to the screen                                                                                                                                                                                        
  while ( ( ch = fgetc( file ) ) != EOF ) {
    printw( "%c", ch );
    refresh();
  }

  printw( "\nPress Enter to exit" );
  printw( "\nfile name is :\n %s", fileName);
  getch();
  endwin();
  fclose( file );
} //open. print file contents                                                                                                                                                                                      



WINDOW *create_newwin(int height, int width, int starty, int startx)
{
  WINDOW *local_win;

  local_win = newwin(height, width, starty, startx);
  box(local_win, 0 , 0);/* 0, 0 gives default characters                                                                                                                                                           
                         * for the vertical and horizontal                                                                                                                                                         
                         * lines*/
  wrefresh(local_win);

  return local_win;
}

void printMenu(WINDOW *menu_win, int highlight)
{
  int x, y, i;

  x = 2;
  y = 2;
  box(menu_win, 0, 0);
  for(i = 0; i < n_choices; ++i)
    {if(highlight == i + 1) /* High light the present choice */
	{wattron(menu_win, A_REVERSE); 
	  mvwprintw(menu_win, y, x, "%s", choices[i]);
	  wattroff(menu_win, A_REVERSE);
	}
      else
	mvwprintw(menu_win, y, x, "%s", choices[i]);
      ++y;
    }wrefresh(menu_win);
}

void createMenu(int arc, char * v[])
{
  WINDOW * menu_win;
  int highlight = 1;
  int choice = 0;
  int c;
  //  int ch;

  initscr();
  clear();
  keypad(stdscr, TRUE);
  cbreak();/* Line buffering disabled. pass on everything */
  startx = (80 - WIDTH) / 2;
  starty = (24 - HEIGHT) / 2;

  refresh();
  menu_win = newwin(HEIGHT, WIDTH, starty, startx);
  keypad(menu_win, TRUE);
  refresh();

  printMenu(menu_win, highlight);
  while(1)
    {
      c = wgetch(menu_win);
      switch(c)
	{case KEY_UP:
	    if(choice-1 < 0)
	      {
		choice = 3;
	      }
	    else
	      {
		choice--;
	      }
	    if(highlight == 1)
	      highlight = n_choices;
	    else
	      --highlight;
	    break;
	case KEY_DOWN:
	  if(choice+1 > 3)
	    {
	      choice = 0;
	    }
	  else
	    {
	      choice++;
	    }
	  if(highlight == n_choices)
	    highlight = 1;
	  else
	    ++highlight;
	  break;
	case 10:
	  break;

	default:
	  mvprintw(24, 0, "Press up or down.");
	  refresh();
	  break;
	}
      printMenu(menu_win, highlight);
      if(c == 10)/* User did a choice come out of the infinite loop */
	break;
    }

  if(choice == 0)
    {
      clear();
      printFile(arc, v);
    }
  else if(choice == 1)
    {
      cout << "Choice 2" << endl;
    }
  else if(choice == 2)
    {
      cout << "Choice 3" << endl;
    }
  else
    {
      endwin();
    }
}
