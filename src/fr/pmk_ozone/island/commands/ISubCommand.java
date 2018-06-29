package fr.pmk_ozone.island.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface ISubCommand {

	public boolean onSubCommand(Player sender , Command cmd , String[] args);
	
}
