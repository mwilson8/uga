
//stat.cpp takes in positive integers & calculates sum, average, sum of squares, & population variance
#include <iomanip>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <string>
#include <sstream>
#include <stdio.h>

using std::cout;
using std::endl;
using std::cin;
using std::pow;
using std::stringstream;
using std::string;
int main()
{

  string input;
  cout << "Enter integers: ";
  std::getline (cin , input);
 
  stringstream stream;
  stream << input;

  
  int count, x;
  double sum, average, squaresum, variance, xsquared;
  count = x = xsquared = 0;
  sum = average = squaresum = variance = xsquared  = 0.0;
  
  for (; stream  >> x; count++)
    {
     
      // cout << "x is: " << x << endl;
      xsquared = pow (x, 2);
      // cout << "x^2 is: " << xsquared << endl;
      squaresum += xsquared;
      sum += x;
      //cout << "sum is: " << sum << endl;
 
    }//end for

  average = sum / count;

  std::setprecision(2);
  std::fixed;
  //cout << "final count: " << count << endl;
  printf( "           Sum: %.1f\n", sum);
  printf( "       Average: %.1f\n", average);
  printf( "Sum of Squares: %.1f\n", squaresum);
  variance =  (squaresum - (pow(average, 2)*count))/count;
  printf( "      Variance: %.2f\n", variance);


  return 0;
}//main
