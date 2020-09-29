import java.util.Scanner;
public class ClassifyMessage {
	
	enum MessageCategory {NEED, OFFER, ALERT, INFO, UNKNOWN}
	public static final double south = 39.882343, north = 40.231315;
	public static final double east = -104.97864, west = -105.743511;

	public static void main(String[] args) {
		
		String payload, catString, n; 
		double latitude, longitude; 
		boolean isInRange; 
		MessageCategory category; 
		
		System.out.println("Please enter formatted message.");
		Scanner keyboard = new Scanner(System.in);
		
		catString = keyboard.next(); catString = catString.trim();
		latitude = keyboard.nextDouble();
		longitude = keyboard.nextDouble();
		payload = keyboard.nextLine();
		if ((catString.equalsIgnoreCase("fire"))||(catString.equalsIgnoreCase("smoke"))){; 
			category = MessageCategory.ALERT;
		}
		else if(catString.equalsIgnoreCase("need"))
					category = MessageCategory.NEED;
			else if (catString.equalsIgnoreCase("offer"))
					category = MessageCategory.OFFER;
				else if ((catString .equalsIgnoreCase( "structure"))||(catString.equalsIgnoreCase( "road"))||
							(catString.equalsIgnoreCase( "photo"))||(catString.equalsIgnoreCase( "evac")))
						category = MessageCategory.INFO;
					
					else{
						category = MessageCategory.UNKNOWN;}
		
		if ((latitude >= south)&&(latitude <= north)&&(longitude>=west)&&(longitude<=east));
			isInRange=true;
		
	
	
		
		
		System.out.println("Category: \t"+category);
		System.out.println("Raw cat: \t"+catString);
		System.out.println("Message: \t"+payload);
		System.out.println("Latitude: \t"+latitude);
		System.out.println("Longitude: \t"+longitude);
		System.out.println("In Range: \t"+isInRange);
		
		
	}

}

