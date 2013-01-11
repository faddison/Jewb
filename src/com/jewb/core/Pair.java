package com.jewb.core;

import java.awt.Point;

public class Pair {
	
	private Point p1;
	private Point p2;
	
	public Pair(Point p1, Point p2)
	{
		this.setP1(p1);
		this.setP2(p2);
	}
	
	public Pair(int x1, int y1, int x2, int y2)
	{
		this(new Point(x1,y1), new Point(x2,y2));
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	public int getX1()
	{
		return getP1().x;
	}
	
	public int getX2()
	{
		return getP2().x;
	}
	
	public int getY1()
	{
		return getP1().y;
	}
	
	public int getY2()
	{
		return getP2().y;
	}
	
	
}
