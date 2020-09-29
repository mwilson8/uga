// prints binary, octal, decimal, & hexadecimal of all unsigned shorts
// if divisible by 3 -> print "go", if by 5 -> print "dawgs", else -> print sic'em

#include <iomanip>
#include <cstdlib>
#include <bitset>
#include <bitset>
#include <iostream>
#include <string>
#include <sstream>

using std::stringstream;
using std::cout;
using std::endl;
using std::string;
using std::bitset;
int main()
{
  /**
     this takes the current number and converts to a string stream/ string and allows manipulation of individual indices; this allows for the addition of apostrophes when needed. The outer loop covers every number
  */
  string number;
  for (int i = 0; i <= 65535; i++)
  {
    stringstream temp;
    cout << "0b" ;
    temp << bitset<16>(i);
    number = temp.str();
    
    //binary
    //this inner loop adds " ' " when neccessary
    for (int j = 0; j < number.length(); j++)
      {
	if (j % 4 == 0)
	  cout << "\'" ;

	cout << number.at(j);
      }//for   
      
    cout << " " ;
    //octal
    cout << std::oct << std::showbase << i;
    cout << " ";
    //decimal
    cout << std::dec << std::showbase << i;
    cout << " ";
    //hex-decimal
    cout << std::hex <<std::showbase << i;
    cout << " ";

    if (i % 3 == 0)
      cout << " Go";
    
    if (i % 5 == 0)
      cout << " Dawgs";

    else 
      cout << " Sic 'Em";

    cout << endl;
   }//for

  return 0;
}//main
