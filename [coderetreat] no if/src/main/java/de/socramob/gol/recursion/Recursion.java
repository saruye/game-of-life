package de.socramob.gol.recursion;

import java.util.Iterator;
import java.util.Map;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public abstract class Recursion {

	Command recursionCommand;

	Recursion(Command command) {
		this.recursionCommand = command;
	}

	abstract Recursion iterate(Map<Boolean, Recursion> recursionRuleMap, Iterator<WorldDimension> listIterator);
}
