package logicalmap;

/*
 * The block is the basic object which populates the LogicalMap
 * it has its associated X and Y coordinates
 * a type value which differentiates the appearance of the block on the Graphical Map
 * and a boolean value movable which indicates whether the block can be moved from its set position on the map
 */

public class Block{
	private int xcord;
	private int ycord;
	private int type;
	private boolean movable;
	/**
	 * Default Constructor  for the block
	 * 
	 * @param x x location
	 * @param y y location
	 * @param btype type of block
	 * @param mov if it can move 
	 */
	public Block(int x, int y, int btype, boolean mov)
	{
		this.xcord = x;
		this.ycord = y;
		this.movable = mov;
		this.type = btype;
		
	}
	
	/**
	 * Asks if the block is movable
	 * @return returns true if yes false otherwise
	 */
	
	public boolean IsMovable()
	{
		return this.movable;
	}
	
	protected int getType()
	{
		return this.type;
	}
	
	/**
	 * Sets the x and y coordinates of the current block
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	
	protected void setLocation(int x, int y)
	{
		this.xcord = x;
		this.ycord = y;
	}
	
	/**
	 * 
	 * @return returns the block's X coordinate
	 */
	
	protected int getX()
	{
		return this.xcord;
	}
	
	/**
	 * 
	 * @return returns the block's Y coordinate
	 */
	
	protected int getY()
	{
		return this.ycord;
	}
	
	/**
	 * 
	 * @param direction direction of the next block
	 * @return returns the X coordinate of the next block in the given direction
	 */
	
	protected int getNextX(int direction)
	{
		int x = this.xcord;
		switch(direction)
		{
			
			case 2:
				x ++;
				break;
			case 3:
				x ++;
				break;
			case 4:
				x ++;
				break;
			case 6:
				x --;
				break;
			case 7:
				x --;
				break;
			case 8:
				x --;
				break;
	
		}
		
		return x;
	}
	
	/**
	 * 
	 * @param direction direction of the next block
	 * @return returns the X coordinate of the next block in the given direction
	 */
	
	protected int getNextY(int direction)
	{
		int y = this.ycord;
		switch(direction)
		{
			case 1:
				y --;
				break;
			case 2:
				y--;
				break;
			case 4:
				y ++;
				break;
			case 5:
				y++;
				break;
			case 6:
				y++;
				break;
			case 8:
				y--;
				break;
		}
		
		return y;
	}
	
	
	
	

}
