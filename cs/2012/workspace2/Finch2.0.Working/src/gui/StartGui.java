package gui;

import interpreter.ExitlessLoopsException;
import interpreter.Interpreter;

import javax.swing.JFrame;
import java.util.ArrayList;

public class StartGui implements Runnable {

	private Interpreter inter;
	private StepSwitch steps;
	StartGui(Interpreter inter, StepSwitch steps)
	{
		this.inter = inter;
		this.steps = steps;
	}
	
	@Override
	public void run() 
	{
		
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
		//run.setVisible(true);
		try {
			inter.Run(1000, steps);
		} catch (ExitlessLoopsException e) {
			ArrayList<Integer> ls = e.getList();
			String lines = "";
			for(int i = 0; i < ls.size(); i++)
			{
				lines += (ls.get(i).toString() + ", ");
			}
			new Error("You Have Loops without exit at lines: " + lines);
		
		}
	}

}
