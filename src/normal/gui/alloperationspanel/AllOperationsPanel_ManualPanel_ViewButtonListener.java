package normal.gui.alloperationspanel;

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

public class AllOperationsPanel_ManualPanel_ViewButtonListener implements
		ActionListener {

	private Month month;
	private int year;
	private int day;
	private JComboBox<Month> AllOperationsPanel_ManualPanel_MonthComboBox;
	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private JSpinner AllOperationsPanel_ManualPanel_DaySpinner;
	private JSpinner AllOperationsPanel_ManualPanel_YearSpinner;
	private JPanel allOperationsPanel_AllOperationsTablePanel;
	private Component parent;
	private JComboBox<Worker> AllOperationPanel_ManualPanel_SellerComboBox;

	public AllOperationsPanel_ManualPanel_ViewButtonListener(
			Component parent,
			JPanel AllOperationsPanel_AllOperationsTablePanel,
			AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel,
			JComboBox<Month> AllOperationsPanel_ManualPanel_MonthComboBox,
			JSpinner AllOperationsPanel_ManualPanel_DaySpinner,
			JSpinner AllOperationsPanel_ManualPanel_YearSpinner,
			JComboBox<Worker> AllOperationPanel_ManualPanel_SellerComboBox) {
		this.allOperationsPanel_AllOperationsTablePanel = AllOperationsPanel_AllOperationsTablePanel;
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
		this.AllOperationsPanel_ManualPanel_MonthComboBox = AllOperationsPanel_ManualPanel_MonthComboBox;
		this.AllOperationsPanel_ManualPanel_DaySpinner = AllOperationsPanel_ManualPanel_DaySpinner;
		this.AllOperationsPanel_ManualPanel_YearSpinner = AllOperationsPanel_ManualPanel_YearSpinner;
		this.parent = parent;
		this.AllOperationPanel_ManualPanel_SellerComboBox = AllOperationPanel_ManualPanel_SellerComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		viewAllOperations();
	}
	
	private void changeBorder(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , day);
		Date date = calendar.getTime();
		allOperationsPanel_AllOperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(date), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeBorderNoDays(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		allOperationsPanel_AllOperationsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	public void viewAllOperations(){
		day = (int) AllOperationsPanel_ManualPanel_DaySpinner.getValue();
		month = Month.valueOf(AllOperationsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		year = (int) AllOperationsPanel_ManualPanel_YearSpinner.getValue();
		String storedWorkerName;
		try {
			storedWorkerName = AllOperationPanel_ManualPanel_SellerComboBox.getSelectedItem().toString();
		} catch (java.lang.NullPointerException e1) {
			storedWorkerName = null;
			e1.printStackTrace();
		}
		
		if(day != 0){
			changeBorder();
		} else {
			changeBorderNoDays();
		}
		
		try {
			allOperationsPanel_AllOperationsTableModel.loadFromDatabase(day, month, year, storedWorkerName, "");
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}

}
