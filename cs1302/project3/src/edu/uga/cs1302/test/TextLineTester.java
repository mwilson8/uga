package edu.uga.cs1302.test;
import edu.uga.cs1302.txtbuff.*;
import java.util.*;

public class TextLineTester {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//1) create a TextLine object initialized to "one line of text"
		TextLine tLine = new TextLine("one line of text");
		
		//2) prompt the user to enter a new line of text
		System.out.println("Enter a line of text:");
		String line = keyboard.nextLine();
		
		//3) create a new EditableTextLine 'eLine' with user-text
		EditableTextLine eLine = new EditableTextLine(line);

		//4) compare eLine with tLine & print equal or different
		System.out.println("4) Compare using .equals eLine with tLine & print Different or Equals");
		if (eLine.equals(tLine))
			System.out.println("4a) Equal\n");
		else
			System.out.println("4a) Different\n");
		
		//5) print eLine using toString
		System.out.println("5) Print eLine using toString():");
		System.out.println("5a " + eLine.toString() + "\n"); 
		
		//6) print eLine's length & capacity
		System.out.println("6) Print eLine length & capacity");
		System.out.println("6a) eLine length: " + eLine.length());
		System.out.println("6a) eLine capacity: " + eLine.capacity() + "\n");
		
		//7) prompt for a string of characters --> str
		System.out.println("Enter a string of characters: ");
		String str = keyboard.nextLine();
		
		//8) print index of all occurences of str in eLine
		
		
		int occurence = eLine.indexOf(str);
		if (occurence == -1)
			System.out.println("Not found");
		
		else {
			while (occurence != -1){
			System.out.println(str + " found at: " + occurence);
			occurence += str.length();
			occurence = eLine.indexOf(str, occurence);
			}
		}
		
		System.out.println(eLine.toString());
		
		//9) append str to the end of eLine & print modified eLine
		eLine.append(str);
		System.out.println("Append str to eLine and print new eLine:");
		System.out.println(eLine.toString() + "\n");
		
		//10) insert str in eLine at index position 0 & print modified eLine
		eLine.insert(0, str);
		System.out.println("Insert str at 0 and print new eLine:");
		System.out.println(eLine.toString() + "\n");
		
		//11) insert str in eLine at position str.length() & print modified eLine
		eLine.insert(str.length(), str);
		System.out.println("Insert str at str.length() and print new eLine");
		System.out.println(eLine.toString() + "\n");
		
		//12) replace all occurences of str in eLine with abc and print modified eLine
		System.out.println("Replace all instances of str with abc and print new line:");
		occurence = eLine.indexOf(str);
		if (occurence == -1)
			System.out.println("Not found");
		
		else {
			while (occurence != -1){
			eLine.replace(occurence, occurence+str.length()-1, "abc");
			occurence += str.length();
			occurence = eLine.indexOf(str, occurence);
			}
		}
		System.out.println(eLine.toString());
		
		
		
	}
	

}
