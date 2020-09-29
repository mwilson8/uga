//read in a float representing degrees celcius & print a float as farenheit.. 2 decimal precision

#include <iomanip>
#include <iostream>
#include <cstdlib>
#include <stdio.h>
using std::cin;
using std::cout;
using std::endl;

int main()
{

  cout << "Enter a temperature in Celcius: ";
  float celcius;
  float farenheit = 0.0;
  cin >> celcius;
  
  farenheit = (celcius * 1.8) + 32;
  printf( "%.2f  degrees Celcius converts to  %.2f degrees Farenheit.\n", celcius, farenheit);

  return 0;
}//main
