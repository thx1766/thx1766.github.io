package codetree;

import java.io.Serializable;

public class Action extends Instruction implements Serializable{
	private int command;
	public Action(Instruction parent, int command)
	{
		super(parent);
		this.setType(1);
		this.command = command;
		
		try
		{
			this.addLevelChild(new Instruction(this));
		}
		catch(NoChildrenAllowedException e)
		{
			
		}
	}
	
	public int getCommand()
	{
		return this.command;
	}
	

}
