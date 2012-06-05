package de.socramob.gol;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.socramob.gol.recursion.FieldInitializerRecursion;
import de.socramob.gol.recursion.GridFillRecursion;
import de.socramob.gol.recursion.GridFillRecursionExit;
import de.socramob.gol.recursion.GridFillRecursionNext;

public class Grid {

	private Map<WorldDimension, Cell> grid = new DefaultMap<WorldDimension, Cell>(Cell.getDeadCell());
	private Set<WorldDimension> dimensionList = new HashSet<WorldDimension>();

	private int greatestXCoordinate = 0;
	private int greatestYCoordinate = 0;

	public void putCellToDimension(WorldDimension worldDimension, Cell cell) {
		greatestXCoordinate = Math.max(greatestXCoordinate, worldDimension.width);
		greatestYCoordinate = Math.max(greatestYCoordinate, worldDimension.height);

		this.grid.put(worldDimension, cell);
	}

	public Set<WorldDimension> getFields() {
		Map<Boolean, GridFillRecursion> gridFillRules = new HashMap<Boolean, GridFillRecursion>();
		gridFillRules.put(Boolean.TRUE, new GridFillRecursionNext());
		gridFillRules.put(Boolean.FALSE, new GridFillRecursionExit());

		int upperBound = Math.max(greatestXCoordinate, greatestYCoordinate) + 1;
		int startXCoordinate = -1;

		Command command = new Command() {
			@Override
			public void run(int xCoordinate, int upperBound,
					Map<Boolean, FieldInitializerRecursion> fieldInitializerRules, Set<WorldDimension> dimensionList) {
				int step = -1;
				FieldInitializerRecursion nextStep = fieldInitializerRules.get(dimensionList.size() < getFieldSize());
				nextStep.initialize(xCoordinate, step, upperBound, dimensionList, fieldInitializerRules);
			}
		};

		GridFillRecursion fillRecursion = gridFillRules.get(this.dimensionList.size() < getFieldSize());
		fillRecursion.fill(gridFillRules, startXCoordinate, upperBound, this.dimensionList, command);

		return this.dimensionList;
	}

	public Iterator<WorldDimension> getFieldIterator() {
		System.out.println(this.getFields().size());
		return this.getFields().iterator();
	}

	public Cell getCell(WorldDimension worldDimension) {
		return this.grid.get(worldDimension);
	}

	public int getFieldSize() {
		int greatestCoordinate = Math.max(greatestXCoordinate, greatestYCoordinate);
		greatestCoordinate = greatestCoordinate + 3; // 0 is also a field

		return greatestCoordinate * greatestCoordinate;
	}

}
