package test2;

public class B extends A{

	public B() {
		System.out.println("In B: Creating B, not explicity calling super");
	}
	
	public B(int i){
		super();
		System.out.println("In B: Creating B and calling super");
	}
	
	public void method(){
		System.out.println("This is the B method");
	}

}
