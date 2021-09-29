package fr.mrlaikz.spartawarns.database;

import fr.mrlaikz.spartawarns.SpartaWarns;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bukkit.OfflinePlayer;

public class Data {
	
	private SpartaWarns plugin;
  
	public Data(SpartaWarns plugin) {
		this.plugin = plugin;
	}
  
	public void createTable() {
		try {
			PreparedStatement ps = this.plugin.db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + this.plugin.db.table + " (id INT AUTO_INCREMENT PRIMARY KEY, player VARCHAR(255), amount INT(11), sanction VARCHAR(255))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
  
	public void insertSanction(String sanction, OfflinePlayer p) {
		try {
			PreparedStatement ps = this.plugin.db.getConnection().prepareStatement("INSERT INTO " + this.plugin.db.table + " (sanction, player, amount) VALUES(?, ?, ?)");
			ps.setString(1, sanction);
			ps.setString(2, p.getName());
			ps.setInt(3, 1);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
  
	public void addSanction(String sanction, OfflinePlayer p) {
		try {
			PreparedStatement ps = this.plugin.db.getConnection().prepareStatement("UPDATE " + this.plugin.db.table + " SET amount = ? WHERE player = ? AND sanction = ?");
			ps.setInt(1, ((Integer)getSanctionAmount(p).get(getSanction(p).indexOf(sanction))).intValue() + 1);
			ps.setString(2, p.getName());
			ps.setString(3, sanction);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
  
	public ArrayList<String> getSanction(OfflinePlayer p) {
		ArrayList<String> list = new ArrayList<>();
		try {
			PreparedStatement ps = this.plugin.db.getConnection().prepareStatement("SELECT * FROM " + this.plugin.db.table + " WHERE player = ?");
			ps.setString(1, p.getName().toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				list.add(rs.getString("sanction")); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
  
  public ArrayList<Integer> getSanctionAmount(OfflinePlayer p) {
    ArrayList<Integer> list = new ArrayList<>();
    try {
      PreparedStatement ps = this.plugin.db.getConnection().prepareStatement("SELECT * FROM " + this.plugin.db.table + " WHERE player = ?");
      ps.setString(1, p.getName().toString());
      ResultSet rs = ps.executeQuery();
      while (rs.next())
        list.add(Integer.valueOf(rs.getInt("amount"))); 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return list;
  }
}
