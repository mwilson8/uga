/*
* [CircleTester].java
* 
* 	Author: [Mitch Wilson]
* 
* 	Submission Date: [Mar 29 2016] *
* 	Last Modified: 	 [Apr 1 2016]
* 
*	Purpose: This program is a client to test the functionality
*			 of objects of the class Circle
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


public class CircleTester{
	
	public static void main(String[] args) {
		
		Circle circle1= new Circle(0.0,0.0,2);
		Circle circle2= new Circle(2.0,1.0,1);
		
		//System.out.println("circle1="+circle1);
		//System.out.println("circle2="+circle2);
		
		// If the method setRadius is implemented correctly,
		// a call to setRadius with a negative number
		// will not change the value of the circle's radius.
		//
		circle1.setRadius(-2.0); 
		
		//
		// Reset the center of circle1 (-3.0,4.0)
		//
		circle1.setX(-3.0);
		circle1.setY(4.0);
		
		
		// print circle1 characteristics (center and radius), use a statement similar 
		// to the previous println statements. Note that is not necessary to call
		//the method toString, why?
		System.out.println("Circle 1 = " + circle1);
		
		// set the circle2 radius to 5.3
		circle2.setRadius(5.3);
		
		// print circle2 characteristics (center and radius), use a statement similar to the first and
		// second println statements
		System.out.println("Circle 2 = " + circle2);
		
		// print circle1 diameter, area and perimeter
		System.out.println("Circle 1 diameter: " + circle1.diameter());
		System.out.println("Circle 1 area: " + circle1.area());
		System.out.println("Circle 1 perimeter: " + circle1.perimeter());
		
		// print circle2 diameter, area and perimeter
		System.out.println("Circle 2 diameter: " + circle2.diameter());
		System.out.println("Circle 2 area: " + circle2.area());
		System.out.println("Circle 2 perimeter: " + circle2.perimeter());
		
		// display whether circle1 is a unit circle
		System.out.println("Circle 1 is a unit circle: " + circle1.isUnitCircle());
		
		// display whether circle2 is a unit circle
		System.out.println("Circle 2 is a unit circle: " + circle2.isUnitCircle());
		
		// your additional tests should be placed below here
		
		
		circle1.setRadius(3);
		circle1.setX(0);
		circle1.setY(0);
		
		circle2.setRadius(4);
		circle2.setX(0);
		circle2.setY(0);
		
		if (circle1.isUnitCircle() == false 
				&& circle1.diameter() == 6
				&& circle2.equals(circle1) == false)
			System.out.println("Test 1 passed..");
		else
			System.out.println("Test 1 failed..");
		
		circle1.setRadius(-1);
		circle2.setRadius(-1);
		
		if (circle1.getRadius() == -1
				|| circle2.getRadius() == circle1.getRadius())
			System.out.println("Test 2 failed..");
		else
			System.out.println("Test 2 passed..");
		
		circle1.setRadius(1);
		circle1.setX(0);
		circle1.setY(0);
		
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(0);
		
		if (circle1.isCocentric(circle2)
				&& circle1.equals(circle2)
				&& circle1.isUnitCircle() == true
				&& circle2.isUnitCircle() == true)
			System.out.println("Test 3 passed..");
		else
			System.out.println("Test 3 failed..");
		
		circle1.setRadius(1);
		circle1.setX(1);
		circle1.setY(0);
		
		circle2.setRadius(2);
		circle2.setX(0);
		circle2.setY(0);

		if (circle1.distance(circle2) == 1
				&& circle1.equals(circle2) == false
				&& circle1.intersects(circle2) == true)
			System.out.println("Test 4 passed..");
		else
			System.out.println("Test 4 failed..");
		
	}
	
	
	
}