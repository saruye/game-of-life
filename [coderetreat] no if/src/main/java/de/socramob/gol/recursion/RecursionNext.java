package de.socramob.gol.recursion;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.socramob.gol.Cell;
import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public class RecursionNext extends Recursion{

	RecursionNext(Command command) {
		super(command);
	}

	@Override
	public Recursion iterate(Map<Boolean, Recursion> recursionRuleMap,
			Iterator<Entry<WorldDimension, Cell>> listIterator) {

		Entry<WorldDimension, Cell> nextEntry = listIterator.next();

		super.recursionCommand.run(nextEntry);

		Recursion nextStep = recursionRuleMap.get(listIterator.hasNext());
		return nextStep.iterate(recursionRuleMap, listIterator);

	}



}
