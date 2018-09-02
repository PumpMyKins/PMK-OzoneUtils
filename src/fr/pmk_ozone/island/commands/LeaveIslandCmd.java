package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class LeaveIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// téléportation
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			sender.teleport(new Location(Bukkit.getWorld("world"), island.getSpawnX(), island.getSpawnY(), island.getSpawnZ()));
			return true;
			
		}else {
			// affichage aide
			aide(sender);
			return false;
			
		}
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub

	}

}
