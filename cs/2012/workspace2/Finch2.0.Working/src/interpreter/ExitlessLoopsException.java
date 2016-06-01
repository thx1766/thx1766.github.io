package interpreter;

import java.util.ArrayList;

public class ExitlessLoopsException extends Exception {
	private ArrayList<Integer> exitless;
	public ExitlessLoopsException(ArrayList<Integer> exitles)
	{
		super();
		this.exitless = exitles;
	}
	
	public ArrayList<Integer> getList()
	{
		return this.exitless;
	}
}
