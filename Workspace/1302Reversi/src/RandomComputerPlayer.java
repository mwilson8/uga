
public class RandomComputerPlayer 
			extends ComputerPlayer {

	private String name;
	
	public RandomComputerPlayer(){
		name = "RandomComputerPlayer";
	}
	
	public RandomComputerPlayer(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
