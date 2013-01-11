package com.jewb.clicker;

import java.awt.Point;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.jewb.control.GlobalKeyListenerExample;
import com.jewb.core.Pair;
import com.jewb.solver.Board;
import com.jewb.solver.BoardHelper;
import com.jewb.util.FileReader;

public class ClickerMain {
	
	static GlobalKeyListenerExample gkey;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		keySetup();
		jewbSetup();
	}
	
	public static void jewbSetup() throws InterruptedException
	{
	
		int moves = -1;
		int delay = 700;
		
		int dimension = 8;
		//int tileSize = 57; // popcap
		int tileSize = 40; // facebook
		
		Point firstTile = new Point(315, 255);
		
		VirtualBoard vboard = new VirtualBoard(dimension, firstTile, tileSize);
		
		VirtualBoardHelper vboardHelper = new VirtualBoardHelper();
		
		System.out.println("Waiting 3 seconds...");
		Thread.sleep(3000);
		System.out.println("Starting Application.");
		
		FileReader reader = new FileReader();
		List<Pair> pairs = reader.readPairs("C:/Users/user/Documents/jewblist2.txt");
		
		for (int i = 0; i != moves; i++)
		{
			if (gkey.getShouldRun())
			{
				vboardHelper.pairShiftingStrategy(vboard, pairs, delay);
				//vboardHelper.clickPairs(vboard, pairs, delay);
				//vboardHelper.clickRandomPair(vboard);
				//vboardHelper.clickSequentialPairs(vboard, 0, false);
			}
			else
				Thread.sleep(delay);
		}
		
		System.out.println("Application Finished.");
	}
	
	public static void keySetup()
	{
		try 
		{
            GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) 
		{
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
		}

		gkey = new GlobalKeyListenerExample();
		//Construct the example object and initialze native hook.
		GlobalScreen.getInstance().addNativeKeyListener(gkey);
	}

}
