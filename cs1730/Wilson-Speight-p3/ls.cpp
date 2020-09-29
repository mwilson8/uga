#include <unistd.h>
#include <sys/stat.h>
#include <cstdlib>
#include <cstdio>
#include <cerrno>
#include <string.h>
#include <limits.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <vector>
#include <algorithm>
#include <dirent.h>
#include <fcntl.h>
#include <string>
#include <cstring>

using std::vector;
using std::string;

void mode_to_letters( mode_t mode );
void printDetails( const char * input );
bool isDirectory( char * filename );
void printVector( vector< string > & vec, bool & all_flag, bool & long_flag );
void stuffTheVector( const char * filename, vector< string > & vec, char * command, bool & all_flag );
int totalSize( const char * directory, bool & all_flag );

int main( int argc, char * argv[] ) {
	int opt;
	bool all_flag = false, long_flag = false;
	char * argument;
	string directory;
	vector< string > vec;
	vector< string > vec2;
	setvbuf( stderr, nullptr, _IONBF, 0 );
	setvbuf( stdout, nullptr, _IONBF, 0 );
	while( ( opt = getopt( argc, argv, "al" ) ) != -1 ) {
	switch( opt ) {
	case 'a': all_flag = true; break;
	case 'l': long_flag = true; break;

	} // end switch
	} // end while
	if( optind >= argc ) {
		argument = get_current_dir_name();
		stuffTheVector( argument, vec, argv[ 0 ], all_flag );
		if( long_flag ) {
			fprintf( stdout, "total %d\n", totalSize( argument, all_flag ) );
		}
		printVector( vec, all_flag, long_flag );
		free( argument );
	}
	for( int i = optind; i < argc; i++ ) {
		if( !isDirectory( argv[ i ] ) ) {
			if( access( argv[ i ], F_OK ) != -1 ) {
				vec.push_back( ( string )argv[ i ] );
			} else {
				fprintf( stderr, "%s: cannot access %s: %s\n", argv[ 0 ], argv[ i ], strerror( errno ) );
			}
		} 
	}
	sort( vec.begin(), vec.end() );
	reverse( vec.begin(), vec.end() );
	printVector( vec, all_flag, long_flag );
	for( int i = optind; i < argc; i++ ) {
		if( isDirectory( argv[ i ] ) ) {
			vec.push_back( ( string )argv[ i ] );
		}
	}
	sort( vec.begin(), vec.end() );
	reverse( vec.begin(), vec.end() );
	while( !vec.empty() ) {
		directory = vec.back();
		vec.pop_back();
		if( argc > ( optind + 1 ) ) {
			fprintf( stdout, "%s:\n", directory.c_str() );
		}
		if( long_flag && ( access( directory.c_str(), R_OK ) != -1 ) ) {
			fprintf( stdout, "total %d\n", totalSize( directory.c_str(), all_flag ) );
		}
		stuffTheVector( directory.c_str(), vec2, argv[ 0 ], all_flag );
		printVector( vec2, all_flag, long_flag );
	}
} // end main

bool isDirectory( char * filename ) {
	struct stat file;
	/* Create stat struct */
	if( lstat( filename, &file ) == -1 ) {
		return false;
	}
	/* Check if the file is a link. */
	if( S_ISLNK( file.st_mode ) ) {
		char * linkname = ( char * )malloc( PATH_MAX );
		ssize_t r = readlink( filename, linkname, PATH_MAX );
		linkname[ r ] = '\0';
		/* Check if what the link points to is a directory, recursively. */
		if( isDirectory( linkname ) ) {
			return true;
		}		
	}
	/* Return true or false based if the file is a directory or not. */
	return ( S_ISDIR( file.st_mode ) );
} // end isDirectory

void printDetails( const char * input ) {
	struct stat sb;
	if( lstat( input, &sb ) == -1 );
	char timeBuffer[ 20 ];
	mode_to_letters( sb.st_mode );
	fprintf( stdout, ". %ld %s %s %ld ", ( long )sb.st_nlink, getpwuid( sb.st_uid )->pw_name, getgrgid( sb.st_gid)->gr_name, ( long )sb.st_size );
	strftime( timeBuffer, 19, "%b %e %H:%M", localtime( &sb.st_mtime ) );
	fprintf( stdout, "%s %s", timeBuffer, basename( input ) );
	if( S_ISLNK( sb.st_mode ) ) {
		char * link = ( char * )malloc( PATH_MAX );
		ssize_t r = readlink( input, link, PATH_MAX );
		link[ r ] = '\0';
		fprintf( stdout, " -> %s", link );
		free( link );
	}
	fprintf( stdout, "\n" );
} // end printDetails

void mode_to_letters( mode_t mode ) {
	char buffer[] = "----------";
	char * str = buffer;
	switch( mode & S_IFMT ) {
	case S_IFBLK: str[ 0 ] = 'b'; break;
	case S_IFCHR: str[ 0 ] = 'c'; break;
	case S_IFDIR: str[ 0 ] = 'd'; break;
	case S_IFIFO: str[ 0 ] = 'p'; break;
	case S_IFLNK: str[ 0 ] = 'l'; break;
	case S_IFSOCK: str[ 0 ] = 's'; break;
	}
	if( mode & S_IRUSR ) str[ 1 ] = 'r';    /* 3 bits for user      */
	if( mode & S_IWUSR ) str[ 2 ] = 'w';
	if( S_ISUID & mode && mode & S_IXUSR ) { str[ 3 ] = 's'; }
		else if( S_ISUID & mode ) { str[ 3 ] = 'S'; }
		else if( mode & S_IXUSR ) { str[ 3 ] = 'x'; }
	if( mode & S_IRGRP ) str[ 4 ] = 'r';    /* 3 bits for group     */
	if( mode & S_IWGRP ) str[ 5 ] = 'w';
	if( S_ISGID & mode && mode & S_IXGRP ) { str[ 6 ] = 's'; }
		else if( S_ISGID & mode ) { str[ 6 ] = 'S'; }
		else if( mode & S_IXGRP ) { str[ 6 ] = 'x'; }
	if( mode & S_IROTH ) str[ 7 ] = 'r';    /* 3 bits for other     */
	if( mode & S_IWOTH ) str[ 8 ] = 'w';
	if( S_ISVTX & mode && mode & S_IXOTH ) { str[ 9 ] = 't'; }
		else if( S_ISVTX & mode ) { str[ 9 ] = 'T'; }
		else if( mode & S_IXOTH ) { str[ 9 ] = 'x'; }	
	fprintf( stdout, "%s", str );
} // end mode_to_letters

void printVector( vector< string > & vec, bool & all_flag, bool & long_flag ) {
	string input2;
	while( !vec.empty() ) {
		input2 = vec.back();
		vec.pop_back();
		if( !all_flag ) {
			if( ( basename( input2.c_str() ) )[ 0 ] == '.' ) {
				continue;
			} 
		}
		if( long_flag ) {
			printDetails( input2.c_str() );
		} else {
			fprintf( stdout, "%s\n", basename( input2.c_str() ) );
		}
	}
} // end printVector

void stuffTheVector( const char * filename, vector< string > & vec, char * command, bool & all_flag ) {
	DIR * dp;
	dirent * d;
	char dirname[ PATH_MAX ];
	char foo[ 1 ] = { '/' };
	char * temp = nullptr;
	char * ptr = dirname;
	if( ( dp = opendir( filename ) ) == nullptr ) {
		fprintf( stderr, "%s: cannot open directory `%s': %s\n", command, filename, strerror( errno ) );
	} else {
		while( ( d = readdir( dp ) ) != nullptr ) {
			for( int i = 0; i < PATH_MAX; i++ ) {
				dirname[ i ] = '\0';
			}
			strcpy( dirname, filename );
			temp = d->d_name;
			if( ptr[ strlen( ptr ) - 1 ] != '/' ) {
				unsigned int i = strlen( dirname );
				dirname[ i ] = foo[ 0 ];
			}
			strcat( dirname, temp );
			vec.push_back( ( string )ptr );
		}
		closedir( dp );
		sort( vec.begin(), vec.end() );
		reverse( vec.begin(), vec.end() );
	}
} // end stuffTheVector

int totalSize( const char * directory, bool & all_flag ) {
	struct stat file;
	DIR * dp;
	dirent * d;
	char dirname[ PATH_MAX ];
	char foo[ 1 ] = { '/' };
	char * temp = nullptr;
	char * ptr = dirname;
	int total = 0;
	if( ( dp = opendir( directory ) ) == nullptr ) {
	} else { 
		while( ( d = readdir( dp ) ) != nullptr ) {
			for( int i = 0; i < PATH_MAX; i ++ ) {
				dirname[ i ] = '\0';
			}
			strcpy( dirname, directory );
			temp = d->d_name;
			if( ptr[ strlen( ptr ) - 1 ] != '/' ) {
				unsigned int i = strlen( dirname );
				dirname[ i ] = foo[ 0 ];
			}
			strcat( dirname, temp );
			lstat( ptr, &file );
			if( !all_flag ) {
				if( ( basename( ptr )[ 0 ] == '.' ) ) {
					continue;
				}
			}
			total += ( long )file.st_blocks; 
		}
		closedir( dp );
	}
	return ( total / 2 );
} // end totalSize
