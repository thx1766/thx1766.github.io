package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenuMapEditor extends JPopupMenu {
	protected MapEditorGui parent;
	protected int x, y;
	//(x,y) use to identify which button this context menu is belong to.
	public ContextMenuMapEditor(int x, int y, MapEditorGui parent){
		super();
		this.parent = parent;
		this.x = x;
		this.y = y;
		ContextMenuActionListener contextMenuActionListener = new ContextMenuActionListener();
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(contextMenuActionListener);
		this.add(delete);
	}
	
	class ContextMenuActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().compareTo("Delete")==0){
				parent.deleteTile(x, y);
			}
		}
		
	}

}
