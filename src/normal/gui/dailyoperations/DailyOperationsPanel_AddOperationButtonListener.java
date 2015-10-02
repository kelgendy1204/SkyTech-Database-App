package normal.gui.dailyoperations;

import global.gui.ErrorMessage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import logic.NumbersHandling;
import normal.gui.itemspanel.Item;

public class DailyOperationsPanel_AddOperationButtonListener implements
		ActionListener {

	private JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox;
	private JTextField DailyOperationPanel_ManualPanel_AmountTextField;
	private JTextField DailyOperationPanel_ManualPanel_NotesTextField;
	private JRadioButton DailyOperationPanel_ManualPanel_PaidRadioButton;
	private JRadioButton DailyOperationPanel_ManualPanel_ReturnedRadioButton;
	private DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel;
	private JTable DailyOperationPanel_DailyOperationTable;
	private Component parent;

	public DailyOperationsPanel_AddOperationButtonListener(
			Component parent,
			JComboBox<Item> DailyOperationPanel_ManualPanel_ItemsComboBox,
			JTextField DailyOperationPanel_ManualPanel_AmountTextField,
			JRadioButton DailyOperationPanel_ManualPanel_PaidRadioButton,
			JRadioButton DailyOperationPanel_ManualPanel_ReturnedRadioButton,
			JTextField DailyOperationPanel_ManualPanel_NotesTextField,
			DailyOperationsPanel_DailyOperationsTableModel dailyOperationsPanel_DailyOperationsTableModel,
			JTable DailyOperationPanel_DailyOperationTable) {
		this.DailyOperationPanel_ManualPanel_ItemsComboBox = DailyOperationPanel_ManualPanel_ItemsComboBox;
		this.DailyOperationPanel_ManualPanel_AmountTextField = DailyOperationPanel_ManualPanel_AmountTextField;
		this.DailyOperationPanel_ManualPanel_PaidRadioButton = DailyOperationPanel_ManualPanel_PaidRadioButton;
		this.DailyOperationPanel_ManualPanel_ReturnedRadioButton = DailyOperationPanel_ManualPanel_ReturnedRadioButton;
		this.DailyOperationPanel_ManualPanel_NotesTextField = DailyOperationPanel_ManualPanel_NotesTextField;
		this.dailyOperationsPanel_DailyOperationsTableModel = dailyOperationsPanel_DailyOperationsTableModel;
		this.DailyOperationPanel_DailyOperationTable = DailyOperationPanel_DailyOperationTable;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addOperation();
	}

	public void addOperation() {
		Item item = (Item) DailyOperationPanel_ManualPanel_ItemsComboBox
				.getSelectedItem();

		int amount;
		if (NumbersHandling.isInteger(DailyOperationPanel_ManualPanel_AmountTextField.getText())) {
			amount = Integer.parseInt(DailyOperationPanel_ManualPanel_AmountTextField.getText());
		} else {
			amount = 1;
		}

		double income = Double.parseDouble(NumbersHandling.decimalFormat.format(item.getBuyingPrice() * amount));

		boolean isPaid = DailyOperationPanel_ManualPanel_PaidRadioButton.isSelected();
		boolean isReturned = DailyOperationPanel_ManualPanel_ReturnedRadioButton.isSelected();
		String notes = DailyOperationPanel_ManualPanel_NotesTextField.getText();
		
		try {
			dailyOperationsPanel_DailyOperationsTableModel.addOperationToDatabase(item, amount, isPaid, isReturned, income, notes);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
		
		DailyOperationPanel_DailyOperationTable.scrollRectToVisible(DailyOperationPanel_DailyOperationTable.getCellRect(DailyOperationPanel_DailyOperationTable.getRowCount()-1, 0, true));
		DailyOperationPanel_ManualPanel_ItemsComboBox.requestFocusInWindow();
		DailyOperationPanel_ManualPanel_ItemsComboBox.setSelectedIndex(0);
		DailyOperationPanel_ManualPanel_AmountTextField.setText("");
		DailyOperationPanel_ManualPanel_PaidRadioButton.setSelected(true);
		DailyOperationPanel_ManualPanel_NotesTextField.setText("");
	}

}
