package org.mateh.test.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mateh.test.Main;
import org.mateh.test.model.InventoryPlayer;
import org.mateh.test.model.InventorySection;
import org.mateh.test.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuInventoryManager {

    private ArrayList<InventoryPlayer> players;

    public MenuInventoryManager() {
        this.players = new ArrayList<>();
    }

    public InventoryPlayer getInventoryPlayer(Player player) {
        for (InventoryPlayer inventoryPlayer : players) {
            if (inventoryPlayer.getPlayer().equals(player)) {
                return inventoryPlayer;
            }
        }
        return null;
    }

    public void removePlayer(Player player) {
        players.removeIf(inventoryPlayer -> inventoryPlayer.getPlayer().equals(player));
    }

    public void openMainInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_MAIN);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&4Menu del Servidor"));

        // Decoracion
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for (int i = 0; i <= 9; i++) {
            inv.setItem(i, item);
        }
        for (int i = 17; i <= 18; i++) {
            inv.setItem(i, item);
        }
        for (int i = 26; i <= 27; i++) {
            inv.setItem(i, item);
        }
        for (int i = 35; i <= 36; i++) {
            inv.setItem(i, item);
        }
        for (int i = 44; i <= 53; i++) {
            inv.setItem(i, item);
        }

        // Informacion
        item = new ItemStack(Material.PAPER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Información del Usuario"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Nivel: &a" + player.getLevel()));
        lore.add(MessageUtils.getColoredMessage("&7Experiencia: &a" + player.getTotalExperience()));
        lore.add(MessageUtils.getColoredMessage("&7Ping: &a" + player.getPing()));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        // Banco
        item = new ItemStack(Material.DIAMOND);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Banco"));
        lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Accede a tu cuenta bancaria."));
        lore.add(MessageUtils.getColoredMessage("&7Solo para VIPS."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(24, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openBankInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_BANK);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&4Menu del Banco"));

        // Decoracion
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        for (int i = 0; i <= 9; i++) {
            inv.setItem(i, item);
        }
        for (int i = 17; i <= 18; i++) {
            inv.setItem(i, item);
        }
        for (int i = 26; i <= 27; i++) {
            inv.setItem(i, item);
        }
        for (int i = 35; i <= 36; i++) {
            inv.setItem(i, item);
        }
        for (int i = 44; i <= 53; i++) {
            inv.setItem(i, item);
        }

        // Atras
        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&7Atrás"));
        item.setItemMeta(meta);
        inv.setItem(45, item);

        // Cofre Minerales
        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Minerales"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Cofre de minerales."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        // Cofre Armas
        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Armas"));
        lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Cofre de armas."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(22, item);

        // Cofre Herramientas
        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Herramientas"));
        lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Cofre de herramientas."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(24, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openMineralesInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_MINERALES);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&4Minerales"));

        // Atras
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&7Atrás"));
        item.setItemMeta(meta);
        inv.setItem(45, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openArmasInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_ARMAS);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&4Armas"));

        // Atras
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&7Atrás"));
        item.setItemMeta(meta);
        inv.setItem(45, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openHerramientasInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_HERRAMIENTAS);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&4Herramientas"));

        // Atras
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&7Atrás"));
        item.setItemMeta(meta);
        inv.setItem(45, item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void inventoryClick(InventoryPlayer inventoryPlayer, int slot) {
        Player player = inventoryPlayer.getPlayer();
        InventorySection section = inventoryPlayer.getSection();
        if (section.equals(InventorySection.MENU_MAIN)) {
            if (slot == 24) {
                if (!player.hasPermission("test.inventario.banco")) {
                    player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cSolos pueden usar los VIPS"));
                    return;
                }
                openBankInventory(inventoryPlayer);
            }
        } else if (section.equals(InventorySection.MENU_BANK)) {
            if (slot == 20) {
                openMineralesInventory(inventoryPlayer);
            }
            if (slot == 22) {
                openArmasInventory(inventoryPlayer);
            }
            if (slot == 24) {
                openHerramientasInventory(inventoryPlayer);
            }
            if (slot == 45) {
                openMainInventory(inventoryPlayer);
            }
        } else if (section.equals(InventorySection.MENU_MINERALES) ||
                section.equals(InventorySection.MENU_ARMAS) ||
                section.equals(InventorySection.MENU_HERRAMIENTAS)) {
            if (slot == 45) {
                openBankInventory(inventoryPlayer);
            }
        }
    }
}
