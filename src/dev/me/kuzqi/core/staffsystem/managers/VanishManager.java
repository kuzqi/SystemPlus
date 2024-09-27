package dev.me.kuzqi.core.staffsystem.managers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class VanishManager implements Listener {

	private final Main m;    
	public VanishManager(Main plugin) {
		this.m = plugin;
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
            cs.sendMessage("§cUsage: /vtp §a<player>");
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
