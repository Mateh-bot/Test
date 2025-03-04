package org.mateh.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mateh.test.Main;
import org.mateh.test.utils.MessageUtils;

public class FlyCommand implements CommandExecutor {
    private Main main;

    public FlyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("miplugin.commands.volar")) {
            sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cNo tienes permiso para usar ese comando."));
            return true;
        }

        Player player = null;
        if (args.length == 0) {
            if (sender instanceof Player) {
                // Bien
                player = (Player) sender;
            } else {
                // Mal
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cDebes usar &7/volar <jugador>"));
                return true;
            }
        } else {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &cEl jugador &7" + args[0] + " &cno existe o no está conectado."));
                return true;
            }
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aModo de vuelo desactivado."));
            if (!player.equals(sender)) {
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aModo de vuelo desactivado para &e" + player.getName() + " &a."));
            }
        } else {
            player.setAllowFlight(true);
            player.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aModo de vuelo activado."));
            if (!player.equals(sender)) {
                sender.sendMessage(MessageUtils.getColoredMessage(Main.prefix + " &aModo de vuelo activado para &e" + player.getName() + " &a."));
            }
        }
        return true;
    }
}
