package normal.gui.alloperationspanel;

import java.sql.Timestamp;

public class Operation {
	protected int operationId;
	protected String itemSold;
	protected Timestamp date;
	protected int amount;
	protected boolean paid;
	protected boolean returned;
	protected Timestamp updatedDate;
	protected double income;
	protected double trueIncome;
	protected int workerId; 
	protected String storedWorkerName;
	protected String notes;

	public Operation(int operationId, String itemSold, Timestamp date, int amount, boolean paid,
			boolean returned, Timestamp updatedDate, double income, double trueIncome, int workerId, String storedWorkerName, String notes) {
		this.operationId = operationId;
		this.itemSold = itemSold;
		this.amount = amount;
		this.date = date;
		this.paid = paid;
		this.returned = returned;
		this.setUpdatedDate(updatedDate);
		this.income = income;
		this.notes = notes;
		this.trueIncome = trueIncome;
		this.workerId = workerId;
		this.storedWorkerName = storedWorkerName;
	}

	public String getItemSold() {
		return itemSold;
	}

	public void setItemSold(String itemSold) {
		this.itemSold = itemSold;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public double getTrueIncome() {
		return trueIncome;
	}

	public void setTrueIncome(double trueIncome) {
		this.trueIncome = trueIncome;
	}
	
	public void setStoredWorkerName(String storedWorkerName) {
		this.storedWorkerName = storedWorkerName;
	}
	
	public String getStoredWorkerName() {
		return storedWorkerName;
	}
	
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	
	public int getWorkerId() {
		return workerId;
	}
	
}
