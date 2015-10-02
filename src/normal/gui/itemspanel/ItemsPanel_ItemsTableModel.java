package normal.gui.itemspanel;

import global.Category;
import global.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ItemsPanel_ItemsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4742244180325155203L;
	public static final String[] columnNames = {"—ﬁ„ «·„‰ Ã", "«·„‰ Ã", "”⁄— «·»Ì⁄", "«·ﬂ„Ì…", "«·‰Ê⁄"};
	public ArrayList<Item> items;
	private Database database;
	private String lastSQL;
	
	public ItemsPanel_ItemsTableModel(Database database) {
		this.database = database;
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return (row + 1);
		case 1:
			return items.get(row).getName();
		case 2:
			return items.get(row).getBuyingPrice();
		case 3:
			return items.get(row).getAmount();
		case 4:
			return items.get(row).getCategory();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void loadFromDatabase(Category categoryChooser, String search) throws SQLException{
		StringBuilder filterType = new StringBuilder();
		
		if(categoryChooser != Category.all) {
			filterType.append(" Where category = '");
			filterType.append(categoryChooser);
			filterType.append("'");
			
			if(!search.equals("")){
				filterType.append(" and name like '%");
				filterType.append(search);
				filterType.append("%'");
			}
			
		} else {
			if(!search.equals("")){
				filterType.append(" WHERE name like '%");
				filterType.append(search);
				filterType.append("%'");
			}
		}
		
		sqlSelectStatement(filterType.toString());
		
	}

	private void sqlSelectStatement(String type) throws SQLException {
		items.clear();
		
		StringBuilder sql = new StringBuilder("select item_id, name, buying_price, amount, category from SkyTech.items");
		sql.append(type);
		
		setLastSQL(sql.toString());
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
			int itemId = results.getInt("item_id");
			String name = results.getString("name");
			double price = results.getDouble("buying_price");
			int amount = results.getInt("amount");
			Category category = Category.valueOf(results.getString("category"));
			
			items.add(new Item(itemId, name, price, amount, category));
		}
		
		fireTableDataChanged();
		
		results.close();
		statement.close();
	}
	
	public void setLastSQL(String lastSQL) {
		this.lastSQL = lastSQL;
	}
	
	public String getLastSQL() {
		return lastSQL;
	}
	
	public void sortLastSQL(String sortBy) throws SQLException {
		if(lastSQL == null || sortBy == null) {
			return;
		}
		
		items.clear();
		
		StringBuilder sql = new StringBuilder(lastSQL);
		sql.append(" order by ");
		sql.append(sortBy);
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
			int itemId = results.getInt("item_id");
			String name = results.getString("name");
			double price = results.getDouble("buying_price");
			int amount = results.getInt("amount");
			Category category = Category.valueOf(results.getString("category"));
			
			items.add(new Item(itemId, name, price, amount, category));
		}
		
		fireTableDataChanged();
		
		results.close();
		statement.close();
	}

}
