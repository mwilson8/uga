
public class Driver {

	
	public static void main (String[] args){
		
			
			Board b = new Board();
			
			//students write one static method, it's signature is below
			StudentSolution.solve(b);
			
			//this prints all the solutions the students found
			b.printSolutions();
			
			//shows if the students found all the correct solutions
			System.out.println(b.checkSolutions());
			
		
			char[][] board = new char[][] {
			     //0   1   2   3   4   5 
			/*0*/{'o',' ',' ','o','w','t'},
			/*1*/{'n',' ',' ',' ',' ','h'},
			/*2*/{'e',' ',' ',' ',' ','r'},
			/*3*/{' ',' ',' ','r',' ','e'},
			/*4*/{' ',' ','u',' ',' ','e'},
			/*5*/{' ','o',' ',' ',' ',' '},
			/*6*/{'f','i','v','e',' ',' '}
			};
			
			String [] list = new String[] {"one", "two", "three", "four", "five"};


			String [] solution = new String[]{"one : (0, 0) (2, 0)",
												"two : (0, 5) (0, 3)",
												"three : (0, 5) (4, 5)",
												"four : (6, 0) (3, 3)",
												"five : (6, 0) (6, 3)"};

			//this is the constructor we'd use for grading
			//pass in our own board and list of words - an example is declared above
			b = new Board(board, list);
			
			//the students's solution still searches the board and finds the words
			//their found solutions are stored in the Board object under studentSolutions
			StudentSolution.solve(b);
			
			//print the found solutions to see what was found, won't execute in the rubric
			//b.printSolutions();
			
			//check the students' found solutions against the correct solutions
			//pass in the correct solution String array, order doesn't matter
			//the int parameter is the number of points this test case is worth
			System.out.println(b.checkSolutions(solution, 20));
			
			//an example if the student did not find a solution they were supposed to
			//simulated here by changing the list to include a solution not actually in the board
			solution[4] = "nine : (1, 1) (1, 1)";
			System.out.println(b.checkSolutions(solution, 20));
		
	}
	
	
}
