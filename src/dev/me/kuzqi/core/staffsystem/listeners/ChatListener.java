package dev.me.kuzqi.core.staffsystem.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class ChatListener implements Listener {

    private final Main m;
    private final Set<String> blockedWords;

    public ChatListener(Main m) {
        this.m = m;
        this.blockedWords = new HashSet<>(m.getSettingsConfig().getStringList("blocked-words"));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
    	Player p = e.getPlayer();
    	
        if (m.lockChat.contains(true) && !p.hasPermission("lockchat.bypass")) {
            e.setCancelled(true);
        }
        
        String message = e.getMessage().toLowerCase();
        int duration = m.getSettingsConfig().getInt(String.valueOf("mute.duration"));
        
        if (blockedWords.stream().anyMatch(message::contains) && !p.hasPermission("mutechat.bypass")) {
            e.setCancelled(true);
            
            m.getServer().dispatchCommand(m.getServer().getConsoleSender(), 
            	"mute " +p.getName().toString()+ " " + duration+"m " + "&cSending blocked messages");

            return;
        }
    }
}
