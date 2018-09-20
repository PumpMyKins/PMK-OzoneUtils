package fr.pmk_ozone.erebus.commands;

import java.util.ArrayList;
import java.util.List;

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

	//GUI OPENER + COMMAND SET
	@SuppressWarnings("deprecation")
	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		if(sender.getWorld().getName().endsWith("DIM66")) {
			
			List<String> LoreTarantule = new ArrayList<>();
			List<String> LoreCrushRoom = new ArrayList<>();
			List<String> LoreOverLord = new ArrayList<>();
			
			System.out.println("Sucess");
			
			//TARENTULE
			ItemStack tarentula = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)43);
			ItemMeta tarentulaM = tarentula.getItemMeta();
			LoreTarantule.add("Prix D'apparition 2000$");
			tarentulaM.setLore(LoreTarantule);
			tarentula.setItemMeta(tarentulaM);
			
			//CRUSHROOM
			ItemStack crushroom = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)40);
			ItemMeta crushroomM = crushroom.getItemMeta();
			LoreCrushRoom.add("Prix D'apparition 1000$");
			crushroomM.setLore(LoreCrushRoom);
			crushroom.setItemMeta(crushroomM);
			
			//OVERLORD
			ItemStack overlord = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)57);
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
			sender.sendMessage("");
		}
		
		return false;
	}
	
	//EVENT HANDLER FOR CLICKED ON EGGS
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		Player clicker = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		if(inventory.getName().equals(invBoss.getName())) {
			event.setCancelled(true);
			
			
		}
		
		
	}

}
