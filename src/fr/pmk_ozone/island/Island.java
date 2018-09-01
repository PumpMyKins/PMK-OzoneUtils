package fr.pmk_ozone.island;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.config.Config;
import fr.pmk_ozone.island.data.IslandFileData;

public class Island {

	public static Island create(Player p) throws IOException {
		
		//création du fichier
		
		IslandFileData isfd = getPathFileIsland();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),isfd.getPathName());
		
		f.createNewFile();
		
		// remplissage du fichier
		
		YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
		
		y.createSection("island");
		
		y.set("island.owner",p.getUniqueId().toString());
		
		y.createSection("island.spawn");
		
		y.set("island.spawn.x", isfd.getX() + (IslandManager.islandsize/2));
		y.set("island.spawn.y", 60);
		y.set("island.spawn.z", isfd.getZ() + (IslandManager.islandsize/2));
		
		y.set("island.players", new ArrayList<String>());
		
		//ajouter dans islands
		
		IslandManager.setIsland(p , isfd);
		
		// copy de de la map
		
		Location l = new Location(Bukkit.getWorld("world"), isfd.getX() + (IslandManager.islandsize/2) - 8, 50, isfd.getZ() + (IslandManager.islandsize/2) - 5);
		
		try {
			pasteIsland(l);
		} catch (@SuppressWarnings("deprecation") DataException | MaxChangedBlocksException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		y.save(f);
		
		return new Island(f);
		
	}
	
	@SuppressWarnings("deprecation")
	private static void pasteIsland(Location loc) throws DataException, IOException, MaxChangedBlocksException {
		
		File file = new File(MainOzone.getInstance().getDataFolder(),"ile.schematic");
		
		Vector v = new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		
		EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), WorldEdit.getInstance().getConfiguration().maxChangeLimit);
		
		SchematicFormat format = SchematicFormat.getFormat(file);
		CuboidClipboard cc = null;
		
		cc = format.load(file);
		
		cc.paste(es, v, false);
		
	}
	
	private static IslandFileData getPathFileIsland() {
		
		for (int x = 0; x < IslandManager.islandnum ; x++) {
			
			for (int z = 0; z < IslandManager.islandnum ; z++) {
				
				File f = new File(MainOzone.getInstance().getDataFolder(), "islands" + File.separator + x + File.separator + z + ".yml");
				
				if(f.exists())
					continue;
				
				return new IslandFileData(x , z , "islands" + File.separator + x + File.separator + z + ".yml");
				
			}
			
		}
		
		try {
			throw new Exception("Plugin cassé donc serveur cassé FIN DU PO2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private File file;
	private YamlConfiguration y;
	
	private String ownerUUID;
	
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	
	private List<String> playerList;
	
	private Island(File f) {
		
		this.file = f;
		
		this.y = MainOzone.getConf().getConfiguration(f);
		
		this.ownerUUID = y.getString("island.owner");
		
		this.spawnX = y.getInt("island.spawn.x");
		this.spawnY = y.getInt("island.spawn.y");
		this.spawnZ = y.getInt("island.spawn.z");
		
		this.playerList = y.getStringList("island.players");
		
	}
	
	public void save() throws IOException {
		
		y.set("island.owner", this.ownerUUID);
		
		y.set("island.spawn.x", this.spawnX);
		y.set("island.spawn.y", this.spawnY);
		y.set("island.spawn.z", this.spawnZ);
		
		y.set("island.players", this.playerList);
		
		y.save(this.file);
		
	}

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public int getSpawnZ() {
		return spawnZ;
	}

	public void setSpawnZ(int spawnZ) {
		this.spawnZ = spawnZ;
	}

	public List<String> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<String> playerList) {
		this.playerList = playerList;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public YamlConfiguration getConf() {
		return y;
	}

	public void setConf(YamlConfiguration conf) {
		this.y = conf;
	}

}
