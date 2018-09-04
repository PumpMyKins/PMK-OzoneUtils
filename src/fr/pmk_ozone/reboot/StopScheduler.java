package fr.pmk_ozone.reboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StopScheduler extends BukkitRunnable {

	private JavaPlugin plugin;
	
	private String message;

	private boolean dostop ;
	
	public static void start(JavaPlugin plugin, int delay, String msg, boolean dostop) {
		
		System.out.println("Lancement StopScheduler : " + delay + " ticks");

		StopScheduler ss = new StopScheduler(plugin, msg,dostop);
		
		ss.runTaskLater(plugin, delay);
		
	}
	
	private StopScheduler(JavaPlugin plugin, String msg, boolean ds) {
		this.plugin = plugin;
		this.message = msg;
		this.dostop = ds;
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(this.dostop) {
			
			StopManager.stop();
			
		}else {
			
			this.plugin.getServer().broadcastMessage(this.message);
			
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}

}
