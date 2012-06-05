package de.socramob.gol.recursion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.socramob.gol.Command;
import de.socramob.gol.WorldDimension;

public class ListIterator {
	protected Map<Boolean, Recursion> nextIteratorStep = null;

	public ListIterator(Command command) {
		nextIteratorStep = new HashMap<Boolean, Recursion>();

		nextIteratorStep.put(Boolean.TRUE, new RecursionNext(command));
		nextIteratorStep.put(Boolean.FALSE, new RecursionExit(command));

	}

	public void iterate(Iterator<WorldDimension> listIterator) {
		Recursion nextStep = this.nextIteratorStep.get(listIterator.hasNext());
		nextStep.iterate(nextIteratorStep, listIterator);
	}
}
