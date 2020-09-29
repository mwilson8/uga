import static java.lang.System.out;

import java.util.Arrays;
public class Main {

	public static void main(String[] args) {
		
		DatabaseAccess.initialize("jdbc:mysql://localhost:3306/students_no_key?autoReconnect=true&useSSL=false");
		DatabaseAccess.initialize("jdbc:mysql://localhost:3306/students_with_keys?autoReconnect=true&useSSL=false");


	}

}
