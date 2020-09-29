import java.util.Scanner;
public class Player {

	private String name;
	private int fastestWin;
	private int gamesCompleted;
	
	Scanner keyboard = new Scanner(System.in);
	
	public String askForGuess(){
		String guess = keyboard.next();
		Integer.parseInt(guess);
		return guess;
	}
	
	public String getName(){
		return name;
	}
	
	public int getFastestWin(){
		return fastestWin;
	}
	
	public int getGamesCompeted(){
		return gamesCompleted;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void updateStats(int numberOfMovesTakenToWin){
		gamesCompleted++;
		if(numberOfMovesTakenToWin < this.getFastestWin()){
			fastestWin = numberOfMovesTakenToWin;
		}
		
	}
}
