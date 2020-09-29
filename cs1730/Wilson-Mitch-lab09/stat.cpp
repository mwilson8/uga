#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <cstdlib>
#include <iostream>
#include <pwd.h>
#include <grp.h>

using namespace std;


string mode_to_letters ( int mode );
int main (int argc, char * argv [])
{
  struct stat sb;
  struct passwd *pw;
  struct group *gr;
  /*
  if ( argc > 1 ) {
    for (int i = 1; i < argc; i++) { 
      if (argv [i][0] == '-') { //catching flags
	fstat ( STDIN_FILENO, &file);
	print_info (&file, argv[i]);
      }//if
      else if ( ( lstat( argv[i], &file )) !=-1) {
	print_infor(&file, argv[i];
      }//else if
	else if ((stat( argv[i], &file)) !=-1) {
	  print_info (&file, argv[i]);
	}//else if
	else {
	  fprintf( stderr, "%s: cannot stat `%s': %s\n", argv[0], argv[i], strerror ( errno));
	}//else
    }//for
 } // if
  
    else {
      fprintf(stderr, "%s: missing operand\n", argv[0]);
    }//else
  */ 

 stat ( argv[1], &sb );
  pw = getpwuid (sb.st_uid);
  gr = getgrgid (sb.st_gid);
  cout << "  File: '" << argv[1] << "'" << endl;
  cout << "  Size: " << ((long long) sb.st_size)
       << "\t\tBlocks: " << ((long long) sb.st_blocks) 
       << "\t\tIO Block: " << ((long) sb.st_blksize) 
       << " \t";
  
  switch (sb.st_mode & S_IFMT) {
  case S_IFBLK:  printf("block device\n");            break;
  case S_IFCHR:  printf("character device\n");        break;
  case S_IFDIR:  printf("directory\n");               break;
  case S_IFIFO:  printf("FIFO/pipe\n");               break;
  case S_IFLNK:  printf("symlink\n");                 break;
  case S_IFREG:  printf("regular file\n");            break;
  case S_IFSOCK: printf("socket\n");                  break;
  default:       printf("unknown?\n");                break;
  }

  cout << "Device: " << hex << sb.st_dev << "h/" << dec <<  sb.st_dev << "d\t" 
       << "Inode: "  << (long)sb.st_ino << "\tLinks: " <<  (long) sb.st_nlink << endl;
  cout << "Access: ("  <<  oct << "0" <<  (long)(sb.st_mode & 07777) << "/" << mode_to_letters (sb.st_mode) << ")";
  cout << "\tUid: (" << dec << (long) sb.st_uid << "/ " << (pw->pw_name) << ")";
  cout << "\tGid: (" << dec << (long) sb.st_gid << "/ " << (gr->gr_name)  << ")" << endl;
  cout << "Access: " << ctime(&sb.st_atime);
  cout << "Modify: " << ctime(&sb.st_mtime);
  cout << "Change: " << ctime(&sb.st_ctime);
  cout << endl;

       
  return  EXIT_SUCCESS;
} //main

string  mode_to_letters ( int mode) {

 
  string str = "----------";

    if ( S_ISDIR(mode) )  str[0] = 'd';    /* directory?       */
    if ( S_ISCHR(mode) )  str[0] = 'c';    /* char devices     */
    if ( S_ISBLK(mode) )  str[0] = 'b';    /* block device     */

    if ( mode & S_IRUSR ) str[1] = 'r';    /* 3 bits for user  */
    if ( mode & S_IWUSR ) str[2] = 'w';
    if ( mode & S_IXUSR ) str[3] = 'x';

    if ( mode & S_IRGRP ) str[4] = 'r';    /* 3 bits for group */
    if ( mode & S_IWGRP ) str[5] = 'w';
    if ( mode & S_IXGRP ) str[6] = 'x';

    if ( mode & S_IROTH ) str[7] = 'r';    /* 3 bits for other */
    if ( mode & S_IWOTH ) str[8] = 'w';
    if ( mode & S_IXOTH ) str[9] = 'x';
 
    return str;
}//m_t_l

/*
 void print_info ( &file file, char * ch)
 {

   pw = getpwuid (file.st_uid);
   gr = getgrgid (file.st_gid);
   cout << "  File: '" << *ch << "'" << endl;
   cout << "  Size: " << ((long long) file.st_size)
    << "\t\tBlocks: " << ((long long) fil.st_blocks)
    << "\t\tIO Block: " << ((long) fil.st_blksize)
    << " \t";

     switch (file.st_mode & S_IFMT) {
     case S_IFBLK:  printf("block device\n");            break;
     case S_IFCHR:  printf("character device\n");        break;
     case S_IFDIR:  printf("directory\n");               break;
     case S_IFIFO:  printf("FIFO/pipe\n");               break;
     case S_IFLNK:  printf("symlink\n");                 break;
     case S_IFREG:  printf("regular file\n");            break;
     case S_IFSOCK: printf("socket\n");                  break;
     default:       printf("unknown?\n");                break;
     }

     cout << "Device: " << hex << file.st_dev << "h/" << dec <<  file.st_dev << "d\t"
          << "Inode: "  << (long)file.st_ino << "\tLinks: " <<  (long) file.st_nlink << endl;
    cout << "Access: ("  <<  oct << "0" <<  (long)(file.st_mode & 07777) << "/" << mode_to_letters (file.st_mode) << ")";
    cout << "\tUid: (" << dec << (long) file.st_uid << "/ " << (pw->pw_name) << ")";
    cout << "\tGid: (" << dec << (long) file.st_gid << "/ " << (gr->gr_name)  << ")" << endl;
    cout << "Access: " << ctime(&file.st_atime);
    cout << "Modify: " << ctime(&file.st_mtime);
    cout << "Change: " << ctime(&file.st_ctime);
    cout << endl;
}//print_into
*/
