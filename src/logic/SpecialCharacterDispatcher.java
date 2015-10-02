package logic;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import normal.gui.NormalUserGUIFrame;

public class SpecialCharacterDispatcher implements KeyEventDispatcher {

	private NormalUserGUIFrame parent;
	private JComponent component;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private char specialCharacter = '`';
	private char specialCharacter2 = 'Ð';
	
	
	public SpecialCharacterDispatcher(NormalUserGUIFrame parent, JTabbedPane tabbedPane, JPanel panel, JComponent component) {
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
		parent.getDailyOperationPanel_DailyOperationTable().requestFocus();
		parent.getDailyOperationPanel_DailyOperationTable().setRowSelectionInterval(parent.getDailyOperationsPanel_DailyOperationsTableModel().getRowCount() - 1, parent.getDailyOperationsPanel_DailyOperationsTableModel().getRowCount() - 1);
		parent.getDailyOperationPanel_DailyOperationTable().setColumnSelectionInterval(parent.getDailyOperationsPanel_DailyOperationsTableModel().getColumnCount() - 1, parent.getDailyOperationsPanel_DailyOperationsTableModel().getColumnCount() - 1);
		parent.getDailyOperationPanel_DailyOperationTable().scrollRectToVisible(parent.getDailyOperationPanel_DailyOperationTable().getCellRect(parent.getDailyOperationPanel_DailyOperationTable().getRowCount()-1, 0, true));
	}

}
