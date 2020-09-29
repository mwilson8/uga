import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * A class designed to help students in CSCI 1301 at UGA
 * during their implementation of the second version of 
 * the Stat lab. This class uses reflection to examine 
 * the Stat class and checks that methods are properly declared. 
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
public class StatMethodCheck_v2 {

	public static void main(String[] args) {
		
		//Constructor objects
		Constructor<Stat> defaultConstructor = null,
						  	dConstructor = null,
							fConstructor = null,
							iConstructor = null,
							lConstructor = null;
									
		
		
		/*NOTE: we don't check for the toString method, because whether it's defined 
		or not, it will still exist b/c inherited from Object
		*/
		//Method objects
		Method setDataFloat    = null,
				setDataDouble  = null,
				setDataInt     = null,
				setDataLong    = null,
				getDataMethod  = null,
				equalsMethod   = null,
				resetMethod    = null,
				appendInt      = null,
				appendFloat    = null,
				appendLong     = null,
				appendDouble   = null,
				isEmptyMethod  = null,
				minMethod      = null,
				maxMethod      = null,
				averageMethod  = null,
				modeMethod     = null,
				varianceMethod = null,
				stdDevMethod   = null;
		
		//default
		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) {}

		//double[] constructor
		try {
			dConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) {}
		
		//float[] constructor
		try {
			fConstructor = Stat.class.getConstructor(new Class[]{float[].class});
		} catch (Exception e) {}
		
		//int[] constructor
		try {
			iConstructor = Stat.class.getConstructor(new Class[]{int[].class});
		} catch (Exception e) {}
		
		//long[] constructor
		try {
			lConstructor = Stat.class.getConstructor(new Class[]{long[].class});
		} catch (Exception e) {}
		
		//////////check for methods\\\\\\\\\\
		
		//setData(float[])
		try{
			setDataFloat = Stat.class.getMethod("setData", new Class[] {float[].class});
		} catch (Exception e){}
		
		//setData(double[])
		try{
			setDataDouble = Stat.class.getMethod("setData", new Class[] {double[].class});
		} catch (Exception e){}
		
		//setData(int[])
		try{
			setDataInt = Stat.class.getMethod("setData", new Class[] {int[].class});
		} catch (Exception e){}
		
		//setData(long[])
		try{
			setDataLong = Stat.class.getMethod("setData", new Class[] {long[].class});
		} catch (Exception e){}				
				
		//getData
		try {
			getDataMethod = Stat.class.getMethod("getData", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}

		//equals
		try {
			equalsMethod = Stat.class.getMethod("equals", new Class[]{Stat.class});
		} catch (NoSuchMethodException e) {}

		//reset
		try {
			resetMethod = Stat.class.getMethod("reset", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//append(int[])
		try{
			appendInt = Stat.class.getMethod("append", new Class[] {int[].class});
		} catch (Exception e){}	
		
		//append(float[])
		try{
			appendFloat = Stat.class.getMethod("append", new Class[] {float[].class});
		} catch (Exception e){}	
		
		//append(long[])
		try{
			appendLong = Stat.class.getMethod("append", new Class[] {long[].class});
		} catch (Exception e){}	
		
		//append(double[])
		try{
			appendDouble = Stat.class.getMethod("append", new Class[] {double[].class});
		} catch (Exception e){}	
		
		//isEmpty
		try {
			isEmptyMethod = Stat.class.getMethod("isEmpty", (Class<?> []) null);
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
		
		//variance
		try {
			varianceMethod = Stat.class.getMethod("variance", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//standardDeviation
		try {
			stdDevMethod = Stat.class.getMethod("standardDeviation", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		/////////////////// test and printout
		System.out.println("\n----------------------");
		
		testConstructor("Stat()", defaultConstructor);
		testConstructor("Stat(double[])", dConstructor);
		testConstructor("Stat(float[])", fConstructor);
		testConstructor("Stat(int[])", iConstructor);
		testConstructor("Stat(long[])", lConstructor);
		
		testMethod("setData(float[])", setDataFloat, Void.TYPE);
		testMethod("setData(double[])", setDataDouble, Void.TYPE);
		testMethod("setData(int[])", setDataInt, Void.TYPE);
		testMethod("setData(long[])", setDataLong, Void.TYPE);
		
		testMethod("getData()", getDataMethod, double[].class);
		
		testMethod("equals(Stat)", equalsMethod, Boolean.TYPE);
		
		testMethod("reset()", resetMethod, Void.TYPE);
		
		testMethod("append(int[])", appendInt, Void.TYPE);
		testMethod("append(float[])", appendFloat, Void.TYPE);
		testMethod("append(long[])", appendLong, Void.TYPE);
		testMethod("append(double[])", appendDouble, Void.TYPE);
		
		testMethod("isEmpty()", isEmptyMethod, Boolean.TYPE);
		
		testMethod("min()", minMethod, Double.TYPE);
		
		testMethod("max()", maxMethod, Double.TYPE);
		
		testMethod("average()", averageMethod, Double.TYPE);
		
		testMethod("mode()", modeMethod, Double.TYPE);
		
		testMethod("variance()", varianceMethod, Double.TYPE);
		
		testMethod("stdDev()", stdDevMethod, Double.TYPE);
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
        
        System.out.print(addTabs(methodName));
         
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
    	System.out.print(constructorName + ":\t\t");
    	
    	if (constructorName.length() < 7) System.out.print("\t");
        
        if (constructor == null)
        	System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
        else
            System.out.println("passed");
         
        System.out.println("\n----------------------");
    }
    /**
     * This is just to help the formatting of the printing
     */
    private static String addTabs(String s){
    	if (s.equals("getData()") || s.equals("equals(Stat)") || s.equals("reset()") 
    			|| s.equals("append(int[])")|| s.equals("isEmpty()")
    			|| s.equals("average()") || s.equals("variance()") || s.equals("setData(int[])")
    			 || s.equals("append(long[])") || s.equals("stdDev()") )
    		return "\t";
    	
    	else if (s.equals("min()") || s.equals("max()") || s.equals("mode()")) 
    			return "\t\t";
    	
    	else return "";
    }

}
