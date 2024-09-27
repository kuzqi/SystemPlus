package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class HealCommand implements CommandExecutor {

	private final Main m;
	public HealCommand(Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
		if (command.equalsIgnoreCase("heal")) {
			m.getPlayerManager().handleHealCommand(cs, args);
			
		}
		return false;
	}
}
