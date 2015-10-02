package root.gui.operations;

import java.sql.Timestamp;

import normal.gui.alloperationspanel.Operation;

public class RootOperation extends Operation {

	private double profit;
	
	public RootOperation(int operationId, String itemSold, Timestamp date,
			int amount, boolean paid, boolean returned, Timestamp updatedDate,
			double income, double trueIncome, double profit, String notes) {
		super(operationId, itemSold, date, amount, paid, returned, updatedDate, income, trueIncome,
				notes);
		this.profit = profit;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

}
