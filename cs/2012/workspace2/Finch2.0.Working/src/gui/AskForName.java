package gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AskForName extends JDialog {

	public static String AskForName()
	{
		/*
		JTextField textField = new JTextField(20);
		Object[] array = {"Please Name Your Method", textField, textField};
		JOptionPane.showConfirmDialog(array,
                 JOptionPane.QUESTION_MESSAGE,
                 JOptionPane.YES_OPTION);
		
		return textField.getText();
		*/
		return JOptionPane.showInputDialog("Please input a value"); 
	
}
}
