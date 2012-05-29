package de.socramob.gol.recursion;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.socramob.gol.Cell;
import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public abstract class Recursion {

	Command recursionCommand;

	Recursion(Command command) {
		this.recursionCommand = command;
	}

	abstract Recursion iterate(Map<Boolean, Recursion> recursionRuleMap,
			Iterator<Entry<WorldDimension, Cell>> listIterator);
}
