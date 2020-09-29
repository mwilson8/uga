
/**

*
* [TemperatureConverter].java
* ~~Lab 5~~
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Feb 16 2016] *
* 
*	Purpose: This program was given out with errors and I was tasked
*				with debugging and returning an error-free program,
*				The code below is the result of the de-bugging.
*					
*		-->  This program does the following:
				Prompts the user to enter a temperature in
		  		Fahrenheit and a temperature scale the user
			  	wants to convert it to. If both the temperature
			  	in Fahrenheit  and the temperature scale are 
				valid, the program converts the temperature in 
			  	Fahrenheit to the specified temperature scale 
			  	and displays the converted value.

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

public class TemperatureConverter {

	// Declare named constants.

	public static final double MIN_FAHRENHEIT = -459.67;

	public static void main(String[] args) {

		// Declare the variables.
		int tempScale = 4;
		double fahrenheit = 32;
		String tempScaleStr = "";
		double convertedDegrees = 0.00;

		// Creating the Scanner object
		Scanner keyboard = new Scanner(System.in);

		// Ask for and receive input (F value)
		System.out.print("Enter the temperature in Fahrenheit: ");
		fahrenheit = keyboard.nextDouble();

		// Validate the user's input
		if (fahrenheit < MIN_FAHRENHEIT) {
			System.out.print("The temperature must be greater than or equal to " + MIN_FAHRENHEIT);
			System.exit(0);
		}

		// Ask for and receive input (scale)
		System.out.print("Enter the temperature scale you want to convert to:\n" + "1. Kelvin \n" + "2. Rankine \n"
				+ "3. Reaumur \n" + "4. Celsius\n" + "Enter a temperature scale: ");
		tempScale = keyboard.nextInt();

		// Validate input
		if (tempScale < 1 || tempScale > 4) {
			System.out.println("Unknown temperature scale - cannot do calculation. Bye");
			System.exit(0);
		} else {
			// remember min fahrenheit is negative
			// logic and math for conversions

			if (tempScale == 1) {
				convertedDegrees = (fahrenheit - MIN_FAHRENHEIT) * 5 / 9;
				tempScaleStr = "Kelvin";

			} else if (tempScale == 2) {
				convertedDegrees = fahrenheit - MIN_FAHRENHEIT;
				tempScaleStr = "Rankine";

			} else if (tempScale == 3) {
				convertedDegrees = (fahrenheit - 32) * 4 / 9;
				tempScaleStr = "Reaumur";

			} else {
				convertedDegrees = (fahrenheit - 32) * 5 / 9;
				tempScaleStr = "Celsius";
			}

			// Print results with description
			System.out.println(
					fahrenheit + " degrees Fahrenheit is " + convertedDegrees + " degrees " + tempScaleStr + ".");

			System.exit(0);
		}
	}
}