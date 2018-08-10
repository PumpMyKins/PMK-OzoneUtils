package fr.pmk_ozone.island;

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
		islandCmd.addSubCommand("goto", new GoToIslandCmd());
		
		islandCmd.addSubCommand("add", new AddIslandCmd());
		islandCmd.addSubCommand("kick", new KickIslandCmd());
		
		islandCmd.addSubCommand("reset", new ResetIslandCmd());
		
	}

	public IslandCommandExecutor getIslandCmd() {
		return islandCmd;
	}

	public void setIslandCmd(IslandCommandExecutor islandCmd) {
		this.islandCmd = islandCmd;
	}
	
}
