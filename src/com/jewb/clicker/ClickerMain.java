package com.jewb.clicker;

import java.awt.Point;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.jewb.control.GlobalKeyListenerExample;
import com.jewb.solver.Board;
import com.jewb.solver.BoardHelper;

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
		int delay = 50;
		
		int dimension = 8;
		int tileSize = 57;
		Point firstTile = new Point(532, 225);
		
		VirtualBoard vboard = new VirtualBoard(dimension, firstTile, tileSize);
		
		VirtualBoardHelper vboardHelper = new VirtualBoardHelper();
		
		System.out.println("Waiting 3 seconds...");
		Thread.sleep(3000);
		System.out.println("Starting Application.");
		
		for (int i = 0; i != moves; i++)
		{
			if (gkey.getShouldRun())
				run(vboardHelper, vboard);
			Thread.sleep(delay);
		}
		
		System.out.println("Application Finished.");
	}
	
	public static void run(VirtualBoardHelper vboardHelper, VirtualBoard vboard)
	{
		vboardHelper.clickRandomPair(vboard);
		//vboardHelper.clickSequentialPairs(vboard, 0, false);
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
