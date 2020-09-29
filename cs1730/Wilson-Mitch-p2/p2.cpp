#include <ncurses.h>
#include <cstdlib>
#include <fcntl.h>
#include <iostream>
#include <string>
#include <vector>
#include <iterator>
#include <unistd.h>

using namespace std;

/*
  Function Prototypes
*/
WINDOW *create_newwin(int height, int width, int starty, int startx);
void printMenu(WINDOW *menu_win, int highlight);
void printFile(int c, char * v[]);
void saveAs();
void save(char fileName[80]);
void openWindow();
//void createMenu(int c, char * v[]);

//size of window
#define WIDTH 30
#define HEIGHT 10
int choice = 0;
int startx = 0;
int starty = 0;
char * fileName = new char [160]; 
WINDOW * firstwin;

//choices in the menu
const char *choices[] = { 
  "Open",
  "Save",
  "Save As",
  "Exit",
};

//number of choices for menu
int n_choices = sizeof(choices) / sizeof(char *);
vector < vector < char > > buff;

/*Main method
  Opens a window with an editable text area.
  Prints window with menu in it.

*/
int main( int argc, char * argv[] ) 
{
  // vector < vector < char > > buff;

  //vector<vector<char>>::iterator row = buff.begin();
  //vector<char>::iterator col = buff.begin()->begin();

  initscr();
  start_color();
  refresh();

  WINDOW * firstWin = newwin(50, 50, 0, 0);
  WINDOW * textWin = newwin(45, 45, 5, 5);

  keypad(stdscr, TRUE);
  keypad(firstWin, TRUE);
  keypad(textWin, TRUE);

  init_pair(1, COLOR_BLACK, COLOR_WHITE);
  init_pair(2, COLOR_BLUE, COLOR_GREEN);
  attron(COLOR_PAIR(1));
  bkgd(COLOR_PAIR(1));
  refresh();

  printw("F1: Menu                   CSCI 1730 TypeWriter \n", firstWin);
  refresh();

  raw();
  refresh();



  //char * fileName = new char [160];
  int f;

  char x [1];
  vector<char> temp;

  if (argc == 2) {
    fileName = argv[1];
    f = open(fileName, O_RDWR);
    //  } // if

  while(read(f,x,1))
    {
      temp.push_back(x[0]);
      if(x[0] == '\n')
	{
	  buff.push_back(temp);
	  temp.clear();
	}
    }
     
  wbkgd(textWin, COLOR_PAIR(2));
  
  for (unsigned int i = 0; i < buff.size(); i++)
    {
      for(unsigned int j = 0; j < buff.at(i).size(); j++)
	{
	  printw("%c", buff.at(i).at(j), textWin);
	}
    }
  refresh();
  } // if

  //if F1, open menu.
  while (1) {
  while(x[0] = getch() != KEY_F(1))
    {
      temp.push_back(x[0]);
      if (x[0] == '\n')
	{
	  buff.push_back(temp);
	  temp.clear();
	}// if
      
    } 
  if (x[0] == KEY_F(1))
    {
      printMenu ( firstwin, choice);
    }
  if (choice != 0 && choice != 1 && choice !=2 ){
    break;
  }

  } // infinite loop .. ?
  //endwin();
  return EXIT_SUCCESS;
} // end main                                                                                                                                                                                                      
void save(char fileName[80])
{ char buffer[1];
  int fd;
    fd =  open ( fileName, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH );
    for (int i = 0; i < buff.size(); i++){
      for (int j = 0; j < buff.at(i).size(); j++){
	buffer[0] = buff.at(i).at(j);
	write ( fd, buffer, 1);
      }//for 2
    }//for 1  
    close (fd);
}


void saveAs()
{
  char buffer[1];
  char fileName[ 80 ];
  //FILE *file; 
  initscr();
  cbreak();

  while (1){
    clear();
  printw("Enter the new name for the file: \n");
  getstr( fileName );
  int fd;
  if (fd = open ( fileName , O_CREAT | O_EXCL, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH ) == -1) // check if the file already exists
    {
      printw ("File already exists, overwrite file? (y or n)");
      close (fd);
  refresh();
  if ( getch() == 'y')
    {
      save(fileName);
      break;
      }//if (getch() ..
    }//if (open (...))

  else {
    fd = open ( fileName, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH) ;
    for (int i = 0; i < buff.size(); i++) {
      for (int j = 0; j < buff.at(i).size(); j++) {
	buffer[0] = buff.at(i).at(j);
	write ( fd, buffer, 1);
      
      }//2nd for
    }//first for
    close (fd);
    break;
  }//else
}

  endwin();

}



void printFile(int c, char * v []) //Open. Print file contents
                                                                                                                                                                                                                  
{
  int ch;
  /*
  File * buffer = fopen("buffer.txt", "w");
  char * buf = malloc(100000);
  setbuf(buffer, buf);
  fputs("Hello", buffer);
  fflush(buffer);
  */

  char fileString[ 80 ];
  FILE *file;

  initscr();
  cbreak();// Line buffering disabled                                                                                                                                                                             
  keypad(stdscr, TRUE);

  //if the user doesn't pass a command argument                                                                                                                                                                 

  //if the user cannot open the file, try again.                                                                                                                                                                  
  file = fopen( v[ 1 ], "r" );

  //prints file to the screen                                                                                                                                                                                    
  while ( ( ch = fgetc( file ) ) != EOF ) {
    printw( "%c", ch );
    refresh();
  }

  printw( "\nPress Enter to exit" );
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
    }
  wrefresh(menu_win);
}


/*createMenu prints the menu on the screen
  It uses the printMenu function and shows the choices: "open, save, save as, exit"
*/
int createMenu(int arc, char * v[])
{
  WINDOW * menu_win;
  int highlight = 1;
  choice = 0;
  int c;

  initscr();
  clear();
  keypad(stdscr, TRUE);
  cbreak();
  startx = (80 - WIDTH) / 2;
  starty = (24 - HEIGHT) / 2;

  refresh();
  menu_win = newwin(HEIGHT, WIDTH, starty, startx);
  keypad(menu_win, TRUE);
  
  start_color();
  init_pair(1, COLOR_WHITE, COLOR_BLACK);
  attron(COLOR_PAIR(1));
  wbkgd(menu_win, COLOR_PAIR(1));

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
      save(fileName);     
    }
  else if(choice == 2)
    {
      saveAs();
    }
  else
    {
      endwin();
      //      return -1;
    }
}
