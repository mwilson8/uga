package quizzes;
public class Quiz4 {

	public static void main(String[ ] args) {
		
		int i, j, x, y;
		j = 0; x = 0; y = 0;
		i = 14;
		
		while (i >= 5 && i <= 14){
			if (i <= 6)
				i -= 3;
			if (i < 3)
				x = 5;
			i -= 2;
			
		}
		System.out.println(i);				//1._______________
		System.out.println(x);				//2._______________

		
		String s = "";
		char myChar = 'a';
		for (i = 0; i < 2; i++){
			j = 0;
			do {
				s += myChar;
				j++;
			} while (j < 3);
		
			if (i ==0)
				myChar = 'b';
			else if (i == 1)
				myChar = 'c';
			else
				myChar = 'd';
		}
		System.out.println(myChar);				//3._______________
		System.out.println(j);				//4._______________
		System.out.println(s);				//5._______________
	}

}
