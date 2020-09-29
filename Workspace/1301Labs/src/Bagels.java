
import java.util.Scanner;

public class Bagels {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		Engine e = new Engine();
		boolean reset = true, playAgain = true;
		int games = 1, guesses = 1;
		Player p1 = new Player();
		
		while (true){
			//reset comes here
			if (reset){
			System.out.println("Enter number of digits");
			e.setNumDigits(keyboard.nextInt());
			keyboard.nextLine();
			reset = false;
			
			guesses = 1;
	
			System.out.println("Enter your name: ");
			p1 = new Player();
			p1.setName(keyboard.nextLine());
			}
			
			//play again comes here
			
			boolean win = false;
			System.out.println("\nStarting game #" + (p1.getGamesCompleted() + 1));
			e.generateNewSecret();
			guesses = 1;
			
			
			while (!win){
				
				
				String guess = p1.askForGuess();
				
				win = Validator.validateGuess(e.getSecretNumber(), e.convertNumToDigitArray(guess), e.getNumDigits());
				
				if(win){
					
					System.out.println("Congrats, you won in " + guesses + " moves");
					p1.updateStats(guesses);
					
					System.out.println("\nStats for " + p1.getName() + ":");
					System.out.println("Games completed: " + p1.getGamesCompleted());
					System.out.println("Number of digits: " + e.getNumDigits());
					System.out.println("Fastest Win: " + p1.getFastestWin() + " guesses");
					
					System.out.println("p - play again");
					System.out.println("r - reset game");
					System.out.println("q - quit");
					
					System.out.println("What would you like to do?");
					char choice = keyboard.nextLine().charAt(0);
					
					//TODO play again and reset
					
					switch (choice){
					case 'p': break; 
					case 'r': reset = true; break;
					case 'q': System.exit(0);
					default: reset = true; break;
					}
				}
				else
					guesses ++;
			}
			
		}	
	}

}
