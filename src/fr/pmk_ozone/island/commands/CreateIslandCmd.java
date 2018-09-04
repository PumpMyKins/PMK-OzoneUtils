package fr.pmk_ozone.island.commands;

import java.io.IOException;
import java.util.List;

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

public class CreateIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		// TODO Auto-generated method stub
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			aide(sender);
			aide1(sender);
			return true;
			
		}else {
			// cr�ation de l'ile
			//System.out.println("create island");
			
			try {
				is.createIsland(sender);
				sender.sendMessage(Island.prefix +"�d Ile cr��e avec succ�s !");
				aide1(sender);
				
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous faites parti / poss�dez d�j� une ile.");
		
	}
	
	public void aide1(Player p) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is goto"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "�3�lT�l�portation vers votre �le !" ).create() ) );
		
		TextComponent msg = new TextComponent("Cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		TextComponent msg1 = new TextComponent(" pour vous y t�l�porter !");
		msg1.setColor(ChatColor.AQUA);
		
		msg.addExtra(msg1);
		
		p.spigot().sendMessage(msg); // clique = aide create island
		
	}
	
}
