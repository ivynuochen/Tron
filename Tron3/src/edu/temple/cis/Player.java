package edu.temple.cis;

import java.awt.*;
public class Player {
	
	private String name;
	private Color c;
	private int score;
	private int xpos, ypos;

	public Color getColor()
	{
		return this.c;
	}

	public int getXpos()
	{
		return this.xpos;
	}

	public int getYpos()
	{
		return this.ypos;
	}

	public void setXpos(int x)
	{
		xpos = x;
	}
	public void setYpos(int y)

	{
		ypos = y;
	}

	public Player(String name, Color c)
	{
		//Needs to check for exceptions
		this.name = name;
		this.c = c;
	}



}
