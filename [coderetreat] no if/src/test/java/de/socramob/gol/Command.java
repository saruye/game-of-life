package de.socramob.gol;

import java.util.LinkedList;

public class Command {

	public void run(Cell cell, Cell neigbourCell, final LinkedList<WorldDimension> neigbourList) {
	};

	static Command getEmptyCommand() {
		return new Command();
	}

}