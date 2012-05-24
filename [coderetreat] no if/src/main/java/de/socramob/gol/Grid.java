package de.socramob.gol;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Grid {

	Map<WorldDimension, Cell> grid = new HashMap<WorldDimension, Cell>();

	public void putCellToDimension(WorldDimension worldDimension, Cell cell) {
		grid.put(worldDimension, cell);

	}

	public Set<Entry<WorldDimension, Cell>> getFields() {
		return grid.entrySet();
	}

	public Cell getCell(WorldDimension worldDimension) {
		return grid.get(worldDimension);
	}


}
