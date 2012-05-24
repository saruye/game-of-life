package de.socramob.gol;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class World {

	Map<Dimension, Cell> grid = new HashMap<Dimension, Cell>();

	public void addCell(Cell cell, Dimension dimension) {
		this.grid.put(dimension, cell);

	}

	private LinkedList<Dimension> generateNeigbours(Dimension dimension) {
		LinkedList<Dimension> neigbours = new LinkedList<Dimension>();
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

		for (Entry<Dimension, Cell> dimension : this.grid.entrySet()) {
			dimension.getValue().incNeigbourCountBy(countLivingNeigbours(dimension.getKey()));
		}
		for (Entry<Dimension, Cell> dimension : this.grid.entrySet()) {
			dimension.getValue().nextGeneration();
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

	private Set<Cell> determineNeighbourCells(Dimension dimension) {
		Set<Cell> neigbourCells = new HashSet<Cell>();

		for (Dimension neigbourSpot : generateNeigbours(dimension)) {
			neigbourCells.add(this.grid.get(neigbourSpot));
		}

		neigbourCells.remove(null);

		return neigbourCells;
	}

}
