package root.gui;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import logic.SpecialCharacterDispatcher;
import logic.TextFieldAndComboBoxHandeler;

public class RootUserSwitchingDialog extends javax.swing.JDialog {


	private static final long serialVersionUID = 5847975199194659968L;
	
	private javax.swing.JFrame parent;

	private SpecialCharacterDispatcher specialCharacterDispatcher;
	
    public RootUserSwitchingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public RootUserSwitchingDialog(java.awt.Frame parent, SpecialCharacterDispatcher asterixDispatcher) {
    	super(parent, ModalityType.APPLICATION_MODAL);
    	this.parent = (JFrame) parent;
    	this.specialCharacterDispatcher = asterixDispatcher;
		initComponents();
	}

    /**
     * Dropbox account
     * Username: skytechm32@yahoo.com
     * password: sky12042408
     */

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        PasswordLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        UsernameLabel = new javax.swing.JLabel();
        StatusLabel = new javax.swing.JLabel();
        UsernameTextField = new javax.swing.JTextField();
        SwitchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Root preferences");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        PasswordLabel.setText("Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(PasswordLabel, gridBagConstraints);

        PasswordField.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(PasswordField, gridBagConstraints);
        
        PasswordField.setToolTipText(null);

        UsernameLabel.setText("Username :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(UsernameLabel, gridBagConstraints);

        StatusLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        StatusLabel.setText("Root user ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(StatusLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(UsernameTextField, gridBagConstraints);

        SwitchButton.setText("Switch");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(SwitchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);
        
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(UsernameTextField);
        TextFieldAndComboBoxHandeler.selectAllAtTextFieldFocus(PasswordField);

        pack();
        
        UsernameTextField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			switchToRoot();
        		}
        	}
		});
        
        PasswordField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			switchToRoot();
        		}
        	}
        });
        
        
        SwitchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToRoot();
				
			}
		});
        
        
    }

    private void switchToRoot() {
		String username = UsernameTextField.getText();
		String password = String.valueOf(PasswordField.getPassword());
		
		if(username.equals(RootUserSwitchingDialog.this.username) && password.equals(RootUserSwitchingDialog.this.password)){
			RootUserSwitchingDialog.this.dispose();
			parent.dispose();
			new RootUserGUIFrame().setVisible(true);
	        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(specialCharacterDispatcher);
		}
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RootUserSwitchingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RootUserSwitchingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RootUserSwitchingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RootUserSwitchingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RootUserSwitchingDialog dialog = new RootUserSwitchingDialog(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final String username = "admin";
    private final String password = "12042408";
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JButton SwitchButton;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
