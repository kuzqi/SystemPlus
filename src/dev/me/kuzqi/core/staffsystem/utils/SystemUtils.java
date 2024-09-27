package dev.me.kuzqi.core.staffsystem.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class SystemUtils {

	private final Main m;    
	public SystemUtils(Main plugin) {
		this.m = plugin;
	}

	
	public void handleServerSender(CommandSender cs, String[] args) {
		if (!(cs.hasPermission("staff.send"))) {
    		cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
			return;
		}
		
		if (args.length != 2) {
			cs.sendMessage("§cUsage: /send §a<user|all> <server>");
			return;
		}
		
		Player target = m.getServer().getPlayer(args[0]);
		if (target == null) {
			cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
			return;
		}
		
		String server = String.valueOf(args[1]);
		
		if (args[0].equalsIgnoreCase("all")) {
			for (Player all : m.getServer().getOnlinePlayers()) {
				m.getPluginMessage().connect(all, server);
			}
			return;
		}
		
		if (!(m.getSettingsConfig().getStringList("servers.allowed-servers").contains(server))) {
			sendServersList(cs);
			return;
		}
		
		m.getPluginMessage().connect(target, server);
	}
	
	
	public void handleServerConnector(CommandSender cs, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
			return;
		}
	        
		if (!cs.hasPermission("staff.server")) {
			cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
			return;
		}
	        
		if (args.length != 1) {
			cs.sendMessage("§cUsage: /server §a<server>");
			return;
		}
		
		String server = String.valueOf(args[0]);

		if (!(m.getSettingsConfig().getStringList("servers.allowed-servers").contains(server))) {
			sendServersList(cs);
			return;
		}
		
		m.getPluginMessage().connect((Player) cs, server);
		
	}

	
	public void sendServersList(CommandSender cs) {
        for (String msg : m.getMessagesConfig().getStringList("invaild-server")) {
        	cs.sendMessage(m.getTextUtils().format(msg.toString()));
        }
	}
}
