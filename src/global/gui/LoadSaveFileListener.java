package global.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LoadSaveFileListener {
	
	private FileChooser fileChooser;
	private JFrame parent;
	
	public LoadSaveFileListener(JFrame parent) {
		fileChooser = new FileChooser();
		this.parent = parent;
	}
	
	public File getFileToLoad(){
		if(fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}
	}
	
	public File getFileToSave() {
		if(fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath().toString();
			if(filePath.substring(filePath.length() - 4, filePath.length()).equals(".sql")) {
				return new File(filePath);
			} else {
				return new File(filePath + ".sql");
			}
		} else {
			return null;
		}
	}
	
}
