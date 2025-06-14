package com.aternos.deathchestcompass;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ChestManager implements Listener {

    private final DeathChestCompass plugin;
    private final Map<UUID, DeathChest> deathChests = new HashMap<>();
    private final Set<Location> protectedChests = new HashSet<>();

    public ChestManager(DeathChestCompass plugin) {
        this.plugin = plugin;
        loadData();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Location deathLocation = player.getLocation();
        
        // Store items and create death chest
        List<ItemStack> items = new ArrayList<>(event.getDrops());
        event.getDrops().clear();
        
        createDeathChest(player, deathLocation, items);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.CHEST && 
            protectedChests.contains(event.getBlock().getLocation())) {
            
            DeathChest deathChest = getDeathChestAt(event.getBlock().getLocation());
            if (deathChest != null && !deathChest.getPlayerId().equals(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You cannot break someone else's death chest!");
            }
        }
    }

    public void createDeathChest(Player player, Location location, List<ItemStack> items) {
        // Implementation similar to previous version
        // with added Aternos optimizations
    }

    public void removeDeathChest(UUID playerId) {
        // Implementation
    }

    public DeathChest getDeathChestAt(Location location) {
        // Implementation
    }

    private void loadData() {
        // Implementation
    }

    public void saveData() {
        // Implementation
    }
}
