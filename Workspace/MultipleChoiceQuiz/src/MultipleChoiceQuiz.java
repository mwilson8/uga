/*
* [MultipleChoiceQuiz].java
* 
* 	Author: 		 [Mitch Wilson]
* 
* 	Submission Date: [Feb 11 2016] 
* 
*	Purpose:    [This program is designed to be a short multiple
*					choice quiz that reads user input (name, gender)
*					and formats that input  then administers a 
*					5-question quiz. At the end, the most popular
*					result is displayed along with a formatted 
*					version of the user's name]
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

public class MultipleChoiceQuiz {
		
	public static void main(String[] args) {
		
		int A = 0;
		int B = 0;
		int C = 0;
		int D = 0;
		String name;
		String empty;
		String answer;
		String either;
		String gender;
		String greeting;
		String lastName;
		int twoNameCheck;
		String firstName;
		String question1;
		String question2;
		String question3; 
		String question4;
		String question5;
		String finalAnswer;
		
		
		
		
		System.out.println("Where should you live?");
		System.out.println("\t A Big City Condo?");
		System.out.println("\t A Mountain Cabin?");
		System.out.println("\t A Beach House?");
		System.out.println("\t A Rural Farmhouse?");
		System.out.print  ("This quiz will tell you!");
		System.out.println("Press <enter> to begin!");
		System.out.println("-------------------------"
						 + "------------------------");
		
		Scanner keyboard = new Scanner(System.in);
				   empty = keyboard.nextLine();
			
			
	 
		System.out.print("Enter your first and last name:");
		Scanner input1 = new Scanner(System.in);
				  name = input1.nextLine();
			
			twoNameCheck = name.indexOf(' ');
				
			if (twoNameCheck <= 0) {
				System.out.println("Name is invalid. Exiting...");
				System.exit(0);
			}
					
		System.out.print("Enter your gender (male or female):");
		Scanner input2 = new Scanner(System.in);
				gender = input2.nextLine();		
				gender = gender.toLowerCase();
			
			if (gender.equals("male"))
				gender = "male";
			else if (gender.equals("female"))
				gender = "female";
			else if (gender.length() == 1) {
					if (gender.equals("f"))
						gender = "female";
					else if (gender.equals("m"))
						gender = "male";
			}
			else {
				System.out.println("Gender is invalid. Exiting...");
				System.exit(0);
			}	
					
		firstName = name.substring(0, name.indexOf(' ') + 1);
		firstName = firstName.trim();
		
		lastName = 
			name.substring((name.indexOf(' ') + 1), name.length());
		
		lastName = lastName.trim();
				
		//takes first letter and capitalizes, lowercases the rest
		firstName = (firstName.substring(0, 1)).toUpperCase() 
		+ ((firstName.substring(1, firstName.length()).toLowerCase()));
				
		lastName = (lastName.substring(0, 1)).toUpperCase()
		+ ((lastName.substring(1, lastName.length()).toLowerCase()));
	
		
		/*// these are just for checking 
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Gender: " + gender);
		*/	
		
		
		if (gender.equals("male")) 
			greeting = "Mr.";
			
		else
			greeting = "Ms.";
			
		
		
		System.out.println("Hey, " + greeting + " " + lastName + 
				", answer the questions below!");
		
		 //question 1
		System.out.print("Question 1:");
		System.out.println(" Pick your favorite scenery:");
		System.out.println("\t (a) City Skyline");
		System.out.println("\t (b) Mountain Peaks");
		System.out.println("\t (c) Palm Trees");
		System.out.println("\t (d) Rolling Hills");
		Scanner firstQuestion = new Scanner(System.in);
					question1 = firstQuestion.next();
					question1 = question1.toLowerCase();
					
		//check for valid input
		if (!(question1.equals("a")) && !(question1.equals("b"))
		&& !(question1.equals("c")) && !(question1.equals("d")))
			{	
			System.out.println("Invalid input. Exiting...");
			System.exit(0);
		}
				
				// question 2
		System.out.print("Question 2: ");
		System.out.println("How do you enjoy spending your free time?");
		System.out.println("\t (a) Social activities with friends");
		System.out.println("\t (b) Hiking and enjoying nature");
		System.out.println("\t (c) Ralaxing with a good book");
		System.out.println("\t (d) Getting work done around the house");
		Scanner secondQuestion = new Scanner(System.in);
					 question2 = secondQuestion.next();	
					 question2 = question2.toLowerCase();
					
		//check for valid input
		if ((!(question2.equals("a"))) && (!(question2.equals("b")))
		&& (!(question2.equals("c"))) && (!(question2.equals("d")))){
			System.out.println("Invalid input. Exiting...");
			System.exit(0);
		}	
				
			// question 3
		System.out.println("Question 3: What's for dinner?");
		System.out.println("\t (a) That new restaurant "
						 + "everyone is talking about");
		System.out.println("\t (b) Barbecue");
		System.out.println("\t (c) Fresh seafood");
		System.out.println("\t (d) Something local and farm fresh");
			Scanner thirdQuestion = new Scanner(System.in);
				question3 = thirdQuestion.next();	
				question3 = question3.toLowerCase();
					
		//check for valid input
		if ((!(question3.equals("a"))) && (!(question3.equals("b")))
		&& (!(question3.equals("c"))) && (!(question3.equals("d"))))
		{	
			System.out.println("Invalid input. Exiting...");
			System.exit(0);
				}	
									
			// question 4
		System.out.println("Question 4: Pick an animal:");
		System.out.println("\t (a) Bird");
		System.out.println("\t (b) Dog");
		System.out.println("\t (c) Whale");
		System.out.println("\t (d) Pig");
		Scanner fourthQuestion = new Scanner(System.in);
					 question4 = fourthQuestion.next();	
					 question4 = question4.toLowerCase();
					
		//check for valid input
		if ((!(question4.equals("a"))) && (!(question4.equals("b")))
		&& (!(question4.equals("c"))) && (!(question4.equals("d")))){
			System.out.println("Invalid input. Exiting...");
			System.exit(0);
		}	
									
			// question 5
		System.out.print("Question 5:");
		System.out.println(" What's your preferred mode"
						 + " of transportation?");
		System.out.println("\t (a) Bus or Walk");
		System.out.println("\t (b) SUV");
		System.out.println("\t (c) Convertible");
		System.out.println("\t (d) Truck");
			Scanner fifthQuestion = new Scanner(System.in);
				question5 = fifthQuestion.next();	
				question5 = question5.toLowerCase();
				
		//check for valid input
		if ((!(question5.equals("a"))) && (!(question5.equals("b")))
		&& (!(question5.equals("c"))) && (!(question5.equals("d"))))
		{
			System.out.println("Invalid input. Exiting...");
			System.exit(0);
		}	
			//math operations: each answer equals a point	
			//question 1	
			if (question1.equals("a"))
				A ++;
			else if (question1.equals("b"))
				B ++;
			else if (question1.equals("c"))
				C ++;
			else if (question1.equals("d"))
				D ++;
			
			
			//question 2	
			if (question2.equals("a"))
				A ++;
			else if (question2.equals("b"))
				B ++;
			else if (question2.equals("c"))
				C ++;
			else if (question2.equals("d"))
				D ++;
				
			
			//question 3	
			if (question3.equals("a"))
				A ++;
			else if (question3.equals("b"))
				B ++;
			else if (question3.equals("c"))
				C ++;
			else if (question3.equals("d"))
				D ++;
			
			
			//question 4	
			if (question4.equals("a"))
				A ++;
			else if (question4.equals("b"))
				B ++;
			else if (question4.equals("c"))
				C ++;
			else if (question4.equals("d"))
				D ++;
			
			
			//question 5	
			if (question5.equals("a"))
				A ++;
			else if (question5.equals("b"))
				B ++;
			else if (question5.equals("c"))
				C ++;
			else if (question5.equals("d"))
				D ++;
			
			
			/*// these are just for checking correct count
			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
			System.out.println("D = " + D);
			*/
			
			
			//logic for answers: highest score "wins"
			answer = "";
			if (A >= 3 || B >= 3 || C >= 3 || D >= 3){
				if (A >= 3)
						answer = "a";
				else if (B >= 3)
						answer = "b";
				else if (C >= 3)
						answer = "c";
				else
						answer = "d";		
			}
			else if (A == 2) {
				if (B == 2)
					answer = "a & b";
				else if (C == 2)
					answer = "a & c";
				else if (D == 2)
					answer = "a & d";
				else 
					answer = "a";
			}
			else if (B == 2) {
				if (A == 2)
					answer = "a & b";
				else if (C == 2)
					answer = "b & c";
				else if (D == 2)
					answer = "b & d";
				else 
					answer = "b";
			}
			else if (C == 2){
				if (A == 2)
					answer = "a & c";
				else if (B == 2)
					answer = "b & c";
				else if (D == 2)
					answer = "c & d";
				else 
					answer = "c";
			}
			else if (D == 2){
				if (A == 2)
					answer = "a & d";
				else if (B == 2)
					answer = "b & d";
				else if (C == 2)
					answer = "c & d";
				else 
					answer = "d";
			}
	
			//conversion of letter answer to word answer
			either = "";
			finalAnswer = "";
			
			if (answer.equals("a"))
				finalAnswer = "a big city condo";
			else if (answer.equals("b"))
				finalAnswer = "a mountain cabin";
			else if (answer.equals("c"))
				finalAnswer = "a beach house";
			else if (answer.equals("d"))
				finalAnswer = "a rural farmhouse";
			
			//ties with A
			else if (answer.equals("a & b")){
				finalAnswer = "a big city condo or a mountain cabin";
				either = "either ";
			}
			else if (answer.equals("a & c")){
				finalAnswer = "a big city condo or a beach house";
				either = "either ";
			}
			else if (answer.equals("a & d")){
				finalAnswer = "a big city condo or a rural farmhouse";
				either = "either ";
			}
			
			//ties with B
			else if (answer.equals("b & c")){
				finalAnswer = "a mountain cabin or a beach house";
				either = "either ";
			}
			else if (answer.equals("b & d")){
				finalAnswer = "a mountain cabin or a rural farmhouse";
				either = "either ";
			}
			
			//ties with C
			else if (answer.equals("c & d")) {
				finalAnswer = "a beach house or a rural farmhouse";
				either = "either ";
			}
		

			System.out.println(firstName  
					+ ", you should live in " 
					+ either  + finalAnswer + ".");
			
	}

}
