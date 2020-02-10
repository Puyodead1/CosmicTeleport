package me.puyodead1.cosmicteleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CosmicTeleportUtils {

    public static String Color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color(msg));
    }

    public static void sendPlayer(Player player, String msg) {
        player.sendMessage(Color(msg));
    }

    public static void sendNoPermission(Player player) {
        sendPlayer(player, CosmicTeleport.plugin.getConfig().getString("messages.no permission"));
    }
}
