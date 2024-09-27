package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class BroadcastCommand implements CommandExecutor {

    private final Main m;
    public BroadcastCommand(Main m) {
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        if (command.equalsIgnoreCase("broadcast") || command.equalsIgnoreCase("bc")) {
        	m.getChatManager().handleBroadcastCommand(cs, args);
        	
        }
        return false;
    }
}