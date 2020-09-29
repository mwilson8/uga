package edu.uga.cs1302.vehicles;

public abstract class Vehicle implements Transporter {
	
	private String name;
	private String manufacturer;
	private int manYear;
	private int maxPassengers;
	private int topSpeed;
	
	
	public static int count=0;
	
	public Vehicle () {
		name = "";
		manufacturer = "";
		manYear = 0000;
		maxPassengers = 0;
		topSpeed = 100;
	}
	
	public Vehicle(String name, String manufacturer, int manufactureYear, int maxPassengers, int topSpeed) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.manYear = manufactureYear;
		this.maxPassengers = maxPassengers;
		this.topSpeed = topSpeed;
		count++;
		
	}
	
	public int getMaxPassengers() {
		return this.maxPassengers;
	}
	
	public int getTopSpeed() {
		return this.topSpeed;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public int getManYear() {
		return this.manYear;
	}
	
	public void setMaxPassengers(int maxPassengers){
		this.maxPassengers = maxPassengers;
	}
	
	public void setTopSpeed(int topSpeed){
		this.topSpeed = topSpeed;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	
	public void setManYear(int manYear)
	{
		this.manYear = manYear;
	}
	
	public String toString()
	{
		String s = "Name: " + name;
		s += "\nManufacturer: " + manufacturer;
		s += "\nManufacture Year: " + manYear;
		s += "\nMax Passengers: " + maxPassengers;
		s += "\nTop Speed: " + topSpeed + " mph";
		return s;
	}

}
