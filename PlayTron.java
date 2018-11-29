package edu.temple.cis;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class PlayTron extends JPanel implements ActionListener, KeyListener
{
	private JFrame frame;
	private Insets inset;

	private Screen playScreen;

	//width must be divisible by cellsPerSide
	private static int width = 1000;
	private static int cellsPerSide = 40;
	private static int cellWidth = width / cellsPerSide;

	private Player player1 , player2;

	public PlayTron()
	{
		playScreen = new Screen(width, cellsPerSide);
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Container can = frame.getContentPane();

		can.add(this, BorderLayout.CENTER);

		//setFocusable makes the focus of KeyListener go to PlayTron
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

		frame.setVisible(true);

		//I made a method randomColor() that returns a random rgb color value just for fun
		//Feel free to change the third parameter to Color.RED or Color.BLUE at any time
		player1 = new Player("p1",3, cellsPerSide/2, randomColor());
		player2 = new Player("p2",cellsPerSide - 3, cellsPerSide/2, randomColor());

		//player1 moves right, player2 move left

		player1.setDirection(2);
		player2.setDirection(4);
		gameLoop();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for(int x = 0; x < cellsPerSide; x++)
		{
			for (int y = 0; y < cellsPerSide; y++) {
				g.setColor(playScreen.getColor(x, y));
				g.fillRect(getGridLocX(x), getGridLocY(y), cellWidth, cellWidth);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {	}

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
		if(key == KeyEvent.VK_W)//moving up
			player1.setDirection(1);

		else if (key == KeyEvent.VK_D)//moving right
			player1.setDirection(2);

		else if (key == KeyEvent.VK_S)//moving down
			player1.setDirection(3);

		else if (key == KeyEvent.VK_A)//moving left
			player1.setDirection(4);

		System.out.println(player1.getDirection());

		//player2 inputs
		if(key == KeyEvent.VK_UP)//moving Up
			player2.setDirection(1);

		else if (key == KeyEvent.VK_RIGHT)//moving right
			player2.setDirection(2);

		else if (key == KeyEvent.VK_DOWN)//moving down
			player2.setDirection(3);

		else if (key == KeyEvent.VK_LEFT)//moving left
			player2.setDirection(4);
	}

	//Unused methods needed because of implements KeyListener
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	private void gameLoop()
	{
		boolean run = true;
		try {
			while (run) {
				boolean p1Mvmt = checkMovement(player1);
				boolean p2Mvmt = checkMovement(player2);

				if (p1Mvmt && !p2Mvmt) {
					popUp(player2, false);
				} else if (!p1Mvmt && p2Mvmt) {
					popUp(player1, false);
				} else if (!p1Mvmt && !p2Mvmt) {
					/*
					 * This is a draw state.
					 * Update popUp to have parameter to announce this.
					 * Giving parameter player1 should be changed in the future.
					 */
					popUp(player1, true);
				}

				movePlayer(player1);
				movePlayer(player2);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.repaint();
				System.out.println("p1 x: " + player1.getX() + " p1 y: " + player1.getY() + " p1 dir: " + player1.getDirection());
				System.out.println("p2 x: " + player2.getX() + " p2 y: " + player2.getY() + " p2 dir: " + player2.getDirection() + "\n");
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("out of bounds");
			if (player1.getX()==-1 || player1.getY()==-1)
				popUp(player1, false);
			else
				popUp(player2, false);
		}
	}


	private void movePlayer(Player p)
	{
		//might not need this
		int locX = p.getX();
		int locY = p.getY();
		int nextX, nextY;
		//try/catch exception from player for going out of bounds got it~~~!
		try
		{
			playScreen.setColor(p.getX(), p.getY(), p.getColor());

			int dir = p.getDirection();
			if (dir == 1)//moving up
			{
				p.setY(locY - 1);
			}
			else if (dir == 2)//moving right
			{
				p.setX(locX + 1);
			}
			else if (dir == 3)//moving down
			{
				p.setY(locY + 1);
			}
			else if (dir == 4)//moving left
			{
				p.setX(locX - 1);
			}
			else
				System.out.println("no dir updates run");
		}
		//Deals with crashing into the border
		//I actually don't know if we need this
		catch(ArrayIndexOutOfBoundsException e)
		{
			popUp(p,false);
		}
	}

	public boolean checkMovement(Player p)
	{
		boolean retBool = true;

		if(playScreen.getColor(p.getX(), p.getY()) != (Color.LIGHT_GRAY))
		{
			System.out.println("invalid");
			retBool = false;
		}
		return retBool;
	}

	//popUp will be called whenever someone does something illegal. Can add extra condition for reason in the future
	private void popUp(Player p, boolean tie)
	{
		Object[] options = {"Play Again", "Exit Game"};
		String message;
		if (tie == false)
			message = "Player "+p.getName()+" has lost. ";
		else
			message = "A head on collision has occured! The game has ended in a tie. ";

		int n = JOptionPane.showOptionDialog(frame,message +"\n"+"Would you like to play the game again?","You've lost the game!",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
		System.out.println(n);

		if (n == 0)
		{
			frame.dispose();
			new PlayTron();  //is this okay?
		}
		if (n == 1)
		{
			frame.dispose();
			System.exit(0);
		}
	}

	//not currently used for anything. Maybe remove later
	private void drawTrail(int x, int y, Color c)
	{
		playScreen.setColor(x, y, c);
		//repaint to draw the rectangle in paint component
		repaint();
	}

	//The purpose is to convert array indexes to grid locations in pixels
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
		//int ranCol = ran.nextInt(256);

		Color retColor = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		return retColor;
	}

	public static void main(String[] args)
	{
		new PlayTron();
	}
}