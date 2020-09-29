package edu.uga.cs1302.vehicles;

public class Amphibious extends Vehicle implements Flyable, Floatable {
	
	private int tonnage;
	private int maxRange;
	private int maxAltitude;
	private int numEngines;
	
	public Amphibious(String name, String manufacturer,
			int manufacturerYear, int maxPassengers, int topSpeed,
			int tonnage, int maxRange, int maxAltitude, int numEngines)  
	{
		super (name, manufacturer, manufacturerYear, maxPassengers, topSpeed);
		this.tonnage = tonnage;
		this.maxRange = maxRange;
		this.maxAltitude = maxAltitude;
		this.numEngines = numEngines;
		
	}
	
	
	public void setTonnage(int tonnage)
	{
		this.tonnage = tonnage;
	}
	public void setMaxRange(int maxRange)
	{
		this.maxRange = maxRange;
	}
	public void setMaxAltitude(int maxAltitude)
	{
		this.maxAltitude = maxAltitude;
	}
	
	public void setNumEngines(int numEngines)
	{
		this.numEngines = numEngines;
	}

	public int getTonnage() {
		return this.tonnage;
	}
	
	public int getMaxRange() {
		return this.maxRange;
	}
	
	public int getMaxAltitude() {
		return this.maxAltitude;
	}
	
	public int getNumEngines() {
		return this.numEngines;
	}
	
	public String toString()
	{
		String s = super.toString();
		s += "\nNumber of Engines: " + numEngines;
		s += "\nMax Altitude: " + maxAltitude + " ft";
		s += "\nMax Range: " + maxRange + " ft";
		s += "\nTonnage: " + tonnage + " t";
		
		return s;
	}
	
	
}
