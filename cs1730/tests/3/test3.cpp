//From deitel but also trying to remember what mike said in class

#include <iostream>
#include <cstdlib>
#include <string>

using std::cout;
using std::endl;
using std::cin;

int main()
{

  int x = 0;
  
  
  cout << "Enter a number" << endl;
  if (cin >> x)
    {
      cout << "if you're seeing this then the extraction was successful" << endl;
      cout << "x is: " << x << endl;
    }//end if 
 
  else {
    cout << "extraction unsuccessful" << endl;
    cout << "x is: " << x << endl;
  }

  return 0;
}//end main