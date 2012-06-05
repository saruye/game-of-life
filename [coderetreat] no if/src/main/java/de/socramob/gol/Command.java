package de.socramob.gol;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import de.socramob.gol.recursion.FieldInitializerRecursion;

public class Command {

	public void run(Cell cell, Cell neigbourCell, final LinkedList<WorldDimension> neigbourList) {
	}

	public void run(WorldDimension currentDimension) {
	}

	public void run(int xCoordinate, int upperBound, Map<Boolean, FieldInitializerRecursion> fieldInitializerRules,
			Set<WorldDimension> dimensionList) {
	}

	public static Command getEmptyCommand() {
		return new Command();
	}
}