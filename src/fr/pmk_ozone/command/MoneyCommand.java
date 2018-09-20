package fr.pmk_ozone.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xanium.gemseconomy.api.GemsEconomyAPI;

public class MoneyCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
		GemsEconomyAPI apiEco = new GemsEconomyAPI();
		Player sender = (Player) cmdSender;
		if(sender instanceof Player) {
			double balance =  apiEco.getBalance(sender.getUniqueId());
			sender.sendMessage("§6§l[§r§2PumpMyEconomy§6§l]§r§2Vous avez §r§4"+ balance +" §r§6PumpCoins");
		}
		else {
			System.out.println("You are not a player");
		}
		return false;
	}

	
}
