
public class inputError {

	public static void main (String[] args){

		/*
		 * trace this code: what is the output? 
		 */
		
		int x = 10, y = 1, z = 3;
		boolean a, b, c = false, d = false;
		a = b = true;
			
		
		if (a == (x % z == y) ? b : c){
			
			/* remove this comment
			 * tricky here, a = c = false doesn't execute but the rest does
			 * it's improperly tabbed over to maybe trick some people 
			 */
			if (!a) 
				a = c = false; 
				if (a | b & c ^ d) {
					x = 1234;
					x %= 10;
					a = true;
					//this executes

				} else if (a){ 
					y = 200;
					y /= 5;
					b = !!a;

				} else if (b){
					z = 50;
					z %= x;
					z ++;
					c = false; 
					d = !c;

				} else if (c){
					y ++;
					x --;
					z = y * x;
					
				} else if (d) {
					x = y = z = 0;

				}
				
			//x = 4
			//y = 1
			//z = 3
		} else System.out.println("Too easy"); 
		
		if (a && x - y == z){

			/* remove this comment
			 * this is no "break" in switch, so output is "Hello and congratulations!"
			 */
			switch (z){
			case 5: System.out.println("Java ");
			case 4: System.out.println("programmers, ");
			case 3: System.out.println("Hello ");
			case 2: System.out.println("and ");
			case 1: System.out.println("congratulations! ");
			
			} //switch
			
		} else {
			switch (x - z){
			case 5: System.out.println("Finally, ");
			case 4: System.out.println("we ");
			case 3: System.out.println("finished ");
			case 2: System.out.println("and ");
			case 1: System.out.println("we ");
			case 0: System.out.println("are ");
			default: System.out.println("happy! ");
		}
		
	}

  }
}
