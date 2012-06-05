package de.socramob.gol;

import java.awt.Dimension;

public class WorldDimension
	extends Dimension {

	private static final long serialVersionUID = -5358293046121047701L;

	public WorldDimension(int width, int height) {
		super(width, height);
	}

	public int stepWest() {
		return this.width - 1;
	}

	public int stepEast() {
		return this.width + 1;
	}

	public int stepNorth() {
		return this.height + 1;
	}

	public int stepSouth() {
		return this.height - 1;
	}

	public static WorldDimension createNorthWest(WorldDimension origin) {
		return new WorldDimension(origin.stepWest(), origin.stepNorth());
	}

	public static WorldDimension createNorth(WorldDimension origin) {
		return new WorldDimension(origin.width, origin.stepNorth());
	}

	public static WorldDimension createNorthEast(WorldDimension origin) {
		return new WorldDimension(origin.stepEast(), origin.stepNorth());
	}

	public static WorldDimension createEast(WorldDimension origin) {
		return new WorldDimension(origin.stepEast(), origin.height);
	}

	public static WorldDimension createSouthEast(WorldDimension origin) {
		return new WorldDimension(origin.stepEast(), origin.stepSouth());
	}

	public static WorldDimension createSouth(WorldDimension origin) {
		return new WorldDimension(origin.width, origin.stepSouth());
	}

	public static WorldDimension createSouthWest(WorldDimension origin) {
		return new WorldDimension(origin.stepWest(), origin.stepSouth());
	}

	public static WorldDimension createWest(WorldDimension origin) {
		return new WorldDimension(origin.stepWest(), origin.height);
	}
}
