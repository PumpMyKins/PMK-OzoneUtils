package fr.pmk_ozone.island.commands;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class KickIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			List<String> l = island.getPlayerList();
			
			if(l.size() == 0) {
				aide1(sender);
				return true;
			}
			
			if(args.isEmpty()) {
				
				aide2(sender,l);
				return true;
				
			}else {
				
				String u = args.get(0);
				
				if(!l.contains(u)) {
					
					aide3(sender);
					return false;
					
				}else {
					
							
					for (Iterator<String> i = l.iterator(); i.hasNext();) {
						
						if(i.next().equals(u)) {
							
							try {
								
								OfflinePlayer target = MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(u));
								
								i.remove();
								island.save();
								
								IslandManager.unsetIsland(target);
								
								sender.sendMessage(Island.prefix + "§r§d Vous avez bien exclu " + target.getName() + " de votre île !");
								
								if(target.isOnline()) {
									Bukkit.getPlayer(target.getUniqueId()).teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
								}
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
														
							
							
						}
						
					}					
									
					return true;
					
				}
				
			}
				
		}else {
			// affichage aide
			aide(sender);
			new GoToIslandCmd().aide1(sender);
			return true;
		}
		
	}

	private void aide3(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Exlusion du joueur non valide !");
	}

	private void aide2(Player p, List<String> playerList) {
		
		p.sendMessage(Island.prefix + "§r§d Liste des membres de l'île :");
		
		for (String string : playerList) {
			
			OfflinePlayer t = MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(string));
			
			TextComponent msg = new TextComponent("");
			
			TextComponent kick = new TextComponent("§c§l[✖]");
			kick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is kick " + string));
			kick.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lExclure le joueur" ).create() ) );
			
			TextComponent msg1 = new TextComponent("    §r§b▌ " + t.getName());
			msg1.setBold(true);
			msg1.setColor(ChatColor.AQUA);
			
			msg.addExtra(kick);
			msg.addExtra(msg1);
			
			p.spigot().sendMessage(msg);
		
		}
		
	}

	private void aide1(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Il n'y a pas de joueur à kick !");
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous n'avez pas d'ile !");
	}

}
