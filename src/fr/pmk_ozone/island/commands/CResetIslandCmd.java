package fr.pmk_ozone.island.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// reset & set de l'owner
				
				String owner = sender.getUniqueId().toString();
				
				island.setOwnerUUID("none");
				
				sender.sendMessage(Island.prefix + "§r§d Votre ile est en cours de reset !");
				sender.teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
				
				try {
					IslandManager.unsetIsland(sender);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Unset Island ERROR");
					e1.printStackTrace();
				}
				
				CreateIslandCmd ciCmd = new CreateIslandCmd();				
				ciCmd.onSubCommand(sender, cmd, args); // création de la nouvelle ile
				sender.sendMessage(Island.prefix +"§d Ile recréée avec succès !");
				ciCmd.aide1(sender);	
				
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// récupération de la liste des joueurs
				
				for (Iterator<String> i = island.getPlayerList().iterator(); i.hasNext();) {
					
					String uuid = i.next();
					
					futureMemberList.add(uuid); 	// ajout à la future team
					
					i.remove();
					
					OfflinePlayer offp = MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(uuid));
					
					if(offp.isOnline()) {
						
						((CommandSender) offp).sendMessage(Island.prefix + "§r§d Votre ile est en cours de reset !");
						((Player) offp).teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
						
					}
					
					try {
						IslandManager.unsetIsland(offp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Unset Island ERROR");
						e.printStackTrace();
					}
					
				}
				
				try {
					island.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
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
