package tutoring;

public class Circle {

	
	private int radius;
	
	public Circle(int radius){
		this.radius = radius;
		
	}
	
	
	
	
	private void setRadius(int newRadius){
		if (newRadius > 0)
			radius = newRadius;
	}
	
	
	public double getArea(){
		return radius * radius * Math.PI;
	}
	
}
