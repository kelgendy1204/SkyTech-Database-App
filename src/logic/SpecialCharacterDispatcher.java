package logic;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import root.gui.RootUserGUIFrame;
import normal.gui.NormalUserGUIFrame;

public class SpecialCharacterDispatcher implements KeyEventDispatcher {

	private JFrame parent;
	private JComponent component;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private char specialCharacter = '`';
	private char specialCharacter2 = 'Ð';


	public SpecialCharacterDispatcher(JFrame parent, JTabbedPane tabbedPane, JPanel panel, JComponent component) {
		this.parent = parent;
		this.component = component;
		this.tabbedPane = tabbedPane;
		this.panel = panel;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getKeyChar() == specialCharacter || e.getKeyChar() == specialCharacter2) {
			e.consume();
		}

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyChar() == specialCharacter || e.getKeyChar() == specialCharacter2) {
				tabbedPane.setSelectedComponent(panel);
				component.requestFocusInWindow();
				return true;
			}
		} 

		if (e.getKeyCode() == KeyEvent.VK_DOWN && e.isAltDown()) {
			e.consume();
		}

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN && e.isAltDown()) {
				if(tabbedPane.getSelectedIndex() == 0){
					selectLastItemAdded();
					return true;
				}
			}
		} 

		return false;
	}

	private void selectLastItemAdded() {
		if(parent.getName().equals("Normal user GUI")){
			((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().requestFocus();
			((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().setRowSelectionInterval(((NormalUserGUIFrame) parent).getDailyOperationsPanel_DailyOperationsTableModel().getRowCount() - 1, ((NormalUserGUIFrame) parent).getDailyOperationsPanel_DailyOperationsTableModel().getRowCount() - 1);
			((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().setColumnSelectionInterval(((NormalUserGUIFrame) parent).getDailyOperationsPanel_DailyOperationsTableModel().getColumnCount() - 1, ((NormalUserGUIFrame) parent).getDailyOperationsPanel_DailyOperationsTableModel().getColumnCount() - 1);
			((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().scrollRectToVisible(((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().getCellRect(((NormalUserGUIFrame) parent).getDailyOperationPanel_DailyOperationTable().getRowCount()-1, 0, true));
		} else {
			((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().requestFocus();
			((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().setRowSelectionInterval(((RootUserGUIFrame) parent).getRootItemPanelTableModel().getRowCount() - 1, ((RootUserGUIFrame) parent).getRootItemPanelTableModel().getRowCount() - 1);
			((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().setColumnSelectionInterval(((RootUserGUIFrame) parent).getRootItemPanelTableModel().getColumnCount() - 1, ((RootUserGUIFrame) parent).getRootItemPanelTableModel().getColumnCount() - 1);
			((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().scrollRectToVisible(((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().getCellRect(((RootUserGUIFrame) parent).getItemsPanel_ItemsTable().getRowCount()-1, 0, true));
		}
	
	}

}
