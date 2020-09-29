import java.util.Scanner;
/*
* StringFun.java
* Author: Neelank Pathak
* Submission Date: March 3rd, 2017
*
* Purpose: This program will take a string an manipulate it based
* on the commands of the user
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
public class StringFun2 {

	public static void main(String[] args) {
		//Declare and initialize scanner object//
				Scanner keyboard = new Scanner(System.in);
				
				//Ask the user to enter a string//
				System.out.println("Enter the string to be manipulated");
				String originalString = keyboard.nextLine();
				String copyOriginalString = originalString; //copy is made in case original is altered//
				String manipulation = "";
				int x = 0;
				int y = 0;
				int z = 0;
				//create the overarching main loop and start with asking which command should be performed//
				while (manipulation.toLowerCase().equals("quit") || manipulation.toLowerCase().equals("print reverse") || manipulation.toLowerCase().equals("replace all") || 
						manipulation.toLowerCase().equals("replace single") || manipulation.toLowerCase().equals("remove"))
				{
					System.out.println("Enter your command (quit, print reverse, replace all, replace single, remove");
					manipulation = keyboard.nextLine();
					
					//Code for print reverse command//
					if (manipulation.toLowerCase().equals("print reverse"))
					{
						String reverse = "";
						int index = originalString.length() - 1;
						while (index >= 0)
						{
							reverse += (originalString.charAt(index));
							index--;
						}
						System.out.println(reverse);
					}
				
					//Code for replace all command//
					else if (manipulation.toLowerCase().equals("replace all"))
					{
						System.out.println("Enter the character to replace");
						String toReplace = keyboard.nextLine();
						char firstChar = toReplace.charAt(0);
						int numberOfLetters = 0;
						System.out.println("Enter the new character");
						String replacement = keyboard.nextLine();
						for (x = 0; x <= originalString.length()-1; x++)
						{
							if (firstChar == originalString.charAt(x))
							{
							originalString = originalString.substring(0,x) + replacement + originalString .substring(x+1);
							numberOfLetters++;
							}
						}
						if(numberOfLetters>0)
						{
							System.out.println("The new string is: \t" + originalString);
						}
						else 
						{
							System.out.print("Error: The entered charater is not found in this string");
						}
					}
					
					//Code for replace single//
					else if(manipulation.toLowerCase().equals("replace single"))
					{
						System.out.println("Enter the character to replace");
						String toReplace = keyboard.nextLine();
						char firstChar = toReplace.charAt(0);
						System.out.println("Enter the new character");
						String newChar = keyboard.nextLine();
						System.out.println("Which " + toReplace + " would you like to replace?");
						int replacingCharIndex = keyboard.nextInt();
						int repetition = 0;
						
						for (y = 0; y<=originalString.length() - 1; y++)
						{
							
							if(firstChar == (originalString.charAt(y)))
							{
							repetition++;
							}
							if(repetition == replacingCharIndex)
							{
								originalString = originalString.substring(0,y) + newChar + originalString.substring(y+1);
								y = originalString.length();
							}
						}
						
						if (repetition == replacingCharIndex)
						{
							System.out.println("The new sentence is: \t");
						}
						else
						{
							System.out.println("Error: The entered character is not found in this sentence");
						}
					}
					
					//Code for remove//
					else if(manipulation.toLowerCase().equals("remove"))
					{
						System.out.println("Enter the character to remove");
						String removeChar = keyboard.nextLine();
						char remove = removeChar.charAt(0);
						String adjustedString = originalString;
						for(z=0; z<=adjustedString.length()-1; z++)
						{
							
							if(remove == (adjustedString.charAt(z)))
								{
								adjustedString = adjustedString.substring(0,z) + adjustedString.substring(z+1);
								z--;
								}
						}
						if(adjustedString.equals(originalString))
						{
							System.out.println("Error: The letter you entered to remove does not exist");
						}
						System.out.println("The new sentence is:\t" + adjustedString);
					}
					
					else if(manipulation.toLowerCase().equals("remove"))
					{
						System.exit(0);
					}
				}

	}

}
