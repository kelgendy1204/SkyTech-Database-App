package global.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import logic.NumbersHandling;
import logic.TextFieldHandeler;
import normal.gui.alloperationspanel.AllOperationsPanel_AllOperationsTableModel;
import normal.gui.alloperationspanel.Operation;
import normal.gui.dailyoperations.DailyOperationsPanel_DailyOperationsTableModel;
import normal.gui.itemspanel.Item;
import root.gui.operations.RootOperation;
import root.gui.operations.RootOperationsTableModel;


public class EditOperationsDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 4579339341023722864L;
	private AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel;
	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private RootOperationsTableModel operationsTableModel;

	public void setAllOperationsPanel_AllOperationsTableModel(
			AllOperationsPanel_AllOperationsTableModel allOperationsPanel_AllOperationsTableModel) {
		this.allOperationsPanel_AllOperationsTableModel = allOperationsPanel_AllOperationsTableModel;
	}

	public void setDailyOperationsPanel_DailyOperationsTableModel(
			DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel) {
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
	}
	
	public void setOperationsTableModel(
			RootOperationsTableModel operationsTableModel) {
		this.operationsTableModel = operationsTableModel;
	}

	public EditOperationsDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public EditOperationsDialog(java.awt.Frame parent, Operation operation, int tabbedPaneIndex, int operationRowNumber, JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox) {
		super(parent, ModalityType.APPLICATION_MODAL);
		initComponents();
		this.DailyOperationPanel_ManualPanel_ItemsComboBox = DailyOperationPanel_ManualPanel_ItemsComboBox;
		ItemSoldName.setText(operation.getItemSold());
		AmountTextField.setText(Integer.toString(operation.getAmount()));
		PaidRadioButton.setSelected(operation.isPaid());
		ReturnedRadioButton.setSelected(operation.isReturned());
		IncomeTextField.setText(Double.toString(operation.getIncome()));
		NotesTextField.setText(operation.getNotes());

		if (parent.getName().equals("Normal user GUI")) {
			IncomeTextField.requestFocus();
			DeleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					switch (tabbedPaneIndex) {

					case 0:
						try {
							EditOperationsDialog.this.dailyOperationsPanel_DailyOperationsTableModel
									.deleteOperation(operation,
											operationRowNumber);
							EditOperationsDialog.this.dispose();
							
							if( EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox != null) {
								EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.requestFocusInWindow();
								EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.setSelectedIndex(0);
							}
							
						} catch (SQLException e1) {
							ErrorMessage.showErrorMessage(EditOperationsDialog.this, e1.getMessage());
							e1.printStackTrace();
						}
						break;

					case 1:
						try {
							EditOperationsDialog.this.allOperationsPanel_AllOperationsTableModel
									.deleteOperation(operation,
											operationRowNumber);
							EditOperationsDialog.this.dispose();
						} catch (SQLException e) {
							ErrorMessage.showErrorMessage(EditOperationsDialog.this, e.getMessage());
							e.printStackTrace();
						}
						break;
					default:
						break;
					}

				}
			});
			
			UpdateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					normalFrameUpdateOperation(operation, tabbedPaneIndex,
							operationRowNumber);
					if( EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox != null) {
						EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.requestFocusInWindow();
						EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.setSelectedIndex(0);
					}
				}
			});
			
			AmountTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						normalFrameUpdateOperation(operation, tabbedPaneIndex,
								operationRowNumber);
						if( EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox != null) {
							EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.requestFocusInWindow();
							EditOperationsDialog.this.DailyOperationPanel_ManualPanel_ItemsComboBox.setSelectedIndex(0);
						}
					}
				}
			});
			IncomeTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			NotesTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			
		} else {
			DeleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						EditOperationsDialog.this.operationsTableModel.deleteOperation((RootOperation) operation, operationRowNumber);
						EditOperationsDialog.this.dispose();
					} catch (SQLException e1) {
						ErrorMessage.showErrorMessage(EditOperationsDialog.this, e1.getMessage());
						e1.printStackTrace();
					}
				}
			});
			
			UpdateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rootFrameUpdateOperation(operation, operationRowNumber);
				}
			});
			
			AmountTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						rootFrameUpdateOperation(operation, operationRowNumber);
					}
				}
			});
			IncomeTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			NotesTextField.addKeyListener(AmountTextField.getKeyListeners()[AmountTextField.getKeyListeners().length - 1]);
			
		}

	}


	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		NotesTextField = new javax.swing.JTextField();
		IncomeLabel = new javax.swing.JLabel();
		ReturnedRadioButton = new javax.swing.JRadioButton();
		IncomeTextField = new javax.swing.JTextField();
		AmountTextField = new javax.swing.JTextField();
		AmountLabel = new javax.swing.JLabel();
		PaidRadioButton = new javax.swing.JRadioButton();
		ItemSoldLabel = new javax.swing.JLabel();
		NotesLabel = new javax.swing.JLabel();
		ItemSoldName = new javax.swing.JTextField();
		Panel1 = new javax.swing.JPanel();
		DeleteButton = new javax.swing.JButton();
		UpdateButton = new javax.swing.JButton();
		buttonGroup = new ButtonGroup();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Edit operation");
		setResizable(false);
		getContentPane().setLayout(new java.awt.GridBagLayout());

		jPanel1.setLayout(new java.awt.GridBagLayout());
		
		NotesTextField.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.ipadx = 382;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(NotesTextField, gridBagConstraints);
		TextFieldHandeler.orientationHandeler(NotesTextField);

		IncomeLabel.setText("Income :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
		jPanel1.add(IncomeLabel, gridBagConstraints);

		ReturnedRadioButton.setText("Returned");
		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
		jPanel1.add(ReturnedRadioButton, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(IncomeTextField, gridBagConstraints);
        
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 121;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(AmountTextField, gridBagConstraints);

		AmountLabel.setText("amount :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(AmountLabel, gridBagConstraints);

		PaidRadioButton.setSelected(true);
		PaidRadioButton.setText("Paid");
		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
		jPanel1.add(PaidRadioButton, gridBagConstraints);
		
		buttonGroup.add(PaidRadioButton);
		buttonGroup.add(ReturnedRadioButton);
		
		PaidRadioButton.setFocusable(false);
		PaidRadioButton.removeMouseListener(PaidRadioButton.getMouseListeners()[0]);
		
		ReturnedRadioButton.setFocusable(false);
		ReturnedRadioButton.removeMouseListener(ReturnedRadioButton.getMouseListeners()[0]);

		ItemSoldLabel.setText("Item Sold :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(ItemSoldLabel, gridBagConstraints);

		NotesLabel.setText("Notes :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(NotesLabel, gridBagConstraints);

		ItemSoldName.setEditable(false);
		ItemSoldName.setColumns(10);
		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(ItemSoldName, gridBagConstraints);
		TextFieldHandeler.orientationHandeler(ItemSoldName);

		Panel1.setLayout(new java.awt.GridBagLayout());

		DeleteButton.setText("Delete");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		Panel1.add(DeleteButton, gridBagConstraints);

		UpdateButton.setText("Update");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		Panel1.add(UpdateButton, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel1.add(Panel1, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 9;
		gridBagConstraints.gridheight = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(jPanel1, gridBagConstraints);
		
		TextFieldHandeler.selectAllAtTextFieldFocus(IncomeTextField);
		TextFieldHandeler.selectAllAtTextFieldFocus(AmountTextField);
		TextFieldHandeler.selectAllAtTextFieldFocus(NotesTextField);

		pack();
	}// </editor-fold>                           

	
	private void normalFrameUpdateOperation(Operation operation,
			int tabbedPaneIndex, int operationRowNumber) {
		int amount;
		if (NumbersHandling.isInteger(AmountTextField.getText())) {
			amount = Integer.parseInt(AmountTextField.getText());
		} else {
			amount = operation.getAmount();
		}

		boolean paid = PaidRadioButton.isSelected();

		boolean returned = ReturnedRadioButton.isSelected();

		double income;
		if (NumbersHandling.isDouble(IncomeTextField.getText())) {
			income = Double.parseDouble(IncomeTextField.getText());
		} else {
			income = operation.getIncome();
		}

		String notes = NotesTextField.getText();

		operation.setAmount(amount);
		operation.setPaid(paid);
		operation.setReturned(returned);
		operation.setIncome(income);
		operation.setNotes(notes);

		switch (tabbedPaneIndex) {

		case 0:
			try {
				EditOperationsDialog.this.dailyOperationsPanel_DailyOperationsTableModel
						.updateOperation(operation,
								operationRowNumber);
				EditOperationsDialog.this.dispose();
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(EditOperationsDialog.this, e.getMessage());
				e.printStackTrace();
			}
			break;

		case 1:
			try {
				EditOperationsDialog.this.allOperationsPanel_AllOperationsTableModel
						.updateOperation(operation,
								operationRowNumber);
				EditOperationsDialog.this.dispose();
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(EditOperationsDialog.this, e.getMessage());
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	
	private void rootFrameUpdateOperation(Operation operation,
			int operationRowNumber) {
		int amount;
		if (NumbersHandling.isInteger(AmountTextField.getText())) {
			amount = Integer.parseInt(AmountTextField.getText());
		} else {
			amount = operation.getAmount();
		}

		boolean paid = PaidRadioButton.isSelected();

		boolean returned = ReturnedRadioButton.isSelected();

		double income;
		if (NumbersHandling.isDouble(IncomeTextField.getText())) {
			income = Double.parseDouble(IncomeTextField.getText());
		} else {
			income = operation.getIncome();
		}

		String notes = NotesTextField.getText();

		operation.setAmount(amount);
		operation.setPaid(paid);
		operation.setReturned(returned);
		operation.setIncome(income);
		operation.setNotes(notes);
		
		try {
			EditOperationsDialog.this.operationsTableModel.updateOperation((RootOperation) operation, operationRowNumber);
			EditOperationsDialog.this.dispose();
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(EditOperationsDialog.this, e.getMessage());
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
			java.util.logging.Logger.getLogger(EditOperationsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(EditOperationsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(EditOperationsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(EditOperationsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				EditOperationsDialog dialog = new EditOperationsDialog(new javax.swing.JFrame(), true);
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

	private javax.swing.JLabel AmountLabel;
	private javax.swing.JTextField AmountTextField;
	private javax.swing.JButton DeleteButton;
	private javax.swing.JLabel IncomeLabel;
	private javax.swing.JTextField IncomeTextField;
	private javax.swing.JLabel ItemSoldLabel;
	private javax.swing.JLabel NotesLabel;
	private javax.swing.JTextField NotesTextField;
	private javax.swing.JRadioButton PaidRadioButton;
	private javax.swing.JPanel Panel1;
	private javax.swing.JRadioButton ReturnedRadioButton;
	private javax.swing.JButton UpdateButton;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField ItemSoldName;
	private ButtonGroup buttonGroup;
	private JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox;
}
