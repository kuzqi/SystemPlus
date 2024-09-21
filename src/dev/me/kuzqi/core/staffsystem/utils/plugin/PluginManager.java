package dev.me.kuzqi.core.staffsystem.utils.plugin;

import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.commands.ReloadCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.BroadcastCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.GamemodeCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.HealCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.InvseeCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.RenameCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.SetRankCommand;
import dev.me.kuzqi.core.staffsystem.commands.admincommands.aTPCommand;
import dev.me.kuzqi.core.staffsystem.commands.membercommands.HelpCommand;
import dev.me.kuzqi.core.staffsystem.commands.membercommands.HubCommand;
import dev.me.kuzqi.core.staffsystem.commands.membercommands.SocialsCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.ClearChatCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.FlightCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.LockChatCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.SendServerCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.ServerCommand;
import dev.me.kuzqi.core.staffsystem.commands.staffcommands.VanishCommand;
import dev.me.kuzqi.core.staffsystem.listeners.ChatListener;
import dev.me.kuzqi.core.staffsystem.listeners.JoinListener;
import dev.me.kuzqi.core.staffsystem.listeners.QuitListener;
import dev.me.kuzqi.core.staffsystem.listeners.VanishListener;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class PluginManager {

	private final Main m;
	public PluginManager(Main m) {
		this.m = m;
	}

    public void loadThePlugin() {
		m.getConfigManager().loadConfigs();
		loadCommands();
		loadListeners();
        m.lockChat.clear();
        m.lockChat.add(false);
     	m.vanish.clear();
    	m.flight.clear();
        m.getServer().getConsoleSender().sendMessage(Messages.PLUGIN_ENABLE.from(m.getMessagesConfig()));
    }
    
    public void unloadThePlugin() {
    	m.getConfigManager().saveConfigs();
        m.getServer().getConsoleSender().sendMessage(Messages.PLUGIN_DISABLE.from(m.getMessagesConfig()));
    }
    
	public void loadListeners() {
        m.getServer().getPluginManager().registerEvents(new JoinListener(m), m);
        m.getServer().getPluginManager().registerEvents(new ChatListener(m), m);
        m.getServer().getPluginManager().registerEvents(new QuitListener(m), m);
        m.getServer().getPluginManager().registerEvents(new VanishListener(m), m);
	}

	public void loadCommands() {
		m.getCommand("v").setExecutor(new VanishCommand(m));
		m.getCommand("vtp").setExecutor(new VanishCommand(m));
		m.getCommand("vlist").setExecutor(new VanishCommand(m));
		//
		m.getCommand("fly").setExecutor(new FlightCommand(m));
		//
		m.getCommand("gmc").setExecutor(new GamemodeCommand(m));
		m.getCommand("gms").setExecutor(new GamemodeCommand(m));
		//
		m.getCommand("discord").setExecutor(new SocialsCommand(m));
		m.getCommand("yt").setExecutor(new SocialsCommand(m));
		m.getCommand("dis").setExecutor(new SocialsCommand(m));
		m.getCommand("store").setExecutor(new SocialsCommand(m));
		m.getCommand("youtube").setExecutor(new SocialsCommand(m));
		m.getCommand("namemc").setExecutor(new SocialsCommand(m));
		//
		m.getCommand("help").setExecutor(new HelpCommand(m));
		m.getCommand("hub").setExecutor(new HubCommand(m));
		//
		m.getCommand("broadcast").setExecutor(new BroadcastCommand(m));
		m.getCommand("bc").setExecutor(new BroadcastCommand(m));
		//
		m.getCommand("heal").setExecutor(new HealCommand(m));
		m.getCommand("invsee").setExecutor(new InvseeCommand(m));
		m.getCommand("atp").setExecutor(new aTPCommand(m));
		//
		m.getCommand("cc").setExecutor(new ClearChatCommand(m));
		m.getCommand("clearchat").setExecutor(new ClearChatCommand(m));
		m.getCommand("lc").setExecutor(new LockChatCommand(m));
		m.getCommand("lockchat").setExecutor(new LockChatCommand(m));
		//
		m.getCommand("setrank").setExecutor(new SetRankCommand(m));
		m.getCommand("temprank").setExecutor(new SetRankCommand(m));
		m.getCommand("addrank").setExecutor(new SetRankCommand(m));
		//
		m.getCommand("rename").setExecutor(new RenameCommand(m));
		//
		m.getCommand("server").setExecutor(new ServerCommand(m));
		m.getCommand("send").setExecutor(new SendServerCommand(m));
		m.getCommand("sr").setExecutor(new ReloadCommand(m));
		m.getCommand("systemreload").setExecutor(new ReloadCommand(m));
	}
    
	public void reloadThePlugin(CommandSender cs) {
        if (!(cs.hasPermission("staff.reload"))) {
            cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
            return;
        }

        m.getConfigManager().reloadConfigs();
        cs.sendMessage(Messages.PLUGIN_RELOAD.from(m.getMessagesConfig()));
	}
}
