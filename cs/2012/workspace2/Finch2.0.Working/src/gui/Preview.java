package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class Preview extends JFrame{
	
	private GridBagLayout gridbag;
	private GridBagConstraints item;
	int map[][];
	Preview(int map[][])
	{
		
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		
		this.map = map;
		int x = 12;
		int y = 12;
		JLabel array[][] = new JLabel[x][y];
		
		for(int i = 0 ; i < x ; i ++)
		{
			for(int j = 0 ; j < y ; j++)
			{
				array[i][j] = new JLabel();
				
				loadJLabel(array[i][j], i, j);
			}
		}
		
		Dimension current = this.getPreferredSize();
		double scale = 1.05;
		current.setSize(current.width * scale , current.height * scale);
		this.setMinimumSize(current);
		this.setVisible(true);
	}
	
	private void loadJLabel(JLabel label, int x, int y)
	{
		RunProgramGui.set(label, map[x][y]);
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = x;
		item.gridy = y;		
		gridbag.setConstraints(label, item);
		add(label);
	}

}
