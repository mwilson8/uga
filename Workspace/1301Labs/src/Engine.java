

public class Engine {

	private int numDigits;
	private int[] secretNumber;
	private java.util.Random randomNumberGenerator = new java.util.Random();
	
	public int[] convertNumToDigitArray(String number){
		int [] temp = new int[number.length()];
		
		for (int i = 0; i < temp.length; i++ )
			temp[i] = Character.getNumericValue(number.charAt(i));
		
		return temp;
	}
	
	public int getNumDigits(){
		return numDigits;
	}
	
	public int[] getSecretNumber(){
		return secretNumber;
	}
	
	public void generateNewSecret(){
		int upperBound = (int) (Math.pow(10, numDigits) - 1);
		int lowerBound = (int) (Math.pow(10, numDigits - 1));
		
		int randomInBounds = randomNumberGenerator.nextInt(upperBound - lowerBound);
		randomInBounds += lowerBound;
		
		//convert randomInBounds to array and store in secret number
		
		secretNumber = convertNumToDigitArray(Integer.toString(randomInBounds));
	}
	
	public void setNumDigits(int numDigits){
		this.numDigits = numDigits;
	}
	
	public void setSecretNumber(int[] secretNumber){
		this.secretNumber = secretNumber;
	}


}