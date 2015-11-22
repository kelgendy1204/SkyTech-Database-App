package root.gui.itemspanel;

import global.Category;

import java.sql.Timestamp;

public class OldRootItem extends RootItem {

	private int type;
	public static final int INSERTED = 1; 
	public static final int UPDATED = 0; 
	public static final int DELETED = -1; 
	
	public OldRootItem(int itemId, String name, double buyingPrice,
			int amount, Category category, double sellingPrice,
			Timestamp createdAt, Timestamp updatedAt, double availableCapital,
			String notes, int type) {
		super(itemId, name, buyingPrice, amount, category, sellingPrice, createdAt,
				updatedAt, availableCapital, notes);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
