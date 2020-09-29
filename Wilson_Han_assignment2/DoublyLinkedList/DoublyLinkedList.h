#include "ItemType.h"
#ifndef DOUBLYLINKEDLIST_H
#define DOUBLYLINKEDLIST_H

struct NodeType{
  ItemType data;
  NodeType* next;
  NodeType* back;
};

class DoublyLinkedList{
 public:
  DoublyLinkedList();
  ~DoublyLinkedList();
  int lengthIs() const;
  void insertItem(ItemType &item);
  void deleteItem(ItemType &item);
  void print();
  void additivePairs(ItemType &item);
  void FindItem(NodeType *listData, ItemType item, NodeType*& location, bool& found);
private:
  int length;
  NodeType *head;
  NodeType *current;
};

#endif

