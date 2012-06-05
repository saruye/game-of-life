package de.socramob.gol.recursion;

import java.util.Iterator;
import java.util.Map;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public class RecursionNext extends Recursion {

	RecursionNext(Command command) {
		super(command);
	}

	@Override
	public Recursion iterate(Map<Boolean, Recursion> recursionRuleMap, Iterator<WorldDimension> listIterator) {

		WorldDimension nextEntry = listIterator.next();

		super.recursionCommand.run(nextEntry);

		Recursion nextStep = recursionRuleMap.get(listIterator.hasNext());
		return nextStep.iterate(recursionRuleMap, listIterator);

	}

}
