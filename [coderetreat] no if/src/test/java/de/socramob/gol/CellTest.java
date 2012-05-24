package de.socramob.gol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	private Cell cell;

	@Before
	public void setUp() {
		this.cell = new Cell();

	}

	@Test
	public void createCell_DefaultCellShouldBeAlive()
		throws Exception {
		assertTrue(this.cell.isAlive());
	}

	@Test
	public void cell_could_die()
		throws Exception {
		this.cell.die();
		assertFalse(this.cell.isAlive());
	}

	@Test
	public void testLiving_StaysAliveWithTwoLivingNeighbors()
		throws Exception {
		this.cell.incNeighbourCount(2);
		this.cell.nextGeneration();
		assertTrue(this.cell.isAlive());
	}

	@Test
	public void testLivingDiesWithOneNeigbour()
		throws Exception {
		this.cell.incNeighbourCount(1);
		this.cell.nextGeneration();
		assertFalse(this.cell.isAlive());
	}

	@Test
	public void testLiving_DiesWithFourNeigbour()
		throws Exception {
		this.cell.incNeighbourCount(4);
		this.cell.nextGeneration();
		assertFalse(this.cell.isAlive());

	}

	@Test
	public void testDead_RevivesWith3Neigbour()
		throws Exception {
		this.cell.die();
		this.cell.incNeighbourCount(3);
		this.cell.nextGeneration();
		assertTrue(this.cell.isAlive());

	}

	@Test
	public void testDead_StaysDeadWith2Neigbour()
		throws Exception {
		this.cell.die();
		this.cell.incNeighbourCount(2);
		this.cell.nextGeneration();
		assertFalse(this.cell.isAlive());

	}

	@Test
	public void testDead_StaysDeadWith4Neigbour()
		throws Exception {
		this.cell.die();
		this.cell.incNeighbourCount(4);
		this.cell.nextGeneration();
		assertFalse(this.cell.isAlive());

	}

	@Test
	public void testCell_GetAliveValue()
		throws Exception {
		assertEquals(1, this.cell.getAliveValue());
	}

}
