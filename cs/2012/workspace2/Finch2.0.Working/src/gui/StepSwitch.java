package gui;
import java.util.concurrent.Semaphore;

public class StepSwitch {
	private Semaphore center;
	private boolean run;
	private boolean step;
	private boolean stop;
	
	public StepSwitch()
	{
		this.center = new Semaphore(1, true);
		this.run = true;
		this.step = true;
		this.stop = false;
		
	}
	
	public void SetRun()
	{
		this.run = true;
	}
	
	public void Stop()
	{
		this.stop = true;
	}
	
	public boolean IsStopped()
	{
		return this.stop;
	}
	
	public void SetStep()
	{
		this.run = false;
		this.step = false;
	}
	
	public boolean IsRun()
	{
		return this.run;
	}
	
	
	public boolean SwitchOff()
	{
		if(this.step == true)
		{
			this.step = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void SwitchOn()
	{
		this.step = true;
	}
	
	public synchronized void Grab()
	{
		try
		{
			this.center.acquire();
		}
		catch(InterruptedException e)
		{
			System.out.println("Thread Interrupted");
		}
	}
	
	public void Release()
	{
		
			this.center.release();
	}
}
