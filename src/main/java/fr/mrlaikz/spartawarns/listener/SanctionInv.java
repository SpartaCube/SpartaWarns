package fr.mrlaikz.spartawarns.listener;

import fr.mrlaikz.spartawarns.SpartaWarns;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SanctionInv implements Listener {
	private SpartaWarns plugin;
  
	public SanctionInv(SpartaWarns plugin) {
		this.plugin = plugin;
	}
  
	@EventHandler
	public void onInteract(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		String name = e.getView().getTitle();
		OfflinePlayer c = (OfflinePlayer)SpartaWarns.sanctions.get(p);
		if (name.equals(SpartaWarns.strConfig("inventory.sanction.name")) && SpartaWarns.sanctions.containsKey(p)) {
		
			if (e.getCurrentItem() != null) {
				
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.chat.name"))) {
					p.closeInventory();
					p.performCommand("sanctiongui chat");
				} 
				
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.name"))) {
					p.closeInventory();
	        		p.performCommand("sanctiongui game");
				} 
        
				e.setCancelled(true);
	        
			} 
		}
		
		if (name.equals(SpartaWarns.strConfig("inventory.sanction.chat.name")) || name.equals(SpartaWarns.strConfig("inventory.sanction.game.name"))) {
			
			e.setCancelled(true);
			p.closeInventory();
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.chat.flood.name"))) {
				sanction(p, c, "flood");
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.chat.spam.name"))) {
				sanction(p, c, "spam"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.chat.insulte1.name"))) {
				sanction(p, c, "insulte1");
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.chat.insulte2.name"))) {
				sanction(p, c, "insulte2"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.xray.name"))) {
				sanction(p, c, "xray"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.cheat.name"))) {
				sanction(p, c, "cheat"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.vol.name"))) {
				sanction(p, c, "vol"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.usebug.name"))) {
				sanction(p, c, "usebug"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.eco.name"))) {
				sanction(p, c, "eco"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.arnaque.name"))) {
				sanction(p, c, "arnaque"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.afk.name"))) {
				sanction(p, c, "anti-afk"); 
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(SpartaWarns.strConfig("inventory.sanction.game.kill.name"))) {
				sanction(p, c, "kill"); 
			}
			
			SpartaWarns.sanctions.remove(p);
		} 
	}
	
	public void sanction(Player modo, OfflinePlayer c, String sanction) {
		if (!this.plugin.data.getSanction(c).contains(sanction)) {
			this.plugin.data.insertSanction(sanction, c);
			
			switch(sanction) {
				case "flood":
					modo.sendMessage(SpartaWarns.strConfig("message.avertissement").replace("%player%", c.getName()));
					break;
				case "spam":
					modo.sendMessage(SpartaWarns.strConfig("message.avertissement").replace("%player%", c.getName()));
					break;
				case "insulte1":
					modo.sendMessage(SpartaWarns.strConfig("message.avertissement").replace("%player%", c.getName()));
					mute(modo, c, "10m", "Insultes");
					break;
				case "insulte2":
					modo.sendMessage(SpartaWarns.strConfig("message.avertissement").replace("%player%", c.getName()));
					mute(modo, c, "30m", "Insultes");
					break;
				
				case "xray":
					modo.performCommand(SpartaWarns.strConfig("commands.clear").replace("%player%", c.getName()));
					tempban(modo, c, "1mo", "X-Ray");
					break;
				case "cheat":
					tempban(modo, c, "1mo", "Cheat");
					break;
				case "vol":
					tempban(modo, c, "12h", "Vol");
					break;
				case "usebug":
					ban(modo, c, "Usebug");
					break;
				case "eco":
					warn(modo, c, "Economie");
					break;
				case "arnaque":
					tempban(modo, c, "1d", "Arnaque");
					break;
				case "anti-afk":
					tempban(modo, c, "2d", "Anti-AFK");
					break;
				case "kill":
					modo.sendMessage(SpartaWarns.strConfig("message.avertissement").replace("%player%", c.getName()));
					break;
			}

		} else if (this.plugin.data.getSanctionAmount(c).get(this.plugin.data.getSanction(c).indexOf(sanction)) == 1) {
			this.plugin.data.addSanction(sanction, c);
			
			switch(sanction) {
				case "flood":
					mute(modo, c, "30m", "Flood");
					break;
				case "spam":
					mute(modo, c, "1h", "Spam");
					break;
				case "insulte1":
					mute(modo, c, "1h", "Insultes");
					break;
				case "insulte2":
					warn(modo, c, "Insultes");
					mute(modo, c, "1h", "Insultes");
					break;
				
				case "xray":
					ban(modo, c, "X-Ray");
					break;
				case "cheat":
					ban(modo, c, "Cheat");
					break;
				case "vol":
					tempban(modo, c, "2w", "Vol");
					break;
				case "usebug":
					ban(modo, c, "Usebug");
					break;
				case "eco":
					warn(modo, c, "Economie");
					break;
				case "arnaque":
					tempban(modo, c, "3d", "Arnaque");
					break;
				case "anti-afk":
					tempban(modo, c, "4d", "Anti-AFK");
					break;
				case "kill":
					modo.performCommand(SpartaWarns.strConfig("commands.kick").replace("%player%", c.getName()).replace("%reason%", "Kill"));
					break;
			}
			
		} else if (this.plugin.data.getSanctionAmount(c).get(this.plugin.data.getSanction(c).indexOf(sanction)) >= 2) {
			this.plugin.data.addSanction(sanction, c);
			
			switch(sanction) {
				case "flood":
					mute(modo, c, "1h", "Flood");
					break;
				case "spam":
					mute(modo, c, "2h", "Spam");
					break;
				case "insulte1":
					warn(modo, c, "Insultes");
					break;
				case "insulte2":
					tempban(modo, c, "1h", "Insultes");
					break;
				
				case "xray":
					ban(modo, c, "X-Ray");
					break;
				case "cheat":
					ban(modo, c, "Cheat");
					break;
				case "vol":
					ban(modo, c, "Vol");
					break;
				case "usebug":
					ban(modo, c, "Usebug");
					break;
				case "eco":
					warn(modo, c, "Economie");
					break;
				case "arnaque":
					ban(modo, c, "Arnaque");
					break;
				case "anti-afk":
					tempban(modo, c, "6d", "Anti-AFK");
					break;
				case "kill":
					tempban(modo, c, "2d", "Anti-AFK");
					break;
			}
			
		} 
	}
  
	public void mute(Player modo, OfflinePlayer c, String time, String raison) {
		modo.performCommand(SpartaWarns.strConfig("commands.mute").replace("%player%", c.getName()).replace("%time%", time).replace("%reason%", raison));
	}
  
	public void tempban(Player modo, OfflinePlayer c, String time, String raison) {
		modo.performCommand(SpartaWarns.strConfig("commands.tempban").replace("%player%", c.getName()).replace("%time%", time).replace("%reason%", raison));
	}
  
	public void ban(Player modo, OfflinePlayer c, String raison) {
		modo.performCommand(SpartaWarns.strConfig("commands.ban").replace("%player%", c.getName()).replace("%reason%", raison));
	}
  
	public void warn(Player modo, OfflinePlayer c, String raison) {
		modo.performCommand(SpartaWarns.strConfig("commands.warn").replace("%player%", c.getName()).replace("%reason%", raison));
	}
}
