package de.socramob.gol;

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

	private static Map<Integer, CellState> aliveRulesMap = new DefaultMap<Integer, CellState>(DEAD);

	private static Map<Integer, CellState> deadRulesMap = new DefaultMap<Integer, CellState>(DEAD);
	static {

		aliveRulesMap.put(2, ALIVE);
		aliveRulesMap.put(3, ALIVE);
		deadRulesMap.put(3, ALIVE);

	}

	abstract CellState getNextGeneration(Integer countOfLivingNeighbors);

	abstract boolean isAlive();

	abstract int getAliveValue();
}
