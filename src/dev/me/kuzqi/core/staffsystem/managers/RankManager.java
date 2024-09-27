package dev.me.kuzqi.core.staffsystem.managers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class RankManager implements Listener {

	private final Main m;    
	public RankManager(Main plugin) {
		this.m = plugin;
	}
	
	

	public void handleAddRankCommand(CommandSender cs, String[] args) {
	    if (!cs.hasPermission("system.addrank")) {
	        cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (args.length != 2) {
	        cs.sendMessage("§cUsage: /addrank §a<player> <group>");
	        return;
	    }


	    Player Target = m.getServer().getPlayer(args[0]);
	    if (Target == null) {
	        cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
	    	return;
	    }
	    
	    String Rank = String.valueOf(args[1]);
	    
	    if (m.getSettingsConfig().getStringList("ranks.exempt-players").contains(Target.getName())) {
	        cs.sendMessage(Messages.NOT_ALLOWED.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (!m.getSettingsConfig().getStringList("ranks.allowed").contains(Rank)) {
	        cs.sendMessage(Messages.UNKNOWN_RANK.from(m.getMessagesConfig()).replace("$rank", Rank));
	        return;
	    }
	    
	    cs.sendMessage("§aYou have added a rank to §f" +Target.getName());
	    cs.sendMessage("§aRank: §e" +Rank+ " §aDuration: §6Forever");
	    
	    m.getServer().dispatchCommand(m.getServer().getConsoleSender(),
	        "lp user " + Target.getName() + " parent add " + Rank);
	}
	
	
	
	public void handleTempRankCommand(CommandSender cs, String[] args) {
	    if (!cs.hasPermission("system.temprank")) {
	        cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (args.length != 3) {
	        cs.sendMessage("§cUsage: /temprank §a<player> <group> <duration>");
	        return;
	    }


	    Player Target = m.getServer().getPlayer(args[0]);
	    if (Target == null) {
	        cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
	    	return;
	    }
	    
	    String Rank = String.valueOf(args[1]);
	    String Duration = String.valueOf(args[2]);
	    
	    if (m.getSettingsConfig().getStringList("ranks.exempt-players").contains(Target.getName())) {
	        cs.sendMessage(Messages.NOT_ALLOWED.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (!m.getSettingsConfig().getStringList("ranks.allowed").contains(Rank)) {
	        cs.sendMessage(Messages.UNKNOWN_RANK.from(m.getMessagesConfig()).replace("$rank", Rank));
	        return;
	    }
	    
	    cs.sendMessage("§aYou have added a temporary rank to §f" +Target.getName());
	    cs.sendMessage("§aRank: §e" +Rank+ " §aDuration: §6"+ Duration);
	    
	    m.getServer().dispatchCommand(m.getServer().getConsoleSender(),
	     "lp user " + Target.getName() + " parent addtemp " + Rank + " " + Duration);
	
	}
	
	

	public void handleSetRankCommand(CommandSender cs, String[] args) {
	    if (!cs.hasPermission("system.setrank")) {
	        cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (args.length != 2) {
	        cs.sendMessage("§cUsage: /setrank §a<player> <group>");
	        return;
	    }


	    Player Target = m.getServer().getPlayer(args[0]);
	    if (Target == null) {
	        cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
	    	return;
	    }
	    
	    String Rank = String.valueOf(args[1]);
	    
	    if (m.getSettingsConfig().getStringList("ranks.exempt-players").contains(Target.getName())) {
	        cs.sendMessage(Messages.NOT_ALLOWED.from(m.getMessagesConfig()));
	        return;
	    }
	    
	    if (!m.getSettingsConfig().getStringList("ranks.allowed").contains(Rank)) {
	        cs.sendMessage(Messages.UNKNOWN_RANK.from(m.getMessagesConfig()).replace("$rank", Rank));
	        return;
	    }
	    
	    cs.sendMessage("§aYou have updated " +Target.getName()+ "'s rank and cleared previous ranks");
	    cs.sendMessage("§aRank: §e" +Rank+ " §aDuration: §6Forever");

	    m.getServer().dispatchCommand(m.getServer().getConsoleSender(),
	        "lp user " + Target.getName() + " parent set " + Rank);
	}
	
}
