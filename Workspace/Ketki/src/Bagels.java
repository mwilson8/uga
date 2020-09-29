import java.util.Arrays;
import java.util.Scanner;
public class Bagels {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		int numGames = 1;
		int count = 0;
		boolean guess = false;
		String next;
		int numGuesses = 0;
		
		Player player = new Player();
		Engine engine = new Engine();
		
		System.out.println("Welcome!");
		System.out.println("Enter the number of digits to use: ");
			int numDigits = keyboard.nextInt();
			keyboard.nextLine();
			engine.setNumDigits(numDigits);
		
			System.out.println("Enter the player's name: ");
			String name = keyboard.nextLine();
			player.setName(name);
		
		engine.generateNewSecret();
		System.out.println(engine.getSecretNumber()	);
		
			System.out.println("Starting game #" + numGames);
			
				while(guess == false){
					System.out.println("Entern your guess");
					String guessNum = player.askForGuess();
					numGuesses++;
					System.out.println(guessNum);
					
					int[] numArray = engine.convertNumToDigitArray(guessNum);
					
					int secretNum = engine.getSecretNumber();
					int[] secretArray = new int[numArray.length];
					int temp = 0;
					
					for(int a1 = numArray.length - 1; a1 >= 0; a1--){
						temp = secretNum % 10;
						secretNum /= 10;
						secretArray[a1] = temp;
					}
					
					if(Validator.validateGuess(secretArray, numArray, numDigits)){	
						guess = true;
						player.updateStats(numGuesses);
						System.out.println("Congratulations! You won in " + count + " moves");
						System.out.println("Statistics for " + name);
						System.out.println("Games completed: " + player.getGamesCompeted());
						System.out.println("Number of digits: " + engine.getNumDigits());
						System.out.println("Fastest Win: " + player.getFastestWin() + " guesses");
						System.out.println("p - Play again\n r - Reset game\n q - Quit");
						System.out.println("What would you like to do?");
							next = keyboard.next();
							if(next.equals("p")){
								numGames++;
							}else if(next.equals("r")){
								numGames++;
								engine.generateNewSecret();
							}else{
								guess = true;
							}
					}else{
						
					}
					System.out.println();
				
				}
			
			
			
			
			
			
			
			
			
		
		

	}

}
