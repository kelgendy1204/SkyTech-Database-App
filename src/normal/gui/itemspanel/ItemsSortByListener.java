package normal.gui.itemspanel;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;

public class ItemsSortByListener extends MouseAdapter {

	private JTable ItemsPanel_ItemsTable;
	private ItemsPanel_ItemsTableModel itemsPanel_ItemsTableModel;
	private Component parent;
	private boolean isNameAsc, isBuyingPriceAsc, isAmountAsc, isCategoryAsc = true;

	public ItemsSortByListener(Component parent, JTable ItemsPanel_ItemsTable,
			ItemsPanel_ItemsTableModel itemsPanel_ItemsTableModel) {
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.itemsPanel_ItemsTableModel = itemsPanel_ItemsTableModel;
		this.parent = parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = ItemsPanel_ItemsTable.columnAtPoint(e.getPoint());
		try {
			itemsPanel_ItemsTableModel.sortLastSQL(databaseColumnName(ItemsPanel_ItemsTable.getColumnName(column)));
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(parent, e1.getMessage());
			e1.printStackTrace();
		}
	}

	private String databaseColumnName(String columnName) {
		String sortByString;
		String orderingType;
		if(columnName.equals(ItemsPanel_ItemsTableModel.columnNames[0])) {
			sortByString = null;
			orderingType = null;
		}else if (columnName.equals(ItemsPanel_ItemsTableModel.columnNames[1])) {
			sortByString = "name";
			
			if(isNameAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isNameAsc = !isNameAsc;
			
		}else if (columnName.equals(ItemsPanel_ItemsTableModel.columnNames[2])) {
			sortByString = "buying_price";
			
			if(isBuyingPriceAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isBuyingPriceAsc = !isBuyingPriceAsc;
			
		}else if (columnName.equals(ItemsPanel_ItemsTableModel.columnNames[3])) {
			sortByString = "amount";

			if(isAmountAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isAmountAsc = !isAmountAsc;
			
		}else if (columnName.equals(ItemsPanel_ItemsTableModel.columnNames[4])) {
			sortByString = "category";

			if(isCategoryAsc) {
				orderingType = " ASC";
			} else {
				orderingType = " DESC";
			}
			isCategoryAsc = !isCategoryAsc;
			
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
