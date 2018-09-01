package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface ISubCommand {

	public boolean onSubCommand(Player sender , Command cmd , List<String> args);
	public void aide(Player p);
}
