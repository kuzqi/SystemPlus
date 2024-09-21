package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class ServerCommand implements CommandExecutor {

	private final Main m;
	public ServerCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(m.getMessage("commands-utils.only-player-cmd-execute"));
            return true;
        }
        
        Player p = (Player) cs;

        if (!p.hasPermission("staff.server")) {
        	p.sendMessage(m.getMessage("no-permission"));
            return true;
        }
        
		if (command.equalsIgnoreCase("server")) {
			m.getSystemUtils().handleServerConnector(p, args);
		}
		
	    return true;
	}


}
