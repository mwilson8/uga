
public class Riddler {

	public static void main(String[] args) {
		
		for (int i = 1000; i < 10000; i++){
			int one = i % 10;
			int two = i % 100;
			int three = i % 1000;
			int four = i % 10000;
			
			if (one == two || one == three || one == four 
					|| two == three || two == four
					|| three == four)
				continue;
		}

	}

}
