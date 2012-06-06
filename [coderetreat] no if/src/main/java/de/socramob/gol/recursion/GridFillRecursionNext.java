package de.socramob.gol.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public class GridFillRecursionNext implements GridFillRecursion {

	@Override
	public void fill(Map<Boolean, GridFillRecursion> gridFillRules, int startXCoordinate, int upperBound,
			Set<WorldDimension> dimensionList, Command command) {

		Map<Boolean, FieldInitializerRecursion> fieldInitializerRules = new HashMap<Boolean, FieldInitializerRecursion>();
		fieldInitializerRules.put(Boolean.TRUE, new FieldInitializerRecursionNext());
		fieldInitializerRules.put(Boolean.FALSE, new FieldInitializerRecursionExit());

		command.run(startXCoordinate, upperBound, fieldInitializerRules, dimensionList);

		startXCoordinate++;

		GridFillRecursion nextStep = gridFillRules.get(startXCoordinate <= upperBound);
		nextStep.fill(gridFillRules, startXCoordinate, upperBound, dimensionList, command);

	}

}
