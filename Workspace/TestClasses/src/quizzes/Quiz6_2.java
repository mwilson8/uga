package quizzes;

public class Quiz6_2 {

	private static int c = 1;
	private String name = "Yoshi";
	private int id;
	
	public Quiz6_2(String name, int id){
		if (name != null){
			this.name = name;
		}
		if (id <= 0 ){
			this.id = c;
		}
		else {
			this.id = id;
		}
		c++;
	}
	public Quiz6_2(){
		this("Bowser", 7);
	}
	public String f(){
		return this.name;
	}
	public int g(){
		return id;
	}
	public static int c(){
		return c;
	}
	public static void main(String[] args) {
		Quiz6_2 q1 = new Quiz6_2("Super ", 5);
		Quiz6_2 q2 = new Quiz6_2();
		Quiz6_2 q3 = new Quiz6_2(q1.f() + "Mario", q1.g() - 20);
		
		System.out.println( q1.f() );		//1._______
		System.out.println( q2.f() );		//2._______
		System.out.println( q3.f() );		//3._______
		
		int x = q1.g() + q2.g();
		System.out.println( x );			//4._______
		System.out.println( Quiz6_2.c );	//5._______

	}

}
