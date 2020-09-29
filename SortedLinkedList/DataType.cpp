 #include "DataType.h"

/*enum Comparison {
  GREATER,
  EQUAL,
  LESS
  };
*/

  DataType::DataType(int value){
      //  DataType* dt = new DataType(value);
       this->value = value;
    }


 Comparison DataType::comparedTo(DataType &item){
     //  DataType* temp = new DataType(item.getValue());
   if(value < item.value){

      return Comparison::LESS;

   } else if(value == item.value){

       return Comparison::EQUAL;

   } else{

       return Comparison::GREATER;
   }

}

 int DataType::getValue()const{
     return value;
   }
