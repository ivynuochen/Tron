import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class PlayTron extends JPanel implements ActionListener, KeyListener
{
	private JFrame frame;
	private Insets inset;
	
	private Screen playScreen;
	
	//width must be divisible by cellsPerSide
	private static  int width = 700;
	private static  int cellsPerSide = 35;
	private static int cellWidth = width / cellsPerSide;
	
	private Player player1 , player2;

	public PlayTron()
	{		
		playScreen = new Screen(width, cellsPerSide);
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Container can = frame.getContentPane();



		can.add(this, BorderLayout.CENTER);

		//setFocusable makes the focus of KeyListerner go to PlayTron
		this.setFocusable(true);
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

		
		//drawTrail(0,0, player1.getColor());

		frame.setVisible(true);
		
		//I made a method randomColor() that returns a random rgb color value just for fun
		//Feel free to change the third parameter to Color.RED or Color.BLUE at any time
		player1 = new Player(3, cellsPerSide/2, randomColor());
		player2 = new Player(cellsPerSide - 3, cellsPerSide/2, randomColor());
		gameLoop();
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for(int x = 0; x < cellsPerSide; x++) {
			for (int y = 0; y < cellsPerSide; y++) {
				g.setColor(playScreen.getColor(x, y));
				g.fillRect(getGridLocX(x), getGridLocY(y), cellWidth, cellWidth);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}

	public void keyPressed(KeyEvent e)
	{
		/*
		up = 1;
		right = 2;
		down = 3;
		left = 4;
		*/
		
		int key = e.getKeyCode();
		
		//player1 inputs
		if(key == KeyEvent.VK_UP)
			player1.setDirection(1);
		
		else if (key == KeyEvent.VK_RIGHT)
			player1.setDirection(2);
		
		else if (key == KeyEvent.VK_DOWN)
			player1.setDirection(3);
		
		else if (key == KeyEvent.VK_LEFT)
			player1.setDirection(4);
		
		System.out.println(player1.getDirection());
		
		//player2 inputs
		if(key == KeyEvent.VK_W)//moving Up
			player2.setDirection(1);
		
		else if (key == KeyEvent.VK_D)//moving right
			player2.setDirection(2);
		
		else if (key == KeyEvent.VK_S)//moving down
			player2.setDirection(3);
		
		else if (key == KeyEvent.VK_A)//moving left
			player2.setDirection(4);
	}
	
	//Unused methods needed because of implements KeyListener
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	private void gameLoop()
	{
		boolean run = true;
		while(run)
		{
			movePlayer(player1);
			movePlayer(player2);
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.repaint();
			System.out.println("p1 x: " + player1.getX() + " p1 y: " + player1.getY() + " p1 dir: " + player1.getDirection());
			System.out.println("p2 x: " + player2.getX() + " p2 y: " + player2.getY() + " p2 dir: " + player2.getDirection() + "\n");
		}
	}
	private void movePlayer(Player p)
	{
		//might not need this
		int locX = p.getX();
		int locY = p.getY();
		
		//set current locations to prevLocations
		p.setPrevX(p.getX());
		p.setPrevY(p.getY());
		
		playScreen.setColor(p.getX(), p.getY(), p.getColor());
		
		int dir = p.getDirection();
		
		//need to try/catch exception from player for going out of bounds
		if(dir == 1)//moving up
			p.setY(locY - 1);
		
		else if(dir == 2)//moving right
			p.setX(locX + 1);
		
		else if(dir == 3)//moving down
			p.setY(locY + 1);
		
		else if(dir == 4)//moving left
			p.setX(locX - 1);
		else
			System.out.println("no dir updates run");

	}
	
	//not currently used for anything. Maybe remove later
	private void drawTrail(int x, int y, Color c)
	{	
		playScreen.setColor(x, y, c);
		
		//repaint to draw the rectangle in paint component
		repaint();
	}
	
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
	
	private static Color randomColor()
	{
		Random ran = new Random();
		int ranCol = ran.nextInt(256);
		
		Color retColor = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		
		return retColor;
	}
	
	public static void main(String[] args)
	{
		new PlayTron();
		
	}
}
