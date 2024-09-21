package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;

public class SendServerCommand implements CommandExecutor {

	private final Main m;
	public SendServerCommand (Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
	    if (command.equalsIgnoreCase("send")) {
	        if (!cs.hasPermission("staff.send")) {
	            cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
	        } else {
	            if (args.length == 2) {
	            	if (args[0].equalsIgnoreCase("all")) {
	            		
		                if (
			                	args[1].equalsIgnoreCase("pvp") || args[1].equalsIgnoreCase("skypvp")
			                 || args[1].equalsIgnoreCase("redpvp") || args[1].equalsIgnoreCase("duels")
			    	         || args[1].equalsIgnoreCase("lobby") || args[1].equalsIgnoreCase("creative")
			    	         || args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("pvpop")
			    	         || args[1].equalsIgnoreCase("kbffa")) {
			    	        		 
			                	for (Player everyone : m.getServer().getOnlinePlayers()) {
			                		m.getPluginMessage().connect(everyone, String.valueOf(args[1]));
			                	}
			                	
			                } else {
			                	cs.sendMessage("§cInvalid server name!");
			                	cs.sendMessage("§cServers: §6pvp, skypvp, redpvp, duels, creative, survival, pvpop, kbffa, lobby");
			                }
		                
	            	}else {
	            	
	            	Player target = m.getServer().getPlayer(args[0]);
	                if (target != null) {
	                	
		                if (
		                	args[1].equalsIgnoreCase("pvp") || args[1].equalsIgnoreCase("skypvp")
		                 || args[1].equalsIgnoreCase("redpvp") || args[1].equalsIgnoreCase("duels")
		    	         || args[1].equalsIgnoreCase("lobby") || args[1].equalsIgnoreCase("creative")
		    	         || args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("pvpop")
		    	         || args[1].equalsIgnoreCase("kbffa")) {
		    	        		 
		                	
		                } else {
		                	cs.sendMessage("§cInvalid server name!");
		                	cs.sendMessage("§cServers: §6pvp, skypvp, redpvp, duels, creative, survival, pvpop, kbffa, lobby");
		                }

	                    m.getPluginMessage().connect(target, String.valueOf(args[0]));
	                    
	                }else {
	                	cs.sendMessage("§cThe player isn't online in this server.");
	                }
	                
	            	}
	            	
	            } else {
	                cs.sendMessage("§c/send <player|all> <server>");
	            }
	        }
	    }
	    return false;
	}


}
