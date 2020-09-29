import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * A class designed to help students in CSCI 1301 at UGA
 * during their implementation of the Stat lab. This class
 * uses reflection to examine the Stat class and checks 
 * that methods are properly declared. 
 * Included checks are:
 * -method name
 * -return type
 * -parameters
 * An important note: 
 * 		This class does not test for correct functionality
 * 
 * @author MitchWilson
 *
 */
public class StatMethodCheck_v1 {

	public static void main(String[] args) {
		
		//two constructor objects
		Constructor<Stat> defaultConstructor = null,
						  	doubleArrayConstructor = null;
		
		//all method objects 
		/*NOTE: we don't check for the toString method, because whether it's defined 
		or not, it will still exist b/c inherited from Object so testing for it
		is pointless for this class
		*/
		Method getDataMethod   = null,
				setDataMethod  = null,
				equalsMethod   = null,
				minMethod      = null,
				maxMethod      = null,
				averageMethod  = null,
				modeMethod     = null;
		
		//default
		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) {}

		//double constructor
		try {
			doubleArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) {}
		
		//getData
		try {
			getDataMethod = Stat.class.getMethod("getData", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}

		//setData
		try{
			setDataMethod = Stat.class.getMethod("setData", new Class[] {double[].class});
		} catch (Exception e){}
		
		//equals
		try {
			equalsMethod = Stat.class.getMethod("equals", new Class[]{Stat.class});
		} catch (NoSuchMethodException e) {}

		//min
		try {
			minMethod = Stat.class.getMethod("min", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//max
		try {
			maxMethod = Stat.class.getMethod("max", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//average
		try {
			averageMethod = Stat.class.getMethod("average", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//mode
		try {
			modeMethod = Stat.class.getMethod("mode", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
	
		/////////////////// begin tests
		
		System.out.println("\n----------------------");
		
		/*
		 * Pass the Constructor object along with their string name to the 
		 * testing method
		 */
		testConstructor("Stat()", defaultConstructor);
		testConstructor("Stat(double[])", doubleArrayConstructor);
		
		/*
		 * Pass the Method object along to the check method along with their 
		 * string name and return type
		 * 
		 * NOTE: a blank file will still pass a toString test because it inherently
		 * exists from Object. For this reason, we don't test for toString
		 */
		testMethod("getData", getDataMethod, double[].class);
		testMethod("setData", setDataMethod, Void.TYPE);
		testMethod("equals", equalsMethod, Boolean.TYPE);
		testMethod("min", minMethod, Double.TYPE);
		testMethod("max", maxMethod, Double.TYPE);
		testMethod("average", averageMethod, Double.TYPE);
		testMethod("mode", modeMethod, Double.TYPE);
	}
	
	

    /**
     * Method for checking if a method is correctly declared in a Stat class
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
     * Method for testing is a constructor is correctly declared in a Stat class
     * @param constructorName - the String representation of the constructor - only used for printing in the test
     * @param constructor - the Constructor object being tested. If the constructor was positively found the test
     * prints a success, otherwise prints a failure with possible reasons for failing
     */
    private static void testConstructor(String constructorName, Constructor<Stat> constructor){
    	System.out.print(constructorName + ":\t");
    	
    	//just for looking good
        if(constructorName.length() < 7) System.out.print("\t");
        
        if (constructor == null)
        	System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
        else
            System.out.println("passed");
         
        System.out.println("\n----------------------");
    }

}
