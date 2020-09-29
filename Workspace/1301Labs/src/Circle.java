

public class Circle {
	

	private double radius;
	private double x;
	private double y;
	
	
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius to the new value only if the new 
	 * value is > 0
	 * @param radius
	 */
	public void setRadius(double radius) {
		if (radius > 0)
			this.radius = radius;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	/**
	 * 
	 * @return the diameter of this circle
	 */
	public double diameter() {
		
		return radius * 2;	
	}
	

	/**
	 * 
	 * @return the area of this circle
	 */
	public double area() {
		
		return Math.PI * radius * radius;
	}

	/**
	 * 
	 * @return the perimeter of this circle
	 */
	public double perimeter() {
		
		return Math.PI * 2 * radius;
	}
	
	/**
	 * Returns <code>true</code> if this circle is a unit circle;
	 * that is, if this circle's x and y are both 0
	 * and this circle's radius is 1, otherwise return 
	 * <code>false</code>
	 * @return if this circle is a unit circle
	 */
	public boolean isUnitCircle() {
		
		return x == 0 && y == 0 && radius == 1;
	}
	
	
	/**
	 * Returns a string representation of this circle in
	 * the form: "center:(x,y)
	 * 			 "radius: radius" 
	 */
	public String toString() {
		
		return "center:(" + x + "," + y + ")\n"
				+ "radius: " + radius;
		
	}
	
	/**
	 * Returns true if this circle and Circle c have the same center
	 * but different radius values
	 * @param c - another circle to be compared with
	 */
	public boolean isConcentric(Circle c){
		return this.x == c.x && this.y == c.y && this.radius != c.radius;
	}
	
	/**
	 * Returns true if this circle and Circle c have the same center
	 * and the same radius value
	 * @param c - another circle to be compared with 
	 */
	public boolean equals(Circle c){
		return this.x == c.x && this.y == c.y && this.radius == c.radius;
	}
	
	/**
	 * Returns true if these two circles share any area
	 * By this implementation's definition: if the distance between
	 * the centers of the two circles is less than the sum of their radius
	 * @param c - another circle to be measured with
	 */
	public boolean intersects(Circle c){
		return this.distance(c) < (this.radius + c.radius);
	}
	
	/**
	 * Returns the distance between the centers of these two circle
	 * by the formula: sqrt( (x1 - x2)^2 + (y1 - y2)^2 )
	 * @param c - another circle to be measured with 
	 */
	public double distance(Circle c){
		return Math.sqrt(Math.pow((this.x - c.x), 2) + Math.pow((this.y - c.y),2));
	}

}