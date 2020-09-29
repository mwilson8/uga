//package cs1302.p1;

import java.io.File;
import java.util.Scanner;

/**
 * This class represents a Minesweeper game.
 *
 * @author Derick Arenales <dga38065@uga.edu>
 */
public class Minesweeper {
    
    	int rows = 0;
	int cols = 0;
	int mines = 0;
	int num1 = 0;
	int num2 = 0;
	int num3 = 0;
	int num4 = 0;
	int num5 = 0;
	int num6 = 0;
	int num7 = 0;
	int num8 = 0;
        int rowcheck = 0;
        int colcheck = 0;
        int rounds = 0;

    String[][] Minegrid;
    boolean[][] Gridcheck;

     public boolean isInBounds(int rowcheck, int colcheck){
	   try{
		   boolean b = Gridcheck[rowcheck][colcheck];
		   return true;
	   } catch (Exception e) { return false; }
	   
	  } 





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
	     
	  Scanner s = new Scanner(seedFile);
	  rows =  s.nextInt(); 
	  cols = s.nextInt();
	  mines = s.nextInt();
	  num1 = s.nextInt();
	  num2 = s.nextInt();
	  num3 = s.nextInt();
	  num4 = s.nextInt();
	  num5 = s.nextInt();
	  num6 = s.nextInt();
	  num7 = s.nextInt();
          num8 = s.nextInt();
	  	}
	catch(Exception e){
	    System.out.println("Error!");
	}


	if(rows > 10 || rows <= 0 || cols <= 0 || cols > 10){
	    System.out.println("Cannot create game with " + seedFile + ", because it is not formatted correctly.");
	}else if(mines >= (rows*cols)){
	    System.out.println("Cannot create game with " + seedFile + ", because it is not formatted correctly.");
        }else if(num1 > rows || num3 > rows || num5 > rows || num7 > rows){
	    System.out.println("Cannot create game with " + seedFile + ", because it is not formatted correctly.");
	}else if(num2 > rows || num4 > rows || num6 > rows || num8 > cols){
	    System.out.println("Cannot create game with " + seedFile + ", because it is not formatted correctly.");
	}
	
	
        Minegrid = new String[rows][cols];
	
	for(int i = 0; i < rows; i++){
	    for(int j = 0; j < cols; j++){
		Minegrid[i][j] = " ";
	    }
	}


        Gridcheck = new boolean[rows][cols];
	
       Gridcheck[num1][num2] = true;
       Gridcheck[num3][num4] = true;
       Gridcheck[num5][num6] = true;
       Gridcheck[num7][num8] = true;



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

       
	if(rows > 10 || cols > 10){
	    System.out.println("ಠ_ಠ says,\"Cannot create a mine field with that many rows and/or columns!\"");
	    System.exit(0);
	}else if(rows <= 10 && cols <= 10){
	    
	    Minegrid = new String[rows][cols];

	    for(int i = 0; i < rows; i++){
		for(int j = 0; j < cols; j++){
		    Minegrid[i][j] = " ";
		}
	    }

	


	mines =(int)( Math.ceil(rows * cols * 0.25));

	Gridcheck = new boolean[rows][cols];
	
	int random = (int)(Math.random() * cols-1);
	int random2 = (int)(Math.random() * rows-1);

	for(int i = 1; i <= mines; i++){
	    
	    Gridcheck[random][random2] = true;
	}
      

	}} 
     // Minesweeper
    

    /**
     * Starts the game and execute the game loop.
     */
	public void run(){

       
	boolean playing = true;

	System.out.println("        _");
	System.out.println("  /\\/\\ (_)_ __  ___  _____      _____  ___ _ __   ___ _ __");
	System.out.println(" /    \\| | '_ \\/ _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|");
	System.out.println("/ /\\/\\ \\ | | | | __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |");
	System.out.println("\\/    \\/_|_| |_|\\__||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|");
	System.out.println("                            ALPHA EDITION |_| v2017.f");
	

	
	do{
	    System.out.println("");
	    System.out.println("Rounds Completed: " + rounds);
	    System.out.println("");
    
	    for(int i = 0; i < rows; i++){
		System.out.print(i + " ");
		for(int j = 0; j < cols; j++){
		    System.out.print("|" + Minegrid[i][j]);
		}            
		System.out.print("|");
		System.out.println("");
	    }

	    int h = 0;
	    System.out.print("   " + h);

	    for(int i = 1; i < rows; i++){
		System.out.print(" " + i);
	    }
	    System.out.println("");

	    System.out.println("Enter command: ");
	   Scanner g = new Scanner(System.in);
	   String command = g.nextLine();
	   
	   command = command.trim().replaceAll("\\s+", " ");   
	   String[] answers = command.split(" ");

	   if(answers[0].equals("reveal") || answers[0].equals("r")){
		   
		   
		   
		   rowcheck = Integer.parseInt(answers[1].trim());
		   colcheck = Integer.parseInt(answers[2].trim());
		  
		   if(isInBounds(rowcheck,colcheck) == false){
		       System.out.println("ಠ_ಠ says,\"Command not recognized!\"");
		       rounds++;
		   }
		   if(Gridcheck[rowcheck][colcheck] == true){
		       System.out.println(" Oh no... You revealed a mine!");
		       System.out.println("  __ _  __ _ _ __ ___   ___    _____   ____  _ __");
		       System.out.println(" / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '  |");
		       System.out.println("| (_| | (_  | | | | | |  __/ | (_) \\ V /  __/ |");
		       System.out.println(" \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|");
		       System.out.println(" |___/");
		       System.out.println("");
		       System.exit(0);
		   }else if(Gridcheck[rowcheck][colcheck] == false){
		       int adjmines = 0;
		       
		       if(isInBounds(rowcheck-1,colcheck-1) == true){
			   
			   if(Gridcheck[rowcheck-1][colcheck-1] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck-1][colcheck-1] == false){
			   }

		       }else if(isInBounds(rowcheck-1,colcheck-1) == false){
		       }
		       
		       if(isInBounds(rowcheck-1,colcheck) == true){
			   
			   if(Gridcheck[rowcheck-1][colcheck] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck-1][colcheck] == false){
			   }

		       }else if(isInBounds(rowcheck-1,colcheck) == false){
		       }
		       
		       if(isInBounds(rowcheck-1,colcheck+1) == true){
			   
			   if(Gridcheck[rowcheck-1][colcheck+1] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck-1][colcheck+1] == false){
			   }

		       }else if(isInBounds(rowcheck-1,colcheck+1) == false){
		       }

		       if(isInBounds(rowcheck,colcheck-1) == true){

			    if(Gridcheck[rowcheck][colcheck-1] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck][colcheck-1] == false){
			   }
			   
		       }else if(isInBounds(rowcheck,colcheck-1) == false){
		       }

		       if(isInBounds(rowcheck+1,colcheck-1) == true){
			   
			   if(Gridcheck[rowcheck+1][colcheck-1] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck+1][colcheck-1] == false){
			   }

		       }else if(isInBounds(rowcheck+1,colcheck-1) == false){
		       }

		       if(isInBounds(rowcheck+1,colcheck) == true){
			   
			   if(Gridcheck[rowcheck+1][colcheck] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck+1][colcheck] == false){
			   }


		       }else if(isInBounds(rowcheck+1,colcheck) == false){
		       }

		       if(isInBounds(rowcheck+1,colcheck+1) == true){
			   
			   if(Gridcheck[rowcheck+1][colcheck+1] == true){
			       adjmines++; 
			   }else if(Gridcheck[rowcheck+1][colcheck+1] == false){
			       }
			  
		       }else if(isInBounds(rowcheck+1,colcheck+1) == false){
			   }
			
		       if(isInBounds(rowcheck,colcheck+1) == true){
			   
			    if(Gridcheck[rowcheck][colcheck+1] == true){
			       adjmines++;}
			   else if(Gridcheck[rowcheck][colcheck+1] == false){
			   }

		       }else if(isInBounds(rowcheck,colcheck+1) == false){
		       }
		       
		       Minegrid[rowcheck][colcheck] = Integer.toString(adjmines);
		       rounds++;
		   }
	   }else if(answers[0].equals("mark") || answers[0].equals("m")){
	       
	        rowcheck = Integer.parseInt(answers[1].trim());
	        colcheck = Integer.parseInt(answers[2].trim());
	       
		if(isInBounds(rowcheck,colcheck) == false){
		       System.out.println("ಠ_ಠ says,\"Command not recognized!\"");
		       rounds++;
		}      
		else{
		    Minegrid[rowcheck][colcheck] = "F";
		    rounds++;

		    if(Gridcheck[rowcheck][colcheck] == true){
			mines--;
		    }
		    if(mines == 0){
			break;}
		    
		}
	   }else if(answers[0].equals("guess") || answers[0].equals("g")){
		   
	        rowcheck = Integer.parseInt(answers[1].trim());
	        colcheck = Integer.parseInt(answers[2].trim());
	       
		if(isInBounds(rowcheck,colcheck) == false){
		       System.out.println("ಠ_ಠ says,\"Command not recognized!\"");
		       rounds++;
		}      
		else{
		    Minegrid[rowcheck][colcheck] = "?";
		    rounds++;
		}	
	      
	   }else if(answers[0].equals("help") || answers[0].equals("h")){
		   System.out.println("Commands Available...");
		   System.out.println("-Reveal: r/reveal row col");
		   System.out.println("-  Mark: m/mark   row col");
		   System.out.println("- Guess: g/guess  row col");
		   System.out.println("-  Help: h/help");
		   System.out.println("-  Quit: q/quit");
	   } else if(answers[0].equals("quit") || answers[0].equals( "q")){
	           playing = false;
	           System.out.println("ლ(ಠ_ಠლ");
		   System.out.println("Y U NO PLAY MORE?");
		   System.out.println("Bye!");
		   System.exit(0);
	   }else{
	        System.out.println("ಠ_ಠ says,\"Command not recognized!\"");
		rounds++;}


	   
	}while(playing == true);

	int score = (rows * cols) - mines - rounds;

	System.out.println(" ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"");
        System.out.println(" ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░");
        System.out.println(" ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score\"");
	System.out.println(" ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░");
	System.out.println(" ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\"");
	System.out.println(" ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░");
	System.out.println(" ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ \"Wow\""); 
	System.out.println(" ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░");
       	System.out.println(" ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░");
        System.out.println(" ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░");
        System.out.println(" ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░");
        System.out.println(" ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌");
        System.out.println(" ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░");
        System.out.println(" ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░");
        System.out.println(" ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░");
        System.out.println(" ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░");
        System.out.println(" ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS! "); 
        System.out.println(" ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON! "); 
        System.out.println(" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: " + score);

	



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


} // Minesweeper

