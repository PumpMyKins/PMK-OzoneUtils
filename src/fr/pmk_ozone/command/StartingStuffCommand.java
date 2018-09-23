package fr.pmk_ozone.command;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.pmk_ozone.island.Island;
import net.md_5.bungee.api.ChatColor;

public class StartingStuffCommand implements CommandExecutor {

	private static HashMap<String, Timestamp> hashStartHash = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		String uuid = p.getUniqueId().toString();
		
		if(hashStartHash.containsKey(uuid)) {
			
			//check du timestamp
			Timestamp t = hashStartHash.get(uuid);
			
			if(new Timestamp(System.currentTimeMillis()).getTime() - t.getTime() <= 90000) {	// 15 minutes d'attente
				//envoie du message d'erreur
				
				sender.sendMessage(Island.prefix + "§r§c Vous devez attendre avant de pouvoir reprendre le kit !");
				
				return true;
			}			
			
			hashStartHash.remove(uuid);
			
		}else {
			
			// ajout du joueur et du timestamp
			
			hashStartHash.put(uuid, new Timestamp(System.currentTimeMillis()));			
			
		}
		
		// execution de la commande
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "" + ChatColor.BOLD + "Stuff de départ");
		
		// ajout des items
		
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.getMaterial(6893), 1)); // 1 quest book
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.APPLE, 16)); // 16 pomme
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.SAPLING, 2)); // 2 pousse d'arbre
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.TORCH, 1)); // 1 torche
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.DIAMOND, 1)); // 1 diamond
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.getMaterial(5242), 1)); // 1 livre tinker
		
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
