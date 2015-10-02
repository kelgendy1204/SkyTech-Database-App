package global.gui;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorMessage {
	
	public static void showErrorMessage(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
