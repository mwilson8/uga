import java.util.Random;

public class Life {

	public static void main(String[] args) {

		int gridSize = 200;
		int cellSize = 3;
		Grid grid = new Grid(gridSize, cellSize, "The Game of Life");
		grid.setDelay(10);

		int aliveColor = 1;
		int deadColor = 0;
		grid.initialize();
		grid.setPos(5, 5, 1);
		grid.update();
		Random r = new Random();

		for (int i = 0; i < gridSize; i++) {
			for (int x = 0; x < gridSize; x++) {
				if (r.nextInt(100) > 49)
					grid.setPos(x, i, aliveColor);
				else
					grid.setPos(x, i, deadColor);
			}
		}
		grid.initialize();
		while (true) {

			for (int i = 0; i < gridSize; i++) {
				for (int x = 0; x < gridSize; x++) {
					if (1 == grid.getPos(x, i)) {
						if (3 == grid.matchingNeighbors(x, i, aliveColor)
								|| 2 == grid.matchingNeighbors(x, i, aliveColor))
							grid.setPos(x, i, aliveColor);
						else {
							grid.setPos(x, i, deadColor);
						}
					} else {
						if (0 == grid.getPos(x, i)) {
							if (3 == grid.matchingNeighbors(x, i, aliveColor))
								grid.setPos(x, i, aliveColor);
						

						else
							grid.setPos(x, i, deadColor);	
						}
					}
				}

			}
			grid.update();

		}
	}
}
