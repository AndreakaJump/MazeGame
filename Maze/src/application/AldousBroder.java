package application;

import java.util.Random;

public class AldousBroder {
	static Grid grid;
	Random rand = new Random();
	int unvisited;
	Cell currentCell;
	Cell nextCell;
	
	AldousBroder(Grid grid){
		this.grid = grid;
		unvisited = grid.getRows()*grid.getColumns()-1;
		currentCell = grid.getCell((int)(Math.random()*(grid.getRows())), (int)(Math.random()*(grid.getColumns())));
	}
	
	void iterateGrid()
	{
		while(unvisited!=0) {
			//System.out.println( currentCell);
			nextCell = currentCell.getNeighbors().get((int)(Math.random()*currentCell.getNeighbors().size()));
			if(!nextCell.isLinkedToAny()) {
				currentCell.link(nextCell, true);
				nextCell.link(currentCell, true);
				unvisited--;
				
			}
			currentCell=nextCell;
		}
		//printGridLinks();
		

	}
	static void printGridLinks() {
		for(int i = 0; i < grid.getRows(); i++) {
			for(int j = 0; j < grid.getColumns(); j++) {
				Cell c = grid.getCell(i, j);
				//System.out.println(c + " N:" + c.isLinked(c.getNorth()) + " S:" + c.isLinked(c.getSouth()) + " E:" + c.isLinked(c.getEast()) + " W:" + c.isLinked(c.getWest()));
			}
		}
	}
}
