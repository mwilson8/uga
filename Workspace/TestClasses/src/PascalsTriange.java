import java.util.Arrays;

public class PascalsTriange {

	public static void main(String[] args) {
		
		int[][] a = new int [9][];
		
		for (int i = 0; i < a.length; i++){
			a[i] = new int [i+1];
			for (int j = 0; j < a[i].length; j++){
				if (j == 0 || j == a[i].length - 1)
					a[i][j] = 1;
				else
					a[i][j] = a[i-1][j] + a[i-1][j-1];
			}//for
			
		}//for

		//print triangle
		//largest number (largest number is in the middle of the last row)
		int biggestNum = a[ a.length - 1 ] [ (int)Math.ceil( a[ a.length - 1 ].length / 2 )]; 
		int width = a[a.length-1].length+1; //easier var name; # of values in last row
		int numDigitsBiggestNum = Integer.toString(biggestNum).length();
		
		for (int i = 0; i < a.length; i++)
			System.out.println(Arrays.toString(a[i]));
		
		System.out.println(biggestNum + " " + width + " " + numDigitsBiggestNum);
		int numChars = 0;
		for (int i = 0; i < a[a.length - 1].length; i++)
			numChars += Integer.toString(a[a.length - 1][i]).length();
		
		char[][] c = new char[a.length][numChars];
		
		for (int i = 0; i < a.length; i ++){
			for (int j = 0, l = 0; j < a[i].length && l < c[i].length; j++, l++){
				for (int k = 0; k < Integer.toString(a[i][j]).length(); k++){
					if (Character.isDigit(Integer.toString(a[i][j]).charAt(k))){
						c[i][l] = Integer.toString(a[i][j]).charAt(k);
							l ++;
					}
				}
			}
			
		}
		
		for (int i = 0; i < c.length; i++)
			System.out.println(Arrays.toString(c[i]));
		
	}

}
