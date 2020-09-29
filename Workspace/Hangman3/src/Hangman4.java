import java.util.Scanner;
public class Hangman4 {

	
	public static void main (String[] args){
	Scanner in = new Scanner(System.in);
	String sW, dW, s;
	int guesses, spaces;
	
	spaces = 4;
	sW = "identifier";
	
	for(boolean valid = false; !valid; ){
		System.out.println("enter spaces guess string");
		s = in.nextLine().trim() + ' ';
		for (int i  =0; i < s.length(); i+=2){
		if (s.length() != spaces*2 || !Character.isDigit(s.charAt(i)) || s.charAt(i+1) != ' ' 
				|| Character.getNumericValue(s.charAt(i)) > sW.length()){
			
			System.out.println("invalid input");
			valid = false;
			break;
			
		}
		else valid = true;
			
		}//for
	}//for
	}
}
