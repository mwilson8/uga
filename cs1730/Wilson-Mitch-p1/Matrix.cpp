//Matrix.cpp

#include <iostream>
#include <iomanip>
#include <cstdlib>
#include "Matrix.h"

using namespace std;

typedef unsigned int uint;
typedef initializer_list<initializer_list<double>> i_list;



Matrix::Matrix ( uint rows, uint cols ) {

  this->rows = reinterpret_cast<uint>( rows );
  this->cols = reinterpret_cast<uint>(cols);

  array = new double * [rows];

  for (uint i = 0; i < rows; i++) {
    array [i] = new double [cols];
  } // for

  for (uint i = 0; i < rows ; i++) {
    for (uint j = 0; j < cols; j++) {
      array [i][j] = 0;
    } //for
  }//for

} // Matrix constructor 1



Matrix::Matrix ( const i_list & list) {

  this->rows = list.size();
  this->cols = list.begin()->size();

  array = new double * [ rows ];

  for (uint i = 0; i < rows; i++) {
    array [i] = new double [cols];
  }//for

  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j < cols; j++) {
      array[i][j] = *((list.begin()+i)->begin()+j);

    }//for

  }//for

}//Matrix constructor 2



Matrix::Matrix ( const Matrix & m) {
 
  this->rows = reinterpret_cast<uint>(m.numRows());
  this->cols = reinterpret_cast<uint>(m.numCols());
  
  array = new double * [ rows ];

  for (uint i = 0; i < rows; i++) {
    array [i] = new double [cols];
  }//for

  for (uint i = 0; i < rows; i ++) {
    for (uint j = 0; j < cols; j++) {
      array [i][j] = m.at(i,j);
    }//for
  }//for

} // Matrix constructor 3


Matrix::~Matrix() {

  for (uint i = 0; i < rows; i++) {
    delete[] array [i];
  } //for
  delete [] array;

}// Matrix destructor


Matrix Matrix::add( double s) const {
 
  Matrix temp = ( *this );
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j < cols; j++) {
      temp.at(i,j) += s;
    }//for
  }//for

  return temp;
}// add

Matrix Matrix::add (const Matrix & m) const {

  Matrix temp  (*this);
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j < cols; j++) {
      temp.at(i,j) += m.at(i,j);
    }//for
  }//for

  return temp;
}// add


Matrix Matrix::subtract ( double s) const {

  Matrix temp = ( *this );
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j < cols; j++) {
      temp.at(i,j) -= s;
    }//for
  }//for

  return temp;
}//subtract


Matrix Matrix::subtract ( const Matrix & m ) const {

  Matrix temp  (*this);
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j <cols; j++) {
      temp.at(i,j) -= m.at(i,j);
    }//for
  }//for

  return temp;

}//subtract


Matrix Matrix::multiply (double s) const {

  Matrix temp  (*this);
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j <cols; j++) {
      temp.at(i,j) *= s;
    }//for
  }//for

  return temp;

}//multiply


Matrix Matrix::multiply (const Matrix & m) const {

  Matrix temp  (*this);
  
  for(uint i=0; i <  temp.numRows(); i++) {
    for(uint j=0; j < temp.numCols(); j++) {
      for(uint k = 0; k <  this->numRows(); k++) {
	  temp.at(i,j)+=this->at(i,k)*m.at(k,j);
      } // for 3
    }//for 2
  }//for 1

  return temp;
}//multiply


Matrix Matrix::divide (double s) const {


  Matrix temp  (*this);
  for (uint i = 0; i < rows; i++) {
    for (uint j = 0; j <cols; j++) {
      temp.at(i,j) /= s;
    }//for
  }//for

  return temp;

}//divide


Matrix Matrix::t() const {

  Matrix temp (this->numCols(), this->numRows());

  for (uint i = 0; i < temp.numCols(); i++) {
    for (uint j = 0; j < temp.numRows(); j++) {
      temp.at(j,i) = this->at(i,j); 
    }//for
  }//for

  return temp;
} //transpose

const uint Matrix::numRows() const {
  return this->rows;
}//numRows

const uint Matrix::numCols() const {
  return this->cols;
}//numCols


double & Matrix:: at (uint row, uint col) {
  return array [row][col];
}// at


const double & Matrix:: at (uint row, uint col) const {
  return array [row][col];
}//at

Matrix Matrix:: operator+ (const Matrix & m) {
  Matrix temp (*this);
  for (uint i = 0; i < m.numRows(); i++){
    for (uint j = 0; j < m.numCols(); j++){
      temp.at(i,j) = this->at(i,j) + m.at(i,j);
    }//for
  }//for

  return temp;
}//operator +

Matrix Matrix:: operator+ ( double s) {
  Matrix temp (*this);
  for (uint i = 0; i < this->numRows(); i++){
    for (uint j = 0; j < this->numCols(); j++){
      temp.at(i,j) = this->at(i,j) + s;
    }//for
  }//for

  return temp;
}//operator +

Matrix Matrix:: operator- (const Matrix & m) {
  Matrix temp (*this);
  for (uint i = 0; i < m.numRows(); i++){
    for (uint j = 0; j <m.numCols(); j++){
      temp.at(i,j) = this->at(i,j) - m.at(i,j);
    }//for
  }//for

  return temp;
}//operator -

Matrix Matrix:: operator- (const double s) {
  Matrix temp (*this);
  for (uint i = 0; i < this->numRows(); i++){
    for (uint j = 0; j < this->numCols(); j++){
      temp.at(i,j) = this->at(i,j) - s;
    }//for
  }//for

  return temp;
}//operator -

Matrix Matrix:: operator* (const Matrix & m) {

  return this->multiply(m);
}//operator *

Matrix Matrix:: operator* (const double s) {
  
  return this->multiply(s);
}//operator *


Matrix Matrix:: operator/ (const double s) {
  
  return this->divide(s);
}//operator /

double & Matrix::operator() (uint row, uint col) {

  return array[row][col];
}//operator ()

double & Matrix::operator() (uint row, uint col) const {

  return array[row][col];
}//operator ()

Matrix Matrix::operator- () {

  return this->multiply(-1);
}//operator -

void Matrix::operator = (const Matrix & m) {
  for (uint i = 0; i < this->numRows(); i++) {
    for (uint j = 0; j < this->numCols(); j++) {
      this->at(i,j) = m.at(i,j);
    }//for
  }//for
}//operator =

void Matrix::operator = (double s) {

  for (uint i = 0; i < this->numRows(); i++) {
    for (uint j = 0; j <this->numCols(); j++) {
      this->at(i,j) = s;
    }//for
  }//for

}//operator =

ostream& operator << (ostream& os, const Matrix & m) {

  
  for (uint i = 0; i < m.numRows(); i++) {
    os << "[ ";  
  for (uint j = 0; j < m.numCols(); j++) {
      os << m.at(i,j) << ", ";
    }//for
  os << "]" << endl;
  }//for

  return os;
}//operator <<

Matrix operator + (double s, const Matrix & m) {
  Matrix temp (m);
  temp = temp.add(s);
  return temp;
}//operator +

Matrix operator - (double s, const Matrix & m) {
  Matrix temp (m);
  temp = temp.subtract(s);
  return temp;
}//operator -

Matrix operator * (double s, const Matrix & m) {
  Matrix temp (m);
  temp = temp.multiply(s); 
 return temp;
}// operator *

Matrix operator / (double s, const Matrix & m) {
  Matrix temp (m);
  temp = temp.divide(s);  
return temp;
}//operator /
