package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.IslandManager;

public class CreateIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		// TODO Auto-generated method stub
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// téléportation
			System.out.println("téléportation");			
		}else {
			// affichage aide
			
		}
		
		return false;
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
	}
	
}
