package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import codetree.BlankLineSelectedException;
import codetree.LineNumberInvalidException;

public class ContextMenuStudio extends JPopupMenu {
	protected JPopupMenu popup = null;
	protected StudioGui parent;
	public ContextMenuStudio(StudioGui parent, String[] actionList){
		super();
		this.parent = parent;
		popup = this;
		ContextMenuActionListener contextMenuActionListener = new ContextMenuActionListener();
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(contextMenuActionListener);
		this.add(delete);
		//JMenuItem add = new JMenuItem("Add");
//		JMenu subAddMenu = new JMenu("Add");
//		popup.add(subAddMenu);
//		JMenu actionMenu = new JMenu("Actions");
//		subAddMenu.add(actionMenu);
//		JMenuItem[] actions = new JMenuItem[actionList.length];
//		int count=0;
//		for(int i=count; i<actions.length; i++){
//			if(actionList[i] == null){
//				i++;
//				count = i;
//				break;
//			}
//			actions[i] = new JMenuItem(actionList[i]);
//			actions[i].addActionListener(contextMenuActionListener);
//			actionMenu.add(actions[i]);
//		}
//		JMenu conditionMenu = new JMenu("Conditions");
//		subAddMenu.add(conditionMenu);
//		for(int j=count; j<actions.length; j++){
//			if(actionList[j] == null){
//				count = j;
//				break;
//			}
//			actions[j] = new JMenuItem(actionList[j]);
//			actions[j].addActionListener(contextMenuActionListener);
//			conditionMenu.add(actions[j]);
//		}
//		JMenu macroMenu = new JMenu("Macros");
//		subAddMenu.add(macroMenu);
		
		MouseListener popupListener = new PopupListener();
		parent.getCurrentList().addMouseListener(popupListener);
	}
	
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	    	maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	      maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	      if (e.isPopupTrigger()){
	    	  parent.getCurrentList().setSelectedIndex(parent.getCurrentList().locationToIndex(e.getPoint()));
	    	  popup.show(e.getComponent(), e.getX(), e.getY());
	      }
	    }
	  }
	
	class ContextMenuActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().compareTo("Delete")==0){
				parent.deleteLine(e);
			}

		}
		
	}
}
