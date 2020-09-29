import java.util.Vector;

public class MyVectorTester {

	private static final boolean PASS = true;
	private static final boolean FAIL = false;
	
	public static void main(String[] args) {
	
		MyVector vec  = new MyVector();
		
		if( !(vec instanceof VectorInterface)){
			System.out.println("MyVector must implement VectorInterface");
			return;
		}
		
		if ( ! constructorTest()){
			System.out.println("Constructor tests failed");
			return;
		}
		
		
		if ( ! addAndRemoveTest()){
			System.out.println("Add/Remove tests failed");
			return;
		}
			
		if ( ! containsTest()){
			System.out.println("Contains test failed");
			return;
		}
		
		if (! ensureCapacityTest()){
			System.out.println("Capacity tests failed");
			return;
		}
		
		if ( ! equalsTest()){
			System.out.println("Equals tests failed");
			return;
		}

		if( ! toStringTest()){
			System.out.println("toString test failed");
			return;
		}
		
		System.out.println("All tests passed");
	}
	
	
	static boolean constructorTest(){
		
		try{
			//default constructor
			MyVector myVec = new MyVector();
			if (myVec.size() != 0) 		return FAIL;
			if (myVec.capacity() != 10) return FAIL;
			if (! myVec.isEmpty()) 		return FAIL;
			
			//initial capacity = 5
			myVec = new MyVector(5);
			if (myVec.size() != 0) 		return FAIL;
			if (myVec.capacity() != 5) 	return FAIL;
			if (! myVec.isEmpty())		return FAIL;
			
			//with invalid initial capacity; default to 10
			myVec = new MyVector(-5);
			if (myVec.size() != 0)		return FAIL;
			if (myVec.capacity() != 10)	return FAIL;
			if (! myVec.isEmpty())		return FAIL;
				
			//with invalid initial capacity; default to 10
			myVec = new MyVector(-10, -20);
			if (myVec.size() != 0)		return FAIL;
			if (myVec.capacity() != 10)	return FAIL;
			if (! myVec.isEmpty())		return FAIL;
		
		//although we are catching the exceptions, print them for ease of debugging	
		}catch (Exception e){
			System.err.println(e);
			return FAIL;
		}
		
		return PASS;
		
	}
	
	static boolean addAndRemoveTest(){
		MyVector myVec = new MyVector();
		/*
		 * In these cases, when adding an element to an already
		 * full vector, it should double in size
		 */
		try{
			//add useless MP3s to fill the vector
			for (int i = 0; i < myVec.capacity(); i++){
				if (myVec.size() != i) return FAIL;
				myVec.add(new MP3File());
			}
			
			if (myVec.isEmpty()) return FAIL;
			
			//add one more, it should have doubled in capacity
			myVec.add(new MP3File());
			if (myVec.capacity() != 20) return FAIL;
			
			//create new with capacity 4
			myVec = new MyVector(4);
			
			for (int i = 0; i < myVec.capacity(); i++){
				if (myVec.size() != i) return FAIL;
				myVec.add(new MP3File());
			}
			
			//add one more, it should have doubled in capacity
			myVec.add(new MP3File());	
			if (myVec.capacity() != 8) return FAIL;
			
			/*
			 * It should now grow by the capacity increment 
			 */
			//create new with capacity 5 & increment 1
			myVec = new MyVector(5, 1);
			
			for (int i = 0; i < myVec.capacity(); i++){
				if (myVec.size() != i) return FAIL;
				myVec.add(new MP3File());
			}
			
			//add one more, it should have increased capacity by 1
			myVec.add(new MP3File());
			if (myVec.capacity() != 6) return FAIL;
			
			
			myVec.add(4, new MP3File("title", "artist"));
			
			//if it didn't add
			if (myVec.capacity() != 7) return FAIL;
			
			//if it didn't add at the correct index
			if (! myVec.get(4).equals(new MP3File("title", "artist"))) return FAIL;
			
			myVec.remove(3);
			
			//if it didn't shift all elements left
			if (! myVec.get(3).equals(new MP3File("title", "artist"))) return FAIL;
			
			myVec.clear();
			
			//it should be empty, but with it's same capacity
			if (! myVec.isEmpty()) return FAIL;
			if (myVec.capacity() != 7) return FAIL;
			
		//although we are catching the exceptions, print them for ease of debugging
		}catch (Exception e){
			System.err.println(e);
			return FAIL;
		}
		
		return PASS;
	}
	
	static boolean containsTest(){
		try{
			MyVector myVec = new MyVector();
			
			//add 6 files
			myVec.add(new MP3File("title1", "artist1"));
			myVec.add(new MP3File("title2", "artist2"));
			myVec.add(new MP3File("title3", "artist3"));
			myVec.add(new MP3File("title4", "artist4"));
			myVec.add(new MP3File("title5", "artist5"));
			myVec.add(new MP3File("title6", "artist6"));
			
			//make sure those files are in the vector
			if (! myVec.contains(new MP3File("title1", "artist1"))) return FAIL;
			if (! myVec.contains(new MP3File("title2", "artist2"))) return FAIL;
			if (! myVec.contains(new MP3File("title3", "artist3"))) return FAIL;
			if (! myVec.contains(new MP3File("title4", "artist4"))) return FAIL;
			if (! myVec.contains(new MP3File("title5", "artist5"))) return FAIL;
			if (! myVec.contains(new MP3File("title6", "artist6"))) return FAIL;
			if (myVec.contains(new MP3File())) return FAIL;
			
			//these 3 lines will likely cause the majority of the failures of this test
			//if there is a null object, fail
			if (myVec.contains(null)) return FAIL;
			//add a null object
			myVec.add(null);
			//if there is not a null object, fail
			if (! myVec.contains(null)) return FAIL;
			
		//although we are catching the exceptions, print them for ease of debugging
		}catch(Exception e){
			System.err.println(e);
			return FAIL;
		}
		
		
		return PASS;
	}
	
	static boolean ensureCapacityTest(){
		MyVector myVec = new MyVector();
		
		if (myVec.capacity() != 10) return FAIL;
		
		//ensure 8 so capacity shouldn't change 
		myVec.ensureCapacity(8);
		if (myVec.capacity() != 10) return FAIL;
		
		//ensure 15, it should double from 10 -> 20
		myVec.ensureCapacity(15);
		if (myVec.capacity() != 20) return FAIL;
		
		//ensure 100, should double from 20 -> 40 -> 80 -> 160
		myVec.ensureCapacity(81);
		if (myVec.capacity() != 160) return FAIL;
		
		/*
		 * trim to size
		 */
		//add a single file
		myVec.add(new MP3File());
		
		//trim the vector to size
		myVec.trimToSize();
		
		//capacity should now be 1
		if (myVec.capacity() != 1) return FAIL;
	
		
		return PASS;
		
	}
	
	static boolean equalsTest(){
		MyVector mv1 = new MyVector();
		MyVector mv2 = new MyVector(5);
		MyVector mv3 = new MyVector(10, 15);
		
		//if any of those return equal, fail
		if (mv1.equals(mv2)) return FAIL;
		if (mv1.equals(mv3)) return FAIL;
		if (mv2.equals(mv3)) return FAIL;
		
		mv2 = new MyVector();
		
		//if these 2 aren't equal, fail
		if (! mv1.equals(mv2)) return FAIL;
		
		//add some elements
		for (int i = 0; i < 7; i++){
			mv1.add(new MP3File("title", "artist"));
			mv2.add(new MP3File("title", "artist"));
		}
		
		//if these 2 aren't equal, fail
		if (! mv1.equals(mv2)) return FAIL;
		
		mv1.remove(6);
		
		//if these 2 are equal, fail
		if (mv1.equals(mv2)) return FAIL;
		
		mv2.remove(6);
		//if these 2 aren't equal, fail
		if (! mv1.equals(mv2)) return FAIL;
		
		return PASS;
		
	}
	
	//this is really not strict about the string representation of a vector,
	//the only requirement is that for each item, the title and artist are present
	static boolean toStringTest(){
		MyVector myVec = new MyVector();
		
		myVec.add(new MP3File("title1", "artist1"));
		myVec.add(new MP3File("title2", "artist2"));
		
		String toString = myVec.toString();
		
		return toString.contains("title1") && toString.contains("artist1")
				&& toString.contains("title2") && toString.contains("artist2");
	}
	
	

}
