package de.socramob.gol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
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
		Set<WorldDimension> gridFields = grid.getFields();
		assertEquals(49, gridFields.size());

		grid = new Grid();
		grid.putCellToDimension(new WorldDimension(3, 1), new Cell());
		gridFields = grid.getFields();
		assertEquals(36, gridFields.size());

		grid = new Grid();
		grid.putCellToDimension(new WorldDimension(2, 3), new Cell());
		gridFields = grid.getFields();
		assertEquals(36, gridFields.size());
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

	@Test
	public void testGetFieldIterator() throws Exception {
		grid.putCellToDimension(new WorldDimension(4, 4), new Cell());
		Iterator<WorldDimension> gridFields = grid.getFieldIterator();

		assertTrue(gridFields.hasNext());
	}

	@Test
	public void testGetGridSize() throws Exception {
		Grid newGrid = new Grid();
		newGrid.putCellToDimension(new WorldDimension(1, 1), new Cell());
		newGrid.putCellToDimension(new WorldDimension(2, 3), new Cell());
		newGrid.putCellToDimension(new WorldDimension(5, 0), new Cell());

		assertEquals(64, newGrid.getFieldSize());

		newGrid.putCellToDimension(new WorldDimension(3, 6), new Cell());
		assertEquals(81, newGrid.getFieldSize());
	}
}
