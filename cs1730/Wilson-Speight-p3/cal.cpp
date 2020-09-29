#include <cstdlib>
#include <string> 
#include <cmath>

using std::string;
using std::to_string;

int getNumDays(int, int); 
string getMonthName(int); 
string getMonthName(int, int);
void printMonth(int, int, int, int, struct tm *); 
void skipToDay(int);
void printSept1752();
int julian_day( int year, int month, int day );
int gregorian_day( int year, int month, int day );

int main ( int argc, char * argv [] )  {
	time_t theTime = time ( NULL );
	struct tm *aTime = localtime( &theTime );
	int year;
	int currentMonth = 1;
	int month = aTime->tm_mon + 1;   
	int thisYear = aTime-> tm_year + 1900;
	int startOfYear;
	setvbuf( stderr, nullptr, _IONBF, 0 );
	setvbuf( stdout, nullptr, _IONBF, 0 );
	if ( argc == 1 ) {
		fprintf( stdout, "%s\n", getMonthName( month ).c_str() );
    		printMonth( thisYear, month, getNumDays( month, thisYear ), gregorian_day( thisYear, month, 1 ) , aTime );
	} else if ( argc == 2 ) { 
		year = atoi( argv[ 1 ] );
		if (year < 1 || year > 9999) {
			fprintf( stderr, "%s: illegal year value: use 1-9999\n", argv[ 0 ] );
			return EXIT_FAILURE;
		}
		if( year == 1752 ) {
			for(int i = 1; i <=8; i++) {
				fprintf( stdout, "%s\n", getMonthName( i ).c_str() );
				printMonth(year, i, getNumDays(i, year), julian_day(year, i, 1), aTime);
			}
		  	printSept1752();
			for (int i = 10; i <= 12; i ++) {
				fprintf( stdout, "%s\n", getMonthName( i ).c_str() );
				printMonth(year, i, getNumDays(i, year), gregorian_day(year, i, 1), aTime);
			}
			return EXIT_SUCCESS;
		}
		fprintf( stdout, "\t%s\n", argv[ 1 ] );
		if( year < 1752 ) {
			startOfYear = julian_day( year, 1, 1 );
		} else {
			startOfYear = gregorian_day( year, 1, 1 );
		}
		for( currentMonth = 1; currentMonth <= 12; currentMonth++ ) {
			fprintf( stdout, "%s\n", getMonthName( currentMonth ).c_str() );
			printMonth( year, currentMonth, getNumDays( currentMonth, year ), startOfYear, aTime );
			fprintf( stdout, "\n" );
		}
	} else if( argc == 3 ) {
		month = atoi (argv[1] );
		year = atoi (argv[2] );
		if( month < 1 || month > 12 ) {
			fprintf( stderr, "%s: illegal month value: use 1-12\n", argv[ 0 ] );
			return EXIT_FAILURE;
		}
		if (year < 1 || year > 9999) {
			fprintf( stderr, "%s: illegal year value: use 1-9999\n", argv[ 0 ] );
			return EXIT_FAILURE;
		}
		if (year == 1752 && month == 9){
			printSept1752();
			return EXIT_SUCCESS;
		}
		fprintf( stdout, "%s\n", getMonthName( month, year ).c_str() );
		if( year < 1752 ) {
			printMonth( year, month, getNumDays( month, year ), julian_day( year, month, 1 ), aTime );
		} else {
			printMonth( year, month, getNumDays( month, year ), gregorian_day( year, month, 1 ), aTime );
		}
	}
	return EXIT_SUCCESS;
} // end main

/** thanks Mike for these 2 functions */
int julian_day( int year, int month, int day ) {
	int a = floor( ( 14 - month ) / 12 );
	int y = year + 4800 - a;
	int m = month + 12 * a - 3;
	int j = day
		+ floor( ( 153 * m + 2 ) / 5 )
		+ ( 365 * y )
		+ floor( y / 4 )
		- 32083;
	return ( j + 1 ) % 7;
} // end julian_day

int gregorian_day( int y, int m, int d ) {
	struct tm tm;
	char date[ 11 ];
	sprintf( date, "%d-%d-%d", y, m, d );
	strptime( date, "%Y-%m-%d", &tm );
	return tm.tm_wday;
} // end gregorian_day

int getNumDays( int m, int year ){
	switch ( m ) {
	case ( 1 ) : return 31;	break;
	case ( 2 ) : 
		if( year > 1752 ) {
			return ( ( year % 4 == 0 && year % 100 != 0) 
				 || year % 400 == 0 ) ? 29 : 28;	break; 
		} else { 
			return ( year % 4 == 0 ) ? 29 : 28; 	        break;
		}
	case ( 3 ) : return 31;	break;
	case ( 4 ) : return 30;	break;
	case ( 5 ) : return 31;	break;
	case ( 6 ) : return 30;	break;
	case ( 7 ) : return 31;	break;
	case ( 8 ) : return 31;	break;
	case ( 9 ) : return 30;	break;
	case ( 10 ): return 31;	break;
	case ( 11 ): return 30;	break;
	case ( 12 ): return 31;	break;
	}//switch 
	return -1;
} // end getNumDays

string getMonthName( int m, int year ) {
	string s ;
	switch ( m ) {
	case 1:  s = "    January " ;	break;
	case 2:  s = "    February ";	break;
	case 3:  s = "    March "   ;	break;
	case 4:  s = "     April "  ;	break;
	case 5:  s = "      May "   ;	break;
	case 6:  s = "     June "   ;	break;
	case 7:  s = "      July "  ;	break;
	case 8:  s = "     August " ;	break;
	case 9:  s = "   September ";	break;
	case 10: s = "    October " ;	break;
	case 11: s = "    November ";	break;
	case 12: s = "    December ";	break;
	}//switch
	s += to_string(year);
	s += "\n";
	s += "Su Mo Tu We Th Fr Sa";
	return s; 
} // end getMonthName

string getMonthName( int m ) {
	string s ;
	switch ( m ) {
	case 1:	s = "      January       ";	break;
	case 2:	s = "      February      ";	break;
	case 3: s = "        March       ";	break;
	case 4:	s = "        April       ";	break;
	case 5:	s = "         May        ";	break;
	case 6:	s = "        June        ";	break;
	case 7:	s = "        July        ";	break;
	case 8: s = "       August       ";	break;
	case 9:	s = "      September     ";	break;
	case 10:s = "       October      ";	break;
	case 11:s = "      November      ";	break;
	case 12:s = "      December      ";	break;
	}//switch
	s += "\n";
	s += "Su Mo Tu We Th Fr Sa";
   	return s;
} // end getMonthName

void skip( int i ) {
	while ( i > 0 ) {
		fprintf( stdout, " " );
		i--;
	}
} // end skip

void printMonth( int currYear, int currentMonth, int numDays, int weekDay, struct tm * time ) {
	int day = 1;
 	skipToDay(weekDay);
	while (day <= numDays) {
		if (day == time->tm_mday && (currentMonth == time->tm_mon + 1) && (currYear == time->tm_year + 1900) ){
			fprintf( stdout, "\033[30;47m%2d\033[0m ", day );
		} else {
			fprintf( stdout, "%2d ", day );
		}
		if (weekDay == 6){
			fprintf( stdout, "\n" );
      			weekDay = 0;
		} else {
			weekDay++;;
		}
    		day ++; 
  	}
	fprintf( stdout, "\n" );
} // end printMonth

void skipToDay( int d ){
	return skip( 3 * d );
} // end skipToDay

void printSept1752() {
	fprintf( stdout, "%s\n", getMonthName( 9, 1752 ).c_str() );
		  	//Su Mo Tu We Th Fr Sa
	fprintf( stdout, "       1  2 14 15 16\n" );
	fprintf( stdout, "17 18 19 20 21 22 23\n" );
	fprintf( stdout, "24 25 26 27 28 29 30\n\n\n\n" );
} // end printSept1752 <<< YES THIS IS HARDCODED LOL
