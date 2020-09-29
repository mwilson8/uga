/*
* FindTheSums.java
* Author: AJ Tuttle
* Submission Date: 12/01/17
*
* Purpose: Finds the sums in a 2d array
*
* Statement of Academic Honesty:
*
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
* of Computer Science at the University of Georgia. 
*/

public class FindTheSums {

	public static String arrayToString(int[][] a) {
		String temp = "";
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				temp += a[x][y];
				if (y != a[0].length-1) {
					temp += " ";
				}
				else if (x != a.length-1) {
					temp += "\n";
				}
			} 
		}
		return temp;
	}
	
	public static int[][] verticalSums(int[][] a, int sumToFind){
		int[][] b = new int[a.length][a[0].length];
		
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				b[x][y] = 0;
			}
		}
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				int value = 0;
				int position = x;
				int counter = 0;
				while (value < sumToFind && position < a.length) {
					value += a[position][y];
					position++;
					counter++;
				}
				if (value == sumToFind) {
					for (int m = 0; m < counter; m++) {
						b[x+m][y] = a[x+m][y];
					}
				}
				
			}
		}
		return b;
	}
	
	public static int[][] horizontalSums(int[][] a, int sumToFind){
		int[][] b = new int[a.length][a[0].length];
		
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				b[x][y] = 0;
			}
		}
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				int value = 0;
				int position = y;
				int counter = 0;
				while (value < sumToFind && position < a[0].length) {
					value += a[x][position];
					position++;
					counter++;
				}
				if (value == sumToFind) {
					for (int m = 0; m < counter; m++) {
						b[x][y+m] = a[x][y+m];
					}
				}
				
			}
		}
		return b;
	}
	
	
}
