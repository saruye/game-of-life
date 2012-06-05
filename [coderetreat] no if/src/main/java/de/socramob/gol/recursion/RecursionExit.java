package de.socramob.gol.recursion;

import java.util.Iterator;
import java.util.Map;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public class RecursionExit extends Recursion {

	RecursionExit(Command command) {
		super(command);
	}

	@Override
	public Recursion iterate(Map<Boolean, Recursion> recursionRuleMap, Iterator<WorldDimension> listIterator) {
		return null;
	}

}
