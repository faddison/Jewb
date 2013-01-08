package com.jewb;

import java.util.Random;

public class Board 
{
	public char[][] board;
	public char[] tiles;
	public int dimension;
	
	public Board(int dimension, char[] tiles)
	{
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
	
	public void show()
	{
		System.out.println("[");
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				System.out.print(this.board[i][j]);
				if (j < dimension - 1)
					System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("]");
	}
	
	
}
