package de.socramob.gol.recursion;

import java.util.Map;
import java.util.Set;

import de.socramob.gol.WorldDimension;

public interface FieldInitializerRecursion {
	public FieldInitializerRecursion initialize(int xCoordinate, int step, int upperBound,
			Set<WorldDimension> dimensionList, Map<Boolean, FieldInitializerRecursion> ruleMap);
}
