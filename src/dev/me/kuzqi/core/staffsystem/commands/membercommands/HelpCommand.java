package dev.me.kuzqi.core.staffsystem.commands.membercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

public class HelpCommand implements CommandExecutor {

	private final Main m;
	public HelpCommand (Main m) {
		this.m = m;
	}
	
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	
    	if (command.equalsIgnoreCase("help")) {
            for (String msg : m.getMessagesConfig().getStringList("messages.help")) {
            	cs.sendMessage(m.getTextUtils().format(msg.toString()));
            }
        }
    	
		return true;
    }
}