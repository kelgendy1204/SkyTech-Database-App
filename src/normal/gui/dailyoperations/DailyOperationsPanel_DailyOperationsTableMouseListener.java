package normal.gui.dailyoperations;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;

import normal.gui.NormalUserGUIFrame;
import normal.gui.alloperationspanel.Operation;
import normal.gui.itemspanel.Item;

public class DailyOperationsPanel_DailyOperationsTableMouseListener extends MouseAdapter {
	
	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private JTable dailyOperationsPanel_DailyOperationsTable;
	private NormalUserGUIFrame normalUserGUIFrame;
	private JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox;
	private static final int tappedPaneIndex = 0;
	
	public DailyOperationsPanel_DailyOperationsTableMouseListener(NormalUserGUIFrame normalUserGUIFrame, DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel, JTable dailyOperationsPanel_DailyOperationsTable, JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox) {
		this.dailyOperationsPanel_DailyOperationsTable = dailyOperationsPanel_DailyOperationsTable;
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
		this.normalUserGUIFrame = normalUserGUIFrame;
		this.DailyOperationPanel_ManualPanel_ItemsComboBox = DailyOperationPanel_ManualPanel_ItemsComboBox;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON3){
			dailyOperationsPanel_DailyOperationsTable.getSelectionModel().clearSelection();
		}
		
		if(e.getClickCount() >= 2){
			int row = dailyOperationsPanel_DailyOperationsTable.rowAtPoint(e.getPoint());
			Operation operation = dailyOperationsPanel_DailyOperationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(normalUserGUIFrame, operation, tappedPaneIndex , row, DailyOperationPanel_ManualPanel_ItemsComboBox);
			editOperationsDialog.setDailyOperationsPanel_DailyOperationsTableModel(dailyOperationsPanel_DailyOperationsTableModel);
	        Dimension dimension = normalUserGUIFrame.getSize();
	        editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
	        editOperationsDialog.setVisible(true);
		}
		
	}
	
}
