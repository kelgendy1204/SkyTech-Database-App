package root.gui.itemspanel;

import global.gui.ErrorMessage;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;

import logic.SizedStack;

public class UndoRedoRootItems {

	public static final int UNKNOWN_ITEM_ROW_NUMBER = -5363;
	private static final int UNDOHISTORYSIZE = 20;
	private JFrame parent;
	private SizedStack<VersionedRootItem> undoRootItems = new SizedStack<VersionedRootItem>(UNDOHISTORYSIZE);
	private SizedStack<VersionedRootItem> redoRootItems = new SizedStack<VersionedRootItem>(UNDOHISTORYSIZE);
	private RootItemPanelTableModel rootItemPanelTableModel;
	private JTable ItemsPanel_ItemsTable;

	public UndoRedoRootItems(JFrame parent, RootItemPanelTableModel rootItemPanelTableModel, JTable ItemsPanel_ItemsTable) {
		this.parent = parent;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
	}

	public void addOldItemToHistory(VersionedRootItem versionedRootItem) {
		undoRootItems.push(versionedRootItem);
		redoRootItems.clear();
	}

	public void undo() {
		if (!undoRootItems.empty()) {
			VersionedRootItem versionedRootItem = undoRootItems.pop();
			redoRootItems.push(versionedRootItem);
			undoAction(versionedRootItem);
		}
	}

	public void redo() {
		if (!redoRootItems.empty()) {
			VersionedRootItem versionedRootItem = redoRootItems.pop();
			undoRootItems.push(versionedRootItem);
			redoAction(versionedRootItem);
		}
	}

	private void redoAction(VersionedRootItem versionedRootItem) {
		switch (versionedRootItem.getType()) {

		case VersionedRootItem.INSERTED:

			try {
				rootItemPanelTableModel.insertItemToDatabase(versionedRootItem.getName(), versionedRootItem.getSellingPrice(), versionedRootItem.getBuyingPrice(), versionedRootItem.getAmount(), versionedRootItem.getCategory(), versionedRootItem.getNotes());
				ItemsPanel_ItemsTable.scrollRectToVisible(ItemsPanel_ItemsTable.getCellRect(ItemsPanel_ItemsTable.getRowCount()-1, 0, true));
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}

			break;

		case VersionedRootItem.UPDATED:

			try {
				rootItemPanelTableModel.updateItem(versionedRootItem.getNewRootItemUpdatedValues(), UNKNOWN_ITEM_ROW_NUMBER);
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}

			break;

		case VersionedRootItem.DELETED:

			try {
				rootItemPanelTableModel.deleteItem(versionedRootItem, UNKNOWN_ITEM_ROW_NUMBER);
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
	}

	private void undoAction(VersionedRootItem versionedRootItem) {
		switch (versionedRootItem.getType()) {

		case VersionedRootItem.INSERTED:

			try {
				rootItemPanelTableModel.deleteItem(versionedRootItem, UNKNOWN_ITEM_ROW_NUMBER);
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}

			break;

		case VersionedRootItem.UPDATED:

			try {
				rootItemPanelTableModel.updateItem(versionedRootItem, UNKNOWN_ITEM_ROW_NUMBER);
			} catch (SQLException e1) {
				ErrorMessage.showErrorMessage(parent, e1.getMessage());
				e1.printStackTrace();
			}

			break;

		case VersionedRootItem.DELETED:

			try {
				rootItemPanelTableModel.insertItemToDatabaseWithSpecificValues(versionedRootItem.getName(), versionedRootItem.getSellingPrice(), versionedRootItem.getBuyingPrice(), versionedRootItem.getAmount(), versionedRootItem.getCategory(), versionedRootItem.getNotes(), versionedRootItem.getItemId(), versionedRootItem.getCreatedAt(), versionedRootItem.getUpdatedAt(), versionedRootItem.getAvailableCapital());
				ItemsPanel_ItemsTable.scrollRectToVisible(ItemsPanel_ItemsTable.getCellRect(ItemsPanel_ItemsTable.getRowCount()-1, 0, true));
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

	}

}
