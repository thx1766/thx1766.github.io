package codetree;

import java.io.Serializable;

public class Self extends Instruction implements Serializable{
	
	private int parameterMod;
	
	public Self(Instruction parent, int parMod)
	{
		super(parent);
		this.parameterMod = parMod;
		this.setType(5);
	}
	
	public int getParameterMod()
	{
		return this.parameterMod;
	}
}
