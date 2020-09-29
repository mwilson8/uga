/*
 * ComputerScience1301.java
 * Author: Davielle Matos 
 * Submission Date: 4/6/18
 * 
 *  Purpose: The purpose of this program is to learn how to create classes and
 *  how to use them to create a program.
 *  
 *  Statement of Academic Honesty:
 *  
 *  The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage CSCI 1301
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the 
* Department of Computer Science at the University of Georgia.
*/
import java.util.Random;
import java.util.Scanner;
public class Engine {
	private int numDigits;
	private int secretNumber;
	private Random randomNumberGenerator = new Random();
	
	
// ----------------------- Methods ------------------- //
	public int [] convertNumtoArray(String temp)
	{
		int [] array = new int [(temp.length())];
		for(int i = 0; i < temp.length(); i++)
		{
			String letter = temp.substring(i, i+1);
			int num = Integer.parseInt(letter);
			array[i] = num;
		}
		
		return array;
	}
	
	public int getNumDigits()
	{
		return numDigits;
	}
	

	public int [] getSecretNumber()
	{
		// This is because the length of the int is based on the number of digits 
		int [] array = new int [numDigits]; 
		String temp = Integer.toString(secretNumber);
		
		// This will go to the last number and do % 10 then divide by 10 to get each individual number
		do { 
			for (int i = (numDigits - 1); i >= 0; i--)
			{
				array[i] = secretNumber % 10; 
				secretNumber /= 10;  
			}
		}
			while (secretNumber != 0); 
	
		return array;
	}
	
	public void generateNewSecret()
	{
		int min = (numDigits - 1);
		int max = numDigits;
		
		int maxRange = (int)Math.pow(10, max);
		
		int minRange = (int)Math.pow(10, min);
		
		//This is making the range of random numbers based on the user input from 
		// 0 to the max number and then adding the min range so that its randomized in the correct range
		int randomNum = (randomNumberGenerator.nextInt((maxRange - minRange)) + minRange);
		
		System.out.print(randomNum);
		secretNumber = randomNum;
	}
	
	public void setNumDigits(int temp)
	{
		numDigits = temp;
	}
	
// I STILL NEED  setSecretNumber(int[])	****************************************************************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
