package dev.me.kuzqi.core.staffsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
	
	public ArrayList<String> vanish = new ArrayList<String>();
	public ArrayList<String> flight = new ArrayList<String>();
	public Set<Boolean> lockChat = new HashSet<>();


	@Override
	public void onEnable() {
        loadThePlugin();
	}
	
	
	@Override
	public void onDisable() {
		unloadThePlugin();
	}
    
    
    private void loadThePlugin() {
        getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
		this.pluginmessage = new PluginMessage(this);
		this.configmanager = new Configuration(this);
		this.textutils = new TextUtils(this);
		this.systemutils = new SystemUtils(this);
		this.pluginmanager = new PluginManager(this);
		this.getPluginManager().loadThePlugin();
    }

    
    private void unloadThePlugin() {
    	this.getPluginManager().unloadThePlugin();
    }
    
	public PluginMessage getPluginMessage() {return this.pluginmessage;}
	public Configuration getConfigManager() {return this.configmanager;}
	public TextUtils getTextUtils() {return this.textutils;}
	public PluginManager getPluginManager() {return this.pluginmanager;}
	public SystemUtils getSystemUtils() {return this.systemutils;}
	public FileConfiguration getSettingsConfig() {return this.getConfigManager().getConfig();}
	public FileConfiguration getMessagesConfig() {return this.getConfigManager().getMessages();}
	
}