package normal.gui.dailyoperations;

import global.gui.EditOperationsDialog;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;

import normal.gui.NormalUserGUIFrame;
import normal.gui.alloperationspanel.Operation;
import normal.gui.itemspanel.Item;

public class DailyOperationsPanel_DailyOperationsTableKeyListener extends KeyAdapter {

	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private JTable dailyOperationsPanel_DailyOperationsTable;
	private NormalUserGUIFrame normalUserGUIFrame;
	private JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox;
	private static final int tappedPaneIndex = 0;

	public DailyOperationsPanel_DailyOperationsTableKeyListener(NormalUserGUIFrame normalUserGUIFrame, DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel, JTable dailyOperationsPanel_DailyOperationsTable,JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox) {
		this.dailyOperationsPanel_DailyOperationsTable = dailyOperationsPanel_DailyOperationsTable;
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
		this.normalUserGUIFrame = normalUserGUIFrame;
		this.DailyOperationPanel_ManualPanel_ItemsComboBox = DailyOperationPanel_ManualPanel_ItemsComboBox;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			int row = dailyOperationsPanel_DailyOperationsTable.getSelectedRow();
			Operation operation = dailyOperationsPanel_DailyOperationsTableModel.operations.get(row);
			EditOperationsDialog editOperationsDialog = new EditOperationsDialog(normalUserGUIFrame, operation, tappedPaneIndex, row, DailyOperationPanel_ManualPanel_ItemsComboBox);
			editOperationsDialog.setDailyOperationsPanel_DailyOperationsTableModel(dailyOperationsPanel_DailyOperationsTableModel);
			Dimension dimension = normalUserGUIFrame.getSize();
			editOperationsDialog.setLocation(dimension.width/2-editOperationsDialog.getSize().width/2, dimension.height/2-editOperationsDialog.getSize().height/2);
			editOperationsDialog.setVisible(true);
		}
	}

}
