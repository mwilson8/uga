#include <unistd.h>
#include <string.h>
#include <cerrno>
#include <cstdlib>
#include <cstdio>

int main( const int argc, char * argv[] ) {
	bool symLink = false;
	int opt;
	while( ( opt = getopt( argc, argv, "s" ) ) != -1 ) {
	switch( opt ) {
	case 's':
		symLink = true;
		break;
	default:
		return EXIT_FAILURE;
	} // end switch
	} // end while
	if( ( argc - optind) <= 0 ) {
		fprintf( stderr, "%s: missing file operand\n", argv[ 0 ] );
		return EXIT_FAILURE;
	}	
	if( symLink ) {
		if( argc <= 4 ) { 
			if( ( symlink( argv[ optind ], argv[ argc - 1 ] ) ) == -1 ) {
				fprintf( stderr, "%s: creating symbolic link `%s': %s\n", argv[ 0 ], argv[ argc - 1 ], strerror( errno ) );
			}
		} else {
			fprintf( stderr, "%s: too many operands\n", argv[ 0 ] );
			return EXIT_FAILURE;
		}
	} else {
		if( argc <= 3 ) {
			if( access( argv[ optind ], F_OK ) != -1 ) {
				if( access( argv[ argc - 1 ], F_OK ) == -1 ) {
					if( link( argv[ optind ], argv[ argc - 1 ] ) == -1 ) {
						fprintf( stderr, "%s: creating hard link `%s': %s\n", argv[ 0 ], argv[ argc - 1 ], strerror( errno ) );
					}
				} else {
					fprintf( stderr, "%s: creating hard link `%s': %s\n", argv[ 0 ], argv[ argc - 1 ], strerror( errno ) );
				}
			} else {
				fprintf( stderr, "%s: accessing `%s': %s\n", argv[ 0 ], argv[ optind ], strerror( errno ) );
			}
		} else {
			fprintf( stderr, "%s: too many operands\n", argv[ 0 ] );
			return EXIT_FAILURE;
		}
	}
	return ( errno == 0 ) ? EXIT_SUCCESS : EXIT_FAILURE;
} // end main
