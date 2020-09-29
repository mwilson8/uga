package edu.uga.cs1302.vehicles;

public class Automobile extends Vehicle {
	
	private int horsepower;
	
	public Automobile (String name, String manufacturer, 
			int manufactureYear, int maxPassengers, 
			int topSpeed, int horsepower) 
	{
		super (name, manufacturer, manufactureYear, 
				maxPassengers, topSpeed);
		this.horsepower = horsepower;
	}
	
	public void setHorsepower(int horsepower)
	{
		this.horsepower = horsepower;
	}
	
	public int getHorsepower() {
		return this.horsepower;
	}
	
	public String toString() 
	{
		String s = super.toString();
		s += "\nEngine Power " + horsepower + " hp";
		return s;
	}

}
