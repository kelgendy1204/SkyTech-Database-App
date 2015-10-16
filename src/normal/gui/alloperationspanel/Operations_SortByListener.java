package normal.gui.alloperationspanel;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableModel;

public class Operations_SortByListener extends MouseAdapter {
	private JTable DailyOperationPanel_DailyOperationTable;
	private JTable AllOperationsPanel_AllOperationsTable;
	private JTabbedPane tappedPane;
	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private Component parent;
	private boolean isStoredNameAsc, isAmountAsc, isDateAsc, isPaidAsc, isReturnedAsc, isUpdatedDateAsc, isIncomeAsc, isNotesAsc = true;
	
	public Operations_SortByListener(
			Component parent, JTabbedPane tappedPane,
			DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel,
			AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel,
			JTable DailyOperationPanel_DailyOperationTable, 
			JTable AllOperationsPanel_AllOperationsTable) {
		this.tappedPane = tappedPane;
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
		this.DailyOperationPanel_DailyOperationTable = DailyOperationPanel_DailyOperationTable;
		this.AllOperationsPanel_AllOperationsTable = AllOperationsPanel_AllOperationsTable;
		this.parent = parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int column = 0;
			
			switch (tappedPane.getSelectedIndex()) {
			
			case 0:
				column = DailyOperationPanel_DailyOperationTable.columnAtPoint(e.getPoint());
				try {
					dailyOperationsPanel_DailyOperationsTableModel.sortLastSQL(databaseColumnName(DailyOperationPanel_DailyOperationTable.getColumnName(column)));
				} catch (SQLException e1) {
					ErrorMessage.showErrorMessage(parent, e1.getMessage());
					e1.printStackTrace();
				}
				break;
				
			case 1:
				column = AllOperationsPanel_AllOperationsTable.columnAtPoint(e.getPoint());
				try {
					allOperationsPanel_AllOperationsTableModel.sortLastSQL(databaseColumnName(AllOperationsPanel_AllOperationsTable.getColumnName(column)));
				} catch (SQLException e1) {
					ErrorMessage.showErrorMessage(parent, e1.getMessage());
					e1.printStackTrace();
				}
				break;
			}
			
		}
	}

	private String databaseColumnName(String columnName) {
		String sortByString;
		String orderingType;
		if(columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[0])) {
			sortByString = null;
			orderingType = null;
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[1])) {
			sortByString = "stored_name";

			if(isStoredNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isStoredNameAsc = !isStoredNameAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[2])) {
			sortByString = "operations.amount";

			if(isAmountAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isAmountAsc = !isAmountAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[3])) {
			sortByString = "operations.date";

			if(isDateAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isDateAsc = !isDateAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[4])) {
			sortByString = "operations.paid";

			if(isPaidAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isPaidAsc = !isPaidAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[5])) {
			sortByString = "operations.returned";

			if(isReturnedAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isReturnedAsc = !isReturnedAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[6])) {
			sortByString = "operations.updated_date";

			if(isUpdatedDateAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isUpdatedDateAsc = !isUpdatedDateAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[7])) {
			sortByString = "operations.income";

			if(isIncomeAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isIncomeAsc = !isIncomeAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[8])) {
			sortByString = "operations.stored_worker_name";

			if(isNotesAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isNotesAsc = !isNotesAsc;
			
		}else if (columnName.equals(AllOperationsPanel_AllOperationsTableModel.columnNames[9])) {
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
