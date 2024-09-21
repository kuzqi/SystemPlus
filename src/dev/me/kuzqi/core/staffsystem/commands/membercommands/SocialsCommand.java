package dev.me.kuzqi.core.staffsystem.commands.membercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class SocialsCommand implements CommandExecutor {

	private final Main m;
	public SocialsCommand (Main m) {
		this.m = m;
	}
	
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {

        if (!(cs instanceof Player)) {
            cs.sendMessage(m.getMessage("commands-utils.only-player-cmd-execute"));
            return true;
        }
        
    	Player p = (Player) cs;
    	
        if (command.equalsIgnoreCase("discord") || command.equalsIgnoreCase("dis") ) {
        	m.getTextUtils().sendDiscordLink(p);
        }
        
        if (command.equalsIgnoreCase("youtube") || command.equalsIgnoreCase("yt")) {
        	m.getTextUtils().sendYouTubeLink(p);
        }
        
        if (command.equalsIgnoreCase("store")) {
        	m.getTextUtils().sendStoreLink(p);
        }
        
        if (command.equalsIgnoreCase("namemc")) {
        	m.getTextUtils().sendNameMCLink(p);
        }
        
		return true;
    }
}
