/*[CSCI 1301].java * Author:  [Shahzad Raza]  * Submission Date:  [2/9/18] * * Purpose: To create an enumeration
that categorizes messages. 
 *  A brief paragraph description of the * program. What does it do? * * Statement of Academic Honesty: * * 
 *  The following code represents my own work. I have neither * received nor given inappropriate assistance. 
 *  I have not copied * or modified code from any source other than the course webpage  
 *  or the course textbook. I recognize that any unauthorized * assistance or plagiarism will be handled in 
 *  accordance with * the University of Georgia's Academic Honesty Policy and the * policies of this course. 
 *  I recognize that my work is based * on an assignment created by the Department of Computer * 
 *  Science at the University of Georgia. Any publishing  * or posting of source code for this project is 
 *  strictly * prohibited unless you have written consent from the Department * of Computer Science at the 
 *  University of Georgia.   */ 

		import java.util.Scanner;

		public class ClassifyMessage {
		    enum MessageCategory {NEED, OFFER, ALERT, INFO, UNKNOWN};
		    
		    public static final double south = 39.882343;
		    public static final double north = 40.231315;
		    public static final double west = -105.743511;
		    public static final double east = -104.907864;

		    public static void main(String[] args) {
			String catString, payload;
			double latitude, longitude;
			boolean isInRange;
			
			MessageCategory category;

			Scanner keyboard = new Scanner(System.in);

			System.out.println("Please enter a formatted message:");
			
			// Assigns catString, latitude, longitude, payload
			catString = keyboard.next();
			latitude = keyboard.nextDouble();
			longitude = keyboard.nextDouble();
			payload = keyboard.nextLine();

			catString = catString.trim();
			payload = payload.trim();

			// Assigns category
			if (catString.equalsIgnoreCase("fire") || catString.equalsIgnoreCase("smoke")) {
			    category = MessageCategory.ALERT;
			} else if (catString.equalsIgnoreCase("need")) {
			    category = MessageCategory.NEED;
			} else if (catString.equalsIgnoreCase("offer")) {
			    category = MessageCategory.OFFER;
			} else if (catString.equalsIgnoreCase("structure") || catString.equalsIgnoreCase("road") || 
				   catString.equalsIgnoreCase("photo") || catString.equalsIgnoreCase("evac")) {
			    category = MessageCategory.INFO;
			} else {
			    category = MessageCategory.UNKNOWN;
			}

			if (latitude >= south && latitude <= north && longitude >= west && longitude <= east)
			    isInRange = true;
			else
			    isInRange = false;

			//Prints Output
			System.out.println("Category:\t" + category + "\nRaw Cat:\t\t" + catString + "\nMessage:\t\t" + payload + "\nLatitude:\t" + latitude
					   + "\nLongitude:\t" + longitude + "\nIn Range:\t" + isInRange);
		    }
		
	}

