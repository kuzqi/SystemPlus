package dev.me.kuzqi.core.staffsystem.utils.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.me.kuzqi.core.staffsystem.Main;

/**
 * @author kuzqi
 */
public class Configuration {

    private final Main plugin;
    private final File configFile;
    private final File messagesFile;

    private FileConfiguration configConfig;
    private FileConfiguration messagesConfig;

    public Configuration(Main plugin) {
        this.plugin = plugin;
        this.plugin.getDataFolder().mkdirs();
        this.configFile = new File(plugin.getDataFolder() + "/settings.yml");
        this.messagesFile = new File(plugin.getDataFolder() + "/messages.yml");
        loadConfigs();
    }

    public FileConfiguration getConfig() {
        return configConfig;
    }

    public FileConfiguration getMessages() {
        return messagesConfig;
    }
    
    public void loadConfigs() {
        configConfig = loadFile(configFile, "settings.yml");
        messagesConfig = loadFile(messagesFile, "messages.yml");
    }

    public void reloadConfigs() {
        loadConfigs();
    }

    public void saveConfigs() {
    	loadConfigs();
    }

}
