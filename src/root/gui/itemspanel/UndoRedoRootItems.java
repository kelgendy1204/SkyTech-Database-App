package root.gui.itemspanel;

import global.gui.ErrorMessage;

import java.sql.SQLException;

import javax.swing.JFrame;

import logic.SizedStack;

public class UndoRedoRootItems {

	public static final int UNKNOWN_ITEM_ROW_NUMBER = -5363;
	private static final int UNDOHISTORYSIZE = 20;
	private JFrame parent;
	private SizedStack<VersionedRootItem> undoRootItems = new SizedStack<VersionedRootItem>(UNDOHISTORYSIZE);
	private SizedStack<VersionedRootItem> redoRootItems = new SizedStack<VersionedRootItem>(UNDOHISTORYSIZE);
	private RootItemPanelTableModel rootItemPanelTableModel;

	public UndoRedoRootItems(JFrame parent, RootItemPanelTableModel rootItemPanelTableModel) {
		this.parent = parent;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
	}

	public void addOldItemToHistory(VersionedRootItem versionedRootItem) {
		undoRootItems.push(versionedRootItem);
		redoRootItems.clear();
	}

	public void undo() {
		VersionedRootItem versionedRootItem = undoRootItems.pop();
		redoRootItems.push(versionedRootItem);
		undoAction(versionedRootItem);
	}

	public void redo() {
		VersionedRootItem versionedRootItem = redoRootItems.pop();
		undoRootItems.push(versionedRootItem);
		redoAction(versionedRootItem);
	}

	private void redoAction(VersionedRootItem versionedRootItem) {
		switch (versionedRootItem.getType()) {

		case VersionedRootItem.INSERTED:

			try {
				rootItemPanelTableModel.insertItemToDatabase(versionedRootItem.getName(), versionedRootItem.getSellingPrice(), versionedRootItem.getBuyingPrice(), versionedRootItem.getAmount(), versionedRootItem.getCategory(), versionedRootItem.getNotes());
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}

			break;

		case VersionedRootItem.UPDATED:

			try {
				rootItemPanelTableModel.deleteItem(versionedRootItem.getNewRootItemUpdatedValues(), UNKNOWN_ITEM_ROW_NUMBER);
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
				rootItemPanelTableModel.insertItemToDatabase(versionedRootItem.getName(), versionedRootItem.getSellingPrice(), versionedRootItem.getBuyingPrice(), versionedRootItem.getAmount(), versionedRootItem.getCategory(), versionedRootItem.getNotes());
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
