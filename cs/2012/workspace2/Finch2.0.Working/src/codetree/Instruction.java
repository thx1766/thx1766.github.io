package codetree; 

import java.io.Serializable;

/*
 * 
 * Instruction is the base node of the code tree
 * it contains Instructions truechild, and elchild
 * and may have five distinct types:
 * (0)Blank: This is used in making the code tree structure easier to search through and doesn't affect code execution
 * 		Blank's truechild and elchild are null.
 * (1)Action: This is used to store single line actions
 * 		Action has a truechild and its elchild is null
 * (2)Condition: This is used to store if/else analog statements
 * 		Condition has a truechild and  an elchild
 * (3)Loop: This is used for loop statements
 * 		Loop has a true child and its elchild is null
 * (4)Function: This is used to store subroutines
 * 		Function has both of it children set as null but has an index pointer 
 * 		which points to a sub code tree in the main code tree's functionlist.
 */

public class Instruction implements Serializable{
	private Instruction levelChild;
	private int type;
	private Instruction parent;
	private int pre;
	private int level;
	private boolean inLoop;
	/**
	 * Creates an instruction of type 0 (blank)
	 */
	
	public Instruction(Instruction parent)
	{
		type = 0;
		this.parent = parent;
		level = 0;
		this.levelChild = null;
		this.inLoop = false;
	}
	
	protected void setParent(Instruction parent)
	{
		this.parent = parent;
		this.level = parent.getLevel();
	}
	
	protected boolean IsInLoop()
	{
		return this.inLoop;
	}
	
	protected void SetInLoop()
	{
		this.inLoop = true;
	}
	
	public Instruction getParent()
	{
		return this.parent;
	}
	
	protected void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	protected void setPre(int pre)
	{
		this.pre = pre;
	}
	
	public int getPre()
	{
		return this.pre;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	protected void setType(int type)
	{
		this.type = type;
	}
	
	protected void addLevelChild(Instruction child) throws NoChildrenAllowedException
	{
		if(this.getType() == 0)
		{
			throw new NoChildrenAllowedException();
		}
		if(child != null)
		{
			child.setParent(this);
			child.setLevel(this.getLevel());
			if(this.IsInLoop())
			{
				child.SetInLoop();
			}
			this.levelChild = child;
		}
	}
	
	public Instruction getLevelChild()
	{
		return this.levelChild;
	}
}
