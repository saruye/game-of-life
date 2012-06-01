package de.socramob.gol;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Grid {

	Map<WorldDimension, Cell> grid = new DefaultMap<WorldDimension, Cell>(Cell.getDeadCell());

	int greatestXCoordinate = 0;
	int greatestYCoordinate = 0;

	public void putCellToDimension(WorldDimension worldDimension, Cell cell) {
		greatestXCoordinate = Math.max(greatestXCoordinate, worldDimension.width);
		greatestYCoordinate = Math.max(greatestYCoordinate, worldDimension.height);

		this.grid.put(worldDimension, cell);

	}

	public Set<Entry<WorldDimension, Cell>> getFields() {

		for (int xCoordinate = -1; xCoordinate <= greatestXCoordinate + 1; xCoordinate++) {
			for (int yCoordinate = -1; yCoordinate <= greatestYCoordinate + 1; yCoordinate++) {
				WorldDimension currentField = new WorldDimension(xCoordinate, yCoordinate);
				if (!this.grid.containsKey(currentField)) {
					Cell deadCell = Cell.getDeadCell();
					this.grid.put(currentField, deadCell);
				}
			}

		}

		return this.grid.entrySet();
	}

	public Iterator<Entry<WorldDimension, Cell>> getFieldIterator() {
		return this.getFields().iterator();
	}

	public Cell getCell(WorldDimension worldDimension) {
		return this.grid.get(worldDimension);
	}

	public int getFieldSize() {
		int greatestCoordinate = Math.max(greatestXCoordinate, greatestYCoordinate);
		greatestCoordinate = greatestCoordinate + 1; // 0 is also a field

		return greatestCoordinate * greatestCoordinate;
	}

}
