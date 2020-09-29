/*
* [Palindrome].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Feb 23 2016] *
* 
*	Purpose: This program is designed to read numbers inputted 
*				and determine if the number is a palindrome.
*				If the number is a palindrome, then display the sum
*				of all numbers below it, if not then terminate.
*				
*  *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */ 


import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		
		
		int summation = 0;
		int count = 0;
		int reverseNumber = 0;
		boolean inputValid = false;
		int number = -1;
		int result = 0;
		int remainder = 0;
		
		do {
		try {
			System.out.print("Please enter an integer > 0: ");
			Scanner keyboard = new Scanner(System.in);
			number = keyboard.nextInt();
			
			if (number <= 0){
				System.out.println("Sorry, you must enter an integer greater than zero.");
				inputValid = false;
			} 
			else if (number > 0){
				inputValid = true;
			}
			
		
		}
		catch (Exception e)
		{
			System.out.println("Invalid input");
			inputValid = false;
		}
		} while (inputValid == false);
		
		if (number < 10){
			
			do {
				
				summation = summation + (count + 1); //count starts at 0
				count +=1 ;
			} while (count < number); //count initialized to zero, so not include equal to
			
			System.out.println("The number " + number + " is a palindrome.");
			System.out.println("The sum of the numbers from 1 to " + number + " is " + summation);
			System.exit(0);
		} //end if single number
		
		//System.out.println("number is " + number); //check point
		int originalNumber = number;
		
		while (number > 0){ //once all digits are taken, number will be "empty"
			remainder = number % 10; //takes 1s digit --> remainder
			number /= 10; //removes 1s digit from number --> number
			reverseNumber = reverseNumber * 10 + remainder; //moves all numbers higher and puts next number on end
		}
	
		
		//System.out.println("reverse is " + reverseNumber); //checkpoint
		
		if (originalNumber == reverseNumber){
			
			do {
				summation = summation + (count + 1); //count starts at 0
				count +=1 ;
			} while (count < originalNumber); //count initialized to zero, so not include equal to
			
			System.out.println("The integer " + originalNumber + " is a palindrome.");
			System.out.println("The sum of the numbers from 1 to " + originalNumber + " is " + summation);
		}
		
		else {
			System.out.println("The integer " + originalNumber + " is not a palindrome.");
		}
	}

}
