package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Distance {
	Cell root;
	Grid grid;
	HashMap<Cell, Integer> map;
	int numOfCells;
	int cellsInMap;
	List<Cell> lastVisited;
	List<Cell> frontier;
	int distance;
	Cell lastCell;
	
	Distance (Grid grid){
		this.grid = grid;
		root = grid.getCell(0, 0);
		map = new HashMap<>();
		setMap(root, 0);
		numOfCells = grid.getRows()*grid.getColumns();
		frontier=new ArrayList<>();
		cellsInMap = 1;
		distance = 1;
		lastVisited = new ArrayList<>();
		lastVisited.add(root);
		iterateGrid();
		grid.setCurrentCell(root);
		grid.setGoalCell(lastCell);
	}
	
	Distance(Cell c, Grid grid){
		System.out.println("in constructor");
		this.grid = grid;
		root = c;
		map = new HashMap<>();
		setMap(root, 0);
		numOfCells = grid.getRows()*grid.getColumns();
		frontier=new ArrayList<>();
		cellsInMap = 1;
		distance = 1;
		lastVisited = new ArrayList<>();
		System.out.println(c);
		lastVisited.add(c);
		System.out.println(lastVisited);
		iterateGrid();
		grid.setCurrentCell(root);
		grid.setGoalCell(lastCell);
		
	}
	
	
	void iterateGrid() {
		System.out.println("CellsinMapabove" + cellsInMap);
		while(cellsInMap!=numOfCells) {
			System.out.println("CellsinMapin" + cellsInMap);
			System.out.println("#" + numOfCells);
			System.out.println(cellsInMap!=numOfCells);
			for(int i = 0; i < lastVisited.size(); i++) {
				System.out.println("CellsinMapinfor" + cellsInMap);
				//System.out.println(lastVisited);
				//System.out.println(lastVisited.size());
				//System.out.println(i);
				//System.out.println(lastVisited.get(i));
				List<Cell> linked = lastVisited.get(i).getLinkedCells();
				//System.out.println("LV" + lastVisited.get(i));
				for(int j = 0; j<linked.size(); j++) {
					System.out.println("j" + j);
					if(!map.containsKey(linked.get(j))){
						frontier.add(linked.get(j));
						setMap(linked.get(j), distance);
						cellsInMap++;
						System.out.println(linked.get(j) + " D:" + distance);
						
					} else {System.out.println("nope");}
				}
			}
			if(frontier.size()!=0) {
				lastCell = frontier.get(0);
			}
			lastVisited.clear();
			lastVisited.addAll(frontier);
			frontier.clear();
			distance++;
			
			System.out.println("LV"+lastVisited);
			System.out.println(lastVisited.get(0));
			System.out.println("CellsinMap" + cellsInMap);
			System.out.println("#" + numOfCells);
			
		}
		
	}
	
	void setMap(Cell c, int i){
		map.put(c, i);
	}
	int getDistace(Cell c){
		return map.get(c);
	}
	boolean isInMap(Cell c) {
		return map.containsKey(c);
	}
	Set<Cell> getCells() {
		return map.keySet();
	}
	public int getDistanceMax() {
		return distance;
	}
	public Cell getMaxCell() {
		System.out.println("In max cell");
		return lastCell;
	}

}
