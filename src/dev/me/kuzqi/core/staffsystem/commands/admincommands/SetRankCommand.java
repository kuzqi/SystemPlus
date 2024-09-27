package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class SetRankCommand implements CommandExecutor {

    private final Main m;
    public SetRankCommand(Main main) {
        this.m = main;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	switch (command.toLowerCase()) {
    	
		case "temprank":
			m.getRankManager().handleTempRankCommand(cs, args);
			break;
			
		case "addrank":
			m.getRankManager().handleAddRankCommand(cs, args);
			break;

		case "setrank":
			m.getRankManager().handleSetRankCommand(cs, args);
			break;
			
		}
        return false;
    }
}