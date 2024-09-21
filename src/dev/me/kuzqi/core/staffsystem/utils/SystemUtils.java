package dev.me.kuzqi.core.staffsystem.utils;

import org.bukkit.GameMode;
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
	
	
    public void toggleChat(CommandSender cs) {
    	if (!(cs.hasPermission("staff.lockchat"))) {
    		cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
    		return;
    	}
    	
    	if (m.lockChat.contains(false)) {
    		m.lockChat.clear();
    		m.lockChat.add(true);
            m.getServer().broadcastMessage(Messages.LOCKED_CHAT.from(m.getMessagesConfig()).replace("$player", cs.getName()));
    	}else {
        	if (m.lockChat.contains(true)) {
        		m.lockChat.clear();
        		m.lockChat.add(false);
                m.getServer().broadcastMessage(Messages.UNLOCKED_CHAT.from(m.getMessagesConfig()).replace("$player", cs.getName()));
        	}
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
	
	public void handleServerConnector(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage("§c/server <server>");
			return;
		}
		
		for (String servers : m.getSettingsConfig().getStringList("servers.allowed-servers")) {
			if (!(servers.contains(args.toString()))) {
		
				return;
			}
		}
		
	}
	
    public void toggleFlight(String[] args, CommandSender cs) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
            return;
        }

        Player p = (Player) cs;

        if (!p.hasPermission("staff.fly")) {
            p.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
            return;
        }

        if (args.length != 0) {
        	p.sendMessage("§cUsage: /fly");
        	return;
        }

        if (p.getGameMode() == GameMode.CREATIVE) {
            p.sendMessage(Messages.FLY_CREATIVE_MOD.from(m.getMessagesConfig()));
            return;
        }

        if (m.flight.contains(p.getName())) {
            m.flight.remove(p.getName());
            p.setAllowFlight(false);
            p.sendMessage(Messages.FLY_DISABLED.from(m.getMessagesConfig()));
            
        } else{
            m.flight.add(p.getName());
            p.setAllowFlight(true);
            p.sendMessage(Messages.FLY_ENABLED.from(m.getMessagesConfig()));
        }
    }
 
	public void handleVanish(Player p) {
        String playerName = p.getName();
        if (m.vanish.contains(playerName)) {
            m.vanish.remove(playerName);
            p.sendMessage(Messages.VANISH_DISABLE.from(m.getMessagesConfig()));
            p.setGameMode(GameMode.SURVIVAL);
            p.setAllowFlight(false);
            p.teleport(p.getWorld().getSpawnLocation());
            revealPlayerToOnlinePlayers(p);
        } else {
            m.vanish.add(playerName);
            p.sendMessage(Messages.VANISH_ENABLE.from(m.getMessagesConfig()));
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(true);
            hidePlayerFromOnlinePlayers(p);
        }
    }

    public void handleVList(Player cs) {
        cs.sendMessage(Messages.VANISH_HEADER_LIST.from(m.getMessagesConfig()));
        if (m.vanish.isEmpty()) {
            cs.sendMessage(Messages.VANISH_EMPTY_LIST.from(m.getMessagesConfig()));
        } else {
            cs.sendMessage("§f" + String.join(", ", m.vanish));
        }
    }

    public void handleTeleportToVanish(Player cs, String[] args) {
        if (!(cs.hasPermission("staff.vtp"))) {
            cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
            return;
        }

        if (args.length != 1) {
            cs.sendMessage("§c/vtp <player>");
            return;
        }

        if (!m.vanish.contains(cs.getName())) {
            cs.sendMessage(Messages.VANISH_TP_RULE.from(m.getMessagesConfig()));
            return;
        }

        Player target = m.getServer().getPlayer(args[0]);
        if (target == null) {
            cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
            return;
        }

        cs.teleport(target.getLocation());
    }

    public void hidePlayerFromOnlinePlayers(Player p) {
        for (Player online : m.getServer().getOnlinePlayers()) {
            online.hidePlayer(p);
        }
    }

    public void revealPlayerToOnlinePlayers(Player p) {
        for (Player online : m.getServer().getOnlinePlayers()) {
            online.showPlayer(p);
        }
    }
}
