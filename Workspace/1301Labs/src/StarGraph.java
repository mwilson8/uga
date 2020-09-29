
import java.util.Scanner;
public class StarGraph {

	public static void main (String [] args){
		Scanner in = new Scanner(System.in);
		double[] y;
		
		System.out.println("Enter number of values: ");
		int n = in.nextInt();
		
		if (n <= 0){ 
			System.out.println("Invalid"); 
			return;
		}
		
		y = new double[n];
		
		System.out.println("Enter minimum value: ");
		double min = in.nextDouble();
		
		System.out.println("Enter increment: ");
		double inc = in.nextDouble();
		
		System.out.println("Values");
		for (int i = 0; i < n; i ++, min += inc){
			y[i] = 20.0 * Math.abs(Math.sin(min)); 
			System.out.printf("x: %.3f\ty: %.3f\n", min, y[i]);
		}
		
		System.out.println("Graph");
		for (int i = 0; i < n; i ++){
			System.out.print(": ");
			for (int j = 0; j < (int)y[i]; j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
