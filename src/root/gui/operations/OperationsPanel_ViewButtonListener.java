package root.gui.operations;

import global.Category;
import global.Month;
import global.Worker;
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

import logic.DateFormats;

public class OperationsPanel_ViewButtonListener implements ActionListener {

	private Component parent;
	private Month month;
	private int year;
	private int day;
	private Category category;
	private JComboBox<Month> OperationsPanel_ManualPanel_MonthComboBox;
	private JComboBox<Category> OperationsPanel_ManualPanel_CategoryComboBox;
	private RootOperationsTableModel operationsTableModel;
	private JSpinner OperationsPanel_ManualPanel_DaySpinner;
	private JSpinner OperationsPanel_ManualPanel_YearSpinner;
	private JPanel OperationsPanel_OperationsTablePanel;
	private JComboBox<Worker> OperationsPanel_ManualPanel_SellerComboBox;

	public OperationsPanel_ViewButtonListener(
			Component parent,
			JComboBox<Month> OperationsPanel_ManualPanel_MonthComboBox,
			JComboBox<Category> OperationsPanel_ManualPanel_CategoryComboBox,
			RootOperationsTableModel operationsTableModel,
			JSpinner OperationsPanel_ManualPanel_DaySpinner,
			JSpinner OperationsPanel_ManualPanel_YearSpinner,
			JPanel OperationsPanel_OperationsTablePanel,
			JComboBox<Worker> OperationsPanel_ManualPanel_SellerComboBox) {
		this.parent = parent;
		this.OperationsPanel_ManualPanel_DaySpinner = OperationsPanel_ManualPanel_DaySpinner;
		this.OperationsPanel_ManualPanel_MonthComboBox = OperationsPanel_ManualPanel_MonthComboBox;
		this.OperationsPanel_ManualPanel_YearSpinner = OperationsPanel_ManualPanel_YearSpinner;
		this.OperationsPanel_OperationsTablePanel = OperationsPanel_OperationsTablePanel;
		this.operationsTableModel = operationsTableModel;
		this.OperationsPanel_ManualPanel_CategoryComboBox = OperationsPanel_ManualPanel_CategoryComboBox;
		this.OperationsPanel_ManualPanel_SellerComboBox = OperationsPanel_ManualPanel_SellerComboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		viewAllOperations("");
	}
	
	private void changeBorder(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , day);
		Date date = calendar.getTime();
		OperationsPanel_OperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(date) + " - "  + category , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeBorderNoDays(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		OperationsPanel_OperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date) + " - "  + category , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	public void viewAllOperations(String search){
		day = (int) OperationsPanel_ManualPanel_DaySpinner.getValue();
		month = Month.valueOf(OperationsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		year = (int) OperationsPanel_ManualPanel_YearSpinner.getValue();
		category = Category.valueOf(OperationsPanel_ManualPanel_CategoryComboBox.getSelectedItem().toString());
		String storedWorkerName = OperationsPanel_ManualPanel_SellerComboBox.getSelectedItem().toString();
		if(day != 0){
			changeBorder();
		} else {
			changeBorderNoDays();
		}
		
		try {
			operationsTableModel.loadFromDatabase(day, month, year, category, storedWorkerName, search);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}

}
