package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class GamemodeCommand implements CommandExecutor {

	private final Main m;
	public GamemodeCommand (Main m) {
		this.m = m;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		 
		if (label.equalsIgnoreCase("gmc")) {
			if (!(sender instanceof ConsoleCommandSender)) {
				Player p = (Player) sender;
				if (!p.hasPermission("staff.gamemode")) {
					p.sendMessage(m.c(m.getConfig().getString("messages.noperm")));

			}else {
				if (args.length == 1) {
					if (m.getServer().getOnlinePlayers().contains(m.getServer().getPlayer(args[0]))) {
						m.getServer().getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						sender.sendMessage(m.c(m.getConfig().getString("messages.survival-gamemode").replace("$player", m.getServer().getPlayer(args[0]).getName())));
			
					}else {
						p.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
					}
					
				}else {
					if (args.length == 0) {
						p.setGameMode(GameMode.CREATIVE);
						
					}else {
						p.sendMessage("§cInvaild command usage.");
					}
				}
					
			}
			
		}else {
			if (args.length == 1) {
				if (m.getServer().getOnlinePlayers().contains(m.getServer().getPlayer(args[0]))) {
					m.getServer().getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
					sender.sendMessage("§bSet "+m.getServer().getPlayer(args[0]).getName()+"'s gamemode to §aCreative");
		
				}else {
					sender.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
				}
			}else {
				sender.sendMessage("§c/gmc <username>");
			}
		}
			
		}
		
		
		if (label.equalsIgnoreCase("gms")) {
			if (!(sender instanceof ConsoleCommandSender)) {
				Player p = (Player) sender;
				if (!p.hasPermission("staff.gamemode")) {
					p.sendMessage(m.c(m.getConfig().getString("messages.noperm")));

			}else {
				if (args.length == 1) {
					if (m.getServer().getOnlinePlayers().contains(m.getServer().getPlayer(args[0]))) {
						m.getServer().getPlayer(args[0]).setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(m.c(m.getConfig().getString("messages.survival-gamemode").replace("$player", m.getServer().getPlayer(args[0]).getName())));
			
					}else {
						p.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
					}
					
				}else {
					if (args.length == 0) {
						p.setGameMode(GameMode.SURVIVAL);

					}else {
						p.sendMessage("§cInvaild command usage.");
					}
				}
					
			}
			
		}else {
			if (args.length == 1) {
				if (m.getServer().getOnlinePlayers().contains(m.getServer().getPlayer(args[0]))) {
					m.getServer().getPlayer(args[0]).setGameMode(GameMode.SURVIVAL);
					sender.sendMessage("§bSet "+m.getServer().getPlayer(args[0]).getName()+"'s gamemode to §aSurvival");
		
				}else {
					sender.sendMessage(m.c(m.getConfig().getString("messages.unknown-player")));
				}
			}else {
				sender.sendMessage("§c/gms <username>");
			}
		}
			
		}
		
		return true;
	}
}
