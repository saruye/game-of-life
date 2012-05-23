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

	Map<Dimension, Cell> grid = new HashMap<Dimension, Cell>();

	public void addCell(Cell cell, Dimension dimension) {
		this.grid.put(dimension, cell);

	}

	private List<Dimension> generateNeigbours(Dimension dimension) {
		List<Dimension> neigbours = new ArrayList<Dimension>();
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

	public void nextGeneration() {
		Map<Dimension, Integer> neigbourMemory = new HashMap<Dimension, Integer>();

		neigbourMemory = createNeighbourMemory();

		for (Entry<Dimension, Cell> dimension : this.grid.entrySet()) {
			Dimension currentDimension = dimension.getKey();
			int livingNeighboursCount = neigbourMemory.get(currentDimension);
			
			Cell currentCell = dimension.getValue();
			currentCell.nextGeneration(livingNeighboursCount);
		}

	}

	public int countLivingNeigbours(Dimension dimension) {
		Set<Cell> neigbourCells = determineNeighbourCells(dimension);

		int neigbourCount = 0;
		for (Cell currentCell : neigbourCells) {
			neigbourCount += currentCell.getAliveValue();
		}

		return neigbourCount;
	}

	public int getLivingCellCount() {
		int livingCellCount = 0;
		for (Entry<Dimension, Cell> currentDimension : this.grid.entrySet()) {
			Cell currentCell = currentDimension.getValue();
			livingCellCount += currentCell.getAliveValue();
		}
		return livingCellCount;

	}


	private Map<Dimension, Integer> createNeighbourMemory() {
		Map<Dimension, Integer> neigbourMemory = new HashMap<Dimension, Integer>();

		for (Entry<Dimension, Cell> dimension : this.grid.entrySet()) {
			int livingNeighboursCount = countLivingNeigbours(dimension.getKey());
			neigbourMemory.put(dimension.getKey(), livingNeighboursCount);
		}

		return neigbourMemory;
	}

	private Set<Cell> determineNeighbourCells(Dimension dimension) {
		Set<Cell> neigbourCells = new HashSet<Cell>();

		for (Dimension neigbourSpot : generateNeigbours(dimension)) {
			neigbourCells.add(this.grid.get(neigbourSpot));
		}

		neigbourCells.remove(null);

		return neigbourCells;
	}

}
