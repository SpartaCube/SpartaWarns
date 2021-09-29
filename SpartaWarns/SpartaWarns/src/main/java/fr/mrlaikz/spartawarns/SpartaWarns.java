package fr.mrlaikz.spartawarns;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrlaikz.spartawarns.command.Commandes;
import fr.mrlaikz.spartawarns.database.Data;
import fr.mrlaikz.spartawarns.database.MySQL;
import fr.mrlaikz.spartawarns.listener.LeaveEvent;
import fr.mrlaikz.spartawarns.listener.SanctionInv;
import net.md_5.bungee.api.ChatColor;

public class SpartaWarns extends JavaPlugin {
	
	public static SpartaWarns instance;
  
	public static HashMap<Player, OfflinePlayer> sanctions = new HashMap<>();
  
	public MySQL db;
  
	public Data data;
	  
	public void onEnable() {
		
		//VARIABLES
		instance = this;
	    sanctions.clear();
	    saveDefaultConfig();
	    
	    //MISC
	    System.out.println("[SpartaWarms] Plugin actif");
	    
	    //COMMANDS
	    getCommand("sanction").setExecutor((CommandExecutor)new Commandes());
	    getCommand("sanctiongui").setExecutor((CommandExecutor)new Commandes());
	    
	    //SQL
	    this.db = new MySQL(this);
	    this.data = new Data(this);
	    try {
	    	this.db.connect();
	    	System.out.println("[SpartaWarns] Base de donnActive");
	    } catch (SQLException|ClassNotFoundException e) {
	    	e.printStackTrace();
	    } 
	    if (this.db.isConnected()) {
	    	this.data.createTable(); 
	    }
	    getServer().getPluginManager().registerEvents((Listener)new SanctionInv(this), (Plugin)this);
	    getServer().getPluginManager().registerEvents((Listener)new LeaveEvent(), (Plugin)this);
	    
	}
  
	public void onDisable() {
		System.out.println("[SpartaWarms] Plugin Innactif");
	}
  
	public static SpartaWarns getInstance() {
		return instance;
	}
  
	public static String strConfig(String c) {
		return ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString(c));
	}
  
	public static int intConfig(String c) {
		return getInstance().getConfig().getInt(c);
	}
  
	public static boolean boolConfig(String c) {
		return getInstance().getConfig().getBoolean(c);
	}
  
	public static Material matConfig(String c) {
		return Material.matchMaterial(strConfig(c));
	}
}
