import java.awt.*;

public class Screen {
	private Row[] myScreen;
	
	public Screen(int length, int height)
	{
		myScreen = new Row[height];
		for(int i = 0; i < height; i++)
		{
			System.out.println("Row[" + i + "]");
			myScreen[i] = new Row(length);
		}
	}

}
