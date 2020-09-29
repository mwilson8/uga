package edu.uga.cs1302.vehicles;

public class Ship extends Vehicle implements Floatable {
	
	private int tonnage;
	private String owner;
	
	
	public Ship (String name, String manufacturer, 
			int manufactureYear, int maxPassengers, 
			int topSpeed, int tonnage, String owner)
	{
		super(name, manufacturer, manufactureYear, 
				maxPassengers, topSpeed);
		this.setTonnage(tonnage);
		this.setOwner(owner);
	}
	public void setTonnage(int tonnage)
	{
		this.tonnage = tonnage;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public int getTonnage() {
		return this.tonnage;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public String toString()
	{
		String s = super.toString();
		s += "\nTonnage: " + tonnage + " t";
		s += "\nOwner: " + owner;
		
		return s;
	}

}
