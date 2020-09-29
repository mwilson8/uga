package quizzes;

public class Quiz5 {

	public String name = "John";
	
	public void setNameToBob(){
		this.name = "Bob";
	}
	
	public void setNameToSam(){
		String name = "Sam";
	}
	
	public void setNameToTom(){
		String name = "Bill";
		this.name = "George";
	}
	
	/**
	 * main
	 */
	public static void main (String [ ] args){
	
	Quiz5 person1 = new Quiz5();
	Quiz5 person2 = new Quiz5();
	person1.setNameToBob();
	System.out.println(person1.name); 				//1.___________
	person1.setNameToSam();
	System.out.println(person1.name); 				//2.___________
	person1.setNameToTom();
	System.out.println(person1.name); 				//3.___________
	person2.setNameToSam();
	System.out.println(person2.name); 				//4.___________
	
	Quiz5 person3 = new Quiz5();
	Quiz5 person4 = new Quiz5();
	person3.setNameToBob();
	person4.setNameToBob();
	System.out.println(person3 == person4); 			//5.___________
	
	}
	
}
