package codetree;

import java.io.Serializable;

public class Condition extends Instruction implements Serializable{
	private int conditionCode;
	private Instruction ifchild;
	private Instruction elsechild;
	private int post;
	
	public Condition(Instruction parent, int conditionCode)
	{
		super(parent);
		this.setType(2);
		this.conditionCode = conditionCode;
		this.post = 0;
		try
		{
			this.addLevelChild(new Instruction(this));
			this.addIfChild(new Instruction(this));
			this.addElseChild(new Instruction(this));
		}
		catch(NoChildrenAllowedException e)
		{
			
		}
		
	}
	
	public void addIfChild(Instruction child)
	{
		if(child != null)
		{
			child.setParent(this);
			if(this.IsInLoop())
			{
				child.SetInLoop();
			}
			child.setLevel(this.getLevel() + 1);
			this.ifchild = child;
		}
	}
	
	public void addElseChild(Instruction child)
	{
		if(child != null)
		{
			child.setParent(this);
			if(this.IsInLoop())
			{
				child.SetInLoop();
			}
			child.setLevel(this.getLevel() + 1);
			this.elsechild = child;
		}
	}
	
	public Instruction getIfChild()
	{
		return this.ifchild;
	}
	
	public Instruction getElseChild()
	{
		return this.elsechild;
	}
	public int getConditionCode()
	{
		return this.conditionCode;
	}
	
	
	protected void setPost(int post)
	{
		this.post = post;
	}
	
	public int getPost()
	{
		return this.post;
	}
}
