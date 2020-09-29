#include <unistd.h>
#include <cstdlib>
#include <fcntl.h>
#include <cerrno>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <limits.h>
#include <cstdio>
#include <cwchar>
#include <locale>

/*
 * The my_print function counts the number of bytes, words, characters, and lines then 
 * prints them if they need to be printed based off passed flags/options.
 * @param filename The char * to the file we are using.
 */
void my_print( char * filename, bool & bchars, bool & blines, bool & bwords, bool & all, bool & bbytes, int & totalb, int & totalw, int & totall, int & total, char * command );
/*
 * The isDirectory function checks if the filename is a directory or
 * a link pointing to a directory.
 * @param original The original file we are checking.
 * @param filename The filename that may change if the function is recursively called.
 * @param command The name of the program being run.
 */
bool isDirectory( char * original, char * filename, char * command );

int main ( int argc, char * argv[] ) {
	int opt, totalb = 0, totalw = 0, totall = 0, totalc = 0;
	bool bchars = false, blines = false, bwords = false, all = true, bbytes = false;
	char temp[] = { "temp.txt" };
	char * tempFile = temp;
	/* Set stderr and stdout to unbuffered. */
	setvbuf( stderr, nullptr, _IONBF, 0 );
	setvbuf( stdout, nullptr, _IONBF, 0 );
	/* Parse through argv to get the flags/options passed by the user. */
	while( ( opt = getopt( argc, argv, "clmw" ) ) != -1 ) {
	switch( opt ) {
	case 'c': /* Only want to print bytes. */
		bbytes = true;
		bchars = false;
		all = false;
		break;
	case 'l': /* Only want to print lines. */
		blines = true;
		all = false;
		break;
	case 'm': /* Only want to print characters. */
		bchars = true;
		bbytes = false;
		all = false;
		break;
	case 'w': /* Only want to print words. */
		bwords = true;
		all = false;
		break;
	default: /* If getopt doesn't recognize the flag/option passed then print and error and exit. */
		fprintf( stderr, "Usage: %s [-c | -m] [-lw] [file...]\n", argv[ 0 ] );
		return EXIT_FAILURE;
	} // end switch
	} // end while
	/* If no file is passed then read from standard input then count information when done. */
	if( optind >= argc ) {
		int n, fd;
		char str[ 1 ];
		if( ( fd = open( tempFile, O_RDWR | O_CREAT, 0666 ) ) == -1 );
		while( ( n = read( STDIN_FILENO, str, 1 ) ) > 0 ) {
			if( write( fd, str, n ) != -1 );
		}
		my_print( tempFile, bchars, blines, bwords, all, bbytes, totalb, totalw, totall, totalc, argv[ 0 ] );
		fprintf( stdout, "\n" ); 
		close( fd );
		unlink( tempFile );	
	}
	for( int i = optind; i < argc; i++ ) {
		/* If argument is -, then read from standard input then count information when done. */
		if( strcmp( argv[ i ], "-" ) == 0 ) {
			int n, fd;
			char str[ 1 ];
			if( ( fd = open( tempFile, O_RDWR | O_CREAT, 0666 ) ) == -1 );
			while( ( n = read( STDIN_FILENO, str, 1 ) ) > 0 ) {
				if( write( fd, str, n ) != -1 );
			}
			my_print( tempFile, bchars, blines, bwords, all, bbytes, totalb, totalw, totall, totalc, argv[ 0 ] );
			fprintf( stdout, "\t-\n" );
			close( fd );
			unlink( tempFile );
		} else if( access( argv[ i ], F_OK ) != -1 ) { /* Check if the file exists and count information if it doesn. */
			my_print( argv[ i ], bchars, blines, bwords, all, bbytes, totalb, totalw, totall, totalc, argv[ 0 ] );
			fprintf( stdout, "\t%s\n", argv[ i ] );
		} else { /* Print an error if all else has failed. */
			fprintf( stderr, "%s: %s: %s\n", argv[ 0 ], argv[ i ], strerror( errno ) );
		}
	}
	/* Print the total of all the information if more than one file is passed. */
	if( argc > ( optind + 1 ) ) {
		if( blines || all )
			fprintf( stdout, "\t%d", totall );
		if( bwords || all )
			fprintf( stdout, "\t%d", totalw );
		if( bbytes || all )
			fprintf( stdout, "\t%d", totalb );
		if( bchars )
			fprintf( stdout, "\t%d", totalc );
		fprintf( stdout, "\ttotal\n" );
	}
	/* return exit status based off errno. */
	return ( errno == 0 ) ? EXIT_SUCCESS : EXIT_FAILURE;
} // end main

void my_print( char * filename, bool & bchars, bool & blines, bool & bwords, bool & all, bool & bbytes, int & totalb, int & totalw, int & totall, int & totalc, char * command ) {
	int n = 0, fd, bytes = 0, lines = 0, words = 0, chars = 0;
	bool countWord = true;
	char str[ 1 ];
	char buffer[ 1024 ];
	wchar_t wc;
	int len;
	char * end;
	char * str1;
	std::mbstate_t state;
	std::setlocale( LC_ALL, "en_US.utf8" );
	/* Check if the filename is a directory or a link to a directory. */
	if( isDirectory( filename, filename, command ) == false ) {
		if( ( fd = open( filename, O_RDONLY ) ) == -1 ) {
			fprintf( stderr, "%s: %s: %s\n", command, filename, strerror( errno ) );
		}
		while( ( n = read( fd, str, 1 ) ) > 0 ) {
			bytes += n;
			if( isspace( str[ 0 ] ) == 0 && countWord == false ) {
				words++;
				countWord = true;
			}
			if( isspace( str[ 0 ] ) != 0 ) {
				countWord = false;
			}
			if( str[ 0 ] == '\n' ) {
				lines++;
			}
		}
		if( words > 0 ) {
			words++;
		}
		lseek( fd, 0, SEEK_SET );
		while ( read( fd, buffer, 1024 ) > 0 ) {
			str1 = buffer;
			state = std::mbstate_t();
			end = str1 + strlen( str1 );
			while( ( len = std::mbrtowc( &wc, str1, end - str1, &state ) ) > 0 ) {
				str1 += len;
				chars++;
				if( str1 > end ) break;
			}
		}
	}
	/* Print the information from the individual file if we need to based off flags/options. */
	if( blines || all ) {
		fprintf( stdout, "\t%d", lines );
		totall += lines;
	}
	if( bwords || all ) {
		fprintf( stdout, "\t%d", words );
		totalw += words;
	}
	if( bbytes || all ) {
		fprintf( stdout, "\t%d", bytes );
		totalb += bytes;
	}
	if( bchars ) {
		fprintf( stdout, "\t%d", chars );
		totalc += chars;
	}
} // end my_print

bool isDirectory( char * original, char * filename, char * command ) {
	struct stat file;
	/* Create a stat struct */
	if( lstat( filename, &file ) != -1 ) {
		/* If the file is a directory then print so and return true */
		if( S_ISDIR( file.st_mode ) ) {
			fprintf( stderr, "%s: %s: Is a directory\n", command, original );
			return true;
		}
		/* If the file is a link then check if what it is pointing to is a directory recursively. */
		if( S_ISLNK( file.st_mode ) ) {
			char * link =( char * )malloc( PATH_MAX );
			ssize_t r = readlink( filename, link, PATH_MAX );
			link[ r ] = '\0';
			if( isDirectory( original, link, command ) ) {
				free( link );
				return true;
			}
			/* NO MEMORY LEAKS! */
			free( link );
		}
	}
	/* Return false because the file is not a directory or a link to a directory. */
	return false;
} // end isDirectory
