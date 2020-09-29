#include <unistd.h>
#include <cstdlib>
#include <cstdio>
#include <string.h>
#include <cerrno>
#include <string>
#include <sys/types.h>
#include <sys/stat.h>
#include <limits.h>

/*
 * The validMode function takes in the char * mode and a boolean.
 * @param mode The char * representation of the mode passed by the user for the -m flag.
 * @param bchmod The boolean used to set the mode of the directory if 4 digits of the mode are passed
 * 	to handle the sticky bits.
 * @return boolean
 */
bool validMode( char * mode, bool & bchmod );

int main( int argc, char * argv[] ) {
	int opt;
	mode_t mode = 0777;
	bool recursion = false, bchmod = false, dontcare = true;
	char * ptr;
	char bazBuffer[ PATH_MAX ];
	char * temp1 = bazBuffer;
	/* Set stderr to unbuffered. */
	setvbuf( stderr, nullptr, _IONBF, 0 );
	/* While loop to parse the flags/options passed. */
	while( ( opt = getopt( argc, argv, "pm:" ) ) != -1 ) {
	switch( opt ) {
	case 'm': { /* If the -m flag/option is passed then check if a mode is entered after the -m and set the mode to it. */
		char * endp = NULL;
		if( !optarg || ( strtol( optarg, &endp, 0 ), ( endp && * endp ) ) ) {
			fprintf( stderr, "%s: invalid mode `%s'\n" , argv[ 0 ], optarg?optarg:"" );
			return EXIT_FAILURE;
		}
		mode = strtol( optarg, nullptr, 8 ); 
		ptr = optarg;
		dontcare = false;
	}
		break;
	case 'p': /* If the -p flag/option is passed then turn on recursion to make the directories recursively. */
		recursion = true;
		break;
	default: /* If a flag/option is passed that isn't valid then print a  */
		fprintf( stderr, "Usage: %s [-m OCTAL_MODE] [-p] directory/...\n", argv[ 0 ] );
		return EXIT_FAILURE;
	} // end switch
	} // end while
	if( optind >= argc ) {
		fprintf( stderr, "%s: missing operand\n", argv[ 0 ] );
		return EXIT_FAILURE;
	}
	/* For loop to iterate through the rest of the arguments */
	for( int i = optind; i < argc; i++ ) {
		/* dontcare is set to false if -m flag/options is set, so that we can then check the input mode to validity */
		if( dontcare || validMode( ptr, bchmod ) ) {
			/* If -p flag/option is passed then we will try to create the all the directories that are passed as arguments. */
			if( recursion ) {
				/* For loop to parse through the argument to make the directories. */
				for( unsigned int j = 0; j < strlen( argv[ i ] ); j++ ) {
					/* if the character is a / then make a directory of the substring */
					if( argv[ i ][ j ] == '/' ) {
						strncpy( temp1, argv[ i ], j );
						/* If we are at the end then don't make the directory because we save that one to be made with the mode that is passed. */
						if( j != ( strlen( argv[ i ] ) - 1 ) ) {
							/* Make directory, but don't print error if it fails because we don't care. */
							if( mkdir( temp1, 0777 ) == -1 );
						}
					}
				}
			}
			/* Turn off the user's default umask if -m flag/option is passed. */
			if( dontcare == false ) {
				umask( 0 );
			}
			/* Now it is time to make the actual end game directory and set the mode for it. */
			if( mkdir( argv[ i ], mode ) == -1 ) {
				fprintf( stderr, "%s: cannot create directory `%s': %s\n", argv[ 0 ], argv[ i ], strerror( errno ) );
			}
			/* If the octal mode that is passed is of length 4 or more then use chmod because it handles sticky bits for some reason and mkdir doesn't */
			if( bchmod ) {
				if( chmod( argv[ i ], mode ) == -1 );
			}
		} else { /* Mode is invalid, so print an error and return EXIT_FAILURE */
			fprintf( stderr, "%s: invalid mode `%s'\n", argv[ 0 ], ptr );
			return EXIT_FAILURE;
		}
	}
	/* return exit status based off errno */
	return ( errno == 0 ) ? EXIT_SUCCESS : EXIT_FAILURE;
} // end main

bool validMode( char * mode, bool & bchmod ) {
	int n;
	char foo[ 1 ];
	char * temp = foo;
	/* For loop to iterate through each character in the char * */
	for( int i = ( strlen( mode ) - 1 ); i >= 0; i-- ) {
		foo[ 0 ] = mode[ i ];
		n = std::stoi( temp );
		/* If the integer value of the character is not 0 - 7 then return false. */
		if( i >= ( int )( strlen( mode ) - 4 ) ) {
			if( n < 0 || n > 7 ) {
				return false;
			}
		} else { /* Allow for user to input more than 4 digits, but they must be 0 or it is invalid mode */
			if( n != 0 ) {
				return false;
			}
		}
	}
	/* If the input mode is 4 or more in length then turn on bchmod, so chmod will be call to set the sticky bits. */
	if( strlen( mode ) >= 4 ) {
		bchmod = true;
	}
	return true;
} // end validMode
