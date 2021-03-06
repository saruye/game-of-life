package de.socramob.gol.recursion;

import java.util.Map;
import java.util.Set;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public interface GridFillRecursion {
	public void fill(Map<Boolean, GridFillRecursion> gridFillRules, int startXCoordinate, int upperBound, Set<WorldDimension> dimensionList, Command command);
}
