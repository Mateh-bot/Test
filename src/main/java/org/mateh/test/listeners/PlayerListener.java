package org.mateh.test.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.mateh.test.Main;
import org.mateh.test.config.MainConfigManager;
import org.mateh.test.utils.ItemUtils;
import org.mateh.test.utils.MessageUtils;

import java.util.List;
import java.util.Random;

public class PlayerListener implements Listener {
    private Main main;

    public PlayerListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.toLowerCase().contains("anashe")) {
            event.setCancelled(true);
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo está permitida la palabra escrita."));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        MainConfigManager mainConfigManager = main.getMainConfigManager();
        if (mainConfigManager.isWelcomeMessageEnabled()) {
            List<String> message = mainConfigManager.getWelcomeMessageMessage();
            for (String m : message) {
                player.sendMessage(MessageUtils.getColoredMessage(m.replace("%player%", player.getName())));
            }
        }

        // Tpear al spawn
        Location location = new Location(Bukkit.getWorld("world"), 69.5, 79, 69.5, 2, 1);
        player.teleport(location);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().getName().equals("world") && !player.hasPermission("test.admin")) {
            event.setCancelled(true);
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + main.getMainConfigManager().getPreventBlockBreak()));
        }

        Block block = event.getBlock();
        if (block.getType().equals(Material.EMERALD_ORE)) {
            int num = new Random().nextInt(10);
            if (num >= 6) {
                ItemStack item = ItemUtils.generateEmeraldItem(1);
                block.getWorld().dropItemNaturally(block.getLocation(), item);
            }
        }
    }
}
