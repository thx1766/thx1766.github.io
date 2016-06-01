import edu.cmu.ri.createlab.terk.robot.finch.Finch;
//ADD JAR TO SRC FOLDER
//THEN
//ECLIPSE->Project->Properties->Java Build Path->Libraries-> Add JAR's


public class finchDriver{

	private Finch myFinch;
	private boolean busy;
	private int fwdHoldTime;
	private int fwdVelocity;
	private int turnHoldTime;
	private int turnVelocity;
	
	//default constructor
	public finchDriver(){
		myFinch= new Finch();
		myFinch.setLED(0, 0, 255);
		fwdHoldTime = 3000;
		fwdVelocity = 100;
		turnHoldTime = 100;
		turnVelocity = 3000;
		busy=false;
	}
	
	//"tailored value" constructor
	public finchDriver(int fHT, int fV, int tHT, int tV){
		myFinch= new Finch();
		fwdHoldTime = fHT;
		fwdVelocity = fV;
		turnHoldTime = tHT;
		turnVelocity = tV;
		busy=false;
	}
	

	public void moveFinchForward(){
		busy=true;
		myFinch.setWheelVelocities(fwdVelocity, fwdVelocity, fwdHoldTime);
		//velocities are matched for forward motion
		//hold for 3000msec, 3 seconds
		//finch code blocks program execution until time has elapsed
		busy=false;
		
	}
	
	public void turnFinchLeft(){
		busy=true;
		myFinch.setWheelVelocities(-(turnVelocity), turnVelocity, turnHoldTime);
		//velocities are equal in magnitude, opposite in direction for pivot rotation
		//hold for turnHoldTime
		//finch code blocks program execution until time has elapsed
		busy=false;
		
	}
	
	public void turnFinchRight(){
		busy=true;
		myFinch.setWheelVelocities(turnVelocity, -(turnVelocity), turnHoldTime);
		//velocities are equal in magnitude, opposite in direction for pivot rotation
		//hold for turnHoldTime
		//finch code blocks program execution until time has elapsed
		busy=false;
		
	}
	
	public boolean isPathBlocked(){
		return myFinch.isObstacle();
		//finch reports if either obstacle sensor is blocked
	}
	
	public boolean isFinchBusy(){
		return busy;
	}
	
	public void quitfinch(){
		myFinch.quit();
	}
	
}
