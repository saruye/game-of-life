package de.socramob.gol;

public class Cell {

	private CellState state = CellState.ALIVE;

	public boolean isAlive() {
		return state.isAlive();
	}

	public void die() {
		state = CellState.DEAD;
	}

	public void nextGeneration(int countLivingNeighbors) {
		state = state.getNextGeneration(countLivingNeighbors);
	}

	public int getAliveValue() {
		return state.getAliveValue();
	}

}
