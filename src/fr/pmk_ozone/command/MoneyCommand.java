package fr.pmk_ozone.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xanium.gemseconomy.api.GemsEconomyAPI;

public class MoneyCommand implements CommandExecutor{
	
	GemsEconomyAPI apiEco = new GemsEconomyAPI();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			double balance =  apiEco.getBalance(((Player) sender).getUniqueId());
			sender.sendMessage("§2Vous avez §r§4"+ balance +" §r§6PumpCoins");
		}
		return false;
	}

	
}
