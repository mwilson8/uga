/*
* [Circle].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Mar 29 2016] *
* 	Last Modified: 	 [Apr 1 2016]
* 
*	Purpose: This class is the container for practicing the
*				creation of methods, for the driver CircleTester.java 
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


public class Circle {
	

	private double radius;  // declare the private double instance  radius
	private double x;       // declare the private double instance  x
	private double y;       // declare the private double instance  y
	
	//----------------------------------------------
	// Class Constructor: set the initial values of
	//                    the instance variables
	//                    for this circle
	//----------------------------------------------	
	public Circle(double x, double y,double  radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;  	   	
	}
	
	public Circle(){
		this(0, 0, 1);
	}
	
	//----------------------------------------------
	// getX - returns the value of x
	//----------------------------------------------
	public double getX() {
		
		return this.x;	
	}
	

	//----------------------------------------------
	// getY - returns the value of y
	//----------------------------------------------
	public double getY() {
		
		return this.y;	
	}
	
	//----------------------------------------------
	// getRadius - returns the value of radius
	//----------------------------------------------
	public double getRadius() {
		
		return this.radius;
	}

	//----------------------------------------------
	// setX - assigns a new value to x
	//----------------------------------------------
	public void setX(double x) {
		
		this.x = x;	
	}
	

	//----------------------------------------------
	// setY - assigns a new value to y
	//----------------------------------------------
	public void setY(double y) {
		
		this.y = y;	
	}	
	
	
	//----------------------------------------------
	// setRadius - assigns a new value to radius
	//----------------------------------------------
	public void setRadius(double radius) {
		
		if (radius >= 0)
		{
			this.radius = radius;
		}
	}
	
	//--------------------------------------------------------
	// diameter - calculates the diameter of the circle
	//--------------------------------------------------------
	public double diameter() {
		
		return (this.radius * 2);	
	}
	

	//--------------------------------------------------------
	// area - returns the area of the circle
	//--------------------------------------------------------
	public double area() {
		
		double area = Math.PI * Math.pow(this.radius, 2);	
		return area;
	}

	//--------------------------------------------------------
	// perimeter - returns the perimeter of the circle
	//--------------------------------------------------------
	public double perimeter() {
		
		double perimeter = 2 * Math.PI * this.radius;
		return perimeter;
	}
	
	//--------------------------------------------------------
	// isUnitCircle - return true if the radius of this circle
	//                is 1 and its center is (0,0) and false
	//      	      otherwise.
	//--------------------------------------------------------
	public boolean isUnitCircle() {
		
		if (this.x == 0 && this.y == 0 && this.radius == 1)
			return true;
		
		else
			return false;
	}
	
	
	//--------------------------------------------------------
	// toString - return a String representation of
	//            this circle in the following format:
	//            center:(x,y)
	//            radius: r
	//--------------------------------------------------------
	public String toString() {
		
		return "center: (" + this.x + "," + this.y + ")" + 	
		"\n\t   radius: " + this.radius;
	}
	
	public boolean equals (Circle anotherCircle)
	{
		if (this.x == anotherCircle.x && this.y == anotherCircle.y && this.radius == anotherCircle.radius)
			return true;
		else
			return false;
	}
	
	public boolean isCocentric (Circle anotherCircle)
	{
		if (this.x == anotherCircle.x && this.y == anotherCircle.y)
			return true;
		else
			return false;
	}
	
	public double distance (Circle anotherCircle)
	{
		double distance = Math.sqrt(Math.pow((this.x - anotherCircle.x), 2) + Math.pow((this.y - anotherCircle.y), 2));
		return distance;
	}
	
	public boolean intersects (Circle anotherCircle)
	{
		//double distance = Math.sqrt(Math.pow((this.x - anotherCircle.x), 2) + Math.pow((this.y - anotherCircle.y), 2));
		if ((this.distance(anotherCircle)) < (this.radius + anotherCircle.radius))
			return true;
		else
			return false;
	}

}