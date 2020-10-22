package application;

import java.util.Random;

public class AldousBroder {
	private Grid grid;
	Random rand;
	int unvisited;
	Cell currentCell;
	Cell nextCell;

	AldousBroder(Grid grid) {
		this.grid = grid;
		this.rand = new Random();
		unvisited = grid.getRows() * grid.getColumns() - 1;
		currentCell = grid.getCell((int) (Math.random() * (grid.getRows())),
				(int) (Math.random() * (grid.getColumns())));
	}

	void iterateGrid() {
		while (unvisited != 0) {
			nextCell = currentCell.getNeighbors().get(
					(int) (Math.random() * currentCell.getNeighbors().size()));
			if (!nextCell.isLinkedToAny()) {
				currentCell.link(nextCell, true);
				nextCell.link(currentCell, true);
				unvisited--;
			}
			currentCell = nextCell;
		}
	}
	public void printGridLinks() {
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getColumns(); j++) {
				Cell c = grid.getCell(i, j);
			}
		}
	}
}
