package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Distance {
	private Cell root;
	private Grid grid;
	private HashMap<Cell, Integer> map;
	private int numOfCells;
	private int cellsInMap;
	private List<Cell> lastVisited;
	private List<Cell> frontier;
	private int distance;
	private Cell lastCell;

	Distance(Grid grid) {
		this.grid = grid;
		root = grid.getCell(0, 0);
		map = new HashMap<>();
		setMap(root, 0);
		numOfCells = grid.getRows() * grid.getColumns();
		frontier = new ArrayList<>();
		cellsInMap = 1;
		distance = 1;
		lastVisited = new ArrayList<>();
		lastVisited.add(root);
		iterateGrid();
		grid.setCurrentCell(root);
		grid.setGoalCell(lastCell);
	}

	Distance(Cell c, Grid grid) {
		this.grid = grid;
		root = c;
		map = new HashMap<>();
		setMap(root, 0);
		numOfCells = grid.getRows() * grid.getColumns();
		frontier = new ArrayList<>();
		cellsInMap = 1;
		distance = 1;
		lastVisited = new ArrayList<>();
		lastVisited.add(c);
		iterateGrid();
		grid.setCurrentCell(root);
		grid.setGoalCell(lastCell);
	}

	public void iterateGrid() {
		while (cellsInMap != numOfCells) {
			for (int i = 0; i < lastVisited.size(); i++) {
				List<Cell> linked = lastVisited.get(i).getLinkedCells();
				for (int j = 0; j < linked.size(); j++) {
					if (!map.containsKey(linked.get(j))) {
						frontier.add(linked.get(j));
						setMap(linked.get(j), distance);
						cellsInMap++;
					}
				}
			}
			if (frontier.size() != 0) {
				lastCell = frontier.get(0);
			}
			lastVisited.clear();
			lastVisited.addAll(frontier);
			frontier.clear();
			distance++;
		}
	}

	public void setMap(Cell c, int i) {
		map.put(c, i);
	}
	public int getDistace(Cell c) {
		return map.get(c);
	}
	public boolean isInMap(Cell c) {
		return map.containsKey(c);
	}
	public Set<Cell> getCells() {
		return map.keySet();
	}
	public int getDistanceMax() {
		return distance;
	}
	public Cell getMaxCell() {
		return lastCell;
	}

}
