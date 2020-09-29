
/*[CSCI1301].java
 * Author: [Sifat Bhuiyan]
 * Submission Date :[02/23/18]
 *  Purpose: A brief paragraph description of the program
* This program plays rock paper scissor
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
 *
 */
import java.util.Scanner;
public class RockPaperScissors {

	public static void main(String[] args) {
	System.out.println("Points To win");
	Scanner keyboard = new Scanner (System.in);
	int PointstoWin = keyboard.nextInt();
	int yourScore = 0;
	int computerScore= 0;
	int Score= 0;
	
	while (yourScore < PointstoWin && computerScore<PointstoWin) {
	System.out.println("Rock, Paper or Scissors?");
	Scanner inputOne = new Scanner(System.in);
	String FirstInput = inputOne.nextLine();
	String ComputerMove= ComputerOpponent.getMove();
	if ( FirstInput.equalsIgnoreCase(ComputerMove)) {
		System.out.println("The Computer chose "+ComputerMove+" It's a tie "+ yourScore+"-"+ computerScore);
		Score = 0;}
	else if(FirstInput.equalsIgnoreCase("Rock") && ComputerMove.equalsIgnoreCase("Paper")) {
			computerScore++;
			System.out.println("The Computer chose "+ComputerMove+" so you lose " + yourScore+"-"+ computerScore);}
		else if (FirstInput.equalsIgnoreCase("Rock") && ComputerMove.equalsIgnoreCase("Scissors")) {
				yourScore ++;
			System.out.println("The Computer chose "+ComputerMove+" so you win! " + yourScore+"-"+ computerScore);}
		else if (FirstInput.equalsIgnoreCase("Paper") && ComputerMove.equalsIgnoreCase("Rock")) {
			yourScore ++;
			System.out.println("The Computer chose "+ComputerMove+" so you win! " + yourScore+"-"+ computerScore);}
		else if (FirstInput.equalsIgnoreCase("Paper") && ComputerMove.equalsIgnoreCase("Scissors")) {
			computerScore++;
			System.out.println("The Computer chose "+ComputerMove+" so you lose " + yourScore+"-"+ computerScore);}
		else if (FirstInput.equalsIgnoreCase("Scissors") && ComputerMove.equalsIgnoreCase("Rock")) {
			computerScore++;
			System.out.println("The Computer chose "+ComputerMove+" so you lose " + yourScore+"-"+ computerScore);}
		else if (FirstInput.equalsIgnoreCase("Scissors") && ComputerMove.equalsIgnoreCase("Paper")) {
			yourScore++;
			System.out.println("The Computer chose "+ComputerMove+" so you win" + yourScore+"-"+ computerScore);}
	
	}
	
	
		

	}

}
