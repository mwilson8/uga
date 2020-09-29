
import java.util.Scanner;

public class Palindrome{
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a positive integer");
		
		int originalNum = keyboard.nextInt(), 
				
				numberCopy, remainder, reverseNum = 0, sum = 0;
		
		if (originalNum <= 0) 
			System.out.println("Invalid input");
		
		else {
			for (numberCopy = originalNum; numberCopy > 0; reverseNum = (reverseNum * 10) + remainder) {
				remainder = numberCopy % 10;
				numberCopy /= 10;
			}
			if (reverseNum == originalNum || originalNum < 10) {
				for (int i = 0; i <= originalNum; i++) sum += i;
				
				System.out.println(originalNum + " is a palindrome\n"
						+ "The sum of numbers from 0 to " + originalNum + " is " + sum);
				
			} else 
				System.out.println("The number " + originalNum + " is not a palindrome.");
		
		} keyboard.close();
	}
}