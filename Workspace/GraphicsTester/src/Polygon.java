import java.awt.Color;
import java.awt.Graphics;

public class Polygon {
	private Color color;
	private int numPoints;
	private int[] xCoordinates = new int [numPoints];
	private int[] yCoordinates = new int [numPoints];
	

	
	public int[] getXCoordinates() {
		return xCoordinates;
	}
	
	public int[] getYCoordinates() {
		return yCoordinates;
	}
	
	public int getNumPoints(){
		return numPoints;
	}
	
	public void setXCoordinates(int x []) 
	{
		this.xCoordinates = x;
	}
	
	public void setYCoordinates(int y []) {
		this.yCoordinates = y;
	}
	
	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
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
		g.fillPolygon(xCoordinates, yCoordinates, numPoints);
		// fillPolygon (x array, y array, number of points)
	}
}
