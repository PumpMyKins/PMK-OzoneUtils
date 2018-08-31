package fr.pmk_ozone.island;

import java.io.File;
import java.io.IOException;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.commands.AddIslandCmd;
import fr.pmk_ozone.island.commands.GoToIslandCmd;
import fr.pmk_ozone.island.commands.HelpIslandCmd;
import fr.pmk_ozone.island.commands.KickIslandCmd;
import fr.pmk_ozone.island.commands.ResetIslandCmd;

public class IslandManager {

	private static Config conf;
	
	public static IslandManager init(Config c) {
		// TODO Auto-generated method stub
		
		conf = c;
		
		try {
			
			initFolder();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return new IslandManager();
		
	}
	
	private static void initFolder() throws IOException {
		
		File f = new File(MainOzone.getInstance().getDataFolder().getCanonicalPath() + File.separator + "islands");
		
		if(!f.exists()) {
			f.mkdir();
		}
		
	}

	public static Config getConf() {
		return conf;
	}
	
	
	private IslandCommandExecutor islandCmd;
	
	public IslandManager() {
		
		islandCmd = new IslandCommandExecutor();
		
		islandCmd.addSubCommand("help", new HelpIslandCmd());
		islandCmd.addSubCommand("goto", new GoToIslandCmd());
		
		islandCmd.addSubCommand("add", new AddIslandCmd());
		islandCmd.addSubCommand("kick", new KickIslandCmd());
		
		islandCmd.addSubCommand("reset", new ResetIslandCmd());
		
		MainOzone.getInstance().getCommand("island").setExecutor(islandCmd);
		MainOzone.getInstance().getCommand("is").setExecutor(islandCmd);
		
	}

	public IslandCommandExecutor getIslandCmd() {
		return islandCmd;
	}
	
}
