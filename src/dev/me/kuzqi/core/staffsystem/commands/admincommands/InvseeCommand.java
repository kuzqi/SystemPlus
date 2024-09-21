package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class InvseeCommand implements CommandExecutor {

	private final Main m;
	public InvseeCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
		
		if (command.equalsIgnoreCase("invsee")) {
			if (cs instanceof Player) {
			if (!cs.hasPermission("staff.invsee")) {
				cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
				
			}else {
				if (args.length == 1) {
					if (args[0] != null) {
						if (!args[0].equals(cs.getName())) {
							Player t = m.getServer().getPlayer(args[0]);
							Player p = (Player) cs;
							p.openInventory(t.getInventory());
							
						}else {
							cs.sendMessage("§cYou can't use this command on yourself.");
						}
					}else {
						cs.sendMessage("§cInvaild player name or player is not online.");
					}
				}else {
					cs.sendMessage("§c/invsee <player>");
				}
			}
			}
		}
		return false;
	}

}
