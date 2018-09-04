package fr.pmk_ozone.reboot;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.pmk_ozone.MainOzone;

public class StopManager {

	public static boolean stop = false;
	
	private static List<String> getRebootList(){
		
		return null;
		
	}
	
	private static void startScheduler(List<String> timeList) {
		
			
		
	}
	
	public static void init() {
		
		List<String> timeList = getRebootList();
		
		if(timeList.isEmpty())
			return;	
		
		startScheduler(timeList);
		
	}

	public static void stop() {
		// TODO Auto-generated method stub
		
		stop = true;
		
		for (Player p : MainOzone.getInstance().getServer().getOnlinePlayers()) {
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("lobby");
			
			p.sendPluginMessage(MainOzone.getInstance(), "BungeeCord", out.toByteArray());
			
		}
		
	}
	
}
