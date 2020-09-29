
/**
 * NOTE: while this is meant to emulate the Vector class, at this point in time, the students
 * have not learned about generic types. Therefore, the best we can do is to have an MP3File
 * array, which will allow addition of any MP3File to our MyVector, where as a true vector
 * would allow type <E> and use an Object array. 
 * 
 * @author MitchWilson
 *
 */
public class MyVector 



/* This class doesn't implement any library interfaces, so it can't be used with a for-each
 * loop or Arrays.sort and so on..
 * 
 * All of the methods that are implemented are included in the VectorInterface class,
 * meaning that as long as this class implements VectorInterface, those methods must be
 * implemented. If you choose to add or remove methods, it's recommended to amend 
 * VectorInterface to represent any additional methods
 */
implements VectorInterface
{
	
	/**
	 * The default capacity for elementData array
	 */
	private static final int DEFAULT_CAP = 10;
	
	/**
	 * The default capacity increment
	 */
	private static final int DEFAULT_CAP_INC = 0;
	
	@Deprecated
	@SuppressWarnings("unused") //it's currently unused and can be deleted, if preferred
								//or, it could be used as part of the implementation
								//the MP3Driver does not use an iterator so there is no need to 
								//check for modification count
	/**
	 * The number of times this list has been structurally modified. 
	 * Structural modifications are those that change the size of the list, 
	 * or modify it in such a way that iterations in progress may yield incorrect results.
	 */
	private int modCount;
	
	
	/**
	 * The array buffer into which the components of the MyVector are stored. 
	 * The capacity of the MyVector is the length of this array buffer, and is 
	 * at least large enough to contain all the vector's elements. 
	 * Any array elements following the last element in the Vector are null.
	 */
	private MP3File [] elementData;
	
	/*in Vector, the below vars are protected ints; 
	 *made private here b/c the overwhelming majority 
	 *of 1301 students have not learned inheritance
	 */
	
	/**
	 * The amount by which the capacity of the MyVector is automatically 
	 * incremented when its size becomes greater than its capacity.
	 */
	private int capacityIncrement;
	
	/**
	 * The number of valid components in this MyVector MP3File.
	 */
	private int elementCount;

	/**
	 * Constructs an empty MyVector so that its internal data array has size DEFAULT_CAP 
	 * and its standard capacity increment is DEFAULT_CAP_INC
	 */
	public MyVector() {
		elementData = new MP3File[DEFAULT_CAP];
		capacityIncrement = DEFAULT_CAP_INC;
	}
	
	/* hopefully this is the only constructor we can't use
	 *
	 *public MyVector(Collection<? extends E> c){...}
	 *
	 */
	
	
	/**
	 * Constructs an empty vector with the specified initial capacity 
	 * and with its capacity increment equal to DEFAULT_CAP_INC.
	 * 
	 * If initialCapacity < 0, elementData becomes an array of capacity DEFAULT_CAP
	 * 
	 * @param initialCapacity - the initial capacity of the vector
	 */
	public MyVector(int initialCapacity){
		if (initialCapacity < 0)
			elementData = new MP3File[DEFAULT_CAP];
		else
			elementData = new MP3File[initialCapacity];
		
		capacityIncrement = DEFAULT_CAP_INC;
	}
	
	/**
	 * Constructs an empty MyVector with the specified initial capacity and capacity increment.
	 * 
	 * If initialCapacity < 0, elementData becomes an array of capacity DEFAULT_CAP
	 * If capacityIncrement < 0, capacityIncrement should be equal to DEFAULT_CAP_INC
	 * 
	 * @param initialCapacity - the initial capacity of the vector
	 * @param capacityIncrement - the amount by which the capacity is increased when 
	 * the vector overflows
	 */
	public MyVector(int initialCapacity, int capacityIncrement){
		if (initialCapacity < 0)
			elementData = new MP3File[DEFAULT_CAP];
		else 
			elementData = new MP3File[initialCapacity];
		
		if (capacityIncrement < 0)
			this.capacityIncrement = DEFAULT_CAP_INC;
		else
			this.capacityIncrement = capacityIncrement;
	}
	
	/**
	 * Appends the specified element to the end of this Vector.
	 * @return true
	 * @param m - MP3File to be appended to this Vector
	 */
	public boolean add (MP3File file){
		if (capacity() == size())
			increaseCapacity();
		
		add(size(), file);
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this Vector. 
	 * It should call the increaseCapacity method, if needed (if current size == current capacity)
	 * Shifts the element currently at that position (if any) and any subsequent 
	 * elements to the right (adds one to their indices). If index is invalid
	 * (index > size() or index < 0), this method should do nothing.
	 * If you're tracking it, this should increment modCount
	 * @param index - index at which the specified element is to be inserted
	 * @param file - MP3File to be inserted
	 */
	public void add (int index, MP3File file){
		if(index < 0 || index > size())
			return;
			//throw new ArrayIndexOutOfBoundsException();
		
		if (capacity() == size())
			increaseCapacity();
		
		for (int i = size() - 1; i >= index; i--)
			elementData[i + 1] = elementData[i];
		
		elementData[index] = file;
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
			elementData[i] = null;
		
		elementCount = 0;
	}
	
	
	/**
	 * Returns true if this vector contains the specified element.
	 * @param file - MP3File whose presence in this vector is to be tested
	 * @return true if this vector contains the specified element
	 */
	public boolean contains(MP3File file){
		if (size() == 0) return false;
		
			for (int i = 0; i < size(); i++){
				
				if (elementData[i] == null){
					if (file == null) return true;
					else continue;
				}
				
				if (elementData[i].equals(file))
					return true;
			}
		return false;
	}
	
	
	/**
	 * Increases the capacity of this vector, if necessary, to ensure that it can hold 
	 * at least the number of components specified by the minimum capacity argument.
	 * 
	 * @param minCapacity - the desired minimum capacity
	 */
	public void ensureCapacity(int minCapacity){	
		while (capacity() < minCapacity)
			increaseCapacity();
	}
	
	
	
	/**
	 * For testing if this MyVector is equal to another MyVector, 
	 * size, capacity, & capacityIncrement should be equal, and they should
	 * contain the same elements, in the same order
	 * @param anotherVector - the MyVector to compare to 
	 * @return if anotherVector is equal to this one
	 */
	public boolean equals(MyVector anotherVector){
		if (this.size() != anotherVector.size() 
				|| this.capacity() != anotherVector.capacity()
				|| this.capacityIncrement != anotherVector.capacityIncrement)
			return false;
		
		for (int i = 0; i < this.size(); i++){
			if (! this.get(i).equals(anotherVector.get(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Returns the element at the specified position in this Vector.
	 * @param index - index of the element to return
	 * @return MP3File at the specified index (index >= 0 && index < size())
	 * null otherwise
	 */
	public MP3File get(int index){
		if (index >= 0 && index < size())
		return elementData[index];
		
		return null;
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
	 * Removes the element at the specified position in this Vector. 
	 * Shifts any subsequent elements to the left (subtracts one from their indices).
	 * The last element should be set to null and returns the element that was removed 
	 * from the Vector.
	 * If you're tracking it, this should increment modCount
	 * @param index - the index of the element to be removed
	 * @return element that was removed, null if index is invalid
	 */
	public MP3File remove(int index){
		if (index < 0 || index >= size())
			return null;
		
		MP3File temp = elementData[index];
		
		for (int i = index; i < size(); i++){
			if (i != size() -1)
				elementData[i] = elementData[i+1];
		}
		elementData[size()-1] = null;
		elementCount--;
		modCount++;
		return temp;
	}
	

	
	/**
	 * Returns the number of components in this vector.
	 * @return the number of components in this vector
	 */
	public int size(){
		return elementCount;
	}
	
	/*
	/**
	 * Swaps the elements at the two indices
	 * @param index1 - index of first element to swap
	 * @param index2 - index of second element to swap
	 *//*
	public void swap(int index1, int index2){
		MP3File temp = elementData[index1];
		elementData[index1] = elementData[index2];
		elementData[index2] = temp;
	}*/
	

	/**
	 * Returns a string representation of this Vector, containing the String representation 
	 * of each element.
	 * @return a string representation of this collection
	 */
	public String toString(){
		String s = "";
		for (int i = 0; i < size(); i++){
			s += get(i).toString();
			if (i != size() - 1)
				s += "\n\n";
		}
		return s;
	}
	
	/**
	 * Trims the capacity of this vector to be the vector's current size. 
	 * This method replaces the inner array with a new one of smaller size. 
	 * An application can use this operation to minimize the memory use of a vector.
	 */
	public void trimToSize(){
		MP3File [] temp = new MP3File[size()];
		for (int i = 0; i < size(); i++){
			temp[i] = get(i);
		}
		elementData = temp;
		modCount++;
	}
	
	
	/**
	 * private helper method to allocate more space when needed
	 * If capacity increment is DEFAULT_CAP_INC, array should double in size, 
	 * otherwise it should increase by the capacity increment
	 * This method replaces the inner array
	 */
	private void increaseCapacity(){
		MP3File [] temp;
		if (capacityIncrement == DEFAULT_CAP_INC)
			temp = new MP3File[capacity() * 2];
		
		else 
			temp = new MP3File[elementData.length + capacityIncrement];
		
		for (int i = 0; i < elementData.length; i++) 
			temp[i] = get(i);

		elementData = temp;
	}


}//MyVector