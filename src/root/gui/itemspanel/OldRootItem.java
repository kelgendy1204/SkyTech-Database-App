package root.gui.itemspanel;

import global.Category;

import java.sql.Timestamp;

public class OldRootItem extends RootItem {

	private String type;
	
	public OldRootItem(int itemId, String name, double buyingPrice,
			int amount, Category category, double sellingPrice,
			Timestamp createdAt, Timestamp updatedAt, double availableCapital,
			String notes, String type) {
		super(itemId, name, buyingPrice, amount, category, sellingPrice, createdAt,
				updatedAt, availableCapital, notes);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
