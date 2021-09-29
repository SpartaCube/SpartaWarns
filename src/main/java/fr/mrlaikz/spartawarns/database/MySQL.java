package fr.mrlaikz.spartawarns.database;

import fr.mrlaikz.spartawarns.SpartaWarns;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private String host = SpartaWarns.strConfig("database.host");
  
	private String port = SpartaWarns.strConfig("database.port");
  
	private String database = SpartaWarns.strConfig("database.database");
  
	private String username = SpartaWarns.strConfig("database.username");
  
	private String password = SpartaWarns.strConfig("database.password");
  
	public String table = SpartaWarns.strConfig("database.table");
  
	private Connection connection;
  
	public SpartaWarns plugin;
  
	public MySQL(SpartaWarns plugin) {
		this.plugin = plugin;
	}
  
	public boolean isConnected() {
		return !(this.connection == null);
	}
  
	public void connect() throws ClassNotFoundException, SQLException {
		if (!isConnected()) {
			this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password); 
		}
	}
  
	public void disconnect() {
		if (isConnected())
			try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
  
	public Connection getConnection() {
		return this.connection;
	}
}
