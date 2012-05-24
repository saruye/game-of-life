package de.socramob.gol;

import static org.junit.Assert.*;

import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridTest {

	Grid grid;

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFields() throws Exception {
		grid.putCellToDimension(new WorldDimension(4, 4), new Cell());
		Set<Entry<WorldDimension, Cell>> gridFields = grid.getFields();
		assertEquals(1, gridFields.size());
	}

	@Test
	public void testGetCell() throws Exception {
		WorldDimension dim = new WorldDimension(4, 4);
		grid.putCellToDimension(dim, new Cell());
		Cell currentCell = grid.getCell(dim);
		assertNotNull(currentCell);

		currentCell = grid.getCell(new WorldDimension(4, 4));
		assertNotNull(currentCell);
	}

}
