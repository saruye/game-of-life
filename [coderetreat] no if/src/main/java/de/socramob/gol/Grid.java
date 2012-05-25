package de.socramob.gol;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Grid {

	Map<WorldDimension, Cell> grid = new DefaultMap<WorldDimension, Cell>(Cell.getDeadCell());

	public void putCellToDimension(WorldDimension worldDimension, Cell cell) {
		this.grid.put(worldDimension, cell);

	}

	public Set<Entry<WorldDimension, Cell>> getFields() {
		return this.grid.entrySet();
	}

	public Cell getCell(WorldDimension worldDimension) {
		return this.grid.get(worldDimension);
	}

}
