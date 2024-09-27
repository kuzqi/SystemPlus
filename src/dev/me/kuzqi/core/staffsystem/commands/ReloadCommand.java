package dev.me.kuzqi.core.staffsystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class ReloadCommand implements CommandExecutor {

    private final Main m;
    public ReloadCommand(Main main) {
    	this.m = main;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        if (command.equalsIgnoreCase("sr") || command.equalsIgnoreCase("systemreload")) {
        	m.getPluginManager().reloadThePlugin(cs);
        	
        }
        return false;
    }
}