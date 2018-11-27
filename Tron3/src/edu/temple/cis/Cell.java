package edu.temple.cis;

import java.awt.*;

public class Cell 
{
	protected Player p = null;
	//this is default background color. Should probably fine better way to set this in the future
	protected Color c = Color.LIGHT_GRAY;
	
	public Cell()
	{

	}
	
	/*
	 * The following getters and setter my not be necessary.
	 * Using protected variables allows Screen and Row to access
	 * these values.
	 */
	
	
	public void setEmpty()
	{
		this.p = null;
		this.c = null;
	}
	
	//get/set Color
	public Color getColor()
	{
		return c;
	}
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	//get/set Player
	public Player getPlayer()
	{
		return p;
	}
	public void setPlayer(Player p)
	{
		this.p = p;
	}
}
