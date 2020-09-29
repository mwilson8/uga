#include "CircularLinkedList.h"
#include <cstdlib>
#include <iostream>


using namespace std;

CircularLinkedList::CircularLinkedList(){
  length=0;
  current= NULL;
}


CircularLinkedList::~CircularLinkedList(){
  NodeType *tempPtr;
  NodeType *nullTemp=NULL;
  while(head!=nullTemp){
    tempPtr = head;
    head = head->next;
    delete tempPtr;
  }
  //  delete nullTemp;
}

void CircularLinkedList::FindItem(NodeType *listData, ItemType item, NodeType*& location, NodeType*& predLoc, bool& found){
  bool mts = true;
  //  cout<<"start of find"<<endl;

  if (length == 1){
    location = listData;
  }else{
    location = listData->next;
    predLoc = listData;
  }
  found = false;
  while(mts && !found){
    
    switch(item.CompareTo(location->data)){
    case L: mts = false; break;
    case E: found = true; mts = false; break;
    case G:
    default: 
      predLoc = location;
      location = location->next;
      mts = (location != listData->next);
    }//switch
    /*
    if(item.CompareTo(location->data)==L)
      mts= false;
    else if(item.CompareTo(location->data)==E)
      found = true;
    else{
      predLoc = location;
      location = location->next;
      mts = (location !=listData->next);
    }//else
    */
  }//while
  // cout<<"end of find\n";
}//FindItem

void CircularLinkedList::insertItem(ItemType &item){
  //cout<<"start of insertItem\n";
  NodeType *newNode;
  NodeType *predLoc;
  NodeType *location;
  bool found;

  //  cout<<"before new node\n";
  newNode = new NodeType();
  newNode->data = item;
  //cout<<"after new node\n";

  if (length == 0){
    //cout << "list is empty\n head is: " << head << endl;
    head =  newNode;
    newNode->next = head;//make it circular
    length ++;
    //cout << "list length: " << length << "\n head is: " << head << endl;
    return;
  }

  //cout << "list is not empty: searching for location" << endl;
 
  //  if((head->data.CompareTo(item)==L)){
   
  //cout<<"before find\n";
    FindItem(head, item, location, predLoc, found);
    //cout<<"after find\n";
    newNode->next = predLoc->next;
    predLoc->next = newNode;
    /*
      if(head->data.CompareTo(item)==L)
        head = newNode;
    */
      // }else{
    /*  
    cout<<"else statement\n";
      head = newNode;
      newNode->next = newNode;
    */
      //}
    length++;
    //    delete newNode;
    //delete predLoc;
    //delete location;
    //cout << "end insertItem" << endl;
}//insertItem

void CircularLinkedList::deleteItem(ItemType &item){
  NodeType* location;
  NodeType* predLoc;

  location = head;
  if (location->next == head){
    head = NULL;//NodeType();
      length = 0;
      return;

  } else {
    while (location->next != head){

      if (item.CompareTo(location->data) == E){
	predLoc = location->next;
      }//if
      predLoc = location;
      location = location->next;

    }//while

  }//else

  delete location;
  length--;
}

int CircularLinkedList::lengthIs()const{
  return length;
}

void CircularLinkedList::print(){
    NodeType* current = head;
    NodeType* temp;
    if (current == NULL)
      return;

    current->data.print();
    cout << " ";
    //    temp = temp->next; 
    bool go = true;

  while (go){
    temp = current; 
    while(temp->next != current){
      //      temp->data.print();
      //cout << " ";
      temp = temp->next;
  
    }//while
    if (temp == head)
      return;
    temp->data.print();
    cout << " ";
    current = temp;
    if (current == head)
      go = false;
  }//while
  //  current = temp;
  //  delete temp;
}//print
