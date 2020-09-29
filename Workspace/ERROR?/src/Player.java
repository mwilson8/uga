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
//import java.io.InputStream;
import java.util.Scanner;
public class Player {
	
// Declaring the private variables in the class
	private String Name;
	private int fastestWin;
	private int gamesCompleted;
	
	
// ----------------------- Methods ------------------- //

// AskForGuess
	public String askForGuess()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter your guess: ");
		String guess = keyboard.nextLine();
		keyboard.nextLine();
		return guess;
	}
	

// getName  
	public String getName()
	{
		return Name;
	}
	
// setName
	public void setName (String temp)
	{
		this.Name = temp;
	}
	
// getFastestWin
	public int getFastestWin()
	{
		return fastestWin;
	}
	
// getGamesCompleted
	public int getGamesCompleted()
	{
		return gamesCompleted;
	}
	
// UpdatingStats
	public void UpdateStats(int temp)
	{
		// FASTEST WIN NEEDS TO BE UPDATED BY PROBABLY AN IF STATEMENT BUT IDK
		//this.fastestWin =  ******************************************************************************************************************
		this.gamesCompleted++;
		
	}
	
	
	
	
	
	
}
