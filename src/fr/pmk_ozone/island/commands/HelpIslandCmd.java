package fr.pmk_ozone.island.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HelpIslandCmd implements ISubCommand {

	private static List<String> helpList = new ArrayList<String>();
	
	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		sender.sendMessage("help commande");
		
		sender.sendMessage("�3�l=======�r�b PumpMyAide�r�3�l =======");
		
		for (String string : args) {
			
		}
		
		sender.sendMessage("�3�l===============================");
		
		return true;
	}

	public boolean onSubCommand(Player p, Command cmd) {
		
		return onSubCommand(p, cmd, null);
		
	}

	public static void setMessage(File helpFile) {
		// TODO Auto-generated method stub
		
		
		
		
	}

}
