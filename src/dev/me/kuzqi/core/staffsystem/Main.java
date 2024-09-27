package dev.me.kuzqi.core.staffsystem;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import dev.me.kuzqi.core.staffsystem.managers.ChatManager;
import dev.me.kuzqi.core.staffsystem.managers.PlayerManager;
import dev.me.kuzqi.core.staffsystem.managers.RankManager;
import dev.me.kuzqi.core.staffsystem.managers.VanishManager;
import dev.me.kuzqi.core.staffsystem.managers.WarnManager;
import dev.me.kuzqi.core.staffsystem.utils.SystemUtils;
import dev.me.kuzqi.core.staffsystem.utils.TextUtils;
import dev.me.kuzqi.core.staffsystem.utils.config.Configuration;
import dev.me.kuzqi.core.staffsystem.utils.plugin.PluginManager;
import dev.me.kuzqi.core.staffsystem.utils.plugin.PluginMessage;

/**
 * @author kuzqi
 */
public class Main extends JavaPlugin {

	private PluginMessage pluginmessage;
	private Configuration configmanager;
	private TextUtils textutils;
	private PluginManager pluginmanager;
	private SystemUtils systemutils;
	private ChatManager chatmanager;
	private PlayerManager playermanager;
	private RankManager rankmanager;
	private VanishManager vanishmanager;
	private WarnManager warnmanager;

	public ArrayList<String> vanish = Lists.newArrayList();
	public ArrayList<String> flight = Lists.newArrayList();
	public Set<Boolean> lockChat = Sets.newHashSet();


	@Override
	public void onEnable() {
        loadThePlugin();
	}
	
	
	@Override
	public void onDisable() {
		unloadThePlugin();
	}
    
    
	
    private void loadThePlugin() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		pluginmessage = new PluginMessage(this);
		textutils = new TextUtils(this);
		systemutils = new SystemUtils(this);
		pluginmanager = new PluginManager(this);
		chatmanager = new ChatManager(this);
		playermanager = new PlayerManager(this);
		rankmanager = new RankManager(this);
		vanishmanager = new VanishManager(this);
		configmanager = new Configuration(this);
		warnmanager = new WarnManager(this);
		getPluginManager().loadThePlugin();
    }
    
    
    private void unloadThePlugin() {
    	getPluginManager().unloadThePlugin();
    }
    
    
	public FileConfiguration getSettingsConfig() {return this.getConfigManager().getConfig();}
	public FileConfiguration getMessagesConfig() {return this.getConfigManager().getMessages();}
	
	public Configuration getConfigManager() {
		return this.configmanager;
	}
	
	public TextUtils getTextUtils() {
		return this.textutils;
	}
	
	public PluginManager getPluginManager() {
		return this.pluginmanager;
	}
	
	public SystemUtils getSystemUtils() {
		return this.systemutils;
	}
	
	public PluginMessage getPluginMessage() {
		return this.pluginmessage;
	}
	
	public ChatManager getChatManager() {
		return this.chatmanager;
	}
	
	public PlayerManager getPlayerManager() {
		return this.playermanager;
	}
	
	public RankManager getRankManager() {
		return this.rankmanager;
	}
	
	public VanishManager getVanishManager() {
		return this.vanishmanager;
	}
	
	public WarnManager getWarnManager() {
		return this.warnmanager;
	}
}