#include "DoublyLinkedList.h"
#include <iostream>
#include <cstdlib>

using namespace std;

DoublyLinkedList::DoublyLinkedList(){
  length =0;
  cout << "constructor called successfully" << endl;
}

DoublyLinkedList::~DoublyLinkedList(){
  NodeType *tempPtr, *nullTemp = NULL;
  while(head!=nullTemp){
    tempPtr = head;
    head = head->next;
    delete tempPtr;
  }
}


void DoublyLinkedList::FindItem(NodeType *listData, ItemType item, NodeType*& location, bool& found){
  bool mts = true;

  location = listData;
  found = false;

  while(mts && !found){
    if(item.CompareTo(location->data)==L)
      mts= false;
      else if(item.CompareTo(location->data)== E)
      found = true;
    else{
      location = location->next;
      mts = (location !=listData->next);
    }
  }
}
int DoublyLinkedList::lengthIs() const{
  return length;
}

void DoublyLinkedList::insertItem(ItemType &item){
  NodeType* newNode;
  NodeType* next;
  NodeType* location;
  NodeType* prev;
    bool found;

  newNode = new NodeType;
  newNode->data = item;
  /*if((head->data.CompareTo(item)==L)){
      FindItem(head,item,location,found);
      newNode->back= predLoc;
      newNode->next= location;
      predLoc->next= newNode;
      location->back = newNode;
      if(head->data.CompareTo(item)==L)
        head = newNode;
    }
    else{
 head = newNode;
      newNode->next = newNode;
    }

  */
  //cout << "1" << endl;
  //newNode->data = item;

  location = head;
  if (length == 0 || location == NULL){
    head = newNode;
    head->next = NULL;
    cout << "head is new node" << endl;

  }else if (length == 1){
    if (head->data.CompareTo(item) == G){
      newNode->next = head;
      head->back = newNode;
    }else{
      head->next = newNode;
      newNode->back = head;
    }//else
    prev = head;
    next = head->next;
  }else {
    location = head;
    prev = location->back;
    next = location->next;

    while (true){



      if (location == NULL){
	cout<< "locaiton null" << endl;
	prev->next = newNode;
	newNode->back = prev;
	break;
      }//if

      cout << "this val: "; location->data.print(); cout<< endl;
      cout << "item val: "; item.print(); cout << endl;
    location = next;
    next = location->next;
    prev = location->back;
    cout << "location moved" << endl;
    cout << "l:" << location<< "p:" << prev << "n:" << next;
    }//while
  }//else
  cout << "length ++" << endl;
    length++;
}

void DoublyLinkedList::print(){
  NodeType* temp;

  temp = head;
  while (temp != NULL){
    temp->data.print();
    cout << " ";
    temp = temp->next;
  }

}

void DoublyLinkedList::deleteItem(ItemType &item){
  NodeType* location;
  NodeType* predLoc;
  bool found;
  FindItem(head, item, location, found);
  if(predLoc==location)
    head = NULL;
    else{
      predLoc->next = location->next;
      if(location == head)
        head = predLoc;
    }
delete location;
  length--;
}

//void DoublyLinkedList::additivePairs(){

//}

