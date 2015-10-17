package normal.gui.alloperationspanel;

import global.Database;
import global.Month;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logic.DateFormats;

public class AllOperationsPanel_AllOperationsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8631357318018020841L;
	public static final String[] columnNames = {"—ﬁ„ «·⁄„·Ì…" , "«·„‰ Ã" , "«·ﬂ„Ì…" , "«· «—ÌŒ" , "»Ì⁄" , "„— Ã⁄" , " «—ÌŒ «· ÕœÌÀ" , "«·œŒ·" , "«·»«∆⁄" ,"„·ÕÊŸ« "};
	public ArrayList<Operation> operations;
	private Database database;
	private String lastSQL;
	
	public AllOperationsPanel_AllOperationsTableModel(Database database) {
		this.database = database;
		operations = new ArrayList<Operation>();
	}
	
	public void addOperation(Operation operation) {
		operations.add(operation);
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return operations.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return (row + 1);
		case 1:
			return operations.get(row).getItemSold();
		case 2:
			return operations.get(row).getAmount();
		case 3:
			return DateFormats.getFullSimpleDateFormat(operations.get(row).getDate());
		case 4:
			return operations.get(row).isPaid();
		case 5:
			return operations.get(row).isReturned();
		case 6:
			return DateFormats.getFullSimpleDateFormat(operations.get(row).getUpdatedDate());
		case 7:
			return operations.get(row).getTrueIncome();
		case 8:
			return operations.get(row).getStoredWorkerName();
		case 9:
			return operations.get(row).getNotes();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 4:
			return Boolean.class;
		case 5:
			return Boolean.class;
		default:
			return super.getColumnClass(column);
		}
	}//begin from here
	
	public void loadFromDatabase(int day, Month monthChooser, int year, String search) throws SQLException{
		StringBuilder filterDate = new StringBuilder();
		filterDate.append(" Where monthname(operations.date) = ");
		filterDate.append("'"+ monthChooser +"'");
		filterDate.append(" and year(operations.date) = ");
		filterDate.append(year);
		
		switch (day) {
		case 0:
			break;
		default:
			filterDate.append(" and day(operations.date) = ");
			filterDate.append(day);
			break;
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
		operations.clear();
		
		StringBuilder sql = new StringBuilder("SELECT operation_id , stored_name , operations.amount , operations.date , operations.paid , operations.returned , operations.updated_date , operations.income , TrueIncome(operation_id) true_income , operations.notes From skytech.operations LEFT join skytech.items on items.item_id = operations.item_id");
		sql.append(filterDate);
		
		setLastSQL(sql.toString());
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
			int operationId = results.getInt("operation_id");
			String itemSold = results.getString("stored_name");
			int amount = results.getInt("amount");
			Timestamp date = results.getTimestamp("date");
			boolean paid = results.getBoolean("paid");
			boolean returned = results.getBoolean("returned");
			Timestamp updatedDate = results.getTimestamp("updated_date");
			double income = results.getDouble("income");
			double trueIncome = results.getDouble("true_income");
			String notes = results.getString("notes");
			
			operations.add(new Operation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, notes));
		}
		
		results.close();
		statement.close();
	}
	
	public void deleteOperation(Operation operation, int operationRowNumber) throws SQLException {
		String sql = ("DELETE FROM skytech.operations WHERE operation_id = ?" );
		PreparedStatement preparedStatement = database.getCon().prepareStatement(sql);
		preparedStatement.setInt(1, operation.getOperationId());
		preparedStatement.executeUpdate();
		operations.remove(operationRowNumber);
		fireTableRowsDeleted(operationRowNumber, operationRowNumber);
		
		preparedStatement.close();
	}
	
	public void updateOperation(Operation operation, int operationRowNumber) throws SQLException {
		String sql = ("UPDATE skytech.operations SET amount = ? , income = ? , notes = ? WHERE operation_id = ?" );
		PreparedStatement updateStatement = database.getCon().prepareStatement(sql);
		
		updateStatement.setInt(1, operation.getAmount());
		updateStatement.setDouble(2, operation.getIncome());
		updateStatement.setString(3, operation.getNotes());
		updateStatement.setInt(4, operation.getOperationId());

		updateStatement.executeUpdate();
		
		PreparedStatement selectStatement = database.getCon().prepareStatement("SELECT amount, updated_date, income, TrueIncome(operation_id) true_income, notes FROM skytech.operations WHERE operation_id = ?");
		selectStatement.setInt(1, operation.getOperationId());
		
		ResultSet results = selectStatement.executeQuery();
		
		results.next();

		Operation operationUpdated = operations.get(operationRowNumber);
		
		int amount = results.getInt("amount");
		Timestamp updatedDate = results.getTimestamp("updated_date");
		double income = results.getDouble("income");
		double trueIncome = results.getDouble("true_income");
		String notes = results.getString("notes");
		
		operationUpdated.setAmount(amount);
		operationUpdated.setIncome(income);
		operationUpdated.setTrueIncome(trueIncome);
		operationUpdated.setNotes(notes);
		operationUpdated.setUpdatedDate(updatedDate);
		
		fireTableRowsUpdated(operationRowNumber, operationRowNumber);
		
		results.close();
		updateStatement.close();
		selectStatement.close();
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
		
		operations.clear();
		
		StringBuilder sql = new StringBuilder(lastSQL);
		sql.append(" order by ");
		sql.append(sortBy);
		
		Statement statement = database.getCon().createStatement();
		ResultSet results = statement.executeQuery(sql.toString());
		
		while(results.next()){
			int operationId = results.getInt("operation_id");
			String itemSold = results.getString("stored_name");
			int amount = results.getInt("amount");
			Timestamp date = results.getTimestamp("date");
			boolean paid = results.getBoolean("paid");
			boolean returned = results.getBoolean("returned");
			Timestamp updatedDate = results.getTimestamp("updated_date");
			double income = results.getDouble("income");
			double trueIncome = results.getDouble("true_income");
			String notes = results.getString("notes");
			
			operations.add(new Operation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, notes));
		}
		
		results.close();
		statement.close();
	}
	
}
