package fr.pmk_ozone;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.pmk_ozone.island.IslandManager;

public class PlayerEvents implements Listener {

	@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		IslandManager is = MainOzone.getIslandManager();
		
		if(!is.playerHasIsland(p)) {
			
			p.teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
			
		}
		
	}
	
}
