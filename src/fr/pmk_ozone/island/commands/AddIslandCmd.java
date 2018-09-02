package fr.pmk_ozone.island.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.pmk_ozone.MainOzone;
import fr.pmk_ozone.island.Island;
import fr.pmk_ozone.island.IslandManager;

public class AddIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		// TODO Auto-generated method stub
		
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			if(args.isEmpty()) {
				aide(sender);
				return false;
			}else {
				
				Player p = MainOzone.getInstance().getServer().getPlayer(args.get(0));
				
				if(p != null) {
					// joueur trouvé
					
					if(p.getName().equals(sender.getName())) {
						// check invite soit meme
						aide1(sender);
						return false;
						
					}else {
						// ajout dans la liste d'invitation
						File f = new File(MainOzone.getInstance().getDataFolder(),"islands_invite.yml");
						YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
						
						if(!y.contains("invite." + p.getUniqueId().toString())) {
							y.set("invite." + p.getUniqueId().toString(), new ArrayList<String>());
						}
						
						List<String> l = y.getStringList("invite." + p.getUniqueId().toString());
						
						Island i = is.getIsland(sender);
						
						if(l.contains(i.getOwnerUUID())) {
							//déjà ajouté
							aide2(sender);
							return false;
						}else {
							// pas encore ajouté
							l.add(i.getOwnerUUID());
							y.set("invite." + p.getUniqueId().toString(), l);
							
							try {
								// réussite donc ajout
								y.save(f);
								sender.sendMessage("§d" + p.getDisplayName() + " a bien été invité sur votre île !");
								p.sendMessage(Island.prefix + "§d" + sender.getDisplayName() + " vous a invité dans sur son île !");
								return true;
							} catch (IOException e) {
								// erreur de save
								e.printStackTrace();
								sender.sendMessage("§cException save invite....");
								return false;
							}
							
						}							
						
					}
					
				}else {
					// joueur non valide
					aide(sender);
					return false;
				}
				
			}
				
		}else {
			// affichage aide
			aide3(sender);
			new GoToIslandCmd().aide1(sender);
			return true;
		}
		
	}
	
	private void aide3(Player p) {
		
		p.sendMessage(Island.prefix + "§r§c Sans ile, vous ne pouvez pas ajouter un joueur à celle-ci !");
		
	}

	private void aide2(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous avez déjà invité cette personne à rejoindre votre ile !");
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous devez donné un nom de joueur non valide et connecté !");
	}
	
	public void aide1(Player p) {
		
		p.sendMessage(Island.prefix + "§r§c Vous ne pouvez pas vous ajouter vous même !");
		
	}
	
}
