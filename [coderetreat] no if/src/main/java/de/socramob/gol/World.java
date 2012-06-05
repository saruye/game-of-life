package de.socramob.gol;

import java.util.LinkedList;

import de.socramob.gol.recursion.ListIterator;

public class World {

	protected Grid grid;

	private DefaultMap<WorldDimension, Command> recursionActionMap = new DefaultMap<WorldDimension, Command>(null);

	public World() {
		this.grid = new Grid();

		Command command = new Command() {
			@Override
			public void run(Cell cell, Cell neigbourCell, final LinkedList<WorldDimension> neigbourList) {
				cell.incNeighbourCount(neigbourCell.getAliveValue());

				WorldDimension neigbourDimension = neigbourList.poll();
				Cell firstNeigbourCell = World.this.grid.getCell(neigbourDimension);

				Command cmd = World.this.recursionActionMap.get(neigbourDimension);
				cmd.run(cell, firstNeigbourCell, neigbourList);
			}

		};

		this.recursionActionMap.setDefaultValue(command);
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

	private void calculateAndSetNeigbourCount(final LinkedList<WorldDimension> neighbourList, Cell cell) {
		this.recursionActionMap.put(null, Command.getEmptyCommand());

		WorldDimension firstNeighbourDim = neighbourList.poll();
		Cell firstNeighbourCell = this.grid.getCell(firstNeighbourDim);

		Command cmd = this.recursionActionMap.get(firstNeighbourDim);
		cmd.run(cell, firstNeighbourCell, neighbourList);
	}

	private void setLivingNeighbourQuantityForEachField() {
		Command command = new Command() {
			@Override
			public void run(WorldDimension fieldDimension) {
				Cell cellOfField = World.this.grid.getCell(fieldDimension).clone();
				World.this.grid.putCellToDimension(fieldDimension, cellOfField);
				calculateAndSetNeigbourCount(generateNeigbours(fieldDimension), cellOfField);
			}

		};

		ListIterator listIterator = new ListIterator(command);
		listIterator.iterate(this.grid.getFieldIterator());
	}

	public void nextGeneration() {

		setLivingNeighbourQuantityForEachField();

		Command command = new Command() {
			@Override
			public void run(WorldDimension currentDimension) {
				Cell cellOfField = World.this.grid.getCell(currentDimension);
				cellOfField.nextGeneration();
			}

		};

		ListIterator listIterator = new ListIterator(command);
		listIterator.iterate(this.grid.getFieldIterator());
	}

	// only necessary for test
	public int getLivingCellCount() {
		int livingCellCount = 0;

		for (WorldDimension currentDimension : this.grid.getFields()) {
			Cell currentCell = grid.getCell(currentDimension);
			livingCellCount += currentCell.getAliveValue();
		}
		return livingCellCount;

	}

	// only necessary for test
	public boolean cellIsAliveAtPosition(WorldDimension dimension) {
		return this.grid.getCell(dimension).isAlive();

	}
}
