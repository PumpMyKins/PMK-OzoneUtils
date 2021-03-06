package fr.pmk_ozone;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pmk_ozone.command.MoneyCommand;
import fr.pmk_ozone.command.StartingStuffCommand;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.erebus.ErebusManager;
import fr.pmk_ozone.erebus.commands.ErebusBossCommand;
import fr.pmk_ozone.island.IslandManager;
import fr.pumpmyskybukkit.commands.HelpIslandCmd;

public class MainOzone extends JavaPlugin {

	private static MainOzone instance;
	private static Config conf;
	private static IslandManager is;
	private static ErebusManager erebus;
	
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
		erebus = ErebusManager.init();
		
		try {
			is.setupIslands();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'initialiser les islands !");
			e.printStackTrace();
			getServer().shutdown();
		}
		
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		getServer().getPluginManager().registerEvents(new ErebusBossCommand(), this);
		
		//starting stuff récupération commande
		getCommand("start").setExecutor(new StartingStuffCommand());
    
		// register money commande
		getCommand("money").setExecutor(new MoneyCommand());
		
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
	public static ErebusManager getErebusManager() {
		return erebus;
	}
	
}
