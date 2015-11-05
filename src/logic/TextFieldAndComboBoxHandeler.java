package logic;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TextFieldAndComboBoxHandeler {
	
	
	public static void orientationHandeler (JComponent component){
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getModifiers() == InputEvent.CTRL_MASK ){
					if(component.getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT){
						English(component);
					} else {
						Arabic(component);
					}
				}
			}
		});
	}
	
    private static void Arabic(JComponent component){
    	component.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);    
	}

	private static void English(JComponent component){
		component.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);    
	}
	
	public static void selectAllAtTextFieldFocus(JTextField textField) {
		
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textField.selectAll();
					}
				});
			}
		});
	}
	
	public static void setCenterAlignmentForTextField (JTextField textField) {
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD , 13));
	}
	
}
