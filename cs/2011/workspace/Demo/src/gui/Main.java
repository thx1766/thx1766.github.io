package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Main extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	
	private GridBagLayout gridbag;
	@SuppressWarnings("unused")
	private GridBagConstraints item;
	
	JButton actions[];
	JButton methods[];
	JList runTime;
	
	public Main()
	{
		//call this for a new window :)
		super("Finch Studio by THE 4");
		gridbag = new GridBagLayout();
		item = new GridBagConstraints();
		setLayout(gridbag);
		loadActions();
		loadJlist();
		loadMethods();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void loadMethods() {
		//TODO add to null responder
		String act[] =
		    {
		        "Walk" , "Loop" , "Dance", "Run" , null , "Shoot", "Reload", "Kill", "Drop the N Word"
		    };
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = 2;
		item.gridy = 0;
		//item.NORTHEAST
		//TODO change stick to north
		item.anchor =GridBagConstraints.NORTHEAST;
		actions = new JButton[act.length];
		for(int i =0 ; i < act.length; i++)
		{
			if(act[i]==null)
			{
				actions[i] = new JButton();
				actions[i].setEnabled(false);
				gridbag.setConstraints(actions[i], item);
				add(actions[i]);
			}
			else
			{
			actions[i] = new JButton(act[i]);
			//JButton current = actions[i];
			//current.setPreferredSize(preferredSize)
			gridbag.setConstraints(actions[i], item);
			add(actions[i]);
			}
			item.gridy++;
		}
	}

	private void loadActions() {
		String act[] =
		    {
		        "New Program" , "Save Program" , "Load Program", "Run"
		    };
		GridBagConstraints item = new GridBagConstraints();
	    item.gridx = 0;
		item.gridy = 0;
		//item.NORTHEAST
		//TODO change stick to north
		item.anchor =GridBagConstraints.NORTHEAST;
		actions = new JButton[act.length];
		for(int i =0 ; i < act.length-1; i++)
		{
			actions[i] = new JButton(act[i]);
			//JButton current = actions[i];
			//current.setPreferredSize(preferredSize)
			gridbag.setConstraints(actions[i], item);
			add(actions[i]);
			item.gridy++;
		}
		item.gridx=1;
		item.gridy=0;
		item.anchor = GridBagConstraints.NORTH;
		actions[act.length-1] = new JButton(act[act.length-1]);
		
		gridbag.setConstraints(actions[act.length-1], item);
		add(actions[act.length-1]);
		
		
		
	}
	
	private void loadJlist()
	{
		DefaultListModel model = new DefaultListModel();
		
		item.gridx = 1;
		item.gridy = 1;	
		item.gridheight =200;
		item.weightx = 1;
		item.weighty = 1;
		runTime = new JList(model);
		
		runTime.setCellRenderer(new Data());
		runTime.setSelectionBackground(Color.YELLOW);
		
		JScrollPane scrollPane = new JScrollPane(runTime);
		//runTime.setSize(200, 500);
		item.insets= new Insets(8,20,8,20);
		item.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(scrollPane, item);
		add(scrollPane);
		String something = "something";
		for(int i =0 ; i < 15 ; i++)
		{
		model.addElement(something);
		something = "\t" + something;
		//model.addElement("\t something");
		}
		
		
		
		
	}

	public void setVisible()
	{
		Dimension current = this.getPreferredSize();
		double scale = 1.2;
		current.setSize(current.width * scale , current.height * scale);
		this.setMinimumSize(current);
		this.setVisible(true);
	}
	
	private void attachAction(JButton current, int code)
	{
		
		current.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
			
			}});
	}
}
