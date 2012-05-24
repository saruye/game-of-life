package de.socramob.gol;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldDimensionTest {

	WorldDimension worldDimension;

	@Before
	public void setUp() throws Exception {
		worldDimension = new WorldDimension(12, 13);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWorldDimension_StepNorth() {
		assertEquals(worldDimension.height + 1, worldDimension.stepNorth());
	}

	@Test
	public void testWorldDimension_StepEast() {
		assertEquals(worldDimension.width + 1, worldDimension.stepEast());
	}

	@Test
	public void testWorldDimension_StepSouth() {
		assertEquals(worldDimension.height - 1, worldDimension.stepSouth());
	}

	@Test
	public void testWorldDimension_StepWest() {
		assertEquals(worldDimension.width - 1, worldDimension.stepWest());
	}

	@Test
	public void testWorldDimension_NewNorthWestDim() throws Exception {
		WorldDimension northWest = WorldDimension.createNorthWest(worldDimension);
		assertEquals(worldDimension.stepWest(), northWest.width);
		assertEquals(worldDimension.stepNorth(), northWest.height);
	}

	@Test
	public void testWorldDimension_NewNorthDim() throws Exception {
		WorldDimension north = WorldDimension.createNorth(worldDimension);
		assertEquals(worldDimension.width, north.width);
		assertEquals(worldDimension.stepNorth(), north.height);
	}

	@Test
	public void testWorldDimension_NewNorthEastDim() throws Exception {
		WorldDimension northEast = WorldDimension.createNorthEast(worldDimension);
		assertEquals(worldDimension.stepEast(), northEast.width);
		assertEquals(worldDimension.stepNorth(), northEast.height);
	}

	@Test
	public void testWorldDimension_NewEastDim() throws Exception {
		WorldDimension east = WorldDimension.createEast(worldDimension);
		assertEquals(worldDimension.stepEast(), east.width);
		assertEquals(worldDimension.height, east.height);

	}

	@Test
	public void testWorldDimension_NewSouthEastDim() throws Exception {
		WorldDimension southEast = WorldDimension.createSouthEast(worldDimension);
		assertEquals(worldDimension.stepEast(), southEast.width);
		assertEquals(worldDimension.stepSouth(), southEast.height);

	}

	@Test
	public void testWorldDimension_NewSouthDim() throws Exception {
		WorldDimension south = WorldDimension.createSouth(worldDimension);
		assertEquals(worldDimension.width, south.width);
		assertEquals(worldDimension.stepSouth(), south.height);

	}

	@Test
	public void testWorldDimension_NewSouthWestDim() throws Exception {
		WorldDimension southWest = WorldDimension.createSouthWest(worldDimension);
		assertEquals(worldDimension.stepWest(), southWest.width);
		assertEquals(worldDimension.stepSouth(), southWest.height);

	}

	@Test
	public void testWorldDimension_NewWestDim() throws Exception {
		WorldDimension west = WorldDimension.createWest(worldDimension);
		assertEquals(worldDimension.stepWest(), west.width);
		assertEquals(worldDimension.height, west.height);


	}

}
