package normal.gui.alloperationspanel;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import normal.gui.NormalUserGUIFrame;


public class AllOperationsPanel_AllOperationsTableMousListener extends MouseAdapter {

	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private JTable AllOperationsPanel_AllOperationsTable;
	private NormalUserGUIFrame normalUserGUIFrame;
	private static final int tappedPaneIndex = 1;

	public AllOperationsPanel_AllOperationsTableMousListener(NormalUserGUIFrame normalUserGUIFrame, AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel,JTable AllOperationsPanel_AllOperationsTable) {
		this.AllOperationsPanel_AllOperationsTable = AllOperationsPanel_AllOperationsTable;
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
		this.normalUserGUIFrame = normalUserGUIFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			AllOperationsPanel_AllOperationsTable.getSelectionModel().clearSelection();
		}

		if(e.getClickCount() >= 2){
			int row = AllOperationsPanel_AllOperationsTable.rowAtPoint(e.getPoint());
			Operation operation = allOperationsPanel_AllOperationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(normalUserGUIFrame, operation, tappedPaneIndex , row, null);
			editOperationsDialog.setAllOperationsPanel_AllOperationsTableModel(allOperationsPanel_AllOperationsTableModel);
	        Dimension dimension = normalUserGUIFrame.getSize();
	        editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
	        editOperationsDialog.setVisible(true);
		}
		
	}
	
}
