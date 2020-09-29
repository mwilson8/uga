/* 
 * Barnes Version
 */

import java.util.Scanner;
import java.util.Random;
/*
 * RandomWord.java
 * CSCI 1301
 * 
 * Author - Karen Aguar (2016)
 * 
 * The purpose of this class is to provide
 * a method to return a pseudorandomly generated 
 * word from an enumerated list.
 * Do not modify any source code in this file.  
 */
public class RandomWord {
	
	//Enumerated List Word Bank
	enum WordBank {algorithms, ascii, binary, branching, compile, debugging, 
	hardware, identifier, java, logic, loops, machine, memory, narrowing, 
	nesting, primitive, processor, software, syntax, widening } 
	
	//This variable is set when grading the program --- does not shuffle the word bank
	private static final boolean GRADING_MODE = false;
	
	//Updated each time we call newWord 
	private static int arrayIndex = 0;
	
	//word array has same length
	static String[] WORD_ARRAY = new String[WordBank.values().length];
	
	public static void populateWORD_ARRAY()
	{
		int i=0;
		for(WordBank p: WordBank.values())
		{
			WORD_ARRAY[i] = p.toString();
			i++;
		}
		
		//If grading mode is off, shuffle word bank.
		if(!GRADING_MODE)
			shuffleWordBank();
	}
	
	public static void shuffleWordBank()
	{
		Random r = new Random();
		int index1, index2;
		
		for(int i=0; i<50; i++) //Performs 50 random "swaps" of indices.
		{
			
			index1 = r.nextInt(20); //random index between 0-19
			index2 = r.nextInt(20); //random index between 0-19
			swap(index1, index2);
		}
	}
	
	public static void swap(int a, int b) //swaps items @ indices a & b in WORD_ARRAY
	{
		String temp = WORD_ARRAY[a];
		WORD_ARRAY[a] = WORD_ARRAY[b];
		WORD_ARRAY[b] = temp;
	}
	
	
	public static String newWord() //returns the next word in the bank
	{
		//On first call, populate the word array.
		if(arrayIndex == 0)
			populateWORD_ARRAY();
		
		String myWord = WORD_ARRAY[arrayIndex];
		arrayIndex++;
		return myWord;

	}
	
	//Hidden method to print word bank contents - can be used for grading
	public static void printWORD_ARRAY()
	{
		for(int i=0; i<WORD_ARRAY.length; i++)
		{
			System.out.println(i + ":\t" + WORD_ARRAY[i]);
		}
	}
}


/*
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class RandomWordOld {
	
	//The number of times the method newWord() has been called
	private static int numberOfMethodCalls = 0;

	//The maximum number of times the method newWord() can be called
	private static final int MAX_CALLS = 25;

	//The delay (in milliseconds) to retrieve a new word from a website 
	private static final int DELAY = 25;

	
	 Return a pseudorandomly generated word from a website.
	 
	  This method requires an internet connection, and it can
	  be called at most MAX_CALLS times.  If it is called more
	  than MAX_CALLS, then it will display an error message 
	  and terminate. 

	public static String newWord()
	{
		String myWord = "";
		URLConnection connection;
		
		//Check that the number of method calls has not exceeded MAX_CALLS
		numberOfMethodCalls++;
		if(numberOfMethodCalls > MAX_CALLS){
			System.out.println("Error:  Your program cannot call RandomWord.newWord() more than " + MAX_CALLS + " times.");
			System.out.println("Goodbye!");
			System.exit(0);
		}
		
		//Retrieve a pseudorandomly generated word from a website
		try {
			while(myWord.length() < 4 || myWord.length() > 9)
			{
				Thread.sleep(DELAY);
				connection = new URL("http://randomword.setgetgo.com/get.php").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				myWord = scanner.nextLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		return myWord.toLowerCase().trim();
	}
}
*/