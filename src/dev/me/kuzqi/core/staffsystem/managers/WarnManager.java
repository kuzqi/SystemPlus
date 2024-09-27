package dev.me.kuzqi.core.staffsystem.managers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class WarnManager {

	private final Main m;    
	public WarnManager(Main plugin) {
		this.m = plugin;
	}
	
	
	
	public void handleWarnCommand(CommandSender cs, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
			return;
		}
		

		if (!(cs.hasPermission("staff.warn"))) {
    		cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
			return;
		}
		
		if (args.length != 1) {
			cs.sendMessage("§cUsage: /warn §a<player>");
			return;
		}
		
		Player Target = m.getServer().getPlayer(args[0]);
		if (Target == null) {
			cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
			return;
		}
		
		Player p = (Player)cs;
		sendReasonsMessage(p, Target);
	}
	
	
	
	public void handleACWarnCommand(CommandSender cs, String command, String[] args) {
		if (!(cs.hasPermission("staff.warn"))) {
    		cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
			return;
		}
		
		if (args.length != 2) {
			cs.sendMessage("§cUsage: /acwarn §a<player> <reason>");
			return;
		}
		
		Player Target = m.getServer().getPlayer(args[0]);
		if (Target == null) {
			cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
			return;
		}
		
		String Reason = String.valueOf(args[1]);
		if (Reason.isEmpty() || Reason == null) {
			cs.sendMessage("§cUsage: /acwarn §a<player> <reason>");
			return;
		}

		sendWarn(Target, Reason.toUpperCase());
		sendWarnBroadcast(cs, Target, Reason.toUpperCase());
	}
	
	
	public void sendWarnBroadcast(CommandSender cs, Player Target, String Reason) {
		for (Player onlineStaff : m.getServer().getOnlinePlayers()) {
			if (cs.hasPermission("staff.warn")) {
				onlineStaff.sendMessage("§c" +Target.getName()+ " §7Has been warned by §6" +cs.getName()+ " §7for §7§l" +Reason);
			}
		}
	}
	
	
	
	public void sendReasonsMessage(Player p, Player Target) {
		p.sendMessage("§8§m-------------------------");
		p.sendMessage("§c§lChoose a reason to warn §6" +Target.getName());
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[CHEATING]", "§cClick to warn", "/acwarn "+ Target.getName()+ " CHEATING");
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[DISRESPECT]", "§cClick to warn", "/acwarn "+ Target.getName()+ " DISRESPECT");
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[RACISM]", "§cClick to warn", "/acwarn "+ Target.getName()+ " RACISM");
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[EXPLOIT]", "§cClick to warn", "/acwarn "+ Target.getName()+ " EXPLOIT");
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[ANNOYING]", "§cClick to warn", "/acwarn "+ Target.getName()+ " ANNOYING");
		m.getTextUtils().sendClickableCommandMessage(p, "§6§l", "[BREAKING RULES]", "§cClick to send a warn", "/acwarn "+ Target.getName()+ " BREAKING-RULES");
		p.sendMessage("§8§m-------------------------");

	}
	
	
	
	public void sendWarn(Player p, String Reason) {
		for (String msg : m.getMessagesConfig().getStringList("warn-message")) {
        	p.sendMessage(m.getTextUtils().format(msg.toString().replace("$reason", Reason)));
        }

	}
	
}
