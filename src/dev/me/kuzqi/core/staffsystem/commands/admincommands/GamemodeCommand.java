package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class GamemodeCommand implements CommandExecutor {

	private final Main m;
	public GamemodeCommand(Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
	   	switch (command.toLowerCase()) {

	   	case "gmc":
	   		m.getPlayerManager().GmcCommandHandler(cs, args);
	   		break;
				
	   	case "gms":
	   		m.getPlayerManager().GmsCommandHandler(cs, args);
	   		break;
				
	   	}
		return false;
	}
}
