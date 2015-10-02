package root.gui.operations;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import normal.gui.alloperationspanel.Operation;
import root.gui.RootUserGUIFrame;

public class OperationsTableKeyListener extends KeyAdapter {

	private RootOperationsTableModel operationsTableModel;
	private JTable OperationsPanel_OperationsTable;
	private RootUserGUIFrame rootUserGUIFrame;

	public OperationsTableKeyListener(RootOperationsTableModel operationsTableModel,
			JTable OperationsPanel_OperationsTable,
			RootUserGUIFrame rootUserGUIFrame) {
		this.OperationsPanel_OperationsTable = OperationsPanel_OperationsTable;
		this.operationsTableModel = operationsTableModel;
		this.rootUserGUIFrame = rootUserGUIFrame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			int row = OperationsPanel_OperationsTable.getSelectedRow();
			Operation operation = operationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(rootUserGUIFrame, operation, 2 , row, null);
			editOperationsDialog.setOperationsTableModel(operationsTableModel);
			Dimension dimension = rootUserGUIFrame.getSize();
			editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
			editOperationsDialog.setVisible(true);
		}
	}
}
