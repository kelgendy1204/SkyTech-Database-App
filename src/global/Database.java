package global;

import global.gui.ErrorMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import logic.BackUpAndRestore;


public class Database {
	
	private static final String host = "localhost"; 
	private static final int port = 3306; 
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://"+ host +":" + port + "/skytech";
	private static final String user = "root";
	private static final String password = "114114114114";
	private Connection con;
	private JFrame parent;
	private BackUpAndRestore backUpAndRestore;
	
	public Database(JFrame parent) {
		this.parent = parent;
		backUpAndRestore = new BackUpAndRestore(this.parent);
	}
	
	public Connection getCon() {
		return con;
	}

	public void connect() {
		if (con != null) return;
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(DB_URL,user,password);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(parent, e.getMessage());
			e.printStackTrace();
			
			if(e.getMessage().equals("Unknown database 'skytech'")) {
				backUpAndRestore.loadDatabaseStructure();
				connect();
			} else {
				System.exit(0);
			}
		}
	}
	
	public void disconnect() {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				ErrorMessage.showErrorMessage(parent, e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static String getUser() {
		return user;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static String getHost() {
		return host;
	}

}
