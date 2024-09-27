package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class LockChatCommand implements CommandExecutor {

	private final Main m;
	public LockChatCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
		if (command.equalsIgnoreCase("lc") || command.equalsIgnoreCase("lockchat")) {
			m.getChatManager().toggleChat(cs);
			
		}
        return false;
	}
}
