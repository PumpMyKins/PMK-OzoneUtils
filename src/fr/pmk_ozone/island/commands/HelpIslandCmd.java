package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HelpIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		
		
		return false;
	}

	public boolean onSubCommand(Player p, Command cmd) {
		
		return onSubCommand(p, cmd, null);
		
	}

}
