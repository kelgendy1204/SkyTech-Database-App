package root.gui.operations;

import global.Category;
import global.Database;
import global.Month;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import logic.DateFormats;

public class RootOperationsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9070121201818075741L;
	public static final String[] columnNames = {"—ﬁ„ «·⁄„·Ì…" , "«·„‰ Ã «·„»«⁄" , "«·ﬂ„Ì…" , "«· «—ÌŒ" , "»Ì⁄" , "„— Ã⁄" , " «—ÌŒ «· ÕœÌÀ" , "«·œŒ·" , "«·—»Õ" , "«·»«∆⁄" , "„·ÕÊŸ« "};
	public ArrayList<RootOperation> operations;
	private Database database;
	private String lastSQL;
	private String lastTotalIncomeSQL;
	private JTextField OperationsPanel_ManualPanel_IncomeTextField;
	
	public RootOperationsTableModel(Database database , JTextField OperationsPanel_ManualPanel_IncomeTextField) {
		this.database = database;
		operations = new ArrayList<RootOperation>();
		this.OperationsPanel_ManualPanel_IncomeTextField = OperationsPanel_ManualPanel_IncomeTextField;
	}
	
	public void addOperation(RootOperation operation) {
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
			return operations.get(row).getProfit();
		case 9:
			return operations.get(row).getStoredWorkerName();
		case 10:
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
	}
	
	public void loadFromDatabase(int day, Month monthChooser, int year, Category category, String storedWorkerName, String search) throws SQLException{
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
		
	}
	
	
	private void sqlSelectStatement(String filterDate) throws SQLException {
		operations.clear();
		
		StringBuilder sql = new StringBuilder("SELECT operation_id , stored_name , operations.amount , operations.date , operations.paid , operations.returned , operations.updated_date , operations.income, TrueIncome(operation_id) true_income, operations.profit , operations.notes, operations.worker_id , operations.stored_worker_name From skytech.operations LEFT join skytech.items on items.item_id = operations.item_id");
		sql.append(filterDate);
		
		StringBuilder totalIncomeSQL = new StringBuilder("SELECT SUM(TrueIncome(operation_id)) AS total_income FROM skytech.items Right JOIN skytech.operations ON items.item_id = operations.item_id");
		totalIncomeSQL.append(filterDate);
		
		setLastSQL(sql.toString());
		setLastTotalIncomeSQL(totalIncomeSQL.toString());
		
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
			double profit = results.getDouble("profit");
			int workerId = results.getInt("worker_id");
			String storedWorkerName = results.getString("stored_worker_name");
			String notes = results.getString("notes");
			
			operations.add(new RootOperation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, profit, workerId, storedWorkerName, notes));
		}
		
		fireTableDataChanged();
		refreshIncomeTextField();

		results.close();
		statement.close();
	}

	public void deleteOperation(RootOperation operation, int operationRowNumber) throws SQLException {
		String sql = ("DELETE FROM skytech.operations WHERE operation_id = ?" );
		PreparedStatement preparedStatement = database.getCon().prepareStatement(sql);
		preparedStatement.setInt(1, operation.getOperationId());
		preparedStatement.executeUpdate();
		operations.remove(operationRowNumber);
		fireTableRowsDeleted(operationRowNumber, operationRowNumber);
		preparedStatement.close();
		
		refreshIncomeTextField();
	}
	
	public void updateOperation(RootOperation operation, int operationRowNumber) throws SQLException {
		String sql = ("UPDATE skytech.operations SET amount = ? , income = ? , notes = ? WHERE operation_id = ?" );
		PreparedStatement updateStatement = database.getCon().prepareStatement(sql);
		
		updateStatement.setInt(1, operation.getAmount());
		updateStatement.setDouble(2, operation.getIncome());
		updateStatement.setString(3, operation.getNotes());
		updateStatement.setInt(4, operation.getOperationId());

		updateStatement.executeUpdate();
		
		PreparedStatement selectStatement = database.getCon().prepareStatement("SELECT amount, updated_date, income, TrueIncome(operation_id) true_income, profit, notes FROM skytech.operations WHERE operation_id = ?");
		selectStatement.setInt(1, operation.getOperationId());
		
		ResultSet results = selectStatement.executeQuery();
		
		results.next();

		RootOperation operationUpdated = operations.get(operationRowNumber);
		
		int amount = results.getInt("amount");
		Timestamp updatedDate = results.getTimestamp("updated_date");
		double income = results.getDouble("income");
		double trueIncome = results.getDouble("true_income");
		double profit = results.getDouble("profit");
		String notes = results.getString("notes");
		
		operationUpdated.setAmount(amount);
		operationUpdated.setIncome(income);
		operationUpdated.setTrueIncome(trueIncome);
		operationUpdated.setNotes(notes);
		operationUpdated.setUpdatedDate(updatedDate);
		operationUpdated.setProfit(profit);
		
		fireTableRowsUpdated(operationRowNumber, operationRowNumber);
		
		results.close();
		updateStatement.close();
		selectStatement.close();
		
		refreshIncomeTextField();
	}
	
	public void setLastSQL(String lastSQL) {
		this.lastSQL = lastSQL;
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
			double profit = results.getDouble("profit");
			int workerId = results.getInt("worker_id");
			String storedWorkerName = results.getString("stored_worker_name");
			String notes = results.getString("notes");
			
			operations.add(new RootOperation(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome, profit, workerId, storedWorkerName, notes));
		}
		
		results.close();
		statement.close();
	}

	public void setLastTotalIncomeSQL(String lastTotalIncomeSQL) {
		this.lastTotalIncomeSQL = lastTotalIncomeSQL;
	}
	
	private void refreshIncomeTextField() throws SQLException {
		PreparedStatement totalIncomePreparedStatement = database.getCon().prepareStatement(lastTotalIncomeSQL);
		ResultSet totalIncomeResult = totalIncomePreparedStatement.executeQuery();
		totalIncomeResult.next();
		OperationsPanel_ManualPanel_IncomeTextField.setText(Double.toString(totalIncomeResult.getDouble("total_income")));
		totalIncomeResult.close();
		totalIncomePreparedStatement.close();
	}

}
