import java.util.Scanner;

public class Palindrome2 {
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a positive integer");
		
		int oNum = keyboard.nextInt(), number, remainder, rNum = 0, sum = 0;
		
		if (oNum <= 0) System.out.println("Invalid input");
		
		else {
			for (number = oNum; number > 0; rNum = (rNum * 10) + remainder) {
				remainder = number % 10;
				number /= 10;
			}
			if (rNum == oNum || oNum < 10) {
				for (int i = 0; i <= oNum; i++) sum += i;
				
				System.out.println(oNum + " is a palindrome\n"
						+ "The sum of numbers from 0 to " + oNum + " is " + sum);
				
			} else System.out.println("The number " + oNum + " is not a palindrome.");
		} keyboard.close();
	}
}