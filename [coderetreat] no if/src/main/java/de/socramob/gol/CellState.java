package de.socramob.gol;

import java.util.Arrays;
import java.util.List;

enum CellState {
	DEAD(false, Arrays.asList(3),0), ALIVE(true, Arrays.asList(2, 3),1);

	boolean alive;
	List<Integer> listWithGoodNeigbourCount;
	int neigbourCountValue;

	private CellState(boolean alive, List<Integer> listWithGoodNeigbourCount, int neigbourCountValue) {

		this.listWithGoodNeigbourCount = listWithGoodNeigbourCount;
		this.alive = alive;
		this.neigbourCountValue=neigbourCountValue;
	}

	public boolean isAlive() {
		return alive;
	}

	public List<Integer> getAliveValues() {
		return listWithGoodNeigbourCount;
	}

	public int getAliveValue() {
		return neigbourCountValue;
	}
	

}