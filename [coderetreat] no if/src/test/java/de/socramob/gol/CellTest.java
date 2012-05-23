package de.socramob.gol;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	private Cell cell;
	@Before
	public void setUp(){
		cell = new Cell();

	}

	@Test
	public void createCell_DefaultCellShouldBeAlive() throws Exception {
		assertTrue(cell.isAlive());
	}

	@Test
	public void cell_could_die() throws Exception {
		cell.die();
		assertFalse(cell.isAlive());
	}

	@Test
	public void testLiving_StaysAliveWithTwoLivingNeighbors() throws Exception {
		cell.nextGeneration(2);
		assertTrue(cell.isAlive());
	}

	@Test
	public void testLivingDiesWithOneNeigbour() throws Exception {
		cell.nextGeneration(1);
		assertFalse(cell.isAlive());
	}

	@Test
	public void testLiving_DiesWithFourNeigbour() throws Exception {
		cell.nextGeneration(4);
		assertFalse(cell.isAlive());

	}
	@Test
	public void testDead_RevivesWith3Neigbour() throws Exception {
		cell.die();
		cell.nextGeneration(3);
		assertTrue(cell.isAlive());

	}
	@Test
	public void testDead_StaysDeadWith2Neigbour() throws Exception {
		cell.die();
		cell.nextGeneration(2);
		assertFalse(cell.isAlive());

	}
	@Test
	public void testDead_StaysDeadWith4Neigbour() throws Exception {
		cell.die();
		cell.nextGeneration(4);
		assertFalse(cell.isAlive());

	}

	@Test
	public void testCell_GetAliveValue() throws Exception {
		assertEquals(1, cell.getAliveValue());
	}

}
