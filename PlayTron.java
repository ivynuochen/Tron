import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PlayTron extends JPanel implements ActionListener {

	private JFrame frame;
	private Insets inset;
	
	private static  int width = 700;
	private static  int cellsPerSide = 100;
	private static int cellWidth = width / cellsPerSide;
	
	private int xStart, xEnd, yStart, yEnd;
	
	public PlayTron()
	{
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Container can = frame.getContentPane();

		can.add(this, BorderLayout.CENTER);
		this.setBackground(Color.DARK_GRAY);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, width));
		frame.pack();		
		inset = frame.getInsets();
		
		//vv bad code, fix later
		frame.setPreferredSize(new Dimension(width + inset.left + inset.right + 1, width + inset.top + inset.bottom + 1));
		frame.pack();

		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		
		System.out.println("Insets {top, bot, left, right} " + inset.top + " " + inset.bottom + " " + inset.left + " " + inset.right);
		
		for(int x = 0; x < width; x += cellWidth)
		{
			System.out.println(x);
			for(int y = 0; y < width; y += cellWidth)
			{
				System.out.println(y);
				g.drawRect(x, y, cellWidth, cellWidth);
			}
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	public int getGridLocX(int cellLocX)
	{
		int locX = cellLocX * cellWidth;
		return locX;
	}
	public int getGridLocY(int cellLocY)
	{
		int locY = cellLocY * cellWidth;
		return locY;
	}
	
	public static void main(String[] args)
	{
		Screen playScreen = new Screen(width, cellsPerSide);
		new PlayTron();
	}

}
