package codetree;

import java.io.Serializable;

public class Function extends Instruction implements Serializable{
	private String functionName;
	private int parameter;
	public Function(Instruction parent, String functionName)
	{
		super(parent);
		this.setType(4);
		this.parameter = 0;
		this.functionName = functionName;
		try
		{
			this.addLevelChild(new Instruction(this));
		}
		catch(NoChildrenAllowedException e)
		{
			
		}
		
	}
	
	
	public String getFunctionName()
	{
		return this.functionName;
	}
	
	protected void setParameter(int par)
	{
		this.parameter = par;
	}
	
	public int getPrameter()
	{
		return this.parameter;
	}
}
