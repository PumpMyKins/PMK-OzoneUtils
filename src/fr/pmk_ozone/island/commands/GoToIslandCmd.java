package fr.pmk_ozone.island.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.BukkitWorld;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// téléportation	
			String uuid = sender.getUniqueId().toString();
			
			File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
			YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
			
			Island island = Island.get(sender);
			
			sender.teleport(new Location(Bukkit.getWorld("world"), island.getSpawnX(), island.getSpawnY(), island.getSpawnZ()));
			
		}else {
			// affichage aide
			aide(sender);
			
		}
		
		return false;
		
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
		p.sendMessage("Sans ile, vous ne pouvez pas vous téléporter à celle-ci !");
		p.sendMessage("Pour créer votre ile, cliquez ICI"); // clique = aide create island
		
	}
	
	

}
