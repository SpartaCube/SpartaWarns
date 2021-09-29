package fr.mrlaikz.spartawarns.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.mrlaikz.spartawarns.SpartaWarns;

public class Commandes implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("sanction")) {
				if (args.length == 0) {
					p.sendMessage(SpartaWarns.strConfig("message.syntax"));
					return false;
				}
				
				if (args.length == 1 && p.hasPermission("spartawarns.use")) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						p.sendMessage(SpartaWarns.strConfig("message.invalid_player"));
						return false;
					} 
					p.openInventory(openSanctionInventory());
					SpartaWarns.sanctions.put(p, player);
					return true;
				} 
				
			} 
			
			if (cmd.getName().equalsIgnoreCase("sanctiongui")) {
				
				if(p.hasPermission("spartawarns.use")) {
					
					if(args.length == 0) {
						p.sendMessage(SpartaWarns.strConfig("message.syntax"));
					}
					if (args.length == 1 && args[0].equalsIgnoreCase("chat")) {
					p.openInventory(openChatInventory());
					return true;
					} 
					if (args.length == 1 && args[0].equalsIgnoreCase("game")) {
						p.openInventory(openGameInventory());
						return true;
					}
					if (args.length == 1 && args[0].equalsIgnoreCase("reload") && p.hasPermission("spartawarns.admin")) {
						SpartaWarns.getInstance().reloadConfig();
						p.sendMessage(SpartaWarns.strConfig("message.config_reload"));
						return true;
					} 
					
				} else {
					p.sendMessage(SpartaWarns.strConfig("message.permission"));
				}
				
				
			}
		}
		return false;
	}
  
	public Inventory openSanctionInventory() {
		
		Inventory inv = Bukkit.createInventory(null, 54, SpartaWarns.strConfig("inventory.sanction.name"));
		
		ItemStack chat = new ItemStack(SpartaWarns.matConfig("inventory.sanction.chat.item"));
	    ItemStack game = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.item"));
	    
	    ItemMeta chatM = chat.getItemMeta();
	    ItemMeta gameM = game.getItemMeta();
	    
	    chatM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.chat.name"));
	    gameM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.name"));
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.chat.enchant")) {
	    	chatM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	chatM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.enchant")) {
	    	gameM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	gameM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    chat.setItemMeta(chatM);
	    game.setItemMeta(gameM);
	    
	    inv.setItem(20, chat);
	    inv.setItem(24, game);
	    
	    return inv;
	  }
  
	public Inventory openChatInventory() {
	    Inventory inv = Bukkit.createInventory(null, 54, SpartaWarns.strConfig("inventory.sanction.chat.name"));
	    
	    ItemStack flood = new ItemStack(SpartaWarns.matConfig("inventory.sanction.chat.flood.item"));
	    ItemStack spam = new ItemStack(SpartaWarns.matConfig("inventory.sanction.chat.spam.item"));
	    ItemStack insulte1 = new ItemStack(SpartaWarns.matConfig("inventory.sanction.chat.insulte1.item"));
	    ItemStack insulte2 = new ItemStack(SpartaWarns.matConfig("inventory.sanction.chat.insulte1.item"));
	    
	    ItemMeta floodM = flood.getItemMeta();
	    ItemMeta spamM = spam.getItemMeta();
	    ItemMeta insulte1M = insulte1.getItemMeta();
	    ItemMeta insulte2M = insulte2.getItemMeta();
	    
	    floodM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.chat.flood.name"));
	    spamM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.chat.spam.name"));
	    insulte1M.setDisplayName(SpartaWarns.strConfig("inventory.sanction.chat.insulte1.name"));
	    insulte2M.setDisplayName(SpartaWarns.strConfig("inventory.sanction.chat.insulte2.name"));
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.chat.flood.enchant")) {
	    	floodM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	floodM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.chat.spam.enchant")) {
	    	spamM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	spamM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.chat.insulte1.enchant")) {
	    	insulte1M.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	insulte1M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.chat.insulte2.enchant")) {
	    	insulte2M.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	insulte2M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    flood.setItemMeta(floodM);
	    spam.setItemMeta(spamM);
	    insulte1.setItemMeta(insulte1M);
	    insulte2.setItemMeta(insulte2M);
	    
	    inv.setItem(11, flood);
	    inv.setItem(15, spam);
	    inv.setItem(29, insulte1);
	    inv.setItem(33, insulte2);
	    
	    return inv;
	}
  
	public Inventory openGameInventory() {
		
	    Inventory inv = Bukkit.createInventory(null, 45, SpartaWarns.strConfig("inventory.sanction.game.name"));
	    
	    ItemStack xray = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.xray.item"));
	    ItemStack cheat = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.cheat.item"));
	    ItemStack vol = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.vol.item"));
	    ItemStack usebug = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.usebug.item"));
	    ItemStack eco = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.eco.item"));
	    ItemStack arnaque = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.arnaque.item"));
	    ItemStack afk = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.afk.item"));
	    ItemStack kill = new ItemStack(SpartaWarns.matConfig("inventory.sanction.game.kill.item"));
	    
	    ItemMeta xrayM = xray.getItemMeta();
	    ItemMeta cheatM = cheat.getItemMeta();
	    ItemMeta volM = vol.getItemMeta();
	    ItemMeta usebugM = usebug.getItemMeta();
	    ItemMeta ecoM = eco.getItemMeta();
	    ItemMeta arnaqueM = arnaque.getItemMeta();
	    ItemMeta afkM = afk.getItemMeta();
	    ItemMeta killM = kill.getItemMeta();
	    
	    xrayM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.xray.name"));
	    cheatM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.cheat.name"));
	    volM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.vol.name"));
	    usebugM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.usebug.name"));
	    ecoM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.eco.name"));
	    arnaqueM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.arnaque.name"));
	    afkM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.afk.name"));
	    killM.setDisplayName(SpartaWarns.strConfig("inventory.sanction.game.kill.name"));
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.xray.enchant")) {
	    	xrayM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	xrayM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.cheat.enchant")) {
	    	cheatM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	cheatM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.vol.enchant")) {
	    	volM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	volM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.usebug.enchant")) {
	    	usebugM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	usebugM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.eco.enchant")) {
	    	ecoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	ecoM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.arnaque.enchant")) {
	    	arnaqueM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	arnaqueM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.afk.enchant")) {
	    	afkM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	afkM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    if (SpartaWarns.boolConfig("inventory.sanction.game.kill.enchant")) {
	    	killM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	    	killM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    } 
	    
	    xray.setItemMeta(xrayM);
	    cheat.setItemMeta(cheatM);
	    vol.setItemMeta(volM);
	    usebug.setItemMeta(usebugM);
	    eco.setItemMeta(ecoM);
	    arnaque.setItemMeta(arnaqueM);
	    afk.setItemMeta(afkM);
	    kill.setItemMeta(killM);
	    
	    inv.setItem(2, xray);
	    inv.setItem(6, cheat);
	    inv.setItem(19, vol);
	    inv.setItem(22, usebug);
	    inv.setItem(25, eco);
	    inv.setItem(37, arnaque);
	    inv.setItem(40, afk);
	    inv.setItem(43, kill);
	    
	    return inv;
	    
	}
}
