import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class ActionsPanel extends JPanel {
	public JList menu;
	public JScrollPane scroll;
	public ActionsPanel(){
		super();
		
		String[] tmp = {"Move # Step", "Turn Left", "etc"};
		menu = new JList(tmp);
		menu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menu.setLayoutOrientation(JList.VERTICAL);
		menu.setVisibleRowCount(-1);
		menu.setPreferredSize(new Dimension(200, 200));
		menu.setBackground(Color.WHITE);
		/*c.insets = new Insets(2,5,2,5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 4;
		c.gridheight = 1;
		c.gridwidth = 4;*/
		add(menu);
		
		scroll = new JScrollPane(menu);
		scroll.setPreferredSize(new Dimension(220, 200));
        //scroll.setViewportBorder(BorderFactory.createLineBorder(Color.black));
		add(scroll);
		
	}
}
