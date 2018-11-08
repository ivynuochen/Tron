import java.awt.*;

public class Screen {
	private Row[] myScreen;
	
	public Screen(int frameWidth, int rowLength)
	{
		//Test for invalid inputs
		if(frameWidth % rowLength != 0)
		{
			System.out.println("Frame width must be divisible by cells in a row");
			System.exit(1);
		}
		
		//declare a array of Rows
		myScreen = new Row[rowLength];
		
		//Construct a Row for set amount
		//The play area is a square, so rowLength should be the same as "colHeight"
		for(int i = 0; i < rowLength; i++)
		{
			System.out.println("Row[" + i + "]");
			myScreen[i] = new Row(rowLength);
		}
	}

}
