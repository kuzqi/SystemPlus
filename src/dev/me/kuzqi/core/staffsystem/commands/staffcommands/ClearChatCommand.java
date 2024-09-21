package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class ClearChatCommand implements CommandExecutor {

    private final Main m;
    public ClearChatCommand(Main m) {
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("cc") || label.equalsIgnoreCase("clearchat")) {
        	
        	m.getSystemUtils().clearChat(cs);
        }
        
        return true;
    }
}
