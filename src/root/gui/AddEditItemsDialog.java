package root.gui;

import global.Category;
import global.gui.ErrorMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTable;

import logic.NumbersHandling;
import logic.TextFieldAndComboBoxHandeler;
import root.gui.itemspanel.RootItem;
import root.gui.itemspanel.RootItemPanelTableModel;
import root.gui.itemspanel.UndoRedoRootItems;
import root.gui.itemspanel.VersionedRootItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khaled
 */
public class AddEditItemsDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7860990737579055768L;
	private UndoRedoRootItems undoRedoRootItems;
	/**
	 * Creates new form AddEditItemsDialog
	 */
	public AddEditItemsDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddEditItemsDialog(java.awt.Frame parent, String addOrEdit, RootItem itemEdited, int itemRowNumber, RootItemPanelTableModel rootItemPanelTableModel, JTable ItemsPanel_ItemsTable, UndoRedoRootItems undoRedoRootItems) {
		super(parent, ModalityType.APPLICATION_MODAL);
		initComponents();
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.undoRedoRootItems = undoRedoRootItems;

		switch (addOrEdit) {
		case "add":
			
			ItemTextField.requestFocus();
			
			UpdateButton.setVisible(false);
			DeleteButton.setVisible(false);
			InsertButton.setVisible(true);

			InsertButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					insertItem();
				}
			});
			
			AmountTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						insertItem();
					}
				}			
			});
			ItemTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			CategoryComboBox.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			SellingPriceTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			BuyingPriceTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			NotesTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			
			break;

		default:
			AmountTextField.requestFocus();
			
			UpdateButton.setVisible(true);
			DeleteButton.setVisible(true);
			InsertButton.setVisible(false);
			ItemTextField.setText(itemEdited.getName());
			SellingPriceTextField.setText(Double.toString(itemEdited.getSellingPrice()));
			BuyingPriceTextField.setText(Double.toString(itemEdited.getBuyingPrice()));
			CategoryComboBox.setSelectedItem(itemEdited.getCategory().toString());
			AmountTextField.setText(Integer.toString(itemEdited.getAmount()));
			NotesTextField.setText(itemEdited.getNotes());

			DeleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					deleteItem(itemEdited, itemRowNumber);
				}
			});

			UpdateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateItem(itemEdited, itemRowNumber);
				}
			});
			
			AmountTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						updateItem(itemEdited, itemRowNumber);
					}
				}			
			});
			TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(AmountTextField);
			
			ItemTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(ItemTextField);
			
			CategoryComboBox.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			
			SellingPriceTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(SellingPriceTextField);
			
			BuyingPriceTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(BuyingPriceTextField);
			
			NotesTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(NotesTextField);
			
			break;
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Panel2 = new javax.swing.JPanel();
        SellingPriceTextField = new javax.swing.JTextField();
        SellingPriceLabel = new javax.swing.JLabel();
        AmountLabel = new javax.swing.JLabel();
        AmountTextField = new javax.swing.JTextField();
        Panel1 = new javax.swing.JPanel();
        DeleteButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        NotesTextField = new javax.swing.JTextField();
        BuyingPriceTextField = new javax.swing.JTextField();
        CategoryLabel = new javax.swing.JLabel();
        CategoryComboBox = new javax.swing.JComboBox();
        ItemTextField = new javax.swing.JTextField();
        ItemLabel2 = new javax.swing.JLabel();
        NotesLabel = new javax.swing.JLabel();
        BuyingPriceLabel = new javax.swing.JLabel();
        InsertButton = new javax.swing.JButton();

        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        Panel2.setLayout(new java.awt.GridBagLayout());

        SellingPriceTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(SellingPriceTextField, gridBagConstraints);

        SellingPriceLabel.setText(":  ”⁄— «·Ã„·…");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(SellingPriceLabel, gridBagConstraints);

        AmountLabel.setText(":  «·ﬂ„Ì…");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(AmountLabel, gridBagConstraints);

        AmountTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(AmountTextField, gridBagConstraints);

        Panel1.setLayout(new java.awt.GridBagLayout());

        DeleteButton.setText("Delete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel1.add(DeleteButton, gridBagConstraints);

        UpdateButton.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel1.add(UpdateButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        Panel2.add(Panel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(NotesTextField, gridBagConstraints);
        TextFieldAndComboBoxHandeler.orientationHandeler(NotesTextField);

        BuyingPriceTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(BuyingPriceTextField, gridBagConstraints);

        CategoryLabel.setText(":  «·‰Ê⁄");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(CategoryLabel, gridBagConstraints);

        CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "electric", "computer", "mobile" , "maintenance" , "other" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(CategoryComboBox, gridBagConstraints);

        ItemTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(ItemTextField, gridBagConstraints);
        TextFieldAndComboBoxHandeler.orientationHandeler(ItemTextField);

        ItemLabel2.setText(":  «·„‰ Ã");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(ItemLabel2, gridBagConstraints);

        NotesLabel.setText(":  „·ÕÊŸ« ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(NotesLabel, gridBagConstraints);

        BuyingPriceLabel.setText(":  ”⁄— «·»Ì⁄");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(BuyingPriceLabel, gridBagConstraints);

        InsertButton.setText("Insert");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.weightx = 1.0;
        Panel2.add(InsertButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(Panel2, gridBagConstraints);
        
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(AmountTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(BuyingPriceTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(ItemTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(NotesTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(SellingPriceTextField);

        pack();
        
    }    
	
	private void updateItem(RootItem itemEdited, int itemRowNumber) {
		
		VersionedRootItem versionedRootItem = new VersionedRootItem(itemEdited, VersionedRootItem.UPDATED);
		
		int amount;
		if (NumbersHandling.isInteger(AmountTextField.getText())) {
			amount = Integer.parseInt(AmountTextField.getText());
		} else {
			amount = itemEdited.getAmount();
		}

		double sellingPrice;
		if (NumbersHandling.isDouble(SellingPriceTextField.getText())) {
			sellingPrice = Double.parseDouble(SellingPriceTextField.getText());
		} else {
			sellingPrice = itemEdited.getSellingPrice();
		}

		double buyingPrice;
		if (NumbersHandling.isDouble(BuyingPriceTextField.getText())) {
			buyingPrice = Double.parseDouble(BuyingPriceTextField.getText());
		} else {
			buyingPrice = itemEdited.getBuyingPrice();
		}

		String notes = NotesTextField.getText();

		String name = ItemTextField.getText();

		Category category = Category.valueOf(CategoryComboBox.getSelectedItem().toString());

		itemEdited.setAmount(amount);
		itemEdited.setSellingPrice(sellingPrice);
		itemEdited.setBuyingPrice(buyingPrice);
		itemEdited.setNotes(notes);
		itemEdited.setName(name);
		itemEdited.setCategory(category);

		try {
			RootItem newRootItem = AddEditItemsDialog.this.rootItemPanelTableModel
					.updateItem(itemEdited, itemRowNumber);
			
			versionedRootItem.initializeNewUpdatedDataItem(newRootItem.getSellingPrice(), 
					newRootItem.getNotes(), newRootItem.getName(), newRootItem.getBuyingPrice(), 
					newRootItem.getAmount(), newRootItem.getCategory(), newRootItem.getCreatedAt(), 
					newRootItem.getUpdatedAt(), newRootItem.getAvailableCapital());
			
			undoRedoRootItems.addOldItemToHistory(versionedRootItem);
			
			AddEditItemsDialog.this.dispose();
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(this, e1.getMessage());
			e1.printStackTrace();
		}
		
	}

	private void insertItem() {
		int amount;
		if (NumbersHandling.isInteger(AmountTextField.getText())) {
			amount = Integer.parseInt(AmountTextField.getText());
		} else {
			amount = 0;
		}

		double sellingPrice;
		if (NumbersHandling.isDouble(SellingPriceTextField.getText())) {
			sellingPrice = Double.parseDouble(SellingPriceTextField.getText());
		} else {
			sellingPrice = 0;
		}

		double buyingPrice;
		if (NumbersHandling.isDouble(BuyingPriceTextField.getText())) {
			buyingPrice = Double.parseDouble(BuyingPriceTextField.getText());
		} else {
			buyingPrice = 0;
		}

		String notes = NotesTextField.getText();

		String name = ItemTextField.getText();

		Category category = Category.valueOf(CategoryComboBox.getSelectedItem().toString());

		try {
			RootItem rootItem = AddEditItemsDialog.this.rootItemPanelTableModel.insertItemToDatabase(name, sellingPrice, buyingPrice, amount, category, notes);
			VersionedRootItem versionedRootItem = new VersionedRootItem(rootItem, VersionedRootItem.INSERTED);
			undoRedoRootItems.addOldItemToHistory(versionedRootItem);
			
			AddEditItemsDialog.this.ItemsPanel_ItemsTable.scrollRectToVisible(AddEditItemsDialog.this.ItemsPanel_ItemsTable.getCellRect(AddEditItemsDialog.this.ItemsPanel_ItemsTable.getRowCount()-1, 0, true));
			AddEditItemsDialog.this.dispose();
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(this, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	private void deleteItem(RootItem itemEdited, int itemRowNumber) {
		try {
			AddEditItemsDialog.this.rootItemPanelTableModel.deleteItem(itemEdited, itemRowNumber);
			VersionedRootItem versionedRootItem = new VersionedRootItem(itemEdited, VersionedRootItem.DELETED) ;
			undoRedoRootItems.addOldItemToHistory(versionedRootItem);
			
			AddEditItemsDialog.this.dispose();
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(AddEditItemsDialog.this, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddEditItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddEditItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddEditItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddEditItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddEditItemsDialog dialog = new AddEditItemsDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	private JTable ItemsPanel_ItemsTable;
	private RootItemPanelTableModel rootItemPanelTableModel;
	private javax.swing.JLabel AmountLabel;
	private javax.swing.JTextField AmountTextField;
	private javax.swing.JLabel BuyingPriceLabel;
	private javax.swing.JTextField BuyingPriceTextField;
	private javax.swing.JComboBox<Category> CategoryComboBox;
	private javax.swing.JLabel CategoryLabel;
	private javax.swing.JButton DeleteButton;
	private javax.swing.JButton InsertButton;
	private javax.swing.JLabel ItemLabel2;
	private javax.swing.JTextField ItemTextField;
	private javax.swing.JLabel NotesLabel;
	private javax.swing.JTextField NotesTextField;
	private javax.swing.JPanel Panel1;
	private javax.swing.JPanel Panel2;
	private javax.swing.JLabel SellingPriceLabel;
	private javax.swing.JTextField SellingPriceTextField;
	private javax.swing.JButton UpdateButton;
}
