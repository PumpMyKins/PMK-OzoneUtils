package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class CResetIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			// reset de l'island
			
			return true;
			
		}else {
			
			new ResetIslandCmd().aide(sender);		
			return true;
		}
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub

	}

}
