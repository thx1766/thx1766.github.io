package codetree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.DefaultListModel;

public class CodeTree implements Serializable{
	private Instruction root;
	private boolean isfunction;
	private boolean recursive;
	
	private int parameter;
	
	public CodeTree()
	{
		this.root = new Instruction(null);
		this.root.setPre(0);
		this.isfunction = false;
		this.recursive = false;
		this.parameter = 0;
	}
	
	public boolean isFunction()
	{
		return this.isfunction;
	}
	
	public void makeFunction()
	{
		this.isfunction = true;
	}
	
	public Instruction getRoot()
	{
		return this.root;
	}
	
	public void UpdateParameter(int parameter)
	{
		this.parameter = parameter;
	}
	
	public int GetParameter()
	{
		return this.parameter;
	}
	
	public void ClearTree()
	{
		this.root = new Instruction(null);
		this.root.setPre(0);
	}
	
	public ArrayList<Integer> CheckExitConditions()
	{
		ArrayList<Loop> looplist = findLoops();
		ArrayList<Integer> exitless = new ArrayList<Integer>();
		for(int i = 0; i < looplist.size(); i++)
		{
			if(!HasExit(looplist.get(i).getLoopChild()))
			{
				exitless.add(looplist.get(i).getPre());
			}
		}
		
		return exitless;
	}
	
	private boolean HasExit(Instruction inst)
	{
		if(inst.getType() == 1)
		{
			Action act = (Action)inst;
			if(act.getCommand() == 20)
			{
				return true;
			}
			else
			{
				return HasExit(act.getLevelChild());
			}
		}
		else if(inst.getType() == 0)
		{
			return false;
		}
		else if(inst.getType() == 2)
		{
			Condition cond = (Condition)inst;
			return (HasExit(cond.getIfChild()) || HasExit(cond.getElseChild()) || 
					HasExit(cond.getLevelChild()));
		}
		else
		{
			return HasExit(inst.getLevelChild());
		}
	}
	
	
	
	private ArrayList<Loop> findLoops()
	{
		ArrayList<Loop> looplist = new ArrayList<Loop>();
		GetLoop(this.root, looplist);
		return looplist;
		
	}
	
	private void GetLoop(Instruction inst, ArrayList<Loop> looplist)
	{
		if(inst.getType() == 3)
		{
			
			Loop loop = (Loop)inst;
			looplist.add(loop);
			GetLoop(loop.getLoopChild(), looplist);
			GetLoop(loop.getLevelChild(), looplist);
		}
		else if(inst.getType() == 2)
		{
			Condition cond = (Condition)inst;
			GetLoop(cond.getIfChild(), looplist);
			GetLoop(cond.getElseChild(), looplist);
			GetLoop(cond.getLevelChild(), looplist);
		}
		else if(inst.getType() == 0)
		{
			
		}
		else
		{
			GetLoop(inst.getLevelChild(), looplist);
		}
	}
	
	public int DeleteStatement(int position) throws LineNumberInvalidException, BlankLineSelectedException
	{
		Instruction place = findLine(position);
		int lastline = position;
		if(place == null)
		{
			throw new LineNumberInvalidException();
		}
		
		switch(place.getType())
		{
			case 0:
				throw new BlankLineSelectedException();
			
			case 2:
				Condition cond = (Condition)place;
				lastline = cond.getPost();
				break;
			case 3:
				Loop loop = (Loop)place;
				lastline = loop.getPost();
				break;
		}
		
		Instruction placeparent = place.getParent();
		if(placeparent == null)
		{
			this.root = this.root.getLevelChild();
		}
		else
		{
		
			switch(placeparent.getType())
			{
				case 2:
					Condition cond = (Condition)placeparent;
					if(cond.getIfChild().getPre() == place.getPre())
					{
						cond.addIfChild(place.getLevelChild());
					}
					else if(cond.getElseChild().getPre() == place.getPre())
					{
						cond.addElseChild(place.getLevelChild());
					}
					else
					{
						try
						{
							cond.addLevelChild(place.getLevelChild());
						}
						catch(NoChildrenAllowedException e)
						{
							
						}
					}
					break;
				case 3:
					Loop loop = (Loop)placeparent;
					if(loop.getLoopChild().getPre() == place.getPre())
					{
						loop.addLoopChild(place.getLevelChild());
					}
					else
					{
						try
						{
							loop.addLevelChild(place.getLevelChild());
						}
						catch(NoChildrenAllowedException e)
						{
							
						}
					}
					break;
				default:
					try
					{
						placeparent.addLevelChild(place.getLevelChild());
					}
					catch(NoChildrenAllowedException e)
					{
						
					}
			}
		}
		
		this.UpdateCount();
		
		return lastline;
	}
	
	public boolean IsRecursive()
	{
		return FoundRecursive(this.root);
	}
	
	private boolean FoundRecursive(Instruction inst)
	{
		switch(inst.getType())
		{
			case 0:
				return false;
			case 2:
				Condition cond = (Condition)inst;
				return (FoundRecursive(cond.getLevelChild())||
						FoundRecursive(cond.getIfChild())||
						FoundRecursive(cond.getElseChild()));
			case 3:
				Loop loop = (Loop)inst;
				return (FoundRecursive(loop.getLoopChild())||
						FoundRecursive(loop.getLevelChild()));
			case 5:
				return true;
			default:
				return FoundRecursive(inst.getLevelChild());
		}
	}
	
	public int AddAction(int position, int command) throws LineNumberInvalidException,
	BreakLoopInvalidException
	{
		Action act = new Action(null, command);
		return AddInstruction(act, position);
	}
	
	public int AddCondition(int position, int conditionCode) throws LineNumberInvalidException
	{
		int a = 0;
		Condition cond = new Condition(null, conditionCode);
		try
		{
			a = AddInstruction(cond, position);
		}
		catch(BreakLoopInvalidException e)
		{
			
		}
		return a;
	}
	
	public int AddLoop(int position) throws LineNumberInvalidException
	{
		int a = 0;
		Loop loop = new Loop(null);
		try
		{
			a = AddInstruction(loop, position);
		}
		catch(BreakLoopInvalidException e)
		{
			
		}
		return a;
		
	}
	
	public int AddFunction(int position, String fname) throws LineNumberInvalidException
	{
		int a = 0;
		Function funct = new Function(null, fname);
		
		try
		{
			a = AddInstruction(funct, position);
		}
		catch(BreakLoopInvalidException e)
		{
			
		}
		
		return a;
		
	}
	
	
	
	public int AddSelf(int position, int parameterMod) throws LineNumberInvalidException
	{
		int a = 0;
		Self slf = new Self(null, parameterMod);
		try
		{
			a = AddInstruction(slf, position);
		}
		catch(BreakLoopInvalidException e)
		{
			
		}
		return a;
	}
	
	private int AddInstruction(Instruction inst, int position) throws LineNumberInvalidException, 
	BreakLoopInvalidException
	{
		
		if(position == 0)
		{
			
			if(inst.getType() == 1)
			{
				Action act = (Action)inst;
				if(act.getCommand() == 20)
				{
					throw new BreakLoopInvalidException();
				}
			}
			if(root.getType() == 0)
			{
				this.root = inst;
			}
			
			else
			{
				try
				{
					inst.addLevelChild(this.root);
				}
				catch(NoChildrenAllowedException e)
				{
					
				}
				this.root = inst;
			}
		}
		else
		{
			Instruction place = findLine(position);
			if(place == null)
			{
				LineNumberInvalidException exc = new LineNumberInvalidException();
				exc.setPosition(position);
				throw exc;
				
			}
			else
			{
				Instruction placeparent = place.getParent();
				if(inst.getType() == 1 && !placeparent.IsInLoop())
				{
					Action ac = (Action)inst;
					if(ac.getCommand() == 20)
					{
						throw new BreakLoopInvalidException();
					}
				}
				try
				{
					
					inst.setParent(placeparent);
					inst.addLevelChild(place);
					switch(placeparent.getType())
					{
						case 2:
							Condition condpar = (Condition)placeparent;
							if(condpar.getIfChild().getPre() == position)
							{
								condpar.addIfChild(inst);
								
							}
							else if(condpar.getElseChild().getPre() == position)
							{
								condpar.addElseChild(inst);
							}
							else
							{
								condpar.addLevelChild(inst);
							}
							
							break;
						case 3:
							Loop looppar = (Loop)placeparent;
							if(looppar.getLoopChild().getPre() == position)
							{
								looppar.addLoopChild(inst);
							}
							else
							{
								looppar.addLevelChild(inst);
							}
							break;
						default:
							placeparent.addLevelChild(inst);
					}
					inst.getLevelChild().setLevel(inst.getLevel());
					if(inst.getType() == 2)
					{
						Condition cond = (Condition)inst;
						cond.getIfChild().setLevel(cond.getLevel() + 1);
						cond.getElseChild().setLevel(cond.getLevel() + 1);
					}
					if(inst.getType() == 3)
					{
						Loop loop = (Loop)inst;
						loop.getLoopChild().setLevel(loop.getLevel() + 1);
					}
				}
				catch(NoChildrenAllowedException e)
				{
						
				}
				
				
						
				
			}
		}
		
		this.UpdateCount();
		return inst.getLevel();
	}
	
	private void UpdateCount()
	{
		UpdateNodes(this.root, 0);
	}
	
	private int UpdateNodes(Instruction inst, int count)
	{
		inst.setPre(count);
		if(inst.getType() == 0)
		{
			return count + 1;
		}
		else if(inst.getType() == 2)
		{
			Condition cond = (Condition)inst;
			int post = UpdateNodes(cond.getIfChild(), count + 1);
			post = UpdateNodes(cond.getElseChild(), post + 1);
			cond.setPost(post);
			return UpdateNodes(cond.getLevelChild(), post + 1);
			
		}
		else if(inst.getType() == 3)
		{
			Loop loop = (Loop)inst;
			int post = UpdateNodes(loop.getLoopChild(), count + 1);
			loop.setPost(post);
			return UpdateNodes(loop.getLevelChild(), post + 1);
			
		}
		else
		{
			return UpdateNodes(inst.getLevelChild(), count + 1);
		}
	}
	private Instruction findLine(int position)
	{
		return findInstruction(position, this.root);
	}
	
	private Instruction findInstruction(int position, Instruction inst)
	{
		if(inst.getPre() == position)
		{
			return inst;
		}
		else
		{
			switch(inst.getType())
			{
				case 0:
					return null;
				case 1:
					return findInstruction(position, inst.getLevelChild());
				case 2:
					Condition cond = (Condition)inst;
					Instruction lev, iff, ell;
					lev = findInstruction(position, cond.getLevelChild());
					iff = findInstruction(position, cond.getIfChild());
					ell = findInstruction(position, cond.getElseChild());
					if(lev != null)
					{
						return lev;
					}
					else if(iff != null)
					{
						return iff;
					}
					else if(ell != null)
					{
						return ell;
					}
					else
					{
						return null;
					}
				case 3:
					Loop loop = (Loop)inst;
					Instruction levv, lop;
					levv = findInstruction(position, loop.getLevelChild());
					lop = findInstruction(position, loop.getLoopChild());
					if(levv != null)
					{
						return levv;
					}
					else if(lop != null)
					{
						return lop;
					}
					else
					{
						return null;
					}
				case 4:
					return findInstruction(position, inst.getLevelChild());
				case 5:
					return findInstruction(position, inst.getLevelChild());
			}
		}
		return null;
	}
	
	public void PrintTree()
	{
		PrintInstructions(this.root);
	}
	
	public void PrintTreeToJlist(DefaultListModel model)
	{
		model.clear();
		PrintInstructionsToJlist(this.root, model);
	}
	
	private void PrintInstructions(Instruction inst)
	{
		String indent = getLevelIndent(inst.getLevel());
		switch(inst.getType())
		{
			case 0:
				System.out.println(indent + "(" + inst.getPre() + ")" + "Blank Instruction " + inst.getLevel());
				break;
			case 1:
				Action act = (Action)inst;
				System.out.println(indent + "(" + inst.getPre() + ")" + "Action:" + act.getCommand());
				PrintInstructions(act.getLevelChild());
				break;
			case 2:
				Condition cond = (Condition)inst;
				System.out.println(indent + "(" + inst.getPre() + ")" + "If Condition:" + cond.getConditionCode());
				PrintInstructions(cond.getIfChild());
				System.out.println(indent + "Else");
				PrintInstructions(cond.getElseChild());
				System.out.println(indent + "(" + cond.getPost() + ")" + "END IF");
				PrintInstructions(cond.getLevelChild());
				break;
			case 3:
				Loop loop = (Loop)inst;
				System.out.println(indent + "(" + loop.getPre() + ")" +  "Loop");
				PrintInstructions(loop.getLoopChild());
				System.out.println(indent + "(" + loop.getPost() + ")" +  "END LOOP");
				PrintInstructions(loop.getLevelChild());
				break;
			case 4:
				Function funct = (Function)inst;
				//System.out.println(indent + "(" + inst.getPre() + ")" + "Function, Index:" + funct.getFunctionIndex());
				PrintInstructions(funct.getLevelChild());
				break;
			case 5:
				Self slf = (Self)inst;
				System.out.println(indent + "(" + inst.getPre() + ")" + "Self-Call, Modifier:" + slf.getParameterMod());
				PrintInstructions(slf.getLevelChild());
				break;
				
		}
	}
	
	private void PrintInstructionsToJlist(Instruction inst, DefaultListModel model)
	{
		String indent = getLevelIndent(inst.getLevel());
		switch(inst.getType())
		{
			case 0: 
				model.addElement("                    ");
				//System.out.println(indent + "(" + inst.getPre() + ")" + "Blank Instruction " + inst.getLevel());
				break;
			case 1:
				Action act = (Action)inst;
				model.addElement(indent + getActionName(act.getCommand()));
				PrintInstructionsToJlist(act.getLevelChild(), model);
				break;
			case 2:
				Condition cond = (Condition)inst;
				model.addElement(indent  + getConditionName(cond.getConditionCode()));
				PrintInstructionsToJlist(cond.getIfChild(), model);
				model.addElement(indent + "Else");
				PrintInstructionsToJlist(cond.getElseChild(), model);
				model.addElement(indent  + "END IF");
				PrintInstructionsToJlist(cond.getLevelChild(), model);
				break;
			case 3:
				Loop loop = (Loop)inst;
				model.addElement(indent  +  "LOOP");
				PrintInstructionsToJlist(loop.getLoopChild(), model);
				model.addElement(indent  +  "END LOOP");
				PrintInstructionsToJlist(loop.getLevelChild(), model);
				break;
			case 4:
				Function funct = (Function)inst;
				model.addElement(indent + "FUNCTION: " + funct.getFunctionName());
				PrintInstructionsToJlist(funct.getLevelChild(), model);
				break;
			case 5:
				Self slf = (Self)inst;
				model.addElement(indent + "Self-Call, Modifier:" + slf.getParameterMod());
				PrintInstructionsToJlist(slf.getLevelChild(), model);
				break;
				
		}
	}

	private String getActionName(int command)
	{
		switch(command)
		{
			case 1:
				return "Move Forward";
			
			case 2:
				return "Small Turn Clockwise";
				
			case 3:
				return "Big Turn Clockwise";
				
			case 4:
				return "Small Turn Counter Clockwise";
				
			case 5:
				return "Big Turn Counter Clockwise";
				
			case 6:
				return "Push Box";
			
			case 7:
				return "Checker Jump";
				
			case 20:
				return "EXIT LOOP";
			
			default:
				
		}
		
		
		return null;
	}
	
	
	private String getConditionName(int conditionCode)
	{
		switch(conditionCode)
		{
			case 1:
				return "Is The Path Clear?";
				
			case 2:
				return "Is Objective Found?";
			
			case 3:
				
				return "Is Danger?";
			case 4:
				
				return "Is Enemy In Front?";
			case 5:
				
				return "Is Spike In Front?";
			case 6:
				
				return "Is Chomper In Front?";
			case 7:
				
				return "Is Pushable Block In Front?";
			case 8:
				
				return "Is Pushable Block Around?";
				
		}
		
		return null;
	}
	
	private String getLevelIndent(int level)
	{
		String indent = "";
		for(int i = 0; i < level; i++)
		{
			indent += "      ";
		}
		
		return indent;
	}
}
