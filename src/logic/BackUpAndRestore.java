package logic;

import global.Database;
import global.gui.ErrorMessage;
import global.gui.LoadSaveFileListener;
import global.gui.WaitingDialog;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import normal.gui.NormalUserGUIFrame;
import root.gui.RootUserGUIFrame;

public class BackUpAndRestore {
	
	private static final String firstCommand = "cmd /c cd C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin";
	private WaitingDialog waitingDialog;
	private JFrame parent;
	private LoadSaveFileListener loadSaveFileListener;
	
	public BackUpAndRestore(JFrame parent) {
		this.parent = parent;
		waitingDialog = new WaitingDialog(this.parent);
		loadSaveFileListener = new LoadSaveFileListener(parent);
	}
	
	public void saveToFile() {
		
		SwingWorker<Process , Integer> swingWorker = new SwingWorker<Process, Integer> () {

			@Override
			protected void done() {
				try {
					Process process = get();
					if(process == null) {
						return;
					}
					process.waitFor();
				} catch (InterruptedException e) {
					ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
					e.printStackTrace();
				} catch (ExecutionException e) {
					ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
					e.printStackTrace();
				}
				
				waitingDialog.setVisible(false);
				Toolkit.getDefaultToolkit().beep();
			}
			
			@Override
			protected Process doInBackground() throws Exception {
				File file = loadSaveFileListener.getFileToSave();
				if(file == null) {
					return null;
				}
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Dimension dimension = parent.getSize();
						waitingDialog.setLocation(dimension.width/2-waitingDialog.getSize().width/2, dimension.height/2-waitingDialog.getSize().height/2);
						waitingDialog.setVisible(true);
					}
				});
				
				return saveToFileNamed(file);
			}
			
		};
		
		swingWorker.execute();
		
	}
	
	
	public void loadFromFile() {
		SwingWorker<Process , Integer> swingWorker = new SwingWorker<Process, Integer> () {
			
			@Override
			protected void done() {
				try {
					Process process = get();
					if(process == null) {
						return;
					}
					process.waitFor();
				} catch (InterruptedException e) {
					ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
					e.printStackTrace();
				} catch (ExecutionException e) {
					ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
					e.printStackTrace();
				}
				
				if(parent.getName().equals("Normal user GUI")){
					((NormalUserGUIFrame) parent).loadItemComboBox();
					((NormalUserGUIFrame) parent).refresh();
				} else {
					((RootUserGUIFrame) parent).refresh();
				}
				
				waitingDialog.setVisible(false);
				
				Toolkit.getDefaultToolkit().beep();
			}
			
			@Override
			protected Process doInBackground() throws Exception {
				File file = loadSaveFileListener.getFileToLoad();

				if(file == null || file.exists() == false) {
					return null;
				}
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Dimension dimension = parent.getSize();
						waitingDialog.setLocation(dimension.width/2-waitingDialog.getSize().width/2, dimension.height/2-waitingDialog.getSize().height/2);
						waitingDialog.setVisible(true);
					}
				});
				
				String secondCommand = "mysql -h " + Database.getHost() +" -u " + Database.getUser() + " -p" + Database.getPassword() + " --default-character-set=utf8  < " + file.getAbsolutePath();
				Process process = Runtime.getRuntime().exec(firstCommand + "&&" + secondCommand);
				return process;
				
			}
			
		};
		
		swingWorker.execute();
		
	}
	
	public void loadDatabaseStructure() {
		String filePath = "c:\\users\\" + System.getProperty("user.name") + "\\appdata\\local\\temp\\tmpmoyts3287293s.cnf";
		String databaseStructureSourcePath = "/tempfiles/Structure.sql"; 
		InputStream inputStream = this.getClass().getResourceAsStream(databaseStructureSourcePath);
		InputStreamToFile inputStreamToFile = new InputStreamToFile(inputStream, filePath);
		File file = inputStreamToFile.getFile();
		
		String secondCommand = "mysql -h " + Database.getHost() +" -u " + Database.getUser() + " -p" + Database.getPassword() + " --default-character-set=utf8  < " + file.getAbsolutePath();
		try {
			Process process = Runtime.getRuntime().exec(firstCommand + "&&" + secondCommand);
			process.waitFor();
		} catch (IOException e) {
			ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			ErrorMessage.showErrorMessage(waitingDialog, e.getMessage());
			e.printStackTrace();
		}
		Toolkit.getDefaultToolkit().beep();
		file.delete();
	}
	
	public Process saveToFileNamed(File file) throws IOException {
		String secondCommand = "mysqldump -h " + Database.getHost() +" -u " + Database.getUser() + " -p" + Database.getPassword() + " --default-character-set=utf8 --single-transaction=TRUE --routines --databases skytech > " + file.getAbsolutePath();
		Process process = Runtime.getRuntime().exec(firstCommand + "&&" + secondCommand);
		return process;
	}

	public static String getFirstcommand() {
		return firstCommand;
	}
	
}