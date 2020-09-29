#ifndef ITEMTYPE_H
#define ITEMTYPE_H


enum Comparison{
  G,
  L,
  E,
};

class ItemType{
 private:
  int value;

 public:
  explicit ItemType();
  void print();
  Comparison CompareTo(ItemType &item);
  void initialize(int number);
  int getValue() const;
};

#endif



