import java.util.Random;

public class CoinFlip {

	public static void main (String[] args){
		final int classSize = 47;
		
		Random r = new Random();
		java.util.Scanner in = new java.util.Scanner(System.in);
		
		for(;;){
			in.nextLine();
			System.out.print("->" + (r.nextInt(classSize) + 1));
			
		}
	}
}
