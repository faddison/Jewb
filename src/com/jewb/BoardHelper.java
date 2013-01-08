package com.jewb;

import java.awt.Point;

public class BoardHelper 
{
	public BoardHelper(){};
	
	public void show(Board board)
	{		
		System.out.println("[");
		for (int i = 0; i < board.getDimension(); i++)
		{
			for (int j = 0; j < board.getDimension(); j++)
			{
				System.out.print(board.getBoard()[i][j]);
				if (j < board.getDimension() - 1)
					System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("]");
	}
	
	public boolean swap(Board board, Point p1, Point p2)
	{
		return swap(board, p1.x, p1.y, p2.x, p2.y);
	}
	
	public boolean swap(Board board, int x1, int y1, int x2, int y2)
	{
		if (validPoint(board, x1, y1) && validPoint(board, x2, y2))
		{
			char[][] newBoard = board.getBoard();
			char tempChar = newBoard[x1][y1];
			newBoard[x1][y1] = newBoard[x2][y2];
			newBoard[x2][y2] = tempChar;
			board.setBoard(newBoard);
			return true;
		}
		else
			return false;
	}
	
	public boolean validPoint(Board board, Point p)
	{
		return validPoint(board, p.x, p.y);
	}
	
	public boolean validPoint(Board board, int x, int y)
	{
		return (x >= 0 &&
				x <= board.getDimension() - 1 &&
				y >= 0 &&
				y <= board.getDimension() - 1);
	}
	
}
