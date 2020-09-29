#include <cstdlib>
#include <unistd.h>
#include <cerrno>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <limits.h>

void my_print( const int & fd, const int & lines, bool & fifo, bool & numbers );

bool isDirectory( char * filename );

int main( const int argc, char * const argv[] ) {
	int opt, fd, n, lines = 10;
	char buffer[ 1 ];
	bool fifo = false, numbers = true;
	char temp[] = { "temp.txt" };
	char * tempFile = temp;
	/* Set stderr to unbuffered. */
	setvbuf( stderr, nullptr, _IONBF, 0 );
	/* While loop to parse the flags/options. */
	while( ( opt = getopt( argc, argv, "fn:c:" ) ) != -1 ) {
	switch( opt ) {
	case 'n': { /* If the -n flag/option is passed then check if the user inputs a number after and set that to lines */
		//numbers = true;
		char * endp = nullptr;
		if( !optarg || ( strtol( optarg, &endp, 0 ), ( endp && * endp ) ) ) {
			fprintf( stderr, "%s: %s: invalid number of lines\n", argv[ 0 ], optarg?optarg:"" );
			return EXIT_FAILURE;
		}
		lines = abs( atoi( optarg ) );
		break;
		}
	case 'c': { /* If the -c flag/option is passed then check if the user inputs a number after and set that to lines */
		numbers = false;
		char * endp = nullptr;
		if( !optarg || ( strtol( optarg, &endp, 0 ), ( endp && * endp ) ) ) {
			fprintf( stderr, "%s: %s: invalid number of bytes\n", argv[ 0 ], optarg?optarg:"" );
			return EXIT_FAILURE;
		}
		lines = abs( atoi( optarg ) );
		}
		break;
	case 'f': /* If the -f flag/option is passed then turn on fifo */
		fifo = true;
		break;
	default:
		fprintf( stderr, "Usage: %s [-n number | -c number] [-f] [FILE]\n", argv[ 0 ] );
		return EXIT_FAILURE;
	} // end switch
	} // end while
	/* If no file operand OR the input is "-" is entered then read from STDIN_FILENO to print later. */
	if( ( optind == argc ) || ( strcmp( argv[ optind ], "-" ) == 0 ) ) {
		fd = open( tempFile, O_RDWR | O_CREAT, 0666 );
		while( ( n = read( STDIN_FILENO, buffer, 1 ) ) > 0 ) {
			if( write( fd, buffer, n ) != n );
		}
		my_print( fd, lines, fifo, numbers );
		close( fd );
		unlink( tempFile );
	} else if( isDirectory( argv[ optind ] ) == false ) { /* Check if the argument is a directory or a link to a directory */
		/* Try to open the file for read permission or print an error if one occurs. */
		if( ( fd = open( argv[ optind ], O_RDONLY ) ) != -1 ) {
			/* If fifo is off, then we need to test if the file operand is a FIFO or a link to a FIFO. */
			if( fifo == false) {
				struct stat file;
				stat( argv[ optind ], &file );
				if( ( file.st_mode & S_IFMT ) == S_IFIFO ){
					fifo = true;
				}
			}
			my_print( fd, lines, fifo, numbers ); 
			close( fd );
		} else { /* If the file operand doesn't exists or the user doesn't have read permission then print an error. */
			fprintf( stderr, "%s: cannot open `%s' for reading: %s\n", argv[ 0 ], argv[ optind ], strerror( errno ) );
		}
	} else { /* If the file operand is a directory or a link to the directory then print an error. */
		fprintf( stderr, "%s: error reading `%s': Is a directory\n", argv[ 0 ], argv[ optind ] );
	}
	return ( errno == 0 ) ? EXIT_SUCCESS: EXIT_FAILURE;
} // end main

void my_print( const int & fd, const int & lines, bool & fifo, bool & numbers ) {
	char buffer[ 1 ];
	int n;
	if( numbers ) { /* If the -c flag/option is passed then print based off the lines. */
		off_t size = lseek( fd, -1, SEEK_END );
		int count = 0;
		while( ( ( n = read( fd, buffer, 1 ) ) > 0 ) && ( count < ( lines + 1 ) ) ) {
			if( buffer[ 0 ] == '\n' ) {
				count++;
			}
			size--;
			lseek( fd, size, SEEK_SET );
		}
		lseek( fd, ( 2 + size ), SEEK_SET );
		while( ( ( n = read( fd, buffer, 1 ) ) > 0 ) || fifo ) {
			if( write( STDOUT_FILENO, buffer, n ) != n );
		}
	} else { /* Else print based off characters */
		if( lseek( fd, -lines, SEEK_END ) == -1 ) {
			lseek( fd, 0, SEEK_SET );
		}
		while( ( ( n = read( fd, buffer, 1 ) ) > 0 || fifo ) ) {
			if( write( STDOUT_FILENO, buffer, n ) != n );
		}
	}
} // end print

bool isDirectory( char * filename ) {
	struct stat file;
	/* Try to create a stat struct from the file operand. */
	if( lstat( filename, &file ) != -1 ) {
		/* If the file is a directory then return true. */
		if( S_ISDIR( file.st_mode ) ) {
			return true;
		}
		/* If the file is a link, then we need to check if the link points to a directory. */
		if( S_ISLNK( file.st_mode ) ) {
			char * link =( char * )malloc( PATH_MAX );
			ssize_t r = readlink( filename, link, PATH_MAX );
			link[ r ] = '\0';
			/* Recursively call the isDirectory function to check if the thing the link points to is a directory. */
			if( isDirectory( link ) ) {
				free( link );
				return true;
			}
			/* NO MEMORY LEAKS! */
			free( link );
		}
	}
	/* If we make it here, then it isn't a directory or a link to a directory. */
	return false;
} // end isDirectory
