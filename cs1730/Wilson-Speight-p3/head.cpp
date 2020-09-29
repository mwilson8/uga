#include <cstdlib>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <cerrno>
#include <string.h>

void print( const int & file_descriptor, const int & line, int count );

int main( const int argc, char * const argv[] ) {
	int opt, fd;
	int lines = 10;
	setvbuf( stdout, NULL, _IONBF, 0 );
	while( ( opt = getopt( argc, argv, "n:" ) ) != -1 ) {
	switch( opt ) {
	case 'n': {
		char * endp = NULL;
		if( !optarg || ( strtol( optarg, &endp, 0 ), ( endp && * endp ) ) ) {
			fprintf( stderr, "invalid n option %s - expecting a number\n" , optarg?optarg:"" );
			return EXIT_FAILURE;
		}
		lines = atoi( optarg );
		break;
		}
	default:
		return EXIT_FAILURE;
	} // end switch
	} // end while
	if( optind == argc ) {
		print( STDIN_FILENO, lines, 1 );
	} else {
		for( int i = optind; i < argc; i++ ) {
			if( strcmp( argv[ i ], "-" ) == 0 ) {
				if( argc > ( optind + 1 ) )
					printf( "==> standard input <==\n" );
				print( STDIN_FILENO, lines, 1 );
				if( argc > ( optind + 1 ) )
					printf( "\n" );
			} else if( ( fd = open( argv[ i ], O_RDONLY ) ) != -1 ) {
				if( argc > ( optind + 1 ) )
					printf( "==> %s <==\n", argv[ i ] );
				print( fd, lines, 0 );
				close( fd );
				if( argc > ( optind + 1 ) )
					printf( "\n" );
			} else {
				fprintf( stderr, "%s: cannot open `%s' for reading: %s\n", argv[ 0 ], argv[ i ], strerror( errno ) );
			}
		}
	}
	return ( errno == 0 ) ? EXIT_SUCCESS: EXIT_FAILURE;
} // end main

void print( const int & file_descriptor, const int & lines, int count ) {
	int n;
	char buffer[ 1 ];
	while( ( ( n = read( file_descriptor, buffer, 1 ) > 0 ) ) && ( count < lines ) ) {
		if( buffer[ 0 ] == '\n' )
			count++;
		if( write( STDOUT_FILENO, buffer, n ) != n );
	}
} // end print
