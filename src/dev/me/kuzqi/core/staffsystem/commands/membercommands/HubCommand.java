package dev.me.kuzqi.core.staffsystem.commands.membercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class HubCommand implements CommandExecutor {

	private final Main m;
	public HubCommand(Main m) {
		this.m = m;
	}
	
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	if (command.equalsIgnoreCase("hub")) {
    		m.getPluginMessage().sendToLobby(cs);
    		
        }
		return false;
    }
}