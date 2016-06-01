package codetree;

public class LineNumberInvalidException extends Exception{
	private int position;
	public LineNumberInvalidException()
	{
		super();
	}
	
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	public int getPosition()
	{
		return this.position;
	}
}
