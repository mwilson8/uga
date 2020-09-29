import java.awt.Color;
import java.awt.Graphics;

public class Rectangle {
	private Color color;
	private int height, width, xCoordinate, yCoordinate;
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setXCoordinate(int x) {
		this.xCoordinate = x;
	}
	
	public void setYCoordinate(int y) {
		this.yCoordinate = y;
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
		g.fillRect(xCoordinate, yCoordinate, width, height);
		// fillRect(x, y, width, height)
	}
}

