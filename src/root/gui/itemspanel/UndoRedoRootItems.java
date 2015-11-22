package root.gui.itemspanel;

import java.util.Queue;
import java.util.Stack;

public class UndoRedoRootItems {
	
	private Stack<OldRootItem> oldRootItems = new Stack<OldRootItem>();
	private Queue<OldRootItem> 
	
	public void addOldItemToHistory(OldRootItem oldRootItem) {
		oldRootItems.push(oldRootItem);
	}
	
	public void redo() {
		oldRootItems.
	}

}
