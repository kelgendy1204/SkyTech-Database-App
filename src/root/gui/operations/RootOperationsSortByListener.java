package root.gui.operations;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;

public class RootOperationsSortByListener extends MouseAdapter {

	private JTable OperationsPanel_OperationsTable;
	private RootOperationsTableModel operationsTableModel;
	private Component parent;
	private boolean isStoredWorkerNameAsc, isStoredNameAsc, isAmountAsc, isDateAsc, isPaidAsc, isReturnedAsc, isUpdatedDateAsc, isIncomeAsc, isProfitAsc, isNotesAsc = true;
	
	public RootOperationsSortByListener(Component parent, JTable OperationsPanel_OperationsTable,
	RootOperationsTableModel operationsTableModel) {
		this.OperationsPanel_OperationsTable = OperationsPanel_OperationsTable;
		this.operationsTableModel = operationsTableModel;
		this.parent = parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = OperationsPanel_OperationsTable.columnAtPoint(e.getPoint());
		try {
			operationsTableModel.sortLastSQL(databaseColumnName(OperationsPanel_OperationsTable.getColumnName(column)));
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(parent, e1.getMessage());
			e1.printStackTrace();
		}
	}

	private String databaseColumnName(String columnName) {
		String sortByString;
		String orderingType;
		if(columnName.equals(RootOperationsTableModel.columnNames[0])){
			sortByString = null;
			orderingType = null;
		}else if(columnName.equals(RootOperationsTableModel.columnNames[1])){
			sortByString = "stored_name";

			if(isStoredNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isStoredNameAsc = !isStoredNameAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[2])){
			sortByString = "operations.amount";

			if(isAmountAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isAmountAsc = !isAmountAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[3])){
			sortByString = "operations.date";

			if(isDateAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isDateAsc = !isDateAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[4])){
			sortByString = "operations.paid";

			if(isPaidAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isPaidAsc = !isPaidAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[5])){
			sortByString = "operations.returned";

			if(isReturnedAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isReturnedAsc = !isReturnedAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[6])){
			sortByString = "operations.updated_date";

			if(isUpdatedDateAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isUpdatedDateAsc = !isUpdatedDateAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[7])){
			sortByString = "operations.income";

			if(isIncomeAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isIncomeAsc = !isIncomeAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[8])){
			sortByString = "operations.profit";

			if(isProfitAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isProfitAsc = !isProfitAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[9])){
			sortByString = "operations.stored_worker_name";

			if(isStoredWorkerNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isStoredWorkerNameAsc = !isStoredWorkerNameAsc;
			
		}else if(columnName.equals(RootOperationsTableModel.columnNames[10])){
			sortByString = "operations.notes";

			if(isNotesAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isNotesAsc = !isNotesAsc;
			
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
