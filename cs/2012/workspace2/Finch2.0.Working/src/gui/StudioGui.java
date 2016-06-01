package gui;

import codetree.*;

import logicalmap.*;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import physical.finchDriver;



import interpreter.*;
/**
 * 
 * The GUI for the studio where you can edit, load, save your program
 *
 */
public class StudioGui extends JFrame {
	//line below added by Nat	
	physical.finchDriver studioguifinchdriver;
	private boolean finchthere;
	JComboBox macroList;
	JLabel mapName;
	FunctionList macroStore;
	//internal Java Swing Object
	private GridBagLayout gridbag;
	private GridBagConstraints item;
	private CodeTree maintree;
	private Interpreter inter;
	private LogicalMap map;
	private RunProgramGui rgui;
	private MapEditorGui mgui;
	private StepSwitch stSwitch;
	//private FunctionList flist;
	/**
	 * Jlist data Model
	 */
	private DefaultListModel model ;
	
	/**
	 * Jlist for java swing
	 */
	private JList runTime;
	
	/**
	 *  button's to group by
	 */
	public ButtonGroup group[];
	


	
	class ButtonList implements ActionListener {
		private int mode, number;
		StudioGui caller;
		ButtonList(int mode, int number, StudioGui caller)
		{
			this.mode = mode;
			this.number = number;
			this.caller=caller;
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
		
	    public synchronized void  actionPerformed(ActionEvent ev) {
	    	//delete code 
	    	if(mode == 4)
	    	{
	    		int item = runTime.getSelectedIndex();
		    	if(item== -1)
		    	{
		    		new Error("Please select something from the list");
		    		
		    	}
		    	else
		    	{
		    		//CODE GOES HERE
		    		try
		    		{
		    			int last = maintree.DeleteStatement(item);
		    			for(int i = item; i <= last; i++)
		    			{
		    				model.remove(item);
		    			}
		    		}
		    		catch(BlankLineSelectedException e)
		    		{
		    			new Error("You May Not Remove a Blank Line");
		    		}
		    		catch(LineNumberInvalidException e)
		    		{
		    			
		    		}
		    		
		    			
		    	}
	    		
		    	 group[mode].clearSelection();
		    	 
	    		if (model.getSize() == 0)
	    		{
	    			model.addElement("                        ");
	    		}
	    		
	    		
	    		return;
	    	}
	    	//this code will run to save/load things
	    	if(mode == 0)
	    	{
	    		
	    		JToggleButton temp = (JToggleButton)ev.getSource();
    	    	String text = temp.getText();
    	    	//String name = temp.getName();
    	    	
    	    	if(text.equals("Save Program"))
    	    	{
    	    		IOUtils.saveProgram(maintree, caller);
    	    	}
    	    	else if(text.equals("Load Program"))
    	    	{
    	    		caller.model.clear();
    	    		maintree = IOUtils.loadProgram(caller);
    	    		if(maintree != null)
    	    		{
    	    			maintree.PrintTreeToJlist(caller.model);
    	    			if(rgui != null)
    	    			{
    	    				rgui.SetTree(maintree);
    	    			}
    	    			inter.SetTree(maintree);
    	    		}
    	    	}
    	    	else if(text.equals("Preview Map"))
    	    	{
    	    		new Preview(map.getMapState());
    	    	}
    	    	else if(text.equals("Save As Method"))
    	    	{
    	    		//System.out.println("trying to save");
    	    		
    	    		String fname = AskForName.AskForName(); //FUNCTION CODE TO BE ADDED IK
    	    		macroStore.Save(fname, maintree);
    	    		macroList.addItem(fname);
    	    		System.out.println(fname);

    	    	}
    	    	else if(text.equals("New Program"))
    	    	{
    	    		boolean bx = ConfirmationDialogBox.confirm("Are you sure you want to start a new program?");
    	    		System.out.println(bx);
    	    		if(bx)
    	    		{
	    	    		maintree.ClearTree();
	    	    		model.clear();
	    	    		model.addElement("                        ");	
    	    		}
    	    		
    	    	}
    	    	
    	    	else if(text.equals("Run Program") || text.equals("Trace Program"))
    	    	{
    	    		if(map!=null)
    	    		{
    	    			stSwitch = new StepSwitch();
    	    			if(text.equals("Run Program"))
    	    			{
    	    				stSwitch.SetRun();
    	    			}
    	    			else
    	    			{
    	    				stSwitch.SetStep();
    	    			}
    	    			
    	    			
    	    			
    	    			rgui = new RunProgramGui(12, 12, maintree, stSwitch);
    	    			//Nat modified this constructor call to un-break code    	    		
        	    		inter = new Interpreter(maintree, map, rgui, macroStore, finchthere);
    	    			try
    	    			{
    	    				map.resetMap();
    	    				rgui.active(true);
    	    				StartGui start = new StartGui(inter, stSwitch);
    	    				Thread worker = new Thread(start);
    	    				worker.start();
    	    			}
    	    			catch(MapNotLoadedException e)
    	    			{
    	    				new Error("You Must Load a Map");
    	    			}
    	    			
    	    			}
    	    			else
    	    			{
    	    				new Error("Please load a map");
    	    			}
	    	    	}
	    	    	else if(text.equals("Map Editor"))
	    	    	{
	    	    		mgui = new MapEditorGui(caller);
	    	    		mgui.Active(true);
	    	    	}
	    	    	else if (text.equals("Load Map"))
	    	    	{
	    		    		try {
	    						int got[][] = IOUtils.loadMap(caller);
	    						
	    						map.passMap(got);
	    						ChangeMapName("Map Loaded");
	    						try
	    						{
	    							map.resetMap();
	    						}
	    						catch(MapNotLoadedException e)
	    						{
	    							
	    						}
	    					} catch (IOException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					} catch (ClassNotFoundException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    		    	
	    	    	}	    		
	    		
	    			group[mode].clearSelection();
	    			return;
	    		}
	    	// move foward , backwards, turning (actions)
	    	if(mode == 1)
	    	{
	    		boolean InvalidExitLoop = false;
	        	if(group[mode].getSelection()!=null){
	    	    	
	    	    	int item = runTime.getSelectedIndex();
	    	    	System.out.println(item);
	    	    	if(item== -1)
	    	    	{
	    	    		new Error("Please select something from the list");
	    	    		group[mode].clearSelection();
	    	    		return;
	    	    	}
	    	    		
	    	    		
	    	    	JToggleButton temp = (JToggleButton)ev.getSource();
	    	    	String text = temp.getText();
	    	    	String name = temp.getName();
	    	    	//CODE GOES HERE 
	    	    	String check = getLineContents(item);
	    	    	if(check.contains("END"))
	    	    	{
	    	    		new Error("You may not edit an end clause statement");
	    	    		group[mode].clearSelection();
	    	    	}
	    	    	else
	    	    	{
	    	    
		    	    	//ITEM IS POS NUMB AND TEXT IS TEXT
		    	    	String indent = "";
		    	    	int L = 0;
		    	    	try
		    	    	{
			    	    	if(text.contains("Move"))
			    	    	{
			    	    		
			    	    			L = maintree.AddAction(item, 1);
			    	    			
			    	    		
			    	    	}
			    	    	else if(text.contains("Small"))
			    	    	{
			    	    		if(text.contains("Counter"))
			    	    		{
			    	    			L = maintree.AddAction(item, 4);
			    	    		}
			    	    		else
			    	    		{
			    	    			L = maintree.AddAction(item, 2);
			    	    		}
			    	    	}
			    	    	else if(text.contains("Big"))
			    	    	{
			    	    		if(text.contains("Counter"))
			    	    		{
			    	    			L = maintree.AddAction(item, 5);
			    	    		}
			    	    		else
			    	    		{
			    	    			L = maintree.AddAction(item, 3);
			    	    		}
			    	    	}
			    	    	else if(text.contains("Push"))
			    	    	{
			    	    		L = maintree.AddAction(item, 6);
			    	    	}
			    	    	else if(text.contains("EXIT"))
			    	    	{
			    	    		
			    	    		L = maintree.AddAction(item, 20);
			    	    		
			    	    	}
			    	    	else if(text.contains("Checker"))
			    	    	{
			    	    		L = maintree.AddAction(item, 7);
			    	    	}
			    	    	else
			    	    	{
			    	    		
			    	    	}
		    	    	}
		    	    	catch(LineNumberInvalidException e)
		    	    	{
		    	    		
		    	    	}
		    	    	catch(BreakLoopInvalidException e)
		    	    	{
		    	    		InvalidExitLoop = true;
		    	    		new Error("You may not add an EXITLOOP command outside of a LOOP clause");
		    	    	}
		    	    	indent = getLevelIndent(L);
		    	    	if(!InvalidExitLoop)
		    	    	{
		    	    		model.add(item, indent + text);
		    	    	}
		    	    	
		    	    	group[mode].clearSelection();
		    	    	runTime.clearSelection();
		    	    	runTime.setSelectedIndex(item + 1);
	    	    	}
	    	    	 
	    	    }
	    		
	    	}
	    	// else type conditions
	    	if(mode == 2)
	    	{
	        	if(group[mode].getSelection()!=null){
	    	    	
	    	    	int item = runTime.getSelectedIndex();
	    	    	System.out.println(item);
	    	    	if(item== -1)
	    	    	{
	    	    		new Error("Please select something from the list");
	    	    		group[mode].clearSelection();
	    	    		return;
	    	    	}
	    	    	String check = getLineContents(item);	
	    	    	if(check.contains("END"))
	    	    	{
	    	    		
	    	    		new Error("You may not edit an end clause statement");
	    	    		group[mode].clearSelection();
	    	    	}
	    	    	else
	    	    	{
	    	    		
		    	    	JToggleButton temp = (JToggleButton)ev.getSource();
		    	    	String text = temp.getText();
		    	    	//CODE GOES HERE
		    	    	String indent = "";
		    	    	int L = 0;
		    	    	try
		    	    	{
			    	    	if(text.equals("LOOP"))
			    	    	{
			    	    		L = maintree.AddLoop(item);
			    	    		indent = getLevelIndent(L);
			    	    		model.add(item, indent + "LOOP");
			    	    		model.add(item + 1, "   ");
			    	    		model.add(item + 2, indent + "END LOOP");
			    	    	}
			    	    	else
			    	    	{
			    	    		if(text.contains("Clear"))
			    	    		{
			    	    			
			    	    			L = maintree.AddCondition(item, 1);
			    	    			
			    	    		}
			    	    		else if(text.contains("Objective"))
			    	    		{
			    	    			L = maintree.AddCondition(item, 2);
			    	    		}
			    	    		else if(text.contains("Danger"))
			    	    		{
			    	    			L = maintree.AddCondition(item, 3);
			    	    		}
			    	    		else if(text.contains("Enemy"))
			    	    		{
			    	    			L = maintree.AddCondition(item, 4);
			    	    		}
			    	    		else if(text.contains("Spike"))
			    	    		{
			    	    			L = maintree.AddCondition(item, 5);
			    	    		}
			    	    		else if(text.contains("Chomper"))
			    	    		{
			    	    			L = maintree.AddCondition(item, 6);
			    	    		}
			    	    		else if(text.contains("Pushable"))
			    	    		{
			    	    			if(text.contains("Front"))
			    	    			{
			    	    				L = maintree.AddCondition(item, 7);
			    	    			}
			    	    			else
			    	    			{
			    	    				L = maintree.AddCondition(item, 8);
			    	    			}
			    	    			
			    	    		}
			    	    		
			    	    		indent = getLevelIndent(L);
			    	    		model.add(item, indent + text);
			    	    		model.add(item+ 1, "   ");
			    	    		model.add(item+ 2, indent + "Else");
			    	    		model.add(item +3, "  " );
			    	    		model.add(item +4, indent + "END IF");
			    	    	}
			    	    	
			    	    	
			    	    	 
			    	    	 group[mode].clearSelection();
			    	    	 runTime.clearSelection();
			    	    	 if(text.equals("LOOP"))
			    	    	 {
			    	    		 runTime.setSelectedIndex(item + 3);
			    	    	 }
			    	    	 else
			    	    	 {
			    	    		 runTime.setSelectedIndex(item + 5);
			    	    	 }
		    	    	}
		    	    	catch(LineNumberInvalidException e)
		    	    	{
		    	    		
		    	    	}
	    	    	}
	        	}
	    	}
	    }
	    	    	
	    	    	
	    	    	
	    		
	        	
	    
	}
	    
	
	/**
	 * Default constructor for this class
	 * 
	 * @param array1 system buttons
	 * @param array2 Method buttons
	 */
	//Nat modified the constructor to pass finch driver from splash	
public StudioGui(String array1[], String array2[], boolean finchthere)
	{
		
		super("Farrel");
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		group = new ButtonGroup[5];
		loadActions(array1);
		loadJlist();
		loadMethods(array2);
		//this.flist = new FunctionList();
		//this.flist.Load();
		this.map = new LogicalMap();
			
		//Nat added the following
		this.finchthere = finchthere;
		
			
		ContextMenuStudio contex = new ContextMenuStudio(this,array2);
	
		
	
		
		Dimension current = this.getPreferredSize();
		double scale = 1.15;
		current.setSize(current.width * scale * 1.3 , current.height * scale );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setMinimumSize(current);
		this.maintree = new CodeTree();
		
		//Nat modified the below constructor call by rewriting constructor to pass finch driver to interpreter		
		this.inter = new Interpreter(this.maintree, this.map, this.rgui, this.macroStore, this.finchthere);
		

	}
	
	

	public JList getCurrentList(){
		return runTime;
	}
	
	public void deleteLine(ActionEvent e){
		ButtonList bl = new ButtonList(4, 0, this);
		bl.actionPerformed(e);
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
	
	private void loadMethods(String act[]) {
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = 2;
		item.gridy = 0;
		//item.NORTHEAST
		//TODO change stick to north
		item.anchor =GridBagConstraints.CENTER;
		
		int mode = 1;
		group[mode] = new ButtonGroup();
		JPanel panel = new JPanel(new GridLayout(0, 1));
		Border border = BorderFactory.createTitledBorder("Actions");
		panel.setBorder(border);
		for(int i =0 ; i < act.length; i++)
		{
			if(act[i]==null)
			{
				gridbag.setConstraints(panel, item);
				add(panel);
				mode++;
				group[mode] = new ButtonGroup();
				 panel = new JPanel(new GridLayout(0, 1));
				 border = BorderFactory.createTitledBorder("Conditions");
				 panel.setBorder(border);
				 item.gridy++;
				
			}
			else
			{
				JToggleButton temp = new JToggleButton(act[i]);
				temp.addActionListener(new ButtonList(mode, i,this));
				panel.add(temp);
				group[mode].add(temp);
			
			}
		}
		macroStore = new FunctionList();
		
		macroList = new JComboBox(macroStore.getNames());
		
		item.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(macroList, item);
		add(macroList);
		item.gridy++;
		
		JButton addn = new JButton("Insert");
		addn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				String temp = (String) macroList.getSelectedItem();
				CodeTree treex = macroStore.getFunction(temp);
				treex.PrintTree();
				int pos = runTime.getSelectedIndex();
				int L = 0;
				if(pos == -1)
				{
					new Error("You must select a line");
				}
				else
				{
					try
					{
						L = maintree.AddFunction(pos, temp);
						String indent = getLevelIndent(L);
						model.add(pos, indent + "FUNCTION: " + temp);
						runTime.clearSelection();
		    	    	runTime.setSelectedIndex(pos + 1);
					}
					catch(LineNumberInvalidException ex)
					{
						
					}
				}
			}
		
		});
		gridbag.setConstraints(addn, item);
		add(addn);
		
		item.gridy++;
		
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e)
			{
				
				String temp = (String) macroList.getSelectedItem();
				macroStore.remove(temp);
				macroList.removeItem(temp);
			}
		
		});
		gridbag.setConstraints(remove, item);
		add(remove);
		
	}

	private void loadActions(String act[]) {
	
		
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = 0;
		item.gridy = 0;
		item.gridheight = 1;
		item.anchor =GridBagConstraints.NORTHEAST;
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		Border border = BorderFactory.createTitledBorder("Program");
		panel.setBorder(border);
		group[0] = new ButtonGroup();
		
		JToggleButton actions[] = new JToggleButton[act.length];
		
		for(int i =0 ; i < act.length; i++)
		{
			actions[i] = new JToggleButton(act[i]);
			actions[i].addActionListener(new ButtonList(0, i, this));
			panel.add(actions[i]);
			group[0].add(actions[i]);
		}
		
		gridbag.setConstraints(panel, item);
		add(panel);
		
		//deleting code//
		panel = new JPanel(new GridLayout(0, 1));
		group[4] = new ButtonGroup();
		border = BorderFactory.createTitledBorder("Program");
		panel.setBorder(border);
		
		JToggleButton action[] = new JToggleButton[1];
		action[0]	= new JToggleButton("Delete");
		action[0].addActionListener(new ButtonList(4, 0,this));
		panel.add(action[0]);
		group[4].add(action[0]);
		 item.gridy = 1;
		 
			gridbag.setConstraints(panel, item);
			add(panel);
		 
	}
	
	private void loadJlist()
	{
		mapName = new JLabel("No Map Loaded");
		mapName.setBorder(BorderFactory.createLineBorder(Color.black));
		item.gridx = 1;
		item.gridy = 10;	
		item.insets = new Insets(10,10,5,10);
		
		item.anchor = GridBagConstraints.NORTH;
	
		gridbag.setConstraints(mapName, item);
		add(mapName);
		
		model = new DefaultListModel();
		
		item.gridx = 1;
		item.gridy = 0;	
		item.gridheight =10;
		item.weightx = 1;
		item.weighty = 1;

		runTime = new JList(model);
		runTime.setCellRenderer(new Data());
		
		
		JScrollPane scrollPane = new JScrollPane(runTime);
		
		item.insets= new Insets(8,20,8,20);
		item.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(scrollPane, item);
		add(scrollPane);
		
		model.addElement("                                  ");
		
	
	}
	
	public void ChangeMapName(String name)
	{
		//mapName.set
		mapName.setText(name);
	}

	/**
	 * Turns on and off the visability of the map
	 * 
	 * @param set the mode its going into
	 */
	public void active(boolean set)
	{
		this.setVisible(set); 
	}
	
	/**
	 * Add's into the Jlist at pos i the String Name
	 * 
	 * 
	 * @param i at pos try to add in the Jlist
	 * @param name add's the name
	 * @return if failed returns false
	 */
	public void AddLineContents(int i , String name)
	{
		model.add(i, name);
		//TODO WARNING UNTESTED if you have size of 10 and u add it to 12 might crash
	}
	
	public int getListSize()
	{
		
		return model.size();
		
	}
	public String getLineContents(int i)
	{
		if( i < model.size())
		{
			return (String) model.get(i);
		}
		
		return null;
				
	}
	
	
	/**
	 * Removes i from Jlist 
	 * @param i at pos from JList to remove
	 * @return if failed returns false, else its true
	 */
	public boolean removeAt(int i)
	{
		
		if(model.size()> i)
		{
			model.remove(i);
			return true;
		}
		//else
		return false;
	}

	public void setmap(int[][] type) {
		this.map.passMap(type);
		try
		{
			this.map.resetMap();
			ChangeMapName("Map Loaded");
		}
		
		catch(MapNotLoadedException e)
		{
			
		}
		
		
	}

}
/**
 * 
 * Action Listener for StudioGUI
 */
class StudioListener implements ActionListener {
    /**
     * Enumb of the button
     */
	int number;
	/**
	 * Sets the enumb
	 * 
	 * @param numb pass in the number
	 */
    public StudioListener(int numb) {
        number = numb;
    }

    /**
     * calls when a button is clicked (as long as its set to it)
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}






class Data extends JLabel implements ListCellRenderer {

    public Data() {
        setOpaque(true);
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        setText(value.toString());
        
    
        
        if(isSelected)
        {
        	setBackground(Color.BLACK);
        	setForeground(Color.WHITE) ;
        	
        	return this;
        }
        
        else if(value.toString().contains("?") || value.toString().contains("IF"))
        {
        	setBackground(Color.GREEN);
        }
        else if(value.toString().contains("Else"))
        {
        	setBackground(Color.RED.brighter());
        }
        
        else if(value.toString().contains("LOOP"))
        {
        	setBackground(Color.CYAN.darker());
        	//do nothing
        }
        else if(value.toString().matches("\\s+"))
        {
        	setBackground(Color.WHITE);
        }
        else
        {
        	setBackground(Color.YELLOW);
        }

        setForeground(Color.BLACK);
        return this;
    }
    
 
	
}
	
