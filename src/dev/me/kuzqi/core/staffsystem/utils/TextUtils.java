package dev.me.kuzqi.core.staffsystem.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author kuzqi
 */
public class TextUtils {

	private final Main m;    
	public TextUtils(Main plugin) {
		this.m = plugin;
	}
	
	public void sendClickableCommandMessage(Player player, String msg, String hover, String command) {
		TextComponent message = new TextComponent(format(msg));
		TextComponent clickablePart = new TextComponent(format(msg));
		clickablePart.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		message.addExtra(clickablePart);
		player.spigot().sendMessage(message);
	}
	
	
	public void sendClickableURLMessage(Player player, String msg, String clickPart, String hover, String url) {
	    TextComponent message = new TextComponent(format(msg));
	    TextComponent clickablePart = new TextComponent(format(clickPart));
	    TextComponent hoverMessage = new TextComponent(format(hover));
	    clickablePart.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverMessage}));
	    clickablePart.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
	    message.addExtra(clickablePart);
	    player.spigot().sendMessage(message);
	}
	
	
	public void sendYouTubeLink(Player p) {
    	p.sendMessage(format("&8┃ &3✪ &8┃ &cVisit our youtube channel"));
    	sendClickableURLMessage(p, "&8┃ &3✪ &8┃ &fBy Clicking here: ", "&c&l[YOUTUBE]", "&cClick here", Messages.YOUTUBE_LINK.from(m.getMessagesConfig()));
	}
	
	
	public void sendDiscordLink(Player p) {
    	p.sendMessage(format("&8┃ &3✪ &8┃ &9Join our discord server"));
    	sendClickableURLMessage(p, "&8┃ &3✪ &8┃ &fBy Clicking here: ", "&9&l[DISCORD]", "&9Click here", Messages.DISCORD_LINK.from(m.getMessagesConfig()));
	}
	
	
	public void sendNameMCLink(Player p) {
    	p.sendMessage(format("&8┃ &3✪ &8┃ &eVote to our server on &eName&aMC"));
    	sendClickableURLMessage(p, "&8┃ &3✪ &8┃ &fBy Clicking here: ", "&e&l[NAMEMC]", "&eClick here", Messages.NAMEMC_LINK.from(m.getMessagesConfig()));
	}
	
	
	public void sendStoreLink(Player p) {
    	p.sendMessage(format("&8┃ &3✪ &8┃ &aVisit our store website"));
    	sendClickableURLMessage(p, "&8┃ &3✪ &8┃ &fBy Clicking here: ", "&a&l[STORE]", "&aClick here", Messages.STORE_LINK.from(m.getMessagesConfig()));
	}
	
	
	public String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
}
