#include "SortedLinkedList.h"
#include <cstdlib>
#include <iostream>

using namespace std;

SortedLinkedList::SortedLinkedList::SortedLinkedList(){
  count =0;
  head = NULL;
}

SortedLinkedList::~SortedLinkedList(){
  ListNode* tempPtr;
  ListNode* nullTemp = NULL;
  while(head!=nullTemp){
    tempPtr = head;
    head= head->next;
    delete tempPtr;
  }
  delete nullTemp;
}

int SortedLinkedList::length(){
  return  this->count;
}

void SortedLinkedList::insertItem(DataType &item){
  ListNode* newNode;
  //  ListNode* nextNode;
  ListNode* previousNode = NULL;
  ListNode* currentNode;
    newNode->item = item;

    //if first item in the list, head points to the new node
  if (count == 0){
    head = newNode;
    count = count + 1;
    return;
  }


  bool moreToSearch;
  //start search at head of list
  currentNode = head;
  // nextNode = head->next;
  moreToSearch = (head != NULL);






  while (moreToSearch){

      //GREATER = 0; LESS = 1, EQUAL = 2
    if (item.getValue() == currentNode->item.getValue()){

      moreToSearch = false; cout<<"equal"<<endl;

    } else if (item.getValue() < currentNode->item.getValue()){

    moreToSearch = false; cout<<"less"<<endl;
    
    } else if (item.getValue() > currentNode->item.getValue()){

    cout<<"greater"<<endl;

          //move previous to the current locaiton
      previousNode = currentNode;
      //move current location to the next location
      currentNode = currentNode->next;
      //move next location to 2 spots from here
       moreToSearch = (currentNode != NULL);   

    }//else if

  }//while

  if (previousNode == NULL){
    newNode->next = head;
    head = newNode;
  
  } else {

  newNode->next = currentNode;
  previousNode->next = newNode;

  }
  count = count + 1;
}//insertItem

void SortedLinkedList::deleteItem(DataType &item){
  ListNode* location = head;
  ListNode* tempLocation;

  if (count == 1){
    if (item.comparedTo(head->item)==EQUAL)
      tempLocation = location;
  }//if
 
  else if(item.comparedTo(head->item)==EQUAL){
    tempLocation = location;
    head = head->next;
  }
  
  else{
    while((item.comparedTo((location->next)->item))!= EQUAL)
      location = location->next;

    tempLocation = location->next;
 
  }
   delete tempLocation;
    count--;
  
}

int SortedLinkedList::searchItem(DataType &item){
  int pos=0;
  bool moreToSearch;
  ListNode* location;

  location = head;
  moreToSearch =(location !=NULL);
  while(moreToSearch){
    switch(item.comparedTo(location->item)){
    case GREATER: location = location->next;
      moreToSearch = (location !=NULL);
      pos++;
      break;
    case EQUAL: moreToSearch= false;
      break;
    case LESS: moreToSearch = false;
      pos = -1;
      break;
    }
  }
  return pos;
}

void SortedLinkedList::clear(){

  ListNode* tempPtr;
  while(head!=NULL){
    tempPtr=head;
    head = head->next;
    delete tempPtr;
  }
  count =0;
}