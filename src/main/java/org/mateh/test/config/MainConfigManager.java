package org.mateh.test.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.mateh.test.Main;

import java.util.List;

public class MainConfigManager {
    private CustomConfig configFile;
    private Main main;
    private String preventBlockBreak;
    private boolean welcomeMessageEnabled;
    private List<String> welcomeMessageMessage;

    public MainConfigManager(Main main) {
        this.main = main;
        configFile = new CustomConfig("config.yml", null, main);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();
        preventBlockBreak = config.getString("messages.prevent_block_break");
        welcomeMessageEnabled = config.getBoolean("config.welcome_message.enabled");
        welcomeMessageMessage = config.getStringList("config.welcome_message.message");
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        loadConfig();
    }

    public String getPreventBlockBreak() {
        return preventBlockBreak;
    }

    public boolean isWelcomeMessageEnabled() {
        return welcomeMessageEnabled;
    }

    public List<String> getWelcomeMessageMessage() {
        return welcomeMessageMessage;
    }
}
