package root.gui.itemspanel;

import global.Category;

import java.sql.Timestamp;

public class VersionedRootItem extends RootItem {

	public static final int INSERTED = 1; 
	public static final int UPDATED = 0; 
	public static final int DELETED = -1;
	
	private int type;
	
	private double newSellingPrice;
	private String newNotes;
	private String newName;
	private double newBuyingPrice;
	private int newAmount;
	private Category newCategory;
	private Timestamp newCreatedAt;
	private Timestamp newUpdatedAt;
	private double newAvailableCapital;
	
	public VersionedRootItem(RootItem rootItem, int type) {
		super(rootItem.getItemId(), rootItem.getName(), rootItem.getBuyingPrice(), 
				rootItem.getAmount(), rootItem.getCategory(), rootItem.getSellingPrice(), 
				rootItem.getCreatedAt(), rootItem.getUpdatedAt(), rootItem.getAvailableCapital(), 
				rootItem.getNotes());
		this.type = type;
	}
	
	public void initializeNewUpdatedDataItem(double newSellingPrice, String newNotes,
			String newName, double newBuyingPrice, int newAmount, 
			Category newCategory, Timestamp newCreatedAt, Timestamp newUpdatedAt,
			double newAvailableCapital) {
		this.newSellingPrice = newSellingPrice;
		this.newNotes = newNotes;
		this.newName = newName;
		this.newBuyingPrice = newBuyingPrice;
		this.newAmount = newAmount;
		this.newCategory = newCategory;
		this.newCreatedAt = newCreatedAt;
		this.newUpdatedAt = newUpdatedAt;
		this.newAvailableCapital = newAvailableCapital;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// From here to the rest, in case of updating only
	public double getNewSellingPrice() {
		return newSellingPrice;
	}

	public void setNewSellingPrice(double newSellingPrice) {
		this.newSellingPrice = newSellingPrice;
	}

	public String getNewNotes() {
		return newNotes;
	}

	public void setNewNotes(String newNotes) {
		this.newNotes = newNotes;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public double getNewBuyingPrice() {
		return newBuyingPrice;
	}

	public void setNewBuyingPrice(double newBuyingPrice) {
		this.newBuyingPrice = newBuyingPrice;
	}

	public int getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(int newAmount) {
		this.newAmount = newAmount;
	}

	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}
	
	public Timestamp getNewCreatedAt() {
		return newCreatedAt;
	}

	public void setNewCreatedAt(Timestamp newCreatedAt) {
		this.newCreatedAt = newCreatedAt;
	}

	public Timestamp getNewUpdatedAt() {
		return newUpdatedAt;
	}

	public void setNewUpdatedAt(Timestamp newUpdatedAt) {
		this.newUpdatedAt = newUpdatedAt;
	}

	public double getNewAvailableCapital() {
		return newAvailableCapital;
	}

	public void setNewAvailableCapital(double newAvailableCapital) {
		this.newAvailableCapital = newAvailableCapital;
	}

	public RootItem getNewRootItemUpdatedValues() {
		RootItem newRootItem = new RootItem(getItemId(), getNewName(), getNewBuyingPrice(), getNewAmount(), getNewCategory(), getNewSellingPrice(), getNewCreatedAt(), getNewUpdatedAt(), getNewAvailableCapital(), getNewNotes());
		return newRootItem;
	}
	
}
