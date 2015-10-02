package normal.gui.alloperationspanel;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import normal.gui.NormalUserGUIFrame;

public class AllOperationsPanel_AllOperationsTableKeyListener extends KeyAdapter {

	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private JTable AllOperationsPanel_AllOperationsTable;
	private NormalUserGUIFrame normalUserGUIFrame;
	private static final int tappedPaneIndex = 1;

	public AllOperationsPanel_AllOperationsTableKeyListener(NormalUserGUIFrame normalUserGUIFrame, AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel,JTable AllOperationsPanel_AllOperationsTable) {
		this.AllOperationsPanel_AllOperationsTable = AllOperationsPanel_AllOperationsTable;
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
		this.normalUserGUIFrame = normalUserGUIFrame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			int row = AllOperationsPanel_AllOperationsTable.getSelectedRow();
			Operation operation = allOperationsPanel_AllOperationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(normalUserGUIFrame, operation, tappedPaneIndex, row, null);
			editOperationsDialog.setAllOperationsPanel_AllOperationsTableModel(allOperationsPanel_AllOperationsTableModel);
			Dimension dimension = normalUserGUIFrame.getSize();
			editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
			editOperationsDialog.setVisible(true);
		}
	}


}
