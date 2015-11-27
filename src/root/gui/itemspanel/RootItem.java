package root.gui.itemspanel;

import global.Category;

import java.sql.Timestamp;

import normal.gui.itemspanel.Item;

public class RootItem extends Item {

	protected double sellingPrice;
	protected Timestamp createdAt;
	protected Timestamp updatedAt;
	protected String notes;
	protected double availableCapital;
	
	public RootItem(int itemId, String name, double buyingPrice, int amount,
			Category category, double sellingPrice, Timestamp createdAt, Timestamp updatedAt, 
			double availableCapital, String notes) {
		super(itemId, name, buyingPrice, amount, category);
		this.availableCapital = availableCapital;
		this.createdAt = createdAt;
		this.notes = notes;
		this.sellingPrice = sellingPrice;
		this.updatedAt = updatedAt;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public Timestamp getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public double getAvailableCapital() {
		return availableCapital;
	}


	public void setAvailableCapital(double availableCapital) {
		this.availableCapital = availableCapital;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (!(obj instanceof RootItem)){ return false;}
		
		RootItem rootItem = (RootItem) obj;
		
		if(this.getName().equals(rootItem.getName()) && this.getItemId() == rootItem.getItemId() ) {
			return true;
		} else {
			return false;
		}
	}
}
