package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConfirmationDialogBox {
	
	public static boolean confirm(String question){
		JFrame frame = new JFrame();
		int result = JOptionPane.showConfirmDialog(frame, question);
		if(JOptionPane.CANCEL_OPTION == result || JOptionPane.NO_OPTION == result){
			return false;
		}else{
			return true;
		}
	}
}
