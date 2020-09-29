import java.awt.Color;
import java.awt.Graphics;

public class Circle {
	private Color color;
	private int radius, xCoordinate, yCoordinate;
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setXCoordinate(int x) {
		this.xCoordinate = x;
	}
	
	public void setYCoordinate(int y) {
		this.yCoordinate = y;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(xCoordinate, yCoordinate, radius, radius);
		// fillOval (x, y, width, height [same for circle])
	}
}
