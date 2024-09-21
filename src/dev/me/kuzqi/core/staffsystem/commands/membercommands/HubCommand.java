package dev.me.kuzqi.core.staffsystem.commands.membercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class HubCommand implements CommandExecutor {

	private final Main m;
	public HubCommand(Main m) {
		this.m = m;
	}
	
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	
        if (!(cs instanceof Player)) {
            cs.sendMessage(m.getMessage("commands-utils.only-player-cmd-execute"));
            return true;
        }
        
    	if (command.equalsIgnoreCase("hub")) {
    		Player p = (Player)cs;
    		m.getPluginMessage().connect(p, m.getSettingsConfig().getString("servers.lobby-server"));
    		
        }
		return true;
    }
}