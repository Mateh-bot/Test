package org.mateh.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mateh.test.Main;
import org.mateh.test.utils.MessageUtils;

public class XpCommand implements CommandExecutor {
    private Main main;

    public XpCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("miplugin.commands.experiencia")) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo tienes permiso para usar ese comando."));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar &7/experiencia <cantidad> (opcional) <jugador>"));
            return true;
        }

        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(args[0]);
            if (cantidad <= 0) {
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar una cantidad valida."));
                return true;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar una cantidad valida."));
            return true;
        }

        Player player = null;
        if (args.length == 1) {
            if (sender instanceof Player) {
                // Bien
                player = (Player) sender;
            } else {
                // Mal
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar &7/experiencia <cantidad> <jugador>"));
                return true;
            }
        } else {
            player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cEl jugador &7" + args[1] + " &cno existe o no está conectado."));
                return true;
            }
        }

        player.giveExpLevels(cantidad);
        player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aHas recibido &e" + cantidad + " &aniveles de experiencia."));
        sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aEl jugador &7" + player.getName() + " &aha recibido &e" + cantidad + " &aniveles de experiencia."));
        return true;
    }
}
