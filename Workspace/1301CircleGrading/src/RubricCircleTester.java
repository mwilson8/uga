import java.lang.reflect.Method;

//*******************************************************
// RubricCircleTester.java
//
//
//  A client to test the functionality of objects
//  of the class Circle
//
// Note: Circles are printed using the notation (x,y,radius) 
//*******************************************************
public class RubricCircleTester{
     
     
    private static double error = 0.0;  // for some tests, this can be adjusted (in case rounding causes slight differences in output). 
     
    
    public static boolean unitCircleTest()
    {
    	System.out.println("Unit Circle Test:");
    	Method setXMethod = null, setYMethod = null, 
    			setRadiusMethod = null, isUnitCircleMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			isUnitCircleMethod = Circle.class.getMethod("isUnitCircle", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (isUnitCircleMethod == null){
    		System.out.println("\tERROR --isUnitCircle method doesn't exist");
    		return false;
    	}

    	try{
        Circle circle1= new Circle();  // not a unit circle
        //circle1.setX(1);
        //circle1.setY(1);
        //circle1.setRadius(1);
			setXMethod.invoke(circle1, 1);
			setYMethod.invoke(circle1, 1);
			setRadiusMethod.invoke(circle1, 1);
        
        Circle circle2= new Circle();  // a unit circle
        //circle2.setX(0);
        //circle2.setY(0);
        //circle2.setRadius(1);
        	setXMethod.invoke(circle2, 0);
        	setYMethod.invoke(circle2, 0);
        	setRadiusMethod.invoke(circle2, 1);
        	
        Circle circle3= new Circle(); // not a unit circle
        //circle3.setX(5);
        //circle3.setY(6);
        //circle3.setRadius(10);
	        setXMethod.invoke(circle3, 5);
	    	setYMethod.invoke(circle3, 6);
	    	setRadiusMethod.invoke(circle3, 10);
	    	
        //boolean testA = (false == circle1.isUnitCircle());
        //boolean testB = (true == circle2.isUnitCircle());
        //boolean testC = (false == circle3.isUnitCircle());
	        boolean testA = (false == (boolean)isUnitCircleMethod.invoke(circle1, (Object [])null));
	        boolean testB = (true == (boolean)isUnitCircleMethod.invoke(circle2, (Object [])null));
	        boolean testC = (false == (boolean)isUnitCircleMethod.invoke(circle3, (Object [])null));
         
         
        
        System.out.println("\tTest Passed: " + testA + "\t-> (1,1,1)  is a unit circle: " + (boolean)isUnitCircleMethod.invoke(circle1, (Object [])null));
        System.out.println("\tTest Passed: " + testB + "\t-> (0,0,1)  is a unit circle: " + (boolean)isUnitCircleMethod.invoke(circle2, (Object [])null));
        System.out.println("\tTest Passed: " + testC + "\t-> (5,6,10) is a unit circle: " + (boolean)isUnitCircleMethod.invoke(circle3, (Object [])null));
                 
        return testA && testB && testC;
    	}catch (Exception e){return false;}
    }
 
     
    public static boolean xTest()
    {
    	
    	System.out.println("\nGetting/Setting x:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			getXMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			getXMethod = Circle.class.getMethod("getX", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (getXMethod == null){
    		System.out.println("\tERROR --getX method doesn't exist");
    		return false;
    	}
    	
    	try{
        double x;
        double xReturn;
        Circle circle= new Circle();
        //circle.setX(20);
        //circle.setY(1.0);
        //circle.setRadius(1.0);
        	setXMethod.invoke(circle, 20);
        	setYMethod.invoke(circle, 1.0);
        	setRadiusMethod.invoke(circle, 1.0);
        
   
        x = 1.0;
        //circle.setX(x);
        //xReturn =  circle.getX();
        	setXMethod.invoke(circle, x);
        	xReturn = (double)getXMethod.invoke(circle, (Object[]) null);
        boolean testA =   (x == xReturn);
        System.out.println("\tTest Passed: " + testA + "\t-> value set: " + x + "\t x returned: " + xReturn);
         
 
        x = -1.0;
        //circle.setX(x);
        //xReturn =  circle.getX();
	        setXMethod.invoke(circle, x);
	    	xReturn = (double)getXMethod.invoke(circle, (Object[]) null);
        boolean testB =   (x == xReturn);
        System.out.println("\tTest Passed: " + testB + "\t-> value set: " + x + "\t x returned: " + xReturn);
 
         
        x = 0.0;
        //circle.setX(x);
        //xReturn =  circle.getX();
	        setXMethod.invoke(circle, x);
	    	xReturn = (double)getXMethod.invoke(circle, (Object[]) null);
        boolean testC =   (x == xReturn);
        System.out.println("\tTest Passed: " + testC + "\t-> value set: " + x + "\t x returned: " + xReturn);
 
 
        x = 1138.25;
        //circle.setX(x);
        //xReturn =  circle.getX();
	        setXMethod.invoke(circle, x);
	    	xReturn = (double)getXMethod.invoke(circle, (Object[]) null);
        boolean testD =   (x == xReturn);
        System.out.println("\tTest Passed: " + testD + "\t-> value set: " + x + "\t x returned: " + xReturn);
 
        return testA && testB && testC && testD;
    	}catch(Exception e){return false;}
    }
     
 
    public static boolean yTest()
    {
    	System.out.println("\nGetting/Setting y:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			getYMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			getYMethod = Circle.class.getMethod("getY", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (getYMethod == null){
    		System.out.println("\tERROR --getY method doesn't exist");
    		return false;
    	}
    	
    	try{
        double y;       
        double yReturn;
        Circle circle= new Circle();
        //circle.setX(20);
        //circle.setY(1.0);
        //circle.setRadius(1.0);
	        setXMethod.invoke(circle, 20);
	    	setYMethod.invoke(circle, 1.0);
	    	setRadiusMethod.invoke(circle, 1.0);
        
         
        y = 1.0;
        //circle.setY(y);
        //yReturn =  circle.getY();
	        setYMethod.invoke(circle, y);
	    	yReturn = (double)getYMethod.invoke(circle, (Object[]) null);
        boolean testA =   (y == yReturn);
        System.out.println("\tTest Passed: " + testA + "\t-> value set: " + y + "\t y returned: " + yReturn);
         
 
        y = -1.0;
        //circle.setY(y);
        //yReturn =  circle.getY();
        	setYMethod.invoke(circle, y);
        	yReturn = (double)getYMethod.invoke(circle, (Object[]) null);
        boolean testB =   (y == yReturn);
        System.out.println("\tTest Passed: " + testB + "\t-> value set: " + y + "\t y returned: " + yReturn);
         
        y = 0.0;
        //circle.setY(y);
        //yReturn =  circle.getY();
        	setYMethod.invoke(circle, y);
        	yReturn = (double)getYMethod.invoke(circle, (Object[]) null);
        boolean testC =   (y == yReturn);
        System.out.println("\tTest Passed: " + testC + "\t-> value set: " + y + "\t y returned: " + yReturn);
 
 
        y = 1000.80;
        //circle.setY(y);
        //yReturn =  circle.getY();
        	setYMethod.invoke(circle, y);
        	yReturn = (double)getYMethod.invoke(circle, (Object[]) null);
        boolean testD =   (y == yReturn);
        System.out.println("\tTest Passed: " + testD + "\t-> value set: " + y + "\t y returned: " + yReturn);
 
    	
        return testA && testB && testC && testD;
    	}catch (Exception e){return false;}
    }
         
 
    public static boolean radiusTest()
    {
    	System.out.println("\nGetting/Setting radius:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			getRadiusMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			getRadiusMethod = Circle.class.getMethod("getRadius", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (getRadiusMethod == null){
    		System.out.println("\tERROR --getRadius method doesn't exist");
    		return false;
    	}
    	
    	try{
        double radius;      
        double radiusReturn;
        Circle circle= new Circle();
        //circle.setX(20);
        //circle.setY(1.0);
        //circle.setRadius(1.0);
	        setXMethod.invoke(circle, 20);
	    	setYMethod.invoke(circle, 1.0);
	    	setRadiusMethod.invoke(circle, 1.0);
        
         
        radius = 1.0;
        //circle.setRadius(radius);
        //radiusReturn =  circle.getRadius();
        	setRadiusMethod.invoke(circle, radius);
        	radiusReturn= (double)getRadiusMethod.invoke(circle, (Object[]) null);
        boolean testA =   (radius == radiusReturn);
        System.out.println("\tTest Passed: " + testA + "\t-> value set: " + radius + "\t radius returned: " + radiusReturn);
         
 
        radius = -1.0;
        //circle.setRadius(radius);
        //radiusReturn =  circle.getRadius();
        	setRadiusMethod.invoke(circle, radius);
        	radiusReturn= (double)getRadiusMethod.invoke(circle, (Object[]) null);
        boolean testB =   (radius != radiusReturn);
        System.out.println("\tTest Passed: " + testB + "\t-> value set: " + radius + "\t radius returned: " + radiusReturn);
 
         
        radius = 0.0;
        //circle.setRadius(radius);
        //radiusReturn =  circle.getRadius();
        	setRadiusMethod.invoke(circle, radius);
        	radiusReturn= (double)getRadiusMethod.invoke(circle, (Object[]) null);
        boolean testC =   (radius == radiusReturn);
        System.out.println("\tTest Passed: " + testC + "\t-> value set: " + radius + "\t radius returned: " + radiusReturn);
 
 
        radius = 99.50;
        //circle.setRadius(radius);
        //radiusReturn =  circle.getRadius();
        	setRadiusMethod.invoke(circle, radius);
        	radiusReturn= (double)getRadiusMethod.invoke(circle, (Object[]) null);
        boolean testD =   (radius == radiusReturn);
        System.out.println("\tTest Passed: " + testD + "\t-> value set: " + radius + "\t radius returned: " + radiusReturn);
 
        return testA && testB && testC && testD;
    	}catch(Exception e){return false;}
    }
     
     
     
    private static double distance(double x, double y, double  x2, double  y2)
    {
        return Math.sqrt((x - x2)* (x - x2) + (y - y2)* (y - y2));
    }
     
    public static boolean distanceTest()
    {
    	System.out.println("\nDistance Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			distanceMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			distanceMethod = Circle.class.getMethod("distance", new Class [] { Circle.class });
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (distanceMethod == null){
    		System.out.println("\tERROR --distance method doesn't exist");
    		return false;
    	}
    	
    	
    	try{
        double x1 = 0.0, y1 = 0.0, r1 = 1.0;
        double x2 = 0.0, y2 = 10.0, r2 = 1.0;
         
        Circle circle1;
        Circle circle2;
                 
        double distance;
        double referenceDistance;
 
         
        
         
        circle1 = new Circle();
        //circle1.setX(x1);
        //circle1.setY(y1);
        //circle1.setRadius(r1);
        	setXMethod.invoke(circle1, x1);
        	setYMethod.invoke(circle1, y1);
        	setRadiusMethod.invoke(circle1, r1);
        
        
        circle2 = new Circle();
        //circle2.setX(x2);
        //circle2.setY(y2);
        //circle2.setRadius(r2);
        	setXMethod.invoke(circle2, x2);
        	setYMethod.invoke(circle2, y2);
        	setRadiusMethod.invoke(circle2, r2);
        	
        //distance = circle1.distance(circle2);
        distance = (double)distanceMethod.invoke(circle1, new Object [] { circle2 });
        referenceDistance = distance(x1,y1,x2,y2);
        boolean testA = (Math.abs(distance-referenceDistance) <= error);
        System.out.println("\tTest Passed: " + testA +"\t-> expected: " + referenceDistance + "\t\t found: " + distance);
         
        x2 = 10;
        circle1 = new Circle();
        //circle1.setX(x1);
        //circle1.setY(y1);
        //circle1.setRadius(r1);
        	setXMethod.invoke(circle1, x1);
        	setYMethod.invoke(circle1, y1);
        	setRadiusMethod.invoke(circle1, r1);
    	
        circle2 = new Circle();
        //circle2.setX(x2);
        //circle2.setY(y2);
        //circle2.setRadius(r2);
        	setXMethod.invoke(circle2, x2);
        	setYMethod.invoke(circle2, y2);
        	setRadiusMethod.invoke(circle2, r2);
    	
        //distance = circle1.distance(circle2);
        distance = (double)distanceMethod.invoke(circle1, new Object [] { circle2 });
        referenceDistance = distance(x1,y1,x2,y2);
        boolean testB = (Math.abs(distance-referenceDistance) <= error);
        System.out.println("\tTest Passed: " + testB +"\t-> expected: " + referenceDistance + "\t found: " + distance);
     
        x2 = 0;
        y2 = 0;
        circle1 = new Circle();
        //circle1.setX(x1);
        //circle1.setY(y1);
        //circle1.setRadius(r1);
        	setXMethod.invoke(circle1, x1);
        	setYMethod.invoke(circle1, y1);
        	setRadiusMethod.invoke(circle1, r1);
        
        circle2 = new Circle();
        //circle2.setX(x2);
        //circle2.setY(y2);
        //circle2.setRadius(r2);
        	setXMethod.invoke(circle2, x2);
        	setYMethod.invoke(circle2, y2);
        	setRadiusMethod.invoke(circle2, r2);
        
        //distance = circle1.distance(circle2);
        distance = (double)distanceMethod.invoke(circle1, new Object [] { circle2 });
        referenceDistance = distance(x1,y1,x2,y2);
        boolean testC = (Math.abs(distance-referenceDistance) <= error);
        System.out.println("\tTest Passed: " + testC +"\t-> expected: " + referenceDistance + "\t\t found: " + distance);
         
        x1 = 20;
        y1 = 20;
        x2 = 25;
        y2 = 32;
        circle1 = new Circle();
        //circle1.setX(x1);
        //circle1.setY(y1);
        //circle1.setRadius(r1);
        	setXMethod.invoke(circle1, x1);
        	setYMethod.invoke(circle1, y1);
        	setRadiusMethod.invoke(circle1, r1);
        circle2 = new Circle();
        //circle2.setX(x2);
        //circle2.setY(y2);
        //circle2.setRadius(r2);
        	setXMethod.invoke(circle2, x2);
        	setYMethod.invoke(circle2, y2);
        	setRadiusMethod.invoke(circle2, r2);
    	
        //distance = circle1.distance(circle2);
        distance = (double)distanceMethod.invoke(circle1, new Object [] { circle2 });
        referenceDistance = distance(x1,y1,x2,y2);
        boolean testD = (Math.abs(distance-referenceDistance) <= error);
        System.out.println("\tTest Passed: " + testD +"\t-> expected: " + referenceDistance + "\t\t found: " + distance);
                 
        return testA && testB && testC && testD;
    	}catch (Exception e){return false;}
    }
     
 
     
    private static double referenceArea(double r)
    {
        return Math.PI * r*r;
    }
     
    public static boolean areaTest()
    {
    	System.out.println("\nArea Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			areaMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			areaMethod = Circle.class.getMethod("area", (Class<?> [])  null );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (areaMethod == null){
    		System.out.println("\tERROR --area method doesn't exist");
    		return false;
    	}
    	
    	try{
        Circle circle;
                 
        double area;
        double referenceArea;
 
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(1);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, 1);
        	
        //area = circle.area();
        area = (double)areaMethod.invoke(circle, (Object []) null);
        referenceArea = referenceArea(1);
        boolean testA = (Math.abs(area-referenceArea) <= error);
        System.out.println("\tTest Passed: " + testA +"\t-> expected: " + referenceArea + "\t found: " + area);
 
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(10);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, 10);
    	
        //area = circle.area();
        area = (double)areaMethod.invoke(circle, (Object []) null);
        referenceArea = referenceArea(10);
        boolean testB = (Math.abs(area-referenceArea) <= error);
        System.out.println("\tTest Passed: " + testB +"\t-> expected: " + referenceArea + "\t found: " + area);
 
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(100);
	        setXMethod.invoke(circle, 0);
	    	setYMethod.invoke(circle, 0);
	    	setRadiusMethod.invoke(circle, 100);
    	
        //area = circle.area();
        area = (double)areaMethod.invoke(circle, (Object []) null);
        referenceArea = referenceArea(100);
        boolean testC = (Math.abs(area-referenceArea) <= error);
        System.out.println("\tTest Passed: " + testC +"\t-> expected: " + referenceArea + "\t found: " + area);
 
                         
        return testA && testB && testC;
    	}catch (Exception e){return false;}
    }
     
     
     
    private static double referencePerimeter(double r)
    {
        return 2*Math.PI * r;
    }
     
    public static boolean perimeterTest()
    {
    	System.out.println("\nPerimeter Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			perimeterMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			perimeterMethod = Circle.class.getMethod("perimeter", (Class<?> [])  null );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (perimeterMethod == null){
    		System.out.println("\tERROR --perimeter method doesn't exist");
    		return false;
    	}
    	
    	
    	try{
        Circle circle;
                 
        double perimeter;
        double referencePerimeter;
 

        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(0.5);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, 0.5);
        	
        //perimeter = circle.perimeter();
        	perimeter = (double)perimeterMethod.invoke(circle, (Object []) null);
        referencePerimeter = referencePerimeter(0.5);
        boolean testA = (Math.abs(perimeter-referencePerimeter) <= error);
        System.out.println("\tTest Passed: " + testA +"\t-> expected: " + referencePerimeter + "\t  found: " + perimeter);
 
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(0.05);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, 0.05);
        //perimeter = circle.perimeter();
        perimeter = (double)perimeterMethod.invoke(circle, (Object []) null);
        referencePerimeter = referencePerimeter(0.05);
        boolean testB = (Math.abs(perimeter-referencePerimeter) <= error);
        System.out.println("\tTest Passed: " + testB +"\t-> expected: " + referencePerimeter + "\t  found: " + perimeter);
 
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(0.005);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, 0.005);
        //perimeter = circle.perimeter();
        perimeter = (double)perimeterMethod.invoke(circle, (Object []) null);
        referencePerimeter = referencePerimeter(0.005);
        boolean testC = (Math.abs(perimeter-referencePerimeter) <= error);
        System.out.println("\tTest Passed: " + testC +"\t-> expected: " + referencePerimeter + " found: " + perimeter);
 
                         
        return testA && testB && testC;
    	}catch (Exception e){return false;}
    }
     
     
 
    private static double referenceDiameter(double r)
    {
        return 2*r;
    }
     
    public static boolean diameterTest()
    {
    	System.out.println("\nDiameter Test:");
    	
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			diameterMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			diameterMethod = Circle.class.getMethod("diameter", (Class<?> [])  null );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (diameterMethod == null){
    		System.out.println("\tERROR --diameter method doesn't exist");
    		return false;
    	}
    	
    	try{
        Circle circle;
 
        double radius;
        double diameter;
        double referenceDiameter;

        radius = 1;
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(radius);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, radius);
        //diameter = circle.diameter();
        diameter = (double)diameterMethod.invoke(circle, (Object []) null);
        referenceDiameter = referenceDiameter(radius);
        boolean testA = (Math.abs(diameter-referenceDiameter) <= error);
        System.out.println("\tTest Passed: " + testA +"\t-> expected: " + referenceDiameter + "\tfound: " + diameter);
 
        radius = 2;
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(radius);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, radius);
        //diameter = circle.diameter();
        diameter = (double)diameterMethod.invoke(circle, (Object []) null);
        referenceDiameter = referenceDiameter(radius);
        boolean testB = (Math.abs(diameter-referenceDiameter) <= error);
        System.out.println("\tTest Passed: " + testB +"\t-> expected: " + referenceDiameter + "\tfound: " + diameter);
 
        radius = 10;
        circle = new Circle();
        //circle.setX(0);
        //circle.setY(0);
        //circle.setRadius(radius);
        	setXMethod.invoke(circle, 0);
        	setYMethod.invoke(circle, 0);
        	setRadiusMethod.invoke(circle, radius);
        //diameter = circle.diameter();
        diameter = (double)diameterMethod.invoke(circle, (Object []) null);
        referenceDiameter = referenceDiameter(radius);
        boolean testC = (Math.abs(diameter-referenceDiameter) <= error);
        System.out.println("\tTest Passed: " + testC +"\t-> expected: " + referenceDiameter + "\tfound: " + diameter);
 
                         
        return testA && testB && testC;
    	}catch (Exception e){return false;}
    }
     
     
 
     
    public static boolean intersectsTest()
    {
    	System.out.println("\nIntersection Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			intersectMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			intersectMethod = Circle.class.getMethod("intersects", new Class [] { Circle.class } );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (intersectMethod == null){
    		System.out.println("\tERROR --intersects method doesn't exist");
    		return false;
    	}
    	
    	try{
        Circle circle1= new Circle();
        //circle1.setX(0);
        //circle1.setY(1);
        //circle1.setRadius(1);
        	setXMethod.invoke(circle1, 0);
        	setYMethod.invoke(circle1, 1);
        	setRadiusMethod.invoke(circle1, 1);
        	
        Circle circle2= new Circle();
        //circle2.setX(1);
        //circle2.setY(0);
        //circle2.setRadius(1);
        	setXMethod.invoke(circle2, 1);
        	setYMethod.invoke(circle2, 0);
        	setRadiusMethod.invoke(circle2, 1);
        Circle circle3= new Circle();
        //circle3.setX(10);
        //circle3.setY(0);
        //circle3.setRadius(2);
	        setXMethod.invoke(circle3, 10);
	    	setYMethod.invoke(circle3, 0);
	    	setRadiusMethod.invoke(circle3, 2);
        Circle circle4= new Circle();
        //circle4.setX(10);
        //circle4.setY(3);
        //circle4.setRadius(4);
        	setXMethod.invoke(circle4, 10);
        	setYMethod.invoke(circle4, 3);
        	setRadiusMethod.invoke(circle4, 4);
                 
        //boolean testA = (true == circle1.intersects(circle2));
        //boolean testB = (true == circle2.intersects(circle1));
        //boolean testC = (false == circle1.intersects(circle3));
        //boolean testD = (true == circle3.intersects(circle4));
        
        boolean testA = (true == (boolean)intersectMethod.invoke(circle1, new Object []{ circle2 }));
        boolean testB = (true == (boolean)intersectMethod.invoke(circle2, new Object []{ circle1 }));
        boolean testC = (false == (boolean)intersectMethod.invoke(circle1, new Object []{ circle3 }));
        boolean testD = (true == (boolean)intersectMethod.invoke(circle3, new Object []{ circle4 }));
         
        
        System.out.println("\tTest Passed: " + testA + "\t(0,1,1)  intersects (1,0,1)   -> expected: true   found: " + (boolean)intersectMethod.invoke(circle1, new Object []{ circle2 }));
        System.out.println("\tTest Passed: " + testB + "\t(1,0,1)  intersects (0,1,1)   -> expected: true   found: " + (boolean)intersectMethod.invoke(circle2, new Object []{ circle1 }));
        System.out.println("\tTest Passed: " + testC + "\t(0,1,1)  intersects (10,0,2)  -> expected: false  found: " + (boolean)intersectMethod.invoke(circle1, new Object []{ circle3 }));
        System.out.println("\tTest Passed: " + testD + "\t(10,0,2) intersects (10,3,4)  -> expected: true   found: " + (boolean)intersectMethod.invoke(circle3, new Object []{ circle4 }));
 
        return testA && testB && testC && testD;
    	}catch (Exception e){return false;}
    }
 
     
 
    public static boolean concentricTest()
    {
    	System.out.println("\nConcentric Circles Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			concentricMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			concentricMethod = Circle.class.getMethod("isConcentric", new Class [] { Circle.class } );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (concentricMethod == null){
    		System.out.println("\tERROR --isConcentric method doesn't exist");
    		return false;
    	}
    	
    	try{
    		
        Circle circle1= new Circle();  
        //circle1.setX(10);
        //circle1.setY(10);
        //circle1.setRadius(5);
        	setXMethod.invoke(circle1, 10);
        	setYMethod.invoke(circle1, 10);
        	setRadiusMethod.invoke(circle1, 5);
    	
        Circle circle2= new Circle();
        //circle2.setX(10);
        //circle2.setY(10);
        //circle2.setRadius(3);
        	setXMethod.invoke(circle2, 10);
        	setYMethod.invoke(circle2, 10);
        	setRadiusMethod.invoke(circle2, 3);
    	
        Circle circle3= new Circle();
        //circle3.setX(0);
        //circle3.setY(0);
        //circle3.setRadius(5);
        	setXMethod.invoke(circle3, 0);
        	setYMethod.invoke(circle3, 0);
        	setRadiusMethod.invoke(circle3, 5);
        	
        Circle circle4= new Circle();
        //circle4.setX(0);
        //circle4.setY(0);
        //circle4.setRadius(4);
        	setXMethod.invoke(circle4, 0);
        	setYMethod.invoke(circle4, 0);
        	setRadiusMethod.invoke(circle4, 4);
                 
        //boolean testA = (true == circle2.isConcentric(circle1));
        //boolean testB = (false == circle2.isConcentric(circle3));
        //boolean testC = (false == circle3.isConcentric(circle1));
        //boolean testD = (true == circle4.isConcentric(circle3));
        
        boolean testA = (true == (boolean)concentricMethod.invoke(circle2, new Object []{ circle1 }));
        boolean testB = (false == (boolean)concentricMethod.invoke(circle2, new Object []{ circle3 }));
        boolean testC = (false == (boolean)concentricMethod.invoke(circle3, new Object []{ circle1 }));
        boolean testD = (true == (boolean)concentricMethod.invoke(circle4, new Object []{ circle3 }));
         
        
        System.out.println("\tTest Passed: " + testA + "\t(10,10,3) concentric with (10,10,5) -> expected: true   found: " + (boolean)concentricMethod.invoke(circle2, new Object []{ circle1 }));
        System.out.println("\tTest Passed: " + testB + "\t(10,10,3) concentric with (0,0,5)   -> expected: false  found: " + (boolean)concentricMethod.invoke(circle2, new Object []{ circle3 }));
        System.out.println("\tTest Passed: " + testC + "\t(0,0,5)   concentric with (10,10,5) -> expected: false  found: " + (boolean)concentricMethod.invoke(circle3, new Object []{ circle1 }));
        System.out.println("\tTest Passed: " + testD + "\t(0,0,4)   concentric with (0,0,5)   -> expected: true   found: " + (boolean)concentricMethod.invoke(circle4, new Object []{ circle3 }));
 
        return testA && testB && testC && testD;
    	}catch(Exception e){return false;}
    }
 
 
    public static boolean equalsTest()
    {
    	System.out.println("\nEquals Test:");
    	Method setXMethod = null, setYMethod = null, setRadiusMethod = null, 
    			equalsMethod = null;
    	
    	//check to see if all of these methods exist in the Circle class
    	try {
			setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
		} catch (NoSuchMethodException e) {}
    	
    	try {
			equalsMethod = Circle.class.getMethod("equals", new Class [] { Circle.class } );
		} catch (NoSuchMethodException e) {}
    	
    	//if one of them doesn't exist, this test method will fail
    	if (setXMethod == null){
    		System.out.println("\tERROR --setX method doesn't exist");
    		return false;
    	}
    	if (setYMethod == null){
    		System.out.println("\tERROR --setY method doesn't exist");
    		return false;
    	}
    	if (setRadiusMethod == null){
    		System.out.println("\tERROR --setRadius method doesn't exist");
    		return false;
    	}
    	if (equalsMethod == null){
    		System.out.println("\tERROR --equals method doesn't exist");
    		return false;
    	}
    	
    	try{
        Circle circle1= new Circle();  
        //circle1.setX(10);
        //circle1.setY(10);
        //circle1.setRadius(5);
        	setXMethod.invoke(circle1, 10);
        	setYMethod.invoke(circle1, 10);
        	setRadiusMethod.invoke(circle1, 5);
        
        Circle circle2= new Circle();
        //circle2.setX(0);
        //circle2.setY(10);
        //circle2.setRadius(5);
        	setXMethod.invoke(circle2, 0);
        	setYMethod.invoke(circle2, 10);
        	setRadiusMethod.invoke(circle2, 5);
        
        Circle circle3= new Circle();
        //circle3.setX(0);
        //circle3.setY(11);
        //circle3.setRadius(5);
        	setXMethod.invoke(circle3, 0);
        	setYMethod.invoke(circle3, 11);
        	setRadiusMethod.invoke(circle3, 5);
    	
        Circle circle4= new Circle();
        //circle4.setX(10);
        //circle4.setY(10);
        //circle4.setRadius(6);
        	setXMethod.invoke(circle4, 10);
        	setYMethod.invoke(circle4, 10);
        	setRadiusMethod.invoke(circle4, 6);
         
                 
        //boolean testA = (true == circle1.equals(circle1));
        //boolean testB = (false == circle2.equals(circle1));
        //boolean testC = (false == circle3.equals(circle2));
        //boolean testD = (false == circle4.equals(circle1));
        
        boolean testA = (true == (boolean)equalsMethod.invoke(circle1, new Object []{ circle1 }));
        boolean testB = (false == (boolean)equalsMethod.invoke(circle2, new Object []{ circle1 }));
        boolean testC = (false == (boolean)equalsMethod.invoke(circle3, new Object []{ circle2 }));
        boolean testD = (false == (boolean)equalsMethod.invoke(circle4, new Object []{ circle1 }));
          
         
        
        System.out.println("\tTest Passed: " + testA + "\t(10,10,5) equals (10,10,5) -> expected: true   found: " + (boolean)equalsMethod.invoke(circle1, new Object []{ circle1 }));
        System.out.println("\tTest Passed: " + testB + "\t(0,10,5)  equals (10,10,5) -> expected: false  found: " + (boolean)equalsMethod.invoke(circle2, new Object []{ circle1 }));
        System.out.println("\tTest Passed: " + testC + "\t(0,11,5)  equals (0,10,5)  -> expected: false  found: " +  (boolean)equalsMethod.invoke(circle3, new Object []{ circle2 }));
        System.out.println("\tTest Passed: " + testD + "\t(10,10,6) equals (10,10,5) -> expected: false  found: " + (boolean)equalsMethod.invoke(circle4, new Object []{ circle1 }));
 
        return testA && testB && testC && testD;
    	}catch (Exception e){return false;}
    }
 
 
     
     
     
     
     
     
    public static void main(String[] args) {
         boolean flag3, flag4, flag5, flag6, flag7, flag8, flag9, flag10, flag11, flag12, flag13;
         flag3=flag4=flag5=flag6=flag7=flag8=flag9=flag10=flag11=flag12=flag13 = false;
         
        //there's try-catch in all the tests so these should be unnecessary but theyre here anyways
    	try{ flag3 = unitCircleTest(); } catch (Exception e){}
    	try{ flag4 = xTest(); } catch (Exception e){}
    	try{ flag5 = yTest(); }catch (Exception e){}
    	try{ flag6 = radiusTest(); }catch (Exception e){}
    	try{ flag7 = distanceTest(); }catch (Exception e){}
    	try{ flag8 = areaTest(); }catch (Exception e){}
    	try{ flag9 = perimeterTest(); }catch (Exception e){}
    	try{ flag10 = diameterTest(); }catch (Exception e){}
    	try{ flag11 = intersectsTest(); }catch (Exception e){}
    	try{ flag12 = concentricTest(); }catch (Exception e){}
    	try{ flag13 = equalsTest(); }catch (Exception e){}
        
        boolean flag = flag3 && flag4 && flag5 && flag6 && flag7 
        	&& flag8 && flag9 && flag10 && flag11 && flag12 && flag13;
        
        int grade = 55;
        
        System.out.println("\n----------------");
        if (!flag3){
        	System.out.println("[-5] unit circle test");
        	grade -= 5;
        }
        if (!flag4){
        	System.out.println("[-5] getting/setting x test");
        	grade -= 5;
        }
        if (!flag5){
        	System.out.println("[-5] getting/setting y test");
        	grade -= 5;
        }
        if (!flag6){
        	System.out.println("[-5] radius test");
        	grade -= 5;
        }
        if (!flag7){
        	System.out.println("[-5] distance test");
        	grade -= 5;
        }
        if (!flag8){
        	System.out.println("[-5] area test");
        	grade -= 5;
        }
        if (!flag9){
        	System.out.println("[-5] perimeter test");
        	grade -= 5;
        }
        if (!flag10){
        	System.out.println("[-5] diameter test");
        	grade -= 5;
        }
        if (!flag11){
        	System.out.println("[-5] intersection test");
        	grade -= 5;
        }
        if (!flag12){
        	System.out.println("[-5] concentric test");
        	grade -= 5;
        }
        if (!flag13){
        	System.out.println("[-5] equals test");
        	grade -= 5;
        }
        
        System.out.println("\nGrade for Circle class: " + grade + "/55");
        System.out.println("\nAll TESTS PASSED:\t" + flag);
              
    }
     
}