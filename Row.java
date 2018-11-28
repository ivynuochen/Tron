public class Row {
	
	protected Cell[] myRow;
	private int length;
	
	public Row(int length)
	{
		this.length = length;
		
		//declare array of Cells
		myRow = new Cell[length];
		
		//Construct cell for set amount
		for(int i = 0; i < length; i++)
		{	
			System.out.println("Cell[" + i + "]");
			myRow[i] = new Cell();
		}
	}
}
