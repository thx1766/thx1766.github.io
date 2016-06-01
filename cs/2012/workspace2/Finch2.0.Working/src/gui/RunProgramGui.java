package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import codetree.CodeTree;



/**
 * This will be the GUI for running the saved program on a saved map.
 * An interpreter will be responsible for setting all objects on RunProgramGui during the running of the program.
 *
 */


public class RunProgramGui extends JFrame {
	
	JScrollPane scrollPane;
	Data2 changeColor;
	Data2 changeColor2;
	
	/**
	 * Used for the img Storage/location
	 */
	private JLabel[][] array;
	
	/**
	 * Used for displaying messages to user about the code
	 */
	private JLabel JTextPane;
	
	/**
	 * Jlist data Model
	 */
	private DefaultListModel model ;
	private DefaultListModel macroModel;
	JScrollPane scrollPane2;
	
	/**
	 * Jlist for java swing
	 */
	private JList runTime;
	private JList macroRunTime;
	
	/**
	 * Used to keep track of size of the grid
	 */
	private int x,y;
	
	protected StepSwitch steps;
	
	//internal swing
	private GridBagLayout gridbag;
	private GridBagConstraints item;
	private CodeTree maintree;
	/**
	 * Creates the display for running program.
	 * It will first set up the loaded map then changing according to the running program
	 * @param maintree 
	 * 
	 * @param m Map is the program is running on
	 */
	public RunProgramGui(int x, int y, CodeTree maintree, StepSwitch steps)
	{
		super("Grid");
		
		this.steps = steps;
		if(x<=0||y<=0)
		{
			//TODO throw error
			return;
		}
		this.maintree = maintree;
		this.x=x; //copy over size
		this.y=y;
		array = new JLabel[x][y]; //set Jlabel used for grid
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		
		loadJlist();
		
		for(int i = 0 ; i < x ; i ++)
		{
			for(int j = 0 ; j < y ; j++)
			{
				array[i][j] = new JLabel();
				
				loadJLabel(array[i][j], i, j);
			}
		}
		

		//for each item in map load the coresponding Icon on the Map
		//adds JtextPanel at the bottom for user to see whats going on
	}
	
	public void SetTree(CodeTree tree)
	{
		this.maintree = tree;
	}
	
	/**
	 * Helper method to load onto the Gridbag Layout at x,y, the current label.
	 * 
	 * @param label whats loaded
	 * @param x x cord
	 * @param y y cord
	 */
	private void loadJLabel(JLabel label, int x, int y)
	{
		set(label, 0);
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = x+1;
		item.gridy = y;		
		gridbag.setConstraints(label, item);
		add(label);
	}
	
	private void loadJlist()
	{
		
		JButton load = new JButton("Stop");
		
		//load.setBorder(BorderFactory.createLineBorder(Color.black));
		load.addActionListener(new MapAction(0, this));
		item.gridx = 0;
		item.gridy = 0;	
		item.insets = new Insets(10,10,10,10);
		item.anchor = GridBagConstraints.NORTH;
		gridbag.setConstraints(load, item);
		add(load);
		
		JButton next = new  JButton("next");
		next.addActionListener(new MapAction(1, this));
		item.gridy = 1;	
		gridbag.setConstraints(next, item);
		add(next);
		
		model = new DefaultListModel();
		
		item.gridx = 0;
		item.gridy = 2;	
		item.gridheight =11;
		item.weightx = 1;
		item.weighty = 1;

		runTime = new JList(model);
		changeColor = new Data2();
		runTime.setCellRenderer(changeColor);
		
		
		scrollPane = new JScrollPane(runTime);
		
	
		
		item.insets= new Insets(0,20,10,20);
		item.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(scrollPane, item);
		add(scrollPane);
		
		item.gridy = 2+11;	
		item.gridheight =5;
		item.weightx = 1;
		item.weighty = 1;
		
		macroModel = new DefaultListModel();
		macroRunTime = new JList(macroModel);
		changeColor2 = new Data2();
		macroRunTime.setCellRenderer(changeColor2);
		
		scrollPane2 = new JScrollPane(macroRunTime);
		gridbag.setConstraints(scrollPane2, item);
		add(scrollPane2);
		
	
	}
		
	/**
	 * Turns the Map on and off
	 * 
	 * @param state the state you want
	 */
	public void active(boolean state)
	{
		
		Dimension current = this.getPreferredSize();
		double scale = 1.05;
		current.setSize(current.width * scale , current.height * scale);
		this.setMinimumSize(current);
		loadToJlist();
		this.setVisible(state); 
	}
	
	/**
	 * sets the Block on the coordinate(x,y) on the current display
	 * 
	 * @param x x cord
	 * @param y y code 
	 * @param b Block
	 * 
	 * @return true if it does not encounter problem with displaying
	 */
	public boolean setTile(int x, int y, int b)
	{
		return set(array[x][y], b);
		
	}
	
	
	
	/**
	 * resets all objects on the map to the start position
	 */
	
	
	public static boolean set(JLabel array, int type) // Finished
	{
		ImageIcon icon;
		java.net.URL imgURL;

		switch(type)
		{
			case 0:
				imgURL = MapEditorGui.class.getResource("../img/(0)Default_Tile.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			case 101:
				imgURL = MapEditorGui.class.getResource("../img/(101)Finch_North.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 102:
				
				imgURL = MapEditorGui.class.getResource("../img/(102)Finch_NorthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 103:
				imgURL = MapEditorGui.class.getResource("../img/(103)Finch_East.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 104:
				imgURL = MapEditorGui.class.getResource("../img/(104)Finch_SouthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 105:
				imgURL = MapEditorGui.class.getResource("../img/(105)Finch_South.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 106:
				imgURL = MapEditorGui.class.getResource("../img/(106)Finch_SouthWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 107:
				imgURL = MapEditorGui.class.getResource("../img/(107)Finch_West.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 108:
				imgURL = MapEditorGui.class.getResource("../img/(108)Finch_NorthWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 109:
				imgURL = MapEditorGui.class.getResource("../img/(109)Finch_Destroyed.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 201:
				imgURL = MapEditorGui.class.getResource("../img/(201)Vertical_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 202:
				imgURL = MapEditorGui.class.getResource("../img/(202)Horizontal_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 203:
				imgURL = MapEditorGui.class.getResource("../img/(203)TopLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
			
			case 204:
				imgURL = MapEditorGui.class.getResource("../img/(204)TopRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 205:
				imgURL = MapEditorGui.class.getResource("../img/(205)BottomRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 206:
				imgURL = MapEditorGui.class.getResource("../img/(206)BottomLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 207:
				imgURL = MapEditorGui.class.getResource("../img/(207)NorthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 208:
				imgURL = MapEditorGui.class.getResource("../img/(208)SouthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 209:
				imgURL = MapEditorGui.class.getResource("../img/(209)WestT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 210:
				
				imgURL = MapEditorGui.class.getResource("../img/(210)EastT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 211:
				
				imgURL = MapEditorGui.class.getResource("../img/(211)Cross_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 301:
				
				imgURL = MapEditorGui.class.getResource("../img/(301)Movable_Block_Red.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 302:
				
				imgURL = MapEditorGui.class.getResource("../img/(302)Movable_Block_Blue.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 303:
				
				imgURL = MapEditorGui.class.getResource("../img/(303)Movable_Block_Green.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 304:
				
				imgURL = MapEditorGui.class.getResource("../img/(304)Movable_Block_Yellow.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 701:
				
				imgURL = MapEditorGui.class.getResource("../img/(701)Spike.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 702:
				
				imgURL = MapEditorGui.class.getResource("../img/(702)Chomper.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			case 601:
				
				imgURL = MapEditorGui.class.getResource("../img/(601)Objective.jpg");
				icon = new ImageIcon(imgURL, "random");
				array.setIcon(icon);
				break;
				
			
		}

		return true;
	}
	
	

	public void loadToJlistMacro(CodeTree maintree)
	{
		macroModel.clear();
		maintree.PrintTreeToJlist(macroModel);
	}
	
	public void clearJlistMacro()
	{
		macroModel.clear();
	}
	
	
 	public void SelectLineMacro(int i )
    {
 		System.out.println("Called Macro at :" + i);
	 changeColor2.setLine(i);

	 scrollPane2.validate();
	 scrollPane2.repaint();
	
	

    }
	
	 	public void SelectLine(int i )
	    {
	 		//System.out.println("Called Normal at :" + i);
		 changeColor.setLine(i);
	
		 scrollPane.validate();
		 scrollPane.repaint();
		
		
	
	    }
	 
		public void AddLineContents(int i , String name)
		{
			model.add(i, name);
			//TODO WARNING UNTESTED if you have size of 10 and u add it to 12 might crash
		}
		
		public void loadToJlist()
		{
			maintree.PrintTreeToJlist(model);
		}
	    
}


class Data2 extends JLabel implements ListCellRenderer {

	private int colnumber;
	
    public Data2() {
        setOpaque(true);
        colnumber = -1;
    }
    
    public void setLine(int i )
    {
    	colnumber= i;
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        if(index == colnumber)
        {
        	setText(value.toString());
        	setBackground(Color.BLUE.brighter());
        	setForeground(Color.WHITE) ;
        	return this;
        }
    	
    	
    	setText(value.toString());
        
    	if(value.toString().contains("?") || value.toString().contains("IF"))
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
        else if(value.toString().contains("  "))
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

/**
 * 
 * Action Listener for RunProgramGUI
 */
class MapAction implements ActionListener {
    /**
     * Enumb of the button
     */
	int number;
	RunProgramGui caller;
	
	
	int temp;
	/**
	 * Sets the enumb
	 * 
	 * @param numb pass in the number
	 */
    public MapAction(int numb, RunProgramGui par) {
        number = numb;
        caller = par;
       
    }

    /**
     * calls when a button is clicked (as long as its set to it)
     */
    public void actionPerformed(ActionEvent e) {
    	if(number==0)
         {
      	   caller.steps.Stop();
      	   return;
         }
    	
    	
    	else if(number==1)
       {
    	   caller.steps.SwitchOn();
    	   return;
       }
       

    }
    
    
}
