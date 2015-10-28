package root.gui.profitspanel;

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

public class ProfitsPanel_ViewButtonListener implements ActionListener {

	private Component parent;
	private Month month;
	private int year;
	private int day;
	private Category category;
	private JComboBox<Month> ProfitsPanel_ManualPanel_MonthComboBox;
	private JComboBox<Category> ProfitsPanel_ManualPanel_CategoryComboBox;
	private ProfitsTableModel profitsTableModel;
	private JSpinner ProfitsPanel_ManualPanel_DaySpinner;
	private JSpinner ProfitsPanel_ManualPanel_YearSpinner;
	private JPanel ProfitsPanel_ProfitsTablePanel;
	private JComboBox<Worker> ProfitsPanel_ManualPanel_SellerComboBox;
	
	public ProfitsPanel_ViewButtonListener(Component parent, 
			JComboBox<Month> ProfitsPanel_ManualPanel_MonthComboBox,
			JComboBox<Category> ProfitsPanel_ManualPanel_CategoryComboBox,
			ProfitsTableModel profitsTableModel,
			JSpinner ProfitsPanel_ManualPanel_DaySpinner,
			JSpinner ProfitsPanel_ManualPanel_YearSpinner,
			JPanel ProfitsPanel_ProfitsTablePanel,
			JComboBox<Worker> ProfitsPanel_ManualPanel_SellerComboBox) {
		this.ProfitsPanel_ManualPanel_CategoryComboBox = ProfitsPanel_ManualPanel_CategoryComboBox;
		this.ProfitsPanel_ManualPanel_DaySpinner = ProfitsPanel_ManualPanel_DaySpinner;
		this.ProfitsPanel_ManualPanel_MonthComboBox = ProfitsPanel_ManualPanel_MonthComboBox;
		this.ProfitsPanel_ManualPanel_YearSpinner = ProfitsPanel_ManualPanel_YearSpinner;
		this.ProfitsPanel_ProfitsTablePanel = ProfitsPanel_ProfitsTablePanel;
		this.ProfitsPanel_ManualPanel_SellerComboBox = ProfitsPanel_ManualPanel_SellerComboBox;
		this.profitsTableModel = profitsTableModel;
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		viewAllOperations("");
	}
	
	private void changeBorder(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , day);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormat(date) + " - "  + category , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeBorderNoDays(){
		Calendar calendar = new GregorianCalendar(year, month.ordinal()-1 , 12);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOuterSimpleDateFormatNoDays(date) + " - "  + category , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	private void changeBorderNoMonths(){
		Calendar calendar = new GregorianCalendar(year, 3 , 12);
		Date date = calendar.getTime();
		ProfitsPanel_ProfitsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, DateFormats.getOutersimpledateformatNoMonths(date) + " - "  + category , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	public void viewAllOperations(String search){
		day = (int) ProfitsPanel_ManualPanel_DaySpinner.getValue();
		month = Month.valueOf(ProfitsPanel_ManualPanel_MonthComboBox.getSelectedItem().toString());
		year = (int) ProfitsPanel_ManualPanel_YearSpinner.getValue();
		category = Category.valueOf(ProfitsPanel_ManualPanel_CategoryComboBox.getSelectedItem().toString());
		String storedWorkerName = ProfitsPanel_ManualPanel_SellerComboBox.getSelectedItem().toString();
		
		if (month == Month.All) {
			changeBorderNoMonths();
		} else {
			if (day != 0) {
				changeBorder();
			} else {
				changeBorderNoDays();
			}
		}
		try {
			profitsTableModel.loadFromDatabase(day, month, year, category, storedWorkerName, search);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
	}

}
