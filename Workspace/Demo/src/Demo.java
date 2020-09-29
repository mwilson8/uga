import java.util.Scanner;
public class Demo {

	public static void main(String[] args) {
		
		String word;
		String part1;
		String part2;
		byte b = 1;
		//System.out.println("Enter a word, we will replace the 2nd 'b' with an 'a'");
		//Scanner keyboard = new Scanner(System.in);
		//word = keyboard.nextLine();
		String phrase;
		String holding;
		String letter;
		
		
		/*seperating strings
		part1 = word.substring(0, word.indexOf('b') + 1);
	part2 = word.substring(word.indexOf('b') + 1, word.length());
		
		part2 = part2.substring(0, part2.indexOf('b')) + 'a' + part2.substring(part2.indexOf('b') + 1);
		
		word = part1 + part2;
		System.out.println(word);
		*/
		
		System.out.println("Enter a phrase:");
		Scanner keyboard = new Scanner(System.in);
		phrase = keyboard.nextLine();
		
		System.out.println("");
		System.out.println("Enter a character:");
		Scanner keyboard2 = new Scanner(System.in);
		holding = keyboard2.nextLine();
		letter = holding.substring(0,1);
		
		
		phrase = phrase.toLowerCase();
		phrase = phrase.substring(0, phrase.indexOf(letter)) + 
				phrase.substring(phrase.indexOf(letter) + 1, phrase.lastIndexOf(letter)) +
				phrase.substring(phrase.lastIndexOf(letter) + 1);
		
		System.out.println("Modified string so far: " + phrase);
		
		phrase = phrase.replace(letter, letter.toUpperCase());
		System.out.println(phrase);
		
	}

}
