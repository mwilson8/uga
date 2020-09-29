#include <cstdlib>
#include <string>
#include <iostream>
#include <cstring>
#include <sstream>
#include <vector>
#include <string.h>
#include <algorithm>


using namespace std;

string trim(string& str);

int main (){

  int pipes;
  string inStream  = "STDIN_FILENO";
  string outStream = "STDOUT_FILENO";
  string errStream = "STDERR_FILENO";
  string input,extract;
  bool streamAltered;
  cout << "repl$ ";
  getline (cin, input);
  stringstream stream(input);
  vector <string> commands;
  vector <string> temp;
  vector <vector <string> > vec;

  /*the command line is in the stream strem "stream"*/
  while (stream >> extract){

    //    cout << "extracted: " << extract << endl;

    /* if "exit" is the first command (theoretically nothing else would follow)*/
    if (commands.empty() && (extract.compare("exit") == 0))
      return EXIT_SUCCESS;
    
    else
    commands.push_back(extract);
  }//while

  /*parse through the "commands" vector*/
  for (unsigned int i = 0; i < commands.size(); i++){
    streamAltered = false;

    //if the extracted has quotes in it
    if (commands.at(i).find("\"") != string::npos){
      string quotes = "";

      for (unsigned int j = i; ; i++, j++){

	quotes = quotes +  commands.at(i) + " ";

	
	/*push elements into 'quotes' until another quote is found & that quote is not preceded with a \ */
	if (commands.at(i).find("\"") != commands.at(i).find("\\") + 1){
	
	  /*trim & remove the leading & trailing quotes*/
	  quotes = trim(quotes);
	  quotes = quotes.substr(1, quotes.length()-2);
	  
	  /*remove the escape characters*/
	  quotes.erase(remove(quotes.begin(), quotes.end(), '\\'), quotes.end());
	  /*add quotes to temp, don't push temp to vec or weird spacing error will occur*/
	  temp.push_back(quotes);
	  break;
	}//if
      }//for

    }//if




    /*if we find a pipe, then push the temp vector onto vec */
else if ((commands.at(i)).compare("|") == 0){
      vec.push_back(temp);
      temp.clear();
      pipes++;
      //cout << "pipe encountered, pushed onto vec" << endl;
    }//if

    /* the following all do roughly the same, if a stream is redirected then change the bool flag
       & skip whatever follows, assigning it to the apprpropriate stream
    */
    else if ((commands.at(i)).compare(">") == 0) { 
      streamAltered = true;
      outStream = (commands.at(i+1) + " (truncate)"); 
      i++;
      //logically, don't think this would ever happen but its just as easy to leave the code in
      if (!streamAltered){
      vec.push_back(temp);
      temp.clear();
      //      cout << " out stream altered (truncate), temp pushed" << endl;
      }//if
     
      
    }

    else if ((commands.at(i)).compare(">>") == 0) { 
      streamAltered = true;
      outStream = (commands.at(i+1) + " (append)"); 
      i++;
      if (!streamAltered){
      vec.push_back(temp);
      temp.clear();
      //cout << " out stream altered (append), temp pushed" << endl;
      }//if

      
    }

  else if ((commands.at(i)).compare("<") == 0) {
    streamAltered = true;
    inStream = commands.at(i+1); 
    i++;
    if (!streamAltered){
    vec.push_back(temp);
    temp.clear();
    //cout << " in stream altered, temp pushed" << endl;
    }//if

   
  }

 else if ((commands.at(i)).compare("e>>") == 0) {
   streamAltered = true;
   errStream = (commands.at(i+1) + " (append)");
   i++;
   if (!streamAltered){
   vec.push_back(temp);
   temp.clear();
   //cout << " error stream altered (append), temp pushed" << endl;
   }//if

 
 }

    else if ((commands.at(i)).compare("e>") == 0) { 
      streamAltered = true;
      errStream = (commands.at(i+1) + " (truncate)"); 
      i++;
      if (!streamAltered){
      vec.push_back(temp);
      temp.clear();
      // cout << "error stream altered (truncate), temp pushed" << endl;
      }//if


    }
    
    else {
      if (!streamAltered){
      temp.push_back(commands.at(i));
      // cout << commands.at(i) << " pushed into temp" << endl;
      }//if
      }//else

    if (i+1 == commands.size()){
      vec.push_back(temp);
      //cout << "i + 1 is equal to commands.size, pushed temp" << endl;
    }//if
  }//for

  cout << endl;
  cout << "Job STDIN  = " << inStream << endl;
  cout << "Job STDOUT = " << outStream << endl;
  cout << "Job STDERR = " << errStream << endl;
  cout << endl;
  cout << pipes <<  " pipe(s)" << endl;
  cout << pipes + 1 << " process(es)" << endl << endl;

  /*read out of "vec" - each member of vec is a vector of a process & it's arguments*/
  for (unsigned int i = 0; i < vec.size(); i++){
    cout << "Process " << i << " argv:" << endl;

    for (unsigned int j = 0; j < vec.at(i).size() ; j++){
      cout << j << ": " << trim(vec.at(i).at(j)) << endl;
    }//for
    cout << endl;

  }//for

  commands.clear();
  temp.clear();
  vec.clear();

  return EXIT_SUCCESS;
}//main


string trim(string& str)
{
  size_t first = str.find_first_not_of(' ');
  size_t last = str.find_last_not_of(' ');
  return str.substr(first, (last-first+1));
}
