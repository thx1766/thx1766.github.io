package Editing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import movement.grid;

public class edit extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JButton[][] array;
	@SuppressWarnings("unused")
	private int x,y;
	private GridBagLayout gridbag;
	@SuppressWarnings("unused")
	private GridBagConstraints item;
	private int type[][];
	
	edit(int x, int y)
	{
		super("Grid");
		if(x<=0||y<=0)
		{
			//TODO throw error
			return;
		}
		this.x=x; //copy over size
		this.y=y;
		array = new JButton[x][y]; //set Jlabel used for grid
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		type = new int[x][y];
		for(int i =0; i < x ; i++)
		{
			for(int j =0 ; j < y; j++ )
			{
				type[i][j] = 0;
			}
		}
		java.net.URL imgURL = grid.class.getResource("img/(0)Default_Tile.jpg");
		ImageIcon icon = new ImageIcon(imgURL, "random");
		/*sets up a blank board*/
			for(int i =0 ; i < x; i++){
			for(int j =0 ; j < y ; j++)
			{
			array[i][j] = new JButton(icon);
			LoadJLabel(array[i][j], i, j);
			}}
			
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
	}
		
		private void LoadJLabel(JButton label, int x, int y)
		{
			GridBagConstraints item = new GridBagConstraints();
		    item.gridx = x;
			item.gridy = y;		
			gridbag.setConstraints(label, item);
			add(label);
		}
		
		public void setVisible()
		{
			Dimension current = this.getPreferredSize();
			double scale = 1.2;
			current.setSize(current.width * scale , current.height * scale);
			this.setMinimumSize(current);
			this.setVisible(true);
		}
		
		/**
		 * Load's the outline of the grid if needed
		 */
		public void loadOutLine()
		{
			
			//set's up corners
			set(0,0,203);
			set(x-1,0,204);
			set(0,y-1,206);
			set(x-1,y-1,205);
			
			//set's up the top and bottom sides
			for(int i =1; i < x-1 ; i++)
			{
				set(i,0,202);
				set(i,y-1,202);
			}
			for(int i =1 ; i < y-1 ; i++)
			{
				set(0,i,201);
				set(x-1, i, 201);
				
			}
			
		}
		
		public boolean set(int x, int y, int type) // Finished
		{
			ImageIcon icon;
			java.net.URL imgURL;
			if(x>=this.x || y>=this.y ) //error case
			{
					return false;
			}
			this.type[x][y] = type; //changes main data
			
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
	

}