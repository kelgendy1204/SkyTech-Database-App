package root.gui.operations;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import normal.gui.alloperationspanel.Operation;
import root.gui.RootUserGUIFrame;

public class OperationsTableMouseListener extends MouseAdapter {
	
	private RootOperationsTableModel operationsTableModel;
	private JTable OperationsPanel_OperationsTable;
	private RootUserGUIFrame rootUserGUIFrame;
	
	public OperationsTableMouseListener(RootOperationsTableModel operationsTableModel,
	JTable OperationsPanel_OperationsTable,
	RootUserGUIFrame rootUserGUIFrame) {
		this.OperationsPanel_OperationsTable = OperationsPanel_OperationsTable;
		this.operationsTableModel = operationsTableModel;
		this.rootUserGUIFrame = rootUserGUIFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			OperationsPanel_OperationsTable.getSelectionModel().clearSelection();
		}
		
		if(e.getClickCount() >= 2){
			int row = OperationsPanel_OperationsTable.rowAtPoint(e.getPoint());
			Operation operation = operationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(rootUserGUIFrame, operation, 2 , row, null);
			editOperationsDialog.setOperationsTableModel(operationsTableModel);
	        Dimension dimension = rootUserGUIFrame.getSize();
	        editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
	        editOperationsDialog.setVisible(true);
		}
	}
}
