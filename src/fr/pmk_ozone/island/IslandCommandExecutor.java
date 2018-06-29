package fr.pmk_ozone.island;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.pmk_ozone.island.commands.ISubCommand;

public class IslandCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public IslandCommandExecutor() {
		
		
		
	}
	
	private List<SubData> subCommandList;
	
	public void addSubCommand(String sub , String perm , ISubCommand i) {
		
		
		
	}
	
	public void addSubCommand(String sub , ISubCommand i) {
		
		
		
	}

}
