#include <cstdlib>
#include <stdio.h>
#include <unistd.h>

int main( const int argc, const char * argv[] ) {

  setvbuf( stdout, NULL, _IONBF, 0 );

	char * cwd = get_current_dir_name();

	fprintf( stdout, "%s\n", cwd );

	free( cwd );

	return EXIT_SUCCESS;
} // end main
