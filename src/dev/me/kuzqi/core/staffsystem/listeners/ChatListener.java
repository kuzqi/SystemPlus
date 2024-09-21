package dev.me.kuzqi.core.staffsystem.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

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
        String message = e.getMessage().toLowerCase();
        
        if (blockedWords.stream().anyMatch(message::contains)) {
            e.setCancelled(true);
            p.sendMessage(Messages.BLOCKED_MSG.from(m.getMessagesConfig()));
            return;
        }

        if (m.lockChat.contains(true) && !p.hasPermission("lockchat.bypass")) {
            e.setCancelled(true);
        }
    }
}
