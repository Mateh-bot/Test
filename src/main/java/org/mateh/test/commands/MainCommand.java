package org.mateh.test.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mateh.test.Main;
import org.mateh.test.utils.ItemUtils;
import org.mateh.test.utils.MessageUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCommand implements CommandExecutor {
    private Main main;

    public MainCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            //Consola
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cSolo un juegador puede usar este comando"));
            return true;
        }

        // Jugador
        Player player = (Player) sender;

        // /test args[0] args[1] args[2]

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("bienvenido")) {
                // /test bienvenido
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &7Bienvenido &a" + player.getName()));
            } else if (args[0].equalsIgnoreCase("fecha")) {
                // /test fecha
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = dateFormat.format(new Date());
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &7Fecha y hora actual: &e" + date));
            } else if (args[0].equalsIgnoreCase("get")) {
                // /test get <author/version>
                subCommandGet(sender, args);
            } else if (args[0].equalsIgnoreCase("reload")) {
                // /test reload
                subCommandReload(sender);
            } else if (args[0].equalsIgnoreCase("item")) {
                // /test item
                subCommandItem(player, args);

            } else {
                help(sender);
            }
        } else {
            // /test
            help(sender);
        }
        //sender.sendMessage(MessageUtils.getColoredMessage(Test.prefix+" &aUsó el comando &7/test"));
        return true;
    }

    public void help(CommandSender sender) {
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l---------COMANDOS &c&lTEST&f&l---------"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7 - /test bienvenido"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7 - /test fecha"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7 - /test get <autor/version>"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l---------COMANDOS &c&lTEST&f&l---------"));
    }

    public void subCommandGet(CommandSender sender, String[] args) {
        if (!sender.hasPermission("test.command.get")) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo tiene permiso para usar este comando"));
            return;
        }
        if (args.length == 1) {
            // test get
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar &7/test get <autor/version>"));
            return;
        }

        if (args[1].equalsIgnoreCase("autor")) {
            // test get autor
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &7Autor del plugin: &e" + main.getDescription().getAuthors()));
        } else if (args[1].equalsIgnoreCase("version")) {
            // test get version
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &7Version del plugin: &e" + main.getDescription().getVersion()));
        } else {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar &7/test get <autor/version>"));
        }
    }

    public void subCommandReload(CommandSender sender) {
        if (!sender.hasPermission("test.command.reload")) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo tiene permiso para usar este comando"));
            return;
        }
        main.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aConfiguración recargada."));
    }

    public void subCommandItem(Player player, String[] args) {
        if (!player.hasPermission("test.command.item")) {
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo tiene permiso para usar este comando"));
            return;
        }

        int amount = 1;
        if (args.length >= 2) {
            try {
                amount = Integer.parseInt(args[1]);
                if (amount <= 0) {
                    player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cUsa una cantidad válida."));
                    return;
                }
            } catch (NumberFormatException e) {
                player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cUsa una cantidad válida."));
                return;
            }
        }

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cInventario lleno."));
        } else {
            ItemStack item = ItemUtils.generateEmeraldItem(amount);
            player.getInventory().addItem(item);
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aItem recibido."));
        }
    }
}
