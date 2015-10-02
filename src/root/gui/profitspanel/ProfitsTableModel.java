package root.gui.profitspanel;

import global.Category;
import global.Database;
import global.Month;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class ProfitsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8642054217661070841L;
	public ArrayList<RootItemProfits> items;
	public static final String[] columnNames = {"—ﬁ„ «·„‰ Ã" , "«·„‰ Ã" , "«·ﬂ„Ì… «·„»«⁄…" , "«·«—»«Õ" };
	private Database database;
	private String lastSQL;
	private String lastTotalProfitsSQL;
	private JTextField ProfitsPanel_ManualPanel_ProfitsTextFiled;
	
	public ProfitsTableModel(Database database , JTextField ProfitsPanel_ManualPanel_ProfitsTextFiled) {
		this.database = database;
		items = new ArrayList<RootItemProfits>();
		this.ProfitsPanel_ManualPanel_ProfitsTextFiled = ProfitsPanel_ManualPanel_ProfitsTextFiled;
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
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return (row + 1);
		case 1:
			return items.get(row).getItemName();
		case 2:
			return items.get(row).getAmountSold();
		case 3:
			return items.get(row).getItemProfits();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void loadFromDatabase(int day, Month monthChooser, int year, Category category, String search) throws SQLException{
		StringBuilder filterDate = new StringBuilder();
		filterDate.append(" Where year(operations.date) = ");
		filterDate.append(year);
		
		switch (monthChooser) {
		case All:
			break;
		default:
			filterDate.append(" and monthname(operations.date) = ");
			filterDate.append("'"+ monthChooser +"'");
			
			switch (day) {
			case 0:
				break;
			default:
				filterDate.append(" and day(operations.date) = ");
				filterDate.append(day);
				break;
			}
			break;
		}
		
		if(category != Category.all){
			filterDate.append(" and items.category = '");
			filterDate.append(category);
			filterDate.append("'");
		}
		
		if(!search.equals("")){
			filterDate.append(" and stored_name like '%");
			filterDate.append(search);
			filterDate.append("%'");
		}

		sqlSelectStatement(filterDate.toString());
		
		fireTableDataChanged();
	}
	
	private void sqlSelectStatement(String filterDate) throws SQLException {
		items.clear();
		
		StringBuilder sql = new StringBuilder("SELECT stored_name , SUM(TrueAmount(operation_id)) true_amount, SUM(profit) profits FROM skytech.operations");
		
		sql.append(" LEFT JOIN skytech.items ON items.item_id = operations.item_id");
		
		sql.append(filterDate);
		sql.append(" GROUP BY operations.stored_name ");
		
		StringBuilder totalProfitsSQL = new StringBuilder("SELECT SUM(profit) profits FROM skytech.operations LEFT JOIN skytech.items ON items.item_id = operations.item_id");
		totalProfitsSQL.append(filterDate);
		
		setLastSQL(sql.toString());
		setLastTotalProfitsSQL(totalProfitsSQL.toString());
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
//			String itemName = results.getString("name");
			String itemName = results.getString("stored_name");
			int amountSold = results.getInt("true_amount");
			double itemProfits = results.getDouble("profits");
			
			items.add(new RootItemProfits(itemName, amountSold, itemProfits));
		}
		
		refreshProfitsTextField();

		results.close();
		statement.close();
	}
	
	public void setLastSQL(String lastSQL) {
		this.lastSQL = lastSQL;
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
			String itemName = results.getString("stored_name");
			int amountSold = results.getInt("true_amount");
			double itemProfits = results.getDouble("profits");
			
			items.add(new RootItemProfits(itemName, amountSold, itemProfits));
		}
		
		results.close();
		statement.close();
	}
	
	public void setLastTotalProfitsSQL(String lastTotalProfitsSQL) {
		this.lastTotalProfitsSQL = lastTotalProfitsSQL;
	}
	
	private void refreshProfitsTextField() throws SQLException {
		PreparedStatement totalProfitsPreparedStatement = database.getCon().prepareStatement(lastTotalProfitsSQL);
		ResultSet totalProfitsResult = totalProfitsPreparedStatement.executeQuery();
		totalProfitsResult.next();
		ProfitsPanel_ManualPanel_ProfitsTextFiled.setText(Double.toString(totalProfitsResult.getDouble("profits")));
		totalProfitsResult.close();
		totalProfitsPreparedStatement.close();
	}

	public String getLastSQL() {
		return lastSQL;
	}
	
}
