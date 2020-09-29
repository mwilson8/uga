/*
* [StarGraph].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Mar 14 2016] 
* 	Last Updated: 	 [Mar 15 2016]
* 
*	Purpose: This program is designed as a first introduction to arrays.
*				Its purpose is to ask the user for a definite amount of
*				numbers, a starting value, and an increment value. From
*				there, the program populates 2 arrays. The first is X
*				values based on the user's input. The seccond is Y values
*				which are calculated by running a formula over the first 
*				array. After both arrays are populated, a horizontal graph
*				is created using the y-value array. 
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
* of Computer Science at the University of Georgia.

import java.util.Scanner;

public class StarGraph {

	public static void main(String[] args) {

		double xMin, increment;

		String xLength = "";

		Scanner keyboard = new Scanner(System.in);
		boolean xLengthCheck = false;
		while (xLengthCheck == false) {
			System.out.print("Please enter the number of x values to process: ");
			xLength = keyboard.nextLine().trim();
			boolean checkNextNumber = true;
			for (int count = 0; count < xLength.length() && checkNextNumber; count++) {
				// for loop checks every character in the string
				// only non zero numbers are allowed
				if (!Character.isDigit(xLength.charAt(count)) || Character.getNumericValue(xLength.charAt(0)) == 0) {
					System.out.println("\nThe number of x values must be an integer greater than 0.");
					xLengthCheck = false;
					checkNextNumber = false;
				}

				else
					xLengthCheck = true;
			}

		}
		int xSize = Integer.parseInt(xLength);

		double[] xArray = new double[xSize];
		double[] yArray = new double[xSize];
		
		String xMinString = "";
		boolean xMinCheck = false;
		while (xMinCheck == false) {
			System.out.print("Enter a minimum value for x: ");
			xMinString = keyboard.nextLine().trim();
			boolean checkNextNumber = true;
			for (int count = 0; count < xMinString.length() && checkNextNumber; count++) {
				// for loop checks every character in the string
				// only numbers and a decimal point are allowed
				if (!Character.isDigit(xMinString.charAt(count)) && xMinString.charAt(count) != '.' && xMinString.charAt(count) != '-') {
					System.out.println("\nThe minimum value must be a valid decimal number.");
					xMinCheck = false;
					checkNextNumber = false;
				}

				else
					xMinCheck = true;
			}			
		}
		
		xMin = Double.parseDouble(xMinString);
		double xValue = xMin;
		String incrementString = "";
		boolean incrementCheck = false;
		while (incrementCheck == false) {
			System.out.print("Enter a value to increment x: ");
			incrementString = keyboard.nextLine().trim();
			boolean checkNextNumber = true;
			for (int count = 0; count < incrementString.length() && checkNextNumber; count++) {
				// for loop checks every character in the string
				// only numbers and a decimal point are allowed
				if (!Character.isDigit(incrementString.charAt(count)) && incrementString.charAt(count) != '.') {
					System.out.println("\nThe increment value must be a decimal number greater than 0.");
					incrementCheck = false;
					checkNextNumber = false;
				}

				else
					incrementCheck = true;
			}
			if (checkNextNumber && Double.parseDouble(incrementString) < .0000000001)
			{
				System.out.println("\nThe increment value must be a decimal number greater than 0.");
				incrementCheck = false;
			}
				
		}

		System.out.println("\nValues");
		increment = Double.parseDouble(incrementString);
		for (int count = 0; count < xSize; count++) {
			// this loop populates both arrays and displays them
			xArray[count] = xValue;
			yArray[count] = 20.0 * Math.abs(Math.sin(xArray[count]));
			System.out.printf("x: %1.3f, y: %1.3f \n", xArray[count], yArray[count]);
			xValue += increment;
		}

		System.out.println("\nGraph");
		for (int count = 0; count < xSize; count++) {
			System.out.print(":");
			for (int count2 = 1; count2 < yArray[count]; count2++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

*/


/*
* StarGraph.java
* Author: Taylon Hammons
* tnh81007@uga.edu
* Submission Date: [3/15/2017]
*
* Purpose: This program takes user input, including the number of x's they want to process, the minimum value, and the increment
* to make two arrays and then graphs the arrays.
* 
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. 
* */


/*
* CSCI 1301.java
* Author: Mykelin Higham
* Submission Date: 3/14/17
*
* Purpose: Given a minimum value, increment value, and number of 
* times to increment, calculate the function of each value beginning
* at the minimum of 20*|sin(x)|. Then output the values of independent 
* and dependent variables to three decimal places, and chart the value
* of the independent variable.
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
/*
* CSCI 1301.java
* Author: Kyle Weiland
* Submission Date: 3/13/2017
*
* Purpose: This application takes the userís input of number of x
* values to process, minimum x value, increment amount, and returns 
* a list of x/y values and prints a text-based graph of the values.
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

/* StarGraph.java
 * Author: Makayla Davis
 * Submission Date: March 15, 2017
 * 
 * Purpose: The purpose of this lab is to take the user's input value, add
 * a desired increment to it, then store those values into an array. These x 
 * values are then plugged into a formula to create the y values used to
 * create the star graph.
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

/*
* StarGraph.java
* Author: [Fang Rong]
* Submission Date: [03172017]
*
*Purpose: This is a program for people to calculate the value of
* x and y. Also, it can also shows a list for star graph based on the value y. 
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
/*
* StarGraph.java
* Author: [Steven Thompson]
* Submission Date: [3/17/17]
*
* Purpose: This application will aid a student in using loops and and arrays. 
* This program uses two loops to get x and y values and prints a graph of '*'s to represent a graph
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
public class StarGraph {
	/**
	 * the main method
	 * 
	 * @param args
	 *            we do not use this
	 */
	public static void main(String[] args) {
		// imports scanner
		Scanner input = new Scanner(System.in);

		// prompts user for information to use for graph
		System.out.println("Please enter the number of x values to process: ");
		int process = input.nextInt();

		System.out.println("Enter a minimum value for x: ");
		double minValue = input.nextDouble();

		System.out.println("Enter the amount to increment x:");
		double increment = input.nextDouble();

		// stores a certain amount of arrays due to amount inputed
		double[] x = new double[process];
		double[] y = new double[process];

		double newNum = 0;

		System.out.println();
		System.out.println("Values");

		newNum = minValue;

		// loops to get the values of x and y
		for (int index = 0; index < x.length; index++) {
			if (index > 0) {
				newNum += increment;
				x[index] = newNum;
				y[index] = 20.0 * (Math.abs(Math.sin(newNum)));
			} else {
				x[index] = minValue;
				y[index] = 20.0 * (Math.abs(Math.sin(minValue)));
			}
			System.out.printf("x: %.3f, y: %.3f", x[index], y[index]);
			System.out.println();
		}

		System.out.println();
		System.out.println("Graph");

		// loops to create the graph
		for (int j = 0; j < x.length; j++) {
			int yPrint = (int) y[j];
			System.out.print(":");
			for (int i = 0; i <= yPrint; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// closes the scanner
		input.close();
	}

}
