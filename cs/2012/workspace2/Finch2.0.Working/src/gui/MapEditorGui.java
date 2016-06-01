package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

//import Util.Error;

/**
 * GUI display of Map Editor
 * Allows users to edit saved map or make a new map.
 */
public class MapEditorGui extends JFrame implements  WindowListener {
	
	
	StudioGui caller;
	
	
	private int changeto;
	/**
	 * Array used for buttons
	 */
	private JButton[][] array; //see if you can delete it
		
	/**
	 * Storage for object when saved
	 */
	private int type[][];
	
	/**
	 * Current Choice of Chords From the user
	 */
	private int x,y;
	
	/**
	 * 
	 */
	public ButtonGroup group;
	
	//internal class
	private GridBagLayout gridbag;
	private GridBagConstraints item;
	
	
	public void setNumb(int x, int y)
	{
		type[x][y] = changeto;
		set(array[x][y],changeto);
	}
	
	public void newNumb(int num)
	{
		changeto = num;
	}
	
	/**
	 * Construct the GUI display and initialize a new Map on start
	 * 
	 * @param x size for horizontal bound
	 * @param y size for vertical bound
	 */
	MapEditorGui(StudioGui caller)
	{
		
		
		super("Grid");
		 
		this.addWindowListener(this);
	
		this.caller = caller;
		changeto = 0;
		this.x = 12;
		this.y = 12;
		
		array = new JButton[12][12];
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		
		
		class ButtonListen implements ActionListener {
		
			MapEditorGui caller;
			ButtonListen(MapEditorGui x)
			{
				caller = x;
			}
			
		    public synchronized void  actionPerformed(ActionEvent ev) {
		    	
		    
		    	if(group.getSelection()!=null){
		    	
		    	JToggleButton temp = (JToggleButton)ev.getSource();
		    	String find = 	temp.getText();
		    	if(find == "Clear/Reset")
		    	{
		    		caller.reset();
		    	}
		    	else if(find == "Load")
		    	{
		    		try {
						int got[][] = IOUtils.loadMap(caller);
						caller.load(got);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	else if(find == "Save")
		    	{
		    		try {
						IOUtils.saveMap(type, caller);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    	}
		    	System.out.println(temp.getText() + " <-- Clicked " );
		    	 //TODO put in handles u can get the name as temp.getText() or however else u find useful code goes here
		    	 group.clearSelection();
		    	}}
		    }
		
		 item.gridx = 0;
		 item.gridy = 0;
		 item.gridheight=5;
		 item.anchor = GridBagConstraints.NORTHEAST;
		 item.insets = new Insets(0,5,0,20);
		
		group = new ButtonGroup();
		JPanel panel = new JPanel(new GridLayout(0, 1));
		Border border = BorderFactory.createTitledBorder("Options");
		panel.setBorder(border);
		
		JToggleButton temp = new JToggleButton("Load");
		temp.addActionListener(new ButtonListen(this));
		panel.add(temp);
		group.add(temp);
		
		temp = new JToggleButton("Save");
		temp.addActionListener(new ButtonListen(this));
		panel.add(temp);
		group.add(temp);
		
		temp = new JToggleButton("Clear/Reset");
		temp.addActionListener(new ButtonListen(this));
		panel.add(temp);
		group.add(temp);
		
		gridbag.setConstraints(panel, item);
		add(panel);
		
		
		java.net.URL imgURL = MapEditorGui.class.getResource("../img/(0)Default_Tile.jpg");
		ImageIcon icon  = new ImageIcon(imgURL, "random");
		type = new int[x][y];
		for(int i =0; i < x ; i++)
		{
			for(int j =0 ; j < y; j++ )
			{
				type[i][j] = 0;
				array[i][j] = new JButton(icon);
				ContextMenuMapEditor contextMenu = new ContextMenuMapEditor(i, j, this);
				array[i][j].setComponentPopupMenu(contextMenu);
				LoadJLabel(array[i][j], i+1, j);
			}
		}
		
		JPanel pane = new side(this);
		
		pane.setBorder(BorderFactory.createCompoundBorder
				(BorderFactory.createRaisedBevelBorder() , BorderFactory.createRaisedBevelBorder() ));
		
		//pane.add(new JButton("Epic"));
		
		item.gridx = x+1;
		item.gridy = 0;
		item.insets = new Insets(20,20,20,20);
		item.gridheight = y;
		item.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(pane, item);
		add(pane);
		
		
		
		
		//end of sizing
		Dimension current = this.getPreferredSize();
		double scale = 1.05;
		current.setSize(current.width * scale , current.height * scale);
		this.setMinimumSize(current);
		
		
	}
	
	public void Active(boolean state)
	{
		
		this.setVisible(state); 
	}
	
	
	protected void load(int x[][])
	{
		for(int i =0; i < 12 ; i++)
		{
			for(int j =0 ; j < 12; j++ )
			{
				type[i][j] =  x[i][j];
				set(array[i][j], x[i][j]);
				System.out.println(x[i][j]);
			}
		}
	}
	
	
	
	private void LoadJLabel(JButton label, int x, int y)
	{
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = x;
		item.gridy = y;		
		label.addActionListener(new EditAction(x-1,y, this));
		gridbag.setConstraints(label, item);
		add(label);
	}
	
	/**
	 * sets a tile of specified type on (x,y)
	 * 
	 * @param x x cord
	 * @param y y code 
	 * @param type type of tile
	 * @return 
	 * 
	 * @return if worked
	 */

	
	/**
	 * resets map to all blank cells
	 */
	public void reset()
	{
		for(int i =0; i < 12 ; i++)
		{
			for(int j =0 ; j < 12; j++ )
			{
				type[i][j] =  0;
				set(array[i][j], 0);
				//System.out.println(x[i][j]);
			}
		}
	}
	
	public void deleteTile(int i, int j){
		int temp = changeto;
		changeto = 0;
		setNumb(i, j);
		changeto= temp;
	}
	
	public static boolean set(JButton change, int type) // Finished
	{
		ImageIcon icon;
		java.net.URL imgURL;
		
		
		switch(type)
		{
			case 0:
				imgURL = MapEditorGui.class.getResource("../img/(0)Default_Tile.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			case 101:
				imgURL = MapEditorGui.class.getResource("../img/(101)Finch_North.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 102:
				
				imgURL = MapEditorGui.class.getResource("../img/(102)Finch_NorthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 103:
				imgURL = MapEditorGui.class.getResource("../img/(103)Finch_East.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 104:
				imgURL = MapEditorGui.class.getResource("../img/(104)Finch_SouthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 105:
				imgURL = MapEditorGui.class.getResource("../img/(105)Finch_South.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 106:
				imgURL = MapEditorGui.class.getResource("../img/(106)Finch_SouthWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 107:
				imgURL = MapEditorGui.class.getResource("../img/(107)Finch_West.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 108:
				imgURL = MapEditorGui.class.getResource("../img/(108)Finch_NothWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 109:
				imgURL = MapEditorGui.class.getResource("../img/(109)Finch_Destroyed.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 201:
				imgURL = MapEditorGui.class.getResource("../img/(201)Vertical_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 202:
				imgURL = MapEditorGui.class.getResource("../img/(202)Horizontal_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 203:
				imgURL = MapEditorGui.class.getResource("../img/(203)TopLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 204:
				imgURL = MapEditorGui.class.getResource("../img/(204)TopRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 205:
				imgURL = MapEditorGui.class.getResource("../img/(205)BottomRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 206:
				imgURL = MapEditorGui.class.getResource("../img/(206)BottomLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 207:
				imgURL = MapEditorGui.class.getResource("../img/(207)NorthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 208:
				imgURL = MapEditorGui.class.getResource("../img/(208)SouthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 209:
				imgURL = MapEditorGui.class.getResource("../img/(209)WestT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 210:
				
				imgURL = MapEditorGui.class.getResource("../img/(210)EastT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 211:
				
				imgURL = MapEditorGui.class.getResource("../img/(211)Cross_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 301:
				
				imgURL = MapEditorGui.class.getResource("../img/(301)Movable_Block_Red.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 302:
				
				imgURL = MapEditorGui.class.getResource("../img/(302)Movable_Block_Blue.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 303:
				
				imgURL = MapEditorGui.class.getResource("../img/(303)Movable_Block_Green.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 304:
				
				imgURL = MapEditorGui.class.getResource("../img/(304)Movable_Block_Yellow.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 701:
				
				imgURL = MapEditorGui.class.getResource("../img/(701)Spike.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
			
			case 702:
				
				imgURL = MapEditorGui.class.getResource("../img/(702)Chomper.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			case 601:
				
				imgURL = MapEditorGui.class.getResource("../img/(601)Objective.jpg");
				icon = new ImageIcon(imgURL, "random");
				change.setIcon(icon);
				break;
				
			
		}

		return true;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("cloing");
		caller.ChangeMapName("Map Loaded");
		caller.setmap(type);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}

/**
 * 
 * Action Listener for MapEditGUI
 */
class EditAction implements ActionListener {

	/**
	 * 
	 */
	int x,y;
	
	/**
	 * 
	 */
	MapEditorGui caller;
	
	/**
	 * Sets the enumb
	 * 
	 * @param numb pass in the number
	 */

    	EditAction(int x, int y, MapEditorGui caller) 
    	{
    
    	this.x =x;
    	this.y =y;
    	this.caller = caller;
    	}

    /**
     * calls when a button is clicked (as long as its set to it)
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("click at x:" + x +" y:" + y);
        caller.setNumb(x,y);
        
    }
    
    
}

class side extends JPanel{

	
	private static final long serialVersionUID = 1L;
	MapEditorGui edit;
	ArrayList<JButton> buttons;
	GridBagLayout gridbag;
	side(MapEditorGui edit)
	{
	buttons = new ArrayList<JButton>();
	 gridbag = new GridBagLayout();
	
	setLayout(gridbag); 
	this.edit = edit;
	
	doTheWork(new JButton(), 0, 0,0);
	doTheWork(new JButton(), 101, 0,1);
	doTheWork(new JButton(), 103, 1,1);
	doTheWork(new JButton(), 105, 2,1);
	doTheWork(new JButton(), 107, 3,1);
	
	//hor vertical
	doTheWork(new JButton(), 201, 1,0);
	doTheWork(new JButton(), 202, 2,0);
	doTheWork(new JButton(), 211, 3,0);
	
	//corners
	doTheWork(new JButton(), 203, 0,2);
	doTheWork(new JButton(), 204, 1,2);
	doTheWork(new JButton(), 205, 2,2);
	doTheWork(new JButton(), 206, 3,2);
	
	//twalls

	doTheWork(new JButton(), 207, 0,3);
	doTheWork(new JButton(), 208, 1,3);
	doTheWork(new JButton(), 209, 2,3);
	doTheWork(new JButton(), 210, 3,3);
	
	doTheWork(new JButton(), 301, 0,4);
	doTheWork(new JButton(), 302, 1,4);
	doTheWork(new JButton(), 303, 2,4);
	doTheWork(new JButton(), 304, 3,4);
	
	doTheWork(new JButton(), 601, 0,5);
	doTheWork(new JButton(), 701, 1,5);
	doTheWork(new JButton(), 702, 2,5);
	
		
	
	}
	
	private void doTheWork(JButton add, int node, int x, int y)
	{
		GridBagConstraints item = new GridBagConstraints();	
		MapEditorGui.set(add, node);
		add.addActionListener(new SideAction(node , edit));
		
		item.gridx = x;
		item.gridy = y;
		gridbag.setConstraints(add,item ) ;
		add(add);
	}
	
}

class SideAction implements ActionListener {

	
	int number;
	MapEditorGui call;
	
    public SideAction(int numb, MapEditorGui call) {
       
    	number = numb;
        this.call = call;
    }

    /**
     * calls when a button is clicked (as long as its set to it)
     */
    public void actionPerformed(ActionEvent e) {
    	//will tell Frame Call what was changed, JFrame will have a last clicked X,Y from the grid and update it accordly
    	System.out.println("clicked #" + number);
    	call.newNumb(number);
    }
}

