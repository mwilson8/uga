package quizzes;

public class Quiz6 {

	private static int c = 0;
	private String name;
	private int id;
	
	public Quiz6(String name, int id) {
		if ( name != null ) { 
			this.name = name;
		}
		if ( id <= 0 ) {
			this.id = c;
		}
		else {
			this.id = id;
		}
		c++;
	}
	
	public Quiz6( ) {
		this("Yoshi", c);
	}
	
	public String f( ) {
		return name;
	}
	
	public int g( ) {
		return id;
	}
	
	public static int c( ) {
		return c;
	}
	
	public static void main (String [ ] args) {
		Quiz6 q1 = new Quiz6 ("Super ", 5);
		Quiz6 q2 = new Quiz6 ( );
		Quiz6 q3 = new Quiz6 (q1.f( ) + "Mario", q1.g( ) - 10 );
		
		System.out.println( q1.f( ) );				//1.____________
		System.out.println( q2.f( ) );				//2.____________
		System.out.println( q3.f( ) );				//3.____________
		
		int x = q1.g( ) * q2.g( );
		System.out.println( x );				//4.____________
		
		System.out.println( Quiz6.c( ) );			//5.____________
		
	}
}
