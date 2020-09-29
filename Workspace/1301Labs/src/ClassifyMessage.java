
import java.util.Scanner;

public class ClassifyMessage {
	
	enum MessageCategory { NEED, OFFER, ALERT, INFO, UNKNOWN }
	
	public static final double SOUTH = 39.882343, NORTH = 40.231315,
								EAST = -104.97864, WEST = -105.743511;

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
	
		MessageCategory category; 
		
		System.out.println("Please enter formatted message.");
		
		
		String catString = keyboard.next().trim();
		double latitude = keyboard.nextDouble();
		double longitude = keyboard.nextDouble();
		String payload = keyboard.nextLine();
		
		if (catString.equalsIgnoreCase("fire")
				||catString.equalsIgnoreCase("smoke"))
				category = MessageCategory.ALERT;
		
		else if(catString.equalsIgnoreCase("need"))
					category = MessageCategory.NEED;
		
		else if (catString.equalsIgnoreCase("offer"))
					category = MessageCategory.OFFER;
		
		else if ((catString .equalsIgnoreCase("structure"))
				|| catString.equalsIgnoreCase("road")
				|| catString.equalsIgnoreCase("photo")
				|| catString.equalsIgnoreCase( "evac"))
					category = MessageCategory.INFO;
					
		else
			category = MessageCategory.UNKNOWN;
		
		boolean isInRange = (latitude >= SOUTH) && (latitude <= NORTH)
				&& (longitude>=WEST) && (longitude<=EAST);

		System.out.println("Category: \t" +category);
		System.out.println("Raw cat: \t"  +catString);
		System.out.println("Message: \t"  +payload);
		System.out.println("Latitude: \t" +latitude);
		System.out.println("Longitude: \t"+longitude);
		System.out.println("In Range: \t" +isInRange);
		
		
	}

}

