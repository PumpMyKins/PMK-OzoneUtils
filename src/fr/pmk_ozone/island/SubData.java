package fr.pmk_ozone.island;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.commands.ISubCommand;

public class SubData {

	private String permissionNode = "none";
	private String subCommand;
	private ISubCommand iSubCommand;
	
	public SubData(String s,String p, ISubCommand i) {
		
		this(s,i);
		this.permissionNode = p;
		
	}
	
	public SubData(String s, ISubCommand i) {
		// TODO Auto-generated constructor stub
		this.subCommand = s;
		this.iSubCommand = i;
	}
	
	public boolean execute(Player sender , Command cmd , List<String> args) {
		
		return this.iSubCommand.onSubCommand(sender, cmd, args);
		
	}

	public String getPermissionNode() {
		return permissionNode;
	}
	public void setPermissionNode(String permissionNode) {
		this.permissionNode = permissionNode;
	}
	public String getSubCommand() {
		return subCommand;
	}
	public void setSubCommand(String subCommand) {
		this.subCommand = subCommand;
	}
	public ISubCommand getSubCommandExecutor() {
		return iSubCommand;
	}
	public void setSubCommandExecutor(ISubCommand iSubCommand) {
		this.iSubCommand = iSubCommand;
	}
	
}
