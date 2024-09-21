package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class aTPCommand implements CommandExecutor {

	private final Main m;
	public aTPCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
		
		if (command.equalsIgnoreCase("atp")) {
			if (cs.hasPermission("staff.teleport")) {
				
					if (args.length == 1) {
						if (args[0] != null) {
							Player target = m.getServer().getPlayer(args[0]);
							Player player = (Player) cs;
							player.teleport(target.getLocation());
						}else {
							cs.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
						}
						
					}else {
						if (args.length == 2) {
						if (!args[1].isEmpty() && args[1] != null && !args[0].isEmpty() && args[0] != null) {
							Player target = m.getServer().getPlayer(args[0]);
							Player player = (Player) cs;
							target.teleport(player.getLocation());
						}
						
						}else {
							cs.sendMessage("§c/atp <player1> <player2>");
							cs.sendMessage("§c/atp <player>");
						}
					}	
			}else {
				cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
			}
        }

        return false;
	}

}
