package fr.pmk_ozone;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.IslandManager;
import fr.pmk_ozone.island.commands.HelpIslandCmd;

public class MainOzone extends JavaPlugin {

	private static MainOzone instance;
	private static Config conf;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		conf = Config.getConfig(this);
		
		conf.initDataFolder();
		conf.initAndGetFile("config.yml");
		File helpFile = conf.initAndGetFile("island_aide.yml");
		
		HelpIslandCmd.setMessage(helpFile);
		
		IslandManager is = IslandManager.init(conf);
		
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
		//IslandManager.purgeIslands();
		
	}

	public static MainOzone getInstance() {
		return instance;
	}
	
}
