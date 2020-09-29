
import java.util.Arrays;

public class FindTheSums {

	
	public static String arrayToString(int[][] arr){
		String s = "";
		for (int i = 0; i < arr.length; i++){
			for (int j = 0; j < arr[i].length; j++){
				s += arr[i][j];
				if (j != arr[i].length - 1) 
					s += " ";
			}
			//the tester gets mad if there's an extra new line on the end
			if (i != arr.length - 1)
				s += "\n";
		}
		
		return s;
	}

	
	public static int[][] horizontalSums(int[][] arr, int sumToFind){
		int[][] result = new int[arr.length][];							//2D array to return
		for(int i = 0; i < arr.length; i++){
			
			result[i] = new int[arr[i].length];							//to handle jagged arrays
			
			for (int j = 0; j < arr[i].length; j++){
				
				for(int k = j; k < arr[i].length; k++){					//another loop to search ahead for the sumToFind
					int sum = 0;
					sum += arr[i][j];
					
					
					if (sum > sumToFind) 								//if we've gone over the sum to find
						continue;						
					
					if (sum == sumToFind){								//we've found the sum we are looking for
						
						for (int l = j; l <= k; l++)					//another loop to flip the values starting at j ending at k
							result[i][l] = arr[i][l];
					}
				}//k loop
				
			}//j loop
		}//i loop
		return result;
	}
	
	public static int[][] verticalSums(int[][] arr, int sum){
		int[][] result = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) 							//because we are looping vertically we need to completely fill out result first
			result[i] = new int[arr[i].length];	
		return null;
		//TODO how to handle a jagged array when looping vertically?
	}
	
}
