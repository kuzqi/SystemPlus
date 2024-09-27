package dev.me.kuzqi.core.staffsystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class JoinListener implements Listener {

    @SuppressWarnings("unused")
	private final Main plugin;
    public JoinListener(Main m) {
        this.plugin = m;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("");
    }

}
