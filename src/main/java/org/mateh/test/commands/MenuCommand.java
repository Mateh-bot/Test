package org.mateh.test.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mateh.test.Main;
import org.mateh.test.model.InventoryPlayer;
import org.mateh.test.utils.MessageUtils;

public class MenuCommand implements CommandExecutor {
    private Main main;

    public MenuCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cComando solo de jugador."));
            return true;
        }

        Player player = (Player) sender;

        main.getMenuInventoryManager().openMainInventory(new InventoryPlayer(player));

        return true;
    }
}
