package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class SetRankCommand implements CommandExecutor {

    private final Main m;
    public SetRankCommand (Main m) {
        this.m = m;
    }
    LuckPerms lp = LuckPermsProvider.get();
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
    	
    	
        if (command.equalsIgnoreCase("temprank")) {
            if (cs.hasPermission("staff.rank")) {
                if (args.length == 3) {
                	if (args[1].equalsIgnoreCase("owner")) {
                		cs.sendMessage("§cYou are not allowed to do that!");
                		return true;
                	}
                	
                	Player target = m.getServer().getPlayer(args[0]);
                	String rank = String.valueOf(args[1]);
                	String duration = String.valueOf(args[2]);
                	
                	if (target.getName().equalsIgnoreCase("kuzqi") || target.getName().equalsIgnoreCase("Gotcha_1") || target.getName().equalsIgnoreCase("_Mr3b")) {
                		cs.sendMessage("§cYou are not allowed to do that!");
                		return true;
                	}
                	
                	if (rank.equalsIgnoreCase("member")) {
                        m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+target.getName()+" parent addtemp default "+duration);
                        return true;
                	}
                	
                    m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+target.getName()+" parent addtemp "+rank+" "+duration);
                    
                } else {
                    cs.sendMessage("§c/temprank <player> <group> <duration>");
                }
            } else {
            	cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
            }
        }
        
        
        
        if (command.equalsIgnoreCase("setrank")) {
            if (cs.hasPermission("staff.rank")) {
                if (args.length == 2) {
                    if (args.length >= 2) {
                    	
                    	if (args[1].equalsIgnoreCase("owner")) {
                    		cs.sendMessage("§cYou are not allowed to do that!");
                    		return true;
                    	}
                    	
                    	String targetName = m.getServer().getPlayer(args[0]).getName();
                    	String rank = String.valueOf(args[1]);
                    	
                    	if (targetName.equalsIgnoreCase("kuzqi") || targetName.equalsIgnoreCase("Gotcha_1") || targetName.equalsIgnoreCase("_Mr3b")) {
                    		cs.sendMessage("§cYou are not allowed to do that!");
                    		return true;
                    	}
                    	
                    	if (rank.equalsIgnoreCase("member")) {
                            m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+targetName+" parent set default");
                            return true;
                    	}            	
            	
                    m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+targetName+" parent set "+ rank);
                    
                    }
                } else {
                    cs.sendMessage("§c/setrank <player> <group>");
                    
                }
            } else {
            	cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
            }
        }
        
        if (command.equalsIgnoreCase("addrank")) {
            if (cs.hasPermission("staff.rank")) {
                if (args.length == 2) {
                    if (args.length >= 2) {
                    	if (args[1].equalsIgnoreCase("owner")) {
                    		cs.sendMessage("§cYou are not allowed to do that!");
                    		return true;
                    	}
                    	
                    	String targetName = m.getServer().getPlayer(args[0]).getName();
                    	String rank = String.valueOf(args[1]);
                    	
                    	if (targetName.equalsIgnoreCase("kuzqi") || targetName.equalsIgnoreCase("Gotcha_1") || targetName.equalsIgnoreCase("_Mr3b")) {
                    		cs.sendMessage("§cYou are not allowed to do that!");
                    		return true;
                    	}
                    	
                    	if (rank.equalsIgnoreCase("member")) {
                            m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+targetName+" parent add default");
                            return true;
                    	}            	
            	
                    m.getServer().dispatchCommand(m.getServer().getConsoleSender(), "lp user "+targetName+" parent add "+ rank);
                    
                    }
                } else {
                    cs.sendMessage("§c/addrank <player> <group>");
                    
                }
            } else {
            	cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
            }
        }
        
        return false;
    }
}