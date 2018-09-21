package fr.pmk_ozone.erebus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.pmk_ozone.erebus.commands.ISubCommand;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import net.md_5.bungee.api.ChatColor;

public class ErebusBossCommand implements ISubCommand, Listener{

		
	Inventory invBoss = Bukkit.createInventory(null, 9, ChatColor.RED + "" + ChatColor.BOLD + "Boss de Erebus");
	GemsEconomyAPI apiEco = new GemsEconomyAPI();
	@SuppressWarnings("deprecation")
	ItemStack tarentula = new ItemStack(Material.getMaterial(7200), 2,(short)0,(byte)43);
	@SuppressWarnings("deprecation")
	ItemStack crushroom = new ItemStack(Material.getMaterial(7200), 1,(short)0,(byte)40);
	@SuppressWarnings("deprecation")
	ItemStack overlord = new ItemStack(Material.getMaterial(7200), 3,(short)0,(byte)57);

	
	//GUI OPENER + COMMAND SET
	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		if(sender.getWorld().getName().endsWith("DIM66")) {
			
			List<String> LoreTarantule = new ArrayList<>();
			List<String> LoreCrushRoom = new ArrayList<>();
			List<String> LoreOverLord = new ArrayList<>();
			
			
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
			
			invBoss.setItem(4, tarentula); //2000 
			invBoss.setItem(2, crushroom); //1000 
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
			if(clicked.getAmount() == 2) {
				if(apiEco.getBalance(playerUUID) > 2000) {
					apiEco.withdraw(playerUUID, 2000);
					clicker.sendMessage("2000 Vous ont été prélevé");
					@SuppressWarnings("deprecation")
					EntityType entitySpawn = EntityType.fromName("erebus-tarantulaminiboss");
					Location spawn = clicker.getLocation();
					World spawnworld = clicker.getWorld();
					spawnworld.spawnEntity(spawn, entitySpawn);
					
					
				}
			}
			
			else if(clicked.getAmount() == 1) {
				if(apiEco.getBalance(playerUUID) > 1000) {
					apiEco.withdraw(playerUUID, 1000);
					clicker.sendMessage("1000 Vous ont été prélevé");
					@SuppressWarnings("deprecation")
					EntityType entitySpawn = EntityType.fromName("erebus-crushroom");
					Location spawn = clicker.getLocation();
					World spawnworld = clicker.getWorld();
					spawnworld.spawnEntity(spawn, entitySpawn);
					
					
				}
			}
			
			else if(clicked.getAmount() == 3) {
				if(apiEco.getBalance(playerUUID) > 4000) {
					apiEco.withdraw(playerUUID, 4000);
					clicker.sendMessage("4000 Vous ont été prélevé");
					@SuppressWarnings("deprecation")
					EntityType entitySpawn = EntityType.fromName("erebus-antlionBoss");
					Location spawn = clicker.getLocation();
					World spawnworld = clicker.getWorld();
					spawnworld.spawnEntity(spawn, entitySpawn);
					
					
				}
			}
			
		}
		
	}

	@EventHandler
	public void erebusRedirection(PlayerTeleportEvent event) {
		Location tpworld = event.getTo();
		World tpdim = tpworld.getWorld();
		if(tpdim.getName().endsWith("DIM66")) {
			Location killzone = tpworld;
			killzone.setY(32);
			killzone.setZ(79896);
			killzone.setX(2041);
			event.getPlayer().teleport(tpworld);
			
		}
		
		
	}
	
	
	//DEBUG
	/*@EventHandler
	public void mobSpawn(EntityDeathEvent die) {
		String EntityId = die.getEntity().getType().getName();
		System.out.println(EntityId);
		
	}*/
}
