package root.gui;

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

public class AddToItemDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6595765885991548857L;
	private boolean isAmountAdded;
	private boolean isTotalCostAdded;
	private int overallAmount;
	private double newSellingPrice;
	private UndoRedoRootItems undoRedoRootItems;
	
	public AddToItemDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddToItemDialog(java.awt.Frame parent,  RootItemPanelTableModel rootItemPanelTableModel, JTable ItemsPanel_ItemsTable, UndoRedoRootItems undoRedoRootItems) throws ArrayIndexOutOfBoundsException{
		super(parent, ModalityType.APPLICATION_MODAL);
		initComponents();
		
		this.rootItemPanelTableModel = rootItemPanelTableModel;
		this.ItemsPanel_ItemsTable = ItemsPanel_ItemsTable;
		this.undoRedoRootItems = undoRedoRootItems;
		
		int itemRowNumber = this.ItemsPanel_ItemsTable.getSelectedRow();
		RootItem itemEdited = this.rootItemPanelTableModel.items.get(itemRowNumber);		
		addToItemTextField.setText(itemEdited.getName());

		addAmountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateItem(itemEdited, itemRowNumber);
			}
		});

		addedAmountTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					updateItem(itemEdited, itemRowNumber);
				}
			}			
		});

		totalCostTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					updateItem(itemEdited, itemRowNumber);
				}
			}			
		});
			
	}
		
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Panel2 = new javax.swing.JPanel();
        addedAmountLabel = new javax.swing.JLabel();
        addedAmountTextField = new javax.swing.JTextField();
        totalCostTextField = new javax.swing.JTextField();
        addToItemTextField = new javax.swing.JTextField();
        addToItemLabel = new javax.swing.JLabel();
        totalCostLabel = new javax.swing.JLabel();
        addAmountButton = new javax.swing.JButton();

        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        Panel2.setLayout(new java.awt.GridBagLayout());

        addedAmountLabel.setText("Amount added : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(addedAmountLabel, gridBagConstraints);

        addedAmountTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(addedAmountTextField, gridBagConstraints);

        totalCostLabel.setText("Total Cost : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(totalCostLabel, gridBagConstraints);
        
        totalCostTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(totalCostTextField, gridBagConstraints);

        addToItemTextField.setEditable(false);
        addToItemTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(addToItemTextField, gridBagConstraints);
        TextFieldAndComboBoxHandeler.orientationHandeler(addToItemTextField);

        addToItemLabel.setText("Add to item : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Panel2.add(addToItemLabel, gridBagConstraints);

        addAmountButton.setText("Add new amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        Panel2.add(addAmountButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(Panel2, gridBagConstraints);
        
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(addedAmountTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(totalCostTextField);

        pack();
    } 
	
	private void updateItem(RootItem itemEdited, int itemRowNumber) {
		VersionedRootItem versionedRootItem = (VersionedRootItem) itemEdited;
		versionedRootItem.setType(VersionedRootItem.UPDATED);
		
		addToItemTextField.setText(itemEdited.getName());
		
		isAmountAdded = false;
		isTotalCostAdded = false;
		
		int amountAdded = 0;
		if (NumbersHandling.isInteger(addedAmountTextField.getText())) {
			if(NumbersHandling.isPositive(Double.parseDouble(addedAmountTextField.getText()))) {
				amountAdded = Integer.parseInt(addedAmountTextField.getText());
				isAmountAdded = true;
			}
		}

		double totalCostOfAddedAmount = 0;
		if (NumbersHandling.isDouble(totalCostTextField.getText())) {
			if(NumbersHandling.isPositive(Double.parseDouble(totalCostTextField.getText()))) {
				totalCostOfAddedAmount = Double.parseDouble(totalCostTextField.getText());
				isTotalCostAdded = true;
			}
		}
		
		if(isAmountAdded && isTotalCostAdded){
			calculation(itemEdited.getAmount(), amountAdded, itemEdited.getAvailableCapital(), totalCostOfAddedAmount);
			itemEdited.setAmount(overallAmount);
			itemEdited.setSellingPrice(newSellingPrice);
			
			try {
				RootItem newRootItem = AddToItemDialog.this.rootItemPanelTableModel
						.updateItem(itemEdited, itemRowNumber);
				
				versionedRootItem.initializeNewUpdatedDataItem(newRootItem.getSellingPrice(), 
						newRootItem.getNotes(), newRootItem.getName(), 
						newRootItem.getBuyingPrice(), newRootItem.getAmount(), 
						newRootItem.getCategory(), newRootItem.getCreatedAt(), 
						newRootItem.getUpdatedAt(), newRootItem.getAvailableCapital());
				
				undoRedoRootItems.addOldItemToHistory(versionedRootItem);
				
				AddToItemDialog.this.dispose();
			} catch (SQLException e1) {
				ErrorMessage.showErrorMessage(this, e1.getMessage());
				e1.printStackTrace();
			}
			
		}

	}
	
	private void calculation(int oldAmount, int amountAdded, double availableCapital, double totalCostOfAddedAmount) {
		
		overallAmount = oldAmount + amountAdded;
		double overallTotalCost;
		
		if(NumbersHandling.isPositive(oldAmount)) {
			overallTotalCost = availableCapital + totalCostOfAddedAmount;
			newSellingPrice = overallTotalCost/overallAmount;
		} else {
			newSellingPrice = totalCostOfAddedAmount/amountAdded;
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
			java.util.logging.Logger.getLogger(AddToItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddToItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddToItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddToItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddToItemDialog dialog = new AddToItemDialog(new javax.swing.JFrame(), true);
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
	private javax.swing.JLabel addedAmountLabel;
	private javax.swing.JTextField addedAmountTextField;
	private javax.swing.JLabel totalCostLabel;
	private javax.swing.JTextField totalCostTextField;
	private javax.swing.JButton addAmountButton;
	private javax.swing.JLabel addToItemLabel;
	private javax.swing.JTextField addToItemTextField;
	private javax.swing.JPanel Panel2;
}
