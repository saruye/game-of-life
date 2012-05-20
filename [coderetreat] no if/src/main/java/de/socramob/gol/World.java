package de.socramob.gol;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class World {

	Map<Dimension, Cell> grid = new HashMap<>();

	public void addCell(Cell cell, Dimension dimension) {
		this.grid.put(dimension, cell);

	}

	public int getCellCount() {
		return this.grid.size();
	}

	private List<Dimension> generateNeigbours(Dimension dimension) {
		List<Dimension> neigbours = new ArrayList<>();
		neigbours.add(new Dimension(dimension.width - 1, dimension.height - 1));
		neigbours.add(new Dimension(dimension.width - 1, dimension.height + 1));
		neigbours.add(new Dimension(dimension.width - 1, dimension.height));
		neigbours.add(new Dimension(dimension.width + 1, dimension.height - 1));
		neigbours.add(new Dimension(dimension.width + 1, dimension.height + 1));
		neigbours.add(new Dimension(dimension.width + 1, dimension.height));
		neigbours.add(new Dimension(dimension.width, dimension.height - 1));
		neigbours.add(new Dimension(dimension.width, dimension.height + 1));

		return neigbours;
	}

	public int getLivingNeigbourCount(Dimension dimension) {
		Set<Cell> neigbourCells = new HashSet<>();

		for (Dimension neigbourSpot : generateNeigbours(dimension)) {
			neigbourCells.add(this.grid.get(neigbourSpot));
		}
		neigbourCells.remove(null);
		int neigbourCount = 0;
		for (Cell currentCell : neigbourCells) {
			neigbourCount += currentCell.state.getAliveValue();
		}

		return neigbourCount;
	}

	public void nextGeneration() {

		Map<Dimension, Integer> neigbourMemory = new HashMap<>();
		for (Entry<Dimension, Cell> entry : this.grid.entrySet()) {
			neigbourMemory.put(entry.getKey(), getLivingNeigbourCount(entry.getKey()));
		}
		for (Entry<Dimension, Cell> entry : this.grid.entrySet()) {
			entry.getValue().nextGeneration(neigbourMemory.get(entry.getKey()));
		}

	}

	public int getLivingCellCount() {
		int livingCellCount = 0;
		for (Entry<Dimension, Cell> currentCell : this.grid.entrySet()) {
			livingCellCount += currentCell.getValue().state.getAliveValue();
		}
		return livingCellCount;

	}

}
