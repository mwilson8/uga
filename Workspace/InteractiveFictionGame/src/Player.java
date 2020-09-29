/*
* [Chest].java
* 
* 	Author: 		 [Mitch Wilson]
* 	
* 	Submission Date: [Apr 12 2016] 
* 	Last Updated: 	 [Apr 12 2016]
* 
*	Purpose: This program is designed to accompany a driver program
*				as more practice for us in OOP and the implemetation
*				of classes and methods
*				
*  *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia. */

public class Player {

	
	private int xCoordinate = 0;
	private int yCoordinate = 0;
	private Lamp lamp = null;
	private Key key = null;
	
	/*
	 * Mutators
	 */
	
	/**
	 * sets the X-Coordinate of the player
	 * @param x
	 */
	public void setX(int x)
	{
		this.xCoordinate = x;
	}
	
	/**
	 * sets the Y-Coordinate of the player
	 * @param y 
	 */
	public void setY(int y)
	{
		this.yCoordinate = y;
	}
	
	/**
	 * sets the "lamp" variable of this player
	 * @param lamp
	 */
	public void setLamp(Lamp lamp)
	{
		this.lamp = lamp;
	}
	
	/**
	 * sets the "key" variable of this player
	 * @param key
	 */
	public void setKey(Key key)
	{
		this.key = key;
	}
	
	
	
	/*
	 * Accessors
	 */
	
	
	/**
	 * 
	 * @return x-coordinate of this player
	 */
	public int getX()
	{
		return this.xCoordinate;
	}
	
	/**
	 * 
	 * @return y-coordinate of this player
	 */
	public int getY()
	{
		return this.yCoordinate;
	}
	
	/**
	 * 
	 * @return the "lamp" variable of this player
	 */
	public Lamp getLamp()
	{
		return this.lamp;
	}
	
	/**
	 * 
	 * @return the "lamp" variable of this player
	 */
	public Key getKey()
	{
		return this.key;
	}
	
}
