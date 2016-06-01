package logicalmap;

/**
 * Will be used to pass Nodes from one class or method to another
 * Since your limited to only one return type
 * 
 * @author Igor
 *
 */

public class CoordNode{
	/**
	 * X cord Passed
	 */
	private int xcord;
	/**
	 * X cord Passed
	 */
	private int ycord;
	/**
	 * Type Passed
	 */
	private int type;
	/**
	 * Defalt Constructor of CoordNode with (x,y,type)
	 * 
	 * @param x of coord
	 * @param y of cooord
	 * @param typ of block
	 */
	public CoordNode(int x, int y, int typ)
	{
		this.xcord = x;
		this.ycord = y;
		this.type = typ;
	}
	
	/**
	 * Returns X of the block
	 */
	public int getX()
	{
		return this.xcord;
	}
	/**
	 * Returns Y of the Block
	 */
	public int getY()
	{
		return this.ycord;
	}
	/**
	 * Returns type of the block
	 */
	public int getType()
	{
		return this.type;
	}

}
