package com.jewb;

import java.util.Random;

public class Board 
{
	private char[][] board;
	private char[] tiles;
	private int dimension;
	
	public Board(int dimension, char[] tiles)
	{
		this.tiles = tiles;
		this.dimension = dimension;
		this.board = build(dimension, tiles);
	}
	
	public char[][] build(int dimension, char[] tiles)
	{
		Random gen = new Random();
		char[][] newBoard = new char[dimension][dimension];
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				int index = gen.nextInt(tiles.length);
				char tile = tiles[index];
				newBoard[i][j] = tile;
			}
		}
		return newBoard;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public char[] getTiles() {
		return tiles;
	}

	public void setTiles(char[] tiles) {
		this.tiles = tiles;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
}
