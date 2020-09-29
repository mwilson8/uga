/*
 * ComputerScience1301.java
 * Author: Davielle Matos 
 * Submission Date: 4/6/18
 * 
 *  Purpose: The purpose of this program is to learn how to use classes in the main method
 *  to reduce how much code is in the main.
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
import java.util.Scanner;
public class Bagels {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Player player1 = new Player();
		Engine engine1 = new Engine();

//		while(true)
//		{
			System.out.println("Welcome!");
			
			System.out.print("Enter the number of digits to use: ");
			
			// i need to save the methods into variables *****************************************************************
			engine1.setNumDigits(keyboard.nextInt());
			
			System.out.print("Enter the player's name: ");
			keyboard.nextLine();
			player1.setName(keyboard.nextLine());
			
			String guess = player1.askForGuess();
			
			int [] guessArray = engine1.convertNumtoArray(guess);
			
			engine1.generateNewSecret();
			System.out.println();
			
			for(int i = 0; i < guessArray.length; i ++)
			{
				System.out.print(guessArray[i]);
			}
			System.out.println();	
			int [] secretArray = engine1.getSecretNumber();
			
			for(int i = 0; i < secretArray.length; i ++)
			{
				System.out.print(secretArray[i]);
			}
		//}
	// HOW TO VALIDATE GUESS ***********************************************************************************************
		// store the guesses into a variable
		
			//Validator.validateGuess(secret, guess, numDigits);
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
