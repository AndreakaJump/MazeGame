package application;
import java.util.ArrayList;
import java.util.List;

public class Cell {
	private int row, column;
	private Cell north, south, east, west;
	private List<Cell> linkedCells;

	Cell(int row, int column) {
		this.row = row;
		this.column = column;
		linkedCells = new ArrayList<>();
	}

	void link(Cell cell, boolean linkInputCellToDestinationCell) {
		linkedCells.add(cell);
		if (linkInputCellToDestinationCell) {
			if (cell.linkedCells.contains(this)) {
				cell.link(this, false);
			}
		}
	}

	void unlink(Cell cell, boolean unlinkInputCellFromDestinationCell) {
		if (linkedCells.contains(cell)) {
			linkedCells.remove(cell);
		}
		if (unlinkInputCellFromDestinationCell) {
			if (cell.linkedCells.contains(this)) {
				cell.linkedCells.remove(this);
			}
		}
	}

	boolean isLinked(Cell cell) {
		if (linkedCells.contains(cell)) {
			return true;
		}
		return false;
	}

	boolean isLinkedToAny() {
		if (linkedCells.size() != 0) {
			return true;
		}
		return false;
	}

	public String whatPng() {
		String fileName = "";

		if (this.north != null) {
			if (!isLinked(north)) {
				fileName += "N";
			}
		} else {
			fileName += "N";
		}

		if (this.south != null) {
			if (!isLinked(south)) {
				fileName += "S";
			}
		} else {
			fileName += "S";
		}

		if (this.east != null) {
			if (!isLinked(east)) {
				fileName += "E";
			}
		} else {
			fileName += "E";
		}
		if (this.west != null) {
			if (!isLinked(west)) {
				fileName += "W";
			}
		} else {
			fileName += "W";
		}
		if (fileName == "") {
			fileName += "none";
		}

		fileName += ".png";

		return fileName;
	}

	public ArrayList<Cell> getNeighbors() {
		ArrayList<Cell> neighbors = new ArrayList<>();
		if (north != null) {
			neighbors.add(north);
		}
		if (south != null) {
			neighbors.add(south);
		}
		if (west != null) {
			neighbors.add(west);
		}
		if (east != null) {
			neighbors.add(east);
		}
		return neighbors;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Cell getNorth() {
		return north;
	}

	public void setNorth(Cell north) {
		this.north = north;
	}

	public Cell getSouth() {
		return south;
	}

	public void setSouth(Cell south) {
		this.south = south;
	}

	public Cell getEast() {
		return east;
	}

	public void setEast(Cell east) {
		this.east = east;
	}

	public Cell getWest() {
		return west;
	}

	public void setWest(Cell west) {
		this.west = west;
	}

	@Override
	public String toString() {
		return "cell (" + row + ", " + column + ")";
	}

	public List<Cell> getLinkedCells() {
		return linkedCells;
	}

}
