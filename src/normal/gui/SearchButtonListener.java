package normal.gui;

import global.Month;
import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logic.DateFormats;
import normal.gui.alloperationspanel.AllOperationsPanel_AllOperationsTableModel;
import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableModel;
import normal.gui.itemspanel.ItemsPanel_ManualPanel_ViewButtonListener;

public class SearchButtonListener implements ActionListener {

	private JTabbedPane TabbedPane;
	private JTextField ToolBar_ItemTextField;
	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private ItemsPanel_ManualPanel_ViewButtonListener itemsPanel_ManualPanel_ViewButtonListener;
	private JComboBox<Month> AllOperationsPanel_ManualPanel_MonthComboBox;
	private JSpinner AllOperationsPanel_ManualPanel_YearSpinner;
	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private JPanel allOperationsPanel_AllOperationsTablePanel;
	private JPanel DailyOperationPanel_DailyOperationTablePanel;
	private Component parent;

	public SearchButtonListener(Component parent, JTabbedPane TabbedPane, JTextField ToolBar_ItemTextField, 
			DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel,
			ItemsPanel_ManualPanel_ViewButtonListener itemsPanel_ManualPanel_ViewButtonListener,
			JComboBox<Month> AllOperationsPanel_ManualPanel_MonthComboBox,
			JSpinner AllOperationsPanel_ManualPanel_YearSpinner,
			AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel,
			JPanel allOperationsPanel_AllOperationsTablePanel,
			JPanel DailyOperationPanel_DailyOperationTablePanel) {
		this.parent = parent;
		this.TabbedPane = TabbedPane;
		this.ToolBar_ItemTextField = ToolBar_ItemTextField;
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
		this.itemsPanel_ManualPanel_ViewButtonListener = itemsPanel_ManualPanel_ViewButtonListener;
		this.AllOperationsPanel_ManualPanel_MonthComboBox = AllOperationsPanel_ManualPanel_MonthComboBox;
		this.AllOperationsPanel_ManualPanel_YearSpinner = AllOperationsPanel_ManualPanel_YearSpinner;
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
		this.allOperationsPanel_AllOperationsTablePanel = allOperationsPanel_AllOperationsTablePanel;
		this.DailyOperationPanel_DailyOperationTablePanel = DailyOperationPanel_DailyOperationTablePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startSearching();
	}

	public void startSearching() {
		if(ToolBar_ItemTextField.getText().equals("")) {
			return;
		}
		searchItem(ToolBar_ItemTextField.getText());
	}

	public void viewAllOperations(String search){
		Month month = Month.valueOf(AllOperationsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		int year = (int) AllOperationsPanel_ManualPanel_YearSpinner.getValue();
		changeAllOperationsBorder(year, month);

		try {
			allOperationsPanel_AllOperationsTableModel.loadFromDatabase(0, month, year, search);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}

	private void changeAllOperationsBorder(int year, Month month){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		allOperationsPanel_AllOperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date) + " (Search mode)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeDailyOperationsBorder(){
		DailyOperationPanel_DailyOperationTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(new Date()) + " (Search mode)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}

	public void searchItem(String search){
		switch (TabbedPane.getSelectedIndex()) {
		case 0:
			try {
				dailyOperationsPanel_DailyOperationsTableModel.loadFromDatabase(search);
			} catch (SQLException e1) {
				ErrorMessage.showErrorMessage(parent, e1.getMessage());
				e1.printStackTrace();
			}
			changeDailyOperationsBorder();
			break;

		case 1:
			viewAllOperations(search);
			break;

		default:
			itemsPanel_ManualPanel_ViewButtonListener.viewAllItems(search);
			break;
		}
	}

}
