package gui;



import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Will show an error to the user
 * 
 * @author Mikhail Kroik
 *
 */
@SuppressWarnings("serial")
public class Error extends JFrame {

	/**
	 * User to show an error
	 */
	private JButton showLabelButton;
	
	/**
	 * Will display the error to the user
	 * 
	 * @param e a string of the error of what happened
	 */
	public Error(String e)
	{
		super("error");
		
			JLabel label = new JLabel(e);
			label.setFont(new Font("Helvetica",Font.ITALIC,14));
			JOptionPane.showMessageDialog(showLabelButton, label, "Label", 
											JOptionPane.INFORMATION_MESSAGE);
		 
	
	}
}
