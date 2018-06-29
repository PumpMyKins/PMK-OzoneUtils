package fr.pmk_ozone;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pmk_ozone.config.Config;

public class MainOzone extends JavaPlugin {

	public static MainOzone instance;
	private static Config conf;
	
	@Override
	public void onEnable() {
		
		instance = this;
		conf = Config.getConfig(this);
		
		conf.initDataFolder();
		conf.initAndGetFile("config.yml");
		
		
		
	}
	
}
