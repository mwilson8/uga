/*
* [ParseTheTweet].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Feb 10 2016] *
* 
*	Purpose: This program is designed to read and categorize sections 
*				of a tweet in order to better help determine if the 
*				sender needs assistance by determining what type of 
*				situation is happening, categorizing that situation, 
*				providing some details and then determining if the
*				location is within a specified range.
*  *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */

//import java.util.Scanner;

public class ParseTheTweet {

	enum MessageCategory {
		NEED, OFFER, ALERT, INFO, UNKNOWN
	};

	public static final double NORTH = 40.231315;
	public static final double EAST = -104.907864;
	public static final double WEST = -105.743511;
	public static final double SOUTH = 39.882343;

	public static void main(String[] args) {

		System.out.println("Please enter a formatted message:");

		MessageCategory category = MessageCategory.UNKNOWN;
		String tweet;
		String type;
		String typeOutput = "";
		String details;
		String location;
		String latString;
		String longString;

		int start, finish;
		
		
		

		java.util.Scanner keyboard = new java.util.Scanner(System.in);
		
		
		
		
		tweet = keyboard.nextLine();
		keyboard.close();

		start = tweet.indexOf('#') + 4; // cuts the hashtag
		finish = tweet.indexOf(';') + 1; // includes ";"
		type = tweet.substring(start, finish);
		type = type.substring(0, (type.length() - 1)); // removes ";"
		type = type.trim(); // removes any whitespace
		// System.out.println("Type: " + type);

		// repeats above process for the remaining information
		tweet = tweet.substring(finish, tweet.length());
		tweet = tweet.trim();

		start = tweet.indexOf('#') + 4;
		finish = tweet.indexOf(';') + 1;

		// finds, trims, and replaces ',' for '-'
		details = tweet.substring(start, finish);
		details = details.substring(0, (details.length() - 1));
		details = details.trim();
		details = details.replace(',', '-');
		// System.out.println("Details: " + details);

		tweet = tweet.substring(finish, tweet.length());
		tweet = tweet.trim();
		start = tweet.indexOf('#') + 4;
		finish = tweet.indexOf(';') + 1;

		// location is not used, but this still removes the data
		location = tweet.substring(start, finish);
		location = location.substring(0, (location.length() - 1));
		location = location.trim();
		// System.out.println("Location: " + location);

		tweet = tweet.substring(finish, tweet.length());
		tweet = tweet.trim();

		start = tweet.indexOf('#') + 4;
		finish = tweet.indexOf(';') + 1;

		latString = tweet.substring(start, finish);
		latString = latString.substring(0, (latString.length() - 1));
		latString = latString.trim();
		// System.out.println("Latitude: " + latString);

		tweet = tweet.substring(finish, tweet.length());
		tweet = tweet.trim();

		start = tweet.indexOf('#') + 4;
		finish = tweet.indexOf(';') + 1;

		longString = tweet.substring(start, finish);
		longString = longString.substring(0, (longString.length() - 1));
		longString = longString.trim();
		// System.out.println("Longitude: " + longString);

		// entire tweet has been processed

		// if type equals fire, assign ALERT, etc..
		// save type as type all caps
		if (type.equalsIgnoreCase("fire"))
			category = MessageCategory.ALERT;

		else if (type.equalsIgnoreCase("smoke"))
			category = MessageCategory.ALERT;

		else if (type.equalsIgnoreCase("need"))
			category = MessageCategory.NEED;

		else if (type.equalsIgnoreCase("offer"))
			category = MessageCategory.OFFER;

		else if (type.equalsIgnoreCase("structure"))
			category = MessageCategory.INFO;

		else if (type.equalsIgnoreCase("road"))
			category = MessageCategory.INFO;

		else if (type.equalsIgnoreCase("photo"))
			category = MessageCategory.INFO;

		else if (type.equalsIgnoreCase("evac"))
			category = MessageCategory.INFO;

		else
			category = MessageCategory.UNKNOWN;

		typeOutput = type.toUpperCase();

		double latitude = Double.parseDouble(latString);
		double longitude = Double.parseDouble(longString);

		boolean isInRange = false;

		// isInRange is initialized as false, so if neither is true
		// it remains false

		if (latitude >= SOUTH && latitude <= NORTH) {
			if (longitude >= WEST && longitude <= EAST) {
				isInRange = true;
			}
		}

		System.out.println("Category: \t" + category);
		System.out.println("Type: \t\t" + typeOutput);
		System.out.println("Detail: \t" + details);
		System.out.println("In range: \t" + isInRange);

	}

}
