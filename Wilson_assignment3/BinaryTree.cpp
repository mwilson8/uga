#include "BinaryTree.h"
#include <cstdlib>
#include <iostream>

using namespace std;

Node* root;
int length;
//Constructor
BinaryTree::BinaryTree(){
  root=NULL;
  length=0;
}

//check to see if the tree is empty
bool BinaryTree::isEmpty()const{
  return root==NULL;
}

//destructer that uses function deletetree to free all memory and changes length
BinaryTree::~BinaryTree(){
  deleteTree(root);
}

//Function that is used to delete the nodes in a tree in postorder
void BinaryTree::deleteTree(Node* tree){
  if(tree!=NULL){
    deleteTree(tree->left);
    deleteTree(tree->right);
    delete tree;
  }
}

//given insert function
void BinaryTree::insert(ItemType& key){
  Insert(root, key);
}
//Insert fucntion that takes in a node as a parameter
void BinaryTree::Insert(Node*& tree, ItemType key){
  if(tree==NULL){
    tree = new Node;
    tree->right = NULL;
    tree->left =NULL;
    tree->key = key;
    length++;
  }
  //else if(key.getValue()==tree->key.getValue()){
  //cout<<"Item already in tree. ";
  //}
  else if(key.getValue()>tree->key.getValue())
    Insert(tree->right,key);
  else
    Insert(tree->left,key);
}

//Given retrieve function that uses getVal to find if a node is in the tree
void BinaryTree::retrieve(ItemType& item, bool& found)const{
  getVal(root, item, found);
}

//Function used to find an item in a tree and sets item to the value if it is found
void BinaryTree::getVal( const Node* tree, ItemType& item, bool&found)const{
  if(tree==NULL)
    found = false;
  else if(item.getValue() < tree->key.getValue())
    getVal(tree->left,item,found);
  else if(item.getValue() > tree->key.getValue())
    getVal(tree->right,item,found);
  else{
    item = tree->key;
    found = true;
  }
}

//given delete function
void BinaryTree::deleteItem(ItemType& key){
  Delete(root, key);
}

//function used to delete an item from a tree and has a node as a paramater
void BinaryTree:: Delete(Node*& tree, ItemType item){
  if(item.getValue()<tree->key.getValue())
    Delete(tree->left,item);
  else if(item.getValue()>tree->key.getValue())
    Delete(tree->right,item);
  else
    DeleteNode(tree);
}

//function that physically calls delete to remove nodes from a tree
void BinaryTree::DeleteNode(Node*& tree){
  ItemType data;
  Node* tempPtr;
  tempPtr = tree;
  if(tree->left == NULL){
    tree = tree->right;
    delete tempPtr;
    length--;
  }
  else if(tree->right==NULL){
    tree = tree->left;
    delete tempPtr;
    length--;
  }
  else{
    getPred(tree->left,data);
    tree->key = data;
    Delete(tree->left,data);
  }
}

//function used to replace a delete node if it has 2 children
void BinaryTree::getPred(Node* tree, ItemType& data){
  while(tree->right!=NULL)
    tree = tree->right;
  data = tree->key;
  //cout << "in getPred: found " << data.getValue();
}

//prints in preorder
void BinaryTree::preOrder()const{
  PreOrder(root);
}

void BinaryTree::postOrder()const{
  PostOrder(root);
}

void BinaryTree::inOrder()const{
  InOrder(root);
}
void BinaryTree::PreOrder(Node* tree)const{
  if(tree!= NULL){
    cout<<tree->key.getValue()<<" ";
    PreOrder(tree->left);
    PreOrder(tree->right);
  }
}

//prints inorder
void BinaryTree::InOrder(Node* tree)const{
  if(tree!=NULL){
    InOrder(tree->left);
    cout<<tree->key.getValue()<<" ";
    InOrder(tree->right);
  }
}
//prints in postOrder
void BinaryTree::PostOrder(Node* tree)const{
  if(tree!=NULL){
    PostOrder(tree->left);
    PostOrder(tree->right);
    cout<<tree->key.getValue()<<" ";
  }
}

//returns the length
int BinaryTree::getLength()const{
  return length;
}

void getSameLevelNonSiblings(ItemType &key);
void printDepth(Node* tree, ItemType &data, int level);
int getDepth(Node *tree, ItemType &data, int level);

void BinaryTree::getSameLevelNonSiblings(ItemType &key){

  //finds how far down the tree the key is, starting @ root (aka 1)
  int depth = getDepth(root, key, 1);

  //prints all siblings at the same level
  printDepth(root, key, depth);

}//gSLNS

int getDepth(Node* tree, ItemType &data, int level){

  //if there is no tree
  if (tree == NULL)
    return 0;

  //if we find the value where we start lookings
  if (tree->key.getValue() == data.getValue())
    return level;

  //serach left sub trees 
  int depth = getDepth(tree->left, data, level+1);
  if (depth != 0)
    return depth;
 
  //if we didn't return then search right sub trees
  return getDepth(tree->right, data, level+1);

}//getDepth

void printDepth(Node* tree, ItemType &item, int level){

  if (tree == NULL || level < 2)
    return;

  if (level == 2){

    if (tree->left->key.getValue() == item.getValue() || tree->right->key.getValue() == item.getValue())
      return;

    if (root->left)
      cout << root->left->key.getValue() << " ";

    if (root->right)
      cout << root->right->key.getValue() << " ";
  }

  else if (level > 2){
    printDepth(tree->left, item, level-1);
    printDepth(tree->left, item, level-1);

  }//else if


}//pD
