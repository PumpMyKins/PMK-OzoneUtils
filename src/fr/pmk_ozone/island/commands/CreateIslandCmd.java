package fr.pmk_ozone.island.commands;

import java.io.IOException;
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
			
			aide(sender);
			
		}else {
			// cr�ation de l'ile
			System.out.println("create island");
			
			try {
				is.createIsland(sender);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage("Vous faite parti / poss�dez d�j� une ile.");
		p.sendMessage("Cliquez ICI pour vous y t�l�porter !"); // click = tp
		p.sendMessage("Vous pouvez �galement quitter votre ile actuelle ou la r�initialiser !"); // click quitter = quitter || click r�init = reinit
		
	}
	
}
