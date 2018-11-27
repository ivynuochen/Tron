package edu.temple.cis;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class PlayTron extends JPanel implements ActionListener, KeyListener {

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

	//variables added by Ivy 26 Nov 2018
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	//

	//Not used right now, maybe delete later
	private int xStart, xEnd, yStart, yEnd;
	private Player player1 = new Player("Brian", Color.red);

	public PlayTron()
	{
		playScreen = new Screen(width, cellsPerSide);
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Container can = frame.getContentPane();



		can.add(this, BorderLayout.CENTER);

		addKeyListener(this);

		//To be set to false after debugging
		frame.setResizable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, width));
		frame.pack();		
		inset = frame.getInsets();
		
		//vv bad code, fix later
		frame.setPreferredSize(new Dimension(width + inset.left + inset.right + 1, width + inset.top + inset.bottom + 1));
		frame.pack();

		//drawTrail(0, 0, Color.PINK);
		//drawTrail(1, 0, Color.BLUE);
		//drawTrail(0, 1, Color.RED);
		//drawTrail(3, 3, Color.GREEN);

		//INC 26 Nov 2018
		drawTrail(0,0, player1.getColor());


		frame.setVisible(true);
		gameLoop();
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.setColor(Color.LIGHT_GRAY);
		System.out.println("Insets {top, bot, left, right} " + inset.top + " " + inset.bottom + " " + inset.left + " " + inset.right);
		g.setColor(color1);
		g.fillRect(rectLocX1, rectLocY1, cellWidth, cellWidth);
		
		for(int x = 0; x < cellsPerSide; x++) {
			for (int y = 0; y < cellsPerSide; y++) {
				g.setColor(playScreen.getColor(x, y));
				g.fillRect(getGridLocX(x), getGridLocY(y), cellWidth, cellWidth);

			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(right)
		{
			player1.setXpos(player1.getXpos()+1);
			drawTrail(player1.getXpos(), player1.getYpos(), player1.getColor());
		}
		/*if(left)
		{

		}
		if(up)
		{

		}
		if(down)
		{

		}
		*/
		repaint();
	}

	public void keyPressed(KeyEvent e)
	{

	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}

	private void gameLoop()
	{
		while(true)
		{

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.repaint();
			System.out.println("gameloop runs");
		}
	}

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
