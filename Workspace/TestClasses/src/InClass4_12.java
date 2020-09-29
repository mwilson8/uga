
public class InClass4_12 {

	
	//FlowerCounter
	private static final String[] flowers = {"petunia", "pansy", "rose", "violet", "carnation"};
	private static final double[] cost = {.50, .75, 1.5, .50, .80};
	
	private static double getCost(){
		java.util.Scanner in = new java.util.Scanner(System.in);
		System.out.println("Which flower do you want?");
		String flower = in.nextLine();
		for (int i = 0; i < flowers.length; i++){
			if (flowers[i].equals(flower))
				return cost[i];
		}
		return -1;
		
	}
	
	private static double getQuantity(){
		java.util.Scanner in = new java.util.Scanner(System.in);
		System.out.println("How many would you like");
		return in.nextDouble();
	}
	
	public static double getTotal(){
		return getCost() * getQuantity();
	}
	
	//execute getTotal() in main
	
	
	//CharacterFrequency
	private static void getFrequency(){
		
		int [] frequency = new int [10];
		java.util.Scanner in = new java.util.Scanner(System.in);
		
		System.out.println("Enter phone number: ");
		String number = in.nextLine();
		
		for (int i = 0; i < number.length(); i ++){
			//make sure character is a digit
			if (Character.isDigit(number.charAt(i))){
				frequency[Character.getNumericValue(number.charAt(i))] ++;
			}
		}
	}
	//execute getFrequency() in main
	
}
