#include <cstdlib>
#include <string>
#include <iostream>
#include <cstring>
#include <sstream>
#include <vector>
#include <string.h>
#include <algorithm>
#include <unistd.h>
#include <stdio.h>
#include <pwd.h>
#include <sys/types.h>
#include <sys/wait.h>

using namespace std;

void trim(string& str);
string getPrompt();
string getHomeDir();
vector<char *> mk_cstrvec(vector<string> & strvec);
void dl_cstrvec(vector<char *> & cstrvec);
void nice_execve(vector<string> argv, vector<string> envp);

int main (){

  int pipes;
  string inStream  = "STDIN_FILENO";
  string outStream = "STDOUT_FILENO";
  string errStream = "STDERR_FILENO";
  string input,extract;
  bool streamAltered;
  vector <string> commands;
  vector <string> temp;
  vector <vector <string> > vec;
  vector <string> envp {};
  //int safety = 0;


  //  while (safety < 10){
  cout << getPrompt();
  getline (cin, input);

  stringstream stream(input);

  /*the command line is in the stream strem "stream"*/
  while (stream >> extract){

    //    cout << "extracted: " << extract << endl;

    /* if "exit" is the first command (theoretically nothing else would follow)*/
    //    if (commands.empty() && (extract.compare("exit") == 0))
    //return EXIT_SUCCESS;
    
    //else
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

	
	/*push elements until another quote is found & that quote is not preceded with a \ */
	if (commands.at(i).find("\"") != commands.at(i).find("\\") + 1){
	
	  /*trim & remove the leading & trailing quotes*/
	  trim(quotes);
	  quotes = quotes.substr(1, quotes.length()-2);
	  
	  /*remove the escape characters*/
	  quotes.erase(remove(quotes.begin(), quotes.end(), '\\'), quotes.end());
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
  /*
  cout << endl;
  cout << "Job STDIN  = " << inStream << endl;
  cout << "Job STDOUT = " << outStream << endl;
  cout << "Job STDERR = " << errStream << endl;
  cout << endl;
  cout << pipes <<  " pipe(s)" << endl;
  cout << pipes + 1 << " process(es)" << endl << endl;
  */
  /*read out of "vec" - each member of vec is a vector of a process & it's arguments*/
  for (unsigned int i = 0; i < vec.size(); i++){
    //    cout << "Process " << i << " argv:" << endl;

    for (unsigned int j = 0; j < vec.at(i).size() ; j++){

      /*cd command*/
      if (vec.at(i).at(0).compare("cd") == 0){
	
	if ((vec.at(i).size() == 1) || (vec.at(i).at(1).compare("~") == 0))
	  chdir(getHomeDir().c_str());
      
	else
	  chdir(vec.at(i).at(1).c_str());
      }//if

      /*exit command*/
      else if (vec.at(i).at(0).compare("exit") == 0){
	  if (vec.at(i).size() == 1)
	    exit( EXIT_SUCCESS );
	  else{
	    int x = stoi(vec.at(i).at(1));
	    exit ( x );
	  }//else
	}//else if

      else{
	

	//	cout << j << ": " << vec.at(i).at(j);
	/*else, would fork & in the child, run Mike's main from exec2, passing in string vector 
	  string vector would be vec.at(i)?
	*/
	
pid_t pid, wpid;
int pstatus;

if ( (pid = fork()) < 0)
perror("FORK ERROR");

else if (pid == 0) { //in child
nice_execve(vec.at(i), envp); //envp should be empty?
}//else if

else { // in parent

bool dead = false;

while (!dead){

if ((wpid = waitpid(pid, &pstatus, 0)) == -1)
perror("waitpid");

//else if (wpid == 0){ cout << "no changed detected" << endl;} // no changes detected

else if (WIFEXITED(pstatus)){ // exited normally
dead = true;
}//else if
}//while

}//else



	
      }//else

    }//for
    cout << endl;

  }//for

  commands.clear();
  temp.clear();
  vec.clear();

  // safety++;
  // }//while true
  //if (safety >= 10)
   // cout << "safety reached" << endl;
	
   //  cout << "loop exited" << endl;
  return EXIT_SUCCESS;
}//main


void trim(string& str)
{
  size_t first = str.find_first_not_of(' ');
  size_t last = str.find_last_not_of(' ');
  str = str.substr(first, (last-first+1));
}

string getPrompt(){
  string homedir = getHomeDir();
  string s  = "1730sh:";
   /*if the curr dir has the home directory in it, replace with ~*/
  string directory = get_current_dir_name();

  if (directory.find(homedir) != string::npos)
  directory.replace(directory.find(homedir), string(homedir).length(), "~");

  s = "running program.. PWD:";
  s = s + directory + "$ ";


    return s;
}//getPrompt

 string getHomeDir(){
   struct passwd *pw = getpwuid(getuid());
   const char *homedir = pw->pw_dir;

   return homedir;

 }//getHomeDir

struct program {

  vector <string> args;

};//program 

vector<char *> mk_cstrvec(vector<string> & strvec) {
  vector<char *> cstrvec;
  for (unsigned int i = 0; i < strvec.size(); ++i) {
    cstrvec.push_back(new char [strvec.at(i).size() + 1]);
    strcpy(cstrvec.at(i), strvec.at(i).c_str());
  } // for
  cstrvec.push_back(nullptr);
  return cstrvec;
} // mk_cstrvec

void dl_cstrvec(vector<char *> & cstrvec) {
  for (unsigned int i = 0; i < cstrvec.size(); ++i) {
    delete[] cstrvec.at(i);
  } // for
} // dl_cstrvec

void nice_execve(vector<string> argv, vector<string> envp) {
  vector<char *> cargv = mk_cstrvec(argv);
  vector<char *> cenvp = mk_cstrvec(envp);
  execve(cargv.at(0), &cargv.at(0), &cenvp.at(0));
  perror("execve");
  dl_cstrvec(cargv);
  dl_cstrvec(cenvp);
  exit(EXIT_FAILURE);
} // nice_exec
