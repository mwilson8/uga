import java.util.Scanner;

public class MainRubric {

	/* class level variables */
	
	public static final String options = "Pick an option:\n"
			+ "1. List houses for sale\n"
			+ "2. Buy a house\n"
			+ "3. Sell your house.\n"
			+ "4. Show your profile.\n"
			+ "5. Exit\n";

	public static void main(String[] args) {
		
		// Instantiate houses array and the scanner
		Scanner scan = new Scanner(System.in);
		House [] houses = new House[3];
		
		//populate 3 hard-coded houses & person
		houses[0] = new House(House.Color.RED, 2000.0, 10.0, 10.0, 10.0);
		houses[1] = new House(House.Color.BLUE, 15000.256, 20, 20, 20);
		houses[2] = new House(House.Color.YELLOW, 30000.33, 50, 45, 40);

		Person user = new Person("Mr. Rubric", 25, 20000.12345);
		
		//Show profile
		System.out.println("\n----------Test 1 (Person Constructor & toString): ");
		System.err.println("3 point for name; 3 points for age; 4 for cash; 2 for correct format");
		System.out.println(user);
		
		//Check House Constructor - print first house
		System.out.println("\n----------Test 2 (House Constructor): ");
		System.err.println("10 points: red, 10.000 x 3, $2,000");
		System.out.println(houses[0]);	
		
		
		//Try to sell house
		System.out.println("\n----------Test 3 (sellHome() when house is null): ");
		System.err.println("5 points for error message");
		try{
		user.sellHome();
		}catch (Exception e){
			System.out.println("FAILED: " + e.getMessage());
		}
		System.out.println();
		//Buy too expensive house
		System.out.println("\n----------Test 4 (buyHouse() for house you can't afford): ");
		System.err.println("5 points, profile should not have house");
		try{
		user.buyHouse(houses[2]);
		}catch (Exception e){
			System.out.println("FAILED: " + e.getMessage());
		}
		System.out.println(user);
		
		//Resetting user in-case buying expensive house failed
		user = new Person("Mr. Rubric", 25, 20000.12345);
		
		//Buy affordable house
		System.out.println("\n----------Test 5 (buyHouse() success): ");
		System.err.println("15 points: 5 for decrementing cash; 10 for correct house (blue, 20, $15,000)");
		try{
		user.buyHouse(houses[1]);
		}catch (Exception e){
			System.out.println("FAILED: " + e.getMessage());
		}
		System.out.println(user);
		
		//Try to buy another affordable house
		System.out.println("\n----------Test 6 (buyHouse() when you already own one): ");
		System.err.println("5 points: house should still be blue");
		try{
		user.buyHouse(houses[0]);
		}catch (Exception e){
			System.out.println("FAILED: " + e.getMessage());
		}
		System.out.println(user);
		
		//Sell house
		System.out.println("\n----------Test 7 (sellHome() success): ");
		System.err.println("10 points: 5 for sold message, 5 for no house in profile");
		try{
		user.sellHome();
		}catch (Exception e){
			System.out.println("FAILED: " + e.getMessage());
		}
		
		//View Profile
		System.out.println(user);


		//Test constructor with House parameter
		System.out.println("\n----------Test 8 (Person Constructor with House param.): \n");
		System.err.println("10 points: 5 boolean, 5 for correct home (red)");
		House myHouse = new House(House.Color.RED, 2000.0, 10.0, 10.0, 10.0);
		user = new Person("Mr. Rubric", 30, 2000, myHouse);
		System.out.print("isForSale set to false: ");
			if(!myHouse.isForSale())
				System.out.println("Passed \n");
			else
				System.out.println("Failed \n");
		System.out.println(user);
		
		System.out.println("\n----------Test 9 (Equals method): \n");
		System.err.println("10 points");

		
		System.out.print("Equals method test: ");
			if(myHouse.equals(houses[0]))
				System.out.println("Passed");
			else
				System.out.println("Failed");
			
		//Default Person constructor
		System.out.println("\n----------Test 10 (default Person constructor): ");
		System.err.println("5 points");
		user = new Person();
		System.out.println(user);
		
		System.out.println("\n----------Test 11 (House volume & area): ");
		System.err.println("5 points total");
		System.out.print("Volume test: ");
			if(houses[0].volume() == 1000.0)
				System.out.println("Passed");
			else
				System.out.println("Failed");
		System.out.print("Area test: ");
			if(houses[0].area() == 100.0)
				System.out.println("Passed");
			else
				System.out.println("Failed");

		
	}
}
