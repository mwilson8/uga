
public class PasswordValidation {

	
	public static void main (String [] args){
		java.util.Scanner in = new java.util.Scanner(System.in);
		
		
		
		/* conditions for password:
		 * >= 1 uppercase (ASCII 65-90) 
		 * >= 1 lowercase (ASCII 97-122)
		 * >= 2 digits (ASCII 48-57)
		 * >= 1 special character (ASCII 33-47)
		 * >= 8 length
		 */
		
		boolean valid = false;
		do {

			System.out.println("Enter a password");
			
			String pw = in.nextLine();
			
			boolean upper = false;
			boolean lower = false;
			int digits = 0;
			boolean special = false;
			
			for(int i = 0; i < pw.length(); i++){
			
				if (Character.isUpperCase(pw.charAt(i)))
					upper = true;
				
				if (Character.isLowerCase(pw.charAt(i)))
					lower = true;
				
				if (Character.isDigit(pw.charAt(i)))
					digits ++;
				
				if ((int)pw.charAt(i) >= 33 && (int)pw.charAt(i) <= 47)
					special = true;
				
			}
			
			valid = (upper && lower && special && pw.length() >= 8 && (digits >= 2));
			
			System.out.println("Password is valid: " + valid);
		
		} while ( ! valid);
		
		
	}
}
