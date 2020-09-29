import java.util.*;
public class Exam1Review {

public static Scanner keyboard = new Scanner(System.in);
	

public static void main (String[] args){
	
	System.out.println("Which example?");
	int p = keyboard.nextInt();
	keyboard.nextLine();
	switch(p){
	case 1: Practice1(); break;
	case 2: Practice2(); break;
	case 3: Practice3(); break;
	}
}



	/**
	  * Practice 1
	  * Purpose: read in 3 integers & determine the minimum value
	  * Assumptions: three values entered on single line
	  * Restrictions: only use 2 int variables: min, temp
	  */
	public static void Practice1(){
		int min, temp;
		
		//assume first value read in min value
		min = keyboard.nextInt();
		
		//assign second value to temp
		temp = keyboard.nextInt();
		
		//if temp is smaller, assign it's value to min
		if (temp < min)
			min = temp;
		
		//repeat logic for 3rd value
		temp = keyboard.nextInt();
		
		if (temp < min)
			min = temp;
		
		System.out.println("Minimum is: " + min);
		
	}
	
	/**
	 * Practice 2
	 * Purpose: read in 3 integers & print them in order
	 * Assumptions: values entered on single line
	 * Restrictions: only use 4 int variables: min, middle, max, temp
	 */
	public static void Practice2(){
		
		int min, middle, max, temp;
		
		//since assignment will occur in if statements, need innitialization here
		min = middle = max = temp = 0;
		
		//assume first value is minimum
		min = keyboard.nextInt();
		
		//put next value in temp
		temp = keyboard.nextInt();
		
		//if temp is smaller, put it in min & shift min to middle
		if (temp < min){
			middle = min;
			min = temp;
		}
		
		//read 3rd value to temp
		temp = keyboard.nextInt();
		
		/*
		 * trickier logic: there's 3 possibilities here
		 * a) the 3rd value is the smallest
		 * b) the 3rd value is the middle value
		 * c) the 3rd value is the largest
		 */
		
		//if temp is less than min, it's the smallest value; shift all values up
		if (temp < min){
			max = middle;
			middle = min;
			min = temp;
		}
		
		//if temp is the middle value, shift middle up & assign temp to middle
		else if (temp > min && temp < middle){
			max = middle;
			middle = temp;
		}
		
		//if temp is the largest value, fill max with it
		else if (temp > middle){
			max = temp;
		}
		
		System.out.println(min + " " + middle + " " + max);
		
	}
	
		/**
		 * Practice 3
		 * Purpose: prompt for a day of the week & print a corresponding menu
		 * Assumptions: must error check input
		 * Restrictions: none
		 */
	public static void Practice3(){
		String day, menu;
		day = menu = null;
		
		System.out.println("Menu for which day?");
		
		//theres multiple ways to ignore case, I will handle that here
		//day is now all lowercase
		day = keyboard.nextLine().toLowerCase();
		
		switch(day){
		case "monday"    : menu = "Meatloaf"; 	        break;
		case "tuesday"   : menu = "Papa John's";        break;
		case "wednesday" : menu = "Red Beans and Rice"; break;
		case "thursday"  : menu = "Pasta"; 				break;
		case "friday"    : menu = "Fish"; 				break;
		case "saturday"  : menu = "Chicken"; 			break;
		case "sunday"    : menu = "Roast Beef"; 		break;
		
		default: 
			System.out.println("I DID NOT UNDERSTAND THAT, GOODBYE");
			System.exit(0);
			break;
		}
	
		System.out.println(day.substring(0, 1).toUpperCase() + day.substring(1) + ": " + menu);	
		
	}
	
		
	public static void Practice4(){
		
		
	}
}
