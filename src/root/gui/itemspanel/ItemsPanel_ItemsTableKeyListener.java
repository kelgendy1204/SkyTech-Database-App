package root.gui.itemspanel;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import root.gui.AddEditItemsDialog;
import root.gui.AddToItemDialog;
import root.gui.RootUserGUIFrame;

public class ItemsPanel_ItemsTableKeyListener extends KeyAdapter {

	private RootUserGUIFrame rootUserGUIFrame; 
	private RootItemPanelTableModel rootItemPanelTableModel; 
	private JTable ItemsPanel_ItemsTable;
	private UndoRedoRootItems undoRedoRootItems;
	
	public ItemsPanel_ItemsTableKeyListener(RootUserGUIFrame rootUserGUIFrame, 
			RootItemPanelTableModel rootItemPanelTableModel, 
			JTable ItemsPanel_ItemsTable, UndoRedoRootItems undoRedoRootItems) {
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.rootUserGUIFrame = rootUserGUIFrame;
		this.undoRedoRootItems = undoRedoRootItems;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			int row = ItemsPanel_ItemsTable.getSelectedRow();
			RootItem item = rootItemPanelTableModel.items.get(row);
			AddEditItemsDialog addEditItemsDialog = new AddEditItemsDialog(rootUserGUIFrame, "edit", item, row, rootItemPanelTableModel, ItemsPanel_ItemsTable, undoRedoRootItems);
	        Dimension dimension = rootUserGUIFrame.getSize();
	        addEditItemsDialog.setLocation(dimension.width/2-addEditItemsDialog.getSize().width/2, dimension.height/2-addEditItemsDialog.getSize().height/2);
	        addEditItemsDialog.setVisible(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			AddToItemDialog addToItemDialog = new AddToItemDialog(rootUserGUIFrame, rootItemPanelTableModel, ItemsPanel_ItemsTable, undoRedoRootItems);
			Dimension dimension = rootUserGUIFrame.getSize();
			addToItemDialog.setLocation(dimension.width/2-addToItemDialog.getSize().width/2, dimension.height/2-addToItemDialog.getSize().height/2);
			addToItemDialog.setVisible(true);
		}
	}
}
