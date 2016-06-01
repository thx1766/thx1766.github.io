package codetree;

import java.io.Serializable;

public class Loop extends Instruction implements Serializable{
	private Instruction loopChild;
	private int post;
	
	public Loop(Instruction parent)
	{
		super(parent);
		this.setType(3);
		this.post = 0;
		try
		{
			this.addLevelChild(new Instruction(this));
		}
		catch(NoChildrenAllowedException e)
		{
			
		}
		this.addLoopChild(new Instruction(this));
	}
	
	protected void addLoopChild(Instruction child)
	{
		if(child != null)
		{
			child.setParent(this);
			child.SetInLoop();
			child.setLevel(this.getLevel() + 1);
			this.loopChild = child;
		}
	}
	
	public Instruction getLoopChild()
	{
		return this.loopChild;
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
