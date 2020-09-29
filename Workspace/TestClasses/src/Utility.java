
public abstract class Utility {

	static int length (String s){
		int length = 0;
		
		for (int i = 0; i < s.length(); i ++)
			length ++;
		
		return length;
	}
	
	/**
	 * Only needs to work for integers [0-9]
	 * @param ch
	 * @return
	 */
	static int getNumericValue(char ch){
		
		return (int)ch - 48;
		
	}
	
	static boolean isDigit(char ch){
		
		return (int)ch <= 57 && (int)ch >= 48;
		
	}
	
	static String trim(String s){
		
		String newString = "";
		
		for (int i = 0; i < s.length(); i++){
			if (s.charAt(i) != ' '){
				newString += s.charAt(i);
			}
		}
		
		return newString;
		
	}
	
	static String substring(String s, int start, int finish){
		
		String newString = "";
		
		for (int i = 0; i < s.length(); i++){
			if (i >= start && i <= finish){
				newString += s.charAt(i);
			}
		}
		
		return newString;
	}
	
	static String substring(String s, int start){
		
		return substring(s, start, s.length());
	}
	
	static double pow(double base, double exponent){
		double result = base;
		
		for (int i = 0; i < exponent; i++){
			result *= base;
		}
		
		return result;
	}
	
	static boolean equals(String s1, String s2){
		if (s1.length() != s2.length())
			return false;
		
		for (int i = 0; i < s1.length(); i ++){
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		}
		
		return true;
		
	}
	
	static String toLowerCase(String s){
		String newString = "";
		
		for (int i = 0; i < s.length(); i ++){
			if ((int)s.charAt(i) >= 97 && (int)s.charAt(i) <= 122){
				//lowercase letter
				newString += s.charAt(i);
			} else {
				//uppercase letter
				newString += s.charAt(i) - 28;
			}
			
		}
		
		return newString;
		
	}
	
	static boolean equalsIgnoreCase(String s1, String s2){
		if (s1.length() != s2.length())
			return false;
		
		for (int i = 0; i < s1.length(); i ++){
			if (s1.charAt(i) != s2.charAt(i) 
					&& s1.charAt(i) != (s2.charAt(i) + 28)
					&& s1.charAt(i) + 28 != s2.charAt(i))
				return false;
			
		}
		
		return true;
	}
	
	public static void main (String [] args){
		
		System.out.println(equalsIgnoreCase("hello", "HeLLo"));
	}
	
}
