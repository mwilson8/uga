/*
* Palindrome.java
* Author: Amogh Phadke
* Submission Date: 9/27/16
*
* Purpose: The program checks if a number is the
* same when it is reversed. If it is, the number is summed.
*
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
CSCI 1301: Project 1 Page 3
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
public class Palindrome3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int reverse = 0;
		int sum = 0;
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Please enter an integer > 0: ");
		
		int firstNum = scan.nextInt();
		int num = firstNum;
		
		if (num <= 0) {
			System.out.println("Sorry. The integer must be greater than 0");
			return;
		}
		else
		{
			while(num > 0)
			{
				int digit = num % 10;
			    reverse = reverse * 10 + digit;
			    num = num / 10;
			}
			
			if(reverse == firstNum)
			{
				System.out.println("The integer " + firstNum + " is a palindrome" );
				
				for(int i = 0;i <=firstNum;i++)
				{
					sum += i;
				}
				System.out.println("The sum of the numbers from 1 to " + firstNum + " is  " + sum);
			}
			else
			{
				System.out.println("The integer " + firstNum + " is not a palindrome");
			}
		}
		
		
		

	}

}
