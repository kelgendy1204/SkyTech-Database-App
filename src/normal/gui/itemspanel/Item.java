package normal.gui.itemspanel;

import global.Category;

public class Item {
	
	protected String name;
	protected double buyingPrice;
	protected int amount;
	protected Category category;
	protected int itemId;
	
	public Item(int itemId, String name, double buyingPrice, int amount, Category category) {
		this.itemId = itemId;
		this.name = name;
		this.buyingPrice = buyingPrice;
		this.amount = amount;
		this.category = category;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double price) {
		this.buyingPrice = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
