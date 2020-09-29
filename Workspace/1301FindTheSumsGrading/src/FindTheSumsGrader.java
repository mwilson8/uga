
/*
 * This class contains methods
 * to test a student's submission
 * for FindTheSums
 * 
 * author Sal LaMarca
 * CSCI 1301
 *
 */
public class FindTheSumsGrader {

	//Example array used for grading
	private static int[][] array1 = { 
		{3, 2, 1, 1},
		{2, 5, 6, 2},
		{1, 2, 9, 8}
	};

	//Example array used for grading
	private static int[][] array2 = {
		{4, 8, 4, 5, 1},
		{5, 4, 5, 2, 8},
		{4, 8, 3, 5, 1},
		{5, 6, 6, 4, 5},
		{1, 2, 4, 9, 2},
		{9, 7, 4, 3, 1}
	};

	//Points scored
	private static int pointsScored = 0;
	
	private static String results = "";

	//Correct answer for test 3
	private static int[][] test3Answer = {
		{0, 0, 0, 0},
		{0, 5, 6, 0},
		{0, 2, 9, 0}
	};

	//Correct answer for test 4
	private static int[][] test4Answer = {
		{0, 8, 4, 5, 1},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{1, 2, 4, 9, 2},
		{0, 0, 0, 0, 0}
	};

	//Correct answer for test 5
	private static int[][] test5Answer = {
		{0, 2, 1, 0},
		{0, 5, 6, 0},
		{0, 2, 0, 0}
	};

	//Correct answer for test 6
	private static int[][] test6Answer = {
		{0, 0, 0, 0, 1},
		{5, 0, 0, 0, 8},
		{4, 0, 0, 0, 1},
		{5, 6, 0, 0, 5},
		{1, 2, 0, 0, 0},
		{9, 7, 0, 0, 0}
	};

	/*
	 * Run the test cases for grading a student submission for FindTheSums
	 * param args - not used
	 */
	public static void main(String[] args){
		arrayToStringGrader();
		horizontalSumsGrader();
		verticalSumsGrader();
		System.out.println("Total:\t\t" + pointsScored + " / 100 points");
		System.out.println(results);
	}

	/*
	 * Test and grade the arrayToString method
	 */
	public static void arrayToStringGrader(){
		String methodCallResult, correctAnswer;

		//Test 1
		methodCallResult = FindTheSums.arrayToString(array1);
		correctAnswer = "3 2 1 1\n2 5 6 2\n1 2 9 8";
		printTestResults(1, methodCallResult, correctAnswer);

		//Test 2
		methodCallResult = FindTheSums.arrayToString(array2);
		correctAnswer = "4 8 4 5 1\n5 4 5 2 8\n4 8 3 5 1\n5 6 6 4 5\n1 2 4 9 2\n9 7 4 3 1";
		printTestResults(2, methodCallResult, correctAnswer);
	}

	/*
	 * Test and grade the horizontalSums method
	 */
	public static void horizontalSumsGrader(){
		int[][] horizontalSums;

		//Test 3
		horizontalSums = FindTheSums.horizontalSums(array1, 11);
		printTestResults(3, horizontalSums, test3Answer);

		//Test 4
		horizontalSums = FindTheSums.horizontalSums(array2, 18);
		printTestResults(4, horizontalSums, test4Answer);
	}

	/*
	 * Test and grade the verticalSums method
	 */
	public static void verticalSumsGrader(){
		int[][] verticalSums;

		//Test 5
		verticalSums = FindTheSums.verticalSums(array1, 7);
		printTestResults(5, verticalSums, test5Answer);

		//Test 6
		verticalSums = FindTheSums.verticalSums(array2, 15);
		printTestResults(6, verticalSums, test6Answer);
	}

	/*
	 * Print results for tests cases that output Strings representation of 2D arrays
	 * 
	 * param testNum - the test case's number
	 * param methodCallResult - the String result from the student's method call
	 * param correctAnswer - the correct answer to the test case
	 */
	public static void printTestResults(int testNum, String methodCallResult, String correctAnswer){
		System.out.println("Test" + testNum + ":");
		System.out.println("Your method's answer:");
		System.out.println(methodCallResult);
		System.out.println("Correct answer:");
		System.out.println(correctAnswer);
		if(correctAnswer.equals(methodCallResult)){
			System.out.println("Test" + testNum + " passed:\t10 / 10 points");
			pointsScored += 10;
		}
		else{
			System.err.println("Test" + testNum + " failed:\t0 / 10 points");
			results += "-10 test " + testNum + "\n";
		}
		System.out.println();
	}

	/*
	 * Print results for tests cases that output 2D arrays
	 * 
	 * param testNum - the test case's number
	 * param methodCallResult - the 2D array result from the student's method call
	 * param correctAnswer - the correct answer to the test case
	 */
	public static void printTestResults(int testNum, int[][] methodCallResult, int[][] correctAnswer){
		System.out.println("Test" + testNum + ":");
		System.out.println("Your method's answer:");
		System.out.println(arrayToString(methodCallResult));
		System.out.println("Correct answer:");
		System.out.println(arrayToString(correctAnswer));
		if(equals(methodCallResult, correctAnswer)){
			System.out.println("Test" + testNum + " passed:\t20 / 20 points");
			pointsScored += 20;
		}
		else{
			System.err.println("Test" + testNum + " failed:\t0 / 20 points");
			results += "-20 test " + testNum + "\n";
		}
		System.out.println();
	}


	/* 
	 * Return the number of elements in the 2D array a.
	 * This method works on jagged/ragged arrays.
	 * If a is null, this method returns zero.
	 * 
	 * param - a the input 2D array
	 * return - the number of elements in a
	 */
	public static int elementsInArray(int[][] a){
		int result = 0;
		if(a != null){
			for(int i = 0; i < a.length; i++){
				for(int j = 0; j < a[i].length; j++){
					result++;
				}
			}
		}
		return result;
	}

	/*
	 * Return true if a and b are equal, false otherwise.
	 * a and b are equal if they are both null or
	 * they contain the same number of elements and
	 * a[i][j] == b[i][j] for all valid indexes i and j.
	 * 
	 * param a - an input 2D array
	 * param b - an input 2D array
	 * return - true if a and b are equal, false otherwise.
	 */
	public static boolean equals(int[][] a, int[][] b){
		boolean result = true;
		if(a == null && b == null){
			result = true;
		}
		else if(elementsInArray(a) != elementsInArray(b)){
			result = false;
		}
		else{
			for(int i = 0; i < a.length; i++){
				for(int j = 0; j < a[i].length; j++){
					if(a[i][j] != b[i][j]){
						result = false;
					}
				}
			}
		}
		return result;
	}

	/*
	 * Return a String representation of a 2D array
	 * 
	 * param a the input 2D
	 * return - a String representation of a
	 */
	public static String arrayToString(int[][] a){
		String result = "";
		if(a != null){
			for(int i = 0; i < a.length; i++){
				for(int j = 0; j < a[i].length; j++){
					if(j < a[i].length - 1){
						result += a[i][j] + " ";
					}
					else{
						result += a[i][j];
					}
				}
				if(i < a.length - 1){
					result += "\n";
				}
			}
		}
		return result;
	}



}
