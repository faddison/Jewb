package com.jewb.virtual;

import java.awt.Point;
import java.util.List;

public class VirtualBoard
{
	private Point firstTile;
	private int tileSize;
	private int dimension;
	private Point[][] tileLocations;
	
	public VirtualBoard(int dimension, Point firstTile, int tileSize)
	{
		this.dimension = dimension;
		this.firstTile = firstTile;
		this.tileSize = tileSize;
		this.tileLocations = build(dimension, firstTile, tileSize);
	}
	
	private Point[][] build(int dimension, Point firstTile, int tileSize)
	{
		Point[][] tileLocations = new Point[8][8];
		for (int i = 0, px = firstTile.x; px < firstTile.x + dimension * tileSize; i++, px += tileSize)
		{
			for (int j = 0, py = firstTile.y; py < firstTile.y + dimension * tileSize; j++, py += tileSize)
			{
				tileLocations[i][j] = new Point(px, py);
			}
		}
		return tileLocations;
	}

	public Point getFirstTile() {
		return firstTile;
	}

	public void setFirstTile(Point firstTile) {
		this.firstTile = firstTile;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public Point[][] getTileLocations() {
		return tileLocations;
	}

	public void setTileLocations(Point[][] tileLocations) {
		this.tileLocations = tileLocations;
	}

}
