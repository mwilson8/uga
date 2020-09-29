#include "DataType.h"
#ifndef LISTNODE_H
#define LISTNODE_H

struct ListNode{
  DataType item;
  ListNode* next;
  explicit ListNode (DataType &item): item(item){};
  };

#endif
