import javax.swing.JButton;
//import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finchStandaloneGUI extends JPanel implements ActionListener {
	protected JButton fwd, left, right, stop, turnon, qf;
	finchDriver myDriver;
	
	public finchStandaloneGUI(){
		
	qf = new JButton("send_quit_finch");
	qf.setActionCommand("quit_finch");

	turnon = new JButton("initialize finch");
	turnon.setActionCommand("boot_finch");
		
	fwd=new JButton("Step Forward");
	fwd.setActionCommand("step_finch_forward");

	left=new JButton("Rotate Left");
	left.setActionCommand("rotate_finch_left");

	right=new JButton("Rotate Right");
	right.setActionCommand("rotate_finch_right");

	stop=new JButton("Stop");
	stop.setActionCommand("stop_finch");
	
	fwd.addActionListener(this);
	left.addActionListener(this);
	right.addActionListener(this);
	stop.addActionListener(this);
	turnon.addActionListener(this);
	qf.addActionListener(this);
	
	add(fwd);
	add(left);
	add(right);
	add(stop);
	add(turnon);
	add(qf);
	}

	public void actionPerformed(ActionEvent e){
//PRINTLN WILL BE REPLACED WITH CALLS TO INTERFACE
//PRINTLN WILL BE REPLACED WITH CALLS TO INTERFACE
//PRINTLN WILL BE REPLACED WITH CALLS TO INTERFACE
//PRINTLN WILL BE REPLACED WITH CALLS TO INTERFACE
		if ("boot_finch".equals(e.getActionCommand())){
			System.out.println("about to initialize finchdriver");
		myDriver = new finchDriver();
	}
		if ("step_finch_forward".equals(e.getActionCommand()))
			//System.out.println("step fwd");
			myDriver.moveFinchForward();
		if ("rotate_finch_left".equals(e.getActionCommand()))
			//System.out.println("turn left");
			myDriver.turnFinchLeft();
		if ("rotate_finch_right".equals(e.getActionCommand()))
			//System.out.println("turn right");
			myDriver.turnFinchRight();
		if ("stop_finch".equals(e.getActionCommand()))
			System.out.println("stop");
			//myDriver
		if ("quit_finch".equals(e.getActionCommand()))
			myDriver.quitfinch();
		
	}
	
	private static void createAndShow() {

        //Create and set up the window.
        JFrame frame = new JFrame("FinchDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        finchStandaloneGUI newContentPane = new finchStandaloneGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShow(); 
            }
        });
    }
	
}