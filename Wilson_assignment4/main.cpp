#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <math.h>

using namespace std;

vector<int> vec;
long comparisons;

void printValues(int x);
void swap(int i, int j);
void insertionSort(vector<int> &vector);
void mergeSort(vector<int> &vec);
void merge(vector<int> &vec1, vector<int> &vec2, vector<int> &result);
void quickSort(vector<int> &vector, int start, int end);
int partition(vector<int> &vec, const int left, const int right);
 
int main(int argc, char** argv){
    int input;
  std::fstream fs;
  fs.open(argv[1]);
  if (fs){
    while (fs >> input){
      vec.push_back(input);
      //      fs >> input;
      //cout << "input is" << input << endl;
      //mainArray[i] = input;
      //i++;
    }//while

  } else {
    cout << "error opening file" << endl;
    return 0;
  }//else
  


  cout << "insertion-sort (i)\tmerge-sort (m)\tquick-sort (q)" << endl;
  cout << "enter algorithm: ";
  char choice;
  cin >> choice;
  switch (choice){
  case 'i': insertionSort(vec);
    cout << "insertion sort:";
    break;
  case 'm': mergeSort(vec);
    cout << "merge sort:";
    break;
  case 'q': quickSort(vec, 0, vec.size() - 1);
    cout << "quick sort:";;
    break;

  }//switch
  printValues(20);
  cout << "Total comparisons: " << comparisons << endl;
  return 0;


  //   printValues(20);
  // insertionSort(vec);
  // quickSort(vec, 0, vec.size() - 1);
  //mergeSort(vec);
  //cout << "after sort" << endl;
  //printValues(20);
  //cout << "comparisons: " << comparisons << endl;
}//main

void insertionSort(vector<int> &vec){
  int i, j, temp;
  for (i = 1; i < vec.size(); i ++){
    j = i;
    temp = vec[i];
    while(j > 0 && temp <= vec[j-1]){
      comparisons ++;
      vec[j] = vec[j-1];
      j--;
    }//while
    vec[j] = temp;
  }//for

}//insertionSort

void printValues(int x){
  for (int i = 0; i < vec.size(); i++){
    cout << vec.at(i) << " ";
  if (i % x == 0)
    cout  << endl;
  }
  cout << endl;
}//printArray

void quickSort(vector<int> &vec, int start, int end){

  if (end > start){
    int pivot = partition(vec, start, end);
    quickSort(vec, start, pivot - 1); //left side
    quickSort(vec, pivot + 1, end); //right side


  }//if

}//quickSort
int partition(vector<int> &vec, const int start, const int end){
 
  int pivot = vec[start];
  int low = start + 1;
  int high = end;

  while (high > low){
    while (low <= high && vec[low] <= pivot){//find values that are on the left but should be on the right
      low ++;
      comparisons ++;
    }//while
    while (low <= high && vec[high] > pivot){//find values on the right that should be on the left
      high --;
      comparisons ++;
    }//while

    if (high > low) //swap them
      std::swap(vec[high], vec[low]);

    while (high > start && vec[high] >= pivot){
      high --;
      //      comparisons ++;
    }//while
 
    if (pivot > vec[high]){
      vec[start] = vec[high];
      vec[high] = pivot;
      return high;
    } else {
      return start;
    }//else

  }//while

}//partition

void merge (vector<int> &vec1, vector<int> &vec2, vector<int> &result){
  int i = 0, j = 0, k = 0;
  while(i < vec1.size() && j < vec2.size() ){
    if (vec1[i] <= vec2[j]){
      result[k] = vec1[i];
      i++;
      comparisons ++;
    } else {
      result[k] = vec2[j];
      j++;
      comparisons ++;
    }//else
    k++;
  }//while

  //if there's any remaining values
  while (i < vec1.size()){
    result[k] = vec1[i];
    i++;
    k++;
  }//while
  //any remainign values in vec2
  while(j < vec2.size()){
    result[k] = vec2[j];
    j++;
    k++;
  }//while
}//merge

void mergeSort(vector<int> &vec){
  //base case is left out, implied here 
  int n = vec.size();
  if (n > 1){
    int size1 = (int)ceil(n/2.0);
    int size2 = (int)floor(n/2.0);
    vector<int> left (size1);
    vector<int> right (size2);

    //original vector split into 2, left and right
    for (int i = 0; i < size1; i ++)
      left[i] = vec[i];

    for (int i = 0; i < size2; i ++)
      right[i] = vec[i + size1];


    //recursively do the same process (splitting into 2 vectors) on each half
    //and combine
    mergeSort(left);
    mergeSort(right);
    merge(left, right, vec);

  }//if
}//mergeSort
