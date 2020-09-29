#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <limits.h>
#include <errno.h>
#include <sstream>
#include <algorithm>
#include <pwd.h>
#include <signal.h>

using namespace std;

FILE*  custom_open  (const char *, const char *);
int    custom_close (FILE *);
string getPrompt    ();
string getHomeDir   ();
long   open_max     (void);
void   trim         (string &);
void   setSignals   (sighandler_t);
#ifdef OPEN_MAX
static long openmax = OPEN_MAX;
#else
static long openmax = 0;
#endif

static pid_t *childpid = NULL;
static int maxfd;
//
//
//

int
main(){
  const int BUFFSIZE = 516;
  char buff[BUFFSIZE];
  FILE* fp;
  string input, extract, commandLine1;
  bool ranBuiltIn = false;
  vector<string> commandHistory;
  pid_t pid8;
  cout.setf(std::ios::unitbuf);
  //
  //



  /* currently not ignoring any signals, so not worried about the infinite loop*/
  while (true){





    /******* set shell to ignore signals *********/
    //    setSignals (SIG_IGN);

    /*
void
545 setSignals (sighandler_t sig) {
546
547   signal (SIGINT, sig);
548   signal (SIGQUIT, sig);
549   signal (SIGTSTP, sig);
550   signal (SIGTTIN, sig);
551   signal (SIGTTOU, sig);
552
553 }// setSignals
    */









    while (true){
    cout << getPrompt();
    getline (cin, input);
    if (input.length() > 0)
      break;
    } //while
    trim(input);

    commandLine1 = "";
    commandHistory.push_back(input);    

    /* if "e>" is in string, replace with 2> */
    if (input.find("e>") != string::npos) {
      size_t f = input.find("e>");
      input.replace(f, string("e>").length(), "2>"); 
      //      cout << " input changed to modify error stream.. new command is: " << input << endl;
    }//if

    /*replace e>> with 2>> */
    if (input.find("e>>") != string::npos) {
      size_t f = input.find("e>>");
      input.replace(f, string("e>>").length(), "2>>");
      //cout << " input changed to modify error stream.. new command is: " <<input << endl;
    }//if

    stringstream stream (input);
  //
  //
  //

  while (stream >> extract){
  /* built-ins */
  if (extract.compare("bg") == 0) {
    cout << "'$ bg' currently not implemented";

    if (stream >> extract){
      int x = stoi(extract);
      cout << " would move process " << x <<  " to the background" << endl;
    }//if
    else
      cout << " -- missing argument to bg " << endl;

    ranBuiltIn = true;
  } else if (extract.compare("cd") == 0){
         
    if (stream >> extract)
      chdir(extract.c_str());
    
    else
      chdir(getHomeDir().c_str());

    ranBuiltIn = true;


  } else if (extract.compare("exit") == 0){
    /* for the sake of debugging, return 0 if no arguments passed */
    int x = 0;   

    if (stream >> extract)
      x = stoi(extract);

      return( x );


    } else if (extract.compare("export") == 0){
   
    if (stream >> extract){ /* if called with an argument, make it an environment var */
      if (putenv(strdup(extract.c_str())) != 0){
	fprintf(stderr, "-%s: %s: %s\n", "1730sh", extract.c_str(), strerror(errno));
      }//if
   } else { /* if no arguments called with export, then sort & print environment vars */

      vector<string> env;

      for (unsigned int i = 0; environ[i] != nullptr; i++)
	env.push_back((string)environ[i]);
      
      sort(env.begin(), env.end());

      for (unsigned int i = 0; i < env.size(); i++)
	fprintf(stdout, "declare -x %s\n", env.at(i).c_str());

      env.clear();
    }//else

    ranBuiltIn = true;
  } else if (extract.compare("fg") == 0){
    cout << "'$ fg' currently not implemented";

    if (stream >> extract){
      int x = stoi(extract);
      cout << " would move process " << x << " to the foreground" << endl;
    }//if
    else
      cout << " -- missing argument" << endl;

    ranBuiltIn = true;
  } else if (extract.compare("help") == 0){
   
    cout << "HELP " << endl;
    cout << "- bg JID    : resume the stopped JID in the background" << endl;
    cout << "- cd [PATH] : change the current durectory to PATH, defaults to home directory" << endl;
    cout << "- exit [N]  : exits the shell with a status of N, defaults to exit status of last job" << endl;
    cout << "- fg JID    : resume JID in the foreground" << endl;
    cout << "- help      : display this menu" << endl;
    cout << "- jobs      : list current jobs" << endl;
    cout << "- history   : print all commands executed in this shell's life" << endl;
    cout << "- kill [-s SIGNAL] PID : sends SIGNAL to process with PID, defaults to SIGTERM if no SIGNAL supplied" << endl;
    cout << "- export NAME[=WORD]   : add NAME to environment variables" << endl;

    ranBuiltIn = true;
  } else if (extract.compare("jobs") == 0){
    cout << "'$ jobs' currently not implemented" << endl;
  
    ranBuiltIn = true;
  } else if (extract.compare("kill") == 0){
   
    string temp1, temp2;
    pid_t pid;
    int sig;
    temp1 = temp2 = "";
    if (stream >> temp1){
      
      if (temp1.compare("-s") == 0){ /* if temp1 is -s then a signal follows, so extract the signal into temp1 */
	stream >> temp1;
        stream >> temp2;             /* & extract the PID into temp2 */

	//convet signal to sig constant using map from MIKE?
	pid = reinterpret_cast<pid_t>(stoi(temp2));
	sig = stoi(temp1);
	
	if ((kill(pid, sig)) == -1)
	  fprintf(stderr, "kill: %s", strerror(errno));
	
	//	cout << "send signal " << sig << " to process with PID " << pid << endl;
      }//if

      else { /* if temp1 was not -s then temp1 should contain the PID to send SIGTERM to */
	
	pid = reinterpret_cast<pid_t>(stoi(temp1));

	
         if ((kill(pid, SIGTERM)) == -1)
           fprintf(stderr, "kill: %s", strerror(errno));
        
	 //cout << "send SIGTERM to process with PID " << pid << endl;
      }//else

    }//if
    else
      cout << " -- missing argument(s)" << endl;

    ranBuiltIn = true;


  } else if (extract.compare("history") == 0){
    cout << "command history:" << endl;
        
    for (unsigned int i = 0; i < commandHistory.size(); i++)
      cout << i << ": " << commandHistory.at(i) << endl;
    
    ranBuiltIn = true;
  } else{

    /* if it's not a built in, then just put in into a string that will be executed*/

    commandLine1 = commandLine1 + extract + " ";
    ranBuiltIn = false;

  }//else

  }//while stream extract
  

  if (!ranBuiltIn){

    /*the string commandLine1 now contains the entire job string
     * need to search to see if '&' is the last character
     */
    trim(commandLine1);
    
    /* if '&' is last character, need to run in background */
    if (commandLine1.find("&") == commandLine1.length() - 1){
      /* remove the '&' */
      commandLine1 = commandLine1.substr(0, commandLine1.size()-1);
      /* trim just in case */
      trim(commandLine1);
      /* on-screen verification */
      //cout << "background execute: " << commandLine1 << endl;


       if ((pid8 = fork()) == -1 ){
          perror ("fork error");
      
       } else if (pid8 == 0) { /* in child process */

	 /* execute block */
	fp = custom_open(commandLine1.c_str(), "r");
	while ( fgets ( buff, BUFFSIZE, fp ) != NULL ) {
        printf ( "%s", buff );
        }//while
	custom_close(fp);
	/* end block */

	/* on-screen verification for when child finishes */
	cout << "\nbackground process has finished executing" << endl;
	cout << getPrompt();
	return (0);

      } else { /* in parent, not supposed to be waiting */


      }//else



      }//if

    else{
  /* this is just an on-screen verification for test cases, not needed */
      //cout << "execute: " << commandLine1 << endl;
  
  /**
   *code to execute in foreground
   */

      /********* set child to default signals ********/
           setSignals (SIG_DFL);

     fp = custom_open (commandLine1.c_str(), "r");

     while ( fgets ( buff, BUFFSIZE, fp ) != NULL ) {
       printf ( "%s", buff );
     }//while

     custom_close(fp);

    }//else


  }//if !ranBuiltIn



  }//while true

  return EXIT_SUCCESS;

}//main

//
//
/**
 * sourced directly from APUE p 52
 */
long
open_max (void) {

  if (openmax == 0) {
    errno = 0;
    if (( openmax = sysconf(_SC_OPEN_MAX)) < 0 ) {
      if (errno == 0)
	openmax = 256; //arbitrary
      else
	perror("sysconf error for _SC_OPEN_MAX");
    }//if
  }//if
  return openmax;

}//open_max
//
//


//
/**
 * sourced directly from APUE pp 543-545
 */
FILE*
custom_open(const char *cmdstring, const char *type) {

  int      i;
  int      pfd[2];
  pid_t    pid;
  FILE *   fp;

  /*only allow "r" or "w"*/
  if ((type[ 0 ] != 'r' && type[ 0 ] != 'w') || type[ 1 ] != 0){
    errno = EINVAL;
    return NULL;
  }//if

  if (childpid == NULL){ //first time through, childpid hasn't been set yet
    /* allocate zeroed out array for child pids */
    maxfd = open_max();
    if ((childpid = reinterpret_cast<pid_t *>(calloc(maxfd, sizeof(pid_t)))) == NULL)
      return NULL;
  }//if

  if (pipe(pfd) < 0) 
    return NULL; /* errno set by pipe */

  if (pfd[ 0 ] >= maxfd || pfd[ 1 ] >= maxfd) {
    close (pfd[ 0 ]);
    close (pfd[ 1 ]);
    errno = EMFILE;
    return NULL;
  }//if

  if ((pid = fork()) < 0) {
    return NULL;
  }//if
  else if ( pid == 0){
    if (*type == 'r'){
      close (pfd[ 0 ]);
      if (pfd[ 1 ] != STDOUT_FILENO) {
	dup2(pfd[ 1 ], STDOUT_FILENO);
	close(pfd[ 1 ]);
      }//if
    }//if
    else {
      close(pfd[ 1 ]);
      if (pfd[ 0 ] != STDIN_FILENO) {
	dup2(pfd[ 0 ], STDIN_FILENO);
	close(pfd[ 0 ]);
      }//if
    }//else

      /*close all descriptors in childpid[] */
      for (i = 0; i < maxfd; i++)
	if (childpid[ i ] > 0)
	  close(i);

      execl("/bin/sh", "sh", "-c", cmdstring, (char *)0);
      _exit(127); //special exit that doesn't do clean up?
  }//else if

    /* parent continues */
    if (*type == 'r'){
      close (pfd[ 1 ]);
      if ((fp = fdopen(pfd[ 0 ], type)) == NULL)
	return NULL;
    }//if
    else {
      close (pfd[ 0 ]);
      if ((fp = fdopen(pfd[ 1 ], type)) == NULL)
	return NULL;
    }//else

    childpid[ fileno(fp) ] = pid; /* remember child pid for this fd */
    return (fp);

}//custom_open
//
//



//
//
/**
 * sourced directly from APUE p 545
 */
int
custom_close(FILE *fp){

  int fd, stat;
  pid_t pid;

  if (childpid == NULL){ /* this would mean custom_open was never called */
    errno = EINVAL;
    return (-1);
  }//if

  fd = fileno(fp);
  if (fd >= maxfd) { /* invalid file descriptor */
    errno = EINVAL;
    return (-1);
  }//if

  if ((pid = childpid[ fd ]) == 0) { /* fp wasnt opened by custom_open */
    errno = EINVAL;
    return (-1);
  }//if

  childpid[ fd ] = 0;
  if (fclose(fp) == EOF)
    return (-1);

  while (waitpid(pid, &stat, 0) < 0)
    if (errno != EINTR)
      return (-1); /*error other that EINTR from waitpid() */

  return (stat);

}//custom_close


//
//
//
string 
getPrompt(){
  string homedir = getHomeDir();
  string s = "1730sh:";
  string directory = get_current_dir_name();

  if (directory.find(homedir) != string::npos)
  directory.replace(directory.find(homedir), string(homedir).length(), "~");

  //  s = "running program.. PWD:";
  s = s + directory + "$ ";

  return s;
}//getPrompt

//
//
//
string
getHomeDir(){

  struct passwd *pw = getpwuid(getuid());
  const char *homedir = pw->pw_dir;

  return homedir;
}//getHomeDir

//
//
//
void
trim (string& str){
  size_t first = str.find_first_not_of(' ');
  size_t last = str.find_last_not_of(' ');
  str = str.substr(first, (last-first+1));
}//trim

//
//
//
void
setSignals (sighandler_t sig) {

  signal (SIGINT, sig);
  signal (SIGQUIT, sig);
  signal (SIGTSTP, sig);
  signal (SIGTTIN, sig);
  signal (SIGTTOU, sig);

}// setSignals
