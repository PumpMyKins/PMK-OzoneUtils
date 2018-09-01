package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.IslandManager;

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// téléportation
			System.out.println("téléportation");			
		}else {
			// affichage aide
			aide(sender);
			
		}
		
		return false;
		
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
		p.sendMessage("Sans ile, vous ne pouvez pas vous téléporter à celle-ci !");
		p.sendMessage("Pour créer votre ile, cliquez ICI"); // clique = aide create island
		
	}
	
	

}
