package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class RenameCommand implements CommandExecutor {

	private final Main m;
	public RenameCommand(Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
	    if (command.equalsIgnoreCase("rename")) {
	    	m.getPlayerManager().handleRenameCommand(cs, args);
	    	
	    }
	    return false;
	}
}