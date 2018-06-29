package fr.pmk_ozone.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pmk_ozone.MainOzone;

public class Config {

	public static final String ISLAND_PATH = "islands";
	public static final String TEAM_PATH = "teams";
	
	private static Config config = new Config();
	private static MainOzone main;
	
	public static Config getConfig(MainOzone m) {
		main = m;
		return config;
	}

	public void initDataFolder() {
		
		if(!main.getDataFolder().exists()) {
			main.getDataFolder().mkdir();
		}
		
		
		
		try {
			
			System.out.println(main.getDataFolder().getCanonicalPath());
			
			File f = new File(main.getDataFolder().getCanonicalPath() + File.separator + ISLAND_PATH);
			
			if(!f.exists()) {
				f.mkdir();
			}
			
			f = new File(main.getDataFolder().getCanonicalPath() + File.separator + TEAM_PATH);
			
			if(!f.exists()) {
				f.mkdir();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public File initAndGetFile(String fileName) {
		
		File file = new File(main.getDataFolder(),fileName);
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
            main.saveResource(fileName, false);    
		}
		return file;
	}
	
	public YamlConfiguration getConfiguration(File file) {
		
		if(file.exists()) {
			YamlConfiguration conf = new YamlConfiguration();
			
			try {
				conf.load(file);	// chargement du fichier
				return conf;
			} catch (IOException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return null;
		
	}
	
}
