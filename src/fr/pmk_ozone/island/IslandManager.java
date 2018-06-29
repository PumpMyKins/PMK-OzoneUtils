package fr.pmk_ozone.island;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.config.Config;

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
		
	}

	public IslandCommandExecutor getIslandCmd() {
		return islandCmd;
	}

	public void setIslandCmd(IslandCommandExecutor islandCmd) {
		this.islandCmd = islandCmd;
	}
	
}
