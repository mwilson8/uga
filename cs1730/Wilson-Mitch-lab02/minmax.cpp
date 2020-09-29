//calculate smallest and largest value

#include <iomanip>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <string>
#include <sstream>

using std::cout;
using std::endl;
using std::cin;
using std::stringstream;
using std::string;

int main()
{

  string input;
  cout << "Enter integers: ";
  std::getline(cin, input);

  stringstream stream;
  stream << input;

  int min, max, x;
  stream >> min;
  max = min;  
  for (;stream >> x;)
    {
      if (x < min)
	min = x;

      if (x > max)
	max = x;
    }//for  

// cout << "Enter integers: " 
   cout << "           Min: " << min << endl;
   cout << "           Max: " << max << endl;

  return 0;
}//main
