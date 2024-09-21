package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class HealCommand implements CommandExecutor {

	private final Main m;
	public HealCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
		
		if (command.equalsIgnoreCase("heal")) {
			
			if (!cs.hasPermission("staff.heal")) {
				cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
				
			}else {
				if (args.length == 1) {
					if (args[0] != null) {
						Player t = m.getServer().getPlayer(args[0]);
						t.setHealth(20);
						t.setFoodLevel(20);
						t.setFireTicks(0);
						cs.sendMessage("§aThe player §b"+t.getName()+ " §ahas successfuly healed!");
						t.sendMessage("§aYou have been healed by §b"+cs.getName()+ "§a!");
						
					}else {
						cs.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
					}
				}else if (args.length == 0) {
					Player p = (Player) cs;
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setFireTicks(0);
					p.sendMessage("§aHealed.");

				}else {
					cs.sendMessage("§c/heal <player>");
				}
			}
		}
		return false;
	}

}
