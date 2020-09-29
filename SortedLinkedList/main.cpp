#include "SortedLinkedList.h"
#include "DataType.h"
#include "ListNode.h"
#include <cstdlib>
#include <iostream>

using namespace std;
void printMenu();
void printList();

int main(){
   SortedLinkedList *list = new SortedLinkedList();
   DataType item;
  cout << "main beginning----" <<endl;
  char choice;
  int x;
  for (;;){
  
    printMenu();
    cin >> choice;

   switch (choice){

    case 'i': 
      cout << "Enter number: ";
      cin >> x;
      item = DataType(x);
      list->insertItem(item); 
      printList();
      break;

   case 'd':
     cout << "Enter number: ";
     cin >> x;
     item = DataType(x);
     list->deleteItem(item);
     printList();
     break;

   case 's':
     cout << "Enter number: ";
     cin >> x;
     item = DataType(x);
     cout << "search returned: " << list->searchItem(item) << endl;
     printList();
     break;

   case 'n':
     //ITR_NEXT
     break;

   case 'r':
     //ITR_RESET
     break;

   case 'p':
     printList();
     break;

   case 'l':
     cout << list->length() << endl;
     break;

   case 'c':
     list->clear();
     break;

   case 'b':
     cout << "pairwise swap not implemented" << endl;
     break;

   case 'q': return 0; 
     break;
 
   }//switch

  }//for
  return 0;

}//main

void printMenu(){

  cout << "INSERT = 'i'" << endl;
  cout << "DELTE = 'd'" << endl;
  cout << "SEARCH = 's'" << endl;
  cout << "ITR_NEXT = 'n'" << endl;
  cout << "RESET_ITR = 'r'" << endl;
  cout << "PRINT_ALL = 'p'" << endl;
  cout << "LENGTH = 'l'" << endl;
  cout << "CLEAR_ALL = 'c'" << endl;
  cout << "PAIR_SWAP = 'b'" << endl;
  cout << "QUIT = 'q'" << endl;
}//printMenu

void printList(){
  ListNode* currPos;
  currPos = list->getHead();

  for (int i = 0 ; currPos != NULL ; i++){
    cout << currPos->item.getValue() << " ";
    currPos = currPos->next;
  }

}
