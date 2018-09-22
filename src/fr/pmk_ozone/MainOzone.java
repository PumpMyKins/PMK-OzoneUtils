package fr.pmk_ozone;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pmk_ozone.command.MoneyCommand;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.IslandManager;
import fr.pmk_ozone.island.commands.HelpIslandCmd;

public class MainOzone extends JavaPlugin {

	private static MainOzone instance;
	private static Config conf;
	private static IslandManager is;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		// this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		conf = Config.getConfig(this);
		
		conf.initDataFolder();
		conf.initAndGetFile("config.yml");
		conf.initAndGetFile("islands.yml");
		conf.initAndGetFile("islands_invite.yml");
		
		File helpFile = conf.initAndGetFile("island_aide.yml");
		HelpIslandCmd.setMessage(helpFile);
		
		is = IslandManager.init(conf);
		
		try {
			is.setupIslands();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'initialiser les islands !");
			e.printStackTrace();
			getServer().shutdown();
		}
		
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		
		// register money commande
		MainOzone.getInstance().getCommand("money").setExecutor(new MoneyCommand());
		
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
		//IslandManager.purgeIslands();
		
	}
	
	public static Config getConf() {
		
		return conf;
		
	}

	public static MainOzone getInstance() {
		return instance;
	}
	
	public static IslandManager getIslandManager() {
		return is;
	}
	
}
