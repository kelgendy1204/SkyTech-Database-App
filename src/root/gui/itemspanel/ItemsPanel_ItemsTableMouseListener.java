package root.gui.itemspanel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import root.gui.AddEditItemsDialog;
import root.gui.RootUserGUIFrame;

public class ItemsPanel_ItemsTableMouseListener extends MouseAdapter {

	private RootUserGUIFrame rootUserGUIFrame; 
	private RootItemPanelTableModel rootItemPanelTableModel; 
	private JTable ItemsPanel_ItemsTable;
	
	public ItemsPanel_ItemsTableMouseListener(RootUserGUIFrame rootUserGUIFrame, 
			RootItemPanelTableModel rootItemPanelTableModel, 
			JTable ItemsPanel_ItemsTable) {
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.rootUserGUIFrame = rootUserGUIFrame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			ItemsPanel_ItemsTable.getSelectionModel().clearSelection();
		}
		
		if(e.getClickCount() >= 2){
			int row = ItemsPanel_ItemsTable.rowAtPoint(e.getPoint());
			RootItem item = rootItemPanelTableModel.items.get(row);
			AddEditItemsDialog addEditItemsDialog = new AddEditItemsDialog(rootUserGUIFrame, "edit", item, row, rootItemPanelTableModel, ItemsPanel_ItemsTable);
	        Dimension dimension = rootUserGUIFrame.getSize();
	        addEditItemsDialog.setLocation(dimension.width/2-addEditItemsDialog.getSize().width/2, dimension.height/2-addEditItemsDialog.getSize().height/2);
	        addEditItemsDialog.setVisible(true);
		}
		
	}

}
