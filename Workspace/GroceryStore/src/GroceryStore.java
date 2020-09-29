/*
* [GroceryStore].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Jan 28 2016] *
* 
*	Purpose: This program is designed to compute the amount of money a
*  	person would owe, given the items they purchased from a menu list.
*  	The prices of the items are multiplied by the user's "shopping list"
*  	to compute the pre-tax amount owed. Tax is then added and both values
*  	are displayed. 
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

public class GroceryStore {

		/*1*/  public static final double BANANA_PRICE  =  0.99;
		/*2*/  public static final double APPLE_PRICE   =  1.85;
		/*3*/  public static final double YOGURT_PRICE  =  2.98;
		/*4*/  public static final double ORANGE_PRICE  =  1.25;
		/*5*/  public static final double GRANOLA_PRICE =  3.00;
		/*6*/  public static final double LETTUCE_PRICE =  2.20;
		/*7*/  public static final double KETCHUP_PRICE =  0.78;
		/*8*/  public static final double PEANUT_PRICE  =  3.05;
		/*9*/  public static final double CHICKEN_PRICE =  6.58;
		/*10*/ public static final double SEAFOOD_PRICE = 13.99;
		
		
	public static void main(String[] args) {
			
			int bananas, apples,  yogurt,  oranges, granola, 
				lettuce, ketchup, peanuts, chicken, seafood;
			
			double total;
			
		//welcome + menu follows
			
		System.out.println("Welcome to my store!");
		System.out.println("The menu is below:");
		
		System.out.println(""); //blank line before menu
		
		/*1*/ System.out.println("1. Bananas \t $ " + BANANA_PRICE);
		/*2*/ System.out.println("2. Apples \t $ "  + APPLE_PRICE);
		/*3*/ System.out.println("3. Yogurt \t $ "  + YOGURT_PRICE);
		/*4*/ System.out.println("4. Oranges \t $ " + ORANGE_PRICE);
		/*5*/ System.out.print  ("5. Granola \t $ ");
				System.out.printf("%.2f", GRANOLA_PRICE);
		
		System.out.println(""); //line skip after formatting price
		
		/*6*/ System.out.print  ("6. Lettuce \t $ ");
				System.out.printf("%.2f", LETTUCE_PRICE);
		
		System.out.println(""); //line skip after formatting price
		
		/*7*/ System.out.println("7. Ketchup \t $ " +  KETCHUP_PRICE);
		/*8*/ System.out.println("8. Peanuts \t $ " +  PEANUT_PRICE);
		/*9*/ System.out.println("9. Chicken \t $ " +  CHICKEN_PRICE);
		/*10*/System.out.println("10.Seafood \t $" +   SEAFOOD_PRICE);
		
		System.out.println(""); //line between menu & input 
		
		System.out.println("Enter the number of each item you would "
				+ "like to purchase seperated by spaces:");

		// opens input, stores to corresponding variables
		Scanner keyboard = new Scanner(System.in);
			bananas = keyboard.nextInt();
			apples  = keyboard.nextInt();
			yogurt  = keyboard.nextInt();
			oranges = keyboard.nextInt();
			granola = keyboard.nextInt();
			lettuce = keyboard.nextInt();
			ketchup = keyboard.nextInt();
			peanuts = keyboard.nextInt();
			chicken = keyboard.nextInt();
			seafood = keyboard.nextInt();
		keyboard.close();
		
		// calculates pre-tax total
		total  =  (bananas * BANANA_PRICE ) 
				+ (apples  * APPLE_PRICE  ) 
				+ (yogurt  * YOGURT_PRICE )
				+ (oranges * ORANGE_PRICE )
				+ (granola * GRANOLA_PRICE)
				+ (lettuce * LETTUCE_PRICE)
				+ (ketchup * KETCHUP_PRICE) 
				+ (peanuts * PEANUT_PRICE ) 
				+ (chicken * CHICKEN_PRICE)
				+ (seafood * SEAFOOD_PRICE);
		
		
		int totalInt = (int)total; 
			// takes the whole part of the (pre-tax) total
		
		double totalDecimal = (total % totalInt) * 100;
			// takes the decimal part of the total,
			// converts to a double
			// moves decimal 2 places (old decimal is now 10s value)
		
		byte totalDecimal2 = (byte) totalDecimal; 
			// truncates the double 
			// produce a byte value of the decimal value
			// old decimal value is now a 2 digit 10s number
		
		double finalPrice = total * 1.07; 
			// computes total plus tax
		
		// repeats steps from above for after-tax value
		int finalInt = (int)finalPrice; 
			
		double finalDecimal = (finalPrice % finalInt) * 100; 
		
		byte finalDecimal2 = (byte) finalDecimal; 

		// output1 is for pre-tax; output2 for post-tax
		String output1="output";
		String output2="output";
		
		
		if (total < 0.01) {
			output1 = "0.00";
		} else output1 = totalInt + "." + totalDecimal2; 
			// combines the 2 values from above 
			// puts a decimal between them
			// if the total is zero, outputs "0.00"
		
		if (total < 0.01) {
			output2 = "0.00";
		} else output2 = finalInt + "." + finalDecimal2;
			// same as output1 	
		
		
		// prints results after "if" statement 
		
		System.out.println("Total owed before tax is:"
								+ "\t" + "$" + output1);
		
		System.out.println("Total owed after tax is:" 
								+ "\t" + "$" + output2);
		
		// it may appear to be tabbed a funny in the viewer,
		// it was so I wouldn't have to scroll L/R to see everything 
		
		
	}

}
		

