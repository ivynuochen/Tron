
import java.awt.*;
public class Player {

	private String name;
	private Color c;
	private int x, y;
	private int prevX, prevY;
	private int dir = 0;

	//Constructor
	public Player (String name, int x, int y, Color c)
	{
		this.name = name;
		this.c = c;

		this.x = x;
		this.y = y;

		this.prevX = x;
		this.prevY = y;

	}

	//Getters
	public String getName()
	{
		return this.name;
	}

	public Color getColor()
	{
		return this.c;
	}

	public int getX()
	{
		return this.x;
	}
	public int getPrevX()
	{
		return this.prevX;
	}

	public int getY()
	{
		return this.y;
	}
	public int getPrevY()
	{
		return this.prevY;
	}
	public int getDirection()
	{
		return this.dir;
	}

	//Setters
	public void setX(int x)
	{
		this.x = x;
	}
	public void setPrevX(int px)
	{
		this.prevX = px;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	public void setPrevY(int py)
	{
		this.prevY = py;
	}

	public void setDirection(int d) //Can only take values 1-4, add check later
	{
		this.dir = d;
	}
}