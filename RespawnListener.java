package com.aternos.deathchest.listeners;

import com.aternos.deathchest.DeathChestCompass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    private final DeathChestCompass plugin;

    public RespawnListener(DeathChestCompass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getChestManager().hasDeathChest(player.getUniqueId())) {
            plugin.getCompassManager().giveCompass(player, 
                plugin.getChestManager().getDeathChest(player.getUniqueId()).getLocation());
        }
    }
}
