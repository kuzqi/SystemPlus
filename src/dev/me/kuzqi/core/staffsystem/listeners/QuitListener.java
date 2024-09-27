package dev.me.kuzqi.core.staffsystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class QuitListener implements Listener {

	private final Main plugin;
	
	public QuitListener (Main m) {
		this.plugin = m;
	}
	
	@EventHandler
    public void onQuit(PlayerQuitEvent e) {
    	e.setQuitMessage("");
    	String pName = e.getPlayer().getName();
    	
		if (plugin.vanish.contains(pName)) {
			plugin.vanish.remove(pName);
		}
		
		if (plugin.flight.contains(pName)) {
			plugin.flight.remove(pName);
		}
    }
}
