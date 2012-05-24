package de.socramob.gol;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class World {

	Grid grid = new Grid();

	public void addCell(Cell cell, WorldDimension dimension) {
		this.grid.putCellToDimension(dimension, cell);

	}

	private List<WorldDimension> generateNeigbours(WorldDimension dimension) {
		List<WorldDimension> neigbours = new LinkedList<WorldDimension>();

		neigbours.add(WorldDimension.createSouthWest(dimension));
		neigbours.add(WorldDimension.createNorthWest(dimension));
		neigbours.add(WorldDimension.createWest(dimension));
		neigbours.add(WorldDimension.createNorthEast(dimension));
		neigbours.add(WorldDimension.createSouthEast(dimension));
		neigbours.add(WorldDimension.createEast(dimension));
		neigbours.add(WorldDimension.createNorth(dimension));
		neigbours.add(WorldDimension.createSouth(dimension));

		return neigbours;
	}

	public void nextGeneration() {

		setLivingNeighbourQuantityForEachField();

		for (Entry<WorldDimension, Cell> gridField : this.grid.getFields()) {
			Cell cellOfField = gridField.getValue();
			cellOfField.nextGeneration();
		}

	}

	public int countLivingNeigbours(WorldDimension dimension) {
		Set<Cell> neigbourCells = determineNeighbourCells(dimension);

		int neigbourCount = 0;
		for (Cell currentCell : neigbourCells) {
			neigbourCount += currentCell.getAliveValue();
		}

		return neigbourCount;
	}

	public int getLivingCellCount() {
		int livingCellCount = 0;
		for (Entry<WorldDimension, Cell> currentDimension : this.grid.getFields()) {
			Cell currentCell = currentDimension.getValue();
			livingCellCount += currentCell.getAliveValue();
		}
		return livingCellCount;

	}

	private Set<Cell> determineNeighbourCells(WorldDimension dimension) {
		Set<Cell> neigbourCells = new HashSet<Cell>();

		for (WorldDimension neigbourSpot : generateNeigbours(dimension)) {
			neigbourCells.add(this.grid.getCell(neigbourSpot));
		}

		neigbourCells.remove(null);

		return neigbourCells;
	}

	private void setLivingNeighbourQuantityForEachField() {
		for (Entry<WorldDimension, Cell> gridField : this.grid.getFields()) {
			Cell cellOfField = gridField.getValue();
			WorldDimension fieldDimension = gridField.getKey();
			cellOfField.setLivingNeigbourQuantity(countLivingNeigbours(fieldDimension));
		}
	}

}
