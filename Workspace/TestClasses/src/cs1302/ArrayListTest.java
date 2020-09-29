package cs1302;

import java.util.Arrays;

public class ArrayListTest {

	
	public static void main (String[]args){
		int[] x = new int[]{1,2,3,4,5};
		int index = 2;
		int[] y = new int[10];
		
		System.out.println(Arrays.toString(x));
		System.out.println(Arrays.toString(y));
		
		//System.arraycopy(x, index, y, index, x.length-index);
		System.arraycopy( x, index, x, index + 1, 5 - index);
		System.out.println(Arrays.toString(y));
	}
}
