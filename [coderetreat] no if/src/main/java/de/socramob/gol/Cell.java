package de.socramob.gol;

public class Cell {

	private CellState state = CellState.ALIVE;

	private int neigbourCount = 0;

	public boolean isAlive() {
		return this.state.isAlive();
	}

	public void die() {
		this.state = CellState.DEAD;
	}

	public void nextGeneration() {
		this.state = this.state.getNextGeneration(this.neigbourCount);
	}

	public int getAliveValue() {
		return this.state.getAliveValue();
	}

	public void incNeighbourCount(int i) {
		this.neigbourCount += i;
	}

	public void resetNeighbourQuantity() {
		this.neigbourCount = 0;
	}

	public static Cell getDeadCell() {
		Cell deadCell = new Cell();
		deadCell.die();
		return deadCell;
	}

}
