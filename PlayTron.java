import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PlayTron extends JPanel implements ActionListener {

	private JFrame frame;
	private Insets inset;
	
	private Screen playScreen;
	
	//width must be divisible by cellsPerSide
	private static  int width = 700;
	private static  int cellsPerSide = 7;
	private static int cellWidth = width / cellsPerSide;
	
	//Variables for paintComponent
	private Color color1;
	private Color color2;
	private int rectLocX1;
	private int rectLocY1;
	
	
	//Not used right now, maybe delete later
	private int xStart, xEnd, yStart, yEnd;
	
	public PlayTron()
	{
		playScreen = new Screen(width, cellsPerSide);
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Container can = frame.getContentPane();

		can.add(this, BorderLayout.CENTER);

		//To be set to false after debugging
		frame.setResizable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, width));
		frame.pack();		
		inset = frame.getInsets();
		
		//vv bad code, fix later
		frame.setPreferredSize(new Dimension(width + inset.left + inset.right + 1, width + inset.top + inset.bottom + 1));
		frame.pack();

		frame.setVisible(true);
		
		drawTrail(0, 0, Color.PINK);
		drawTrail(1, 0, Color.BLUE);
		drawTrail(0, 1, Color.RED);
		drawTrail(3, 3, Color.GREEN);

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.setColor(Color.LIGHT_GRAY);
		
		System.out.println("Insets {top, bot, left, right} " + inset.top + " " + inset.bottom + " " + inset.left + " " + inset.right);
		
		g.setColor(color1);
		g.fillRect(rectLocX1, rectLocY1, cellWidth, cellWidth);
		
		for(int x = 0; x < cellsPerSide; x++)
		{
			for(int y = 0; y < cellsPerSide; y++)
			{
				g.setColor(playScreen.getColor(x, y));
				g.fillRect(getGridLocX(x), getGridLocY(y), cellWidth, cellWidth);
				
			}
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	//values must be 0 <= {x or y} <= 6
	//Test for this later
	private void drawTrail(int x, int y, Color c)
	{	
		playScreen.setColor(x, y, c);
		/*
		color1 = c;
		rectLocX1 = getGridLocX(x);
		rectLocY1 = getGridLocY(y);
		*/
		
		//repaint to draw the rectangle in paint component
		repaint();
	}
	
	//These lines are not used yet
	//The purpose is to convert array indexes to grip locations in pixels
	private int getGridLocX(int cellLocX)
	{
		int locX = cellLocX * cellWidth;
		return locX;
	}
	private int getGridLocY(int cellLocY)
	{
		int locY = cellLocY * cellWidth;
		return locY;
	}
	
	public static void main(String[] args)
	{
		new PlayTron();
		
	}
}
