import java.util.Scanner;
import java.util.Vector;
public class HangmanNew {

	private static String displayWord = "", secretWord = "";
	static Scanner in = new Scanner (System.in);
	private static enum Difficulty {EASY, MEDIUM, HARD}
	private static final boolean testingMode = true;
	
	public static void main(String[] args) {
		initialize();
		Difficulty diff = getDifficulty();
		int guesses = 0, spaces = 0;
		switch(diff){
			case EASY: guesses = 15; spaces = 4; break;
			case MEDIUM: guesses = 15; spaces = 4; break;
			case HARD: guesses = 15; spaces = 4; break;
		}
		if (testingMode) out("Secret word: " + secretWord);
		out("Display word: " + displayWord);
		out("-----Difficulty: " + diff);
		/*
		char guess = getLetterGuess();
		
		if(guess == '!')//solve
			if(!solveWord()) guesses --;
			else{
				if (playAgain()) out("-----we're playing again");
				else System.exit(0);
			}
		
		*/
		out("num spaces to guess: " + spaces);
		int [] spacesGuess = validateSpaces(spaces);
		
	}
	
	private static void initialize(){
		secretWord = RandomWord.newWord();
		for (int i = 0; i < secretWord.length(); i++) displayWord += "-";
	}
	
	private static Difficulty getDifficulty(){
		for(;;){
			out("Enter difficulty: ");
			switch(in.nextLine().charAt(0)){
				case 'e': return Difficulty.EASY;
				case 'i': return Difficulty.MEDIUM;
				case 'h': return Difficulty.HARD;
				default: out("Invalid difficulty");
			}
		}
	}
	
	private static char getLetterGuess(){
		for(;;){
			out("Please enter the letter you want to guess");
			String s = in.nextLine();
			if (s.equalsIgnoreCase("solve")) return '!';
			else if (Character.isLetter(s.charAt(0))) return s.charAt(0);
			else out("Your input is not valid");
		}
	}
	private static boolean solveWord(){
		out("Solve the word: ");
		String s = in.nextLine();
		if (s.equals(secretWord)){
			out("Congrats, you guessed the secret word");
			return true;
		}else{
			out("That's not the secret word");
			return false;
		}
	}
	private static boolean playAgain(){
		for(;;){
			out("Play again? ");
			switch(in.nextLine().charAt(0)){
			case 'y': case 'Y': return true;
			case 'n': case 'N': out("goodbye"); return false;
			default: out("Invalid");
			}
		}
	}
	private static int[] validateSpaces(int numSpaces){
		int [] temp = new int [numSpaces];
		out("What spaces do you want to guess?");
		String s = in .nextLine();
		for (int i = 0; i < s.length(); i +=2){
			if (Character.isDigit(s.charAt(i)) && s.charAt(i+1) == ' ') temp[i] = Character.getNumericValue(s.charAt(i));
			else{
				out("Invalid");
			}
		}
	}
	private static void out(String m){
		System.out.println(m);
	}
}