import java.util.Random;

/**
 * 
 * @author MitchWilson
 *
 */
public class Life {

	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public static final int GRID_SIZE = 200;
	public static final int CELL_SIZE = 3;
	public static final int GRID_DELAY = 10;
	
	public static void main(String[] args) {

		Grid grid = new Grid(GRID_SIZE, CELL_SIZE, "The Game of Life");
		Random r = new Random();
		
		grid.setDelay(GRID_DELAY);
		
		for (int i = 0; i < GRID_SIZE; i++){
			for (int j = 0; j < GRID_SIZE; j++){
				//set the grid to 50% alive, 50% dead (roughly)
				grid.setPos(i, j, r.nextBoolean() ? ALIVE : DEAD);
			}
		}
		grid.initialize();
		
		for(;;){
			for (int i = 0; i < GRID_SIZE; i++){
				for (int j = 0; j < GRID_SIZE; j++){
					
					if (grid.getPos(i, j) ==  ALIVE){ //cell is alive
						
						if (grid.matchingNeighbors(i, j, ALIVE) != 2 
								&& grid.matchingNeighbors(i, j, ALIVE) != 3)
							//if the cell is alive and does not have 2 or 3 alive neighbors
							//it dies, otherwise it remains alive
							grid.setPos(i, j, DEAD);
					}
					
					if (grid.getPos(i, j) == DEAD){ //cell is dead
						
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


