package quizzes;

import java.util.Arrays;

public class Quiz7 {

	public static void main (String[] args){
		int[][] a ={
				{1,1,8},
				{7,4,2},
				{6,2,3},
				{4,6,1}
		};
		
		int[][] testArray = new int[3][4];
		
		for(int row = 0; row < testArray.length; row++){
			for(int col = 0; col<testArray[row].length; col++){
				testArray[row][col] = col;
			}
		}
		
		System.out.println("1--- " + testArray[2][3]);
		
		System.out.println("2--- " + testArray[2][1]);
		
		System.out.println("3--- " + a.length);
		
		int[] c = a[2];
		System.out.println("4--- " + c[2]);
		
		int x = 0;
		for(int i = 0; i<=3; i++){
			x+= a[i][0];
		}
		
		System.out.println("5--- " + x);
		
		System.out.println("testArray");
		for (int i =0; i<testArray.length; i++){
			for(int j=0; j<testArray[0].length; j++)
				System.out.print(testArray[i][j] + " ");
			System.out.println();
		}
	}

}
