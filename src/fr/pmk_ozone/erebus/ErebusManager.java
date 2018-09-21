package fr.pmk_ozone.erebus;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.erebus.commands.ErebusBossCommand;
import fr.pmk_ozone.erebus.commands.ErebusCommandExecutor;
import fr.pmk_ozone.erebus.commands.ErebusHelpCommand;
import fr.pmk_ozone.erebus.commands.ErebusMobsCommand;

public class ErebusManager {

	public static ErebusManager init() {
		
		return new ErebusManager();
	}
	
	private ErebusCommandExecutor erebCmd;
	
	public ErebusManager() {
		
		erebCmd = new ErebusCommandExecutor();
		
		MainOzone.getInstance().getCommand("erebus").setExecutor(erebCmd);
		
		erebCmd.addSubCommand("help", new ErebusHelpCommand());
		erebCmd.addSubCommand("boss", new ErebusBossCommand());
		erebCmd.addSubCommand("mobs", new ErebusMobsCommand());
		
	}

	
}
