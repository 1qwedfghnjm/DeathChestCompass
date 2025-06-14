package com.aternos.deathchest.listeners;

import com.aternos.deathchest.DeathChestCompass;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestListener implements Listener {

    private final DeathChestCompass plugin;

    public ChestListener(DeathChestCompass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null || 
            event.getClickedBlock().getType() != Material.CHEST) {
            return;
        }
        
        Location chestLoc = event.getClickedBlock().getLocation();
        if (plugin.getChestManager().isProtected(chestLoc)) {
            Player player = event.getPlayer();
            if (plugin.getChestManager().getDeathChest(player.getUniqueId()) != null &&
                plugin.getChestManager().getDeathChest(player.getUniqueId()).getLocation().equals(chestLoc)) {
                
                plugin.getCompassManager().stopTracking(player);
                plugin.getChestManager().removeDeathChest(player.getUniqueId());
                player.sendMessage(plugin.getConfig().getString("messages.chest-opened", "§aYou have recovered your items!"));
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.CHEST && 
            plugin.getChestManager().isProtected(event.getBlock().getLocation())) {
            
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getConfig().getString("messages.cannot-break", "§cYou cannot break a death chest!"));
        }
    }
}
