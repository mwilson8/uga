//*******************************************************
// Circle.java
//
// 
//*******************************************************
public class Circle {
	

	   private double radius;   // declare the private double instance  radius
	   private double x;  // declare the private double instance  x
	   private double y;   // declare the private double instance  y
	
	
	//----------------------------------------------
	// getX - returns the value of x
	//----------------------------------------------
	public double getX() {
		
		return x;// Your code goes here	
	}
	

	//----------------------------------------------
	// getY - returns the value of y
	//----------------------------------------------
	public double getY() {
		
		return y;// Your code goes here	
	}
	
	//----------------------------------------------
	// getRadius - returns the value of radius
	//----------------------------------------------
	public double getRadius() {
		
		return radius;// Your code goes here	
	}

	//----------------------------------------------
	// setX - assigns a new value to x
	//----------------------------------------------
	public void setX(double x) {
		
		this.x=x;// Your code goes here	
	}
	

	//----------------------------------------------
	// setY - assigns a new value to y
	//----------------------------------------------
	public void setY(double y) {
		
		this.y=y;// Your code goes here	
	}	
	
	
	//----------------------------------------------
	// setRadius - assigns a new value to radius
	//----------------------------------------------
	public void setRadius(double radius) {
		if(radius>=0)
		{
		this.radius=radius;// Your code goes here
		}
	}
	
	//--------------------------------------------------------
	// diameter - calculates the diameter of the circle
	//--------------------------------------------------------
	public double diameter() {
		
		return radius*2;// Your code goes here	
	}
	

	//--------------------------------------------------------
	// area - returns the area of the circle
	//--------------------------------------------------------
	public double area() {
		
		return Math.PI*radius*radius;// Your code goes here	
	}

	//--------------------------------------------------------
	// perimeter - returns the perimeter of the circle
	//--------------------------------------------------------
	public double perimeter() {
		
		return 2*Math.PI*radius;// Your code goes here	
	}
	
	//--------------------------------------------------------
	// isUnitCircle - return true if the radius of this circle
	//                is 1 and its center is (0,0) and false
	//      	      otherwise.
	//--------------------------------------------------------
	public boolean isUnitCircle() {
		
		return ((radius==1)&&(x==0)&&(y==0));// Your code goes here	
	}
	
	
	//--------------------------------------------------------
	// toString - return a String representation of
	//            this circle in the following format:
	//            center:(x,y)
	//            radius: r
	//--------------------------------------------------------
	public String toString() {
		
		return "center: "+"("+x+","+y+")\n"+"radius: "+radius;// Your code goes here	
	}
	//return true if x, y, and radius of circle is equal to anotherCircle
	public boolean equals(Circle anotherCircle) {
		return(this.x==anotherCircle.x
				&&(this.y==anotherCircle.y)
				&&(this.radius==anotherCircle.radius));
	}
	//returns true if x and y of circle is equal to anotherCircle
	public boolean isConcentric(Circle anotherCircle)
	{
		return ((this.x==anotherCircle.x)&&(this.y==anotherCircle.y));
	}
	//returns the distance between the centers of circle and another circle
	public double distance(Circle anotherCircle)
	{
		return sqrt(pow((this.x-anotherCircle.x),2)+(pow((this.y-anotherCircle.y),2)));
	}
		double sqrt(double number)
		{
			return Math.sqrt(number);
		}
		double pow(double base, double exponent)
		{
			return Math.pow(base, exponent);
		}
	//returns true if the distance between the centers of the two circles is less than the sum of the two radiuses
	public boolean intersects(Circle anotherCircle)
	{
		return ((this.distance(anotherCircle))<(this.radius+anotherCircle.radius));
	}

}

