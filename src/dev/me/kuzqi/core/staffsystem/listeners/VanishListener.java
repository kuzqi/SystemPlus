package dev.me.kuzqi.core.staffsystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class VanishListener implements Listener {

	private final Main m;
	public VanishListener(Main plugin) {
		this.m = plugin;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteractAtEtntity(PlayerInteractAtEntityEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void qzzwd(EntityDamageEvent e) {
		if (m.vanish.contains(e.getEntity().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void qwd(BlockDamageEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if (m.vanish.contains(e.getEntity().getName())) {
			e.setCancelled(true);
		}else {
		if (m.vanish.contains(e.getDamager().getName())) {
			e.setCancelled(true);
		}
		}
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPickup(PlayerPickupItemEvent e) {
		if (m.vanish.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onVanishJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (m.vanish.contains(p.getName())) {
			m.getVanishManager().hidePlayerFromOnlinePlayers(p);
		}
		
	}

}
