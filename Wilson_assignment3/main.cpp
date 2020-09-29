#include "ItemType.h"
#include "BinaryTree.h"
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <iostream>

using namespace std;
void printMenu();

int main(int argc, char* argv[]){
  BinaryTree *tree = new BinaryTree();
  ItemType item;
  bool found;
  int input;
  char choice;

  std::fstream fs;
  fs.open(argv[1], std::fstream::in);

  if (fs.is_open()){

    fs >> input;
    while (!fs.eof()){
      item.initialize(input);
      tree->insert(item);
      fs >> input;

    }//while
  } else {

    cout << "Failed to open file" << endl;

    return 0;
  }
  printMenu();
  for(;;){
    cout<<"\nEnter a command: ";
    cin>>choice;
    if(choice=='i'||choice=='d'||choice== 'r'||choice== 'l'||choice== 'o'||choice\
       == 'p'||choice== 'n'||choice=='c' ||choice=='q' ){

      switch(choice){
      case 'i':cout<<"Item to be inserted ";
        cin>>input;
	item.initialize(input);
	tree->retrieve(item,found);
	if(found==true){
	  cout<<"Item already in tree.\n";
	  cout<<"In-Order: ";
	  tree->inOrder();
	  cout<<endl;
	}
	else
	  tree->insert(item);
	break;

      case 'd':cout<<"Item to delete: ";
	cin>>input;
	item.initialize(input);
	tree->retrieve(item,found);
	if(found == true){
	  tree->deleteItem(item);
	  cout<<"In-Order: ";
	  tree->inOrder();
	  cout<<endl;
	}
	else{
	  cout<<"Item not in list. \n";
	  cout<<"In-Order: ";
	  tree->inOrder();
	  cout<<endl;
	}
	break;

      case 'l': cout<< "List Length: "<<tree->getLength()<<endl;
	break;

      case 'r': cout<<"Item to be retrieved: ";
	cin>>input;
	item.initialize(input);
	tree->retrieve(item,found);
	if(found==true)
	  cout<<"Item found in tree. \n";
	else
	  cout<<"Item not found in tree. \n";
	break;
      case 'n':cout<<"In-Order: ";
	tree->inOrder();
	cout<<endl;
	break;

      case 'p': cout<<"Pre-Order: ";
	tree->preOrder();
	cout<<endl;
	break;

      case 'o': cout<<"Post-Order: ";
	tree->postOrder();
	cout<<endl;
	break;

      case 'c': cout <<"Item to use for siblings: ";
	cin >> input;
	item.initialize(input);
	tree->getSameLevelNonSiblings(item);
	break;

      case 'q':
	cout<<"Quitting program... \n";
	return 0;
	break;
      }
    }
    else
      cout<<"Command not recognized. Try again \n";
  }
  return 0;
}

void printMenu(){
  cout<< "-Commands- \ninsert (i)\ndelete (d)\nretrieve (r)\nlength (l)\nin-order\
 (n)\npre-order (p)\npost_order (o)\ngetSameLevelNonsiblings (c)\nquit (q)\n";
}


