package fr.pmk_ozone.island.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class JoinIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		// TODO Auto-generated method stub
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(!is.playerHasIsland(sender)) {
			
			File f = new File(MainOzone.getInstance().getDataFolder(),"islands_invite.yml");
			YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
			
			if(!y.contains("invite." + sender.getUniqueId().toString())) {
				y.set("invite." + sender.getUniqueId().toString(), new ArrayList<String>());
			}
			
			List<String> l = y.getStringList("invite." + sender.getUniqueId().toString());
			
			if(args.isEmpty()) {
				
				aide(sender);
				
			}else {
				
				for (String string : l) {
					
				}
				
			}
				
		}else {
			// affichage aide
			aide(sender);
			return true;
		}
		
		return false;
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
		
	}

}
