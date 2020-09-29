package test2;

import java.lang.reflect.Array;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println("\n--------\nIn main, create A");
		A a1 = new A();
		
		
		System.out.println("\n--------\nIn main, create B (type B)");
		B b1 = new B();
		
		
		System.out.println("\n--------\nIn main, create B (type B, super called)");
		B b2 = new B(1);
		
		
		System.out.println("\n--------\nIn main, create B (type A)");
		A b3 = new B();
		
		System.out.println("\n--------\nIn main, create B (type A, super called)");
		A b4 = new B(1);
		
		

		String y = "Yes";
		String n = "No";
		
		System.out.println(y.compareTo(n));
		
		
		
		int [] askdjfhd = new int [5];
		Array.getShort(askdjfhd, 4);
	}

}
