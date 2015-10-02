package root.gui.profitspanel;

public class RootItemProfits {
	
	private String itemName;
	private int amountSold;
	private double itemProfits;
	
	public RootItemProfits(String itemName, int amountSold, double itemProfits) {
		this.amountSold = amountSold;
		this.itemName = itemName;
		this.itemProfits = itemProfits;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(int amountSold) {
		this.amountSold = amountSold;
	}

	public double getItemProfits() {
		return itemProfits;
	}

	public void setItemProfits(double itemProfits) {
		this.itemProfits = itemProfits;
	}
	
}
