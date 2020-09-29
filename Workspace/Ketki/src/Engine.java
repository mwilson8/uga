import java.util.Random;
public class Engine {

	private int numDigits;
	private int[] secretNumber;

	Random randomNumberGenerator = new Random();
	
	public int[] convertNumToDigitArray(String number){
		int length = number.length();
		numDigits = length;
		int index;
		int[] secretNumber = new int[length];
		for(int i =0; i < secretNumber.length; i++){
			secretNumber[i] = Character.getNumericValue(number.charAt(i));
		}
		return secretNumber;
	}
	
	public int getNumDigits(){
		return numDigits;
	}
	
	public int getSecretNumber(){
		String s = ""; 
		for(int index = 0; index < secretNumber.length; index++){
			s += secretNumber[index];
		}
		return Integer.parseInt(s);
	}
	
	public void generateNewSecret(){
		int newSecret;
		int upperBound = (int) Math.pow(10, getNumDigits()) - 1;
		int lowerBound = (int) Math.pow(10, (getNumDigits()) - 1);
		int range = upperBound - lowerBound;
		newSecret = randomNumberGenerator.nextInt(upperBound);
		//newSecret = (int)(Math.random() * range) + lowerBound;
		String secretNum = Integer.toString(newSecret);
		setSecretNumber(convertNumToDigitArray(secretNum));
		
	}
	
	public void setNumDigits(int numDigits){
		this.numDigits = numDigits;
	}
	
	public void setSecretNumber(int[] secretNumber){
		this.secretNumber = new int [secretNumber.length];
		for(int index = 0; index < secretNumber.length; index++){
			this.secretNumber[index] = secretNumber[index];
		}
	}
	
	
}
