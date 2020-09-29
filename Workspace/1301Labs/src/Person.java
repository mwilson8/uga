
import java.text.DecimalFormat;


/**
 * Class representing a person (a human user) on a real estate market.
 * A person has a name, age, cash, and (potentially) a house.
 */
public class Person {

	/* Instance variables */

	private String name;
	private int age;
	private double cash;
	private House house;
	
	/* Constructors */

	/**
	 * The Default constructor creates a 20 year old John L. with a penny of cash and no home.
	 */
	public Person() {
		
		this ("John L.", 21, 0.01);
		
	}
	/**
	 * A second constructor that enables the creation of a custom instance of the Person class. 
	 * The house instance variable is set to null.
	 * @param name : the person's name 
	 * @param age : the person's age
	 * @param cash : the amount of money the person has
	 */
	public Person(String name, int age, double cash) {
		
		this.name = name;
		this.age = age;
		this.cash = cash;

	}

	/**
	 * A third constructor including a parameter for the persons house. The house is also updated
	 * because it is no longer for sale.
	 * @param name : the person's name 
	 * @param age : the person's age
	 * @param cash : the amount of money the person has
	 * @param house : the person's home
	 */
	public Person(String name, int age, double cash, House house) {
		
		this (name, age, cash);
		this.house = house;

	}
	
	/**
	 * Show the name and age of the person as well as their assets (cash and home if they have one).
	 * E.g.
	 * Name: John L.
	 * Age: 20 years old
	 * Cash: $ 0.01
	 */
	@Override
	public String toString() {
		DecimalFormat decimalFormatObj = (DecimalFormat) DecimalFormat.getInstance();
        decimalFormatObj.setDecimalSeparatorAlwaysShown(true);
        decimalFormatObj.setMinimumFractionDigits(2);
        decimalFormatObj.setMaximumFractionDigits(2);
		
        String s = "";
		s += "Name: " + name + "\n";
		s += "Age: " + age + " years old\n";
		
		s += "Cash: $" + decimalFormatObj.format(cash) + "\n";
		
		//print house if not null
		if (house != null){
			s += "House: \n";
			s += house.toString();
		}
		
		return s;
	}
	
	/* Accessors / Getters */
	
	/**
	 * @return the person's name 
	 */
	public String getName() {
		
		return name;
	}
	/**
	 * @return the person's age
	 */
	public int getAge() {
		
		return age;
	}
	
	/**
	 * @return the amount of cash this person has
	 */
	public double getCash() {
		
		return cash;
	}
	
	/**
	 * @return a reference the house object currently owned by this person
	 */
	public House getHouse() {
		
		return house;
	}
	

	/**
	 * @return true if this person has a home
	 */
	public boolean ownsAHouse() {
		
		return house != null;
	}
	
	/* Mutators */
	
	/**
	 * @param amount : the amount of cash to give this person
	 */
	public void addCash(double amount) {
		
		cash += amount;

	}
	
	/**
	 * If this person owns home, put it up for sale and pay the person the price of the home.
	 */
	public void sellHome() {
		
		if (house == null){
			System.out.println(name + " has no home to sell");
			return;
		}
		
		cash += house.getPrice();
		house.setForSale(true);
		house = null;
		System.out.println(name + " has sold their home");
		
	}

	/**
	 * This method lets the person buy a home if they do not already have a home, have the cash and the home is for sale.
	 * If the person successfully buys a home, their cash is decreased by the cost of the home.
	 * @param h the house to be bought
	 */
	public void buyHouse(House h) {
		
		if (house != null){
			System.out.println(name + " already owns a house");
			return;
		}
		if (cash < h.getPrice()){
			System.out.println(name + " can't afford this house");
			return;
		}
		
		cash -= h.getPrice();
		house = h;
		house.setForSale(false);

	}
}
