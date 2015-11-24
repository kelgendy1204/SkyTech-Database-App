package root.gui.itemspanel;

import global.Category;
import global.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import logic.DateFormats;
import logic.NumbersHandling;


public class RootItemPanelTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1035057508724996683L;

	public static final String[] columnNames = {"«·—ﬁ„", "«·„‰ Ã", "”⁄— «·Ã„·…", "”⁄— «·»Ì⁄", "«·ﬂ„Ì…", "«·‰Ê⁄", " «—ÌŒ «·«‰‘«¡", " «—ÌŒ «· ÕœÌÀ", "—√” «·„«· «·„ «Õ", "„·ÕÊŸ« "};
	public ArrayList<RootItem> items;
	private Database database;
	private String lastSQL;
	private String lastTotalCapitalSQL = "SELECT Sum(available_capital) total_capital FROM skytech.items WHERE item_id = -1";
	private JTextField ItemsPanel_ManualPanel_CapitalTextField;
	
	public RootItemPanelTableModel(Database database, JTextField ItemsPanel_ManualPanel_CapitalTextField) {
		this.database = database;
		items = new ArrayList<RootItem>();
		this.ItemsPanel_ManualPanel_CapitalTextField = ItemsPanel_ManualPanel_CapitalTextField;
	}
	
	public void addItem(RootItem item) {
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
			return items.get(row).getSellingPrice();
		case 3:
			return items.get(row).getBuyingPrice();
		case 4:
			return items.get(row).getAmount();
		case 5:
			return items.get(row).getCategory();
		case 6:
			return DateFormats.getFullSimpleDateFormat(items.get(row).getCreatedAt());
		case 7:
			return DateFormats.getFullSimpleDateFormat(items.get(row).getUpdatedAt());
		case 8:
			return items.get(row).getAvailableCapital();
		case 9:
			return items.get(row).getNotes();
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
		
		StringBuilder sql = new StringBuilder("select * from SkyTech.items");
		sql.append(type);
		
		StringBuilder totalCapitalSQL = new StringBuilder("SELECT Sum(available_capital) total_capital FROM skytech.items");
		totalCapitalSQL.append(type);
		
		setLastSQL(sql.toString());
		setLastTotalCapitalSQL(totalCapitalSQL.toString());
		
		sql.append(" ORDER BY amount DESC, name");
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
			int itemId = results.getInt("item_id");
			String name = results.getString("name");
			double sellingPrice = results.getDouble("selling_price");
			double buyingPrice = results.getDouble("buying_price");
			int amount = results.getInt("amount");
			Category category = Category.valueOf(results.getString("category"));
			Timestamp createdAt = results.getTimestamp("created_at");
			Timestamp updatedAt = results.getTimestamp("updated_at");
			double availableCapital = results.getDouble("available_capital");
			String notes = results.getString("notes");
			
			items.add(new RootItem(itemId, name, buyingPrice, amount, category, sellingPrice, createdAt, updatedAt, availableCapital, notes));
		}
		
		fireTableDataChanged();
		refreshCapitalTextField();
		
		results.close();
		statement.close();
	}
	
	public void deleteItem(RootItem item, int itemRowNumber) throws SQLException {
		String sql = ("DELETE FROM skytech.items WHERE item_id = ?" );
		PreparedStatement preparedStatement = database.getCon().prepareStatement(sql);
		preparedStatement.setInt(1, item.getItemId());
		preparedStatement.executeUpdate();
		double availableCapital = item.getAvailableCapital();		
		
		//double availableCapital = items.get(itemRowNumber).getAvailableCapital();
		
		if (itemRowNumber == UndoRedoRootItems.UNKNOWN_ITEM_ROW_NUMBER) {
			try {
				items.remove(item);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fireTableDataChanged();
		} else {
			items.remove(itemRowNumber);
			fireTableRowsDeleted(itemRowNumber, itemRowNumber);
		}
		
		preparedStatement.close();
		double totalCapitalBeforeDelete = Double.parseDouble(ItemsPanel_ManualPanel_CapitalTextField.getText());
		double totalCapitalAfterDelete = Double.parseDouble(NumbersHandling.decimalFormat.format(totalCapitalBeforeDelete - availableCapital));
		ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(totalCapitalAfterDelete));
		
	}
	
	public void updateItem(RootItem item, int itemRowNumber) throws SQLException {
		String sql = ("UPDATE skytech.items SET name = ?, selling_price = ?, buying_price = ?, amount = ? , category = ? , notes = ? WHERE item_id = ?" );
		PreparedStatement updateStatement = database.getCon().prepareStatement(sql);
		
		updateStatement.setString(1, item.getName());
		updateStatement.setDouble(2, item.getSellingPrice());
		updateStatement.setDouble(3, item.getBuyingPrice());
		updateStatement.setInt(4, item.getAmount());
		updateStatement.setString(5, item.getCategory().toString());
		updateStatement.setString(6, item.getNotes());
		updateStatement.setInt(7, item.getItemId());

		updateStatement.executeUpdate();
		
		PreparedStatement selectStatement = database.getCon().prepareStatement("SELECT name, selling_price, buying_price, amount, category, updated_at, available_capital, notes FROM skytech.items WHERE item_id = ?");
		selectStatement.setInt(1, item.getItemId());
		
		ResultSet results = selectStatement.executeQuery();
		
		results.next();

		RootItem itemUpdated;
		
		if (itemRowNumber == UndoRedoRootItems.UNKNOWN_ITEM_ROW_NUMBER) {
			itemUpdated = items.get(items.indexOf(item));
		} else {
			itemUpdated = items.get(itemRowNumber);			
		}
		
		double oldAvailableCapital = itemUpdated.getAvailableCapital();
		
		String name = results.getString("name");
		double sellingPrice = results.getDouble("selling_price");
		double buyingPrice = results.getDouble("buying_price");
		int amount = results.getInt("amount");
		Category category = Category.valueOf(results.getString("category"));
		Timestamp updatedDate = results.getTimestamp("updated_at");
		double availableCapital = results.getDouble("available_capital");
		String notes = results.getString("notes");
		
		itemUpdated.setName(name);
		itemUpdated.setSellingPrice(sellingPrice);
		itemUpdated.setBuyingPrice(buyingPrice);
		itemUpdated.setAmount(amount);
		itemUpdated.setCategory(category);
		itemUpdated.setUpdatedAt(updatedDate);
		itemUpdated.setAvailableCapital(availableCapital);
		itemUpdated.setNotes(notes);
		
		if (itemRowNumber == UndoRedoRootItems.UNKNOWN_ITEM_ROW_NUMBER) {
			fireTableDataChanged();
		} else {
			fireTableRowsUpdated(itemRowNumber, itemRowNumber);			
		}
		
		results.close();
		updateStatement.close();
		selectStatement.close();
		
		double totalCapitalBeforeUpdate = Double.parseDouble(ItemsPanel_ManualPanel_CapitalTextField.getText());
		double totalCapitalAfterUpdate = Double.parseDouble(NumbersHandling.decimalFormat.format(totalCapitalBeforeUpdate - oldAvailableCapital + availableCapital));
		ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(totalCapitalAfterUpdate));
		
	}
	
	public void insertItemToDatabase(String name, double sellingPrice, double buyingPrice, int amount, Category category, String notes) throws SQLException{
		String sql = "INSERT INTO skytech.items (name, selling_price, buying_price, amount, category, notes) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = database.getCon().prepareStatement(sql);
		insertStatement.setString(1, name);
		insertStatement.setDouble(2, sellingPrice);
		insertStatement.setDouble(3, buyingPrice);
		insertStatement.setInt(4, amount);
		insertStatement.setString(5, category.toString());
		insertStatement.setString(6, notes);
		insertStatement.executeUpdate();

		PreparedStatement selectInsertedStatement = database.getCon().prepareStatement("SELECT item_id, created_at, updated_at, available_capital FROM items WHERE item_id = (select LAST_INSERT_ID())");
		ResultSet results = selectInsertedStatement.executeQuery();
		results.next();
		
		int itemId = results.getInt("item_id");
		Timestamp createdAt = results.getTimestamp("created_at");
		Timestamp updatedAt = results.getTimestamp("updated_at");
		double availableCapital = results.getDouble("available_capital");
		
		items.add(new RootItem(itemId, name, buyingPrice, amount, category, sellingPrice, createdAt, updatedAt, availableCapital, notes));
		
		fireTableRowsInserted(items.size() - 1, items.size() - 1);
		
		double totalCapitalBeforeInsert = Double.parseDouble(ItemsPanel_ManualPanel_CapitalTextField.getText());
		double totalCapitalAfterInsert = Double.parseDouble(NumbersHandling.decimalFormat.format(totalCapitalBeforeInsert + availableCapital));
		ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(totalCapitalAfterInsert));
		
		results.close();
		selectInsertedStatement.close();
		insertStatement.close();
	}

	public void setLastSQL(String lastSQL) {
		this.lastSQL = lastSQL;
	}

	public void setLastTotalCapitalSQL(String lastTotalCapitalSQL) {
		this.lastTotalCapitalSQL = lastTotalCapitalSQL;
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
			double sellingPrice = results.getDouble("selling_price");
			double buyingPrice = results.getDouble("buying_price");
			int amount = results.getInt("amount");
			Category category = Category.valueOf(results.getString("category"));
			Timestamp createdAt = results.getTimestamp("created_at");
			Timestamp updatedAt = results.getTimestamp("updated_at");
			double availableCapital = results.getDouble("available_capital");
			String notes = results.getString("notes");
			
			items.add(new RootItem(itemId, name, buyingPrice, amount, category, sellingPrice, createdAt, updatedAt, availableCapital, notes));
		}
		
		results.close();
		statement.close();
		
		fireTableDataChanged();
	}
	
	private void refreshCapitalTextField() throws SQLException {
		PreparedStatement totalCapitalPreparedStatement = database.getCon().prepareStatement(lastTotalCapitalSQL);
		ResultSet totalCapitalResult = totalCapitalPreparedStatement.executeQuery();
		totalCapitalResult.next();
		ItemsPanel_ManualPanel_CapitalTextField.setText(Double.toString(totalCapitalResult.getDouble("total_capital")));
		totalCapitalResult.close();
		totalCapitalPreparedStatement.close();
	}

}
