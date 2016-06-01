import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


public class EditView extends JFrame {
	public MainMenuPanel menu;
	public JMenuBar menubar;
	JMenu file, edit;
	public JMenuItem save, exit, newAction, editAction;
	GridBagConstraints c = new GridBagConstraints();
	
	public EditView(){
		super("EditView");
		
		setLayout(new GridBagLayout());
		setLocation(100,100);
		setSize(1000,700);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		menubar = createMenuBar();
		menubar.setBackground(new Color(233,233,233));
		this.setJMenuBar(menubar);
		
		JButton run = new JButton("Run");
		c.gridx = 1;
		c.gridy = 0;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		add(run, c);
		
		JLabel main = new JLabel("Main Menu");
		c.gridy=0;
		c.gridx=0;
		add(main, c);
		c.gridx = 0;
		c.gridy = 1;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		c.gridheight = 1;
		c.gridwidth = 1;
		menu = new MainMenuPanel();
		add(menu, c);
		
		JTextArea code = new JTextArea();
		code.setPreferredSize(new Dimension(400, 200));
		c.fill = GridBagConstraints.BOTH;
		c.gridx=1;
		c.gridy = 1;
		c.gridheight=1;
		c.gridwidth =1;
		add(code, c);
		
		JLabel action = new JLabel("Action Menu");
		c.gridy=0;
		c.gridx=2;
		add(action, c);
		c.gridx = 2;
		c.gridy = 1;
		//c.weightx = 2;
		//c.weighty = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		ActionsPanel menu2 = new ActionsPanel();
		add(menu2, c);
		
		JLabel tool = new JLabel("Tool Menu");
		c.gridy=2;
		c.gridx=2;
		add(tool, c);
		c.gridx=2;
		c.gridy=3;
		ToolsPanel menu3 = new ToolsPanel();
		add(menu3,c);
		
		setVisible(true);
		setResizable(false);
	}
	
	private JMenuBar createMenuBar(){
		menubar = new JMenuBar();
		
		file = new JMenu("File");
		file.setBackground(new Color(233,233,233));
		save = new JMenuItem("Save");
		//save.addActionListener(this);
		file.add(save);
		file.addSeparator();
		exit = new JMenuItem("EXIT");
		//exit.addActionListener(this);
		file.add(exit);
		menubar.add(file);
		
		edit = new JMenu("Edit");
		edit.setBackground(new Color(233,233,233));
		newAction = new JMenuItem("Make New Action");
		edit.add(newAction);
		editAction = new JMenuItem("Edit Existing Action");
		edit.add(editAction);
		menubar.add(edit);
		
		return menubar;
	}
	
	public static void main(String[] args) {
		new EditView();
	}
}
