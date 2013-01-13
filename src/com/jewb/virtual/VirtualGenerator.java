package com.jewb.virtual;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jewb.core.Pair;

public class VirtualGenerator 
{
	private Random gen;
	
	public VirtualGenerator()
	{
		this.gen = new Random();
	}
	
	public Point getRandomLocation(VirtualBoard vboard)
	{
		int x = this.gen.nextInt(vboard.getDimension());
		int y = this.gen.nextInt(vboard.getDimension());
		return new Point(x, y);
	}
	
	public Point[] getRandomPair(VirtualBoard vboard)
	{
		Point first = getRandomLocation(vboard);
		Point second = getRandomAdjacent(vboard, first);
		return new Point[]{first, second};
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
