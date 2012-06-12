package de.socramob.gol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WorldTest {

	@Test
	public void createWorld_CanAddCells() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 1));
		assertEquals(1, world.getLivingCellCount());

	}

	@Test
	public void createWorld_CanAddTwoCells() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 1));
		world.addCell(new Cell(), new WorldDimension(1, 2));
		assertEquals(2, world.getLivingCellCount());

	}

	@Test
	public void testAddLivingCell_CellShouldBeAlive() throws Exception {
		World world = new World();
		WorldDimension dimension = new WorldDimension(1, 1);
		world.addCell(new Cell(), dimension);
		assertTrue(world.cellIsAliveAtPosition(dimension));
	}

	@Test
	public void testGenerateNextGeneration_shouldDieWithLessThan2Neigbours() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 2));
		world.nextGeneration();
		assertEquals(0, world.getLivingCellCount());
	}

	@Test
	public void testGenerateNextGeneration_shouldStayAliveWith2Neigbours() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(0, 1));
		world.addCell(new Cell(), new WorldDimension(1, 1));
		world.addCell(new Cell(), new WorldDimension(2, 2));
		world.nextGeneration();
		assertEquals(2, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(1, 1)).isAlive());
	}

	@Test
	public void testGenerateNextGeneration_shouldStayAliveWith3Neigbours() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 0));
		world.addCell(new Cell(), new WorldDimension(3, 0));
		world.addCell(new Cell(), new WorldDimension(2, 1));
		world.addCell(new Cell(), new WorldDimension(3, 2));
		world.nextGeneration();

		assertEquals(3, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(3, 1)).isAlive());
	}

	@Test
	public void testGenerateNextGeneration_shouldBeDieWithMoreThen3Neigbours() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 0));
		world.addCell(new Cell(), new WorldDimension(3, 0));
		world.addCell(new Cell(), new WorldDimension(2, 1));
		world.addCell(new Cell(), new WorldDimension(3, 2));
		world.addCell(new Cell(), new WorldDimension(1, 2));
		world.nextGeneration();

		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(3, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 2)).isAlive());
		assertFalse(world.grid.getCell(new WorldDimension(2, 1)).isAlive());
		assertEquals(4, world.getLivingCellCount());
	}

	@Test
	public void testGenerateNextGeneration_GenerationMix() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(1, 0));
		world.addCell(new Cell(), new WorldDimension(1, 1));
		world.addCell(new Cell(), new WorldDimension(2, 1));
		world.addCell(new Cell(), new WorldDimension(2, 2));
		world.nextGeneration();
		assertEquals(6, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(1, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 2)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 2)).isAlive());

		world.nextGeneration();
		assertEquals(6, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(1, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(0, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(3, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 2)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 2)).isAlive());

		world.nextGeneration();
		assertEquals(6, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(1, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(0, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(3, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 2)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 2)).isAlive());

		world.nextGeneration();
		assertEquals(6, world.getLivingCellCount());
		assertTrue(world.grid.getCell(new WorldDimension(1, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 0)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(0, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(3, 1)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(1, 2)).isAlive());
		assertTrue(world.grid.getCell(new WorldDimension(2, 2)).isAlive());
	}

	@Test
	public void testGenerateNextGeneration_oneShouldBeRevived() throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(0, 2));
		world.addCell(new Cell(), new WorldDimension(2, 2));
		world.addCell(new Cell(), new WorldDimension(0, 0));
		world.nextGeneration();
		assertEquals(1, world.getLivingCellCount());
	}

	@Test
	public void testGenerateNextGeneration_oneShouldBeRevived_oneShouldBeBornWithThreeLivingNeighbors()
			throws Exception {
		World world = new World();
		world.addCell(new Cell(), new WorldDimension(0, 0));
		world.addCell(new Cell(), new WorldDimension(0, 1));
		world.addCell(new Cell(), new WorldDimension(1, 0));
		world.nextGeneration();
		assertEquals(4, world.getLivingCellCount());
	}
}
