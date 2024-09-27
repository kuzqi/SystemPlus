package dev.me.kuzqi.core.staffsystem.utils.plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class PluginMessage {
	
	private final Main m;    
	public PluginMessage(Main plugin) {
		this.m = plugin;
	}
	    
	public void connect(Player p, String server) {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			out.writeUTF("Connect");
			out.writeUTF(server);
			p.sendPluginMessage(m, "BungeeCord", b.toByteArray());
			b.close();
			out.close();
		} catch (Exception e) {
			p.sendMessage(ChatColor.RED + "Error while connecting to " + server);
			e.printStackTrace();
		}
	}
	
	public void sendToLobby(CommandSender cs) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
            return;
        }
        
        Player p = (Player)cs;
        m.getPluginMessage().connect(p, m.getSettingsConfig().getString("servers.lobby-server"));
	}
}