package com.jewb.clicker;

import java.awt.Point;

import com.jewb.solver.Board;
import com.jewb.solver.BoardHelper;

public class ClickerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		
		int moves = 1000;
		int delay = 50;
		
		int dimension = 8;
		int tileSize = 68;
		Point firstTile = new Point(506, 171);
		
		VirtualBoard vboard = new VirtualBoard(dimension, firstTile, tileSize);
		
		VirtualBoardHelper vboardHelper = new VirtualBoardHelper();
		
		System.out.println("Waiting 3 seconds...");
		Thread.sleep(3000);
		System.out.println("Starting Application.");
		for (int i = 0; i < moves; i++)
		{
			vboardHelper.clickRandomPair(vboard);
			Thread.sleep(delay);
		}
		System.out.println("Application Finished.");

	}

}
