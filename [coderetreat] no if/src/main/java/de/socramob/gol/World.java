package de.socramob.gol;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class World {

	Grid grid;

	Map<Cell, Command> actionMap;

	public World() {
		this.grid = new Grid();
		this.actionMap = new DefaultMap<Cell, Command>(Command.getIncNeigbourCountCommand());
		this.actionMap.put(null, Command.getEmptyCommand());
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

	private void calculateAndSetNeigbourCount(final LinkedList<WorldDimension> neigbourList, Cell cell) {

		final DefaultMap<WorldDimension, Command> recursionActionMap = new DefaultMap<WorldDimension, World.Command>(null);
		recursionActionMap.setDefaultValue(new Command() {
			@Override
			public void run(Cell cell, Cell neigbourCell, WorldDimension dimension) {
				World.this.actionMap.get(neigbourCell).run(cell, neigbourCell, null);
				doRecursion(neigbourList, recursionActionMap, cell, neigbourCell);
			}

		});
		recursionActionMap.put(null, Command.getEmptyCommand());
		WorldDimension firstNeigbour = neigbourList.poll();

		recursionActionMap.get(firstNeigbour).run(cell, this.grid.getCell(firstNeigbour), null);

	}

	private void doRecursion(final LinkedList<WorldDimension> neigbourList, final DefaultMap<WorldDimension, Command> recursionActionMap, Cell cell,
								Cell neigbourCell) {

		WorldDimension neigbourDimension = neigbourList.poll();
		Cell realNeigbourCell = World.this.grid.getCell(neigbourDimension);
		recursionActionMap.get(neigbourDimension).run(cell, realNeigbourCell, neigbourDimension);
	}

	private void setLivingNeighbourQuantityForEachField() {
		for (Entry<WorldDimension, Cell> gridField : this.grid.getFields()) {
			Cell cellOfField = gridField.getValue();
			WorldDimension fieldDimension = gridField.getKey();
			calculateAndSetNeigbourCount(generateNeigbours(fieldDimension), cellOfField);
		}
	}

	static class Command {

		public void run(Cell cell, Cell neigbourCell, WorldDimension dimension) {
		};

		static Command getEmptyCommand() {
			return new Command();
		}

		static Command getIncNeigbourCountCommand() {
			return new Command() {

				@Override
				public void run(Cell cell, Cell neigbourCell, WorldDimension dimension) {
					cell.incNeighbourCount(neigbourCell.getAliveValue());
				}
			};
		}

	}

}
