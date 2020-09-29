import java.util.Random;

/**
 * 
 * @author MitchWilson
 *
 */
public class Life2 {

	public static final int DEAD = 0; 		 //these correspond to colors in grid class 0: black
	public static final int ALIVE = 1;		 //these correspond to colors in grid class 1: white
	public static final int GRID_SIZE = 200; //number of cells in the square grid
	public static final int CELL_SIZE = 3;	 //number of pixels per cell? 
	public static final int GRID_DELAY = 10; //delay after updating the grid (in ms?)
	
	public static void main(String[] args) {

		
		Grid grid = new Grid(GRID_SIZE, CELL_SIZE, "The Game of Life");
		Random r = new Random();
		grid.setDelay(GRID_DELAY);
		
		for (int i = 0; i < GRID_SIZE; i++){
			for (int j = 0; j < GRID_SIZE; j++){
				//set the current position to either dead or alive, roughly 50% of each
				grid.setPos(i, j, r.nextInt(2));
			}
		}
		grid.initialize();
		
		for(;;){
			for (int i = 0; i < GRID_SIZE; i++){
				for (int j = 0; j < GRID_SIZE; j++){
					
					if (grid.getPos(i, j) ==  ALIVE){
						//cell is alive
						if (grid.matchingNeighbors(i, j, ALIVE) != 2 
								&& grid.matchingNeighbors(i, j, ALIVE) != 3)
							//if the cell is alive and does not have 2 or 3 alive neighbors
							//it dies, otherwise it remains alive
							grid.setPos(i, j, DEAD);
					}
					
					if (grid.getPos(i, j) == DEAD){
						//cell is dead
						if (grid.matchingNeighbors(i, j, ALIVE) == 3)
							//if the cell is dead & has exactly 3 alive neighbors
							//it comes back to life, otherwise it remains dead
							grid.setPos(i, j, ALIVE);
					}
				} //inner for loop
			}//outer for loop
			
			grid.update();
		}//infinite game loop
	}

}


