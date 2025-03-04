package org.mateh.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.mateh.test.commands.FlyCommand;
import org.mateh.test.commands.MainCommand;
import org.mateh.test.commands.MenuCommand;
import org.mateh.test.commands.XpCommand;
import org.mateh.test.config.MainConfigManager;
import org.mateh.test.listeners.InventoryListener;
import org.mateh.test.listeners.PlayerListener;
import org.mateh.test.managers.MenuInventoryManager;

public final class Main extends JavaPlugin {
    public static String prefix = "&8[&c&lTest&8]";
    private String version = getDescription().getVersion();
    private MainConfigManager mainConfigManager;
    private MenuInventoryManager menuInventoryManager;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);
        menuInventoryManager = new MenuInventoryManager();

        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&fPlugin " + prefix + " &einiciado correctamente. &fVersion: " + version));
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&fPlugin " + prefix + " &ecerrado correctamente. &fVersion: " + version));
    }

    public void registerCommands() {
        this.getCommand("test").setExecutor(new MainCommand(this));
        this.getCommand("experiencia").setExecutor(new XpCommand(this));
        this.getCommand("volar").setExecutor(new FlyCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));

    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public MenuInventoryManager getMenuInventoryManager() {
        return menuInventoryManager;
    }
}
