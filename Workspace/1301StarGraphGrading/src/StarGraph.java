import java. util.Scanner;
public class StarGraph {

	public static void main(String[] args) {
		
	System.out.print("Please Enter the number of x values to process:");	
	Scanner keyboard = new Scanner (System.in);
	double numberofvalues = keyboard.nextDouble();
	System.out.print("Enter the minimum value for x: ");
	double minimumvalue = keyboard.nextDouble();
	System.out.print("Enter the amount to incerement : ");
	double incrementamount = keyboard.nextDouble();
	double sum ;
	double[] ArrayofX= new double[(int) numberofvalues+1];
	double [] ArrayofY= new double[(int) numberofvalues];
	String String = "";
	
	if ( numberofvalues <=0 || minimumvalue<0)
	{
		System.out.println("The number of values must be greater than O");
		System.exit(0);
	}
	else 
		
		for (int  i =0; i <(numberofvalues); i++){
			ArrayofY[i]= (20.0*Math.abs(Math.sin(ArrayofX[i])));
			if (i >=1) {
				ArrayofX[i]= ArrayofX[i-1] + incrementamount;
				ArrayofY[i]= (20.0*Math.abs(Math.sin(ArrayofX[i])));
			}
			System.out.println(String);
			System.out.printf("x:%3.3f",ArrayofX[i]);
			System.out.printf(" y:%3.3f",ArrayofY[i]);
	
		}
	System.out.println(String);
	System.out.println("Graph");
	for (int  i =0; i <(numberofvalues); i++) {
		ArrayofY[i]= (20.0*Math.abs(Math.sin(ArrayofX[i])));
		int Asterix=((int)ArrayofY[i]);
		System.out.print(":");
		for(int  x =0; x <(Asterix); x++) 
			System.out.print("*");
			System.out.println(String);
			
			
		
			
		
	}

	}

}
