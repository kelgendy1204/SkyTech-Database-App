package normal.gui.dailyoperations;

import global.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import logic.DateFormats;
import normal.gui.alloperationspanel.AllOperationsPanel_AllOperationsTableModel;
import normal.gui.alloperationspanel.Operation;
import normal.gui.itemspanel.Item;

public class DailyOperationsPanel_DailyOperationsTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6974896282618819837L;
	public ArrayList<Operation> operations;
	private Database database;
	private String lastSQL;
	
	public DailyOperationsPanel_DailyOperationsTableModel(Database database) {
		this.database = database;
		operations = new ArrayList<Operation>();
	}
	
	public void addOperation(Operation operation) {
		operations.add(operation);
	}
	
	@Override
	public int getColumnCount() {
		return AllOperationsPanel_AllOperationsTableModel.columnNames.length;
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
		return AllOperationsPanel_AllOperationsTableModel.columnNames[column];
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
	}
	
	public void loadFromDatabase(String storedWorkerName, String search) throws SQLException{
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		
		StringBuilder filterDate = new StringBuilder();
		filterDate.append(" Where month(operations.date) = ");
		filterDate.append("'"+ month +"'");
		filterDate.append(" and year(operations.date) = ");
		filterDate.append(year);
		filterDate.append(" and day(operations.date) = ");
		filterDate.append(day);
		
		if (!(storedWorkerName == null || storedWorkerName.equals(""))) {
			filterDate.append(" and stored_worker_name = ");
			filterDate.append("'" + storedWorkerName + "'");
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
		
		StringBuilder sql = new StringBuilder("SELECT operation_id , stored_name , operations.amount , operations.date , operations.paid , operations.returned , operations.updated_date , operations.income , TrueIncome(operation_id) true_income ,  operations.worker_id , operations.stored_worker_name , operations.notes From skytech.operations LEFT join skytech.items on items.item_id = operations.item_id");
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
			int workerId = results.getInt("worker_id");
			String storedWorkerName = results.getString("stored_worker_name");
			String notes = results.getString("notes");
			
			operations.add(new Operation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, workerId, storedWorkerName, notes));
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
		String sql = ("UPDATE skytech.operations SET amount = ? , paid = ? , returned = ?, income = ? , notes = ? WHERE operation_id = ?" );
		PreparedStatement updateStatement = database.getCon().prepareStatement(sql);
		
		updateStatement.setInt(1, operation.getAmount());
		updateStatement.setBoolean(2, operation.isPaid());
		updateStatement.setBoolean(3, operation.isReturned());
		updateStatement.setDouble(4, operation.getIncome());
		updateStatement.setString(5, operation.getNotes());
		updateStatement.setInt(6, operation.getOperationId());

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
	
	public void addOperationToDatabase(Item item, int amount, boolean paid, boolean returned, double income, int worker_id, String notes) throws SQLException{
		String sql = "INSERT INTO skytech.operations (item_id, amount, paid, returned, income, worker_id, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = database.getCon().prepareStatement(sql);
		insertStatement.setInt(1, item.getItemId());
		insertStatement.setInt(2, amount);
		insertStatement.setBoolean(3, paid);
		insertStatement.setBoolean(4, returned);
		insertStatement.setDouble(5, income);
		insertStatement.setInt(6, worker_id);
		insertStatement.setString(7, notes);
		insertStatement.executeUpdate();

		PreparedStatement selectInsertedStatement = database.getCon().prepareStatement("SELECT operation_id, date, updated_date, TrueIncome(operation_id) true_income, stored_worker_name From  operations where operation_id = (select LAST_INSERT_ID())");
		ResultSet results = selectInsertedStatement.executeQuery();
		results.next();
		
		int operationId = results.getInt("operation_id");
		Timestamp date = results.getTimestamp("date");
		Timestamp updatedDate = results.getTimestamp("updated_date");
		double trueIncome = results.getDouble("true_income");
		String storedWorkerName = results.getString("stored_worker_name");
		
		operations.add(new Operation(operationId, item.getName(), date, amount, paid, returned, updatedDate, income, trueIncome,worker_id, storedWorkerName, notes));
		
		fireTableRowsInserted(operations.size() - 1, operations.size() - 1);
		
		results.close();
		selectInsertedStatement.close();
		insertStatement.close();
	}
	
	public String[] getColumnNames() {
		return AllOperationsPanel_AllOperationsTableModel.columnNames;
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
			int workerId = results.getInt("worker_id");
			String storedWorkerName = results.getString("stored_worker_name");
			String notes = results.getString("notes");
			
			operations.add(new Operation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, workerId, storedWorkerName, notes));
		}
		
		results.close();
		statement.close();
		
		fireTableDataChanged();
	}
}
