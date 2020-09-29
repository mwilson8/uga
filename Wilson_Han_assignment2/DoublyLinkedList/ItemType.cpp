#include "ItemType.h"
#include <iostream>

using namespace std;

ItemType::ItemType(){
  value = 0;
}

Comparison ItemType::CompareTo(ItemType &item){
  //  cout<<"start of compareTo\n";
  //ItemType *temp;
  //  temp->initialize(item.getValue());
  //cout<<"item val "<<item.getValue()<<endl;
  //cout << "this val " << value << endl;
  if(value < item.getValue()){
    //cout<<"compare less\n";
    return L;
  }else if(value==item.getValue()){
    //cout<<"compare eqaual\n";
    return E;
  }else{
    //cout<<"compare greater\n";
    return G;
  }
  // delete temp;
}

void ItemType::print(){
  cout<<value;
}

void ItemType::initialize(int number){
  value = number;
  // cout<<"initialize method ran\n";

}

int ItemType::getValue()const{
  return value;
}

