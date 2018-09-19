package fr.pmk_ozone.erebus.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.erebus.commands.ISubCommand;
import me.xanium.gemseconomy.api.GemsEconomyAPI;

public class ErebusBossCommand implements ISubCommand{


	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		GemsEconomyAPI GemApi = new GemsEconomyAPI();

		if(sender.getWorld().getName().endsWith("DIM66")) {
			
			System.out.println("Sucess");
			
			
		}
		else {
			/* DEBUG
			*System.out.println("Retry");
			*System.out.println(sender.getWorld().getName());
			*
			*
			*/
		}
		
		return false;
	}

}
