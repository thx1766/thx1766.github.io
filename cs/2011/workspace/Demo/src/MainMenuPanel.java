import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class MainMenuPanel extends JPanel {
	public JList menu;
	public JScrollPane scroll;
	GridBagConstraints c = new GridBagConstraints();
	
	public MainMenuPanel(){
		super();
		
		setLayout(new GridBagLayout());
		
		/*JLabel name = new JLabel("Main Menu");
		c.gridx=0;
		c.gridy=0;
		add(name,c);*/
		
		String[] tmp = {"Select a Problem", "Create your own Problem", "etc"};
		menu = new JList(tmp);
		menu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menu.setLayoutOrientation(JList.VERTICAL);
		menu.setVisibleRowCount(-1);
		menu.setPreferredSize(new Dimension(200, 200));
		menu.setBackground(Color.WHITE);

		c.gridy=0;
		add(menu);
		
		scroll = new JScrollPane(menu);
		scroll.setPreferredSize(new Dimension(220, 200));
        //scroll.setViewportBorder(BorderFactory.createLineBorder(Color.black));
		add(scroll);
		
	}

}
