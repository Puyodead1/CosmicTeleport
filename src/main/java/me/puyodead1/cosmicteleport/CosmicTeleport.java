package me.puyodead1.cosmicteleport;

import me.puyodead1.cosmicteleport.Commands.SpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CosmicTeleport extends JavaPlugin {

    public static CosmicTeleport plugin;

    private final String PREFIX = "&7[&dCosmicTeleport&7] ";

    @Override
    public void onEnable() {
        plugin = this;

        CosmicTeleportUtils.sendConsole(PREFIX + "&b=============================================================");

        // Ensure essentials is installed
        if(!Bukkit.getPluginManager().getPlugin("Essentials").isEnabled()) {
            CosmicTeleportUtils.sendConsole("&cEssentials not enabled or not installed! Plugin will be disabled!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        initConfig();
        initCommands();

        CosmicTeleportUtils.sendConsole(PREFIX + "&d========================");
        CosmicTeleportUtils.sendConsole(PREFIX + "&bAuthor: &ePuyodead1");
        CosmicTeleportUtils.sendConsole(PREFIX + "&b" + this.getDescription().getName() + " Version: &e" + getServer().getPluginManager().getPlugin(this.getDescription().getName()).getDescription().getVersion());
        CosmicTeleportUtils.sendConsole(PREFIX + "&bEssentials Version: &e" + getServer().getPluginManager().getPlugin("Essentials").getDescription().getVersion());
        CosmicTeleportUtils.sendConsole(PREFIX + "&bMinecraft Version: &e" + getServer().getVersion());
        CosmicTeleportUtils.sendConsole(PREFIX + "&b=============================================================");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initConfig() {
        long STARTED = System.currentTimeMillis();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        CosmicTeleportUtils.sendConsole(PREFIX + "&bLoaded Configuration &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void initCommands() {
        long STARTED = System.currentTimeMillis();

        getCommand("spawn").setExecutor(new SpawnCommand());

        CosmicTeleportUtils.sendConsole(PREFIX + "&bLoaded Commands &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }
}
