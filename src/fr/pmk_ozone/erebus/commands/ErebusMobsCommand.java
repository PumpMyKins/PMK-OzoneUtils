package fr.pmk_ozone.erebus.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.erebus.commands.ISubCommand;;

public class ErebusMobsCommand implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {

		sender.sendMessage("§6§l[§r§2PumpMyErebus§6§l] §r§2§oListe des Commandes §r§6§l[§r§2PumpMyKit§6§l]");
		sender.sendMessage("§2 Commande en cours de déploiement");
		
		return false;
	}

}
