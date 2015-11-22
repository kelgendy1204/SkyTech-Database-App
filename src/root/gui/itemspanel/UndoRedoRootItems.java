package root.gui.itemspanel;

import global.Database;
import logic.SizedStack;

public class UndoRedoRootItems {

	private SizedStack<OldRootItem> undoRootItems = new SizedStack<OldRootItem>(20);
	private SizedStack<OldRootItem> redoRootItems = new SizedStack<OldRootItem>(20);
	private Database database;
	private RootItemPanelTableModel rootItemPanelTableModel;

	public UndoRedoRootItems(Database database, RootItemPanelTableModel rootItemPanelTableModel) {
		this.database = database;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
	}
	
	public void addOldItemToHistory(OldRootItem oldRootItem) {
		undoRootItems.push(oldRootItem);

	}

	public void undo() {
		OldRootItem oldRootItem = undoRootItems.pop();
		redoRootItems.push(oldRootItem);
		doAction(oldRootItem);
	}

	public void redo() {
		OldRootItem oldRootItem = redoRootItems.pop();
		undoRootItems.push(oldRootItem);
		doAction(oldRootItem);
	}

	private void doAction(OldRootItem oldRootItem) {
		switch (oldRootItem.getType()) {
		
		case OldRootItem.INSERTED:
			redoInsertSql(oldRootItem);
			break;

		case OldRootItem.UPDATED:
			redoUpdateSql(oldRootItem);
			break;
			
		case OldRootItem.DELETED:
			redoDeleteSql(oldRootItem);
			break;
			
		default:
			break;
		}
		
	}

	private void redoDeleteSql(OldRootItem oldRootItem) {
		// TODO Auto-generated method stub
		
	}

	private void redoUpdateSql(OldRootItem oldRootItem) {
		// TODO Auto-generated method stub
		
	}

	private void redoInsertSql(OldRootItem oldRootItem) {
		// TODO Auto-generated method stub
		
	}
}
