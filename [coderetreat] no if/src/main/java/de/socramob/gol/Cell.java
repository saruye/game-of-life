package de.socramob.gol;
import java.util.HashMap;
import java.util.Map;

public class Cell {

	CellState state = CellState.ALIVE;

	public boolean isAlive() {
		return state.isAlive();
	}

	public void die() {
		state = CellState.DEAD;

	}

	public void revive() {
		state = CellState.ALIVE;
	}

	public void nextGeneration(int countLivingNeighbors) {
		state = getNextCellState(state,countLivingNeighbors);
	}

	public static CellState getNextCellState(CellState state,
			Integer neigbourcount) {
		Map<Integer, CellState> ruleMap = new HashMap<>();
		for (int i = 0; i < 8; i++) {
			ruleMap.put(i, CellState.DEAD);
		}

		for (int value : state.getAliveValues()) {
			ruleMap.put(value, CellState.ALIVE);

		}

		return ruleMap.get(neigbourcount);
	}

}
