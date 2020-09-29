package edu.uga.cs1302.vehicles;
import java.util.Scanner;
public class VehicleDriver {

	public static void main(String[] args) {
		
		
		Vehicle[] vArray = new Vehicle[15];
		

		

		vArray[0] = new Amphibious("Spruce Goose", "Hughes Aircraft Company", 1947, 750, 250, 200, 3000, 20900, 8);
		

		vArray[1] = new Amphibious("PBY Catalina", "Consolidated Aircraft", 1935, 10, 196, 18, 2520, 15800, 2);
		

		vArray[2] = new Amphibious("Harbin SH-5", "Harbin Aircraft Factory", 1976, 8, 345, 23, 2955, 33629, 4);
		

		vArray[3] = new Airplane("F-35 Lightning II", "Lockheed Martin Aeronautics", 2006, 1, 1200, 1200, 50000, 1 );
		

		vArray[4] = new Airplane("B-52 Stratofortress", "Boeing", 1952, 5, 650, 8764, 50000, 8);

		
		vArray[5] = new Airplane("SR-71 Blackbird", "Lockheed Martin Aeronautics", 1964, 2, 2200, 3200, 85000, 2);

	
		vArray[6] = new Ship("USS Gerald R. Ford", "Newport News Shipbuilding", 2015, 4660, 35, 100000, "United States Navy");
		
		
		vArray[7] = new Ship("Harmony of the Seas", "STX France SA", 2015, 9080, 29, 226963, "Carnival Cruise Lines");
		
	
		vArray[8] = new Ship("Queen Mary 2", "STX Europe Chantiers de l'Atlantique", 2003, 3948, 35, 148528, "Carnival Cruise Lines");
		

		vArray[9] = new Automobile("Ferrari 458 Italia", "Ferrari", 2009, 2, 210, 562);
		
	
		vArray[10] = new Automobile("Corvette Z06", "Chevrolet", 2015, 4, 206, 650);
		
		
		vArray[11] = new Automobile("Chevy Tahoe", "Chevrolet", 2015, 7, 120, 310);
		

		vArray[12] = new Helicopter("AH-64 Apache", "McDonnell Douglas", 1975, 2, 182, 1180, 2100, 2);
		

		vArray[13] = new Helicopter("USCG MH-60 Jayhawk", "Sikorsky Aircraft", 1989, 6, 205, 802, 5000, 2);
		
	
		vArray[14] = new Helicopter("UH-60 Blackhawk", "Sikorsky Aircraft", 1974, 12, 183, 1380, 19000, 2);
		
		
		
		
		Scanner keyboard = new Scanner(System.in);
		printMenu();
		while (true)
		{
			char choice = keyboard.next().charAt(0);
			
			switch (choice) 
			{
			
			case '1':
				System.out.println("There are " + Vehicle.count + " vehicles in the system");
				break;
				
			case '2':
				for (int i = 0; i < vArray.length; i++)
				{
					System.out.print(vArray[i].getName() + ": ");
					
					if (vArray[i] instanceof Airplane)
						System.out.println("Airplane");
					
					else if (vArray[i] instanceof Amphibious)
						System.out.println("Flying Boat");
					
					else if (vArray[i] instanceof Automobile)
						System.out.println("Automobile");
					
					else if (vArray[i] instanceof Helicopter)
					System.out.println("Helicopter");
					
					else if (vArray[i] instanceof Ship)
						System.out.println("Ship");
					
					else
						System.out.println("Unknown type");
				}
				break;
				
				
			case '3':
				for (int i = 0; i < vArray.length; i++)
				{
					if (vArray[i] instanceof Flyable)
						System.out.println(vArray[i].getName() + " is an airplane");
					
				}
				break;
				
			case '4':
				for (int i = 0; i < vArray.length; i++)
				{
					if (vArray[i] instanceof Floatable)
						System.out.println(vArray[i].getName() + " is a ship");
					
				}
				break;
				
			case '5':
				for (int i = 0; i < vArray.length; i++)
				{
					if (vArray[i] instanceof Flyable 
							&& vArray[i] instanceof Floatable)
						System.out.println(vArray[i].getName() + " is and airplane and a ship");
					
				}
				break;
				
				
			case '6':
				for (int i = 0; i < vArray.length; i++)
				{
					System.out.println(vArray[i].toString());
					System.out.println();
					
				}
				break;
				
			case 'h':
				printMenu();
				break;
				
			case 'q':	
				return;
				
			default:
				System.out.println("Unrecognized choice");
				return;
				
			}
		}
		
	}
	
	
	private static void printMenu()
	{
		System.out.println("Press 1 to see how many vehicles are in the system");
		System.out.println("Press 2 to see the name and kind of each vehicle");
		System.out.println("Press 3 to see which vehicles can fly");
		System.out.println("Press 4 to see which vehicles can float");
		System.out.println("Press 5 to see which vehicles can float AND fly");
		System.out.println("Press 6 to see a description of each vehicle");
		System.out.println("Press 'h' to see the menu again");
		System.out.println("Press 'q' to exit the program");
	}


}

