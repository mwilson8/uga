
public class Rounding {

	public static void main (String[] args){
		double price = 7.00 + (5 * 0.50);
		double round = 0.01 * Math.ceil(price);
		
		System.out.println(price);
		System.out.println(Math.ceil(price));
		System.out.println(round);
	}
}
