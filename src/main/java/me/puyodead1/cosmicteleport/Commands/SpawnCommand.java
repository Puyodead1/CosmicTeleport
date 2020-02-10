package me.puyodead1.cosmicteleport.Commands;

import me.puyodead1.cosmicteleport.CosmicTeleport;
import me.puyodead1.cosmicteleport.CosmicTeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import static org.bukkit.Bukkit.getServer;

public class SpawnCommand implements CommandExecutor {

    private static final CosmicTeleport cosmicTeleport = CosmicTeleport.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player player = sender instanceof Player ? (Player) sender : null;
        if (player != null) {
            // valid player
            if (player.hasPermission("cosmicteleport.spawn") || player.isOp()) {
                // has permission to teleport to spawn
                final Location spawnLocation = player.getWorld().getSpawnLocation();
                final int teleportDelay = cosmicTeleport.getConfig().getInt("settings.teleport delay");

                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, teleportDelay*20, 1));
                player.getWorld().playEffect(player.getLocation(), Effect.PORTAL, null);

                BukkitScheduler scheduler = getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(cosmicTeleport, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(spawnLocation);
                    }
                }, teleportDelay*20L);
                return true;
            } else {
                // no permission
                CosmicTeleportUtils.sendNoPermission(player);
                return false;
            }
        }
        return false;
    }
}
