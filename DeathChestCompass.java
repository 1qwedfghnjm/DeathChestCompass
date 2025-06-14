package com.aternos.deathchestcompass;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathChestCompass extends JavaPlugin {

    private ChestManager chestManager;
    private CompassTracker compassTracker;

    @Override
    public void onEnable() {
        // Save default config if it doesn't exist
        saveDefaultConfig();
        
        // Initialize managers
        this.chestManager = new ChestManager(this);
        this.compassTracker = new CompassTracker(this);
        
        // Register events
        getServer().getPluginManager().registerEvents(chestManager, this);
        getServer().getPluginManager().registerEvents(compassTracker, this);
        
        // Register commands
        getCommand("deathchest").setExecutor(new DeathChestCommand(this));
        
        getLogger().info("DeathChestCompass has been enabled!");
    }

    @Override
    public void onDisable() {
        // Save all data when plugin disables
        chestManager.saveData();
        getLogger().info("DeathChestCompass has been disabled!");
    }

    public ChestManager getChestManager() {
        return chestManager;
    }

    public CompassTracker getCompassTracker() {
        return compassTracker;
    }
}
