package fr.pmk_ozone.command;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartingStuffCommand implements CommandExecutor {

	private static HashMap<String, Timestamp> hashStartHash = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static HashMap<String, Timestamp> getHashStartHash() {
		return hashStartHash;
	}

	public static void setHashStartHash(HashMap<String, Timestamp> hashStartHash) {
		StartingStuffCommand.hashStartHash = hashStartHash;
	}

}
