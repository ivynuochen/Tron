import java.awt.*;
public class Player {
	
	private String name;
	private Color c;
	private int score;
	
	public Player(String name, Color c)
	{
		//Needs to check for exceptions
		this.name = name;
		this.c = c;
	}
	public Color getColor()		//returns the color of the player
	{
		return this.c;
	}
	
	public Color setColor(Color color)		//sets the color of the player after instantiation
	{
		this.c = color; 
	}
	
}
