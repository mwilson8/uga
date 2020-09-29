import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>{

	public static final int DEFAULT_SIZE = 10;
	
	@Deprecated
	private int capacityIncrement;
	//private int modCount;
	
	private int elementCount = 0;
	private Box<T>[] elementData;

	/**
	 * Construct a new ArrayList with default capacity of 10
	 */
	public ArrayList(){
		this(10);
	}
	
	/**
	 * Constructs a new ArrayList with initial given capacity
	 * @param size - initial capacity of this ArrayList
	 */
	public ArrayList(int size){
		this(10, 0);
	}
	
	/**
	 * Private constructor. Since capacityIncrement isn't used, this ArrayList
	 * will always grow by a factor of 1.5
	 * @param size - initial capacity of this ArrayList
	 * @param capacityIncrement - the 
	 */
	private ArrayList(int size, int capacityIncrement){
		if (size < 0)
			throw new IllegalArgumentException("invalid size: " + size);
		
		if (capacityIncrement < 0)
			throw new IllegalArgumentException("invalid capacityIncrement: " + size);
		
		elementData = Box.<T>array(size);
		this.capacityIncrement = capacityIncrement;
	}
	
	/**
	 * Append the given element to the end of this ArrayList
	 * @param elem - the element to be added
	 */
	@Override
	public void add(T elem) 
			throws NullPointerException {
		
		add(size(), elem);
	}

	/**
	 * Insert the given element into the ArrayList at the given index
	 * @param index - the index at which the element should be added
	 * @param elem - the element to be added
	 */
	@Override
	public void add(int index, T elem) 
			throws NullPointerException, IndexOutOfBoundsException {
		
		parameterCheck(index, elem);
		
		if (elementData.length == size())
			increaseCapacity();
		
		//shift the contents of the array one spot to the right from index
		System.arraycopy( elementData, index, elementData, index + 1, elementCount - index);

		elementData[index] = new Box<T>(elem);
		elementCount++;
	}

	/**
	 * Clears the values of this ArrayList. This implementation keeps the size
	 * of the inner array the same and sets all values to <code>null</code>
	 */
	@Override
	public void clear() {
		for (int i = 0; i < elementData.length; i++)
			elementData[i] = null;
		
		elementCount = 0;	
	}

	/**
	 * Returns the element at the given index
	 * @param index - the location of the element to be returned
	 * @throws IndexOutOfBoundsException - if the given <code>index</code> is out of bounds
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		
		indexCheck(index);
		
		return (T)elementData[index].get();
	}

	/**
	 * Sets the specified index to the given element
	 * @param index - the location to be modified
	 * @param elem - the elemetn to be added
	 * @throws NullPointerException - if the given <code>elem</code> is null
	 * @throws IndexOutOFBoundsException - if the given <code>index</code> is out of bounds
	 */
	@Override
	public T set(int index, T elem) 
			throws NullPointerException, IndexOutOfBoundsException {
		
		parameterCheck(index, elem);
		
		T temp = elementData[index].get();
		elementData[index] = new Box<T>(elem);
		
		return temp;
	}

	/**
	 * Returns the size of this ArrayList; i.e., the number of elements 
	 */
	@Override
	public int size() {
		
		return elementCount;
	}

	/**
	 * Returns <code>true</code> if this ArrayList contains no elements,
	 * <code>false</code> otherwise
	 */
	@Override
	public boolean isEmpty() {
		
		return size() == 0;
	}

	/**
	 * Returns an Iterator for this ArrayList
	 */
	@Override
	public Iterator iterator(){
		return iterator(0);
	}
	
	/**
	 * Returns an Iterator for this ArrayList, starting at the given <code>index</code>
	 * @param index - the index at which this Iterator will begin
	 * @return - an iterator starting at the given <code>index</code>
	 */
	public Iterator iterator(int index){
		return new Iterator(index);
	}
	
	/**
	 * Returns <code>true</code> if this ArrayList contains the given element, 
	 * <code>false</code> otherwise
	 * @param elem - the element to search for
	 * @return true if the given <code>elem</code> is in this ArrayList, 
	 * <code>false</code> otherwise
	 * @throws NullPointerException - if the given <code>elem</code> is null
	 */
	@Override
	public boolean contains(T elem) throws NullPointerException {
		
		objectCheck(elem);
		return indexOf(elem) >= 0;
	}

	/**
	 * Removes the first occurrence of the given element
	 * @param elem - the element to remove
	 * @throws NullPointerException - if the given <code>elem</code> is null
	 * @return true if the given <code>elem</code> was found and removed, 
	 * <code>false</code> otherwise
	 */
	@Override
	public boolean remove(T elem) throws NullPointerException {
		
		objectCheck(elem);
		
		if (this.contains(elem)){
			for (int i = indexOf(elem); i < size(); i++)
				if (i != size() -1)
					elementData[i] = elementData[i+1];
			
			elementData[size() - 1] = null;
			return true;
		}
		return false;
	}

	/**
	 * Returns the index of the first occurrence of the given <code>elem</code>
	 * @param elem - the element to search for
	 * @throws NullPointerException - if the given <code>elem</code> is null
	 * @return the index of the first occurrence of the given <code>elem</code>, 
	 * -1 if not found
	 */
	@Override
	public int indexOf(T elem) throws NullPointerException {
		
		//doesn't support null Ts
		objectCheck(elem);
		
		for (int i = 0; i < size(); i ++)
			if (elementData[i].get().equals(elem))
				return i;
		
		return -1;
	}

	/**
	 * Private helper method to grow the inner array, when neccessary
	 */
	private void increaseCapacity(){
		Box<T> [] temp;
		if (capacityIncrement == 0)
			temp = Box.<T>array((elementData.length * 3) / 2 + 1);
		
		else 
			temp = Box.<T>array(elementData.length + capacityIncrement);
		
		for (int i = 0; i < elementData.length; i++) 
			temp[i] = elementData[i];

		elementData = temp;
	}
	
	private void indexCheck(int index){
		if (index < 0 || index > elementCount)
			throw new IndexOutOfBoundsException("invalid index: " + index);
	}
	
	/**
	 * Private helper method to check that the given element is not null
	 * @param t - the element to check
	 * @throws NullPointerException - if given <code>t</code> is <code>null</code>
	 */
	private void objectCheck(T t){
		if (t == null)
			throw new NullPointerException("invalid element: " + t);
	}
	
	/**
	 * Private helper method to validate the given parameters
	 * @param index - the index value to check
	 * @param t - the element to check
	 * @throws NullPointerException - if the given element <code>t</code> is <code>null</code>
	 * @throws IndexOutOfBoundsException - if the given <code>index</code> is out of bounds
	 */
	private void parameterCheck(int index, T t){
		indexCheck(index);
		objectCheck(t);
	}

	/**
	 * Method for trimming the size of this ArrayList, if necessary. Implementations
	 * can use this method to minimize the space used by this ArrayList
	 */
	public void trimToSize(){
		Box<T> [] temp = Box.<T>array(size());
		for (int i = 0; i < size(); i++){
			temp[i] = elementData[i];
		}
		
		elementData = temp;
	}
	
	/**
	 * We are assuming that the ArrayList will not change during iteration; no need to check mod count
	 * @author MitchWilson
	 *
	 */
	private class Iterator
		implements ListIterator<T>{
		
		private int currPos = 0;
	    //private int expectedModCount = modCount;

		public Iterator( int index ){
			
		    this.currPos = index;
		}

		public boolean hasNext(){
			
		    return (currPos < size()-1); 
		}
	
		public boolean hasPrevious(){
			
		    return (currPos > 0);
		}
	
		@SuppressWarnings("unchecked")
		public T next(){
		    //checkForComodification();
			
		    if( !hasNext() )
		    	throw new NoSuchElementException();
		    
		    return (T) ArrayList.this.elementData[ currPos++ ];
		}
	
		public int nextIndex(){
		  return currPos +1;
		}
	
		public T previous(){
			//checkForComodification();
			
			if ( !hasPrevious() ) 
			   throw new NoSuchElementException();
		   
		    return (T)ArrayList.this.elementData[currPos-=1].get();
		}

		public int previousIndex(){
		    
		    return currPos -1;
		}

		public void add(T t){
			
		    //throw new UnsupportedOperationException( "add is not available" );
			ArrayList.this.add(currPos, t);
		}
	
		public void remove(){
			
		    //throw new UnsupportedOperationException( "remove is not available" );
			ArrayList.this.remove(elementData[currPos].get());
		}

		public void set(T elem){
			
		    //throw new UnsupportedOperationException( "set is not available" );
			ArrayList.this.set(currPos, elem);
		}
	
		/*
		private final void checkForComodification() {
		    if (expectedModCount != SimpleArrayList.this.modCount)
			throw new ConcurrentModificationException();
		}
		*/
    }
	
}
