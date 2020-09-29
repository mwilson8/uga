#include "ListNode.h"
#include "DataType.h"
#ifndef SORTEDLINKEDLIST_H
#define SORTEDLINKEDLIST_H
 
class SortedLinkedList
{
 private:
   int count;
   ListNode *head;

 public:
  SortedLinkedList();
  ~SortedLinkedList();
  int length();
  void insertItem(DataType &item);
  void deleteItem(DataType &item);
  int searchItem(DataType &item);
  void clear();
   //  void pairwiseSwap();
    };


#endif
