package dev.me.kuzqi.core.staffsystem.managers;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class ChatManager implements Listener {

	private final Main m;    
	public ChatManager(Main plugin) {
		this.m = plugin;
	}
	
	public void handleBroadcastCommand(CommandSender cs, String[] args) {
	    if (!(cs.hasPermission("staff.broadcast"))) {
	    	cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	    	return;
	    }
    	
	    if (args.length < 1) {
	    	cs.sendMessage("§cUsage: /bc §a<text>");
	    	return;
	    }
    	
	    StringBuilder msg = new StringBuilder();
	    for (String arg : args) {
	    	msg.append(arg).append(" ");
	    }

	    String broadcastFormat = Messages.BORADCAST_FORMAT.from(m.getMessagesConfig());
	    if (broadcastFormat == null) {
	    	cs.sendMessage("§cBroadcast format not found.");
	    	return;
	    }
        
	    String finalMessage = broadcastFormat.replace("$message", m.getTextUtils().format(msg.toString()));
	    m.getServer().broadcastMessage(finalMessage);

	}


	public void toggleChat(CommandSender cs) {
	    if (!(cs.hasPermission("staff.lockchat"))) {
	    	cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	    	return;
	    }
	    	
	    if (m.lockChat.contains(false)) {
		m.lockChat.clear();
	    	m.lockChat.add(true);
	    	m.getServer().broadcastMessage(Messages.LOCKED_CHAT.from(m.getMessagesConfig()).replace("$player", cs.getName()));
		    
	    }else if (m.lockChat.contains(true)) {    
		m.lockChat.clear();
	    	m.lockChat.add(false);
	    	m.getServer().broadcastMessage(Messages.UNLOCKED_CHAT.from(m.getMessagesConfig()).replace("$player", cs.getName()));

	    }
	}

	
	public void clearChat(CommandSender cs) {
	    if (!(cs.hasPermission("staff.clearchat"))) {
	    	cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	    	return;
	    }

	    for (int i = 0; i < 100; i++) {
	    	m.getServer().broadcastMessage(" ");
	    }

	    m.getServer().broadcastMessage(Messages.CLEAR_CHAT.from(m.getMessagesConfig()).replace("$player", cs.getName()));

	}
	
}
