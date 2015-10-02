package root.gui.profitspanel;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;

public class ProfitsSortByListener extends MouseAdapter {
	
	
	private JTable ProfitsPanel_ProfitsTable;
	private ProfitsTableModel profitsTableModel;
	private Component parent;
	private boolean isStoredNameAsc, isTrueAmountAsc, isProfitsAsc = true;
	
	public ProfitsSortByListener(Component parent, JTable ProfitsPanel_ProfitsTable,
			ProfitsTableModel profitsTableModel) {
		this.ProfitsPanel_ProfitsTable = ProfitsPanel_ProfitsTable;
		this.profitsTableModel = profitsTableModel;
		this.parent = parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = ProfitsPanel_ProfitsTable.columnAtPoint(e.getPoint());
		try {
			profitsTableModel.sortLastSQL(databaseColumnName(ProfitsPanel_ProfitsTable.getColumnName(column)));
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(parent, e1.getMessage());
			e1.printStackTrace();
		}
	}

	private String databaseColumnName(String columnName) {
		String sortByString;
		String orderingType;
		if(columnName.equals(ProfitsTableModel.columnNames[0])) {
			sortByString = null;
			orderingType = null;
		}else if(columnName.equals(ProfitsTableModel.columnNames[1])){
			sortByString = "stored_name";

			if(isStoredNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isStoredNameAsc = !isStoredNameAsc;
			
		}else if(columnName.equals(ProfitsTableModel.columnNames[2])){
			sortByString = "true_amount";

			if(isTrueAmountAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isTrueAmountAsc = !isTrueAmountAsc;
			
		}else if(columnName.equals(ProfitsTableModel.columnNames[3])){
			sortByString = "profits";

			if(isProfitsAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isProfitsAsc = !isProfitsAsc;
			
		}else {
			sortByString = null;
			orderingType = null;
		}
		
		if(sortByString != null){
			return sortByString + orderingType;
		} else {
			return sortByString;
		}
		
	}
}
