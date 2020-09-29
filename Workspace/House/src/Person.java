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
		
		name = "John L.";
		age = 21;
		cash = 0.01;
		house = null;
		
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
		house = null;

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
		
		this.name = name;
		this.age = age;
		this.cash = cash;
		this.house = house;
		house.setForSale(false);

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
		s += "Name: " + this.getName() +"\n";
		s += "Age: " + this.getAge() + " years old\n";
		s += "Cash: $ " + decimalFormatObj.format(getCash()) + "\n";
		if (this.house != null)
			s += "Home:\n" + house.toString();
		return s;
	
	}
	
	/* Accessors / Getters */
	
	/**
	 * @return the person's name 
	 */
	public String getName() {
		
		// Your code here

		return this.name; // replace this line with your own code
	}
	/**
	 * @return the person's age
	 */
	public int getAge() {
		
		// Your code here

		return this.age; // replace this line with your own code
	}
	
	/**
	 * @return the amount of cash this person has
	 */
	public double getCash() {
		
		// Your code here

		return this.cash; // replace this line with your own code
	}
	
	/**
	 * @return a reference the house object currently owned by this person
	 */
	public House getHouse() {
		
		// Your code here
		
		
		return this.house; // replace this line with your own code
	}
	

	/**
	 * @return true if this person has a home
	 */
	public boolean ownsAHouse() {
		
		if (this.house == null)
			return false; 
		
		else
			return true;
	}
	
	/* Mutators */
	
	/**
	 * @param amount : the amount of cash to give this person
	 */
	public void addCash(double amount) {
		
		this.cash += amount;

	}
	
	/**
	 * If this person owns home, put it up for sale and pay the person the price of the home.
	 */
	public void sellHome() {
		
		if (this.house != null)
		{
			this.house.setForSale(true);
			this.addCash(this.house.getPrice());
			System.out.println(name + " has sold their house to the market!");
			
		}
		
	}

	/**
	 * This method lets the person buy a home if they do not already have a home, have the cash and the home is for sale.
	 * If the person successfully buys a home, their cash is decreased by the cost of the home.
	 * @param h the house to be bought
	 */
	public void buyHouse(House h) {
		
		if (this.house == null
				&& this.getCash() >= h.getPrice()
				&& h.isForSale())
		{
			this.addCash(-h.getPrice());
			h.setForSale(false);
			this.house = h;
			System.out.println(name + " is now a proud homeowner!");
			
		}

	}
}
