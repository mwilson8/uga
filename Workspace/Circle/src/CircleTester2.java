//*******************************************************
// CircleTester.java
//
//
//  A client to test the functionality of objects
//  of the class Circle
// 
//*******************************************************
public class CircleTester2{
	
	public static void main(String[] args) {
		
		Circle circle1 = new Circle();
		Circle circle2 = new Circle();
		circle1.setX(0.0);
		circle1.setY(0.0);
		circle1.setRadius(2);
		circle2.setX(2.0);
		circle2.setY(1.0);
		circle2.setRadius(1);
		System.out.println("circle1="+circle1);
		System.out.println("circle2="+circle2);
		
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
		System.out.println("circle1="+circle1);
		// set the circle2 radius to 5.3
		circle2.setRadius(5.3);
		// print circle2 characteristics (center and radius), use a statement similar to the first and
		// second println statements
		System.out.println("circle2="+circle2);
		// print circle1 diameter, area and perimeter
		System.out.println("circle 1 diameter:\t"+circle1.diameter());
		System.out.println("circle 1 area:\t\t"+circle1.area());
		System.out.println("circle 1 perimeter:\t"+circle1.perimeter());
		// print circle2 diameter, area and perimeter
		System.out.println("circle 2 diameter:\t"+circle2.diameter());
		System.out.println("circle 2 area:\t\t"+circle2.area());
		System.out.println("circle 2 perimeter:\t"+circle2.perimeter());
		// display whether circle1 is a unit circle
		if(circle1.isUnitCircle()==true){
			System.out.println("Circle1 is a unit cirlce.");
		}
		else{
			System.out.println("Circle1 is not a unit circle");
		}
		// display whether circle2 is a unit circle
		if(circle2.isUnitCircle()==true){
			System.out.println("Circle2 is a unit cirlce.");
		}
		else{
			System.out.println("Circle2 is not a unit circle");
		}
		// your additional tests should be placed below here
		
		//getX Tests
		if(circle1.getX()>-0.1&&circle1.getX()<0.1){
			System.out.println("Passed getX method.");
		}
		else{
			System.out.println("Failed getX method.");
		}
		if(circle2.getX()>1.99&&circle2.getX()<2.01){
			System.out.println("Passed getX method.");
		}
		else{
			System.out.println("Failed getX method.");
		}
		if(circle1.getX()>=0.0){
			System.out.println("Passed getX method.");
		}
		else{
			System.out.println("Failed getX method.");
		}
		//Test 1
		System.out.println("Test 1");
		System.out.println("Circle1 x = "+circle1.getX()+"\tCircle1 y = "+circle1.getY());
		System.out.println("Circle1 radius = "+circle1.getRadius());
		circle1.setX(0.0); circle1.setY(0.0); circle1.setRadius(1.0);
		System.out.println("Circle1 new x = "+circle1.getX()+"\tCircle1 new y = "+circle1.getY()); 
		System.out.println("Circle1 new radius = "+circle1.getRadius());
		System.out.println("Circle1 diameter = "+circle1.diameter());
		System.out.println("Circle1 area = "+circle1.area());
		System.out.println("Circle1 perimeter = "+circle1.perimeter());
		if(circle1.isUnitCircle()){
			System.out.println("Circle1 is a unit cirlce.");
		}
		else{
			System.out.println("Circle1 is not a unit circle");
		}
		System.out.println();
		System.out.println("Circle2 x = "+circle2.getX()+"\tCircle2 y = "+circle2.getY()); 
		System.out.println("Circle 2 radius = "+circle2.getRadius());
		circle2.setX(0.0); circle2.setY(0.0); circle2.setRadius(1.0);
		System.out.println("Circle2 new x = "+circle2.getX()+"\tCircle2 new y = "+circle2.getY());
		System.out.println("Circle2 new radius = "+circle1.getRadius());
		System.out.println("Circle2 area = "+circle2.area());
		System.out.println("Circle2 perimeter = "+circle1.perimeter());
		if(circle2.isUnitCircle()==true){
			System.out.println("Circle2 is a unit cirlce.");
		}
		else{
			System.out.println("Circle2 is not a unit circle");
		}
		if(circle1.equals(circle2)){
			System.out.println("Circle1 and circle2 are twins!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT the same.");
		}
		if(circle1.isCocentric(circle2)){
			System.out.println("Circle1 and circle2 are cocentric!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT cocentric.");
		}
		System.out.println("The distance between circle1 and circle2 is :"+circle1.distance(circle2));
		if(circle1.intersects(circle2)){
			System.out.println("Circle1 and circle2 intersect.");
		}
		else{
			System.out.println("Circle1 and circle2 do NOT intersect.");
		}
		//Test 2
		System.out.println("Test 2");
		circle1.setX(7.0); circle1.setY(5.0); circle1.setRadius(4.0);
		System.out.println("Circle1 new x = "+circle1.getX()+"\tCircle1 new y = "+circle1.getY()); 
		System.out.println("Circle1 new radius = "+circle1.getRadius());
		System.out.println("Circle1 diameter = "+circle1.diameter());
		System.out.println("Circle1 area = "+circle1.area());
		System.out.println("Circle1 perimeter = "+circle1.perimeter());
		if(circle1.isUnitCircle()){
			System.out.println("Circle1 is a unit cirlce.");
		}
		else{
			System.out.println("Circle1 is not a unit circle");
		}
		System.out.println();
		System.out.println("Circle2 x = "+circle2.getX()+"\tCircle2 y = "+circle2.getY()); 
		System.out.println("Circle 2 radius = "+circle2.getRadius());
		circle2.setX(-4.0); circle2.setY(-4.0); circle2.setRadius(4.0);
		System.out.println("Circle2 new x = "+circle2.getX()+"\tCircle2 new y = "+circle2.getY());
		System.out.println("Circle2 new radius = "+circle1.getRadius());
		System.out.println("Circle2 area = "+circle2.area());
		System.out.println("Circle2 perimeter = "+circle1.perimeter());
		if(circle2.isUnitCircle()==true){
			System.out.println("Circle2 is a unit cirlce.");
		}
		else{
			System.out.println("Circle2 is not a unit circle");
		}
		if(circle1.equals(circle2)){
			System.out.println("Circle1 and circle2 are twins!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT the same.");
		}
		if(circle1.isCocentric(circle2)){
			System.out.println("Circle1 and circle2 are cocentric!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT cocentric.");
		}
		System.out.println("The distance between circle1 and circle2 is :"+circle1.distance(circle2));
		if(circle1.intersects(circle2)){
			System.out.println("Circle1 and circle2 intersect.");
		}
		else{
			System.out.println("Circle1 and circle2 do NOT intersect.");
		}
		//Test 3
		System.out.println("Test 3");
		circle1.setX(0.0); circle1.setY(5.0); circle1.setRadius(5.0);
		System.out.println("Circle1 new x = "+circle1.getX()+"\tCircle1 new y = "+circle1.getY()); 
		System.out.println("Circle1 new radius = "+circle1.getRadius());
		System.out.println("Circle1 diameter = "+circle1.diameter());
		System.out.println("Circle1 area = "+circle1.area());
		System.out.println("Circle1 perimeter = "+circle1.perimeter());
		if(circle1.isUnitCircle()){
			System.out.println("Circle1 is a unit cirlce.");
		}
		else{
			System.out.println("Circle1 is not a unit circle");
		}
		System.out.println();
		System.out.println("Circle2 x = "+circle2.getX()+"\tCircle2 y = "+circle2.getY()); 
		System.out.println("Circle 2 radius = "+circle2.getRadius());
		circle2.setX(0.0); circle2.setY(-5.0); circle2.setRadius(5.0);
		System.out.println("Circle2 new x = "+circle2.getX()+"\tCircle2 new y = "+circle2.getY());
		System.out.println("Circle2 new radius = "+circle1.getRadius());
		System.out.println("Circle2 area = "+circle2.area());
		System.out.println("Circle2 perimeter = "+circle1.perimeter());
		if(circle2.isUnitCircle()==true){
			System.out.println("Circle2 is a unit cirlce.");
		}
		else{
			System.out.println("Circle2 is not a unit circle");
		}
		if(circle1.equals(circle2)){
			System.out.println("Circle1 and circle2 are twins!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT the same.");
		}
		if(circle1.isCocentric(circle2)){
			System.out.println("Circle1 and circle2 are cocentric!");
		}
		else{
			System.out.println("Circle1 and circle2 are NOT cocentric.");
		}
		System.out.println("The distance between circle1 and circle2 is :"+circle1.distance(circle2));
		if(circle1.intersects(circle2)){
			System.out.println("Circle1 and circle2 intersect.");
		}
		else{
			System.out.println("Circle1 and circle2 do NOT intersect.");
		}
	}
}

