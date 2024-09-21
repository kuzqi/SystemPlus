package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class FlightCommand implements CommandExecutor {

    private final Main m;
    public FlightCommand(Main m) {
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	if (command.equalsIgnoreCase("fly")) {
    		
    		m.getSystemUtils().toggleFlight(args, cs);
    	}
    	
		return true;
    }

}
