
public class ReversiBoard 
			extends Board {

	
	private int [][] board;
	private Player whitePlayer;
	private Player blackPlayer;
	
	//constants for the board display
	public static final int WHITE = 1;
	public static final int BLACK = -1;
	public static final int EMPTY = 0;
	
	public ReversiBoard(){
		board = new int [8][8];
		initialize();
	}
	
	public ReversiBoard(Player whitePlayer, Player blackPlayer){
		this();
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
	}
	
	
	
	
	
	
	
	/**
	 * initialize the board so it's ready to play
	 */
	private void initialize(){
		//fill the board with empty spaces
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = EMPTY; 
			}
		}
		
		board[3][3] = board[4][4] = BLACK;
		board[3][4] = board[4][3] = WHITE;
	}//initialize
	
	public String toString(){
		String s = "";
		
		s = "  1 2 3 4 5 6 7 8\n";
		for (int i = 0; i < 8; i++){
			s += (i + 1) + " ";
			for (int j = 0; j < 8; j++){
				
				switch(board[i][j]){
				
				//black
				case -1 : s+= "X "; break;
				
				//empty
				case 0  : s+= ". "; break;
				
				//white
				case 1  : s+= "O "; break;
				}
			}
			s+="\n";
			s.trim();
		}
		
		return s;
	}//toString
	
	public boolean isAvailable(int row, int col){
		return board[row][col] == EMPTY;
	}
	
	
	public void printBoard(Player player){
		
		if (player.equals(this.blackPlayer)){
			
			
			
			
		} else if (player.equals(this.whitePlayer)){
			
		} else {
			throw new IllegalArgumentException("Player invalid");
		}
	}
	
	protected int[][] getBoard(){
		return board;
	}
}
