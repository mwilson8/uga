package quizzes;

public class Quiz3 {
	 
	public static void main (String[] args){
		
		int example1 = 21 % 6;
		example1 *= 4;
		System.out.println(example1); 		//1._______________
		
		boolean example2;
		example2 = (16 % 4) > 0;
		System.out.println(example2);		//2._______________
		
		String s = "Vince is my dog";
		
		String t = s.substring(9);
		System.out.println(t);			//3._______________
		
		s = s.toUpperCase();
		System.out.println(s);			//4._______________
		
		int example4 = 2 / 3;
		int example5 = 6 / 3;
		
		if (example4 <= 0) {
			if (example5 != 2) {
				example4 = (int)10.0;
			}
			else{
				example4 = (int)5.0;
			}
		}
		else {
			example4 = (int)50.5;
		}
		System.out.println(example4);		//5._______________
	}
}
