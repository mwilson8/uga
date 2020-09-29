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

using namespace std;

FILE* diff_popen  (const char *, const char *);
int   diff_pclose (FILE *);

long open_max (void);
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
  string input;

  //
  //
  //
  /* using popen & pclose - straight system calls */
  /*
  cout << "command: ";
  getline (cin, input);

  fp = popen (input.c_str(), "r");

  while ( fgets ( buff, BUFFSIZE, fp ) != NULL ) {
    printf ( "%s", buff );
  }//while

  pclose(fp);
  */
  /* end first block */
  //
  //
  //


  /* using APUE-defined popen & pclose */
  cout << "command v2: ";
  getline (cin, input);

     fp = diff_popen (input.c_str(), "r");

     while ( fgets ( buff, BUFFSIZE, fp ) != NULL ) {
       printf ( "%s", buff );
     }//while

     diff_pclose(fp);

  return EXIT_SUCCESS;

}//main

//
//
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
//
FILE*
diff_popen(const char *cmdstring, const char *type) {

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
      _exit(127); //special exit that doesn't co clean up?
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

}//diff_popen
//
//



//
//
int
diff_pclose(FILE *fp){

  int fd, stat;
  pid_t pid;

  if (childpid == NULL){ /* this would mean diff_popen was never called */
    errno = EINVAL;
    return (-1);
  }//if

  fd = fileno(fp);
  if (fd >= maxfd) { /* invalid file descriptor */
    errno = EINVAL;
    return (-1);
  }//if

  if ((pid = childpid[ fd ]) == 0) { /* fp wasnt opened by diff_popen */
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

}//diff_pclose
//
//

