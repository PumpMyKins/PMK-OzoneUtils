package fr.pmk_ozone.island.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class CResetIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			if(sender.getUniqueId().toString().equals(island.getOwnerUUID())) {
				
				List<String> futureMemberList = new ArrayList<>();
				
				// reset de l'island
				
				// récupération de la liste des joueurs
				for (Iterator<String> i = island.getPlayerList().iterator(); i.hasNext();) {
					
					String uuid = i.next();
					
					futureMemberList.add(uuid); 	// ajout à la future team
					
					i.remove();
					
					try {
						IslandManager.unsetIsland(MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(uuid)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Unset Island");
						e.printStackTrace();
					}
					
				}
				
				
				
				return true;
				
			}else {
				
				// impossible car pas propriétaire
				new ResetIslandCmd().aide3(sender);
				return true;
				
			}
			
		}else {
			
			new ResetIslandCmd().aide(sender);		
			return true;
		}
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub

	}

}
