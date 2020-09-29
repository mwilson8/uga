import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author MitchWilson
 * 
 *         Designed for use by the Computer Science department at the University
 *         of Georgia, Athens, GA, USA for grading of CSCI 1301 lab submissions.
 *         Any publishing or posting of source code from this file is strictly
 *         prohibited unless you have written consent from the Department of
 *         Computer Science at the University of Georgia.
 * 
 * @version 3.2 - Jan 2018
 * 
 **v3.2 - separated graders for lab 12 and lab 13 to allow students to
 *			run the tester for lab 12 without having access to the test cases
 *         	for lab 13
 * 
 **v3.1 - added ability for students to show the stack trace of errors
 *          to aid in debugging; source code cleaned to remove repetitive
 *          try-catch
 * 
 **v3.0 - modified Lab 13 tests to provide specific feedback about
 *          individual tests, allowing the output to be copied directly into eLC
 *          (already a feature in Lab 12 tests); changed Lab 13 tests to double
 *          instead of boolean return; the entire program will now run with
 *          feedback about every sub-test instead of an error causing a test to
 *          terminate; also added comments to translate the reflection method
 *          calls into "normal" java that is easier to read when debugging
 * 
 **v2.2 - improved use of reflection to handle more incorrect possible
 *          classes; this version released alongside StatMethodCheck which
 *          serves to prevent incorrect methods in the Stat class pro-actively
 *          before the Stat class is executed with StatTester
 * 
 **v2.0 - added reflection and exception handling to decrease
 *			likelihood of crash when grading as well as allowing StatTester to
 *          execute with an incomplete Stat class. This version prevented
 *          compilation errors within StatTester due to incorrect method
 *          signatures or missing methods in the Stat class which allowed the
 *          grading to proceed more efficiently
 *
 **v1 - combined testers for lab 12 and lab 13
 *
 *  	NOTE: when viewing output, the cause of the error will be displayed,
 *          but in order to keep the output compact this program defaults to not
 *          show the stack trace of any errors to the TA's; by changing the
 *          boolean variable, students can have the stack trace shown. 
 *          
 *          Change the variable <code>printStackTrace</code> to <code>true</code>
 *          if you are wanting the program to "crash" and allow you to step through
 *          the errors
 * 
 * 
 */
public class StatGrading_v2 {

	// TA's should leave this as false in order to streamline grading output
	// students are encouraged to change this to true in order to help debug
	private static boolean printStackTrace = false;

	
	/*
	 * if (t1 < 25)
			System.out.println("[-" + (25 - t1) + "] test 1");
		if (t2 < 24)
			System.out.println("[-" + (24 - t2) + "] test 2");
		if (t3 < 24)
			System.out.println("[-" + (24 - t3) + "] test 3");
		if (t4 < 3)
			System.out.println("[-" + (3 - t4) + "] test 4");
		if (t5 < 12)
			System.out.println("[-" + (12 - t5) + "] test 5");
		if (t6 < 12)
			System.out.println("[-" + (12 - t6) + "] test 6");
	 */
	
	private static final double TEST_1_MAX_POINTS = 25.0;
	private static final double TEST_2_MAX_POINTS = 24.0;
	private static final double TEST_3_MAX_POINTS = 24.0;
	private static final double TEST_4_MAX_POINTS = 3.0;
	private static final double TEST_5_MAX_POINTS = 12.0;
	private static final double TEST_6_MAX_POINTS = 12.0;
	
	/**
	 * Test for Lab 13 test 1 
	 * NOTE that lab 13 tests are able to receive partial credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 25
	 */
	private static double test1() {
		/*
		 * NOTE about grading: entire test is worth 25 points entire tests 
		 * 1.x are worth 5 points each, divided as so: 
		 * 1.x.1 are worth 3 points each
		 * 1.x.2 are worth 2 points each
		 */
		double totalPoints = TEST_1_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 1:\n ");

		// objects for test 1
		Constructor<Stat> defaultConstructor = null, doubleConstructor = null, floatConstructor = null,
				intConstructor = null, longConstructor = null;
		Stat test;
		double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
		float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
		int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
		long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };

		// default constructor
		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) {}

		// double[] param constructor
		try {
			doubleConstructor = Stat.class.getConstructor(new Class[] { double[].class });
		} catch (Exception e) {}

		// float[] param constructor
		try {
			floatConstructor = Stat.class.getConstructor(new Class[] { float[].class });
		} catch (Exception e) {}

		// int[] param constructor
		try {
			intConstructor = Stat.class.getConstructor(new Class[] { int[].class });
		} catch (Exception e) {}

		// long[] param constructor
		try {
			longConstructor = Stat.class.getConstructor(new Class[] { long[].class });
		} catch (Exception e) {}

		// -->Begin Test 1.1
		System.out.println("constructor test 1 (default)");

		// Test 1.1.1
		try {
			// this line is equivalent to: test = new Stat();
			test = (Stat) defaultConstructor.newInstance();

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 5;
		}

		System.out.println();

		// -->Begin Test 1.2
		System.out.println("constructor test 2 (double[])");

		// Test 1.2.1
		try {
			
			// this line is equivalent to: test = new Stat(testArrayOne);
			test = (Stat) doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 1.2.2
		try {
			testArrayOne = null;
			
			// this line is equivalent to: test = new Stat(testArrayOne);
			test = (Stat) doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		System.out.println();

		// -->Begin Test 1.3
		System.out.println("constructor test 3 (float[])");

		// Test 1.3.1
		try {
			
			// this line is equivalent to: test = new Stat(testArrayTwo);
			test = (Stat) floatConstructor.newInstance(testArrayTwo);

			boolean localTestPassed = (test.toString().trim().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 1.3.2
		try {
			testArrayTwo = null;

			// this line is equivalent to: test = new Stat(testArrayTwo);
			test = (Stat) floatConstructor.newInstance(testArrayTwo);

			boolean localTestPassed = (test.toString().trim().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		System.out.println();

		// -->Begin Test 1.4
		System.out.println("constructor test 4 (int[])");

		// Test 1.4.1
		try {
			
			// this line is equivalent to: test = new Stat(testArrayThree);
			test = (Stat) intConstructor.newInstance(testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: "
					+ e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 1.4.2
		try {
			testArrayThree = null;
			
			// this line is equivalent to: test = new Stat(testArrayThree);
			test = (Stat) intConstructor.newInstance(testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: []\t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		System.out.println();

		// -->Begin Test 1.5
		System.out.println("constructor test 5 (long[])");

		// Test 1.5.1
		try {
			
			// this line is equivalent to: test = new Stat(testArrayFour);
			test = (Stat) longConstructor.newInstance(testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0]\tfound: "
					+ e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 1.5.2
		try {
			testArrayFour = null;
			
			// this line is equivalent to: test = new Stat(testArrayFour);
			test = (Stat) longConstructor.newInstance(testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected: []\t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
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
	 * Test for Lab 13 test 2 NOTE that lab 13 tests are able to receive partial
	 * credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 24
	 */
	private static double test2() {
		/*
		 * NOTE about grading: entire test is worth 24 points 
		 * Each test 2.x is worth 6 points each divided as so:
		 *  
		 * 2.x.1 are worth 1.5 points each
		 * 2.x.2 are worth 1.5 points each 
		 * 2.x.3 are worth 3 points each
		 */
		double totalPoints = TEST_2_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 2:\n ");

		// create objects that will be used for the entirety of Test 2
		Constructor<Stat> defaultConstructor = null;
		Method getData = null;
		Method setData = null;
		Stat test = null;

		double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
		float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
		int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
		long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };

		// we will change the setData method object when we test for different
		// input parameters; the first test will use a double input parameter
		try {
			setData = Stat.class.getMethod("setData", new Class[] { double[].class });
		} catch (Exception e) { /* setData will remain null */ }

		try {
			getData = Stat.class.getMethod("getData");
		} catch (Exception e) { /* getData will remain null */ }

		try {
			defaultConstructor = Stat.class.getConstructor();
		} catch (Exception e) { /* default constructor will remain null */ }

		try {
			
			// this line is equivalent to: test = new Stat();
			test = (Stat) defaultConstructor.newInstance();

		} catch (Exception e) { /* test will remain null */ }

		System.out.println();
		// -->Begin Test 2

		// -->Begin Test 2.1
		System.out.println("setData(double[] a) test");

		// Test 2.1.1
		try {
			
			// this line is equivalent to: test.setData(testArrayOne);
			setData.invoke(test, new Object[] { testArrayOne });

			testArrayOne[0] = 0;

			boolean localTestPassed = (test.toString().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.1.2
		try {
			testArrayOne[0] = 0;

			// this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).trim().equals(...);
			boolean localTestPassed = (Arrays.toString((double[]) getData.invoke(test)).trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: "
							+ Arrays.toString((double[]) getData.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0]\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.1.3
		try {
			testArrayOne = null;

			// this line is equivalent to: test.setData(testArrayOne);
			setData.invoke(test, new Object[] { testArrayOne });

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// -->Begin Test 2.2
		System.out.println("\nsetData(float[] a) test");

		// change the Method object setData to use a float [] parameter
		setData = null;
		try {
			setData = Stat.class.getMethod("setData", new Class[] { float[].class });

		} catch (Exception e) {/* setData will remain null */ }

		// Test 2.2.1
		try {
			
			// this line is equivalent to: test.setData(testArrayTwo);
			setData.invoke(test, new Object[] { testArrayTwo });

			boolean localTestPassed = (test.toString().trim().equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {

			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.2.2
		try {
			testArrayTwo[0] = 0;

			// this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).equals(...);
			boolean localTestPassed = (Arrays.toString((double[]) getData.invoke(test)).equals("[5.0, 3.0, 6.0, 10.5, 0.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: "
					+ Arrays.toString((double[]) getData.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [5.0, 3.0, 6.0, 10.5, 0.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.2.3
		try {
			testArrayTwo = null;

			// this line is equivalent to: test.setData(testArrayTwo);
			setData.invoke(test, new Object[] { testArrayTwo });

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		// -->Begin Test 2.3
		System.out.println("setData(int[] a) test");

		// change the Method object setData to use a int [] parameter
		setData = null;
		try {
			setData = Stat.class.getMethod("setData", new Class[] { int[].class });

		} catch (Exception e) { /* setData will remain null */ }

		// Test 2.3.1
		try {
			// this line is equivalent to: test.setData(testArrayThree);
			setData.invoke(test, new Object[] { testArrayThree });

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.3.2
		try {
			testArrayThree[0] = 0;
			
			// this line is equivalent to: test.setData(testArrayThree);
			boolean localTestPassed = (Arrays.toString((double[]) getData.invoke(test)).trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: "
							+ Arrays.toString((double[]) getData.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.3.3
		try {
			testArrayThree = null;

			// this line is equivalent to: test.setData(testArrayThree);
			setData.invoke(test, new Object[] { testArrayThree });

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [] \t\t\t\tfound: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		// -->Begin Test 2.4
		System.out.println("setData(long[] a) test");

		// change the Method object setData to use a long [] parameter
		setData = null;
		try {
			setData = Stat.class.getMethod("setData", new Class[] { long[].class });

		} catch (Exception e) { /* setData will remain null */ }

		// Test 2.4.1
		try {
			
			// this line is equivalent to: test.setData(testArrayFour);
			setData.invoke(test, new Object[] { testArrayFour });

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.4.2
		try {
			testArrayFour[0] = 0;

			// this line is equivalent to: localTestPassed = (Arrays.toString(test.getData())).trim().equals(...);
			boolean localTestPassed = (Arrays.toString((double[]) getData.invoke(test)).trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: "
							+ Arrays.toString((double[]) getData.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1.5;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected: [1.0, 2.0, 3.0, 4.0, 5.0, 100.0] \tfound: " + e.getCause());
			passed = false;
			totalPoints -= 1.5;
		}

		// Test 2.4.3
		try {
			testArrayFour = null;

			// this line is equivalent to: test.setData(testArrayFour);
			setData.invoke(test, new Object[] { testArrayFour });

			boolean localTestPassed = (test.toString().trim().equals("[]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected: [] \t\t\t\tfound: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
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
	 * Test for Lab 13 test 1 NOTE that lab 13 tests are able to receive partial
	 * credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 24
	 */
	private static double test3() {
		/*
		 * NOTE about grading: entire test 3 is worth 24 points 
		 * entire tests 3.x are worth 6 points each, divided as so: 
		 * 3.x.1 are worth 3 points each
		 * 3.x.2 are worth 3 points each
		 */
		double totalPoints = TEST_3_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 3:\n ");

		// create objects to be used in test 3
		Constructor<Stat> doubleConstructor = null;
		Method append = null;
		Stat test = null;

		double[] initialArray = { 1, 2, 3, 4 };
		double[] testArrayOne = { 5, 6, 7, 8, 9 };
		float[] testArrayTwo = { 50, 6, 7, 8, 9 };
		int[] testArrayThree = { 500, 6, 7, 8, 9 };
		long[] testArrayFour = { 5000, 6, 7, 8, 9 };

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[] { double[].class });
		} catch (Exception e) { /* double constructor will remain null */ }

		// the append method will be changed throughout this test in order to
		// test for different input parameters
		// for the first test it will use a double []
		try {
			append = Stat.class.getMethod("append", new Class[] { double[].class });
		} catch (Exception e) { /* append method will remain null */ }

		// --> Begin test 3.1
		System.out.println("\nappend(double[] a) test");

		// Test 3.1.1
		try {
			// this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);

			// this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 3.1.2
		try {
			testArrayOne = null;

			// this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}
		System.out.println();

		// --> Begin Test 3.2
		System.out.println("append(float[] a) test");

		// change append to use a float [] as input parameter
		append = null;
		try {
			append = Stat.class.getMethod("append", new Class[] { float[].class });
		} catch (Exception e) { /* append method will remain null */ }

		// Test 3.2.1
		try {
			// this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);

			// this line is equivalent to: test.append(testArrayTwo);
			append.invoke(test, testArrayTwo);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 3.2.2
		try {
			testArrayTwo = null;

			// this line is equivalent to: test.append(testArrayTwo);
			append.invoke(test, testArrayTwo);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// --> Begin Test 3.3
		System.out.println("\nappend(int[] a) test");

		// change append to use a int [] as input parameter
		append = null;
		try {
			append = Stat.class.getMethod("append", new Class[] { int[].class });
		} catch (Exception e) { /* append method will remain null */ }

		// Test 3.3.1
		try {
			// this line is equivalent to: test = new Stat (initialArray);
			test = doubleConstructor.newInstance(initialArray);

			// this line is equivalent to: test.append(testArrayThree);
			append.invoke(test, testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 3.3.2
		try {
			testArrayThree = null;

			// this line is equivalent to: test.append(testArrayOne);
			append.invoke(test, testArrayThree);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		System.out.println();

		// -->Begin Test 3.4
		System.out.println("append(long[] a) test");

		// change append to use a long [] as input parameter
		append = null;
		try {
			append = Stat.class.getMethod("append", new Class[] { long[].class });
		} catch (Exception e) { /* append method will remain null */ }

		// Test 3.4.1
		try {
			// this line is equivalent to: test = new Stat(initialArray);
			test = doubleConstructor.newInstance(initialArray);

			// this line is equivalent to: test.append(testArrayFour);
			append.invoke(test, testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 3;
		}

		// Test 3.4.2
		try {
			testArrayFour = null;

			// this line is equivalent to: test.append(testArrayFour);
			append.invoke(test, testArrayFour);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t -> expected [1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 3;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
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
	 * Test for Lab 13 test 4 NOTE that lab 13 tests are able to receive partial
	 * credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 3
	 */
	private static double test4() {
		/*
		 * NOTE about grading: entire test 4 is worth 3 points, divided as so:
		 * 4.1 is worth 2 
		 * 4.2 is worth 1
		 */
		double totalPoints = TEST_4_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 4:\n ");

		double[] testArrayOne = { 1, 2, 3, 4 };
		Stat test = null;
		System.out.println("reset() and isEmpty() test");

		// create objects for Test 4
		Constructor<Stat> doubleConstructor = null;
		Method isEmptyMethod = null, resetMethod = null;

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[] { double[].class });
		} catch (Exception e) { /* double constructor will remain null */ }

		try {
			isEmptyMethod = Stat.class.getMethod("isEmpty", (Class<?>[]) null);
		} catch (Exception e) { /* isEmpty will remain null */}

		try {
			resetMethod = Stat.class.getMethod("reset", (Class<?>[]) null);
		} catch (Exception e) {/* reset will remain null */ }

		try {
			// this line is equivalent to: test = new Stat(testArrayOne);
			test = doubleConstructor.newInstance(testArrayOne);

			boolean localTestPassed = (test.toString().trim().equals("[1.0, 2.0, 3.0, 4.0]"));

			System.out.println("Test Passed: " + localTestPassed + "\t-> expected [1.0, 2.0, 3.0, 4.0]\t found: " + test);

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed: " + false + "\t-> expected [1.0, 2.0, 3.0, 4.0]\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		try {
			// this line is equivalent to: test.reset();
			resetMethod.invoke(test);

			// this line is equivalent to: localTestPassed = test.isEmpty();
			boolean localTestPassed = (boolean) isEmptyMethod.invoke(test);

			System.out.println("Test Passed: " + localTestPassed + " \t-> expected true\t\t\t found: "
					+ (boolean) isEmptyMethod.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 1;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
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
	 * Test for Lab 13 test 5 NOTE that lab 13 tests are able to receive partial
	 * credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 12
	 */
	private static double test5() {
		/*
		 * NOTE about grading: entire test 5 is worth 12 points, divided as so:
		 * 5.x are worth 2 points each
		 */
		double totalPoints = TEST_5_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 5: \n");

		// create objects for Test 5
		Constructor<Stat> doubleConstructor = null;
		Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
		Stat test = null;
		double[] data = null;

		System.out.println("stats test (null)");

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[] { double[].class });
		} catch (Exception e) { /* double constructor will remain null */ }

		try {
			min = Stat.class.getMethod("min", (Class<?>[]) null);
		} catch (Exception e) { /* min will remain null */ }

		try {
			max = Stat.class.getMethod("max", (Class<?>[]) null);
		} catch (Exception e) { /* max will remain null */ }

		try {
			average = Stat.class.getMethod("average", (Class<?>[]) null);
		} catch (Exception e) { /* average will remain null */ }

		try {
			mode = Stat.class.getMethod("mode", (Class<?>[]) null);
		} catch (Exception e) { /* mode will remain null */ }

		try {
			variance = Stat.class.getMethod("variance", (Class<?>[]) null);
		} catch (Exception e) { /* variance will remain null */ }

		try {
			stdev = Stat.class.getMethod("standardDeviation", (Class<?>[]) null);
		} catch (Exception e) { /* stdev will remain null */ }

		try {
			// this line is equivalent to: test = new Stat(data);
			test = doubleConstructor.newInstance(data);
		} catch (Exception e) { /* test will remain null */ }

		boolean localTestPassed = false;
		// Test 5.1
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.min());
			localTestPassed = Double.isNaN((double) min.invoke(test));

			System.out.println("Test Passed (min)  : " + localTestPassed + "\t-> expected NaN\t found: " + min.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (min)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 5.2
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.max());
			localTestPassed = Double.isNaN((double) max.invoke(test));

			System.out.println("Test Passed (max)  : " + localTestPassed + "\t-> expected NaN\t found: " + max.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (max)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 5.3
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.average());
			localTestPassed = Double.isNaN((double) average.invoke(test));

			System.out.println(
					"Test Passed (avg)  : " + localTestPassed + "\t-> expected NaN\t found: " + average.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (avg)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 5.4
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.mode());
			localTestPassed = Double.isNaN((double) mode.invoke(test));

			System.out.println("Test Passed (mode) : " + localTestPassed + "\t-> expected NaN\t found: " + mode.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (mode) : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 5.5
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.variance());
			localTestPassed = Double.isNaN((double) variance.invoke(test));

			System.out.println("Test Passed (var)  : " + localTestPassed + "\t-> expected NaN\t found: " + variance.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (var)  : " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 5.6
		try {
			// this line is equivalent to: localTestPassed = Double.isNan(test.standardDeviation());
			localTestPassed = Double.isNaN((double) stdev.invoke(test));

			System.out.println("Test Passed (stdev): " + localTestPassed + "\t-> expected NaN\t found: " + stdev.invoke(test));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (stdev): " + false + "\t-> expected NaN\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		return totalPoints;

	}

	/**
	 * Test for Lab 13 test 6 NOTE that lab 13 tests are able to receive partial
	 * credit, so return is a double
	 * 
	 * @return total number of points earned on this test, out of 12
	 */
	private static double test6() {
		/*
		 * NOTE about grading: entire test 6 is worth 12 points, divided as so:
		 * 6.x are worth 2 points each
		 */
		double totalPoints = TEST_6_MAX_POINTS;
		boolean passed = true;

		System.out.println("\nTest 6:\n ");

		// objects for Test 6
		Constructor<Stat> doubleConstructor = null;
		Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
		Stat test = null;
		double[] data = { -5.3, 2.5, 88.9, 0, 0.0, 28, 16.5, 88.9, 109.5, -90, 88.9, 100.34, 50.01, 50.01, -8 };

		System.out.println("stats test (real data)");

		try {
			doubleConstructor = Stat.class.getConstructor(new Class[] { double[].class });
		} catch (Exception e) { /* double constructor will remain null */ }

		try {
			min = Stat.class.getMethod("min", (Class<?>[]) null);
		} catch (Exception e) { /* min will remain null */ }

		try {
			max = Stat.class.getMethod("max", (Class<?>[]) null);
		} catch (Exception e) { /* max will remain null */ }

		try {
			average = Stat.class.getMethod("average", (Class<?>[]) null);
		} catch (Exception e) { /* average will remain null */ }

		try {
			mode = Stat.class.getMethod("mode", (Class<?>[]) null);
		} catch (Exception e) { /* mode will remain null */ }

		try {
			variance = Stat.class.getMethod("variance", (Class<?>[]) null);
		} catch (Exception e) { /* variance will remain null */ }

		try {
			stdev = Stat.class.getMethod("standardDeviation", (Class<?>[]) null);
		} catch (Exception e) { /* stdev will remain null */ }

		try {
			// this line is equivalent to: test = new Stat(data);
			test = doubleConstructor.newInstance(data);
		} catch (Exception e) { /* test will remain null */ }

		boolean localTestPassed = false;
		// Test 6.1
		try {
			// this line is equivalent to: localTestPassed = test.min() == -90;
			localTestPassed = ((double) min.invoke(test) == -90);

			System.out.println("Test Passed (min)  : " + localTestPassed + "\t-> expected -90\t\t\t found: "
					+ ((double) min.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (min)  : " + false + "\t-> expected -90\t\t\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 6.2
		try {
			// this line is equivalent to: localTestPassed = (test.max() == 109.5);
			localTestPassed = ((double) max.invoke(test) == 109.5);

			System.out.println("Test Passed (max)  : " + localTestPassed + "\t-> expected 109.5\t\t found: "
					+ ((double) max.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (max)  : " + false + "\t-> expected 109.5\t\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 6.3
		try {
			// this line is equivalent to: localTestPassed = (test.average() == 34.684);
			localTestPassed = ((double) average.invoke(test) == 34.684);

			System.out.println("Test Passed (avg)  : " + localTestPassed + "\t-> expected 34.684\t\t found: "
					+ ((double) average.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (min)  : " + false + "\t-> expected 34.684\t\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 6.4
		try {
			// this line is equivalent to: localTestPassed = (test.mode() == 88.9);
			localTestPassed = ((double) mode.invoke(test) == 88.9);

			System.out.println("Test Passed (mode) : " + localTestPassed + "\t-> expected 88.9\t\t found: "
					+ ((double) mode.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (mode) : " + false + "\t-> expected 88.9\t\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 6.5
		try {
			// this line is equivalent to: localTestPassed = (test.variance() == 2798.6591...);
			localTestPassed = ((double) variance.invoke(test) == 2798.6591973333334);
			System.out.println("Test Passed (var)  : " + localTestPassed + "\t-> expected 2798.6591973333334\t found: "
					+ ((double) variance.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (var)  : " + false + "\t-> expected 2798.6591973333334\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		// Test 6.6
		try {
			// this line is equivalent to: localTestPassed = (test.standardDeviation() == 52.9023...);
			localTestPassed = ((double) stdev.invoke(test) == 52.90235530988515);
			System.out.println("Test Passed (stdev): " + localTestPassed + "\t-> expected 52.90235530988515\t found: "
					+ ((double) stdev.invoke(test)));

			// deduct points if incorrect
			if (!localTestPassed)
				totalPoints -= 2;

			passed = passed && localTestPassed;

		} catch (Exception e) {
			// if student debug mode is on, print stack trace and exit
			if (printStackTrace) {
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Test Passed (stdev): " + false + "\t-> expected 52.90235530988515\t found: " + e.getCause());
			passed = false;
			totalPoints -= 2;
		}

		return totalPoints;
	}

	public static void main(String[] args) {
		double t1, t2, t3, t4, t5, t6;
		t1 = t2 = t3 = t4 = t5 = t6 = 0.0;

			t1 = test1();

		System.out.println("--------");

			t2 = test2();

		System.out.println("--------");

			t3 = test3();

		System.out.println("--------");

			t4 = test4();

		System.out.println("--------");

			t5 = test5();

		System.out.println("--------");

			t6 = test6();


		System.out.println("\n--------------------------------------");
		System.out.println("--------------------------------------\n");
		
		//all the output is padded to 4 spaces to help format look better
		if (t1 < TEST_1_MAX_POINTS)
			System.out.printf("[-%4.1f] test 1 \n", (TEST_1_MAX_POINTS - t1));
		
		if (t2 < TEST_2_MAX_POINTS)
			System.out.printf("[-%4.1f] test 2 \n", (TEST_2_MAX_POINTS - t2));
		
		if (t3 < TEST_3_MAX_POINTS)
			System.out.printf("[-%4.1f] test 3 \n", (TEST_3_MAX_POINTS - t3));
		
		if (t4 < TEST_4_MAX_POINTS)
			System.out.printf("[-%4.1f] test 4 \n", (TEST_4_MAX_POINTS - t4));
		
		if (t5 < TEST_5_MAX_POINTS)
			System.out.printf("[-%4.1f] test 5 \n", (TEST_5_MAX_POINTS - t5));
		
		if (t6 < TEST_6_MAX_POINTS)
			System.out.printf("[-%4.1f] test 6 \n", (TEST_6_MAX_POINTS - t6));

		System.out.println("\nGrade for Stat class: " + (t1 + t2 + t3 + t4 + t5 + t6) + "/100");
		System.out.println("ALL TESTS PASSED: " + ((t1 + t2 + t3 + t4 + t5 + t6) == 100));
	}
}
