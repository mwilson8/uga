
import java.util.Scanner;
public class StringFun {

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a string to be manipulated: ");
		String string = in.nextLine();
		
		for(;;){
			System.out.println("Enter a command");
			String command = in.nextLine();
			
			/*
			 * replace all
			 */
			if (command.equalsIgnoreCase("replace all")){
				
				System.out.println("Enter character to replace");
				char oldChar = in.nextLine().charAt(0);
				
				System.out.println("Enter the new character");
				char newChar = in.nextLine().charAt(0);
				
				boolean found = false;
				
				for (int i = 0; i < string.length(); i++){
					
					if (string.charAt(i) == oldChar){
						string = string.substring(0, i) + newChar
								+ string.substring(i+1);
						
						found = true;
					}
				}
				
				if (!found) System.out.println("Character not found");
				System.out.println("New string: " + string);
				
				
				/*
				 * replace single
				 */
			} else if (command.equalsIgnoreCase("replace single")){
				
				System.out.println("Enter character to replace");
				char oldChar = in.nextLine().charAt(0);
				
				System.out.println("Enter the new character");
				char newChar = in.nextLine().charAt(0);
				
				System.out.println("Which " + oldChar + " ?");
				char instance = in.nextLine().charAt(0);
				
				if (! Character.isDigit(instance)){
					System.out.println("Invalid input");
					continue;
				}
					
				
				int instanceInt = Character.getNumericValue(instance);
				boolean found = false;
				int count = 0;
				
				for (int i = 0; i < string.length(); i ++){
					
					if (string.charAt(i) == oldChar){
						count ++;
						if (count == instanceInt){
							string = string.substring(0, i) + newChar
							+ string.substring(i+1);
						found = true;
						}
					}
				}
				if (!found) System.out.println("Character not found");
				System.out.println("New string: " + string);
				
				/*
				 * remove
				 */
			} else if (command.equalsIgnoreCase("remove")){
				
				System.out.println("Enter character to remove");
				char oldChar = in.nextLine().charAt(0);
				
				boolean found = false;
				
				for (int i = 0; i < string.length(); i++){
					
					if (string.charAt(i) == oldChar){
						
						string = string.substring(0, i)
								+ string.substring(i+1);
						i--;
						found = true;
					}
				}
				
				if (!found) System.out.println("Character not found");
				System.out.println("New string: " + string);
				
			} else if (command.equalsIgnoreCase("print reverse")){
				for (int i = 0; i < string.length(); i++)
					System.out.print(string.charAt(string.length()-1-i));
				System.out.println();
				
				
			} else if (command.equalsIgnoreCase("quit")){
				return;
			} else 
				System.out.println("Invalid command");
		}
	}
}
