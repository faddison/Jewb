package com.jewb;

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
		board.show();
		 

	}
}
