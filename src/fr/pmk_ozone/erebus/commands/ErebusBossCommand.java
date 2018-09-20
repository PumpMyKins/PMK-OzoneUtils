package fr.pmk_ozone.erebus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.pmk_ozone.erebus.commands.ISubCommand;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import net.md_5.bungee.api.ChatColor;

public class ErebusBossCommand implements ISubCommand{

		
	Inventory invBoss = Bukkit.createInventory(null, 9, ChatColor.RED + "" + ChatColor.BOLD + "Boss de Erebus");
	GemsEconomyAPI apiEco = new GemsEconomyAPI();
	@SuppressWarnings("deprecation")
	ItemStack tarentula = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)43);
	@SuppressWarnings("deprecation")
	ItemStack crushroom = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)40);
	@SuppressWarnings("deprecation")
	ItemStack overlord = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)57);

	
	//GUI OPENER + COMMAND SET
	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		if(sender.getWorld().getName().endsWith("DIM66")) {
			
			List<String> LoreTarantule = new ArrayList<>();
			List<String> LoreCrushRoom = new ArrayList<>();
			List<String> LoreOverLord = new ArrayList<>();
			
			System.out.println("Sucess");
			
			//TARENTULE
			ItemMeta tarentulaM = tarentula.getItemMeta();
			LoreTarantule.add("Prix D'apparition 2000$");
			tarentulaM.setLore(LoreTarantule);
			tarentula.setItemMeta(tarentulaM);
			
			//CRUSHROOM
			ItemMeta crushroomM = crushroom.getItemMeta();
			LoreCrushRoom.add("Prix D'apparition 1000$");
			crushroomM.setLore(LoreCrushRoom);
			crushroom.setItemMeta(crushroomM);
			
			//OVERLORD
			ItemMeta overlordM = overlord.getItemMeta();
			LoreOverLord.add("Prix D'apparition 4000$");
			overlordM.setLore(LoreOverLord);
			overlord.setItemMeta(overlordM);
			
			invBoss.setItem(2, tarentula); //2000 
			invBoss.setItem(4, crushroom); //1000 
			invBoss.setItem(6, overlord);  //4000 
			
			sender.openInventory(invBoss);
			

		}
		else {
			/* DEBUG
			*System.out.println("Retry");
			*System.out.println(sender.getWorld().getName());
			*
			*
			*/
			sender.sendMessage("§2Vous devez être dans l'Erebus");
		}
		
		return false;
	}

	//EVENT HANDLER FOR CLICKED ON EGGS
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		Player clicker = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		ItemStack clicked = event.getCurrentItem();
		if(inventory.getName().equals(invBoss.getName())) {
			event.setCancelled(true);
			UUID playerUUID = clicker.getUniqueId();
			if(clicked.isSimilar(tarentula)) {
				
				if(apiEco.getBalance(playerUUID) < 2000) {
					apiEco.withdraw(playerUUID, 2000);
					
					
				}
			}
			
			if(clicked.isSimilar(crushroom)) {
				if(apiEco.getBalance(playerUUID) < 1000) {
					apiEco.withdraw(playerUUID, 1000);
					
					
				}
			}
			
			if(clicked.isSimilar(overlord)) {
				if(apiEco.getBalance(playerUUID) < 4000) {
					apiEco.withdraw(playerUUID, 4000);
					
					
				}
			}
			
			
		}
		
		
	}

}
