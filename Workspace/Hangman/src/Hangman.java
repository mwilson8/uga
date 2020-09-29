/*
* [Hangman2].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Mar 3 2016] 
* 	Last Updated: 	 [Mar 3 2016]
* 
*	Purpose: This program is designed to simulate a game of hangman
*				where a word is created and the user has a limited
*				number of guesses to solve the word. Unlike normal
*				hangman, the user must also specify which spaces in
*				the word they want to check. 3 difficulty levels 
*				control how many letter & spaces can be guessed at
*				a time. The program runs until the user doesn't want
*				to play anymore, or the word bank runs out (20 words)
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

public class Hangman {
	public static final boolean testingMode = true;
	
	// Brad challenged me to do it in a few lines as possible
	// crammed all together, I can get it into 80 lines, but readability suffer slightly
	// spaced out it's closer to 120, not counting comments
	
	public static void main(String[] args) {
		
		int spaces = 2, guesses = 10, gamesPlayed, indexGuess = 0;
		boolean wantsToKeepPlaying = true;
		
		Scanner keyboard = new Scanner(System.in);
		for (gamesPlayed = 1; (gamesPlayed <= 20 && wantsToKeepPlaying == true); gamesPlayed++) {
			// main program loop: executes as long as there's words left (gamesPlayed <= 20) &&
			// the user wants to continue (wantsToKeepPlaying == true)
			
			boolean difficultyCheck = true;
			do {
				System.out.println("Select a dificulty: Easy (e)," + " Intermediate (i)," + " Hard (h): ");
				
				String difficulty = keyboard.nextLine();
				
				if (difficulty.charAt(0) == 'h' || difficulty.charAt(0) == 'i' || difficulty.charAt(0) == 'e') {
					if (difficulty.charAt(0) != 'h') {
						guesses = (difficulty.charAt(0) == 'i') ? 12 : 15;
						spaces = (difficulty.charAt(0) == 'i') ? 3 : 4;
						// if the difficulty starts with 'h', 'i', or 'e' go into the conditional
						// guesses & spaces are initialized for hard, so conditional handles easy & intermediate
					}
				} else {
					System.out.println("Invalid difficulty.");
					difficultyCheck = false;
						// if difficulty doesn't start with 'h', 'i', or 'e', 
						// it's invalid, and we return to check again
				}
			} while (difficultyCheck == false);
				// difficulty check (do-while) loop executes until difficultyCheck == true
			
			String secretWord = RandomWord.newWord(), displayWord = "";
			
			int guessesRemaining = guesses;
			
			for (byte count2 = 0; count2 < secretWord.length(); count2++)
				displayWord = displayWord + "-";
			// this loop converts secret word to dashes
			
			if (testingMode == true)
				System.out.println("Secret word is: " + secretWord);
			
			System.out.println("The word is: " + displayWord);
			
			while (guessesRemaining > 0 && !displayWord.equals(secretWord)) {
				// main game loop executes as long as the user has guesses remaining &&
				// they haven't guesses the secret word yet
				
				String guessString;
				char guessChar = ' ';
				boolean solveGuessCorrect = false, guessIsSolve = false, inputIsALetter = false;
				while (inputIsALetter == false) {
					// letter guess validation loop
					// executes until inputIsALetter == true
					
					System.out.print("Please guess a letter or \"solve\": ");
					guessString = (keyboard.nextLine()).toLowerCase();
					guessChar = guessString.charAt(0);
					if (Character.isDigit(guessChar)) {
						// if the guess is a number, return and re-ask
						
						inputIsALetter = false;
						System.out.println(
								"\n The guess must be a letter or \"solve\" \nGuesses Remaining: " + guessesRemaining);
					} else if (guessString.equals("solve")) {
						// if guess is "solve", exit this loop (inputIsALetter = true)
						// and set guessIsSolve = true, to skip over spaces loop
						
						guessIsSolve = true;
						solveGuessCorrect = false;
						inputIsALetter = true;
						System.out.println("\n Solve the word: ");
						String solveGuess = (keyboard.nextLine()).trim();
						solveGuessCorrect = (solveGuess.equals(secretWord) ? true : false);
						// conditional to check if solve guess is correct
						
					} else
						inputIsALetter = true;
						// if input is not a number, or solve, it must be a letter or a word
						// words are okay, we just take the first letter
				}
				System.out.println("What spaces do you want to check?");
				String spacesString = "";
				boolean spacesCheckPassed = false;
				while (spacesCheckPassed == false) {
					// spaces validation loop executes until spacesCheckPassed == true
					
					System.out.println("Please enter " + spaces
							+ " single-digit numbers seperated by single spaces \n The values of the spaces cannot be longer than the word");
					spacesString = keyboard.nextLine().trim();
					spacesString = spacesString + " ";
					// take the input string, trim & add a space to the end
					// need space on the end for following loop, because we check 2 at a time, unconditionally
					
					boolean keepChecking = true;
					for (int index = 0; index < spacesString.length() && keepChecking; index += 2) {
						// keep checking loop continues to execute if the first round of checks passes
						// otherwise, exit this loop, return and re-ask for valid input
						// in this loop, we check 2 chars at a time: char at 'index' should be a number
							// char at 'index + 1' should be a space
						
						if (!Character.isDigit(spacesString.charAt(index)) || (spacesString.charAt(index + 1) != ' ')
								|| Character.getNumericValue(spacesString.charAt(index)) > secretWord.length() - 1
								|| spacesString.length() > (spaces * 2))
							// if first char is not a number 
							// || second char is not a space 
							// || first char value is longer than secret word length
							// || the entire spaces string is longer than # of space-guesses allowed
							
							keepChecking = false;
							// if there's 1 part that doesn't pass, exit and re-ask for input
							// otherise, check the next 2 chars (increase index by 2)
					}
					if (keepChecking)
						spacesCheckPassed = true;
					// if keepChecking == true, it means we exitied the check loop becuase we checked the whole word
					// in that case, the spaces check passed so: spacesCheckPassed = true
					
				}
				boolean guessInWord = false;
				for (int indexForCountLoop = 0, count1 = 0; count1 < spaces && !guessIsSolve; indexForCountLoop += 2, count1++) {
					// this loop checks if the letter guessed is in the word
					// continues to execute until we've checked the entire word
					// executes if the guess is not "solve"
					
					indexGuess = Character.getNumericValue(spacesString.charAt(indexForCountLoop));
					// indexGuess is the numeric value of the digit we're checking (initialized to 0)
					
					if (secretWord.charAt(indexGuess) == guessChar) {
						guessInWord = true;
						displayWord = displayWord.substring(0, indexGuess) + guessChar
								+ displayWord.substring(indexGuess + 1);
						// if the letter is at the index in the secret word, flip the letter in display word
					}
				}
				if (guessIsSolve == false) {
					// as long as the guess isn't "solve", come here
					// this is pretty self explanatory
					
					if (guessInWord == true)
						System.out.println("Your guess is in the word!");
					else {
						System.out.println("Your guess was not found in the spaces provided.");
						guessesRemaining--;
					}
					System.out.println(
							"The updated word is: " + displayWord + "\n Guesses remaining: " + guessesRemaining);
				}
				if (guessIsSolve == true) {
					if (solveGuessCorrect == true) {
						// solveGuessCorrect was handled by a conditional above
						
						System.out.println("You guessed the word");
						displayWord = secretWord;
						
					} else if (solveGuessCorrect == false) {
						guessesRemaining--;
						// must reset these two in order for the validation loops to execute again
						inputIsALetter = false;
						guessIsSolve = false;
						System.out.println("That's not the word \n Guesses remaining: " + guessesRemaining);
					}
				}
			}
			boolean keepPlayingCheck = false;
			while (keepPlayingCheck == false) {
				// this loop keeps executing until keepPlayingCheck == true
				
				if (displayWord.equals(secretWord))
					System.out.println("You win! \n Play again? Yes(y) or No(n)");
				else if (guessesRemaining == 0)
					System.out.println(
							"You have failed to guess the word and are out of guesses. \n Play again? Yes(y) or No(n)");
				String keepPlayingBlank = keyboard.nextLine();
				if (keepPlayingBlank.charAt(0) == 'y' || keepPlayingBlank.charAt(0) == 'n') {
					// if the input starts with either 'y' or 'n', come here
					
					wantsToKeepPlaying = (keepPlayingBlank.charAt(0) == 'y') ? true : false;
					keepPlayingCheck = true;
					// since we know it starts with 'y' or 'n', conditional can handle it
					// and the input is valid
					
				} else
					keepPlayingCheck = false;
					// if it doesn't start with 'y' or 'n', go back and re-ask for valid input
			}
		}
		if (gamesPlayed == 20)
			System.out.println("Only 20 games are allowed in one run of the program");
		// if we are out of games, wantsToKeepPlaying is irrelevant
		// if we are out of games, say so and exit
		
		else if (wantsToKeepPlaying == false)
			System.out.println("Thank you for playing. Goodbye.");
		// if we have games left but they don't want to play again,
		// say so and exit
		
		System.exit(0);
	}
}
