package edu.uga.cs1302.vehicles;

public class Airplane extends Vehicle implements Flyable
{
	private int maxAltitude;
	private int maxRange;
	private int numEngines;

	public Airplane (String name, String manufacturer, int manufactureYear, int maxPassengers, int topSpeed, int maxRange, int maxAltitude, 
		int numEngines)
	{
		super (name, manufacturer, manufactureYear,maxPassengers, topSpeed);
		this.setMaxAltitude(maxAltitude);
		this.setMaxRange(maxRange);
		this.setNumEngines(numEngines);
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
	
	public int getMaxRange() 
	{
		return this.maxRange;
	}
	
	public int getMaxAltitude() 
	{
		return this.maxAltitude;
	}
	
	public int getNumEngines() 
	{
		return this.numEngines;
	}

	public String toString() 
	{
		String s = super.toString();
		s += "\nNumberof Engines: " + numEngines;
		s += "\nMax Altitude: " + maxAltitude + " ft";
		s += "\nMax Range: " + maxRange + " ft";
		
		return s;
	}

}
