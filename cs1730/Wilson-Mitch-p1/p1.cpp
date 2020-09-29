#include <iostream>
#include <cstdlib>
#include "Matrix.h"

using std::cout;
using std::endl;

typedef std::initializer_list<initializer_list<double>> i_list;

int main () {

  Matrix a(2, 2);
  cout << "Empty 2x2 Matrix:" << endl << a << endl;
  a.at(0, 0) = 1; // [ 1, 2 ]
  a.at(0, 1) = 2; // [ 1, 3 ]
  a.at(1, 0) = 1;
  a.at(1, 1) = 3;
  cout << "Matrix a filled:" << endl << a << endl;

  i_list list = {{1,2},{3,4}}; 
  Matrix b(list);
  
  cout << "Matrix made with list 1234" << endl << b << endl;

  Matrix c (b);
  cout << "Matrix c is copy of b:" << endl << c << endl;

  a.at(0,0) = 5;
  a.at(0,1) = 6;
  a(1,0) = 7;
  a(1,1) = 8;

  cout << "Matrix a values change with at() & at overload to 5678" << endl << a << endl;

  Matrix d = a.add(5);

  cout << "5 added to above" << endl << d << endl;

  d = d.subtract (3);

  cout << "3 subtracted from above" << endl << d << endl;

  Matrix e (2,2);
 e =  e.add (10);
 e =  e.divide (2);

  cout << "matrix of 0, with 10 added, and divided by 2" << endl << e << endl;

  i_list list2 = {{1,1},{2,2},{3,3}};
  Matrix f (list2);

  cout << "before transpose" << endl << f << endl;
  Matrix g = f.t();  
  cout << "after transpose" << endl << g << endl;



}//main
