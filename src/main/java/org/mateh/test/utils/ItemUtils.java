package org.mateh.test.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack generateEmeraldItem(int amount) {
        ItemStack item = new ItemStack(Material.EMERALD, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&aEsmeralda Exótica"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Esta esmeralda puede ser utilizada para"));
        lore.add(MessageUtils.getColoredMessage("&7intercambiar items poderosos."));
        lore.add("");
        lore.add(MessageUtils.getColoredMessage("&7Nivel: &a5"));
        meta.setLore(lore);

        meta.addEnchant(Enchantment.SHARPNESS, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }
}
