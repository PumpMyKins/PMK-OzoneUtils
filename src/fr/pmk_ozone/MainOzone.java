package fr.pmk_ozone;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.IslandManager;

public class MainOzone extends JavaPlugin {

	private static MainOzone instance;
	private static Config conf;
	
	@Override
	public void onEnable() {
		
		instance = this;
		conf = Config.getConfig(this);
		
		conf.initDataFolder();
		conf.initAndGetFile("config.yml");
		
		IslandManager.init(conf);
		
	}

	public static MainOzone getInstance() {
		return instance;
	}
	
}
