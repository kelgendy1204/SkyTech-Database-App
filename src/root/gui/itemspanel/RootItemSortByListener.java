package root.gui.itemspanel;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;

public class RootItemSortByListener extends MouseAdapter {

	private RootItemPanelTableModel rootItemPanelTableModel;
	private JTable ItemsPanel_ItemsTable;
	private Component parent;
	private boolean isNameAsc, isSellingPriceAsc, isBuyingPriceAsc, isAmountAsc, isCategoryAsc, isCreatedAtAsc, isUpdatedAtAsc, isAvailableCapitalAsc, isNotesAsc = true;
	
	public RootItemSortByListener(Component parent, RootItemPanelTableModel rootItemPanelTableModel,
	JTable ItemsPanel_ItemsTable) {
		this.parent = parent;
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int column = ItemsPanel_ItemsTable.columnAtPoint(e.getPoint());
		try {
			rootItemPanelTableModel.sortLastSQL(databaseColumnName(ItemsPanel_ItemsTable.getColumnName(column)));
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(parent, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	private String databaseColumnName(String columnName) {
		String sortByString;
		String orderingType;
		if(columnName.equals(RootItemPanelTableModel.columnNames[0])) {
			sortByString = null;
			orderingType = null;
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[1])) {
			sortByString = "name";
			
			if(isNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isNameAsc = !isNameAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[2])) {
			sortByString = "selling_price";

			if(isSellingPriceAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isSellingPriceAsc = !isSellingPriceAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[3])) {
			sortByString = "buying_price";

			if(isBuyingPriceAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isBuyingPriceAsc = !isBuyingPriceAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[4])) {
			sortByString = "amount";

			if(isAmountAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isAmountAsc = !isAmountAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[5])) {
			sortByString = "category";

			if(isCategoryAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isCategoryAsc = !isCategoryAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[6])) {
			sortByString = "created_at";

			if(isCreatedAtAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isCreatedAtAsc = !isCreatedAtAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[7])) {
			sortByString = "updated_at";

			if(isUpdatedAtAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isUpdatedAtAsc = !isUpdatedAtAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[8])) {
			sortByString = "available_capital";

			if(isAvailableCapitalAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isAvailableCapitalAsc = !isAvailableCapitalAsc;
			
		}else if(columnName.equals(RootItemPanelTableModel.columnNames[9])) {
			sortByString = "notes";

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
