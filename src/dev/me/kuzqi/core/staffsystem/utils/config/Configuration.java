package dev.me.kuzqi.core.staffsystem.utils.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class Configuration {

    private final Main plugin;
    private File configFile;
    private File messagesFile;
    public String path;
    
    private FileConfiguration configConfig;
    private FileConfiguration messagesConfig;
    
    public Configuration(Main plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "settings.yml");
        this.messagesFile = new File(plugin.getDataFolder(), "messages.yml");
    }


    public FileConfiguration getConfig() {
        return configConfig;
    }

    public FileConfiguration getMessages() {
        return messagesConfig;
    }
    
    public void loadConfigs() {
        initializeFile(configFile, "settings.yml");
        initializeFile(messagesFile, "messages.yml");
        configConfig = YamlConfiguration.loadConfiguration(configFile);
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
    

    public void reloadConfigs() {
    	configConfig = reloadFile(configFile, "settings.yml");
    	messagesConfig = reloadFile(messagesFile, "messages.yml");
    	System.out.println("[System] Reloaded plugin files!");
    }

    
    public void saveConfigs() {
        try {
        	configConfig.save(configFile);
        	messagesConfig.save(messagesFile);
        	System.out.println("[System] Saved plugin files!");
        } catch (IOException e) {
            System.err.println("[System] Error while saving configs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeFile(File file, String configName) {
    	
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        
    	this.path = plugin.getDataFolder().getPath();
    	new File(path).mkdir();
    	file = new File(path, configName);

        if (!file.exists()) {

        	System.out.println("[System] Created new files! " + configName);
        	plugin.saveResource(configName, true);

        } else {
            System.out.println("[System] '" + configName + "' Has been loaded successfully!");
        }
    }
    
    private FileConfiguration reloadFile(File f, String rn) {
        initializeFile(f, rn);
        return YamlConfiguration.loadConfiguration(f);
    }
}