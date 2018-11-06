import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PlayTron extends JPanel {

	private JFrame frame;
	
	public PlayTron()
	{
		frame = new JFrame();
		
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setVisible(true);
		
		frame.pack();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	public static void main(String[] args)
	{
		Screen playScreen = new Screen(10, 10);
		new PlayTron();
	}

}
