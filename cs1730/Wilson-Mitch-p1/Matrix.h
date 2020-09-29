//Matrix.h
#include <initializer_list>
#include <iostream>
#include <cstdlib>

#ifndef MATRIX_H
#define MATRIX_H

using namespace std;

typedef unsigned int uint;
typedef initializer_list<initializer_list<double>> i_list;
class Matrix {

 public:

  Matrix(uint rows, uint cols);                 // constructor (all elements initialized to 0)
  Matrix(const i_list & list);                  // constructor (using initializer list)
  Matrix(const Matrix & m);                     // copy constructor
 ~Matrix();                                    // destructor

  Matrix add(double s) const;                   // add scalar to this matrix
  Matrix add(const Matrix & m) const;           // add this matrix and another matrix

  Matrix subtract(double s) const;              // subtract scalar from this matrix   
  Matrix subtract(const Matrix & m) const;      // subtract another matrix from this matrix

  Matrix multiply(double s) const;              // multiply this matrix by a scaler
  Matrix multiply(const Matrix & m) const;      // multiply this matrix by another matrix

  Matrix divide(double s) const;                // divide this matrix by a scaler
  Matrix t() const;                             // transpose of this matrix

  const uint numRows() const;                   // returns the number of rows
  const uint numCols() const;                   // returns the number of cols

  double & at(uint row, uint col);              // get/set element at row,col
  const double & at (uint row, uint col) const; // get element at row,col (when using a const object)

  Matrix operator+ (const Matrix & m);
  Matrix operator+ (const double s);
  Matrix operator- (const Matrix & m);
  Matrix operator- (const double s);
  Matrix operator* (const Matrix & m);
  Matrix operator* (const double s);
  Matrix operator/ (const double s);
  double & operator() (uint row, uint col);
  double & operator() (uint row, uint col) const;
  Matrix operator- ();
  void operator= (const Matrix & m);
  void operator= (const double s);

 private:
  uint rows, cols;
  double ** array;



}; // Matrix

ostream& operator << (ostream& os, const Matrix & m);
Matrix operator+ (double s, const Matrix& m);
Matrix operator- (double s, const Matrix& m);
Matrix operator* (double s, const Matrix& m);
Matrix operator/ (double s, const Matrix& m);
#endif
