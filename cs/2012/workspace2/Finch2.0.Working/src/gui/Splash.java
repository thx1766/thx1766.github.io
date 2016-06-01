package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loading temp = new Loading();
		boolean runRobot = temp.getstate();
		physical.finchDriver myFinchDriver=null;		
			if(runRobot){
		System.out.println("about to try creating Finch()");
		myFinchDriver = new physical.finchDriver();
		myFinchDriver.quitfinch();
		System.out.println("succeeded");
			}
		String act[] =
			   {
			        "New Program",
			        "Save Program",
			        "Load Program",
			        "Save As Method",
			        "Map Editor",
			        "Load Map",
			        "Preview Map",
			        "Trace Program",
			        "Run Program"
			        
			   };
		String other[]=	    {
	"Move Forward",
	"Small Turn Clockwise",
	"Small Turn Counter Clockwise",
	"Big Turn Clockwise",
	"Big Turn Counter Clockwise",
	"Push Object",
	"Checker Jump",
	"EXIT LOOP",
	
	null,
	"Is The Path Clear?",
	"Found Objective?",
	"Is Danger?",
	"Is Enemy In Front?",
	"Is Pushable Block In Front?",
	"Is Pushable Block Around?",
	"Is Spike In Front?",
	"Is Chomper In Front",
	
	"LOOP",
	null
		    };
		System.out.println("got here");
		StudioGui finalrun;
		if(runRobot)
			finalrun = new StudioGui(act, other, true);
		else
			finalrun = new StudioGui(act, other, false);
		
		finalrun.active(true);
		
		
		
		//Display Studio load screen while it
		//Run sub algo to check for read write access
		//see if it is the first time user is logging in
		//check to see if all files and good to go
		//load all maps (init) not active
		//when done close splash screen and open RunProgramGui
	}
	
	

}

class Loading extends JFrame{
    boolean state;
    Loading()
    {
            ImageIcon icon;
            java.net.URL imgURL;

    JLabel img = new JLabel("");
    imgURL = MapEditorGui.class.getResource("../img/splash.png");
    icon = new ImageIcon(imgURL, "random");
    img.setIcon(icon);
    add(img);

    Dimension current = this.getPreferredSize();
    double scale = 1.00;
    current.setSize(current.width * scale , current.height * scale);
    this.setMinimumSize(current);
    this.setVisible(true);

     state =
                    ConfirmationDialogBox.confirm("Do you want the Finch enabled?\nWarning You will need to reset the program to change this setting");

    try {
            Thread.sleep(1000);
    } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
    }



    }

    public boolean getstate() {

            this.setVisible(false);
            return state;

    }

}

