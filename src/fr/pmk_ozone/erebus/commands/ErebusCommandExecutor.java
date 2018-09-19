package fr.pmk_ozone.erebus.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ErebusCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			
			if(args.length < 1) {
				
				new ErebusHelpCommand().onSubCommand(p, cmd, null);
			}
			else {
				
				String sub = args[0];
				
				for(SubData s : this.subCommandList) {
					
					String subCmd = s.getSubCommand();
					
					if(sub.equals(subCmd)) {
						
						return s.execute(p, cmd, getArgs(args));
					}		
				}
			}
		}
		return true;
	}
	public ErebusCommandExecutor() {
		
		this.subCommandList = new ArrayList<>();
		
	}
	private List<SubData> subCommandList;
	public void addSubCommand(String sub, ISubCommand i) {
		
		this.subCommandList.add(new SubData(sub, i));
	}
	
	public List<SubData> getSubCommandList(){
		
		return subCommandList;
	}
	private static List<String> getArgs(String[] a){
		
		List<String> l = new ArrayList<>();
		for (int i = 1; i <a.length; i++) {
			l.add(a[i]);
		}
		
		return l;
	}

}
