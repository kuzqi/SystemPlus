package dev.me.kuzqi.core.staffsystem.utils.config;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;

/**
 * @author kuzqi
 */
public enum Messages {

    NO_PERMISSION("no-permission"),
    
    DISCORD_LINK("links.discord-link"),
    NAMEMC_LINK("links.namemc-link"),
    STORE_LINK("links.store-link"),
    YOUTUBE_LINK("links.youtube-link"),
    
    BLOCKED_MSG("chat-manager.blocked-msg"),
    ANTI_SPAM("chat-manager.anti-spam"),
    CLEAR_CHAT("chat-manager.cleared-chat"),
    LOCKED_CHAT("chat-manager.locked-chat"),
    UNLOCKED_CHAT("chat-manager.unlocked-chat"),
    BORADCAST_FORMAT("chat-manager.broadcast-format"),
	
    NON_ITEM("commands-utils.non-item"),
    NOT_ALLOWED("commands-utils.not-allowed"),
    NOT_ALLOWED_FORSELF("commands-utils.not-allowed-forself"),
    ONLY_PLAYER_CMD("commands-utils.only-player-cmd-execute"),
    UNKNOWN_PLAYER("commands-utils.unknown-player"),
    UNKNOWN_RANK("commands-utils.unknown-rank"),
	
    FLY_CREATIVE_MOD("fly-msg.creative-mode"),
    FLY_ENABLED("fly-msg.fly-enable"),
    FLY_DISABLED("fly-msg.fly-disable"),
	
    VANISH_ENABLE("vanish-msg.vanish-enable"),
    VANISH_DISABLE("vanish-msg.vanish-disable"),
    VANISH_HEADER_LIST("vanish-msg.vanish-list"),
    VANISH_EMPTY_LIST("vanish-msg.empty-vanish-list"),
    VANISH_TP_RULE("vanish-msg.vanishtp-rule"),
	
    PLUGIN_BY("plugin-copyright.plugin-by"),
    PLUGIN_ENABLE("plugin-copyright.plugin-startup-msg"),
    PLUGIN_DISABLE("plugin-copyright.plugin-shutdown-msg"),
    PLUGIN_RELOAD("plugin-copyright.plugin-reload");
	
    private final String msg;

    Messages(String msg) {
        this.msg = msg;
    }
    
    public String from(FileConfiguration fc) {
        String message = fc.getString(msg);
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
