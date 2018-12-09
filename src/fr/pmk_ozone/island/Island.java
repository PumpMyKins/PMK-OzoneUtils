package fr.pmk_ozone.island;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
import fr.pumpmyskycore.IslandFileData;

@SuppressWarnings("deprecation")
public class Island {
	
	public static final String prefix = "�6�l[�r�2Pump�eMy�aIsland�r�6�l]";

	public static Island create(Player p) throws IOException {
		
		//cr�ation du fichier
		
		IslandFileData isfd = getPathFileIsland();
		
		File f = new File(MainOzone.getInstance().getDataFolder(),isfd.getPathName());
		
		f.createNewFile();
		
		// remplissage du fichier
		
		YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
		
		y.createSection("island");
		
		y.set("island.owner",p.getUniqueId().toString());
		
		y.createSection("island.spawn");
		
		y.set("island.x", isfd.getX());
		y.set("island.z", isfd.getZ());
		y.set("island.pathname", isfd.getPathName());
		
		y.set("island.spawn.x", (isfd.getX() * IslandManager.islandsize) + (IslandManager.islandsize/2) + 0.5);
		y.set("island.spawn.y", 60);
		y.set("island.spawn.z", (isfd.getZ()* IslandManager.islandsize) + (IslandManager.islandsize/2) + 0.5);
		
		y.set("island.players", new ArrayList<String>());
		
		//ajouter dans islands
		
		IslandManager.setIsland(p , isfd);
		
		// copy de de la map
		
		Location l = new Location(Bukkit.getWorld("Void"), (isfd.getX() * IslandManager.islandsize) + (IslandManager.islandsize/2) - 8, 50, (isfd.getZ()* IslandManager.islandsize) + (IslandManager.islandsize/2) - 5);
		
		try {
			pasteIsland(l);
		} catch (DataException | MaxChangedBlocksException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		y.save(f);
		
		return new Island(f);
		
	}
	
	public static Island get(File f) {
		return new Island(f);
	}
	
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
			throw new Exception("Plugin cass� donc serveur cass� FIN DU PO2");
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
	
	private double spawnX;
	private double spawnY;
	private double spawnZ;
	
	private int dataX;
	private int dataZ;
	private String pathName;
	
	public int getDataX() {
		return dataX;
	}

	public void setDataX(int dataX) {
		this.dataX = dataX;
	}

	public int getDataZ() {
		return dataZ;
	}

	public void setDataZ(int dataZ) {
		this.dataZ = dataZ;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	private List<String> playerList;
	
	private Island(File f) {
		
		this.file = f;
		
		this.y = MainOzone.getConf().getConfiguration(f);
		
		this.ownerUUID = y.getString("island.owner");
		
		this.dataX = y.getInt("island.x");
		this.dataZ = y.getInt("island.z");
		this.pathName = y.getString("island.pathname");
		
		this.spawnX = y.getDouble("island.spawn.x");
		this.spawnY = y.getDouble("island.spawn.y");
		this.spawnZ = y.getDouble("island.spawn.z");
		
		this.playerList = y.getStringList("island.players");
		
	}
	
	public void save() throws IOException {
		
		y.set("island.owner", this.ownerUUID);
		
		y.set("island.players", this.playerList);
		
		y.save(this.file);
		
	}
	
	public void addPlayer(Player p) {
		
		playerList.add(p.getUniqueId().toString());
		
	}
	
	public void addPlayer(OfflinePlayer p) {
		
		playerList.add(p.getUniqueId().toString());
		
	}

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public double getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(double spawnX) {
		this.spawnX = spawnX;
	}

	public double getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(double spawnY) {
		this.spawnY = spawnY;
	}

	public double getSpawnZ() {
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
	
	public IslandFileData getIslandData() {
		
		return new IslandFileData(dataX, dataZ, pathName);
		
	}

}
