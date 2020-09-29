/*
* WordSearch.java
* Author: Jeremy Zou
* Submission Date: 25 April 2018
*
* Purpose: This class creates a 2 dimensional board of letters
* using a seed and searches through it for words from a dictionary.
* When found, the word is uppercased and outputted.
*
* Statement of Academic Honesty:
*
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
* of Computer Science at the University of Georgia.
*/
import java.util.Scanner;
public class WordSearch {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		//gets the seed from the user
		long seed = promptUserForSeed(keyboard);
		//calls findWords and stores the board
		WordBoard board = findWords(10, 10, seed);
		
		System.out.println(board);
		System.out.println();
		//calls each discovered word
		for (String word : board.getDiscoveredWords()) {
			System.out.println(word);	
		}
		System.out.println();
		board.checkAnswers();

	}

	public static long promptUserForSeed(Scanner input) {
		System.out.println("Enter the seed for a 10x10 board:");
		return input.nextLong();
	}

	public static WordBoard findWords(int rows, int cols, long seed) {
		//creates a new board using the parameters
		WordBoard board = new WordBoard(rows, cols, seed);

		//stores the board as an array of chars called boardArr
		char[][] boardArr = board.getBoard();

		//creates an array that contains each possible word in the board
		String[] dictionary = board.getDictionary();

		//for each word in the dictionary array
		for (String word : dictionary) {

			boolean isWordFound = false;
			String testWord = "";

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					testWord = "";

					if (boardArr[i][j] == word.charAt(0)) {
						//checks right
						for (int k = 0; k < word.length(); k++) {

							if (j + k < board.getWidth()) {
								testWord = testWord + boardArr[i][j + k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i, j + k);
							}
						}

						testWord = "";
						//checks down right
						for (int k = 0; k < word.length(); k++) {

							if (i + k < board.getHeight() && j + k < board.getWidth()) {
								testWord = testWord + boardArr[i + k][j + k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i + k, j + k);
							}
						}
	
						testWord = "";
						//checks down
						for (int k = 0; k < word.length(); k++) {

							if (i + k < board.getHeight()) {
								testWord = testWord + boardArr[i + k][j];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i + k, j);
							}
						}

						testWord = "";
						//checks down left
						for (int k = 0; k < word.length(); k++) {

							if (i + k < board.getHeight() && j - k >= 0) {
								testWord = testWord + boardArr[i + k][j - k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i + k, j - k);
							}
						}

						testWord = "";
						//checks left
						for (int k = 0; k < word.length(); k++) {

							if (j - k >= 0) {
								testWord = testWord + boardArr[i][j - k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i, j - k);
							}
						}

						testWord = "";
						//checks up left
						for (int k = 0; k < word.length(); k++) {

							if (j - k >= 0 && i - k >= 0) {
								testWord = testWord + boardArr[i - k][j - k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i - k, j - k);
							}
						}

						testWord = "";
						//checks up
						for (int k = 0; k < word.length(); k++) {

							if (i - k >= 0) {
								testWord = testWord + boardArr[i - k][j];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i - k, j);
							}
						}

						testWord = "";
						
						//checks up right
						for (int k = 0; k < word.length(); k++) {

							if (i - k >= 0 && j + k < board.getWidth()) {
								testWord = testWord + boardArr[i - k][j + k];
							}
							if (testWord.equals(word)) {
								isWordFound = true;
								board.highlightWord(i, j, i - k, j + k);
							}
						}

						testWord = "";
					}
				}
			}
		}
		return board;
	}
}
