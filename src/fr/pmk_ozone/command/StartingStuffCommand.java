package fr.pmk_ozone.command;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;

public class StartingStuffCommand implements CommandExecutor {

	private static HashMap<String, Timestamp> hashStartHash = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		Player p = (Player) sender;
		String uuid = p.getUniqueId().toString();
		
		if(hashStartHash.containsKey(uuid)) {
			
			//check du timestamp
			Timestamp t = hashStartHash.get(uuid);
			
			if(t.getTime() >= 900000) {	// 15 minutes d'attente
				//envoie du message d'erreur
				
				return true;
			}			
			
			hashStartHash.remove(uuid);
			
		}else {
			
			// ajout du joueur et du timestamp
			
			hashStartHash.put(uuid, new Timestamp(System.currentTimeMillis()));			
			
		}
		
		// execution de la commande
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.RED + "" + ChatColor.BOLD + "Stuff de départ");
		
		// ajout des items
		
		inv.setItem(inv.firstEmpty(), null);
		
		p.openInventory(inv);	// ouverture de l'inventaire
		
		return true;
	}
	
	public static HashMap<String, Timestamp> getHashStartHash() {
		return hashStartHash;
	}

	public static void setHashStartHash(HashMap<String, Timestamp> hashStartHash) {
		StartingStuffCommand.hashStartHash = hashStartHash;
	}

}
