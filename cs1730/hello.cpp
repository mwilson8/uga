
#include <cstdlib> //angle brackets tell us is a system library
#include <iostream>

using std::cout;
using std::endl;

// using namespace std; //same as above, but recommended doing individually 

int main(){
  cout << "Hello World" << endl;
  return EXIT_SUCCESS;
} //main

/*  after completion of writing the class, we will:
    compile: g++ -c fileName
    compilation will convert a .cpp file to a .o file

    link (into an executable file): g++ -o [outputFileName] [.oFile(s)]
    creates an executable file with name outputFileName

    to execute, provide the absolute path to the command line OR
    if in the present directory: ./outputFileName (since it's executable, it's 
    name is a valid command
*/

    
