package logicalmap;
/**
 * Extends Block to implement Farrel
 * 
 * @author Igor
 *
 */

	/*
	 * The Farrel is a basic extension of the Block
	 * The things that are different about it are
	 * the direction variable which indicates the direction in which the farrel is facing
	 * the getType() method
	 * which returns a different type depending on the direction that the farrel
	 * Also direction can be changed with the 
	 * SmallTurnClockwise(), SmallTurnCounterClockwise(), BigTurnClockwise(), BigTurnCounterClockwise()
	 * methods
	 * also the getNextX() and getNextY() methods are overwritten in such a way that there is no direction input
	 */
public class Farrel extends Block{
	private int direction;
	private boolean destroyed;
	/**
	 * Default constructor for the Farrel
	 * 
	 * @param x initial X coordinate
	 * @param y initial Y coordinate
	 * @param initial_direction
	 */
	public Farrel(int x, int y, int initial_direction)
	{
		super(x, y, 0, false);
		this.direction = initial_direction;
		this.destroyed = false;
	}
	
	public void Destroy()
	{
		this.destroyed = true;
	}
	
	public void Heal()
	{
		this.destroyed = false;
	}
	
	public boolean isDestroyed()
	{
		return this.destroyed;
	}
	
	protected void setDirection(int dir)
	{
		this.direction = dir;
	}
	
	
	public int getType()
	{
		if(this.destroyed)
		{
			return 109;
		}
		return this.direction + 100;
	}
	
	
	/**
	 * Gets the current direction that the Farrel is facing
	 * @return int representing a particular direction
	 */
	
	protected int getDirection()
	{
		return this.direction;
	}
	
	/**
	 * Gets the X coordinate of the block in front of the farrel
	 * @return int representing the X coordinate
	 */
	
	protected int getNextX()
	{
		return this.getNextX(this.direction);
	}
	
	/**
	 * Gets the Y coordinate of the block in front of the farrel
	 * @return int representing the Y coordinate
	 */
	
	protected int getNextY()
	{
		return this.getNextY(this.direction);
	}
	
	
	/**
	 * Turns the Farrel Clockwise 45 degrees
	 */
	
	protected void SmallTurnClockwise()
	{
		if(this.direction == 8)
		{
			this.direction = 1;
		}
		else
		{
			this.direction ++;
		}
	}
	
	/**
	 * Turns the Farrel Counter Clockwise 45 degrees
	 */
	
	protected void SmallTurnCounterClockwise()
	{
		if(this.direction == 1)
		{
			this.direction = 8;
		}
		else
		{
			this.direction --;
		}
	}
	
	/**
	 * Turns the Farrel Clockwise 90 degrees
	 */
	
	protected void BigTurnClockwise()
	{
		if(this.direction == 8)
		{
			this.direction = 2;
		}
		else if(this.direction == 7)
		{
			this.direction = 1;
		}
		else
		{
			this.direction += 2;
		}
		
	}
	
	/**
	 * Turns the Farrel Counter Clockwise 90 degrees
	 */
	
	protected void BigTurnCounterClockwise()
	{
		if(this.direction == 1)
		{
			this.direction = 7;
		}
		else if(this.direction == 2)
		{
			this.direction = 8;
		}
		else
		{
			this.direction -= 2;
		}
		
	}
	
	
	
	
}
