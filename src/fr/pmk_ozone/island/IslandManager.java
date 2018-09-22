package fr.pmk_ozone.island;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.command.MoneyCommand;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.commands.AddIslandCmd;
import fr.pmk_ozone.island.commands.CreateIslandCmd;
import fr.pmk_ozone.island.commands.GoToIslandCmd;
import fr.pmk_ozone.island.commands.HelpIslandCmd;
import fr.pmk_ozone.island.commands.JoinIslandCmd;
import fr.pmk_ozone.island.commands.KickIslandCmd;
import fr.pmk_ozone.island.commands.LeaveIslandCmd;
import fr.pmk_ozone.island.commands.ResetIslandCmd;
import fr.pmk_ozone.island.data.IslandFileData;

public class IslandManager {

	private static Config conf;
	
	public static final int islandsize = 4096;	
	public static final int islandnum = 64;
	
	
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
	private MoneyCommand moneyCmd;


	public IslandManager() {
		
		islandCmd = new IslandCommandExecutor();
		
		islandCmd.addSubCommand("help", new HelpIslandCmd());
		islandCmd.addSubCommand("goto", new GoToIslandCmd());
		
		islandCmd.addSubCommand("create", new CreateIslandCmd());
		
		islandCmd.addSubCommand("add", new AddIslandCmd());
		islandCmd.addSubCommand("kick", new KickIslandCmd());
		islandCmd.addSubCommand("join", new JoinIslandCmd());
		
		islandCmd.addSubCommand("leave", new LeaveIslandCmd());
		islandCmd.addSubCommand("reset", new ResetIslandCmd());
		
		MainOzone.getInstance().getCommand("island").setExecutor(islandCmd);
		MainOzone.getInstance().getCommand("is").setExecutor(islandCmd);
		
	}

	public IslandCommandExecutor getIslandCmd() {
		return islandCmd;
	}
	
	

	public void setupIslands() throws IOException {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < islandnum ; i++) {
			
			File f = new File(MainOzone.getInstance().getDataFolder().getCanonicalPath() + File.separator + "islands" + File.separator + i);
			
			if(f.isDirectory() & f.exists()) {
				continue;
			}
			
			f.mkdirs();
			
		}
		
	}
	
	public Island createIsland(Player p) throws IOException {
		
		return Island.create(p);
		
	}
	
	public Island getIsland(Player p) {
		
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		int x = y.getInt("islands." + uuid + ".x");
		int z = y.getInt("islands." + uuid + ".z");
		
		f = new File(MainOzone.getInstance().getDataFolder(), "islands" + File.separator + x + File.separator + z + ".yml");
		
		return Island.get(f);
		
	}
	
	public boolean playerHasIsland(Player p) {
		
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		int x = y.getInt("islands." + uuid + ".x");
		int z = y.getInt("islands." + uuid + ".z");
		
		if(x != -1 & z != -1 & y.contains("islands." + uuid)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static void unsetIsland(Player p) throws IOException {
		
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		y.set("islands." + uuid + ".x", -1);
		y.set("islands." + uuid + ".z", -1);	
		
		y.save(f);
		
	}

	public static void setIsland(Player p, IslandFileData isfd) throws IOException {
		// TODO Auto-generated method stub
		
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		y.createSection("islands." + uuid);
		
		y.set("islands." + uuid + ".x", isfd.getX());
		y.set("islands." + uuid + ".z", isfd.getZ());		
		
		y.save(f);
		
	}

	public Island getIsland(OfflinePlayer p) {
		
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		int x = y.getInt("islands." + uuid + ".x");
		int z = y.getInt("islands." + uuid + ".z");
		
		f = new File(MainOzone.getInstance().getDataFolder(), "islands" + File.separator + x + File.separator + z + ".yml");
		
		return Island.get(f);
	}

	public static void unsetIsland(OfflinePlayer p) throws IOException {
		// TODO Auto-generated method stub
		String uuid = p.getUniqueId().toString();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),"islands.yml");
		YamlConfiguration y = conf.getConfiguration(f);
		
		y.set("islands." + uuid + ".x", -1);
		y.set("islands." + uuid + ".z", -1);	
		
		y.save(f);
	}
	
	public MoneyCommand getMoneyCmd() {
		return moneyCmd;
	}

	public void setMoneyCmd(MoneyCommand moneyCmd) {
		this.moneyCmd = moneyCmd;
	}
}
