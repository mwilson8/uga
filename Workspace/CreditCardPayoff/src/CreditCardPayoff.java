/*
* [CreditCardPayoff].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Feb 2 2016] *
* 
*	Purpose: This program is designed to compute the amount of time and 
*				money it would take to pay off a credit card payment,
*				given the principal amount, annual interest rate, and
*				monthly payments made.
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
import java.lang.Math;

public class CreditCardPayoff {

	public static void main(String[] args) {
		
		double amountPaid;
		double overpayment;
		double interestPaid;
		double monthsNeeded;
		double monthlyPayment;
		double principalAmount;
		double annualInterestRate;
		int    monthsNeededCeiling;
		double monthsNeededNumerator;
		double monthsNeededDenominator;
		
		
		//prompts for principal value; stores --> principalAmount
		System.out.print("Principal:" + "\t\t\t");
		Scanner principal = new Scanner(System.in);
		principalAmount = principal.nextDouble();
		
		//prompts for interest rate; stores --> annualInterestRate
		System.out.print("Annual Interest Rate (%):" + "\t");
		Scanner annualRate = new Scanner(System.in);
		annualInterestRate = annualRate.nextDouble();
		
		//prompts for monthly payment; stores --> monthlyPayment
		System.out.print("Monthly Payment:" + "\t\t");
		Scanner monthPay = new Scanner(System.in);
		monthlyPayment = monthPay.nextDouble();
		
		//calculation for months needed, seperated into num, den, & final
		monthsNeededNumerator = (Math.log(monthlyPayment) - Math.log(monthlyPayment - (annualInterestRate/1200.0) * principalAmount));
		monthsNeededDenominator = Math.log((annualInterestRate / 1200.0) + 1.0);
		monthsNeeded = monthsNeededNumerator / monthsNeededDenominator;

		//System.out.println(monthsNeededNumerator);
		//System.out.println(monthsNeededDenominator);
		//System.out.println(monthsNeeded);
		System.out.println("");
		
		//rounds monthsNeeded up to the next whole number & truncates decimal
		monthsNeededCeiling = (int)Math.ceil(monthsNeeded);
		
		//here down is calculations & value output
		System.out.print("Months Needed to Pay Off:" + "\t" + monthsNeededCeiling);
		System.out.println("");
		
		amountPaid = monthsNeededCeiling * monthlyPayment;
		interestPaid = amountPaid - principalAmount;
		
		/*
		 * because sometimes you can't make payments evenly to pay off your loan
		 * i.e. if your amount due was $1250, and your monthly payment is $100,
		 * mathematically you need 12.5 months to pay it off but you can't pay for
		 * 1/2 of a month, so you'd have to pay 13 months ($1300). your overpayment is $50
		 * formula is below..
		 */
	
		overpayment = amountPaid - (monthsNeeded * monthlyPayment);
		
		
		System.out.printf("Total Amount Paid:\t\t$%.2f \n", amountPaid);
		System.out.printf("Total Interest Paid:\t\t$%.2f \n", interestPaid);
		System.out.printf("Overpayment:\t\t\t$%.2f \n", overpayment);

		
		
	}	
	

}
