package myvector;

import java.util.*;

/**
 * NOTE: while this is meant to emulate the Vector class, at this point in time, the students
 * have not learned about generic types. Therefore, the best we can do is to have an Object
 * array, which will allow addition of any element to our MyVector, where as a true vector
 * would only allow type <E>. 
 * However, the Vector class does allow instantiation without declaring a type 
 * i.e. Vector v = new Vector() as opposed to Vector<A> v = new Vector<A>().
 * 
 * @author MitchWilson
 *
 */
public class MyVector {
	
	
	@Deprecated //causes the "cross out" for the variable, but the variable is still tracked
	/**
	 * The number of times this list has been structurally modified. 
	 * Structural modifications are those that change the size of the list, 
	 * or modify it in such a way that iterations in progress may yield incorrect results.
	 * 
	 * Implementation merely has to increment this field in its add(int, E) and remove(int) methods
	 * If an implementation does not wish to provide fail-fast iterators, this field may be ignored.
	 */
	private int modCount;
	
	
	/**
	 * The array buffer into which the components of the MyVector are stored. 
	 * The capacity of the MyVector is the length of this array buffer, and is 
	 * at least large enough to contain all the vector's elements. 
	 * Any array elements following the last element in the Vector are null.
	 */
	private Object [] elementData;
	
	/*in Vector, the below vars are protected ints; 
	 *made private here b/c 1301 has not learned inheritance
	 */
	
	/**
	 * The amount by which the capacity of the MyVector is automatically 
	 * incremented when its size becomes greater than its capacity.
	 */
	private int capacityIncrement;
	
	/**
	 * The number of valid components in this MyVector object.
	 */
	private int elementCount;

	/**
	 * Constructs an empty MyVector so that its internal data array has size 10 
	 * and its standard capacity increment is zero.
	 */
	public MyVector() {
		elementData = new Object[10];
		capacityIncrement = 0;
	}
	
	/* hopefully this is the only constructor we can't use
	 *public MyVector(Collection<? extends E> c){...}
	 */
	
	
	/* the following try to emulate Vector as closely as possible for now, 
	 * even though students at this point have not learned about exceptions
	 */
	
	/**
	 * Constructs an empty vector with the specified initial capacity 
	 * and with its capacity increment equal to zero.
	 * 
	 * @throws IllegalArgumentException - if initialCapacity is less than 0
	 * @param initialCapacity - the initial capacity of the vector
	 */
	public MyVector(int initialCapacity){
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		
		elementData = new Object[initialCapacity];
		capacityIncrement = 0;
	}
	
	/**
	 * Constructs an empty MyVector with the specified initial capacity and capacity increment.
	 * 
	 * @throws IllegalArgumentException if initialCapacity or capacityIncrement are less than 0
	 * @param initialCapacity - the initial capacity of the vector
	 * @param capacityIncrement - the amount by which the capacity is increased when the vector overflows
	 */
	public MyVector(int initialCapacity, int capacityIncrement){
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		
		if (capacityIncrement < 0)
			throw new IllegalArgumentException();
		
		elementData = new Object[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}
	
	/**
	 * Appends the specified element to the end of this Vector.
	 * @return true
	 * @param o - Object to be appended to this Vector
	 */
	public boolean add (Object o){
		if (capacity() == size())
			increaseCapacity();
		
		add(size(), o);
		return true;
	}
	
	/**
	 * Inserts the specified element at the specified position in this Vector. 
	 * Shifts the element currently at that position (if any) and any subsequent 
	 * elements to the right (adds one to their indices).
	 * @param index - index at which the specified element is to be inserted
	 * @param o - Object to be inserted
	 */
	public void add (int index, Object o){
		if(index < 0 || index > size())
			throw new ArrayIndexOutOfBoundsException();
		
		if (capacity() == size())
			increaseCapacity();
		
		for (int i = size() - 1; i >= index; i--)
			elementData[i + 1] = elementData[i];
		
		elementData[index] = o;
		elementCount++;
		modCount++;
	}
	
	/**
	 * Returns the current capacity of this vector.
	 * @return the current capacity (the length of its internal data array, 
	 * kept in the field elementData of this vector)
	 */
	public int capacity(){
		return elementData.length;
	}
	
	/**
	 * Removes all of the elements from this Vector. 
	 * The Vector will be empty after this call returns.
	 * It retains it's capacity
	 */
	public void clear(){
		for (int i = 0; i < elementData.length; i++)
			remove(i);
	}
	
	/**
	 * Returns true if this vector contains the specified element. 
	 * @param o - Object whose presence in this vector is to be tested
	 * @return true if this vector contains the specified element
	 */
	public boolean contains(Object o){
		for (int i = 0; i < elementData.length; i++){
			if (elementData[i].equals(o)) 
				return true;
		}
		return false;
	}
	
	/**
	 * Copies the components of this vector into the specified array. 
	 * The item at index k in this vector is copied into component k of anArray.
	 * @param anArray - the array into which the components get copied
	 * @throws NullPointerException - if the given array is null
	 * @throws IndexOutOfBoundsException - if the specified array is not large enough to hold all the components of this vector
	 */
	public void copyInto(Object[] anArray){
		if (anArray == null)
			throw new NullPointerException();
		
		if (anArray.length < size())
			throw new IndexOutOfBoundsException();
		
		for (int i = 0; i < size(); i++)
			anArray[i] = elementData[i];
	}
	
	/**
	 * Increases the capacity of this vector, if necessary, to ensure that it can hold 
	 * at least the number of components specified by the minimum capacity argument.
	 * 
	 * Vector API dictates that capacity will increase by capacityIncrement unless 
	 * capacityIncrement <= 0; in that case, double in size. If doubling the capacity would 
	 * still not reach minCapacity, set new capacity to minCapacity
	 * 
	 * @param minCapacity - the desired minimum capacity
	 */
	public void ensureCapacity(int minCapacity){	
		while (capacity() < minCapacity)
			increaseCapacity();
	}
	
	/**
	 * Returns the first component (the item at index 0) of this vector.
	 * @return the first component of this MyVector
	 * @throws NoSuchElementException - if this vector has no components
	 */
	public Object firstElement(){
		if (isEmpty())
			throw new NoSuchElementException();
			
		return get(0);
	}
	
	/**
	 * Returns the element at the specified position in this Vector.
	 * @param index - index of the element to return
	 * @return object at the specified index
	 * @throws ArrayIndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	 */
	public Object get(int index){
		if (index < 0 || index >= size())
			throw new ArrayIndexOutOfBoundsException();
		
		return elementData[index];
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element 
	 * in this vector, or -1 if this vector does not contain the element.
	 * @param o - element to search for
	 * @return the index of the first occurrence of the specified element in this vector, 
	 * or -1 if this vector does not contain the element
	 */
	public int indexOf(Object o){
		return indexOf(o, 0);
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element 
	 * in this vector, or -1 if this vector does not contain the element.
	 * @param s - element to search for
	 * @param index - index to start searching from
	 * @return the index of the first occurrence of the specified element in this vector, 
	 * or -1 if this vector does not contain the element
	 */
	public int indexOf(Object o, int index){
		if ( o != null){
			for (int i = index; i < size(); i++){
				if (elementData[i].equals(o))
					return i;
			}
		} else {
			for (int i = index; i < size(); i++){
				if (elementData[i] == null)
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * Tests if this vector has no components.
	 * @return true if and only if this vector has no components, 
	 * that is, its size is zero; false otherwise.
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * Returns the last component (the item at index size() - 1) of this vector.
	 * @return the last component of this MyVector
	 * @throws NoSuchElementException - if this vector has no components
	 */
	public Object lastElement(){
		return get(size() - 1);
	}
	
	/**
	 * Removes the element at the specified position in this Vector. 
	 * Shifts any subsequent elements to the left (subtracts one from their indices). 
	 * Returns the element that was removed from the Vector.
	 * @param index - the index of the element to be removed
	 * @return element that was removed
	 * @throws ArrayIndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	 */
	public Object remove(int index){
		if (index < 0 || index >= size())
			throw new ArrayIndexOutOfBoundsException();
		
		Object temp = elementData[index];
		
		for (int i = index; i < size(); i++){
			elementData[i] = elementData[i+1];
		}
		elementCount--;
		modCount++;
		return temp;
	}
	
	/**
	 * Sets the size of this vector. If the new size is greater than the current size, 
	 * new null items are added to the end of the vector. If the new size is less than 
	 * the current size, all components at index newSize and greater are discarded.
	 * @param newSize - the new size of this vector
	 * @throws ArrayIndexOutOfBoundsException - if the new size is negative
	 */
	public void setSize(int newSize){
		if (newSize < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		if (newSize > size()){
			for(int i = size(); i < newSize; i++)
				add(null);
			
		}else{
			for (int i = size(); i > newSize; i--)
				remove(i);	
		}
	}
	
	/**
	 * Returns the number of components in this vector.
	 * @return the number of components in this vector
	 */
	public int size(){
		return elementCount;
	}
	
	/**
	 * Returns an array containing all of the elements in this Vector in the correct order.
	 * @return an array containing all of the elements in this collection
	 */
	public Object[] toArray(){
		Object[] temp = new Object[size()];
		copyInto(temp);
		return temp;
	}
	
	/**
	 * Returns a string representation of this Vector, containing the String representation of each element.
	 * @return a string representation of this collection
	 */
	public String toString(){
		String s = "";
		for (int i = 0; i < size(); i++){
			s += get(i).toString();
			if (i != size() - 1)
				s += ", ";
		}
		return s;
	}
	
	/**
	 * Trims the capacity of this vector to be the vector's current size. 
	 * If the capacity of this vector is larger than its current size, 
	 * then the capacity is changed to equal the size by replacing its 
	 * internal data array, kept in the field elementData, with a smaller one. 
	 * An application can use this operation to minimize the storage of a vector.
	 */
	public void trimToSize(){
		Object [] temp = new Object[size()];
		for (int i = 0; i < size(); i++){
			temp[i] = get(i);
		}
		elementData = temp;
		modCount++;
	}
	
	
	/**
	 * private helper method to allocate more space when needed
	 * If capacity increment is 0, array should double in size, 
	 * otherwise it should increase by the capacity increment
	 */
	private void increaseCapacity(){
		Object [] temp;
		if (capacityIncrement == 0)
			temp = new Object[capacity() * capacity()];
		
		else 
			temp = new Object[elementData.length + capacityIncrement];
		
		for (int i = 0; i < elementData.length; i++) 
			temp[i] = get(i);

		elementData = temp;
	}
	
	/**
	 * for testing if the myVector closely resembles a true vector
	 * to be equal, size and capacity should be equal, and they should
	 * contain the same elements, in the same order
	 * @param v
	 * @return
	 */
	private boolean isEqual(Vector v){
		if (this.size() != v.size() || this.capacity() != v.capacity())
			return false;
		
		for (int i = 0; i < this.size(); i++){
			if (this.get(i) != v.get(i))
				return false;
		}
		return true;
	}
	
	public static void main (String [] args){

		String s = " ";
		Vector<String> vector = new Vector();
		
		MyVector myVector = new MyVector();
		
		System.out.println(myVector.isEqual(vector));
		
		
		for (int i =0; i < 8; i++)
			vector.add(s);
	
		for (int i =0; i < 8; i++)
			myVector.add(s); 
		
		System.out.println(myVector.isEqual(vector));
		
		vector.add(5, "add");
		myVector.add(5, "add"); 
		
		System.out.println(myVector.isEqual(vector));

		vector.remove(4);
		myVector.remove(4);
		
		System.out.println(myVector.isEqual(vector));
		
		vector.trimToSize();
		myVector.trimToSize();
		
		System.out.println(myVector.isEqual(vector));
		
		
	}
	
}//MyVector
