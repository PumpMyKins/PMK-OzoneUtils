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

	public static final String NO_PERM = "Vous n'avez pas la permission de faire cela !";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if( args.length < 1 ) {
				
				System.out.println("No sub command");
				return new GoToIslandCmd().onSubCommand(p, cmd);
				
			}else {
				
				String sub = args[0];
				
				System.out.println("Sub command");
				for (SubData s : this.subCommandList) {
					
					String subCmd = s.getSubCommand();
					String permission = s.getPermissionNode();
					
					if(sub.equals(subCmd)) {
						
						if(!permission.equals("none") & !p.hasPermission(permission)) {
							// pas la permission
							p.sendMessage(NO_PERM);
							return true;
							
						}else {
							// permission trouvé
							System.out.println("Executor sub command : " + s.getSubCommandExecutor().getClass().getName());
							return s.execute(p, cmd, getArgs(args));
							
						}
						
					}
					
				}
				
				System.out.println("Sub command no found");
				return new HelpIslandCmd().onSubCommand(p, cmd);
				
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
	
	private static List<String> getArgs(String[] a) {
		
		List<String> l = new ArrayList<>();
		
		for (int i = 1; i < a.length; i++) {
			
			l.add(a[i]);
			
		}
		
		return l;
		
	}
	
}
