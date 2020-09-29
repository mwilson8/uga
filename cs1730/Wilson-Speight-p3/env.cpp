#include <stdio.h>
#include <cstdlib>

int main( const int argc, const char * argv[], char * envp[] ) {
	setvbuf( stdout, NULL, _IONBF, 128 );	
	for( int i = 0; envp[ i ] != nullptr ; i++ ) {
		printf( "%s\n", envp[ i ] );
	}
	return EXIT_SUCCESS;
} // end main
