package gui;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import codetree.CodeTree;

public class FunctionList {
	
	
	private Hashtable<String,CodeTree> hashtable;
	
	public FunctionList()
	{		
		hashtable = new Hashtable<String,CodeTree>();
		Load();
	}
	
	
	
	public String[] getNames()
	{
		Set<String> temp = hashtable.keySet();
		String ArrayTemp[] = new String[temp.size()];
		return temp.toArray(ArrayTemp);
		

	}
	
	
	public boolean Save(String name, CodeTree Macro)
	{
		if(hashtable.get(name)==null)
		{
			hashtable.put(name, Macro);
		}
		else
		{
			boolean con = ConfirmationDialogBox.confirm("Same Name Found are you sure you want to overwrite?");
			
			if(con)
			{
				
				//hashtable.remove(name);
				hashtable.put(name, Macro);
				
			}
		}
		
		try {
			IOUtils.saveMacro(hashtable, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new Error("Error Saving File");
			return false;
		}
		
		return true;
	}
	
	public CodeTree getFunction(String name)
	{
		return hashtable.get(name);
	}
	
	public void Load()
	{
		Hashtable<String, CodeTree> hs = null;
		try {
			hs = IOUtils.loadMacro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(hs != null)
		{
			this.hashtable = hs;
		}
	}



	public void remove(String temp) {
		hashtable.remove(temp);
		try {
			IOUtils.saveMacro(hashtable, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new Error("Error Saving File");
			
		}
	}
	
	

}
