
public class FootballFan {
	
	private String name;
	private int numDrinksHad;
	
	private static int numDrinksInCooler = 10;
	
	public FootballFan(String newName){
		name = newName;
	}
	
	public void haveADrink(){
		numDrinksHad ++;
		numDrinksInCooler --;
	}
	
	public int getNumDrinksHad(){
		return numDrinksHad;
	}
	
	public static int getNumDrinksInCooler(){
		return numDrinksInCooler;
	}
	
	
}//end FootballFan class


