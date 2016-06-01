package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.*;
import javax.swing.JList;

public class Data extends JLabel implements ListCellRenderer {

    public Data() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        setText(value.toString());
        
        if(cellHasFocus)
        {
        	setBackground(Color.PINK);
        	return this;
        }
        // based on the index you set the color.  This produces the every other effect.
        if (index % 2 == 0) setBackground(Color.WHITE);
        else setBackground(Color.BLUE);

        return this;
    }
}
