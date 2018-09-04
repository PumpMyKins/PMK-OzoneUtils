package fr.pmk_ozone;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.pmk_ozone.island.IslandManager;
import fr.pmk_ozone.reboot.StopManager;

public class PlayerEvents implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(StopManager.stop) {
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("lobby");
			
			p.sendPluginMessage(MainOzone.getInstance(), "BungeeCord", out.toByteArray());
			
		}
		
		e.setJoinMessage("§l[§r§a+§r§l]§r§e " + p.getName());
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(!is.playerHasIsland(p)) {
			
			p.teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
			
		}
		
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		e.setQuitMessage("§l[§r§c-§r§l]§r§e " + p.getName());
		
	}
	
}
