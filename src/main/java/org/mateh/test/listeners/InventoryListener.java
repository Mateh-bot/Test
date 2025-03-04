package org.mateh.test.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.mateh.test.Main;
import org.mateh.test.model.InventoryPlayer;

public class InventoryListener implements Listener {
    private Main main;

    public InventoryListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryPlayer inventoryPlayer = main.getMenuInventoryManager().getInventoryPlayer(player);
        if (inventoryPlayer != null) {
            // Jugador dentro de un inventario
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())) {
                main.getMenuInventoryManager().inventoryClick(inventoryPlayer, event.getSlot());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        main.getMenuInventoryManager().removePlayer(player);
    }
}
