package de.socramob.gol;

import java.util.LinkedList;
import java.util.Map.Entry;

public class World {

	Grid grid;

	final DefaultMap<WorldDimension, Command> recursionActionMap = new DefaultMap<WorldDimension, Command>(null);

	public World() {
		this.grid = new Grid();
		this.recursionActionMap.setDefaultValue(new Command() {
			@Override
			public void run(Cell cell, Cell neigbourCell, final LinkedList<WorldDimension> neigbourList) {
				cell.incNeighbourCount(neigbourCell.getAliveValue());
				WorldDimension neigbourDimension = neigbourList.poll();
				World.this.recursionActionMap.get(neigbourDimension).run(cell, World.this.grid.getCell(neigbourDimension), neigbourList);
			}

		});
	}

	public void addCell(Cell cell, WorldDimension dimension) {
		this.grid.putCellToDimension(dimension, cell);

	}

	private LinkedList<WorldDimension> generateNeigbours(WorldDimension dimension) {
		LinkedList<WorldDimension> neigbours = new LinkedList<WorldDimension>();

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

	private void calculateAndSetNeigbourCount(final LinkedList<WorldDimension> neigbourList, Cell cell) {

		this.recursionActionMap.put(null, Command.getEmptyCommand());
		WorldDimension firstNeigbour = neigbourList.poll();
		this.recursionActionMap.get(firstNeigbour).run(cell, this.grid.getCell(firstNeigbour), neigbourList);

	}

	private void setLivingNeighbourQuantityForEachField() {
		for (Entry<WorldDimension, Cell> gridField : this.grid.getFields()) {
			Cell cellOfField = gridField.getValue();
			WorldDimension fieldDimension = gridField.getKey();
			calculateAndSetNeigbourCount(generateNeigbours(fieldDimension), cellOfField);
		}
	}

	public void nextGeneration() {

		setLivingNeighbourQuantityForEachField();

		for (Entry<WorldDimension, Cell> gridField : this.grid.getFields()) {
			Cell cellOfField = gridField.getValue();
			cellOfField.nextGeneration();
		}

	}

	// only necessary for test
	public int getLivingCellCount() {
		int livingCellCount = 0;
		for (Entry<WorldDimension, Cell> currentDimension : this.grid.getFields()) {
			Cell currentCell = currentDimension.getValue();
			livingCellCount += currentCell.getAliveValue();
		}
		return livingCellCount;

	}

	// only necessary for test
	public boolean cellIsAliveAtPosition(WorldDimension dimension) {
		return this.grid.getCell(dimension).isAlive();

	}

}
