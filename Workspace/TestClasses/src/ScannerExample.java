import java.util.Scanner;

public class ScannerExample {

	public static void main(String[] args) {
		
		//since I've imported the Scanner class, I can use it directly
		Scanner keyboard1 = new Scanner (System.in);

		//if I don't import the Scanner class, I would have to tell
		//the compiler a more precise location for where it can find it
		java.util.Scanner keyboard2 = new java.util.Scanner(System.in);
		
		keyboard1.next();
		keyboard2.next();
		keyboard1.close();
		keyboard2.close();
	}

}
