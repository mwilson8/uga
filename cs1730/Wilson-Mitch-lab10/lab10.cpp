#include <cstdlib>
#include <iostream>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <dirent.h>
#include <unistd.h>
#include <errno.h>
#include <string>
#include <string.h>
using namespace std;
int remove_directory(const char * path, bool force, bool recursive);
int main(int argc, char* argv[])
{
  bool recursive = false, force = false;
  int opt;

  if (argc == 1)
    { 
      fprintf(stderr, "%s missing operand\n", argv[0]);
    }//if
  while (( opt = getopt(argc, argv, "rf")) != -1){
  switch ( opt ) {

  case 'r': recursive = true;    break;
  case 'f': force = true;        break;
  default: return EXIT_FAILURE;  break;

  }//switch
  }//while

  remove_directory(argv[1], force, recursive);

}//main

int remove_directory(const char *path, bool force, bool recursive)
  {
    DIR * dp;
    FILE * fp;
    struct stat buf;
    struct dirent *dstruct;
    if ( (dp = opendir(path)) == NULL)
      {
	printf("not a directory\n");

	
      }//if
    else{
    dstruct = readdir(dp);
    stat(dstruct->d_name, &buf);
    }
    if (force)
      printf("force is active\n");

    if (recursive)
      printf("recursive is active\n");
     

    int count = 0;
	    /* Skip the names "." and ".." as we don't want to recurse on them. */
	    if (!strcmp(dstruct->d_name, ".") || !strcmp(dstruct->d_name, "..") && count <= 2)
	      {
		printf("skipping %s", dstruct->d_name);
		count ++;
	      }
	
		    if (S_ISDIR(buf.st_mode))
		      {
			printf("would remove directory: %s\n", dstruct->d_name);
			//r2 = remove_directory(buf);

		      }
		    else
		      {
			printf("would remove file: %s\n", *fp);
			//r2 = unlink(buf);

		      }
		    closedir(dp);		 


  }//remove_directory
