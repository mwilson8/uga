//BinaryTree.h
#include "ItemType.h"

#ifndef BINARYTREE_H
#define BINARYTREE_H

struct Node{
  ItemType key;
  Node *left;
  Node *right;
};

class BinaryTree{

private:
  // Node *root;
  //int length;
  void deleteTree(Node*);
  void Insert(Node*&, ItemType);
  void getVal(const Node*, ItemType &, bool &) const;
  void retrieveItem(Node* tree, ItemType &item, bool &found) const;
  void PreOrder(Node*) const;
  void InOrder(Node*) const;
  void PostOrder(Node*) const;
 void insertItem(Node*& tree, ItemType &item);
 void Delete(Node*&, ItemType);
 
 void destroy(Node*& tree);
 void DeleteNode(Node*& tree);
 void getPred(Node *tree, ItemType& data);
 void deleteItemSubroutine(Node *tree, ItemType &item);
 void printRecursive(Node * tree);

public:
 BinaryTree(); 
 ~BinaryTree();
 bool isEmpty() const;
 void insert(ItemType &key);
 void deleteItem(ItemType &key);
 void retrieve(ItemType &item, bool &found) const;
 void preOrder() const;
 void inOrder() const;
 void postOrder() const;
 int getLength() const;
 void print();
 void getSameLevelNonSiblings(ItemType&);
}; //class
#endif
