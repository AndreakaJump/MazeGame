package application;

public class Grid {
	private int rows, columns;
	private Cell[][] cellsArr;
	private Cell currentCell;
	private Cell goalCell;

	public Cell getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

	Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;

		createGrid();
		configureCells();
		currentCell = cellsArr[0][0];
		currentCell = cellsArr[rows - 1][columns - 1];
	}

	public boolean moveCell(String direction) {
		switch (direction) {
			case "up" :
				if (currentCell.getRow() - 1 >= 0) {
					if (currentCell.isLinked(currentCell.getNorth())) {
						currentCell = cellsArr[currentCell.getRow()
								- 1][currentCell.getColumn()];
						return true;
					}
				}
				return false;
			case "down" :
				if (currentCell.getRow() + 1 < rows) {
					if (currentCell.getSouth().isLinked(currentCell)) {
						currentCell = cellsArr[currentCell.getRow()
								+ 1][currentCell.getColumn()];
						return true;
					}
				}
				return false;
			case "left" :
				if (currentCell.getColumn() - 1 >= 0) {
					if (currentCell.getWest().isLinked(currentCell)) {
						currentCell = cellsArr[currentCell.getRow()][currentCell
								.getColumn() - 1];
						return true;
					}

				}
				return false;
			case "right" :
				if (currentCell.getColumn() + 1 < columns) {
					if (currentCell.isLinked(currentCell.getEast())) {
						currentCell = cellsArr[currentCell.getRow()][currentCell
								.getColumn() + 1];
						return true;
					}
				}
				return false;
		}
		return false;
	}

	private void createGrid() {
		cellsArr = new Cell[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cellsArr[i][j] = new Cell(i, j);
			}
		}
	}

	private void configureCells() {
		for (Cell[] c : cellsArr) {
			for (Cell cell : c) {
				if (cell.getRow() + 1 < rows) {
					cell.setSouth(
							cellsArr[cell.getRow() + 1][cell.getColumn()]);
				}
				if (cell.getRow() - 1 >= 0) {
					cell.setNorth(
							cellsArr[cell.getRow() - 1][cell.getColumn()]);
				}
				if (cell.getColumn() - 1 >= 0) {
					cell.setWest(cellsArr[cell.getRow()][cell.getColumn() - 1]);
				}
				if (cell.getColumn() + 1 < columns) {
					cell.setEast(cellsArr[cell.getRow()][cell.getColumn() + 1]);
				}
			}
		}
	}

	public String toString() {
		String maze = "";
		String slashes = "";
		String top = "";
		String bottom = "";
		for (int i = 0; i < columns; i++) {
			slashes += "---+";
		}
		maze = "+" + slashes + "\n";

		for (Cell[] c : cellsArr) {
			top = "|";
			bottom = "+";
			for (Cell cell : c) {
				if (cell.getEast() != null) {
					if (cell.isLinked(cell.getEast())) {
						top += "    ";
					} else {
						top += "   |";
					}
				} else {
					top += "   |";
				}
				if (cell.getSouth() != null) {
					if (cell.isLinked(cell.getSouth())) {
						bottom += "   +";
					} else {
						bottom += "---+";
					}
				} else {
					bottom += "---+";
				}

			}
			maze += top + "\n" + bottom + "\n";
		}
		return maze;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Cell getCell(int x, int y) {
		return cellsArr[x][y];
	}
	public Cell getGoalCell() {
		return goalCell;
	}
	public void setGoalCell(Cell goalCell) {
		this.goalCell = goalCell;
	}
}
