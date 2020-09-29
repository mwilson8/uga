
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A class designed to help students in CSCI 1301 at UGA
 * during their implementation of the Circle lab. This class
 * uses reflection to examine the Circle class and checks 
 * that methods are properly declared. 
 * Included checks are:
 * -method name
 * -return type
 * -parameters
 * An important note: 
 * 		This class does not test for correct functionality
 * 
 * @author MitchWilson
 * @version 2.1 - Nov 2017
 */
public class CircleMethodCheck {

	public static void main(String[] args){
	
		//create Method objects for all methods that should be in
		//Circle class; initialized to null
		Method setXMethod     = null, 
		   setYMethod         = null, 
		   setRadiusMethod    = null,
		   getXMethod         = null,
		   getYMethod         = null,
		   getRadiusMethod    = null,
		   diameterMethod     = null,
		   areaMethod         = null,
		   perimeterMethod    = null,
		   toStringMethod     = null,
		   equalsMethod       = null,
		   isConcentricMethod = null,
		   intersectsMethod   = null,
		   distanceMethod     = null,
		   isUnitCircleMethod = null;

	/*
	 * Each method gets it's own try-catch so that they 
	 * will all be checked separately.. any exception will
	 * cause the Method object to remain null, which we check
	 * later
	 */
	
		
	//setX
	try {
		setXMethod = Circle.class.getMethod("setX", new Class [] { double.class });
	} catch (NoSuchMethodException e) {}
	
	//setY	
	try {
		setYMethod = Circle.class.getMethod("setY", new Class [] { double.class });
	} catch (NoSuchMethodException e) {}
	
	//setRadius
	try {
		setRadiusMethod = Circle.class.getMethod("setRadius", new Class [] { double.class });
	} catch (NoSuchMethodException e) {}
	
	//getX
	try {
		getXMethod = Circle.class.getMethod("getX", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	//getY
	try {
		getYMethod = Circle.class.getMethod("getY", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
		
	//getRadius
	try {
		getRadiusMethod = Circle.class.getMethod("getRadius", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	//diameter
	try {
		diameterMethod = Circle.class.getMethod("diameter", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}

	//area
	try {
		areaMethod = Circle.class.getMethod("area", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	//perimeter
	try {
		perimeterMethod = Circle.class.getMethod("perimeter", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	//toString
	try {
		toStringMethod = Circle.class.getMethod("toString", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	//equals
	try {
		equalsMethod = Circle.class.getMethod("equals",  new Class [] { Circle.class });
	} catch (NoSuchMethodException e) {}
	
	//isConcentric
	try {
		isConcentricMethod = Circle.class.getMethod("isConcentric",  new Class [] { Circle.class });
	} catch (NoSuchMethodException e) {}
	
	//intersects
	try {
		intersectsMethod = Circle.class.getMethod("intersects",  new Class [] { Circle.class });
	} catch (NoSuchMethodException e) {}
	
	//distance
	try {
		distanceMethod = Circle.class.getMethod("distance",  new Class [] { Circle.class });
	} catch (NoSuchMethodException e) {}
	
	//isUnitCircle
	try {
		isUnitCircleMethod = Circle.class.getMethod("isUnitCircle", (Class<?> []) null);
	} catch (NoSuchMethodException e) {}
	
	

	
	/*
	 * Pass the Method object along to the check method along with their 
	 * string name and return type
	 * 
	 * NOTE: a blank file will still pass a toString test because it inherently
	 * exists from Object. For this reason, we don't test for toString
	 */
	System.out.println("*****   Circle   *****\n");
	checkFields(Circle.class);
	
//////setX
	testMethod("setX", setXMethod, Void.TYPE);
	
//////setY
	testMethod("setY", setYMethod, Void.TYPE);
	
//////setRadius
	testMethod("setRadius", setRadiusMethod, Void.TYPE);
	
//////getX
	testMethod("getX", getXMethod, Double.TYPE);
	
//////getY
	testMethod("getY", getYMethod, Double.TYPE);
	
//////getRadius
	testMethod("getRadius", getRadiusMethod, Double.TYPE);
	
//////diameter
	testMethod("diameter", diameterMethod, Double.TYPE);
	
//////area
	testMethod("area", areaMethod, Double.TYPE);
	
//////perimeter
	testMethod("perimeter", perimeterMethod, Double.TYPE);
	
//////isUnitCircle
	testMethod("isUnitCircle", isUnitCircleMethod, Boolean.TYPE);
	
//////toString
	testMethod("toString", toStringMethod, String.class);
	
//////isConcentric
	testMethod("isConcentric", isConcentricMethod, Boolean.TYPE);
	
//////intersects
	testMethod("intersects", intersectsMethod, Boolean.TYPE);
	
//////equals
	testMethod("equals", equalsMethod, Boolean.TYPE);
	
//////distance
	testMethod("distance", distanceMethod, Double.TYPE);

}

	/**
	 * 
	 * @param methodName - the String representation of the method - only used for printing in the test
	 * @param method - the Method object that we are testing. Prints an error if the method is null, 
	 * which would result from improper spelling or improper parameters. Prints an error if the method
	 * has incorrect return type
	 * @param type - the return type of the method being testing
	 */
	private static void testMethod(String methodName, Method method, java.lang.reflect.Type type){
		System.out.print(methodName + ":\t"); 
		
		//just for looking good
		if(methodName.length() < 7) System.out.print("\t");
		
		if (method == null) 
			System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
		else if 
			(!method.getReturnType().equals(type)) System.out.println("failed\n *incorrect return type*");
		else 
			System.out.println("passed");
		
		System.out.println("\n----------------------");
	}
	
	/**
     * A method to check all the instance variables of a class to see if they 
     * correspond to the given UML
     * @param clazz
     */
    private static void checkFields(Class<?> clazz){
    	System.out.println(clazz.getSimpleName() + " instance variables:");
    	//array of all Fields in the class
    	Field[] fields = clazz.getDeclaredFields();
    	//2 ArrayLists to hold the types of the Fields and the names of the Fields
		List<Class<?>> fieldTypes = new ArrayList<Class<?>>();
		List<String> fieldNames = new ArrayList<String>();
		
		//populate both ArrayLists
		for (Field f : fields){
			//System.out.println(f.getName() + ": " + f.getType());
			fieldTypes.add(f.getType());
			fieldNames.add(f.getName());
		}
		boolean x = false, y = false, radius = false;
		
		System.out.print("double x\t");
		try{
			
			//could throw OutOfBoundsException if indexOf returns -1
			if (fieldTypes.get(fieldNames.indexOf("x")).equals(double.class)){
				System.out.println("passed");
				x = true;
			} else System.out.println("failed");
				
		}catch(Exception e){
			System.out.println("failed");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		
		System.out.print("double y\t");
		try{
			
			//could throw OutOfBoundsException if indexOf returns -1
			if (fieldTypes.get(fieldNames.indexOf("y")).equals(double.class)){
				System.out.println("passed");
				y = true;
			} else System.out.println("failed");
		
		}catch(Exception e){
			System.out.println("failed");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		
		System.out.print("double radius\t");
		try{
			
			//could throw OutOfBoundsException if indexOf returns -1
			if (fieldTypes.get(fieldNames.indexOf("radius")).equals(double.class)){
				System.out.println("passed");
				radius = true;
			} else System.out.println("failed");
				
		}catch(Exception e){
			System.out.println("failed");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		
		//if the variables x, y, radius exist but there is another instance variable
		if (x && y && radius && fields.length > 3){
			System.err.println("You should not declare any more instance variables other than x, y, radius.");
			System.exit(0);
		}
		
    }
	
}
