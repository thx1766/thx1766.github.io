package movement;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Class will be used to start the demo
 * In the future will be used to load/start the movement of the data
 * 
 * @author Mikhail Kroik
 * @edited by Igor Kaplunov
 *
 */



public class grid extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static grid instance = null;
	public static int SizeX;
	public static int SizeY;
	private FinchGrid Inner_Grid;
	
	private JLabel[][] array;
	
	private GridBagLayout gridbag;
	private GridBagConstraints item;
	
	private grid()
	{
		super("Demo");

		// set's up gridbag style layout and array
		array = new JLabel[SizeX][SizeY];
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		
		
		//Add Underlying Grid
		this.Inner_Grid = new FinchGrid(SizeX, SizeY); 
		
		//JLabel lable;
		JButton button;
		
		java.net.URL imgURL = grid.class.getResource("img/(0)Default_Tile.jpg");
		ImageIcon icon = new ImageIcon(imgURL, "random");
		
		LoadSide(); /*loads sides*/
	
		for(int i =1 ; i < SizeX-1; i++)
		{
			for(int y =1 ; y < SizeY-1 ; y++)
			{
			array[i][y] = new JLabel(icon);
			LoadJLabel(array[i][y], i, y);
			}
		}
		/*
		button = new JButton("click me");
	    button.addActionListener(new ActionListener() {
	    	  
	            public void actionPerformed(ActionEvent e)
	            {
	            	java.net.URL imgURL = grid.class.getResource("img/(101)Finch_North.jpg");
	        		ImageIcon icon = new ImageIcon(imgURL, "random");
	                System.out.println("Click");
	                array[4][4].setIcon(icon);
	                Inner_Grid.setFinchLocation(4,4);
	                
	                
	            }
	        }); 
	   // item.insets = new Insets(10,0,0,0);
	    item.gridx = 0;
		item.gridy = 11;
		item.gridwidth = 7;
		gridbag.setConstraints(button, item);
		add(button);
		*/
	}
	
	public void TurnFinchClockwise() // Finch Manipulation Methods Start Here
	{
		int[] coords = (int[])this.Inner_Grid.turnFinchClockwise();
		int type1 = this.Inner_Grid.getTileType(coords[0], coords[1]);
		this.set(coords[0], coords[1], type1);

	}
	
	public void TurnFinchCounterClockwise() 
	{
		int[] coords = (int[])this.Inner_Grid.turnFinchCounterClockwise();
		int type1 = this.Inner_Grid.getTileType(coords[0], coords[1]);
		this.set(coords[0], coords[1], type1);
		
	}
	
	public static void pause(int ms)
	{
		try
		{
			Thread.currentThread();
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void MoveFinchForward()
	{
		int[] coords = (int[])this.Inner_Grid.moveForward();
		int type1 = this.Inner_Grid.getTileType(coords[0], coords[1]);
		this.set(coords[0], coords[1], type1);
		int type2 = this.Inner_Grid.getTileType(coords[2], coords[3]);
		this.set(coords[2], coords[3], type2);
		
	}
	
	
	public static grid getInstance()
	{
		if(instance == null) {
			 SizeX = 10;
			 SizeY = 10;
	         instance = new grid();
	      }
		return instance;
	}
	
	public static grid setInstance(int x, int y)
	{
		
			SizeX = x;
			SizeY = y;
			instance = new grid();
	      
		return instance;
	}
	//.set(int x, int y, int tyle)
	
	private void LoadSide() {
		//JButton button;
		java.net.URL imgURL = grid.class.getResource("img/(203)TopLeftCorner_Wall.jpg");
		ImageIcon icon = new ImageIcon(imgURL, "random");
		array[0][0] = new JLabel(icon);
		LoadJLabel(array[0][0], 0, 0);
		
		imgURL = grid.class.getResource("img/(202)Horizontal_Wall.jpg");
		icon = new ImageIcon(imgURL, "random");
		for(int x = 1; x < SizeX-1 ; x++)
		{
		array[x][0] = new JLabel(icon);
		LoadJLabel(array[x][0], x, 0);
		array[x][SizeY-1] = new JLabel(icon);
		LoadJLabel(array[x][SizeY-1], x, SizeY-1);
		}
		
		imgURL = grid.class.getResource("img/(201)Vertical_Wall.jpg");
		icon = new ImageIcon(imgURL, "random");
		
		for(int x = 1 ; x < SizeY-1 ; x++ )
		{
			array[0][x] = new JLabel(icon);
			LoadJLabel(array[0][x], 0, x);
			array[SizeX-1][x] = new JLabel(icon);
			LoadJLabel(array[SizeX-1][x], SizeX-1, x);
			
		}
		
		imgURL = grid.class.getResource("img/(204)TopRightCorner_Wall.jpg");
		icon = new ImageIcon(imgURL, "random");
		array[SizeX-1][0] = new JLabel(icon);
		LoadJLabel(array[SizeX-1][0], SizeX-1, 0);
		
		imgURL = grid.class.getResource("img/(206)BottomLeftCorner_Wall.jpg");
		icon = new ImageIcon(imgURL, "random");
		array[0][SizeY -1] = new JLabel(icon);
		LoadJLabel(array[0][SizeY -1], 0, SizeY -1);
		
		imgURL = grid.class.getResource("img/(205)BottomRightCorner_Wall.jpg");
		icon = new ImageIcon(imgURL, "random");
		array[SizeX-1][SizeY -1] = new JLabel(icon);
		LoadJLabel(array[SizeX-1][SizeY -1], SizeX-1, SizeY -1);
		
		
	}

	private void LoadJLabel(JLabel label, int x, int y)
	{
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = x;
		item.gridy = y;		
		gridbag.setConstraints(label, item);
		add(label);
	}

	public boolean set(int x, int y, int type) // Finished
	{
		ImageIcon icon;
		java.net.URL imgURL;
		if(x>=SizeX || y>=SizeY ) //error case
		{
				return false;
		}
		
		switch(type)
		{
			case 0:
				imgURL = grid.class.getResource("img/(0)Default_Tile.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			case 101:
				imgURL = grid.class.getResource("img/(101)Finch_North.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 102:
				
				imgURL = grid.class.getResource("img/(102)Finch_NorthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 103:
				imgURL = grid.class.getResource("img/(103)Finch_East.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 104:
				imgURL = grid.class.getResource("img/(104)Finch_SouthEast.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 105:
				imgURL = grid.class.getResource("img/(105)Finch_South.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 106:
				imgURL = grid.class.getResource("img/(106)Finch_SouthWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 107:
				imgURL = grid.class.getResource("img/(107)Finch_West.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 108:
				imgURL = grid.class.getResource("img/(108)Finch_NothWest.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 201:
				imgURL = grid.class.getResource("img/(201)Vertical_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 202:
				imgURL = grid.class.getResource("img/(202)Horizontal_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 203:
				imgURL = grid.class.getResource("img/(203)TopLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
			
			case 204:
				imgURL = grid.class.getResource("img/(204)TopRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 205:
				imgURL = grid.class.getResource("img/(205)BottomRightCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 206:
				imgURL = grid.class.getResource("img/(206)BottomLeftCorner_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 207:
				imgURL = grid.class.getResource("img/(207)NorthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 208:
				imgURL = grid.class.getResource("img/(208)SouthT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 209:
				imgURL = grid.class.getResource("img/(209)WestT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			case 210:
				System.out.println("We got into set");
				imgURL = grid.class.getResource("img/(210)EastT_Wall.jpg");
				icon = new ImageIcon(imgURL, "random");
				this.array[x][y].setIcon(icon);
				break;
				
			
		}
		
			
		
		return true;
	}
	
	public void placeFinch()
	{
		java.net.URL imgURL = grid.class.getResource("img/(101)Finch_North.jpg");
	    ImageIcon icon = new ImageIcon(imgURL, "random");  
	    System.out.println("Placed Finch");
	    this.array[1][8].setIcon(icon);
	    this.Inner_Grid.setFinchLocation(1,8);
		
	}
	
	
	
	
	public static void demo() {
		// TODO Auto-generated method stub
		
		grid icon = setInstance(10,10);
		icon.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//icon.pack();
		//icon.setLocationRelativeTo(null);
		icon.setMinimumSize(icon.getPreferredSize());
		//icon.setSize(600, 400);
		icon.setVisible(true);
		icon.placeFinch();
		
		icon.set(2, 9, 208);
		icon.set(2, 8, 201);
		icon.set(2, 7, 201);
		icon.set(2, 6, 201);
		icon.set(2, 5, 203);
		icon.set(3, 5, 202);
		icon.set(4, 5, 202);
		icon.set(5, 5, 204);
		icon.set(5,6,201);
		icon.set(5,7,201);
		icon.set(5,8,201);
		icon.set(5,9,208);
	
		icon.set(0,3,210);
		icon.set(1, 3, 202);
		icon.set(2, 3, 202);
		icon.set(3, 3, 202);
		icon.set(4, 3, 202);
		icon.set(5, 3, 205);
		icon.set(5, 2, 201);
		icon.set(5, 1, 201);
		icon.set(5, 0, 207);
		icon.set(6, 0, 0);
		icon.set(7,0,207);
		icon.set(7, 1, 201);
		icon.set(7, 2, 201);
		icon.set(7, 3, 201);
		icon.set(7, 4, 201);
		icon.set(7, 5, 201);
		icon.set(7, 6, 201);
		icon.set(7, 7, 201);
		icon.set(7, 8, 201);
		icon.set(7, 9, 208);
		
		
		pause(1000);
		icon.MoveFinchForward();
		
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.TurnFinchClockwise();
		icon.TurnFinchClockwise();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.TurnFinchClockwise();
		icon.TurnFinchClockwise();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.MoveFinchForward();
		pause(1000);
		icon.TurnFinchClockwise();
		icon.TurnFinchClockwise();
		pause(1000);
		icon.TurnFinchCounterClockwise();
		icon.TurnFinchCounterClockwise();
		icon.TurnFinchCounterClockwise();
		icon.TurnFinchCounterClockwise();
		pause(1000);
		//new Error("Dead End");
		icon.TurnFinchCounterClockwise();
		icon.TurnFinchCounterClockwise();
		//new Error("RUN!!!");
		for(int i =0; i <7 ; i++ )
		{
			icon.MoveFinchForward();
			pause(350);
		}
		
		
		
	}

}
