import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author MitchWilson
 * 
 * Designed for use by the Computer Science department at the University
 * of Georgia, Athens, GA, USA for grading of CSCI 1301 lab submissions.
 * Any publishing or posting of source code from this file is strictly 
 * prohibited unless you have written consent from the Department of 
 * Computer Science at the University of Georgia.
 * 
 * @version 3.1 - Dec 2017
 * 
 **v3.1 - added ability for students to show the stack trace of errors
 * 	to aid in debugging; source code cleaned to remove repetitive try-catch 
 * 	
 **v3.0 - Modified Lab 13 tests to provide specific feedback about individual 
 * 	tests, allowing the output to be copied directly into eLC (already a feature
 * 	in Lab 12 tests); changed Lab 13 tests to double instead of boolean return; 
 * 	the entire program will now run with feedback about every sub-test instead of
 *  an error causing a test to terminate; also added comments to translate the 
 *  reflection method calls into "normal" java that is easier to read when debugging
 * 
 **v2.2 - improved use of reflection to handle more incorrect possible classes;
 * 	this version released alongside StatMethodCheck which serves to prevent incorrect
 * 	methods in the Stat class proactively before the Stat class is executed with 
 * 	StatTester
 * 
 **v2.0 - added reflection and exception handling to decrease likelihood of crash 
 *	when grading as well as allowing StatTester to execute with an incomplete Stat
 *	class. This version prevented compilation errors withing StatTester due to 
 *	incorrect method signatures or missing methods in the Stat class which allowed 
 *	the grading to proceed more efficiently 
 *
 **v1 - combined testers for lab 12 and lab 13
 *
 * NOTE: when viewing output, the cause of the error will be displayed,
 * but in order to keep the output compact this program defaults to not show
 * the stack trace of any errors to the TA's; by changing the boolean 
 * variable, students can have the stack trace shown. Here are the 5 possible 
 * executable variations of this program along with their variable values: 
 * 		1) Default before first execution
 * 			- version = 0
 * 		2) Lab 12 grading 
 * 			- printStackTrace = false	version = 1
 * 		3) Lab 12 debugging
 * 			- printStackTrace = true   	version = 1
 * 		4) Lab 13 grading
 * 			- printStackTrace = false 	version = 2
 * 		5) Lab 13 debugging
 * 			- printStackTrace = true; 	version = 2
 * 		
 * 
 */
public class StatTester {

	//TA's should leave this as false in order to streamline grading output
	//students are encouraged to change this to true in order to help debug
	private static boolean printStackTrace = false;

	/*
	 * CHANGE THIS BEFORE EXECUTION
	 * lab 12 -> VERSION = 1
	 * lab 13 -> VERSION = 2
	 */
	private static int version = 0;

	/**
	 * Test for Lab 12 test 1
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test1() {

		System.out.println("Test 1: Default constructor and toString()\n");
		boolean passed = true;

		//objects to be used in Test 1
		Method toStringMethod = null;
		Constructor<Stat> defaultConstructor = null;
		Stat stat1 = null;
		String reference = null, result = null;

		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) { /* default constructor will remain null */ }

		try {
			toStringMethod = Stat.class.getMethod("toString", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* to String will remain null */ }

		try {
			stat1 = (Stat)defaultConstructor.newInstance();
		} catch (Exception e){ /* stat1 will remain null */ }

		//Test 1
		try{
			//this is equivalent to: result = stat1.toString();
			result = (String)toStringMethod.invoke(stat1);
			reference = "[0.0]";
			passed = (result.equals(reference));
			System.out.println("Test Passed: " + passed + "\t -> expected: "+ reference +" \t found: " + result);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [0.0] \t found: " + e.getCause());
			passed = false;
		}

		System.out.println();

		return passed;

	}

	/**
	 * Test for Lab 12 test 2
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test2() {

		boolean passed = true;
		System.out.println("\nTest 2: Stat(double[] d) constructor and toString()\n");

		Constructor<Stat> dArrayConstructor = null;
		Method toStringMethod = null;
		double[] data1 = { 1, 2, 3, 4, 5};
		Stat stat1 = null;
		String result = null, reference = "[1.0, 2.0, 3.0, 4.0, 5.0]";

		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) { /* double array constructor will remain null */ }

		try {
			toStringMethod = Stat.class.getMethod("toString", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* to string method will remain null */ }

		try{
			stat1 = dArrayConstructor.newInstance(new Object[]{data1});
		} catch (Exception e){ /* stat1 will remain null */ }

		//Test 2
		try{
			//this line is equivalent to: result = stat1.toString();
			result = (String)toStringMethod.invoke(stat1);

			passed = result.equals(reference);
			System.out.println("Test Passed: " + passed + "\t -> expected: "+ reference +" found: " + result);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ reference +" found: " + e.getCause());
			passed = false;
		}
		System.out.println();

		return passed;

	}

	/**
	 * Test for Lab 12 test 3
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test3() {
		boolean passed = true;

		System.out.println("\nTest 3: Min test\n");
		Constructor<Stat> dArrayConstructor = null;
		Method minMethod = null;
		Stat stat1 = null, stat2 = null, stat3 = null;

		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) { /* double constructor will remain null */ }

		try {
			minMethod = Stat.class.getMethod("min", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* min will remain null */ }

		double min = -20;
		double[] data1 = { min, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		double[] data2 = { 1, 2, 3, 4, 5, min, 6, 7, 8, 9, 0 };
		double[] data3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, min};

		try{
			//this line is equivalent to: stat1 = new Stat(data1)
			stat1 = dArrayConstructor.newInstance(new Object[]{data1});
		} catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat2 = new Stat(data2)
			stat2 = dArrayConstructor.newInstance(new Object[]{data2});
		} catch (Exception e){ /* stat2 will remain null */ }

		try{
			//this line is equivalent to: stat3 = new Stat(data3)
			stat3 = dArrayConstructor.newInstance(new Object[]{data3});
		} catch (Exception e){ /* stat3 will remain null */ }

		//Test 3.1
		try{
			//this line is equivalent to: min1 = stat1.min();
			double min1 = (double)minMethod.invoke(stat1);
			passed = (min == min1);
			System.out.println("Test Passed: " + (min == min1) + "\t -> expected: "+ min +" \tfound: " + min1);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ min +" \tfound: " + e.getCause());
			passed = false;
		}

		//Test 3.2
		try{
			//this line is equivalent to: min2 = stat2.min();
			double min2 = (double)minMethod.invoke(stat2);
			passed = passed && (min == min2);
			System.out.println("Test Passed: " + (min == min2) + "\t -> expected: "+ min +" \tfound: " + min2);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ min +" \tfound: " + e.getCause());
			passed = false;
		}

		//Test 3.3
		try{
			//this line is equivalent to: min3 = stat3.min();
			double min3 = (double)minMethod.invoke(stat3);
			passed = passed && (min == min3);
			System.out.println("Test Passed: " + (min == min3) + "\t -> expected: "+ min +" \tfound: " + min3);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ min +" \tfound: " + e.getCause());
			passed = false;
		}

		return passed;
	}

	/**
	 * Test for Lab 12 test 4
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test4() {
		System.out.println("\nTest 4: Max test\n");
		boolean passed = true;

		Constructor<Stat> dArrayConstructor = null;
		Method maxMethod = null;
		Stat stat1 = null, stat2 = null, stat3 = null;

		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) { /* double countructor will remain null */ }

		try {
			maxMethod = Stat.class.getMethod("max", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* max will remain null */ }


		double max = 2000;
		double[] data1 = { max, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		double[] data2 = { 1, 2, 3, 4, 5, max, 6, 7, 8, 9, 0 };
		double[] data3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, max};

		try{
			//this line is equivalent to: stat1 = new Stat(data1);
			stat1 = dArrayConstructor.newInstance(new Object[]{data1});
		} catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat2 = new Stat(data2);
			stat2 = dArrayConstructor.newInstance(new Object[]{data2});
		} catch (Exception e){ /* stat2 will remain null */ }

		try{
			//this line is equivalent to: stat3 = new Stat(data3);
			stat3 = dArrayConstructor.newInstance(new Object[]{data3});
		} catch (Exception e){ /* stat3 will remain null */ }

		//Test 4.1
		try{
			//this line is equivalent to: max1 = stat1.max();
			double max1 = (double)maxMethod.invoke(stat1);
			passed = (max == max1);
			System.out.println("Test Passed: " + (max == max1) + "\t -> expected: "+ max +" \t found: " + max1);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ max +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 4.2
		try{
			//this line is equivalent to: max2 = stat2.max();
			double max2 = (double)maxMethod.invoke(stat2);
			passed = passed && (max == max2);
			System.out.println("Test Passed: " + (max == max2) + "\t -> expected: "+ max +" \t found: " + max2);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ max +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 4.3
		try{
			//this line is equivalent to: max3 = stat3.max();
			double max3 = (double)maxMethod.invoke(stat3);
			passed = passed && (max == max3);
			System.out.println("Test Passed: " + (max == max3) + "\t -> expected: "+ max +" \t found: " + max3);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ max +" \t found: " + e.getCause());
			passed = false;
		}

		return passed;

	}

	/**
	 * Test for Lab 12 test 5
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test5() {
		System.out.println("\nTest 5: Average test\n");
		boolean passed = true;

		Constructor<Stat> dArrayConstructor = null, defaultConstructor = null;
		Stat stat1 = null, stat2 = null, stat3 = null;
		Method averageMethod = null;

		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) { /* default constructor will remain null */ }


		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) { /* double constructor will remain null */ }


		try {
			averageMethod = Stat.class.getMethod("average", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* average will remain null */ }

		double[] data2 = { 10, 10, 10, 10, 10, 10, 20, 20 };
		double[] data3 = { 1, 2, 2, 3, 3, 4 };

		try{
			//this line is equivalent to: stat1 = new Stat();
			stat1 = defaultConstructor.newInstance();
		} catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat2 = new Stat(data2);
			stat2 = dArrayConstructor.newInstance(new Object[]{data2});
		} catch (Exception e){ /* stat2 will remain null */ }

		try{
			//this line is equivalent to: stat3 = new Stat(data3);
			stat3 = dArrayConstructor.newInstance(new Object[]{data3});
		} catch (Exception e){ /* stat3 will remain null */ }

		double refAvg1 = 0;
		double refAvg2 = 12.5;
		double refAvg3 = 2.5;

		//Test 5.1
		try{
			//this line is equivalent to: average1 = stat1.average();
			double average1 = (double)averageMethod.invoke(stat1);
			passed = (refAvg1 == average1);
			System.out.println("Test Passed: " + (refAvg1 == average1) + "\t -> expected: "+ refAvg1 +" \t found: " + average1);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ refAvg1 +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 5.2
		try{
			//this line is equivalent to: average2 = stat2.average();
			double average2 = (double)averageMethod.invoke(stat2);
			passed = passed && (refAvg2 == average2);
			System.out.println("Test Passed: " + (refAvg2 == average2) + "\t -> expected: "+ refAvg2 +" \t found: " + average2);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ refAvg2 +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 5.3
		try{
			//this line is equivalent to: average3 = stat3.average();
			double average3 = (double)averageMethod.invoke(stat3);
			passed = passed && (refAvg3 == average3);
			System.out.println("Test Passed: " + (refAvg3 == average3) + "\t -> expected: "+ refAvg3 +" \t found: " + average3);

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ refAvg3 +" \t found: " + e.getCause());
			passed = false;
		}

		return passed;

	}

	/**
	 * Test for Lab 12 test 6
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test6() {
		boolean passed = true;
		System.out.println("\nTest 6: Mode test\n");
		Constructor<Stat> dArrayConstructor = null, defaultConstructor = null;
		Stat stat1 = null, stat2 = null, stat3 = null;

		Method modeMethod = null;

		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) {}

		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) {}

		try {
			modeMethod = Stat.class.getMethod("mode", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}

		double[] data2 = { 10, 10, 10, 10, 10, 10, 20, 20 };
		double[] data3 = { 1, 2, 2, 3, 3, 4 };

		try{
			//this line is equivalent to: stat1 = new Stat();
			stat1 = defaultConstructor.newInstance();
		}catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat2 = new Stat(data2);
			stat2 = dArrayConstructor.newInstance(new Object[]{data2});
		}catch (Exception e){ /* stat2 will remain null */ }

		try{
			//this line is equivalent to: stat3 = new Stat(data3);
			stat3 = dArrayConstructor.newInstance(new Object[]{data3});
		}catch (Exception e){ /* stat3 will remain null */ }

		double refMode1 = 0.0;
		double refMode2 = 10;

		//Test 6.1
		try{
			//this line is equivalent to: mode1 = stat1.mode();
			double mode1 = (double)modeMethod.invoke(stat1);
			passed = (refMode1 == mode1);
			System.out.println("Test Passed: " + (refMode1 == mode1) + "\t -> expected: "+ refMode1 +" \t found: " + mode1);

		}catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ refMode1 +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 6.2
		try{
			//this line is equivalent to: mode2 = stat2.mode();
			double mode2 = (double)modeMethod.invoke(stat2);
			passed = passed && (refMode2 == mode2);
			System.out.println("Test Passed: " + (refMode2 == mode2) + "\t -> expected: "+ refMode2 +" \t found: " + mode2);

		}catch (Exception e){ 
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ refMode2 +" \t found: " + e.getCause());
			passed = false;
		}

		//Test 6.3
		try{
			//this line is equivalent to: mode3 = stat3.mode();
			double mode3 = (double)modeMethod.invoke(stat3);
			passed = passed && (Double.isNaN(mode3));
			System.out.println("Test Passed: " + (Double.isNaN(mode3)) + "\t -> expected: "+ Double.NaN +" \t found: " + mode3);

		}catch (Exception e){ 
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: "+ Double.NaN +" \t found: " + e.getCause());
			passed = false;
		}

		return passed;

	}

	/**
	 * Test for Lab 12 test 7
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test7() {
		boolean passed = true;
		System.out.println("\nTest 7: Equals Test\n");
		Constructor<Stat> dArrayConstructor = null;
		Stat stat1 = null, stat2 = null, stat3 = null;

		Method equalsMethod = null;

		try {
			dArrayConstructor = Stat.class.getConstructor(double[].class);
		} catch (Exception e) { /* double constructor will remain null */ }

		try {
			equalsMethod = Stat.class.getMethod("equals", new Class[]{Stat.class});
		} catch (NoSuchMethodException e) { /* equals method will remain null */}


		double[] data1 = { 1, 2, 2, 3, 3, 4 };
		double[] data2 = { 1, 2, 2, 3, 3, 4 };
		double[] data3 = { 2, 2, 3, 3 };

		try{
			//this line is equivalent to: stat1 = new Stat(data1);
			stat1 = dArrayConstructor.newInstance(new Object[]{data1});
		} catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat2 = new Stat(data2);
			stat2 = dArrayConstructor.newInstance(new Object[]{data2});
		} catch (Exception e){ /* stat2 will remain null */ }

		try{
			//this line is equivalent to: stat3 = new Stat(data3);
			stat3 = dArrayConstructor.newInstance(new Object[]{data3});
		} catch (Exception e){ /* stat3 will remain null */ }


		try{
			//this line is equivalent to: res1 = (stat1.equals(stat1));
			boolean res1 = (boolean)equalsMethod.invoke(stat1, new Object[]{stat1});
			System.out.println("stat1.equals(stat1)\t -> expected: " + true + "\t found: " + res1);
			passed = res1;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("stat1.equals(stat1)\t -> expected: " + true + "\t found: " + e.getCause());
			passed = false;
		}

		try{
			//this line is equivalent to: res2 = (stat2.equals(stat1));
			boolean res2 = (boolean)equalsMethod.invoke(stat2, new Object[]{stat1});
			System.out.println("stat2.equals(stat1)\t -> expected: " + true + "\t found: " + res2);
			passed = passed && res2;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("stat2.equals(stat1)\t -> expected: " + true + "\t found: " + e.getCause());
			passed = false;
		}

		try{
			//this line is equivalent to: res3 = !(stat3.equals(stat2));
			boolean res3 = !(boolean)equalsMethod.invoke(stat3, new Object[]{stat2});
			System.out.println("!stat3.equals(stat2)\t -> expected: " + true + "\t found: " + res3);
			passed = passed && res3;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("!stat3.equals(stat2)\t -> expected: " + true + "\t found: " + e.getCause());
			passed = false;
		}

		return passed;
	}

	/**
	 * Test for Lab 12 test 8
	 * NOTE that lab 12 tests are all-or-nothing, so method returns a boolean
	 * @return <code>true</code> if the test is passed, <code>false</code> otherwise
	 */
	private static boolean v1test8() {
		boolean passed = true; 
		System.out.println("\nTest 8: Getter/Setter test\n");
		Constructor<Stat> defaultConstructor = null;
		Method getDataMethod = null, setDataMethod = null;
		Stat stat1 = null;

		double[] data1 = { 1, 2, 3, 4 };
		double[] data2 = { 1, 2, 3, 4};
		double [] data3 = null;
		
		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) { /* default constructor will remain null */ }

		try {
			getDataMethod = Stat.class.getMethod("getData", (Class<?> []) null);
		} catch (NoSuchMethodException e) { /* getData will remain null */ }

		try{
			setDataMethod = Stat.class.getMethod("setData", new Class[] {double[].class});
		} catch (Exception e){ /* setData will remain null */ }

		try{
			//this line is equivalent to: stat1 = new Stat();
			stat1 = defaultConstructor.newInstance();
		} catch (Exception e){ /* stat1 will remain null */ }

		try{
			//this line is equivalent to: stat1.setData(data1);
			setDataMethod.invoke(stat1, new Object[]{data1});
		} catch (Exception e){ /* stat1 will be unchanged */ }

		data1[0] = 0;

		try{
			//this line is equivalent to: data3 = stat1.getData();
			data3 = (double[])getDataMethod.invoke(stat1);
			boolean result = Arrays.equals(data2,  data3);
			passed = result;
			System.out.println("data2 equals data3\t -> expected: true\t found: " + result);

		} catch (Exception e) {
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("data2 equals data3\t -> expected: "+ true + "\t found: " + e.getCause());
			passed = false;
		}

		return passed;
	}


	/**
	 * Test for Lab 13 test 1
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 25
	 */
	private static double v2test1() {
		/*
		 * NOTE about grading: 
		 * 		entire test is worth 25 points 
		 * 		entire tests 1.x are worth 5 points each, divided as so:
		 * 		1.x.1 are worth 3 points each
		 * 		1.x.2 are worth 2 points each
		 */
		double totalPoints = 25;
		boolean passed = true;

		System.out.println("\nTest 1:\n ");

		//objects for test 1
		Constructor<Stat> defaultConstructor = null, doubleConstructor = null,
				floatConstructor = null, intConstructor = null, longConstructor = null;
		Stat test;
		double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
		float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
		int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
		long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };

		//default constructor
		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) {}

		//double[] param constructor
		try {
			doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
		} catch (Exception e) {}

		//float[] param constructor
		try {
			floatConstructor = Stat.class.getConstructor(new Class[]{float[].class});
		} catch (Exception e) {}

		//int[] param constructor
		try {
			intConstructor = Stat.class.getConstructor(new Class[]{int[].class});
		} catch (Exception e) {}

		//long[] param constructor
		try {
			longConstructor = Stat.class.getConstructor(new Class[]{long[].class});
		} catch (Exception e) {}

		//-->Begin Test 1.1
		System.out.println("constructor test 1 (default)");

		//Test 1.1.1
		try {
			//this line is equivalent to: test = new Stat();
			test = (Stat)defaultConstructor.newInstance();

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			//if student debug mode is on, print stack trace and exit
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 5;
		}

		System.out.println();

		//-->Begin Test 1.2
		System.out.println("constructor test 2 (double[])");

		//Test 1.2.1
		try{
			//this line is equivalent to: test = new Stat(testArrayOne);
			test = (Stat)doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		

		//Test 1.2.2
		try{
			testArrayOne = null;
			//this line is equivalent to: test = new Stat(testArrayOne);
			test = (Stat)doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;	

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}


		System.out.println();

		//-->Begin Test 1.3
		System.out.println("constructor test 3 (float[])");

		//Test 1.3.1
		try{
			//this line is equivalent to: test = new Stat(testArrayTwo);
			test = (Stat)floatConstructor.newInstance(testArrayTwo);

			boolean localTestPassed = (test.toString().trim().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test1.3.2
		try{
			testArrayTwo = null;
			
			//this line is equivalent to: test = new Stat(testArrayTwo);
			test = (Stat)floatConstructor.newInstance(testArrayTwo);

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		System.out.println();

		//-->Begin Test 1.4
		System.out.println("constructor test 4 (int[])");

		//Test 1.4.1
		try{
			//this line is equivalent to: test = new Stat(testArrayThree);
			test = (Stat)intConstructor.newInstance(testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}


		//Test 1.4.2
		try{
			testArrayThree = null;
			//this line is equivalent to: test = new Stat(testArrayThree);
			test = (Stat)intConstructor.newInstance(testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		System.out.println();

		//-->Begin Test 1.5
		System.out.println("constructor test 5 (long[])");

		//Test 1.5.1
		try{
			//this line is equivalent to: test = new Stat(testArrayFour);
			test = (Stat)longConstructor.newInstance(testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test 1.5.2
		try{
			testArrayFour = null;
			//this line is equivalent to: test = new Stat(testArrayFour);
			test = (Stat)longConstructor.newInstance(testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		return totalPoints;

	}

	/**
	 * Test for Lab 13 test 2
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 24
	 */
	private static double v2test2() {
		/*
		 * NOTE about grading:
		 * 		entire test is worth 24 points 
		 * 		Each test 2.x is worth 6 points each divided as so:
		 * 		2.x.1 are worth 1.5 points each
		 * 		2.x.2 are worth 1.5 points each
		 * 		2.x.3 are worth 3 points each
		 */
		double totalPoints = 24;
		boolean passed = true;

		System.out.println("\nTest 2:\n ");

		//create objects that will be used for the entirety of Test 2
		Constructor<Stat> defaultConstructor = null;
		Method getData = null;
		Method setData = null;
		Stat test = null;

		double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
		float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
		int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
		long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };


		//we will change the setData method object when we test for different input parameters
		//the first test will use a double input parameter
		try{
			setData = Stat.class.getMethod("setData", new Class[]{double[].class});

		}catch (Exception e){ /* setData will remain null */ }

		try{
			getData = Stat.class.getMethod("getData");

		}catch (Exception e){ /* getData will remain null */ }

		try {
			defaultConstructor = Stat.class.getConstructor();

		} catch (Exception e){ /* default constructor will remain null */ }

		try {
			//this line is equivalent to: test = new Stat();
			test = (Stat)defaultConstructor.newInstance();

		} catch (Exception e) { /* test will remain null */ }


		System.out.println();
		//-->Begin Test 2

		//-->Begin Test 2.1
		System.out.println("setData(double[] a) test");

		//Test 2.1.1
		try{
			//this line is equivalent to: test.setData(testArrayOne);
			setData.invoke(test, new Object[]{testArrayOne});

			testArrayOne[0] = 0;

			boolean localTestPassed = (test.toString().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.1.2
		try{
			testArrayOne[0] = 0;

			//this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).trim().equals(...);
			boolean localTestPassed = (Arrays.toString((double[])getData.invoke(test)).trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + Arrays.toString((double[])getData.invoke(test))); 

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}


		//Test 2.1.3
		try{
			testArrayOne = null;
			
			//this line is equivalent to: test.setData(testArrayOne);
			setData.invoke(test, new Object[]{testArrayOne});

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}


		//-->Begin Test 2.2
		System.out.println("\nsetData(float[] a) test");

		//change the Method object setData to use a float [] parameter
		setData = null;
		try{
			setData = Stat.class.getMethod("setData", new Class[]{float[].class});

		}catch (Exception e){ /* setData will remain null */ }

		//Test 2.2.1
		try{
			//this line is equivalent to: test.setData(testArrayTwo);
			setData.invoke(test, new Object[]{testArrayTwo});

			boolean localTestPassed = (test.toString().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){

			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.2.2
		try{
			testArrayTwo[0] = 0;
			
			//this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).equals(...);
			boolean localTestPassed = (Arrays.toString((double[])getData.invoke(test)).equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + Arrays.toString((double[])getData.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.2.3
		try{
			testArrayTwo = null;
			
			//this line is equivalent to: test.setData(testArrayTwo);
			setData.invoke(test, new Object[]{testArrayTwo});

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		//-->Begin Test 2.3
		System.out.println("setData(int[] a) test");

		//change the Method object setData to use a int [] parameter
		setData = null;
		try{
			setData = Stat.class.getMethod("setData", new Class[]{int[].class});

		}catch (Exception e){ /* setData will remain null */ }

		//Test 2.3.1
		try{
			//this line is equivalent to: test.setData(testArrayThree);
			setData.invoke(test, new Object[]{testArrayThree});

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.3.2
		try{
			testArrayThree[0] = 0;
			//this line is equivalent to: test.setData(testArrayThree);
			boolean localTestPassed = (Arrays.toString((double[])getData.invoke(test)).trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + Arrays.toString((double[])getData.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.3.3
		try{
			testArrayThree = null;
			
			//this line is equivalent to: test.setData(testArrayThree);
			setData.invoke(test, new Object[]{testArrayThree});

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		//-->Begin Test 2.4
		System.out.println("setData(long[] a) test");

		//change the Method object setData to use a long [] parameter
		setData = null;
		try{
			setData = Stat.class.getMethod("setData", new Class[]{long[].class});

		}catch (Exception e){ /* setData will remain null */ }

		//Test 2.4.1
		try{
			//this line is equivalent to: test.setData(testArrayFour);
			setData.invoke(test, new Object[]{testArrayFour});

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.4.2
		try{
			testArrayFour[0] = 0;
			
			//this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).trim().equals(...);
			boolean localTestPassed = (Arrays.toString((double[])getData.invoke(test)).trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + Arrays.toString((double[])getData.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		//Test 2.4.3
		try{
			testArrayFour = null;
			
			//this line is equivalent to: test.setData(testArrayFour);
			setData.invoke(test, new Object[]{testArrayFour});

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		return totalPoints;

	}

	/**
	 * Test for Lab 13 test 1
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 24
	 */
	private static double v2test3() {
		/*
		 * NOTE about grading: 
		 * 		entire test 3 is worth 24 points
		 * 		entire tests 3.x are worth 6 points each, divided as so:
		 * 		3.x.1 are worth 3 points each
		 * 		3.x.2 are worth 3 points each
		 */
		double totalPoints = 24;
		boolean passed = true;

		System.out.println("\nTest 3:\n ");

		//create objects to be used in test 3
		Constructor<Stat> doubleConstructor = null;
		Method append = null;
		Stat test = null;

		double[] initialArray = { 1, 2, 3, 4 };
		double[] testArrayOne = { 5, 6, 7, 8, 9 };
		float[] testArrayTwo = { 50, 6, 7, 8, 9 };
		int[] testArrayThree = { 500, 6, 7, 8, 9 };
		long[] testArrayFour = { 5000, 6, 7, 8, 9 };

		try{
			doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
		} catch (Exception e){ /* double constructor will remain null */ }


		//the append method will be changed throughout this test in order to test for different input parameters
		//for the first test it will use a double []
		try{
			append = Stat.class.getMethod("append", new Class[]{double[].class});
		} catch (Exception e){ /* append method will remain null */ }

		//--> Begin test 3.1
		System.out.println("\nappend(double[] a) test");

		//Test 3.1.1
		try{
			//this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);
			
			//this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]")) ;

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test 3.1.2
		try{
			testArrayOne = null;
			
			//this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}
		System.out.println();

		//--> Begin Test 3.2
		System.out.println("append(float[] a) test");

		//change append to use a float [] as input parameter
		append = null;
		try{
			append = Stat.class.getMethod("append", new Class[]{float[].class});
		} catch (Exception e){ /* append method will remain null */ }

		//Test 3.2.1
		try{
			//this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);
			
			//this line is equivalent to: test.append(testArrayTwo);
			append.invoke(test, testArrayTwo);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " +  localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test 3.2.2
		try{
			testArrayTwo = null;
			
			//this line is equivalent to: test.append(testArrayTwo);
			append.invoke(test, testArrayTwo);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}


		//--> Begin Test 3.3
		System.out.println("\nappend(int[] a) test");

		//change append to use a int [] as input parameter
		append = null;
		try{
			append = Stat.class.getMethod("append", new Class[]{int[].class});
		} catch (Exception e){ /* append method will remain null */ }

		//Test 3.3.1
		try{
			//this line is equivalent to: test = new Stat (initialArray);
			test = doubleConstructor.newInstance(initialArray);
			
			//this line is equivalent to: test.append(testArrayThree);
			append.invoke(test, testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test 3.3.2
		try{
			testArrayThree = null;
			
			//this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		//-->Begin Test 3.4
		System.out.println("append(long[] a) test");

		//change append to use a long [] as input parameter
		append = null;
		try{
			append = Stat.class.getMethod("append", new Class[]{long[].class});

		} catch (Exception e){ /* append method will remain null */ }

		//Test 3.4.1
		try{
			//this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);
			
			//this line is equivalent to: test.append(testArrayFour);
			append.invoke(test, testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		//Test 3.4.2
		try{
			testArrayFour = null;
			
			//this line is equivalent to: test.append(testArrayFour);
			append.invoke(test, testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " +localTestPassed  + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		return totalPoints;
	}

	/**
	 * Test for Lab 13 test 4
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 3
	 */
	private static double v2test4() {
		/*
		 * NOTE about grading: 
		 * 		entire test 4 is worth 3 points, divided as so:
		 * 		4.1 is worth 2
		 * 		4.2 is worth 1
		 */
		double totalPoints = 3;
		boolean passed = true;

		System.out.println("\nTest 4:\n ");

		double[] testArrayOne = { 1, 2, 3, 4 };
		Stat test = null;
		System.out.println("reset() and isEmpty() test");

		//create objects for Test 4
		Constructor<Stat> doubleConstructor = null;
		Method isEmptyMethod = null, resetMethod = null;


		try {
			doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
		} catch (Exception e) { /* double constructor will remain null */ }

		try{
			isEmptyMethod = Stat.class.getMethod("isEmpty", (Class<?>[])null);
		}catch (Exception e){ /* isEmpty will remain null */}

		try{
			resetMethod = Stat.class.getMethod("reset", (Class<?>[])null);
		}catch (Exception e){ /* reset will remain null */ }


		try{
			//this line is equivalent to: test = new Stat(testArrayOne);
			test = doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected [1.0, 2.0, 3.0, 4.0]\t found: " + test);

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed: " + false + "\t-> expected [1.0, 2.0, 3.0, 4.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		try{
			//this line is equivalent to: test.reset();
			resetMethod.invoke(test);

			//this line is equivalent to: localTestPassed = test.isEmpty();
			boolean localTestPassed = (boolean)isEmptyMethod.invoke(test);

			System.out.println("Test Passed: " + localTestPassed + " \t-> expected true\t\t\t found: " + (boolean)isEmptyMethod.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 1;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + " \t-> expected true\t\t\t found: " + e.getCause());
			passed = false;
			totalPoints -= 1;
		}

		return totalPoints;
	}

	/**
	 * Test for Lab 13 test 5
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 12
	 */
	private static double v2test5() {
		/*
		 * NOTE about grading: 
		 * 		entire test 5 is worth 12 points, divided as so:
		 * 		5.x are worth 2 points each
		 */
		double totalPoints = 12;
		boolean passed = true;

		System.out.println("\nTest 5: \n");

		//create objects for Test 5
		Constructor<Stat> doubleConstructor = null;
		Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
		Stat test = null;
		double[] data = null;

		System.out.println("stats test (null)");

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
		} catch (Exception e) { /* double constructor will remain null */ }

		try{
			min = Stat.class.getMethod("min", (Class<?>[])null);
		}catch (Exception e){ /* min will remain null */ }

		try{
			max = Stat.class.getMethod("max", (Class<?>[])null);
		}catch (Exception e){ /* max will remain null */ }

		try{
			average = Stat.class.getMethod("average", (Class<?>[])null);
		}catch (Exception e){ /* average will remain null */ }

		try{
			mode = Stat.class.getMethod("mode", (Class<?>[])null);
		}catch (Exception e){ /* mode will remain null */ }

		try{
			variance = Stat.class.getMethod("variance", (Class<?>[])null);
		}catch (Exception e){ /* variance will remain null */ }

		try{
			stdev = Stat.class.getMethod("standardDeviation", (Class<?>[])null);
		}catch (Exception e){ /* stdev will remain null */ }

		try{
			//this line is equivalent to: test = new Stat(data);
			test = doubleConstructor.newInstance(data);
		} catch (Exception e){ /* test will remain null */ }

		boolean localTestPassed = false;
		//Test 5.1
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.min());
			localTestPassed = Double.isNaN((double)min.invoke(test));
			
			System.out.println("Test Passed(min)  : " + localTestPassed + "\t-> expected NaN\t found: " + min.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0);
			}
			System.out.println("Test Passed(min)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 5.2
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.max());
			localTestPassed = Double.isNaN((double)max.invoke(test));
			
			System.out.println("Test Passed(max)  : " + localTestPassed + "\t-> expected NaN\t found: " + max.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(max)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 5.3
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.average());
			localTestPassed = Double.isNaN((double)average.invoke(test));
			
			System.out.println("Test Passed(avg)  : " + localTestPassed + "\t-> expected NaN\t found: " + average.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(avg)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 5.4
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.mode());
			localTestPassed = Double.isNaN((double)mode.invoke(test));
			
			System.out.println("Test Passed(mode) : " + localTestPassed + "\t-> expected NaN\t found: " + mode.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(mode) : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 5.5
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.variance());
			localTestPassed = Double.isNaN((double)variance.invoke(test));
			
			System.out.println("Test Passed(var)  : " + localTestPassed + "\t-> expected NaN\t found: " + variance.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(var)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 5.6
		try{
			//this line is equivalent to: localTestPassed = Double.isNan(test.standardDeviation());
			localTestPassed = Double.isNaN((double)stdev.invoke(test));
			
			System.out.println("Test Passed(stdev): " + localTestPassed + "\t-> expected NaN\t found: " + stdev.invoke(test));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(stdev): " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}	

		return totalPoints;

	}

	/**
	 * Test for Lab 13 test 6
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * @return total number of points earned on this test, out of 12
	 */
	private static double v2test6() {
		/*
		 * NOTE about grading: 
		 * 		entire test 6 is worth 12 points, divided as so:
		 * 		6.x are worth 2 points each
		 */
		double totalPoints = 12;
		boolean passed = true;

		System.out.println("\nTest 6:\n ");

		//objects for Test 6
		Constructor<Stat> doubleConstructor = null;
		Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
		Stat test = null;
		double[] data = { -5.3, 2.5, 88.9, 0, 0.0, 28, 16.5, 88.9, 109.5,
				-90, 88.9, 100.34, 50.01, 50.01, -8 };


		System.out.println("stats test (real data)");

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
		} catch (Exception e) { /* double constructor will remain null */ }

		try{
			min = Stat.class.getMethod("min", (Class<?>[])null);
		}catch (Exception e){ /* min will remain null */ }

		try{
			max = Stat.class.getMethod("max", (Class<?>[])null);
		}catch (Exception e){ /* max will remain null */ }

		try{
			average = Stat.class.getMethod("average", (Class<?>[])null);
		}catch (Exception e){ /* average will remain null */ }

		try{
			mode = Stat.class.getMethod("mode", (Class<?>[])null);
		}catch (Exception e){ /* mode will remain null */ }

		try{
			variance = Stat.class.getMethod("variance", (Class<?>[])null);
		}catch (Exception e){ /* variance will remain null */ }

		try{
			stdev = Stat.class.getMethod("standardDeviation", (Class<?>[])null);
		}catch (Exception e){ /* stdev will remain null */ }

		try{
			//this line is equivalent to: test = new Stat(data);
			test = doubleConstructor.newInstance(data);
		} catch (Exception e){ /* test will remain null */ }

		boolean localTestPassed = false;
		//Test 6.1
		try{
			//this line is equivalent to: localTestPassed = test.min() == -90;
			localTestPassed = ((double)min.invoke(test) == -90);
			
			System.out.println("Test Passed(min)  : " + localTestPassed + "\t-> expected -90\t\t\t found: " + ((double)min.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(min)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 6.2
		try{
			//this line is equivalent to: localTestPassed = test.max() == 109.5;
			localTestPassed = ((double)max.invoke(test) == 109.5);
			
			System.out.println("Test Passed(max)  : " + localTestPassed + "\t-> expected 109.5\t\t found: " + ((double)max.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(max)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 6.3
		try{
			//this line is equivalent to: localTestPassed = test.average() == 34.684;
			localTestPassed = ((double)average.invoke(test) == 34.684);
			
			System.out.println("Test Passed(avg)  : " + localTestPassed + "\t-> expected 34.684\t\t found: " + ((double)average.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(min)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 6.4
		try{
			//this line is equivalent to: localTestPassed = test.mode() == 88.9;
			localTestPassed = ((double)mode.invoke(test) == 88.9);
			
			System.out.println("Test Passed(mode) : " + localTestPassed + "\t-> expected 88.9\t\t found: " + ((double)mode.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(mode) : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 6.5
		try{
			//this line is equivalent to: localTestPassed = test.variance() == 2798.6591...;
			localTestPassed = ((double)variance.invoke(test) == 2798.6591973333334);
			System.out.println("Test Passed(var)  : " + localTestPassed + "\t-> expected 2798.6591973333334\t found: " + ((double)variance.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(var)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		//Test 6.6
		try{
			//this line is equivalent to: localTestPassed = test.standardDeviation() == 52.9023...;
			localTestPassed = ((double)stdev.invoke(test) == 52.90235530988515);
			System.out.println("Test Passed(stdev): " + localTestPassed + "\t-> expected 52.90235530988515\t found: " + ((double)stdev.invoke(test)));

			//deduct points if incorrect
			if (! localTestPassed) 
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e){
			//if student debug mode is on, print stack trace and exit 
			if(printStackTrace){ 
				e.printStackTrace(); 
				System.exit(0); 
			}
			System.out.println("Test Passed(stdev): " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		return totalPoints;
	}

	public static void main(String[] args) {

		if (version == 1){
			int grade = 100;

			boolean v1t1, v1t2, v1t3, v1t4, v1t5, v1t6, v1t7, v1t8;
			v1t1 = v1t2 = v1t3 = v1t4 = v1t5 = v1t6 = v1t7 = v1t8 = false;

			v1t1=v1test1();

			System.out.println("--------");

			v1t2=v1test2();

			System.out.println("--------");

			v1t3=v1test3();

			System.out.println("--------");

			v1t4=v1test4();

			System.out.println("--------");

			v1t5=v1test5();

			System.out.println("--------");

			v1t6=v1test6();

			System.out.println("--------");

			v1t7=v1test7();

			System.out.println("--------");

			v1t8=v1test8();


			System.out.println("--------------------------------------");
			System.out.println("--------------------------------------\n");

			if (!v1t1){
				System.out.println("-15 test 1 (default contructor & toString)");
				grade -= 15;
			}
			if (!v1t2){
				System.out.println("-15 test 2 (1 param constructor & toString)");
				grade -= 15;
			}
			if (!v1t3){
				System.out.println("-15 test 3 (min)");
				grade -= 15;
			}
			if (!v1t4){
				System.out.println("-15 test 4 (max)");
				grade -= 15;
			}
			if (!v1t5){
				System.out.println("-10 test 5 (average)");
				grade -= 10;
			}
			if (!v1t6){
				System.out.println("-10 test 6 (mode)");
				grade -= 10;
			}
			if (!v1t7){
				System.out.println("-10 test 7 (equals)");
				grade -= 10;
			}
			if (!v1t8){
				System.out.println("-10 test 8 (getter/setter)");
				grade -= 10;
			}

			System.out.println("\nGrade for Stat class " + grade + "/100");
			System.out.println("ALL TESTS PASSED: " + (v1t1 && v1t2 && v1t3 && v1t4 && v1t5 && v1t6 && v1t7 && v1t8));

		}else if (version == 2){

			double t1, t2, t3, t4, t5, t6;
			t1 = t2 = t3 = t4 = t5 = t6 = 0.0;
			try{
				t1=v2test1();
			}catch(Exception e){}

			System.out.println("--------");

			try{
				t2=v2test2();
			}catch(Exception e){}

			System.out.println("--------");

			try{
				t3=v2test3();
			}catch(Exception e){}

			System.out.println("--------");

			try{
				t4=v2test4();
			}catch(Exception e){}

			System.out.println("--------");

			try{
				t5=v2test5();
			}catch(Exception e){}

			System.out.println("--------");

			try{
				t6=v2test6();
			}catch(Exception e){}

			System.out.println("\n--------------------------------------");
			System.out.println("--------------------------------------\n");
			if (t1 < 25) System.out.println("[-" + (25 - t1) + "] test 1");
			if (t2 < 24) System.out.println("[-" + (24 - t2) + "] test 2");
			if (t3 < 24) System.out.println("[-" + (24 - t3) + "] test 3");
			if (t4 < 3)  System.out.println("[-" + (3 - t4)  + "] test 4");
			if (t5 < 12) System.out.println("[-" + (12 - t5) + "] test 5");
			if (t6 < 12) System.out.println("[-" + (12 - t6) + "] test 6");

			System.out.println("Score for Stat class: " + (t1 + t2 + t3 + t4 + t5 + t6) + "/100");
			System.out.println("ALL TESTS PASSED: " + ((t1 + t2 + t3 + t4 + t5 + t6) == 100));

		}else{
			System.out.println("READ THE COMMENT AT THE TOP OF THE CODE"
					+ "\nand make sure you've changed the version variable");
		}
	}

}
