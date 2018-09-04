package fr.pmk_ozone.island.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// téléportation
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			sender.teleport(new Location(Bukkit.getWorld("Void"), island.getSpawnX(), island.getSpawnY(), island.getSpawnZ()));
			return true;
			
		}else {
			// affichage aide
			aide(sender);
			aide1(sender);
			return false;
			
		}
		
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
		p.sendMessage(Island.prefix + "§r§c Sans île, vous ne pouvez pas vous téléporter à celle-ci !");
		
	}
	
	public void aide1(Player p) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is create"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lPour créer votre île !" ).create() ) );
		
		TextComponent msg = new TextComponent("Pour créer votre île, cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		p.spigot().sendMessage(msg); // clique = aide create island
		
	}
	
	

}
