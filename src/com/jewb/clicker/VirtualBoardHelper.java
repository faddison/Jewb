package com.jewb.clicker;

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
		this.gen = new Random();
		
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
	
	
	public Point getRandomLocation(VirtualBoard vboard)
	{
		int x = this.gen.nextInt(vboard.getDimension());
		int y = this.gen.nextInt(vboard.getDimension());
		return new Point(x, y);
	}
	
	public void clickRandomPair(VirtualBoard vboard)
	{
		Point[] points = getRandomPair(vboard);
		Point px = getCoordinate(vboard, points[0]);
		Point py = getCoordinate(vboard, points[1]);
		click(px, py);
	}
	
	public Point[] getRandomPair(VirtualBoard vboard)
	{
		Point first = getRandomLocation(vboard);
		Point second = getRandomAdjacent(vboard, first);
		return new Point[]{first, second};
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
	
	public List<Pair> shiftPairsRight(List<Pair> pairs)
	{
		return shiftPairs(pairs, 1, 0);
	}
	
	public List<Pair> shiftPairsLeft(List<Pair> pairs)
	{
		return shiftPairs(pairs, -1, 0);
	}
	
	public List<Pair> shiftPairsDown(List<Pair> pairs)
	{
		return shiftPairs(pairs, 0, 1);
	}
	
	public List<Pair> shiftPairsUp(List<Pair> pairs)
	{
		return shiftPairs(pairs, 0, -1);
	}
	
	
	
	public List<Pair> shiftPairs(List<Pair> pairs, int shiftX, int shiftY)
	{
		List<Pair> newPairs = new ArrayList<Pair>();
		for (Pair pair: pairs)
		{
			System.out.println("old pair:"+pair.toString());
			if (isPairDelay(pair))
				newPairs.add(pair);
			else
			{
				Point p1 = new Point(pair.getP1().x + shiftX, pair.getP1().y + shiftY);
				Point p2 = new Point(pair.getP2().x + shiftX, pair.getP2().y + shiftY);
				Pair newPair = new Pair(p1, p2);
				System.out.println("new pair :"+newPair);
				newPairs.add(newPair);
			}
		}
		return newPairs;
	}
	
	public List<Pair> invertPairs(List<Pair> pairs)
	{
		List<Pair> newPairs = new ArrayList<Pair>();
		for (Pair pair: pairs)
		{
			//System.out.println("old pair:"+pair.toString());
			if (isPairDelay(pair))
				newPairs.add(pair);
			else
			{
				Point p1 = new Point(pair.getP1().y, pair.getP1().x);
				Point p2 = new Point(pair.getP2().y, pair.getP2().x);
				Pair newPair = new Pair(p1, p2);
				//System.out.println("new pair :"+newPair);
				newPairs.add(newPair);
			}
		}
		return newPairs;
	}
	
	public void pairShiftingStrategy(VirtualBoard vboard, List<Pair> pairs, int delay) throws InterruptedException
	{
		clickPairs(vboard, pairs, delay);
		clickPairs(vboard, shiftPairsRight(pairs), delay);
		clickPairs(vboard, invertPairs(pairs), delay);
		clickPairs(vboard, shiftPairsDown(invertPairs(pairs)), delay);
	}
	
	public void clickPairs(VirtualBoard vboard, List<Pair> pairs, int delay) throws InterruptedException
	{
		for (Pair pair: pairs)
		{
			if (isPairDelay(pair))
				Thread.sleep(delay);
			else
			{
				if (validPoint(vboard, pair))
				click(getCoordinate(vboard, pair));
			}
		}
	}
	
	private boolean isPairDelay(Pair p)
	{
		return (p.getX1() == -1 && p.getX2() == -1 && p.getY1() == -1 && p.getY2() == -1);
	}

	public Point getRandomAdjacent(VirtualBoard vboard, Point p)
	{
		List<Point> points = getAdjacentLocations(vboard, p);
		return points.get(this.gen.nextInt(points.size()));
	}
	
	public List<Point> getAdjacentLocations(VirtualBoard vboard, Point p)
	{
		List<Point> adjPoints = new ArrayList<Point>();
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(p.x, p.y - 1));
		points.add(new Point(p.x, p.y + 1));
		points.add(new Point(p.x + 1, p.y));
		points.add(new Point(p.x - 1, p.y));
		for (Point point: points)
		{
			if (validPoint(vboard, point))
				adjPoints.add(point);
		}
		return adjPoints;
	}
	
	private boolean validPoint(VirtualBoard board, Point p)
	{
		return validPoint(board, p.x, p.y);
	}
	
	private boolean validPoint(VirtualBoard board, int x, int y)
	{
		return (x >= 0 &&
				x <= board.getDimension() - 1 &&
				y >= 0 &&
				y <= board.getDimension() - 1);
	}
	
	private boolean validPoint(VirtualBoard vboard, Pair pair)
	{
		return (validPoint(vboard, pair.getP1()) && validPoint(vboard, pair.getP2()));
	}
	
	
}
