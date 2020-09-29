
//package cs1302.p1;

import java.io.File;
import java.util.Scanner;
import java.io.*; //all exceptions

/**
 * 
 * @author MitchWilson
 *
 */
public class Minesweeper {

	
	private int rows, cols, numMines, rounds = 0;
	private boolean [][] mineField;
	private int [][] gameBoard;
	/*
	 * key for minefield:
	 * -3 empty
	 * -2 mine
	 * -1 flagged
	 * >=0 successfully turned over, displays # neighboring mines
	 */
	
	
    /**
     * Constructs an object instance of the {@link Minesweeper} class using the
     * information provided in <code>seedFile</code>. Documentation about the 
     * format of seed files can be found in the project's <code>README.md</code>
     * file.
     *
     * @param seedFile the seed file used to construct the game
     * @see            <a href="https://github.com/mepcotterell-cs1302/cs1302-minesweeper-alpha/blob/master/README.md#seed-files">README.md#seed-files</a>
     */
    public Minesweeper(File seedFile) {

    	try{
    		Scanner in = new Scanner(seedFile);
    		
    		/*
    		 * file should contain args in format
    		 * int, int, \n <-- rows, cols
    		 * int, \n <-- numMines
    		 * int, int \n <-- location of mines continues (rows, cols)
    		 * ...
    		 * ...
    		 */
    		
    		rows = in.nextInt();
    		cols = in.nextInt();
    		numMines = in.nextInt();
    		
    		//check values 
    		mineField = new boolean [rows][cols];
    		gameBoard = new int [rows][cols];
    		
    		for (int i =0; i < rows; i ++){
    			for (int j = 0; j < cols; j++){
    				gameBoard[i][j] = -3;
    			}
    		}
    		
    		System.out.println(rows + " " + cols + " " + numMines);
    		
    		/*
    		 * key for minefield:
    		 * -3 empty
    		 * -2 mine
    		 * -1 flagged
    		 * >=0 successfully turned over, displays # neighboring mines
    		 */
    		while (in.hasNextInt()){
    			mineField[in.nextInt()][in.nextInt()] = true;
    			//System.out.println("mine at " + in.nextInt() + in.nextInt());
    		}
    		
    		System.out.println("minefield created");
    		in.close();
    		
    	}catch (FileNotFoundException fnfe){
    		System.err.println("Seedfile not found");
    		fnfe.printStackTrace(System.err);
    		System.exit(-1);
    	}catch (Exception e){
    		System.err.println("Error creating game");
    		e.printStackTrace(System.err);
    		System.exit(-1);
    	}
    	
    } // Minesweeper


    /**
     * Constructs an object instance of the {@link Minesweeper} class using the
     * <code>rows</code> and <code>cols</code> values as the game grid's number
     * of rows and columns respectively. Additionally, One quarter (rounded up) 
     * of the squares in the grid will will be assigned mines, randomly.
     *
     * @param rows the number of rows in the game grid
     * @param cols the number of cols in the game grid
     */
    public Minesweeper(int rows, int cols) {

	this.rows = rows;
	this.cols = cols;
	mineField = new boolean [rows][cols];
	gameBoard = new int [rows][cols];
	
	
    } // Minesweeper
    

    /**
     * Starts the game and execute the game loop.
     */
    public void run() {
    	/*
		 * key for minefield:
		 * -3 empty
		 * -2 mine
		 * -1 flagged
		 * >=0 successfully turned over, displays # neighboring mines
		 */
    	printPicture();
		int row = 0, col = 0;
    	while(true){
	    	printField();
			String[] command = getCommand();
	
			switch(command[0]){
			case "mark" : 
				row = Integer.parseInt(command[1]);
				col = Integer.parseInt(command[2]);
				System.out.println("mark " + row + " " + col);
				if (row >= rows || col >= cols || row < 0 || cols < 0){
					System.out.println("invalid");
					break;
				} 
				gameBoard[row][col] = -2;
				break;
				
			case "guess" : 
				row = Integer.parseInt(command[1]);
				col = Integer.parseInt(command[2]);
				System.out.println("guess " + row + " " + col);
				if (row >= rows || col >= cols || row < 0 || cols < 0){
					System.out.println("invalid");
					break;
				}
				gameBoard[row][col] = -1;
				break;
				
			case "reveal" : 
				row = Integer.parseInt(command[1]);
				col = Integer.parseInt(command[2]);
				System.out.println("reveal " + row + " " + col);
				if (row >= rows || col >= cols || row < 0 || cols < 0){
					System.out.println("invalid");
					break;
				}
				if (mineField[row][col]){
					System.out.println("game over");
					break;
				}
				
				gameBoard[row][col] = getNumAdjacentMines(row, col);
				break;
			}
			rounds++;
    	}
    } // run


    /**
     * The entry point into the program. This main method does implement some
     * logic for handling command line arguments. If two integers are provided
     * as arguments, then a Minesweeper game is created and started with a 
     * grid size corresponding to the integers provided and with 10% (rounded
     * up) of the squares containing mines, placed randomly. If a single word 
     * string is provided as an argument then it is treated as a seed file and 
     * a Minesweeper game is created and started using the information contained
     * in the seed file. If none of the above applies, then a usage statement
     * is displayed and the program exits gracefully. 
     *
     * @param args the shell arguments provided to the program
     */
    public static void main(String[] args) {

	/*
	  The following switch statement has been designed in such a way that if
	  errors occur within the first two cases, the default case still gets
	  executed. This was accomplished by special placement of the break
	  statements.
	*/

	Minesweeper game = null;

	switch (args.length) {

        // random game
	case 2: 

	    int rows, cols;

	    // try to parse the arguments and create a game
	    try {
		rows = Integer.parseInt(args[0]);
		cols = Integer.parseInt(args[1]);
		game = new Minesweeper(rows, cols);
		break;
	    } catch (NumberFormatException nfe) { 
		// line intentionally left blank
	    } // try

	// seed file game
	case 1: 

	    String filename = args[0];
	    File file = new File(filename);

	    if (file.isFile()) {
		game = new Minesweeper(file);
	
		break;
		
	    } // if
    
        // display usage statement
	default:

	    System.out.println("Usage: java Minesweeper [FILE]");
	    System.out.println("Usage: java Minesweeper [ROWS] [COLS]");
	    System.exit(0);

	} // switch

	// if all is good, then run the game
	game.run();

    } // main
    
    /**
     * prints the minefield with marked squares and turned squares
     */
    private void printField(){
    	System.out.println("\n Rounds Completed: " + rounds + "\n");
    	/*
    	 * key for minefield:
    	 * -2 marked as mine
    	 * -1 flagged
    	 * 0 empty
    	 * >0 successfully turned over, displays # neighboring mines
    	 */
    	for (int i = 0; i < rows; i ++){
    		System.out.print(" " + i + " | ");
    		for (int j = 0; j < cols; j ++){
    			
    			switch (gameBoard[i][j]){
    			
    			case -2: //marked as definitely a mine
    				System.out.print("F");
    				break;
    			case -1: //flagged as unsure
    				System.out.print("?");
    				break;
    			case -3: //empty
    				System.out.print(" ");
    				break;
    			default: //any number that's been successfully flipped
    				System.out.print(gameBoard[i][j]);
    				break;
    			}//switch
    			
    			System.out.print(" | ");
    		}
    		System.out.print("\n");
    		
    		if (i == rows - 1){
    			System.out.print("    ");
    			for (int j = 0; j < cols; j++)
    				System.out.print(" " + j + "  ");
    		}
    	}
    }
    
    private void printPicture(){
    	System.out.println(""
		 +"         _ \n"
    	 +"  /\\/\\  (_)_ __   ___  _____      _____  ___ _ __   ___ _ __  \n"
		 +" /    \\ | | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__| \n"
		 +"/ /\\/\\ \\| | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |    \n"
		 +"\\/    \\/ _|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|    \n"
		 +"                              ALPHA EDITION |_| v2017.f"
                             		+ "");
    }
    
    private String[] getCommand(){
    	String[] s = new String[3];
    	Scanner in = new Scanner(System.in);
    	System.out.print("\n\nminesweeper-alpha$ ");
    	String command = in.nextLine().trim();
    	
    	switch(command){
    	
    	case "reveal":
    	case "r":
    		s[0] = "reveal";
    		s[1] = Integer.toString(in.nextInt());
    		s[2] = Integer.toString(in.nextInt());
    	break;
    		
    	case "mark":
    	case "m":	
    		s[0] = "mark";
    		s[1] = Integer.toString(in.nextInt());
    		s[2] = Integer.toString(in.nextInt());
    	break;
    	
    	case "guess":
    	case "g":
    		s[0] = "guess";
    		s[1] = Integer.toString(in.nextInt());
    		s[2] = Integer.toString(in.nextInt());
    	break;
    	
    	case "help":
    	case "h":
    		System.out.println("\n\nCommands Available...\n"
				 +"- Reveal: r/reveal row col \n"
				 +"-   Mark: m/mark   row col \n"
				 +"-  Guess: g/guess  row col \n"
				 +"-   Help: h/help \n"
				 +"-   Quit: q/quit");
    		s[0] = "help";
    	break;
    	
    	case "quit":
    	case "q":
    		System.exit(0);
    		break;
    		
    	default: System.out.println("Command not recognized");	
    	s[0] = "invalid";
    	}
    	in.close();
    	return s;
    	
    	
    }//getCommand
    
    private int getNumAdjacentMines(int row, int col){
    	int sum = 0;
    	for (int i = row - 1; i < row + 1; i++){
    		if (i < 0 || i > rows ) continue;
    		
    		for (int j = col - 1; j < col + 1; j++){
    			
    			if (j < 0 || j > cols) continue;
    			
    			if(mineField[i][j]) sum ++;
    		}
    	}
    	return sum;
    }



    
    
    
    
} // Minesweeper
