/*
* [StringFun].java
* Author:  [Aaron Xiao] 
* Submission Date:  [01/02/18]
*
* Purpose: This lab focuses on using loops to take in commands from user on what 
* to do on a string also provided by the user. Aside from loops this lab also focuses heavily on
* the use of if statements which, are found throughout the program. This program itself is 
* designed as a loop itself with several smaller loops within it. This program will continue
* to let the user alter a said string unless the user types in the command to terminate 
* the loop.
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* 
received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
CSCI 1301: Project 1
Page 
3
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the 
University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on
an assignment 
created by the Department of 
Computer
* Science at the University of Georgia. Any publishing 
* 
or posting 
of source cod
e for this project is strictly
* prohibited unless you have 
written consent from the Department 
* 
of Computer Science
at the University of Georgia
.  
*/
import java.util.Scanner;;
public class StringFun {

	public static void main(String[] args) {
		//Variable declaration
	String word, command, newWord = "", first, last, charReplace, charNew, ltrReplace = null, ltrNew = null, remove = null;
	int instance = 0, location=0;
	int ltrNum = 0;
	boolean condition=true, trigger= false;
	
	//prompt user for word
	Scanner keyboard = new Scanner(System.in);
	System.out.println("Enter the string to be manipulated");
	word = keyboard.nextLine();
	int index = 0;
	//loop asking for commands
	while(condition)
	{
		System.out.println("Enter your command (quit, print reverse, replace all, replace single, remove)");
		command = keyboard.nextLine();
		//code for print reverse command
		if(command.equalsIgnoreCase("print reverse"))
		{
			for(int i=word.length()-1; i>=0; i--)
			{
				System.out.print(word.charAt(i));
			}
			System.out.println();
		}
		
		//code for replace all
		else if(command.equalsIgnoreCase("replace all"))
		{
			int count = 0;
			System.out.println("Enter the character to replace");
			charReplace= keyboard.nextLine();
			System.out.println("Enter the new character");
			charNew=keyboard.nextLine();
			for( index=0;index<=word.length()-1;index++)
			{
				if(charReplace.charAt(0)==word.charAt(index))
				{
					count++;
					word=word.substring(0,index)+charNew+word.substring(index+1);
				}
			}
			//checks to see if letter is in the string
			if(count==0)
			{
				System.out.println("Error: the letter you are trying to replace does not exist");
			}
			else
			{
			System.out.println("Your new string is: "+word);
			}
		}
		//code for replace single
		else if(command.equalsIgnoreCase("replace single"))
		{
			String changedWord="";
			//obtain user inputs
			System.out.println("Enter the character to replace");
			ltrReplace = keyboard.nextLine();
			System.out.println("Enter the new Character");
			ltrNew = keyboard.nextLine();
			System.out.println("Which "+ltrReplace+" would you like to replace?");
			char ltrNum1 = keyboard.nextLine().charAt(0);
			int count = 0;
			boolean key= true;
			//String blank =keyboard.nextLine();
			if(Character.isDigit(ltrNum1)) {
				int guess = Character.getNumericValue(ltrNum1);
			}else {
				System.out.println("Invalid");
				key = false;
				
			}
			for(int n=0;n<word.length();n++)
			{
				
				if(ltrReplace.charAt(0)==word.charAt(n))
				{
					++instance;	
					if(instance==Character.getNumericValue(ltrNum1))
					{
						changedWord=word.substring(0,n)+ltrNew+word.substring(n+1);
					}
					
				}
				
				if(ltrReplace.charAt(0)==word.charAt(n))
				{
					count++;
				}
			}
			//checks to see if the letter at the specified instance exists
			word=changedWord;
			if(count!=Character.getNumericValue(ltrNum1))
			{
				if(key)
				{
				System.out.println("Error: the letter you are trying to replace does not exist");
				condition=true;
				}
			}
			else
			{
				System.out.println(word);
				System.out.println(instance);
				System.out.println(Character.getNumericValue(ltrNum1));
				condition=true;
				System.out.println("Your new sentence is: "+word);
			}
			
		}
		//code for remove
		else if(command.equalsIgnoreCase("remove"))
		{
			int count=0;
			newWord="";
			System.out.println("Enter the character to remove");
			remove=keyboard.nextLine();
			for(int n=0;n<word.length();n++)
			{
				if(!(word.charAt(n)==remove.charAt(0)))
				{
					newWord=newWord+word.charAt(n);
				}
				else
				{
					count++;
				}
			}
			//checks to see if letter exists
			if(count==0)
			{
				System.out.println("Error: the letter you are trying to remove does not exist");
				word=newWord;
			}
			else
			{
			word=newWord;
			System.out.println("The new sentence is: "+newWord);
			}
		}
		//code for quit
		else if(command.equalsIgnoreCase("quit"))
		{
			condition = false;
			
		}
		else
		{
			System.out.println("You have entered a invalid command");
		}
	}
	

	}

}
