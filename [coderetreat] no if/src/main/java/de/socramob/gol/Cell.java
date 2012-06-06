package de.socramob.gol;

public class Cell implements Cloneable {

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

	public static Cell getDeadCell() {
		Cell deadCell = new Cell();
		deadCell.die();
		return deadCell;
	}

	public Cell clone() {
		try {
			return (Cell) super.clone();
		} catch (CloneNotSupportedException e) {
			return getDeadCell();
		}
	}

	public void resetNeigbourCount() {
		this.neigbourCount = 0;
	}
}
