package global.gui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 387181115753864483L;
	
	public FileChooser() {
		super();
		this.setDialogTitle("Database files");
		this.setFileFilter(new FileNameExtensionFilter("Database files (*.sql)" , "sql"));
	}
}
