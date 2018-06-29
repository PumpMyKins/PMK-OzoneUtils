package fr.pmk_ozone.island;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pmk_ozone.island.commands.GoToIslandCmd;
import fr.pmk_ozone.island.commands.HelpIslandCmd;
import fr.pmk_ozone.island.commands.ISubCommand;

public class IslandCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if( args.length == 0 ) {
				
				return new GoToIslandCmd().onSubCommand(p, cmd, args);
				
			}else {
				
				for (SubData sub : this.subCommandList) {
					
					
					
				}
				
				return new HelpIslandCmd().onSubCommand(p, cmd, args);
				
			}
			
		}else {
		
			sender.sendMessage("Vous devez etre joueur pour faire cela !");
			return true;
			
		}
		
	}
	
	public IslandCommandExecutor() {
		
		this.subCommandList = new ArrayList<>();
		
	}
	
	private List<SubData> subCommandList;
	
	public void addSubCommand(String sub , String perm , ISubCommand i) {
		
		this.subCommandList.add(new SubData(sub, perm, i));
		
	}
	
	public void addSubCommand(String sub , ISubCommand i) {
		
		this.subCommandList.add(new SubData(sub, i));
		
	}

	public List<SubData> getSubCommandList() {
		return subCommandList;
	}
	
}
