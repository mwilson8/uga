#include "ItemType.h"
#ifndef CIRCULARLINKEDLIST_H
#define CIRCULARLINKEDLIST_H

struct NodeType{
  ItemType data;
  NodeType *next;
};


class CircularLinkedList{
 private:
  int length;
  NodeType *head;
  NodeType *current;

 public:
  CircularLinkedList();
  ~CircularLinkedList();
  void insertItem(ItemType &item);
  void deleteItem(ItemType &item);
  int lengthIs() const;
  void print();
  void FindItem(NodeType *listData, ItemType item, NodeType*& location, NodeType*& predLoc, bool& found);
};

#endif



