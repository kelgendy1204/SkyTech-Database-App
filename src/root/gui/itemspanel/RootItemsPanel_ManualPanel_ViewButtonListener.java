package root.gui.itemspanel;

import global.Category;
import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class RootItemsPanel_ManualPanel_ViewButtonListener implements ActionListener {
	private Category category;
	private JComboBox<Category> ItemsPanel_ManualPanel_CategoryComboBox;
	private RootItemPanelTableModel rootItemPanelTableModel;
	private JPanel ItemsPanel_ItemsTablePanel;
	private Component parent;
	
	public RootItemsPanel_ManualPanel_ViewButtonListener(Component parent, JPanel ItemsPanel_ItemsTablePanel, RootItemPanelTableModel rootItemPanelTableModel, JComboBox<Category> ItemsPanel_ManualPanel_CategoryComboBox) {
		this.ItemsPanel_ItemsTablePanel = ItemsPanel_ItemsTablePanel;
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.ItemsPanel_ManualPanel_CategoryComboBox = ItemsPanel_ManualPanel_CategoryComboBox;
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		viewAllItems("");
	}
	
	private void changeBorder(){
		ItemsPanel_ItemsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, category.toString(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}

	private void changeBorderSearchMode(){
		ItemsPanel_ItemsTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, category.toString() + " (Search mode)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 12)));
	}
	
	public void viewAllItems(String search){
		category = Category.valueOf(ItemsPanel_ManualPanel_CategoryComboBox.getSelectedItem().toString());
		if(search.equals("")){
			changeBorder();
		} else {
			changeBorderSearchMode();
		}
		
		try {
			rootItemPanelTableModel.loadFromDatabase(category, search);
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(parent, e1.getMessage());
			e1.printStackTrace();
		}
	}

}
