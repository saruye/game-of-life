package de.socramob.gol;
import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Test;


public class WorldTest {

	@Test
	public void createWorld_CanAddCells() throws Exception {
		World world = new World();
		world.addCell(new Cell(),new Dimension(1, 1));
		assertEquals(1, world.getLivingCellCount());
		
	}
	@Test
	public void createWorld_CanAddTwoCells() throws Exception {
		World world = new World();
		world.addCell(new Cell(),new Dimension(1, 1));
		world.addCell(new Cell(),new Dimension(1, 2));
		assertEquals(2, world.getLivingCellCount());
		
	}
	@Test
	public void getNumberOfLivingNeigbours_shouldBeOne() throws Exception {
		World world = new World();
		world.addCell(new Cell(),new Dimension(1, 1));
		world.addCell(new Cell(),new Dimension(1, 2));
		assertEquals(1, world.getLivingNeigbourCount(new Dimension(1, 1)));
		
		
	}
	@Test
	public void getNumberOfLivingNeigbours_shouldBeZero() throws Exception {
		World world = new World();
		world.addCell(new Cell(),new Dimension(1, 2));
		assertEquals(0, world.getLivingNeigbourCount(new Dimension(1, 2)));
		
		
	}
	
	@Test
	public void getNumberOfLivingNeigbours_shouldBeZeroBecauseCellisDead() throws Exception {
		World world = new World();
		Cell deadCell = new Cell();
		deadCell.die();
		world.addCell(deadCell,new Dimension(1, 2));
		assertEquals(0, world.getLivingNeigbourCount(new Dimension(1, 1)));
		
	}
	
	@Test
	public void testGenerateNextGeneration_shouldHaveNooneAlive() throws Exception {
		World world = new World();
		Cell cell = new Cell();
		world.addCell(cell, new Dimension(1,2));
		world.nextGeneration(); 
		assertEquals(0, world.getLivingCellCount());
	}
	
	@Test
	public void testGenerateNextGeneration_shouldHaveOneAlive() throws Exception {
		World world = new World();
		Cell cell = new Cell();
		world.addCell(cell, new Dimension(1,1));
		world.addCell(new Cell(), new Dimension(1,2));
		world.addCell(new Cell(), new Dimension(0,0));
		world.nextGeneration(); 
		assertEquals(1, world.getLivingCellCount());
	}
}
