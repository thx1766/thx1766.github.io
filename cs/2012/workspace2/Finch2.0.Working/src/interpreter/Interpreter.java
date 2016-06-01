package interpreter;

import codetree.*;
import logicalmap.*;
import gui.*;

import java.util.ArrayList;
import java.lang.Thread;
import java.util.Stack;

import physical.finchDriver;

public class Interpreter {
	private Thread runthread;
	private CodeTree maintree;
	private LogicalMap map;
	private RunProgramGui gui;
	private FunctionList flist;
	private Stack<CodeTree> FunctionStack;
	physical.finchDriver interpreterfinchdriver;
	private boolean runRobot; 
	public Interpreter(CodeTree tree, LogicalMap map, RunProgramGui gui, FunctionList fl, boolean runRobot)
	{
		this.gui = gui;
		this.map = map;
		this.maintree = tree;
		this.flist = fl;
		this.FunctionStack = new Stack<CodeTree>();
		this.runRobot = runRobot;
		
	}
	
	public void SetTree(CodeTree tree)
	{
		this.maintree = tree;
	}
	
	public void Run(int delay, StepSwitch steps) throws ExitlessLoopsException
	{
		//this.gui.active(true);
		//this.gui.validate();
		
		ArrayList<Integer> exitless = this.maintree.CheckExitConditions();
		
		if(exitless.size() != 0)
		{
			this.gui.active(false);
			throw new ExitlessLoopsException(exitless);
		}
		
		try
		{
			UpdateGraphicalMap();
			
			
			executeInstructions(this.maintree.getRoot(), steps, false);
		
		}
		catch(BreakLoopException e)
		{
			
		}
		catch(StopRunningException e)
		{
			System.out.println("EXECUTION STOPPED");
		}
		this.gui.SelectLine(-1);
		//TODO put in finish before closing
		//this.gui.active(false);
	}
	
	
	private void executeInstructions(Instruction inst, StepSwitch steps, boolean function) throws BreakLoopException,
	StopRunningException
	{
		steps.Grab();
		if(steps.IsStopped())
		{
			steps.Release();
			throw new StopRunningException();
		}
		steps.Release();
		
		steps.Grab();
		if(!steps.IsRun())
		{
			boolean stp = steps.SwitchOff();
			steps.Release();
			
			while(!stp)
			{
				try
				{
					Thread.sleep(250);
					
				}
				catch(Exception e)
				{
					
				}
				steps.Grab();
				stp = steps.SwitchOff();
				steps.Release();
			}
		}
		else
		{
			steps.Release();
		}
		
		
		
		System.out.println("Call is made");
		steps.Grab();
		boolean run = steps.IsRun();
		steps.Release();
		switch(inst.getType())
		{
			case 1:
				if(function)
				{
					this.gui.SelectLineMacro(inst.getPre());
				}
				else
				{
					this.gui.SelectLine(inst.getPre());
				}
				Action act = (Action)inst;
				ExecuteAction(act.getCommand(), run);
				executeInstructions(act.getLevelChild(), steps, function);
				break;
			case 2:
				if(function)
				{
					this.gui.SelectLineMacro(inst.getPre());
				}
				else
				{
					this.gui.SelectLine(inst.getPre());
				}
				
				Condition cond = (Condition)inst;
				
				if(AskCondition(cond.getConditionCode(), run))
				{
					executeInstructions(cond.getIfChild(), steps, function);
				}
				else
				{
					executeInstructions(cond.getElseChild(), steps, function);
				}
				if(function)
				{
					this.gui.SelectLineMacro(cond.getPost());
				}
				else
				{
					this.gui.SelectLine(cond.getPost());
				}
				
				executeInstructions(cond.getLevelChild(), steps, function);
				
				break;
				
			case 3:
				
				if(function)
				{
					this.gui.SelectLineMacro(inst.getPre());
				}
				else
				{
					this.gui.SelectLine(inst.getPre());
				}
				Loop loop = (Loop)inst;
				try
				{
					while(true)
					{
						executeInstructions(loop.getLoopChild(), steps, function);
					}
				}
				catch(BreakLoopException e)
				{
					executeInstructions(loop.getLevelChild(), steps, function);
				}
				if(function)
				{
					this.gui.SelectLineMacro(loop.getPost());
				}
				else
				{
					this.gui.SelectLine(loop.getPost());
				}
				break;
			case 4:
				if(function)
				{
					this.gui.SelectLineMacro(inst.getPre());
				}
				else
				{
					this.gui.SelectLine(inst.getPre());
				}
				Function funct = (Function)inst;
				ExecuteFunction(funct.getFunctionName(), steps);
				this.gui.clearJlistMacro();
				executeInstructions(funct.getLevelChild(), steps, function);
				
				break;
			case 5:
				Self slf = (Self)inst;
				ExecuteSelf(slf.getParameterMod(), slf);
				break;
			default:
		}
		
	}
	
	private void UpdateGraphicalMap()
	{
		CoordNode temp;
		int x, y, type;
		while((temp = this.map.getNextChange()) != null)
		{
			x = temp.getX();
			y = temp.getY();
			type = temp.getType();
			System.out.println("tile updated: " + type + "@" + x + "," + y);
			this.gui.setTile(x, y, type);
		}
	}
	
	private void ExecuteAction(int command, boolean run) throws BreakLoopException
	{
		edu.cmu.ri.createlab.terk.robot.finch.Finch tempbot;
		if(run)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
			
			}
		}
		/*if(runRobot)
		{
			interpreterfinchdriver.quitfinch();
			physical.finchDriver temp = new physical.finchDriver();
			temp.moveFinchForward();
			temp.quitfinch();
		}*/
		switch(command)
		{
			
			case 1:
				if(runRobot){
					System.out.println("GOT HERE");
				tempbot = new edu.cmu.ri.createlab.terk.robot.finch.Finch();
				tempbot.setLED(0, 255, 0);
				tempbot.setWheelVelocities(100, 100, 500);
				tempbot.quit();
				}
				map.moveFarrelForward();
				break;
			case 2:
				map.SmallTurnClockwise();
				if(runRobot){
					tempbot = new edu.cmu.ri.createlab.terk.robot.finch.Finch();
					tempbot.setLED(255, 0, 0);
					tempbot.setWheelVelocities(75, -75, 525);
					tempbot.quit();
					}
				break;
			case 3:
				map.BigTurnClockwise();
				if(runRobot){
					tempbot = new edu.cmu.ri.createlab.terk.robot.finch.Finch();
					tempbot.setLED(255, 0, 100);
					tempbot.setWheelVelocities(150, -150, 525);
					tempbot.quit();
					}
				break;
			case 4:
				map.SmallTurnCounterClockwise();
				if(runRobot){
					tempbot = new edu.cmu.ri.createlab.terk.robot.finch.Finch();
					tempbot.setLED(255, 0, 0);
					tempbot.setWheelVelocities(-75, 75, 525);
					tempbot.quit();
					}
				break;
			case 5:
				map.BigTurnCounterClockwise();
				if(runRobot){
					tempbot = new edu.cmu.ri.createlab.terk.robot.finch.Finch();
					tempbot.setLED(255, 0, 100);
					tempbot.setWheelVelocities(-150, 150, 525);
					tempbot.quit();
					}
				break;
			case 6:
				
				map.pushBox();
				
				break;
			case 7:
				map.checkerJump();
				
				break;
			case 20:
				throw new BreakLoopException();
			
		}
		this.UpdateGraphicalMap();
		
	}
	
	private boolean AskCondition(int conditionCode, boolean run)
	{
		//Show Progress in StudioGui
		if(run)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
		}
		switch(conditionCode)
		{
			case 1: //Is Path Free
				
				return map.IsFarrelPathFree();
			case 2: //Reached Objective
				
				return map.reachedObjective();
			case 3: //Is Danger
				
				return map.isDanger();
			case 4: //Is Enemy In Front
				
				return map.isEnemyInFront();
			case 5: //Is Spike In Front
				
				return map.isSpikeInFront();
			case 6: //Is Chomper In Front
				
				return map.isChomperInFront();
			case 7: //Is Pushable Block In Front
				
				return map.isPushableBlockInFront();
			case 8:
				
				return map.isPushableBlockAround();
		}
		if(conditionCode == 1)
		{
			return map.IsFarrelPathFree();
		}
		else if(conditionCode == 2)
		{
			return map.reachedObjective();
		}
		else
		{
			return false;
		}
	}
	
	
	
	private Instruction getRoot(Instruction inst)
	{
		while(inst.getParent() != null)
		{
			inst = inst.getParent();
		}
		return inst;
	}
	
	
	private void ExecuteFunction(String functionName, StepSwitch steps) throws BreakLoopException, StopRunningException
	{
		CodeTree fn = this.flist.getFunction(functionName);
		this.FunctionStack.push(fn);
		System.out.println("We Are Getting A Function");
		fn.PrintTree();
		gui.loadToJlistMacro(fn);
		//Add Function Contents To FunctionModel
		
		this.executeInstructions(fn.getRoot(), steps, true);
		
		this.FunctionStack.pop();
	}
	
	private void ExecuteSelf(int parMod, Instruction selfcall)
	{
		
		
		
	}
}
