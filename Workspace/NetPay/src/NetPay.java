

/*
* [NetPay].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Jan 26 2016] *
* 
*	Purpose: This program is designed to compute the amount of money a
*  	person would earn, given the hours they worked in a week (from the user)
* 	The program takes into account certain deductions, such as 
* 	federal & state taxes along with medicare and Social Security with-
* 	holdings. The gross pay is the output, along with all deductions 
* 	and the net pay
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

public class NetPay {

	//order of these words doesn't really matter.. typically static comes before final
	public static final double FEDERAL_TAX_PERCENT = 10.00;
	public final static double STATE_TAX_PERCENT = 4.5;
	public static final double SS_PERCENT = 6.2;
	public static final double MEDICARE_PERCENT = 1.45;
	public static final double PAY_PER_HOUR = 7.25;
	
		public static void main(String[] args) {

			
			System.out.print("Hours per week: "); 

			Scanner keyboard = new java.util.Scanner(System.in);
			double hoursPerWeek = keyboard.nextDouble();
			keyboard.close();
			
			double grossPay = hoursPerWeek*PAY_PER_HOUR;
			double federalTax = grossPay*(FEDERAL_TAX_PERCENT / 100);
			double stateTax = grossPay*(STATE_TAX_PERCENT / 100);
			double ss_Tax = grossPay*(SS_PERCENT / 100);
			double medTax = grossPay*(MEDICARE_PERCENT / 100);
			double netPay = grossPay - federalTax - stateTax - ss_Tax - medTax;
			
			System.out.printf("Gross pay:\t\t$%.2f \n",grossPay);
			System.out.printf("Net pay:\t\t$%.2f \n\n",netPay);
			System.out.println("Deductions:");
			System.out.printf("Federal:\t\t$%.2f \n",federalTax);
			System.out.printf("State:\t\t\t$%.2f \n",stateTax);
			System.out.printf("Social Security:\t$%.2f \n",ss_Tax);
			System.out.printf("Medicare:\t\t$%.2f \n",medTax);

			
		}

}


