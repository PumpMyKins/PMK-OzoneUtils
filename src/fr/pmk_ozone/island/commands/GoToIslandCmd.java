package fr.pmk_ozone.island.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}
	
	

}