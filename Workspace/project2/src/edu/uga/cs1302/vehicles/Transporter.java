package edu.uga.cs1302.vehicles;

public interface Transporter {

	public int getMaxPassengers();
	public int getTopSpeed();
	
	public void setMaxPassengers(int maxPassengers);
	public void setTopSpeed (int topSpeed);
}
