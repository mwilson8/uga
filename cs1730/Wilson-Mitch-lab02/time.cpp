// calculate the number of hours, mins, & seconds that are in an int of seconds

#include <iostream>
#include <cstdlib>

using std::cout;
using std::cin;
using std::endl;

int main ()
{

  cout << "Enter in the number of seconds as an integer: ";
  int original, input, hours, minutes, seconds;
  cin >> input;
  original = input;

  hours = input / 3600;
  input -= hours * 3600;
  minutes = input / 60;
  input -= minutes * 60;
  seconds = input;

  cout << original << " seconds is equivalent to ";
  cout << hours << " hours ";
  cout << minutes << " minutes ";
  cout << seconds << " seconds." << endl;

  return 0;
}//main
