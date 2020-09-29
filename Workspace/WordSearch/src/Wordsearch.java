import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Contains methods for testing a word search solving lab
 * Note that all boards will be square with dimensions
 * Wordsearch.boardSize x Wordsearch.boardSize
 * @author MitchWilson
 *
 */
public class Wordsearch {

	private Random r = new Random();
	private int numOfWords;	
	private char [][] simpleBoard;
	private char [][] gameBoard;
	private String [] listOfWords;
	private int boardSize;
	
	//default constructor gets a random pre-made board
	public Wordsearch(){
		this(-1);
	}

	public Wordsearch(int boardNumber){
		getRandomBoard(boardNumber);
		fillGameBoard(); //fill board with values from simple board
	}

	public Wordsearch(String[] list){
		this(list, 0); // the parameter can be any number, just needed a different signature
		setUp(); //hides the words in the maze
		
	}
	
	
	private Wordsearch(String[] list, int boardSize){
		for (String s : list){
			if (s.length() > boardSize)
				boardSize = s.length();
		}
		listOfWords = list;
		boardSize += (r.nextInt(9) + 1); //make the board a random size that is at least 1 longer than the longest word
		numOfWords = list.length;
		this.boardSize = boardSize;
	}

	public static void printThisBoard(char[][] board){
		System.out.print("\n  ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("  " + (i % 10)); //only print 1's digit of numbers > 10
		 
		System.out.print("\n   ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("___");
		
		System.out.println();
		
		for (int i = 0; i < board.length; i++){
			System.out.printf("%2d| ", i);
			for (int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j]);
				if (j != board[0].length - 1)
					System.out.print("  ");
			}
			System.out.println("");
		}
		System.out.println();
	}
	
	public int getNumOfWords() {
		return numOfWords;
	}

	public char[][] getBoard() {
		char[][] temp = new char[gameBoard.length][gameBoard[0].length];
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[0].length; j++)
				temp[i][j] = gameBoard[i][j];
		
		return temp;
	}
	
	public char[][] getSimpleBoard(){
		char[][] temp = new char[simpleBoard.length][simpleBoard[0].length];
		for (int i = 0; i < simpleBoard.length; i++)
			for (int j = 0; j < simpleBoard[0].length; j++)
				temp[i][j] = simpleBoard[i][j];
		
		return temp;
	}

	/**
	 * 
	 * @return the list of words hidden in this board
	 */
	public String[] getList(){
		String[] sA = new String[listOfWords.length];
		for (int i = 0; i < listOfWords.length; i++){
			sA[i] = listOfWords[i];
		}
		return sA;
	}

	/**
	 * Takes the same words and rehides them in a new board
	 */
	public void reset(){
		setUp();
	}
	
	/**
	 * Sets up the wordsearch grid by generating random words and inserting them into the 2D board array
	 */
	private void setUp(){
		WordGenerator generator = new WordGenerator();
		int numGenerated = 0;
		
		//Create 2D array that represent the board
		simpleBoard = new char[boardSize][boardSize];
		gameBoard = new char[boardSize][boardSize];
		
		//fill board with '-'
		for (int i = 0; i < simpleBoard.length; i ++)
			for (int j = 0; j < simpleBoard[0].length; j++)
				simpleBoard[i][j] = '-';

		
		while(numGenerated < numOfWords){
			
			ArrayList<Integer> randRow = new ArrayList<Integer>();
			ArrayList<Integer> randCol = new ArrayList<Integer>();
			
			String word =generator.generateWord();
			//listOfWords.add(word);

			//determine if the word will be vertical or horizontal
			boolean vertical = r.nextBoolean();
			
			
			//generate a row 0 to the length - 1
			if(vertical){
				
				for(int i = 0; i<boardSize; i++){
					randCol.add(i);
				}
				
				for(int j = 0; j<(boardSize-word.length()); j++){
					randRow.add(j);
				}
				Collections.shuffle(randCol, new Random());
				Collections.shuffle(randRow, new Random());
				
				for(int j = 0; j < randCol.size(); j++){
					boolean success = false;
					for(int i = 0; i < randRow.size(); i++){
						int r = randRow.get(i);
						
						//check if the spots are empty in the 2d array
						boolean haveRoom = true;
						for(int k = 0; k < word.length(); k++){
							if(simpleBoard[k+r][randCol.get(j)]!='-'){
								//check for overlapping letters
								if(simpleBoard[k+r][randCol.get(j)] != (word.charAt(k))){
									haveRoom = false;
									break;
								}
							}
						}
						//if there's room, insert the word
						if(haveRoom){
							//insert each letter in the word into the board
							for(int k = 0; k < word.length(); k++){
								simpleBoard[k+r][randCol.get(j)] = word.charAt(k);
							}
							success = true;
							break;
						}
					}
					if(success)
						break;
				}
			}else{
				for(int i = 0; i<boardSize; i++){
					randRow.add(i);
				}
				
				for(int j = 0; j<boardSize-word.length(); j++){
					randCol.add(j);
				}
				
				Collections.shuffle(randCol, new Random());
				Collections.shuffle(randRow, new Random());
				
				for(int j = 0; j < randRow.size(); j++){
					boolean success = false;
					for(int i = 0; i < randCol.size(); i++){
						int c = randCol.get(i);
						
						//check if the spots are empty in the 2d array
						boolean haveRoom = true;
						for(int k = 0; k < word.length(); k++){
							if(simpleBoard[randRow.get(j)][k+c]!='-'){
								//check for overlapping letters
								if(simpleBoard[randRow.get(j)][k+c] != (word.charAt(k))){
									haveRoom = false;
									break;
								}
							}
						}
						//if there's room, insert the word
						if(haveRoom){
							//insert each letter in the word into the board
							for(int k = 0; k < word.length(); k++){
								simpleBoard[randRow.get(j)][k+c] = word.charAt(k);
							}
							success = true;
							break;
						}
					}
					if(success)
						break;
				}
			}
			numGenerated++;
		}
		fillGameBoard();
	}
	

	private void fillGameBoard(){
		gameBoard = new char[boardSize][boardSize];
		final char[] consonants = 
			{'b', 'c', 'd', 'f', 'g', 'h', 'j', 
			'k', 'l', 'm', 'n', 'p', 'q', 'r', 
			's', 't', 'v', 'w', 'x', 'y', 'z'};
		
		for (int i = 0; i < gameBoard.length; i ++)
			for (int j = 0; j < gameBoard[0].length; j++)
				gameBoard[i][j] = simpleBoard[i][j];
		
		for (int i = 0; i < gameBoard.length; i ++)
			for (int j = 0; j < gameBoard[0].length; j++){
				if (gameBoard[i][j] == '-')
					gameBoard[i][j] = (r.nextBoolean()) ? consonants[r.nextInt(consonants.length)] : 
						Character.toUpperCase(consonants[r.nextInt(consonants.length)]);
			}
	}
	
	
	
	 private char[][] one = {
	            {'o','n','e'},
	            {'n','n','-'},
	            {'e','-','e'},
	    };
	 public static final String[] oneList = {"one","eno"};
	    // UGA Themed
	    private char[][] xsm = {
	            {'a','t','h','e','n','s'},
	            {'u','r','-','-','c','l'},
	            {'-','g','c','-','l','c'},
	            {'-','-','a','h','m','-'},
	            {'-','-','-','-','-','-'},
	            {'-','-','-','-','-','-'}
	    };
	  public static final String[] xsmList = {"athens","uga","mlc","arch","slc"};

	    //Java/Comp Sci
	    private char[][] sm = {
	            {'-','-','m','-','b','-','-'},
	            {'j','a','v','a','y','-','-'},
	            {'-','d','j','-','t','e','-'},
	            {'-','-','k','-','e','d','-'},
	            {'t','c','e','j','b','o','-'},
	            {'-','-','r','-','-','c','-'},
	            {'-','-','-','s','-','-','-'}
	    };
	   public static final String[] smList = {"java","jvm","byte","object","code","src","jdk"};

	    //Athens Landmarks
	    private char[][] med = {
	            {'-','w','u','x','t','r','y','-','-','-'},
	            {'-','-','-','-','r','e','v','l','-','s'},
	            {'-','y','l','a','z','y','-','-','a','a'},
	            {'-','k','a','m','i','c','i','i','-','n'},
	            {'-','s','-','-','-','d','l','-','o','d'},
	            {'-','e','-','-','a','o','-','h','r','b'},
	            {'-','u','-','o','n','-','a','-','t','a'},
	            {'-','l','r','g','-','w','-','-','n','r'},
	            {'-','b','a','g','r','i','l','l','e','-'},
	            {'-','m','h','e','d','g','e','s','c','-'}
	    };
	    public static final String[] medList = {"bluesky","magnolias","sandbar","wuxtry","broad",
	                        "hedges","lazy","centro","grill", "amici","waho", };

	    //CS related
	    private char[][] lg = {
	            {'-','-','-','-','b','e','r','n','e','r','s','-','h','-'},
	            {'-','-','-','-','-','c','-','-','-','-','-','-','o','-'},
	            {'t','a','h','d','e','r','-','-','-','-','-','s','p','-'},
	            {'i','p','-','-','-','u','b','u','n','t','u','y','p','-'},
	            {'m','a','-','-','-','o','-','-','i','e','-','s','e','-'},
	            {'-','c','-','-','h','s','a','b','k','c','-','g','r','-'},
	            {'-','h','k','e','r','n','a','l','e','a','-','n','-','-'},
	            {'-','e','-','x','-','e','-','-','-','r','v','i','m','-'},
	            {'-','-','u','i','-','p','-','-','-','g','-','t','-','-'},
	            {'x','t','-','n','s','o','s','-','-','n','-','a','-','-'},
	            {'-','u','u','u','-','-','c','-','b','i','n','r','-','-'},
	            {'-','-','n','-','e','-','a','-','-','r','-','e','-','-'},
	            {'-','i','g','i','e','-','m','-','-','u','-','p','-','-'}, // dijkstra
	            {'l','-','-','-','l','-','e','-','-','t','-','o','-','-'}
	    };
	    public static final  String[] lgList = {"linus","linux","gnu","kernal","opensource","ubuntu",
	            "unix","tux","redhat","nike", "emacs","operatingsys","vim","tim",
	            "berners","lee","turing","grace","hopper","dijkstra","bin","apache"};
	    
	    private void getRandomBoard(int boardNumber){
	    	if (boardNumber < 0) //if negative, change it's value to randomly pick a board
	    		boardNumber = r.nextInt(5);
	    	
	    	//otherwise, just return the requested board
	    	switch (boardNumber) {
            case 1:
                simpleBoard = xsm;
                listOfWords = xsmList;
                break;
            case 2:
                simpleBoard = sm;
                listOfWords = smList;
                break;
            case 3:
                simpleBoard = med;
                listOfWords = medList;
                break;
            case 4:
                simpleBoard = lg;
                listOfWords = lgList;
                break;
            case 0:
                simpleBoard = one;
                listOfWords = oneList;
                break;
                
                default: 
                	throw new IllegalArgumentException("Invalid board number " + boardNumber);
	    	}
	    	numOfWords = listOfWords.length;
			boardSize = simpleBoard.length;
	    }
	/**
	 * A private class that returns the words from the list in order
	 */
	private class WordGenerator{
		private int loc = 0;
		private String generateWord(){
			if (loc < listOfWords.length)
			return listOfWords[loc++];
			
			return null;
		}
		
	}
}