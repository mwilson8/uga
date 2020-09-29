import java.awt.Color;
import java.awt.Graphics;

public class MyGraphics
{

	
	
	
    /*Define your methods here*/
	//example: public void drawHouse(Graphics g) { ... }
	
	
	/*Example method - draws 2 Line objects*/
	public void drawLine(Graphics g)
	{
		Line myLine = new Line();
		myLine.setX1(100);
		myLine.setX2(100);
		myLine.setY1(200);
		myLine.setY1(200);
		myLine.setColor(Color.RED);
		myLine.draw(g);
	}
	
	public void drawCircle(Graphics g)
	{
		Circle circle1 = new Circle();
		circle1.setXCoordinate(100);
		circle1.setYCoordinate(100);
		circle1.setRadius(50);
		circle1.setColor(Color.RED);
		circle1.draw(g);
	}
	
	public void drawRectangle(Graphics g)
	{
		Rectangle rec1 = new Rectangle();
		rec1.setXCoordinate(100);
		rec1.setYCoordinate(100);
		rec1.setWidth(50);
		rec1.setHeight(50);
		rec1.setColor(Color.RED);
		rec1.draw(g);
	}
	
	public void drawPolygon(Graphics g)
	{
		int [] xCoordinates = {50, 100, 150};
		int [] yCoordinates = {50, 100, 50};
		Polygon triangle = new Polygon();
		triangle.setNumPoints(3);
		triangle.setXCoordinates(xCoordinates);
		triangle.setYCoordinates(yCoordinates);
		triangle.setColor(Color.RED);
		triangle.draw(g);
	}
	
	public void drawHouse (Graphics g)
	{
		Rectangle house = new Rectangle();
		house.setXCoordinate(150);
		house.setYCoordinate(250);
		house.setWidth(200);
		house.setHeight(200);
		house.setColor(Color.RED);
		house.draw(g);
		
		int [] xCoordinates = {150, 250, 350};
		int [] yCoordinates = {250, 100, 250};
		Polygon roof = new Polygon();
		roof.setNumPoints(3);
		roof.setXCoordinates(xCoordinates);
		roof.setYCoordinates(yCoordinates);
		roof.setColor(Color.BLACK);
		roof.draw(g);
		
		Rectangle door = new Rectangle();
		door.setXCoordinate(225);
		door.setYCoordinate(350);
		door.setWidth(50);
		door.setHeight(100);
		door.setColor(Color.YELLOW);
		door.draw(g);

	}
	
	public void drawSmile(Graphics g)
	{
		Circle face = new Circle();
		face.setXCoordinate(100);
		face.setYCoordinate(100);
		face.setRadius(300);
		face.setColor(Color.YELLOW);
		face.draw(g);
		
		Circle leftEye = new Circle();
		leftEye.setXCoordinate(150);
		leftEye.setYCoordinate(150);
		leftEye.setRadius(50);
		leftEye.setColor(Color.DARK_GRAY);
		leftEye.draw(g);
		
		Circle rightEye = new Circle();
		rightEye.setXCoordinate(300);
		rightEye.setYCoordinate(150);
		rightEye.setRadius(50);
		rightEye.setColor(Color.DARK_GRAY);
		rightEye.draw(g);
		
		Polygon mouth = new Polygon();
		int [] xCoordinates = {150, 250, 350, 350, 250, 150};
		int [] yCoordinates = {250, 300, 250, 300, 350, 300};
		mouth.setNumPoints(6);
		mouth.setXCoordinates(xCoordinates);
		mouth.setYCoordinates(yCoordinates);
		mouth.setColor(Color.DARK_GRAY);
		mouth.draw(g);

	}
	
	public void drawFlower(Graphics g)
	{
		Circle petal1 = new Circle();
		petal1.setXCoordinate(75);
		petal1.setYCoordinate(75);
		petal1.setRadius(200);
		petal1.setColor(Color.PINK);
		petal1.draw(g);
		
		Circle petal2 = new Circle();
		petal2.setXCoordinate(225);
		petal2.setYCoordinate(75);
		petal2.setRadius(200);
		petal2.setColor(Color.PINK);
		petal2.draw(g);
		
		Circle petal3 = new Circle();
		petal3.setXCoordinate(225);
		petal3.setYCoordinate(225);
		petal3.setRadius(200);
		petal3.setColor(Color.PINK);
		petal3.draw(g);
		
		Circle petal4 = new Circle();
		petal4.setXCoordinate(75);
		petal4.setYCoordinate(225);
		petal4.setRadius(200);
		petal4.setColor(Color.PINK);
		petal4.draw(g);
		
		Circle center = new Circle();
		center.setXCoordinate(175);
		center.setYCoordinate(175);
		center.setRadius(150);
		center.setColor(Color.YELLOW);
		center.draw(g);
	}
	
	
	
	
	
}
