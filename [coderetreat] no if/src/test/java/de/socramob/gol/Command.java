package de.socramob.gol;

import java.util.LinkedList;
import java.util.Map.Entry;

public class Command {

	public void run(Cell cell, Cell neigbourCell, final LinkedList<WorldDimension> neigbourList) {
	};

	static Command getEmptyCommand() {
		return new Command();
	}

	public void run(Entry<WorldDimension, Cell> gridField) {

	}

}