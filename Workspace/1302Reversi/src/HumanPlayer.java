import java.util.Scanner;

public class HumanPlayer
			extends Player {
	
	private String name;
	
	public HumanPlayer(){
		name = "HumanPlayer";
	}
	
	public HumanPlayer(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	/**
	 * error currently - how to know if human player is white or dark from board
	 * @param board
	 * @throws IndexOutOfBoundsException
	 */
	public void playTurn(ReversiBoard board) throws IndexOutOfBoundsException{
		Scanner in = new Scanner(System.in);
		System.out.println(name + " it's your turn: choose a square to flip");
		int row = in.nextInt();
		int col = in.nextInt();
		
		if (row < 0 || row > 8) throw new IndexOutOfBoundsException("Invalid row: " + row );
		if (col < 0 || col > 8) throw new IndexOutOfBoundsException("Invalid col: " + col );
		
		if (board.isAvailable(row, col)){
			board.getBoard()[row][col] = /*??*/ -50;
		} else{
			System.out.println("That index is unavailable");
		}
	}
}
