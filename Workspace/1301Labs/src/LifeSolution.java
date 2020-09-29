import java.util.Random;
/**
 * 
 * @author MitchWilson
 *	Solution to Lab 10 UGA 1301 - The Game of Life
 *	RULES:
 * 		-board is randomly filled with approx 50% alive cells
 * 		-if a cell is alive - it must have 2 or 3 alive neighbors 
 * 			in order to survive the next generation
 * 		-if a cell is dead - it must have 3 alive neighbors in order
 * 			to come back to life the next generation
 */
public class LifeSolution {

	//I chose to create these as constants, but it's not mandatory 
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public static final int GRID_SIZE = 200;
	public static final int CELL_SIZE = 3;
	public static final int GRID_DELAY = 10;
	
	public static void main(String[] args) {
		Grid grid = new Grid(GRID_SIZE, CELL_SIZE, "The Game of Life");
		Random r = new Random();
		
		grid.setDelay(GRID_DELAY);
		
		for (int i = 0; i < GRID_SIZE; i++)
			for (int j = 0; j < GRID_SIZE; j++)
				grid.setPos(i, j, r.nextBoolean() ? ALIVE : DEAD); //fill grid with roughly 50% alive & 50% dead cells
		
		grid.initialize(); //only needed for the first time 
		
		for(;;){
			for (int i = 0; i < GRID_SIZE; i++){
				for (int j = 0; j < GRID_SIZE; j++){
					
					if (grid.getPos(i, j) == ALIVE){ //if cell is alive
						
						if (grid.matchingNeighbors(i, j, ALIVE) != 2 
								&& grid.matchingNeighbors(i, j, ALIVE) != 3) //if cell doesn't have 2 or 3 neighbors that are alive

								grid.setPos(i, j, DEAD);//it dies
						
					}else{ //if cell is dead
						
						if (grid.matchingNeighbors(i, j, ALIVE) == 3) //if cell has exactly 3 alive neighbors
							
							grid.setPos(i, j, ALIVE); //it comes back to life
					}
					 //grid.update(); //wrong - common error
				} 
				//grid.update(); //wrong - common error
			}
			grid.update(); //correct 
		}
	}

}


