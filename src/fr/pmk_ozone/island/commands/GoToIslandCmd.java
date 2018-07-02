package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		sender.sendMessage("go to commande");		
		return true;
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}
	
	

}
