package com.jewb.virtual;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.jewb.core.Pair;
import com.jewb.solver.Board;
import com.jewb.util.FileReader;

public class VirtualBoardHelper 
{
	private Robot robot;
	private Random gen;
	
	public VirtualBoardHelper()
	{
		try 
		{
			this.robot = new Robot();
		} 
		catch (AWTException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickSequentialPairs(VirtualBoard vboard, int delay, boolean reverse) throws InterruptedException
	{
		List<Point> points = new ArrayList<Point>();
		for (int i = 0, ir = vboard.getDimension() - 1; i < vboard.getDimension(); i++, ir--)
		{
			for (int j = 0, jr = vboard.getDimension() - 1; j < vboard.getDimension(); j++, jr--)
			{
				if (reverse)
				{
					if (ir % 2 == 1)
					{
						points.add(getCoordinate(vboard, new Point(ir,jr)));
						points.add(getCoordinate(vboard, new Point(ir-1,jr)));
					}
				}
				else
				{	
					if (i % 2 == 0)
					{
						points.add(getCoordinate(vboard, new Point(i,j)));
						points.add(getCoordinate(vboard, new Point(i+1,j)));
					}
				}	
			}
		}
		for (int i = 0; i < points.size(); i+=2)
		{
			click(points.get(i), points.get(i+1));
			Thread.sleep(delay);
		}
	}
	
	public Point getCoordinate(VirtualBoard vboard, Point p)
	{
		return getCoordinate(vboard, p.x, p.y);
	}
	
	public Point getCoordinate(VirtualBoard vboard, int x, int y)
	{
		return vboard.getTileLocations()[x][y];
	}
	
	public Pair getCoordinate(VirtualBoard vboard, Pair pair)
	{
		Point p1 = getCoordinate(vboard, pair.getP1());
		Point p2 = getCoordinate(vboard, pair.getP2());
		return new Pair(p1, p2);
	}
	
	public void click(Point p1, Point p2, int delay)
	{
		click(p1);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(p2);
	}
	
	public void click(Pair p)
	{
		click(p.getP1(), p.getP2());
	}
	
	public void click(Point p1, Point p2)
	{
		click(p1);
		click(p2);
	}
	
	public void click(Point p)
	{
		this.robot.mouseMove(p.x, p.y);
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
		System.out.println("Clicked "+p.x+", "+p.y);
	}
	
	public void clickPairs(VirtualBoard vboard, List<Pair> pairs) throws InterruptedException
	{
		clickPairs(vboard, pairs, 0);
	}
	
	public void clickPairs(VirtualBoard vboard, List<Pair> pairs, int delay) throws InterruptedException
	{
		for (Pair pair: pairs)
		{
			if (isPairDelay(pair))
				Thread.sleep(delay);
			else
			{
				click(getCoordinate(vboard, pair));
			}
		}
	}
	
	private boolean isPairDelay(Pair p)
	{
		return (p.getX1() == -1 && p.getX2() == -1 && p.getY1() == -1 && p.getY2() == -1);
	}
	
	

	
	
	
	
	
}
