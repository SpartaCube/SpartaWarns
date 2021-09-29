package fr.mrlaikz.spartawarns.listener;

import fr.mrlaikz.spartawarns.SpartaWarns;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
  @EventHandler
  public void onLeave(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    if (SpartaWarns.sanctions.containsKey(p)) {
      SpartaWarns.sanctions.remove(p);
      System.out.println("[SpartaWarns] Un mods'est den sanction !");
    } 
  }
}
