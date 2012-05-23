package de.socramob.gol;

import java.util.HashMap;
import java.util.Map;

enum CellState {
	DEAD() {
		@Override
		CellState getNextGeneration(Integer countOfLivingNeighbors) {
			return deadRulesMap.get(countOfLivingNeighbors);
		}

		@Override
		boolean isAlive() {
			return false;
		}

		@Override
		int getAliveValue() {
			return 0;
		}

	},
	ALIVE() {
		@Override
		CellState getNextGeneration(Integer countOfLivingNeighbors) {
			return aliveRulesMap.get(countOfLivingNeighbors);
		}

		@Override
		boolean isAlive() {
			return true;
		}

		@Override
		int getAliveValue() {
			return 1;
		}

	};

	private static Map<Integer, CellState> aliveRulesMap = new HashMap<Integer, CellState>();
	private static Map<Integer, CellState> deadRulesMap = new HashMap<Integer, CellState>();
	static {
		aliveRulesMap.put(0, DEAD);
		aliveRulesMap.put(1, DEAD);
		aliveRulesMap.put(2, ALIVE);
		aliveRulesMap.put(3, ALIVE);
		aliveRulesMap.put(4, DEAD);
		aliveRulesMap.put(5, DEAD);
		aliveRulesMap.put(6, DEAD);
		aliveRulesMap.put(7, DEAD);
		aliveRulesMap.put(8, DEAD);

		deadRulesMap.put(0, DEAD);
		deadRulesMap.put(1, DEAD);
		deadRulesMap.put(2, DEAD);
		deadRulesMap.put(3, ALIVE);
		deadRulesMap.put(4, DEAD);
		deadRulesMap.put(5, DEAD);
		deadRulesMap.put(6, DEAD);
		deadRulesMap.put(7, DEAD);
		deadRulesMap.put(8, DEAD);
	}

	abstract CellState getNextGeneration(Integer countOfLivingNeighbors);

	abstract boolean isAlive();

	abstract int getAliveValue();
}
