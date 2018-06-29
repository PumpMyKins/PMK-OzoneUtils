package fr.pmk_ozone.island;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.commands.HelpIslandCmd;

public class IslandManager {

	private static Config conf;
	
	public static IslandManager init(Config c) {
		// TODO Auto-generated method stub
		
		conf = c;
		
		return new IslandManager();
		
	}

	public static Config getConf() {
		return conf;
	}
	
	
	private IslandCommandExecutor islandCmd;
	
	public IslandManager() {
		
		islandCmd = new IslandCommandExecutor();
		
		MainOzone.getInstance().getCommand("island").setExecutor(islandCmd);
		MainOzone.getInstance().getCommand("is").setExecutor(islandCmd);
		
		islandCmd.addSubCommand("help", new HelpIslandCmd());
		islandCmd.addSubCommand("goto", new HelpIslandCmd());
		
	}

	public IslandCommandExecutor getIslandCmd() {
		return islandCmd;
	}

	public void setIslandCmd(IslandCommandExecutor islandCmd) {
		this.islandCmd = islandCmd;
	}
	
}
