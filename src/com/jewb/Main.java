package com.jewb;

import java.awt.Point;
import java.util.Random;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int dimension = 8;
		char[] tiles = {'a','b','c','d','e','f','g'};
		
		Board board = new Board(dimension, tiles); 
		BoardHelper boardHelper = new BoardHelper();
		boardHelper.show(board);
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		boardHelper.swap(board, p1, p2);
		boardHelper.show(board);
		 

	}
}
