package root.gui;

import global.Category;
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
import root.gui.itemspanel.RootItemsPanel_ManualPanel_ViewButtonListener;
import root.gui.operations.RootOperationsTableModel;
import root.gui.profitspanel.ProfitsTableModel;

public class RootSearchButtonListener implements ActionListener {

	private Component parent;
	private Category category;
	private int day;
	private int year;
	private Month month;
	private JTabbedPane TabbedPane;
	private RootOperationsTableModel operationsTableModel;
	private JTextField ToolBar_ItemTextField; 
	private JComboBox<Month> OperationsPanel_ManualPanel_MonthComboBox;
	private JSpinner OperationsPanel_ManualPanel_YearSpinner;
	private JPanel OperationsPanel_OperationsTablePanel;
	private JComboBox<Category> OperationsPanel_ManualPanel_CategoryComboBox;
	private JSpinner ProfitsPanel_ManualPanel_DaySpinner;
	private JComboBox<Month> ProfitsPanel_ManualPanel_MonthComboBox;
	private JSpinner ProfitsPanel_ManualPanel_YearSpinner;
	private JComboBox<Category> ProfitsPanel_ManualPanel_CategoryComboBox;
	private JPanel ProfitsPanel_ProfitsTablePanel;
	private ProfitsTableModel profitsTableModel;
	private RootItemsPanel_ManualPanel_ViewButtonListener rootItemsPanel_ManualPanel_ViewButtonListener;
	
	public RootSearchButtonListener(Component parent, JTabbedPane TabbedPane, JTextField ToolBar_ItemTextField, 
			JComboBox<Month> OperationsPanel_ManualPanel_MonthComboBox,
			JSpinner OperationsPanel_ManualPanel_YearSpinner,
			RootOperationsTableModel operationsTableModel,
			JPanel OperationsPanel_OperationsTablePanel,
			JComboBox<Category> OperationsPanel_ManualPanel_CategoryComboBox,
			JSpinner ProfitsPanel_ManualPanel_DaySpinner,
			JComboBox<Month> ProfitsPanel_ManualPanel_MonthComboBox,
			JSpinner ProfitsPanel_ManualPanel_YearSpinner,
			JComboBox<Category> ProfitsPanel_ManualPanel_CategoryComboBox,
			JPanel ProfitsPanel_ProfitsTablePanel,
			ProfitsTableModel profitsTableModel,
			RootItemsPanel_ManualPanel_ViewButtonListener rootItemsPanel_ManualPanel_ViewButtonListener) {
		this.OperationsPanel_ManualPanel_CategoryComboBox = OperationsPanel_ManualPanel_CategoryComboBox;
		this.OperationsPanel_ManualPanel_MonthComboBox = OperationsPanel_ManualPanel_MonthComboBox;
		this.OperationsPanel_ManualPanel_YearSpinner = OperationsPanel_ManualPanel_YearSpinner;
		this.OperationsPanel_OperationsTablePanel = OperationsPanel_OperationsTablePanel;
		this.operationsTableModel = operationsTableModel;
		this.ToolBar_ItemTextField = ToolBar_ItemTextField;
		this.TabbedPane = TabbedPane;
		this.ProfitsPanel_ManualPanel_DaySpinner = ProfitsPanel_ManualPanel_DaySpinner;
		this.ProfitsPanel_ManualPanel_MonthComboBox = ProfitsPanel_ManualPanel_MonthComboBox;
		this.ProfitsPanel_ManualPanel_YearSpinner = ProfitsPanel_ManualPanel_YearSpinner;
		this.ProfitsPanel_ManualPanel_CategoryComboBox = ProfitsPanel_ManualPanel_CategoryComboBox;
		this.ProfitsPanel_ProfitsTablePanel = ProfitsPanel_ProfitsTablePanel;
		this.rootItemsPanel_ManualPanel_ViewButtonListener = rootItemsPanel_ManualPanel_ViewButtonListener;
		this.profitsTableModel = profitsTableModel;
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		startSearching();
	}

	public void startSearching() {
		if(ToolBar_ItemTextField.getText().equals("")) {
			return;
		}
		searchItem();
	}
	
	public void viewProfits(String search) {
		day = (int) ProfitsPanel_ManualPanel_DaySpinner.getValue();
		month = Month.valueOf(ProfitsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		year = (int) ProfitsPanel_ManualPanel_YearSpinner.getValue();
		category = Category.valueOf(ProfitsPanel_ManualPanel_CategoryComboBox.getSelectedItem().toString());
		
		if(month == Month.All) {
			changeProfitsPanelBorderNoMonths(year);
		} else {
			if(day == 0) {
				changeProfitsPanelBorderNoDays(year, month);
			} else {
				changeProfitsPanelBorder(year, month, day);
			}
		}
		
		try {
			profitsTableModel.loadFromDatabase(day, month, year, category, search);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void viewOperations(String search){
		month = Month.valueOf(OperationsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		year = (int) OperationsPanel_ManualPanel_YearSpinner.getValue();
		category = Category.valueOf(OperationsPanel_ManualPanel_CategoryComboBox.getSelectedItem().toString());
		changeOperationsBorder(year, month);
		
		try {
			operationsTableModel.loadFromDatabase(0, month, year, category, search);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void changeOperationsBorder(int year, Month month){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		OperationsPanel_OperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date) + " - " + category + " (Search mode)" , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeProfitsPanelBorderNoDays(int year, Month month){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date) + " - " + category + " (Search mode)" , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeProfitsPanelBorderNoMonths(int year){
		Calendar calendar = new GregorianCalendar(year, 3 , 12);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOutersimpledateformatNoMonths(date) + " - " + category + " (Search mode)" , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeProfitsPanelBorder(int year, Month month, int day){
		Calendar calendar = new GregorianCalendar(year, 3 , day);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(date) + " - " + category + " (Search mode)" , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}

	private void searchItem() {
		
		switch (TabbedPane.getSelectedIndex()) {
		case 0:
			rootItemsPanel_ManualPanel_ViewButtonListener.viewAllItems(ToolBar_ItemTextField.getText());
			break;

		case 1:
			viewProfits(ToolBar_ItemTextField.getText());
			break;

		default:
			viewOperations(ToolBar_ItemTextField.getText());
			break;
		}
	}
	
}
