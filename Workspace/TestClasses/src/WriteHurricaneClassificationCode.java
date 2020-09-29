
public class WriteHurricaneClassificationCode {

	public static void main(String[] args) {
		System.out.println("String s = \"\";");
		System.out.println("int windSpeed;");
		System.out.println("switch(windSpeed){");
		
		for (int i = 0; i <= 155; i++){
				System.out.print("case " + i + ": ");
		if (i == 38) System.out.println("s = \"Tropical Depression\" break;");	
		if (i == 73) System.out.println("s = \"Tropical Storm\" break;");
		if (i == 98) System.out.println("s = \"Category 1 Hurricane\" break;");
		if (i == 110) System.out.println("s = \"Category 2 Hurricane\" break;");
		if (i == 130) System.out.println("s = \"Category 3 Hurricane\" break;");
		if (i == 155) System.out.println("s = \"Category 4 Hurricane\" break;");
		if (i % 10 == 0) System.out.println();
		}
		System.out.println("default: s = \"Category 5 Hurricane\"");
		System.out.println("}");
	}

}
