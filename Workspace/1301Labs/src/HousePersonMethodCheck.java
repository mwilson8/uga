
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class HousePersonMethodCheck {

	public static void main (String[] args){
		
		//House Constructors and methods
		Constructor<House> house_defaultConstructor = null,
						    house_parameterConstructor = null;	
		
		Method house_equalsMethod      = null,
				house_areaMethod       = null,
				house_volumeMethod     = null,
				house_setColorMethod   = null,
				house_setPriceMethod   = null,
				house_setForSaleMethod = null,
				house_isForSaleMethod  = null,
				house_getColorMethod   = null,
				house_getPriceMethod   = null,
				house_getWidthMethod   = null,
				house_getLengthMethod  = null,
				house_getHeightMethod  = null;
		
		
		//default constructor
		try {
			house_defaultConstructor = House.class.getConstructor();
		} catch (Exception e) {}

		//double constructor
		try {
			house_parameterConstructor = House.class.getConstructor(new Class<?>[]{
				/*Color color*/		House.Color.class, 
				/*double price*/	double.class, 
				/*double width*/	double.class, 
				/*double length*/	double.class, 
				/*double height*/	double.class});
		} catch (Exception e) {}
		
		///////begin method checks
		
		//equals
		try {
			house_equalsMethod = House.class.getMethod("equals", new Class[] {House.class});
		} catch (NoSuchMethodException e) {}
	
		//area
		try {
			house_areaMethod = House.class.getMethod("area", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//volume
		try {
			house_volumeMethod = House.class.getMethod("volume", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//setColor
		try {
			house_setColorMethod = House.class.getMethod("setColor", new Class[] {House.Color.class});
		} catch (NoSuchMethodException e) {}
		
		//setPrice
		try {
			house_setPriceMethod = House.class.getMethod("setPrice", new Class[] {double.class});
		} catch (NoSuchMethodException e) {}
		
		//setForSale
		try {
			house_setForSaleMethod = House.class.getMethod("setForSale", new Class[] {boolean.class});
		} catch (NoSuchMethodException e) {}
		
		//isForSale
		try {
			house_isForSaleMethod = House.class.getMethod("isForSale", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getColor
		try {
			house_getColorMethod = House.class.getMethod("getColor", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getPrice
		try {
			house_getPriceMethod = House.class.getMethod("getPrice", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getWidth
		try {
			house_getWidthMethod = House.class.getMethod("getWidth", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getLength
		try {
			house_getLengthMethod = House.class.getMethod("getLength", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getHeight
		try {
			house_getHeightMethod = House.class.getMethod("getHeight", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		////////////////////////////////////////////// end checks for house
		/////////////begin checks for person
		//////////////////////////////////////////////
		
		Constructor<Person> person_defaultConstructor = null,
							person_3parameterConstructor = null,
							person_4parameterConstructor = null;
	
		Method person_getNameMethod     = null,
				person_getAgeMethod     = null,
				person_getCashMethod    = null,
				person_getHouseMethod   = null,
				person_ownsAHouseMethod = null,
				person_addCashMethod    = null,
				person_sellHomeMethod   = null,
				person_buyHouseMethod   = null;
		
		
		//default constructor
		try {
			person_defaultConstructor = Person.class.getConstructor();
		} catch (Exception e) {}

		//3 param constructor
		try {
			person_3parameterConstructor = Person.class.getConstructor(new Class<?>[]{
				/*String name*/	String.class, 
				/*int age*/		int.class, 
				/*double cash*/	double.class});
		} catch (Exception e) {}

		//4 param constructor
		try {
			person_4parameterConstructor = Person.class.getConstructor(new Class<?>[]{
				/*name*/String.class, 
				/*age*/int.class, 
				/*cash*/double.class, 
				/*house*/House.class});
		} catch (Exception e) {}
		
		//////////////////////////////
		/////////methods
		
		//getName()
		try {
			person_getNameMethod = Person.class.getMethod("getName", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getAge()
		try {
			person_getAgeMethod = Person.class.getMethod("getAge", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getCash()
		try {
			person_getCashMethod = Person.class.getMethod("getCash", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getHouse()
		try {
			person_getHouseMethod = Person.class.getMethod("getHouse", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//ownsAHouse()
		try {
			person_ownsAHouseMethod = Person.class.getMethod("ownsAHouse", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//addCash(double)
		try {
			person_addCashMethod = Person.class.getMethod("addCash", new Class[] {double.class});
		} catch (NoSuchMethodException e) {}
		
		//sellHome()
		try {
			person_sellHomeMethod = Person.class.getMethod("sellHome", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//buyHouse(House)
		try {
			person_buyHouseMethod = Person.class.getMethod("buyHouse", new Class[] {House.class});
		} catch (NoSuchMethodException e) {}
	
	
		//print tests
		System.out.println("*****   House   *****\n");
		testConstructor("House()", house_defaultConstructor);
		testConstructor("House(5 param)", house_parameterConstructor);
		
		/*testMethod("methodName", methodObject, returnType); */
		testMethod("equals", house_equalsMethod, Boolean.TYPE);
		testMethod("area", house_areaMethod, Double.TYPE);
		testMethod("volume", house_volumeMethod, Double.TYPE);
		testMethod("setColor", house_setColorMethod, Void.TYPE);
		testMethod("setPrice", house_setPriceMethod, Void.TYPE);
		testMethod("setForSale", house_setForSaleMethod, Void.TYPE);
		testMethod("isForSale", house_isForSaleMethod, Boolean.TYPE);
		testMethod("getColor", house_getColorMethod, House.Color.class);
		testMethod("getPrice", house_getPriceMethod, Double.TYPE);
		testMethod("getWidth", house_getWidthMethod, Double.TYPE);
		testMethod("getLength", house_getLengthMethod, Double.TYPE);
		testMethod("getHeight", house_getHeightMethod, Double.TYPE);

		System.out.println("\n*****  Person  *****");
		testConstructor(person_defaultConstructor, "Person()");
		testConstructor(person_3parameterConstructor, "Person(3 param)");
		testConstructor(person_4parameterConstructor, "Person(4 param)");
		
		/*testMethod("methodName", methodObject, returnType); */
		testMethod("getName", person_getNameMethod, String.class);
		testMethod("getAge", person_getAgeMethod, Integer.TYPE);
		testMethod("getCash", person_getCashMethod, Double.TYPE);
		testMethod("getHouse", person_getHouseMethod, House.class);
		testMethod("ownsAHouse", person_ownsAHouseMethod, Boolean.TYPE);
		testMethod("addCash", person_addCashMethod, Void.TYPE);
		testMethod("sellHome", person_sellHomeMethod, Void.TYPE);
		testMethod("buyHouse", person_buyHouseMethod, Void.TYPE);
		
	}	
	
	
	

    /**
     * Method for checking if a method is correctly declared in a House/Person class
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
     * Method for testing is a constructor is correctly declared in a House class
     * @param constructorName - the String representation of the constructor - only used for printing in the test
     * @param constructor - the Constructor object being tested. If the constructor was positively found the test
     * prints a success, otherwise prints a failure with possible reasons for failing
     */
    private static void testConstructor(String constructorName, Constructor<House> constructor){
    	System.out.print(constructorName + ":\t");
    	
    	//just for looking good
        if(constructorName.length() < 7) System.out.print("\t");
        
        if (constructor == null)
        	System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
        else
            System.out.println("passed");
         
        System.out.println("\n----------------------");
    }
    
    /**
     * Method for testing is a constructor is correctly declared in a Person class
     * @param constructorName - the String representation of the constructor - only used for printing in the test
     * @param constructor - the Constructor object being tested. If the constructor was positively found the test
     * prints a success, otherwise prints a failure with possible reasons for failing
     */
    private static void testConstructor(Constructor<Person> constructor, String constructorName){
    	System.out.print(constructorName + ":");
    	
    	//just for looking good
        if(constructorName.length() < 10) System.out.print("\t");
        
        if (constructor == null)
        	System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
        else
            System.out.println("passed");
         
        System.out.println("\n----------------------");
    }
}
